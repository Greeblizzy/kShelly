package Helpers;

public interface FileSystem<T> {
    boolean contains(String name);
    T get(String name);

    int size();
    String runCommand(String... cmd) throws Exception;

    void clearSystem();
}
