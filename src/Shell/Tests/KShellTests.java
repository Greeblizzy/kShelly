package Shell.Tests;

import Helpers.FileSystem;
import Helpers.pprint;
import Shell.Base;
import Shell.Constants;
import Shell.KShell;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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
        assertEquals(Constants.LS_DESC, kShelly.runCommand(Constants.MAN,  Constants.LS));
    }

    @Test
    @DisplayName("touch FileName")
    void touchFileTest() throws Exception {
        kShelly.runCommand(Constants.TOUCH,  "FileName");
        assertEquals(1, kShelly.size());
        assertTrue(kShelly.contains("FileName"));
    }

    // now, the rest is yours -- test every single method by itself
    @Test
    @DisplayName("Template")
    void methodName() throws Exception {
        kShelly.runCommand("Something");
        // show me what you learned from the book
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
                            String[] cmd_ = sCurrentLine.split(" ");
                            kShelly.runCommand(cmd_[0], Arrays.copyOf(cmd_, cmd_.length - 1));
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
                pprint.nonEmpty(kShelly.runCommand("ll"));
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
}
