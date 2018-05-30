package Shell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum FileType implements FileOp {
    TXT {
        @Override
        public String read(String content) {
            return content;
        }

        @Override
        public String write(String content) {
            return null;
        }
    }, PNG {
        @Override
        public String read(String content) {
            FileType.setUpASCIIMapping();
            String[] AsciiContent = new String[content.length()];
            content.toUpperCase();
            for (int index = 0 ; index < content.length(); index++) {
                AsciiContent += ASCII_mapping.getOrDefault
                        (Character.toString(content.charAt(index)), Character.toString(content.charAt(index));
            }
            return String.valueOf(AsciiContent);
        }

        @Override
        public String write(String content) {
            return null;
        }
    };
    private static final Map<String, String[]> ASCII_mapping = new HashMap<>();
    private static void setUpASCIIMapping() {
        ASCII_mapping.put("A", Constants.ASCII_A);
        ASCII_mapping.put("B", Constants.ASCII_B);
        ASCII_mapping.put("C", Constants.ASCII_C);
        ASCII_mapping.put("D", Constants.ASCII_D);
        ASCII_mapping.put("E", Constants.ASCII_E);
        ASCII_mapping.put("F", Constants.ASCII_F);
        ASCII_mapping.put("G", Constants.ASCII_G);
        ASCII_mapping.put("H", Constants.ASCII_H);
        ASCII_mapping.put("I", Constants.ASCII_I);
        ASCII_mapping.put("J", Constants.ASCII_J);
        ASCII_mapping.put("K", Constants.ASCII_K);
        ASCII_mapping.put("L", Constants.ASCII_L);
        ASCII_mapping.put("M", Constants.ASCII_M);
        ASCII_mapping.put("N", Constants.ASCII_N);
        ASCII_mapping.put("O", Constants.ASCII_O);
        ASCII_mapping.put("P", Constants.ASCII_P);
        ASCII_mapping.put("Q", Constants.ASCII_Q);
        ASCII_mapping.put("R", Constants.ASCII_R);
        ASCII_mapping.put("S", Constants.ASCII_S);
        ASCII_mapping.put("T", Constants.ASCII_T);
        ASCII_mapping.put("U", Constants.ASCII_U);
        ASCII_mapping.put("V", Constants.ASCII_V);
        ASCII_mapping.put("W", Constants.ASCII_W);
        ASCII_mapping.put("X", Constants.ASCII_X);
        ASCII_mapping.put("Y", Constants.ASCII_Y);
        ASCII_mapping.put("Z", Constants.ASCII_Z);
    }

    private static final Map<String, FileType> mapping = new HashMap<>();
    static {
        Arrays.stream(FileType.values()).forEach(f -> mapping.put(f.toString(), f));
    }

    public static FileType get(String extension) {
        return mapping.getOrDefault(extension.toUpperCase(), TXT);
    }
}
