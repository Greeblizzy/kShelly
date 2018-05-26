package Shell;

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
    }, COWSAY {
        @Override
        public String read(String content) {
            return null;
        }

        @Override
        public String write(String content) {
            return null;
        }
    }, PNG {
        @Override
        public String read(String content) {
            return null;
        }

        @Override
        public String write(String content) {
            return null;
        }
    };

    private static final Map<String, FileType> mapping = new HashMap<>();
    static {
        mapping.put("TXT", TXT);
        mapping.put("COWSAY", COWSAY);
        mapping.put("PNG", PNG);
    }

    public static FileType get(String val) {
        return mapping.getOrDefault(val, TXT);
    }
}
