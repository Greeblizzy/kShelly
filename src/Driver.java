import Shell.GDirectory;
import Shell.GFile;

import java.util.*;

public class Driver {
    private static final Map<String, String> manPages = new HashMap<>();
    private static GDirectory root;

    public static void main(String[] args) {
        String[] line;
        boolean quit = false;
        setUpManual();
        Scanner sc = new Scanner(System.in);    // Intellij does auto imports
        GDirectory currDir = root = new GDirectory("/", null);

        System.out.println("Welcome to kShelly!");
        // Process incoming user commands
        while (!quit) {
            System.out.print("\n>");
            // reads input line, stores the split array on space in line
            // considers the first word -> lower case for switch
            switch ((line = sc.nextLine().split(" "))[0].toLowerCase()) {
                case "man":     // line = ["manPages", "<content>"]      process it
                    System.out.println(line.length == 2 ?
                            manPages.getOrDefault(line[1], "Invalid Command") :
                            "Expected: man <command>");
                    break;
                case "touch":       // line = ["touch", "<fileName>"]      process it
                    if (line.length != 2) {
                        System.out.println("Expected: touch <fileName>");
                        break;
                    }

                    if (currDir.contains(line[1])) {
                        System.out.println("File/Directory with such name exists");
                        break;
                    }
                    GFile<String> gFile = new GFile<>(line[1]);
                    currDir.add(gFile);
                    break;
                case "rm":      // line = ["rm", "<fileName>"]      process it
                    if (line.length != 2) {
                        System.out.println("Expected: rm <fileName");
                        break;
                    }

                    if (currDir.containsFile(line[1]))
                        currDir.remove(line[1]);
                    else
                        System.out.println("File Not Found");
                    break;
                case "mkdir":
                    if (line.length != 2) {
                        System.out.println("Expected mkdir <fileName>");
                        break;
                    }

                    if (!currDir.contains(line[1]))
                        currDir.add(new GDirectory(line[1], currDir));
                    else
                        System.out.println("File/Directory with such name exists");
                    break;
                case "rmdir":
                    if (line.length != 2) {
                        System.out.println("Expected rmdir <directoryName>");
                        break;
                    }

                    if (currDir.containsDirectory(line[1]) &&
                            ((GDirectory) currDir.get(line[1])).isEmpty())
                        currDir.remove(line[1]);
                    else
                        System.out.println("Directory Not Found");
                    break;
                case "ls": case "ll":   // skip ll for now
                    System.out.println(currDir);
                    break;
                case "echo":
                    if (line.length != 2) {
                        System.out.println("Expected: echo content");
                        break;
                    }

                    System.out.println(line[1]);
                    break;
                case "cd":
                    if (line.length != 2) {
                        System.out.println("Expected cd <directoryName>");
                        break;
                    }

                    if (currDir.containsDirectory(line[1]))
                        currDir = (GDirectory) currDir.get(line[1]);
                    else if (line[1].equals("/"))
                        currDir = root;
                    else if (line[1].equals(".."))
                        currDir = currDir.getParent();
                    else if (!line[1].equals("."))
                        System.out.println("Directory Not Found");
                    break;
                case "pwd":
                    GDirectory temp = currDir;
                    StringBuilder presentWorkingDirectory = new StringBuilder();
                    do {
                        presentWorkingDirectory.insert(0, temp.getName());
                        if (currDir != root)
                            presentWorkingDirectory.insert(0, "/");
                    } while ((temp = temp.getParent()) != root);
                    System.out.println(presentWorkingDirectory.toString());
                    break;
                case "write":

                    break;
                case "quit":
                    quit = true;
                    break;
                default:
                    System.out.println("Unrecognized Command");
            }
        }
    }

    private static void setUpManual() {
        manPages.put("manPages", "prints out a help menu for a given command");
        manPages.put("touch", "creates a file named <fileName>");
        manPages.put("rm", "removes the file named <fileName>");
        manPages.put("mkdir", "creates a folder named <directoryName>");
        manPages.put("rmdir", "removes the folder named <directoryName>");
        manPages.put("ls", "lists all files and directories in current folder");
        manPages.put("ll", "lists all files and directories in greater detail");
        manPages.put("echo", "outputs content to terminal output");
        manPages.put("cd", "changes directory to given folder");
        manPages.put("pwd", "prints present working directory");
        manPages.put("write", "writes <content> to <fileName>"); // takes 2 param
        manPages.put("quit", "logout");
    }
}
