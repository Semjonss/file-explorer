package managers.openFile;

import constants.Constants;
import managers.file.FileManager;
import managers.openFile.impl.ImageFileOpenBuilder;
import managers.openFile.impl.TexFileOpenBuilder;
import models.files.FileModel;
import ui.fileView.FileReader;

public class FileOpenFactory {

  private static final OpenFileBuilder[] BUILDERS = new OpenFileBuilder[2];
  static{
    BUILDERS[0] = new TexFileOpenBuilder();
    BUILDERS[1] =new ImageFileOpenBuilder();
  }

  private FileOpenFactory() {

  }


  public static FileReader getOpenFileFor(FileManager fileManager, FileModel file) {
    String extension = file.getExtension().toLowerCase();

    if (Constants.TEXT_EXTENSION.contains(extension)) {
      return BUILDERS[0].getReader(fileManager, file);
    } else if (Constants.IMAGE_EXTENSION.contains(extension)) {
      return BUILDERS[1].getReader(fileManager, file);
    } else {
      return null;
    }
  }

}
