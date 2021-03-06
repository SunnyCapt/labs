package capt.sunny.labs.l7.serv;

import capt.sunny.labs.l7.Command;
import capt.sunny.labs.l7.IOTools;
import capt.sunny.labs.l7.ObjectOutputStreamWrapper;
import capt.sunny.labs.l7.User;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidParameterException;

public class AnswerMSocket implements Runnable {
    Command command;
    DataManager dataManager;
    String message = "";
    Exception[] exception;
    ObjectOutputStreamWrapper oos;
    User user;

    public AnswerMSocket(Command _command, DataManager _dataManager, ObjectOutputStreamWrapper _oos, Exception[] _exception, User _user) {
        command = _command;
        dataManager = _dataManager;
        exception = _exception;
        oos = _oos;
        user = _user;
    }

    @Override
    public void run() {
        String errMessage = null;
        // token verification
        if (!(command.getUserName() == null || command.getToken() == null || command.getUserName().isEmpty() || command.getToken().isEmpty())
                && !command.getName().equals("login") && !command.getName().equals("help") && !command.getName().equals("signin") && !command.getName().equals("signin_finish")) {
            if (!(command.getUserName().equals(user.getNick()) &&
                    command.getToken().equals(user.getToken()) &&
                    user.isTokenValid())) {
                errMessage = "Wrong token, login again...";
            }
        }
        try {
            message = errMessage == null ? CommandExecutor.execute(dataManager, command, "UTF-8") : errMessage;
        } catch (InvalidParameterException | FileSavingException e) {
            message = "Invalid: " + e.getMessage();
            System.out.println(message);
        } catch (Exception e) {
            exception[0] = e;
        }
        try {
            if (!message.isEmpty()) {
                if (errMessage == null && (command.getName().equals("login")||command.getName().equals("signin_finish"))){
                    JSONObject tempJSON = new JSONObject(message);
                    if (tempJSON.has("token")) {
                        user.updateToken(tempJSON.getString("token"));
                        user.setNick(command.getFirstParameter());
                    }
                }
                IOTools.sendObject(oos, message, String.class.getName(), false, true);
            }
        } catch (InterruptedException ignored) {

        } catch (IOException e) {
            exception[0] = e;
        }
    }

}
