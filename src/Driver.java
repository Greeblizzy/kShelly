import Shell.GDirectory;
import Shell.GFile;

import java.util.*;

public class Driver {
    private static final Map<String, String> man = new HashMap<>();
    private static GDirectory root;

    public static void main(String[] args) {
        String[] line;
        boolean quit = false;
        setUpManual(man);
        Scanner sc = new Scanner(System.in);    // Intellij does auto imports
        GDirectory currDir = root = new GDirectory("/", null);

        System.out.println("Welcome to kShelly!");
        // Process incoming user commands
        while (!quit) {
            System.out.print("\n>");
            // reads input line, stores the split array on space in line
            // considers the first word -> lower case for switch
            switch ((line = sc.nextLine().split(" "))[0].toLowerCase()) {
                case "man":     // line = ["man", "<content>"]      process it
                    System.out.println(line.length == 2 ?
                            man.getOrDefault(line[1], "Invalid Command") :
                            "Expected: man <command>");
                    break;
                case "touch":       // line = ["touch", "<fileName>"]      process it
                    if (line.length != 2) {
                        System.out.println("Expected: touch <fileName>");
                        break;
                    }

                    GFile<String> gFile = new GFile<String>(line[1]);
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
                    break;
                case "ls":
                    System.out.println(currDir);
                    break;
                case "quit":
                    quit = true;
                    break;
                default:
                    System.out.println("Unrecognized Command");
            }
        }
    }

    private static void setUpManual(Map<String, String> man) {
        man.put("man", "prints out a help menu for a given command");
        man.put("touch", "creates a file named <fileName>");
        man.put("rm", "removes the file named <fileName>");
        man.put("mkdir", "creates a folder named <directoryName>");
        man.put("rmdir", "removes the folder named <directoryName>");
        man.put("ls", "lists all files and directories in current folder");
        man.put("ll", "lists all files and directories in greater detail");
        man.put("echo", "outputs content to terminal output");
        man.put("pwd", "prints present working directory");
        man.put("write", "writes <content> to <fileName>"); // takes 2 param
        man.put("quit", "logout");
    }
}