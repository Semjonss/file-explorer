package managers.file.impl;

import constants.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import managers.file.FileManager;
import models.files.FileModel;
import models.files.impl.FTPFileModel;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPFileManager implements FileManager {

  private final FTPClient client;

  public FTPFileManager(FTPClient client) {

    this.client = client;
  }

  @Override
  public FileModel[] getChildFolders(FileModel parent) {

    if (parent instanceof FTPFileModel) {
      FTPFileModel localFile = (FTPFileModel) parent;

      try {
        client.changeWorkingDirectory(parent.getName());
        FTPFile[] result = (FTPFile[]) Arrays.stream(client.listFiles())
            .filter(f -> (f.isDirectory())).toArray();

        return mapToFileModel(result, localFile.getIndex() + 1);
      } catch (IOException e) {
        return new FileModel[0];
      }

    }
    return new FileModel[0];
  }

  @Override
  public FileModel[] getAllChild(FileModel parent) {
    if (parent instanceof FTPFileModel) {
      FTPFileModel localFile = (FTPFileModel) parent;
      FileModel[] result;

      try {
        client.changeWorkingDirectory(parent.getName());
        result = mapToFileModel(client.listFiles(), localFile.getIndex() + 1);
      } catch (IOException e) {
        result = new FileModel[0];
      }

      return result;
    }
    return new FileModel[0];
  }


  @Override
  public int getChildCount(FileModel parent) {
    if (parent instanceof FTPFileModel) {
      FTPFileModel localFile = (FTPFileModel) parent;
      return getChildFolders(localFile).length;
    }
    return 0;
  }


  @Override
  public boolean isLastFolderLevel(FileModel parent) {
    if (parent instanceof FTPFileModel) {
      FTPFileModel localFile = (FTPFileModel) parent;
      return getChildFolders(localFile).length == 0;
    }
    return false;
  }


  @Override
  public InputStream getInputStream(FileModel file) throws IOException {

    InputStream stream = client.retrieveFileStream(file.getName());

    if (!client.completePendingCommand()) {
      return null;
    }

    return stream;
  }


  private FileModel[] mapToFileModel(FTPFile[] files, int childIndex) {
    if (files == null) {
      return null;
    }
    List<FileModel> result = new ArrayList<>(files.length);
    for (FTPFile file : files) {
      result.add(

          new FTPFileModel(file.getName(), childIndex, getFileExtension(file),file.isDirectory()));
    }
    return result.toArray(new FileModel[0]);
  }


  public String getFileExtension(FTPFile file) {
    if (file.isDirectory()) {
      return Constants.FOLDER_EXTENSION;
    }
    String result = getAbsoluteExtension(file.getName());
    return Constants.EMPTY_STRING.equals(result) ? Constants.FOLDER_EXTENSION : result;
  }


  private String getAbsoluteExtension(String path) {
    return FilenameUtils.getExtension(path);
  }


}
