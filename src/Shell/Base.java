package Shell;

public abstract class Base {
    private String  name;
    private int size;
    public Base(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getSize()
    {
        return size;
    }
    public void setSize(int size)
    {
        this.size = size;
    }

    public abstract boolean isFile();
}
