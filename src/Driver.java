import java.io.*;
import java.util.*;
import Shell.FileSystem;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileSystem fs = new FileSystem();

        // read and run scripts
        for (String fileName : args) {
            try {       // external script - reads an actual file
                String sCurrentLine;
                // opens a file to read based on args
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                while ((sCurrentLine = br.readLine()) != null) {
                    try {
                        // for every line in the file, execute it
//                        System.out.println(sCurrentLine);
                        printNonEmpty(fs.runCommand(sCurrentLine.split(" ")));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (FileNotFoundException fe) {
                System.out.println("Invalid File");
            } catch (IOException io) {
                System.out.println("Invalid Operation");
            }
        }

        while (fs.isRunning()) {
            System.out.print("> ");
            try {       // reads input line, stores the split array on space in line
                printNonEmpty(fs.runCommand(sc.nextLine().split(" ")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printNonEmpty(String str) {
        if (!str.equals(""))
            System.out.println(str);
    }
}
