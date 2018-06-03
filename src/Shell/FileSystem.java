package Shell;

import Shell.Exception.UnexpectedDirectoryException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;

public class FileSystem {
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

    public FileSystem() {
        this.currDir = this.root = new GDirectory("/", null);
    }

    public boolean isRunning() {
        return !quit;
    }

    public String runCommand(String... line) throws Exception {
        // String... = an array as runCommand("1", "2", "3") - forces it to be the last param in a method
        String output = "";

        switch (line[0].toLowerCase()) {
            case Constants.MAN:         // line = ["manPages", "<content>"]
                assertEQ(2, line.length, "Expected: man <command>");
                output = manPages.getOrDefault(line[1], "Invalid Command");
                break;
            case Constants.TOUCH:       // line = ["touch", "<fileName>"]
                assertEQ(2, line.length, "Expected: touch <fileName>");
                if (currDir.containsDirectory(line[1]))
                    throw new UnexpectedDirectoryException("Naming Conflict");

                String[] fileName = line[1].split("\\.");
                currDir.add(new GFile(line[1], fileName.length == 2 ? FileType.get(fileName[1]) : FileType.TXT));
                break;
            case Constants.RM:          // line = ["rm", "<fileName>"]
                assertEQ(2, line.length, "Expected: rm <fileName>");
                assertIsFile(line[1]);

                currDir.remove(line[1]);
                break;
            case Constants.MKDIR:       // line = ["mkdir", "<directoryName>"]
                assertEQ(2, line.length, "Expected: mkdir <directoryName>");
                if (currDir.contains(line[1]))
                    throw new UnexpectedDirectoryException(Constants.missingFileDir);

                currDir.add(new GDirectory(line[1], currDir));
                break;
            case Constants.RMDIR:
                assertEQ(2, line.length, "Expected: rmdir <directoryName>");
                if (!currDir.containsDirectory(line[1]) || !((GDirectory) currDir.get(line[1])).isEmpty())
                    throw new UnexpectedDirectoryException("Directory not found");

                currDir.remove(line[1]);
                break;
            case Constants.LL:   // skip ll for now
                System.out.println(currDir);
            case Constants.LS:
                output = currDir.toString();
                break;
            case Constants.ECHO:
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < line.length; i++)
                    sb.append(line[i]).append(" ");

                sb.setLength(sb.length() -1);
                output = sb.toString();
                break;
            case Constants.CD:
                assertEQ(2, line.length, "Expected: cd <directoryName>");
                processCD(line[1]);
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
                assertIsFile(line[1]);

                StringBuilder content = new StringBuilder();
                for (int wordIndex = 2; wordIndex < line.length; wordIndex++)
                    content.append(line[wordIndex]).append(" ");

                ((GFile) currDir.get(line[1])).append(content.toString());
                break;
            case Constants.CAT:
                assertEQ(2, line.length, "Expected: cat <fileName");
                assertIsFile(line[1]);

                output = ((GFile) currDir.get(line[1])).read();
                break;
            case Constants.QUIT:
                quit = true;
                break;
            case Constants.SH:      // internal script
                assertEQ(2, line.length, "Expected: sh <script.sh>");
                assertIsFile(line[1]);

                // read the gfile
                String[] commandLines = ((GFile)currDir.get(line[1])).read().split("\n");
                try {
                    for (String commandLine : commandLines)
                        System.out.println(runCommand(commandLine.split(" ")));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case Constants.MV:
                assertIsFile(line[1]);
                assertEQ(3, line.length, "Expected: mv <origin> <destination>");
                move(line[1], line[2]);
                break;
            case "#":
            case "":
                break;
            default:
                throw new IllegalArgumentException("No such command");
        }
        return output;
    }

    private void assertEQ(int expected, int actual, String errorMsg) throws IllegalArgumentException {
        if (expected != actual)
            throw new IllegalArgumentException(errorMsg);
    }

    private void assertIsFile(String path) throws FileSystemException {
//        var dir = multiDirectory(path);


        if (currDir.containsDirectory(path))
            throw new UnexpectedDirectoryException(path + " is actually a directory");
        if (!currDir.containsFile(path))
            throw new NoSuchFileException(path + " doesn't exist");
    }

    private void processCD(String path) throws InvalidPathException {
        currDir = multiDirectory(path);
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
                throw new InvalidPathException(path, "Directory Not Found");
        }
    }

    // TODO: change GDirectory -> Base .. refactor everything and make sure it is consistent - Lisa
    private GDirectory multiDirectory(String path) throws InvalidPathException {
        // Consider same directory first
        GDirectory curr = currDir;

        try {   // test if symbolic link
            return symbolicLink(path, currDir);
        } catch (InvalidPathException ignored) {}

        if (!path.contains("/"))
            if (currDir.containsDirectory(path))
                return (GDirectory) currDir.get(path);
            else
                throw new InvalidPathException(path, "Not found");

        // does it start at root
        if (path.charAt(0) == '/') {
            curr = root;
            path = path.substring(1);
        }

        // multi directory support
        for (String dir : path.split("/"))
            curr = curr.containsDirectory(dir) ? (GDirectory) curr.get(dir) : symbolicLink(dir, curr);

        return curr;
    }

    private void move (String src, String dest) throws FileSystemException {
        // checking if src is real
        GDirectory srcDir = multiDirectory(src.substring(0, src.lastIndexOf("/")));
        String srcFileName = src.substring(src.lastIndexOf("/") + 1);
        if (!srcDir.containsFile(srcFileName))
            throw new NoSuchFileException(srcFileName + " doesn't exist");

        // checking if dest is valid
        GDirectory destDir = multiDirectory(dest.substring(0, dest.lastIndexOf("/")));

        // now actually do the move
        // you now have 2 directories, move the desired file from one to the other
        // remove from src, add to dest
        destDir.add(srcDir.remove(srcFileName));
    }
}
