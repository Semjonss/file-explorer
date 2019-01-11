package managers.file.impl;

import constants.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.swing.filechooser.FileSystemView;
import managers.file.FileManager;
import models.files.FileModel;
import models.files.impl.LocalFileModel;
import org.apache.commons.io.FilenameUtils;
import utils.FileUtil;

;

public class LocalFileManager implements FileManager {





  private static FileSystemView fileSystemView = FileSystemView.getFileSystemView();


  @Override
  public FileModel[] getChildFolders(FileModel parent) {
    if (parent instanceof LocalFileModel) {
      LocalFileModel localFile = (LocalFileModel) parent;
      File[] result = localFile.getFile()
          .listFiles(f -> (f.isDirectory() || f.getPath().endsWith(Constants.ZIP_EXTENSION)));
      return mapToFileModel(result, localFile.getIndex() + 1);

    }
    return null;
  }


  @Override
  public FileModel[] getAllChild(FileModel parent) {
    if (parent instanceof LocalFileModel) {
      LocalFileModel localFile = (LocalFileModel) parent;
      File[] result = localFile.getFile().listFiles();
      return mapToFileModel(result, localFile.getIndex() + 1);
    }
    return new FileModel[0];
  }


  @Override
  public int getChildCount(FileModel parent) {
    if (parent instanceof LocalFileModel) {
      File[] localFile = ((LocalFileModel) parent).getFile().listFiles();

      return localFile == null ? 0
          : ((int) Arrays.stream(localFile)
              .filter(f -> f.isDirectory() || f.getPath().endsWith(Constants.ZIP_EXTENSION))
              .count());
    }
    return 0;
  }


  @Override
  public boolean isLastFolderLevel(FileModel parent) {
    if (parent instanceof LocalFileModel) {
      File[] localFile = ((LocalFileModel) parent).getFile().listFiles();
      return localFile != null && Arrays.stream(localFile).noneMatch(File::isDirectory);
    }
    return false;
  }

  @Override
  public InputStream getInputStream(FileModel file) throws FileNotFoundException {
    if (file instanceof LocalFileModel && !file.isDirectory()) {
      return new FileInputStream(((LocalFileModel) file).getFile());
    }
    return null;

  }


  private FileModel[] mapToFileModel(File[] files, int childIndex) {
    if (files == null) {
      return null;
    }
    List<FileModel> result = new ArrayList<>(files.length);
    for (File file : files) {
      FileModel newElement = getNewLocalFileModel(file, childIndex);
      if (newElement != null) {
        result.add(newElement);
      }

    }
    return result.toArray(new FileModel[0]);
  }


  public FileModel getNewLocalFileModel(File file, int index) {
    String extension = getFileExtension(file);

    if (Constants.ZIP_EXTENSION.equals(extension)) {

      try {
        File tmp = Files.createTempDirectory(UUID.randomUUID().toString()).toFile();
        tmp.deleteOnExit();
        if (FileUtil.unzip(new FileInputStream(file), tmp)) {
          return new LocalFileModel(tmp, getFileName(file), getFileExtension(file), index);
        }
      } catch (IOException e) {
        return null;
      }
    }

    return new LocalFileModel(file, getFileName(file),
        getFileExtension(file), index);


  }


  private static String getFileName(File file) {
    return fileSystemView.getSystemDisplayName(file);
  }


  private String getFileExtension(File file) {
    if (file.isDirectory()) {
      return Constants.FOLDER_EXTENSION;
    }
    String result = getAbsoluteExtension(file);
    return Constants.EMPTY_STRING.equals(result) ? Constants.FOLDER_EXTENSION : result;
  }

  private String getAbsoluteExtension(File file) {
    return FilenameUtils.getExtension(file.getAbsolutePath());
  }

}
