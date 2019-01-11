package models.files.impl;

import models.files.FileModel;

public class FTPFileModel implements FileModel {

  private final String name;
  private final int index;
  private final String extension;
  private final boolean isDir;

  public FTPFileModel(String name, int index, String extension, boolean isDir) {
    this.name = name;
    this.index = index;
    this.extension = extension;
    this.isDir = isDir;
  }

  @Override
  public String getName() {
    return name;
  }


  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public boolean isDirectory() {
    return isDir;
  }

  @Override
  public String getExtension() {
    return extension;
  }
}
