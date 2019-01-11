package managers.openFile.impl;

import managers.file.FileManager;
import managers.openFile.OpenFileBuilder;
import models.files.FileModel;
import ui.fileView.FileReader;
import ui.fileView.ImageFileReader;

public class ImageFileOpenBuilder implements OpenFileBuilder {

  @Override
  public FileReader getReader(FileManager fileManager, FileModel file) {
    return new ImageFileReader(fileManager, file);
  }
}
