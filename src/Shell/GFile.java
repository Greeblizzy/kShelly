package Shell;

public class GFile extends Base {
    private String content;
    public GFile(String name) {
       super(name);
    }

    public String getContent()
    {
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
}
