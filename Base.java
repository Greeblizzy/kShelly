public abstract class Base {
    String  name;
    double size;
    public Base(String name)
    {
        this.name = name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setSize(double size)
    {
        this.size = size;
    }
    public String getName()
    {
        return name;
    }
    public double getSize()
    {
        return size;
    }
    public boolean isFile(){
        return false;
    }   
}
