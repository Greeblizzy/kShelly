package Shell;

public class GFile extends Base {
    private String content;
    private FileType type;

    public GFile(String name, FileType type) {
       super(name);
       this.content = "";
       this.type = type;
    }

    public void append(String content)
    {
        this.content += content;
    }

    public String read() {
        return type.read(content);
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("GFile(%s - %s)", getName(), type);
    }
    //%s puts in the corresponding arg (getName()) into that position
}
