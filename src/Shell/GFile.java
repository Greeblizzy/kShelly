package Shell;

public class GFile extends Base {
    private String content;
    private FileType type;

    public GFile(String name, FileType type) {
       super(name);
       this.content = "";
       this.type = type;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void append(String content)
    {
        this.content += content;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("GFile(%s)", getName());
    }
    //%s puts in the corresponding arg (getName()) into that position

    public String read() {
        return type.read(content);
    }

//    public void write(String newContent) {
//        content = type.write(content);
//    }
}
