package Helpers;

public class pprint {       // pretty print
    private pprint() {}

    public static void nonEmpty(String str) {
        if (!str.equals(""))
            System.out.println(str);
    }
}
