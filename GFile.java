public class GFile<T> extends Base
{
    private T obj;
    public T getObj()
    {
        return obj;
    }
    public void setObj(T obj)
    {
        this.obj = obj;
    }
    public GFile(String name)
    {
        GFile.name = name;
    }
}
