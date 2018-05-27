package Shell;

public class Constants {
    // startup
    public static final String welcomeText = "Welcome to kShelly!";

    // missing info
    public static final String missingFileDir = "File/Directory with such name exists";

    // commands
    public static final String MAN = "man";
    public static final String TOUCH = "touch";
    public static final String RM = "rm";
    public static final String MKDIR = "mkdir";
    public static final String RMDIR = "rmdir";
    public static final String LS = "ls";
    public static final String LL = "ll";
    public static final String ECHO = "echo";
    public static final String CD = "cd";
    public static final String PWD = "pwd";
    public static final String WRITE = "write";
    public static final String CAT = "cat";
    public static final String QUIT = "quit";

    // command descriptions
    public static final String MAN_DESC = "prints out a help menu for a given command";
    public static final String TOUCH_DESC = "creates a file named <fileName>";
    public static final String RM_DESC = "removes the file named <fileName>";
    public static final String MKDIR_DESC = "creates a folder named <directoryName>";
    public static final String RMDIR_DESC = "removes the folder named <directoryName>";
    public static final String LS_DESC = "lists all files and directories in current folder";
    public static final String LL_DESC = "lists all files and directories in greater detail";
    public static final String ECHO_DESC = "outputs content to terminal output";
    public static final String CD_DESC = "changes directory to given folder";
    public static final String PWD_DESC = "prints present working directory";
    public static final String WRITE_DESC = "writes <content> to <fileName>";
    public static final String CAT_DESC = "Prints content in <fileName>";
    public static final String QUIT_DESC = "logout";
}
