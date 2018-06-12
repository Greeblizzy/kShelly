package Shell;

import Helpers.FileSystem;
import Shell.Exception.UnexpectedDirectoryException;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KShell implements FileSystem<Base> {
    private static final Map<String, String> manPages = new HashMap<>();
    private GDirectory root;
    private GDirectory currDir;
    private boolean quit = false;

    static {
        manPages.put(Constants.MAN, Constants.MAN_DESC);
        manPages.put(Constants.TOUCH, Constants.TOUCH_DESC);
        manPages.put(Constants.RM, Constants.RM_DESC);
        manPages.put(Constants.MKDIR, Constants.MKDIR_DESC);
        manPages.put(Constants.RMDIR, Constants.RMDIR_DESC);
        manPages.put(Constants.LS, Constants.LS_DESC);
        manPages.put(Constants.LL, Constants.LL_DESC);
        manPages.put(Constants.ECHO, Constants.ECHO_DESC);
        manPages.put(Constants.CD, Constants.CD_DESC);
        manPages.put(Constants.PWD, Constants.PWD_DESC);
        manPages.put(Constants.WRITE, Constants.WRITE_DESC);
        manPages.put(Constants.CAT, Constants.CAT_DESC);
        manPages.put(Constants.SH, Constants.SH_DESC);
        manPages.put(Constants.QUIT, Constants.QUIT_DESC);
        manPages.put(Constants.MV, Constants.MV_DESC);
    }

    public KShell() {
        this(new GDirectory("/", null));
    }

    private KShell(GDirectory root) {
        this.currDir = this.root = root;
    }

    public boolean isRunning() {
        return !quit;
    }

    @Override
    public void clearSystem() {
        currDir = root;
        root.clearSystem();
    }

    @Override
    public String runCommand(String cmd, String... kwargs) throws Exception {
        // String... = an array as runCommand(cmd, "1", "2", "3") - forces it to be the last param in a method
        String output = "";

        // TODO: convert into World.Commands
        switch (cmd) {
            case Constants.MAN:         // line = ["manPages", "<content>"]
                assertEQ(1, kwargs.length, "Expected: man <command>");
                output = manPages.getOrDefault(kwargs[0], "Invalid Command");
                break;
            case Constants.TOUCH:       // line = ["touch", "<fileName>"]
                assertEQ(1, kwargs.length, "Expected: touch <fileName>");
                if (currDir.containsDirectory(kwargs[0]))
                    throw new UnexpectedDirectoryException("Naming Conflict");

                String[] fileName = kwargs[0].split("\\.");
                currDir.add(new GFile(kwargs[0], fileName.length == 2 ? FileType.get(fileName[1]) : FileType.TXT));
                break;
            case Constants.RM:          // line = ["rm", "<fileName>"]
                assertEQ(1, kwargs.length, "Expected: rm <fileName>");
                assertIsFile(kwargs[0]);

                currDir.remove(kwargs[0]);
                break;
            case Constants.MKDIR:       // line = ["mkdir", "<directoryName>"]
                assertEQ(1, kwargs.length, "Expected: mkdir <directoryName>");
                if (currDir.contains(kwargs[0]))
                    throw new UnexpectedDirectoryException(Constants.missingFileDir);

                currDir.add(new GDirectory(kwargs[0], currDir));
                break;
            case Constants.RMDIR:
                assertEQ(1, kwargs.length, "Expected: rmdir <directoryName>");
                if (!currDir.containsDirectory(kwargs[0]) || !((GDirectory) currDir.get(kwargs[0])).isEmpty())
                    throw new UnexpectedDirectoryException("Directory not found");

                currDir.remove(kwargs[0]);
                break;
            case Constants.LL:
                output = currDir.toString();
            case Constants.LS:
                // TODO: print files/directory
                break;
            case Constants.ECHO:
                output = Arrays.stream(kwargs).reduce((a, b) -> String.format("%s %s", a, b)).orElse("Empty");
                break;
            case Constants.CD:
                assertEQ(1, kwargs.length, "Expected: cd <directoryName>");
                processCD(kwargs[0]);
                break;
            case Constants.PWD:
                GDirectory temp = currDir;
                StringBuilder presentWorkingDirectory = new StringBuilder();
                do {
                    presentWorkingDirectory.insert(0, temp.getName());
                    if (currDir != root)
                        presentWorkingDirectory.insert(0, "/");
                } while ((temp = temp.getParent()) != root);

                output = presentWorkingDirectory.toString();
                break;
            case Constants.WRITE:
                assertIsFile(kwargs[0]);

                StringBuilder content = new StringBuilder();
                for (int wordIndex = 1; wordIndex < kwargs.length; wordIndex++)
                    content.append(kwargs[wordIndex]).append(" ");

                ((GFile) currDir.get(kwargs[0])).append(content.toString());
                break;
            case Constants.CAT:
                assertEQ(1, kwargs.length, "Expected: cat <fileName");
                assertIsFile(kwargs[0]);

                output = ((GFile) currDir.get(kwargs[0])).read();
                break;
            case Constants.QUIT:
                quit = true;
                break;
            case Constants.SH:      // internal script
                assertEQ(1, kwargs.length, "Expected: sh <script.sh>");
                assertIsFile(kwargs[0]);

                // read the gfile
                String[] commandLines = ((GFile)currDir.get(kwargs[0])).read().split("\n");
                try {
                    for (String commandLine : commandLines) {
                        String[] cmd_ = commandLine.split(" ");
                        System.out.println(runCommand(cmd_[0], Arrays.copyOf(cmd_, cmd_.length - 1)));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case Constants.MV:
                assertIsFile(kwargs[0]);
                assertEQ(2, kwargs.length, "Expected: mv <origin> <destination>");
                move(kwargs[0], kwargs[1]);
                break;
            case "#": case "": break;
            default:
                throw new IllegalArgumentException("No such command");
        }
        return output;
    }

    private void assertEQ(int expected, int actual, String errorMsg) throws IllegalArgumentException {
        if (expected != actual)
            throw new IllegalArgumentException(errorMsg);
    }

    private GFile assertIsFile(String path) throws FileSystemException {
        var file = multiDirectory(path);
        if (!(file instanceof GFile))
            throw new UnexpectedDirectoryException(path + " is actually a directory");

        return (GFile) file;
    }

    private GDirectory assertIsDirectory(String path) throws FileSystemException {
        var dir = multiDirectory(path);
        if (!(dir instanceof GDirectory))
            throw new UnexpectedDirectoryException(path + " is actually a File");

        return (GDirectory) dir;
    }

    private void processCD(String path) throws InvalidPathException {
        Base location = multiDirectory(path);
        if (location.isFile())
            throw new InvalidPathException(path, "Can't cd into a file");
        currDir = (GDirectory) location;
    }

    private GDirectory symbolicLink(String path, GDirectory curr) {
        switch (path) {
            case "/":
                return root;
            case ".":   // empty
                return curr;
            case "..":
                return curr.getParent();
            default:
                return null;
        }
    }

    /**
     * converts some/path/to/Location -> Base for "some/path/to/Location"
     * @param path some/path/to/Location
     * @return Base for "some/path/to/Location"
     * @throws InvalidPathException invalid path
     */
    private Base multiDirectory(String path) throws InvalidPathException {
        Base curr = symbolicLink(path, currDir);
        if (curr != null)
            return curr;

        if (!path.contains("/"))        // look in current directory only
            if (currDir.contains(path))
                return currDir.get(path);
            else
                throw new InvalidPathException(path, "Not found");

        // does it start at root
        curr = currDir;
        if (path.charAt(0) == '/') {
            curr = root;
            path = path.substring(1);
        }

        // multi directory support
        GDirectory gCurr;
        for (String dir : getPathDirectory(path).split("/")) {
            if (curr instanceof GFile)
                throw new InvalidPathException(path, "file in middle of path");

            gCurr = ((GDirectory) curr);
            if (gCurr.contains(dir))
                curr = gCurr.get(dir);
            else if ((gCurr = symbolicLink(dir, gCurr)) != null)
                curr = gCurr;
            else
                throw new InvalidPathException(path, "Base doesn't exist");
        }

        if (curr.isFile())
            throw new InvalidPathException(path, "file in middle of path");

        gCurr = (GDirectory) curr;
        if ((gCurr = symbolicLink(getPathLocation(path), gCurr)) != null)
            return gCurr;

        gCurr = (GDirectory) curr;
        if (!gCurr.contains(getPathLocation(path)))
            throw new InvalidPathException(path, "Base doesn't exist");
        return gCurr.get(getPathLocation(path));
    }

    private void move(String src, String dest) throws FileSystemException, FileNotFoundException {
        GDirectory srcDir = assertIsDirectory(getPathDirectory(src));
        if (!srcDir.contains(getPathLocation(src)))
            throw new FileNotFoundException(src + " is invalid");

        GDirectory destDir = assertIsDirectory(getPathDirectory(dest));
        if (destDir.contains(getPathLocation(src)))
            throw new FileSystemException("Duplicate Name");

        Base srcLocation = srcDir.get(getPathLocation(src));
        srcLocation.setName(getPathLocation(dest));

        // now actually do the move
        // you now have 2 directories, move the desired file from one to the other
        // remove from src, add to dest
        destDir.add(srcDir.remove(srcLocation.getName()));
    }

    /**
     * converts some/path/to/Location -> "some/path/to"
     * @param path some/path/to/Location
     * @return "some/path/to"
     */
    private String getPathDirectory(String path) {
        if (path.equals("/"))
            return "/";
        return path.contains("/") ? path.substring(0, path.lastIndexOf("/") -1) : "" ;
    }

    /**
     * converts some/path/to/Location -> Base for "Location"
     * @param path some/path/to/Location
     * @return "Location"
     */
    private String getPathLocation(String path) {
        return path.contains("/") ? path.substring(path.lastIndexOf("/") + 1) : path;
    }

    @Override
    public int size() {
        return root.size();
    }

    @Override
    public boolean contains(String name) {
        return root.contains(name);
    }

    @Override
    public Base get(String name) {
        return root.get(name);
    }
}