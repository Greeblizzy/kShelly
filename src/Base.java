public abstract class Base {
    private String  name;
    private int size;
    public Base(String name)
    {
        this.name = name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setSize(int size)
    {
        this.size = size;
    }
    public String getName()
    {
        return name;
    }
    public int getSize()
    {
        return size;
    }
    public abstract boolean isFile(){};
}
