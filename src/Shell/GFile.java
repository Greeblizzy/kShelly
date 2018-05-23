package Shell;

public class GFile<T> extends Base {
    private T obj;
    public GFile(String name) {
       super(name);
    }

    public T getObj()
    {
        return obj;
    }
    public void setObj(T obj)
    {
        this.obj = obj;
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
