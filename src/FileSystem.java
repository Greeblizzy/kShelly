public interface FileSystem<T> {
    boolean contains(String name);
    T get(String name);
    boolean add(T content);
    boolean remove(T content);

    int size();
    void runCommand(String... cmd);
}
