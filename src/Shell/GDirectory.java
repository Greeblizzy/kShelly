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

    @Override
    public boolean isFile() {
        return false;
    }

    public void add(Base b) {
        files.put(b.getName(), b);
    }

    public void remove(String name) {
        files.remove(name);
    }

    public boolean contains(String name) {
        return files.values().stream()
                .map(Base::getName)                 // convert every file -> name
                .anyMatch(f -> f.equals(name));     // if any matches given name
        // files currently contains files and folders, but I only want its name
        // so this outputs a list of its names
        // map? what to what?  Maps Base (file/dir) -> String
    }

    public boolean containsFile(String name) {
        return contains(name) && files.get(name).isFile();
    }

    public boolean containsDirectory(String name) {
        return contains(name) && !files.get(name).isFile();
    }

    @Override
    public String toString() {
        return String.format("<from %s -> %s>", parent.getName(), files);
    }
}
