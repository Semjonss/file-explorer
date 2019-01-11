package managers.openFile;

import managers.file.FileManager;
import models.files.FileModel;
import ui.fileView.FileReader;

public interface OpenFileBuilder {

  FileReader getReader(FileManager fileManager, FileModel file);
}