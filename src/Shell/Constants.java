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
    public static final String SH = "sh";

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
    public static final String SH_DESC = "Executes all command in a file as if you had typed them out";

    //ASCII letters
    static final int ASCII_SIZE = 5;
    static final String[] ASCII_A = new String[] {
            "  _|_|  ",
            "_|    _|",
            "_|_|_|_|",
            "_|    _|",
            "_|    _|"
    };

    static final String[] ASCII_B = new String[] {
            "_|_|_|  ",
            "_|    _|",
            "_|_|_|  ",
            "_|    _|",
            "_|_|_|  "
    };

    static final String[] ASCII_C = new String[] {
            "  _|_|_|",
            "_|      ",
            "_|      ",
            "_|      ",
            "  _|_|_|"
    };

    static final String[] ASCII_D = new String[] {
            "_|_|_|  ",
            "_|    _|",
            "_|    _|",
            "_|    _|",
            "_|_|_|  "
    };

    static final String[] ASCII_E = new String[] {
            "_|_|_|_|",
            "_|      ",
            "_|_|_|  ",
            "_|      ",
            "_|_|_|_|"
    };

    static final String[] ASCII_F = new String[] {
            "_|_|_|_|",
            "_|      ",
            "_|_|_|  ",
            "_|      ",
            "_|      "
    };

    static final String[] ASCII_G = new String[] {
            "  _|_|_|",
            "_|      ",
            "_|  _|_|",
            "_|    _|",
            "  _|_|_|"
    };

    static final String[] ASCII_H = new String[] {
            "_|    _|",
            "_|    _|",
            "_|_|_|_|",
            "_|    _|",
            "_|    _|"
    };

    static final String[] ASCII_I = new String[] {
            "_|_|_|",
            "  _|  ",
            "  _|  ",
            "  _|  ",
            "_|_|_|"
    };

    static final String[] ASCII_J = new String[] {
            "      _|",
            "      _|",
            "      _|",
            "_|    _|",
            "  _|_|  "
    };

    static final String[] ASCII_K = new String[] {
            "_|    _|",
            "_|  _|  ",
            "_|_|    ",
            "_|  _|  ",
            "_|    _|"
    };

    static final String[] ASCII_L = new String[] {
            "_|      ",
            "_|      ",
            "_|      ",
            "_|      ",
            "_|_|_|_|"
    };

    static final String[] ASCII_M = new String[] {
            "_|      _|",
            "_|_|  _|_|",
            "_|  _|  _|",
            "_|      _|",
            "_|      _|"
    };

    static final String[] ASCII_N = new String[] {
            "_|      _|",
            "_|_|    _|",
            "_|  _|  _|",
            "_|    _|_|",
            "_|      _|"
    };

    static final String[] ASCII_O = new String[] {
            "  _|_|  ",
            "_|    _|",
            "_|    _|",
            "_|    _|",
            "  _|_|  "
    };

    static final String[] ASCII_P = new String[] {
            "_|_|_|  ",
            "_|    _|",
            "_|_|_|  ",
            "_|      ",
            "_|      "
    };

    static final String[] ASCII_Q = new String[] {
            "  _|_|    ",
            "_|    _|  ",
            "_|  _|_|  ",
            "_|    _|  ",
            "  _|_|  _|"
    };

    static final String[] ASCII_R = new String[] {
            "_|_|_|  ",
            "_|    _|",
            "_|_|_|  ",
            "_|    _|",
            "_|    _|"
    };

    static final String[] ASCII_S = new String[] {
            "  _|_|_|",
            "_|      ",
            "  _|_|  ",
            "      _|",
            "_|_|_|  "
    };

    static final String[] ASCII_T = new String[] {
            "_|_|_|_|_|",
            "    _|    ",
            "    _|    ",
            "    _|    ",
            "    _|    "
    };

    static final String[] ASCII_U = new String[] {
            "_|    _|",
            "_|    _|",
            "_|    _|",
            "_|    _|",
            "  _|_|  "
    };

    static final String[] ASCII_V = new String[] {
            "_|      _|",
            "_|      _|",
            "_|      _|",
            "  _|  _|  ",
            "    _|    "
    };

    static final String[] ASCII_W = new String[] {
            "_|          _|",
            "_|          _|",
            "_|    _|    _|",
            "  _|  _|  _|  ",
            "    _|  _|    "
    };

    static final String[] ASCII_X = new String[] {
            "_|      _|",
            "  _|  _|  ",
            "    _|    ",
            "  _|  _|  ",
            "_|      _|"
    };

    static final String[] ASCII_Y = new String[] {
            "_|      _|",
            "  _|  _|  ",
            "    _|    ",
            "    _|    ",
            "    _|    "
    };

    static final String[] ASCII_Z = new String[] {
            "_|_|_|_|_|",
            "      _|  ",
            "    _|    ",
            "  _|      ",
            "_|_|_|_|_|"
    };

    static final String[] ASCII_SPACE = new String[]{
            " ",
            " ",
            " ",
            " ",
            " "
    };

    static final String[] ASCII_DOUBLE_SPACE = new String[]{
            "  ",
            "  ",
            "  ",
            "  ",
            "  "
    };

    static final String[] ASCII_ERROR = new String[]{
            "",
            "",
            "",
            "",
            ""
    };

}
