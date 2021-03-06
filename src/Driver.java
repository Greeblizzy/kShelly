import java.io.*;
import java.util.*;

import Helpers.pprint;
import Shell.KShell;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        KShell fs = new KShell();

        // read and run scripts
        for (String fileName : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {  // reads an actual external script
                String sCurrentLine;
                // opens a file to read based on args
                while ((sCurrentLine = br.readLine()) != null) {
                    try {
                        // for every line in the file, execute it
//                        System.out.println(sCurrentLine);
                        pprint.nonEmpty(fs.runCommand(sCurrentLine.split(" ")));
                    } catch (Exception e) {
                        System.out.println("Error running script");
                        e.printStackTrace();
                        break;
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
                pprint.nonEmpty(fs.runCommand(sc.nextLine().split(" ")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thanks for using kShelly");
    }
}
