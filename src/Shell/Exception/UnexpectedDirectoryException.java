package Shell.Exception;

import java.nio.file.FileSystemException;

public class UnexpectedDirectoryException extends FileSystemException {
    public UnexpectedDirectoryException(String msg) {
        super(msg);
    }
}
