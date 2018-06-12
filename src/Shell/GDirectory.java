package Shell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GDirectory extends Base {
    private Map<String, Base> files;        // Name -> file/directory
    private GDirectory parent;

    public GDirectory(String directoryName, GDirectory parent) {
        super(directoryName);
        this.parent = parent == null ? this : parent;
        files = new HashMap<>();
    }

    GDirectory getParent() {
        return parent;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    void add(Base b) {
        files.put(b.getName(), b);
    }

    Base remove(String name) {
        return files.remove(name);
    }

    boolean contains(String name) {
        return files.containsKey(name);
    }

    public boolean containsFile(String name) {
        return contains(name) && files.get(name).isFile();
    }

    boolean containsDirectory(String name) {
        return contains(name) && !files.get(name).isFile();
    }

    Base get(String name) {
        return files.get(name);
    }

    boolean isEmpty() {
        return files.isEmpty();
    }

    void clearSystem() {
        files.clear();
    }

    public int size() {
        return files.size();
    }

    @Override
    public String toString() {
        return String.format("<from %s -> %s>", parent.getName(), files);
    }
}