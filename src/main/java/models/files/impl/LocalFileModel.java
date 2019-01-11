package models.files.impl;

import java.io.File;
import models.files.FileModel;

public class LocalFileModel implements FileModel {

  private final String name;
  private final File file;
  private int index;
  private final String extension;


  public LocalFileModel(File file, String name, String extension, int index) {
    this.name = name;
    this.file = file;
    this.index = index;
    this.extension = extension;
  }

  public String getPath() {
    return file.getPath();
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public String getName() {
    return name;
  }

  public File getFile() {
    return file;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public String getExtension() {
    return extension;
  }

  @Override
  public boolean isDirectory() {
    return file.isDirectory();
  }


}
