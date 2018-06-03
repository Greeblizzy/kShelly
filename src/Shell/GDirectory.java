package Shell;

import java.util.HashMap;
import java.util.Map;

public class GDirectory extends Base {
    private Map<String, Base> files;        // Name -> file/directory
    private GDirectory parent;

    public GDirectory(String directoryName, GDirectory parent) {
        super(directoryName);
        this.parent = parent == null ? this : parent;
        files = new HashMap<>();
    }

    public GDirectory getParent() {
        return parent;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    public void add(Base b) {
        files.put(b.getName(), b);
    }

    public Base remove(String name) {
        return files.remove(name);
    }

    public boolean contains(String name) {
        return files.containsKey(name);
    }

    public boolean containsFile(String name) {
        return contains(name) && files.get(name).isFile();
    }

    public boolean containsDirectory(String name) {
        return contains(name) && !files.get(name).isFile();
    }

    public Base get(String name) {
        return files.get(name);
    }

    public boolean isEmpty() {
        return files.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("<from %s -> %s>", parent.getName(), files);
    }
}