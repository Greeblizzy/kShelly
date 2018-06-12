package Helpers;

public interface FileSystem<T> {
    boolean contains(String name);
    T get(String name);

    int size();
    String runCommand(String cmd, String... kwargs) throws Exception;

    void clearSystem();
}
