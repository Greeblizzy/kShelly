package Shell;

public class Constants {
    private Constants() {}

    // missing info
    static final String missingFileDir = "File/Directory with such name exists";

    // commands
    public static final String MAN = "man";
    static final String TOUCH = "touch";
    static final String RM = "rm";
    static final String MKDIR = "mkdir";
    static final String RMDIR = "rmdir";
    static final String LS = "ls";
    static final String LL = "ll";
    static final String ECHO = "echo";
    static final String CD = "cd";
    static final String PWD = "pwd";
    static final String WRITE = "write";
    static final String CAT = "cat";
    static final String QUIT = "quit";
    static final String SH = "sh";
    static final String MV = "mv";

    // command descriptions
    public static final String MAN_DESC = "prints out a help menu for a given command";
    static final String TOUCH_DESC = "creates a file named <fileName>";
    static final String RM_DESC = "removes the file named <fileName>";
    static final String MKDIR_DESC = "creates a folder named <directoryName>";
    static final String RMDIR_DESC = "removes the folder named <directoryName>";
    static final String LS_DESC = "lists all files and directories in current folder";
    static final String LL_DESC = "lists all files and directories in greater detail";
    static final String ECHO_DESC = "outputs content to terminal output";
    static final String CD_DESC = "changes directory to given folder";
    static final String PWD_DESC = "prints present working directory";
    static final String WRITE_DESC = "writes <content> to <fileName>";
    static final String CAT_DESC = "Prints content in <fileName>";
    static final String QUIT_DESC = "logout";
    static final String SH_DESC = "Executes all command in a file as if you had typed them out";
    static final String MV_DESC = "moves <origin> to <destination>";

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
