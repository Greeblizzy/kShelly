import Shell.Base;
import Shell.GFile;

import java.util.HashSet;
import java.util.Set;

public class Driver {
    public static void main(String[] args) {
        Set<Base> sys = new HashSet<>();

        sys.add(new GFile<String>("Welcome"));
        sys.add(new GFile<String>("Home"));

        System.out.println(sys);
    }
}
