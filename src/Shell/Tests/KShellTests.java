package Shell.Tests;

import Helpers.FileSystem;
import Shell.Base;
import Shell.Constants;
import Shell.KShell;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KShellTests {
    private static FileSystem<Base> kShelly;

    @BeforeAll
    static void initKShelly() {
        kShelly = new KShell();
    }

    // Unit tests individual commands
    @Test
    @DisplayName("man man")
    void manManTest() throws Exception {
        assertEquals(kShelly.runCommand(Constants.MAN,  Constants.MAN), Constants.MAN_DESC);
    }

    @Test
    @DisplayName("man ls")
    void manLsTest() throws Exception {
        assertEquals(kShelly.runCommand(Constants.MAN,  Constants.LS), Constants.LS_DESC);
    }

    @Test
    @DisplayName("man ls")
    void touchFileTest() throws Exception {
        kShelly.runCommand(Constants.TOUCH,  "FileName");
        assertEquals(kShelly.size(), 1);
        assertTrue(kShelly.contains("FileName"));
    }

    // "Integration" Tests with multiple commands

    // "Integration" Tests with Internal Scripts

    // "Integration" Tests with Externals Scripts
    @Nested
    class ExternalScripts {
        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class Script01 {
            @BeforeAll
            void runScript_01() {
                try (BufferedReader br = new BufferedReader(new FileReader("init.sh"))) {
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        try {
                            kShelly.runCommand(sCurrentLine.split(" "));
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

            @Test
            @DisplayName("Existence of members")
            void contentsExist() throws Exception{
                printNonEmpty(kShelly.runCommand("ll"));
            }
        }

        @Test
        @DisplayName("Runs Script 01")
        void runScript_01() {

        }
    }

    @AfterEach
    void resetSystem() {
        kShelly.clearSystem();
    }

    private static void printNonEmpty(String str) {
        if (!str.equals(""))
            System.out.println(str);
    }
}
