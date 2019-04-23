package capt.sunny.labs.l6;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Commands {
    SHOW("\nshow;\n\tвывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"),
    IMPORT("\nload {\"fileName\":\"path/to/file\"};" +
            "\n\tSet file with data (from client)\n"),
    EXIT("\nexit;\n\tвыход с сохранением\n"),
    INFO("\ninfo;\n\tвывести в стандартный поток вывода информацию о коллекции (тип, дата \n\tинициализации, количество элементов)\n"),
    LOAD("\nload {\"fileName\":\"path/to/file\"};" +
            "\n\tSet file with data (from server)\n"),
    SAVE("\nsave;\n\tсохранить коллекцию в файл\n"),
    HELP("\nhelp;\n\tпоказать этот текст\n"),
    INSERT("\ninsert {String key} {element};\n\tдобавить новый элемент с заданным ключом\n\tпример:" +
            "\n\tinsert{\n" +
            "\t       \"key\":\"key0\"\n" +
            "\t      } {\n" +
            "\t         \"element\":{\n" +
            "\t                   \"name\":\"name3\",\n" +
            "\t                   \"age\":270,\n" +
            "\t                   \"height\":345.34,\n" +
            "\t                   \"type\":\"human\",\n" +
            "\t                   \"isLive\":true,\n" +
            "\t                   \"creationDate\":\"10 Apr 2019 17:42:26 GMT\",\n" +
            "\t                   \"location\":{\n" +
            "\t                           \"x\":23.23,\n" +
            "\t                           \"y\":23.2,\n" +
            "\t                           \"z\":25.2\n" +
            "\t                          }\n" +
            "\t                   }\n" +
            "\t          };\n"),
    ADD_IF_MIN("\nadd_if_min {element};\n\tдобавить новый элемент в коллекцию, если его значение\n\tменьше, чем у наименьшего элемента этой коллекции(сравнение по возрасту)\n\tпример:" +
            "\n\tadd_if_min {\n" +
            "\t             \"element\":{\n" +
            "\t                   \"name\":\"name3\",\n" +
            "\t                   \"age\":270,\n" +
            "\t                   \"height\":345.34,\n" +
            "\t                   \"type\":\"human\",\n" +
            "\t                   \"isLive\":true,\n" +
            "\t                   \"creationDate\":\"10 Apr 2019 17:42:26 GMT\",\n" +
            "\t                   \"location\":{\n" +
            "\t                           \"x\":23.23,\n" +
            "\t                           \"y\":23.2,\n" +
            "\t                           \"z\":25.2\n" +
            "\t                          }\n" +
            "\t                   }\n" +
            "\t             };\n"),
    REMOVE_LOWER("\nremove_lower {String key}; удалить из коллекции все элементы, ключ которых меньше, чем заданный\n\tпример:" +
            "\n\tremove_lower{\n" +
            "\t              \"key\":\"key0\"\n" +
            "\t            }\n"),
    REMOVE("\nremove {String key};\n\tудалить элемент из коллекции по его ключу\n\tпример: " +
            "\n\tremove{\n" +
            "\t       \"key\":\"key0\"\n" +
            "\t      }\n");

    private String manual;
    private static String help = ADD_IF_MIN.manual+EXIT.manual+HELP.manual+REMOVE.manual+REMOVE_LOWER.manual+INSERT.manual+INFO.manual+LOAD.manual+SHOW.manual+SAVE.manual+IMPORT.manual;
    private static final String commandNames = Arrays.asList(values()).stream().map(e->String.format("%s,",e.name())).collect(Collectors.joining());
    public String man() {
        return manual;
    }

    public static boolean check(String command){
        return commandNames.contains(command.toUpperCase());
    }

    public static String help(){
        commandNames.contains("ass");
        return help;
    }

    Commands(String _manual){
        manual = _manual;
    }
}
