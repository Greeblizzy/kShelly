package Shell;

import java.util.Set;

public class GDirectory extends Base {
    private Set<Base> files;
    private Base parent;

    public GDirectory(String directoryName)
    {
        super(directoryName);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public void add(Base b) {
        files.add(b);
    }
}
