package managers.file;

import java.io.IOException;
import java.io.InputStream;
import models.files.FileModel;

public interface FileManager {

  FileModel[] getChildFolders(FileModel parent);

  FileModel[] getAllChild(FileModel parent);

  int getChildCount(FileModel parent);

  boolean isLastFolderLevel(FileModel parent);

  InputStream getInputStream(FileModel file) throws IOException;

}
