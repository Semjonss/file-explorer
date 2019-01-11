package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;

public class FileUtil {

  public static File getRoot() {
    return new File("/");
  }

  public static boolean unzip(InputStream inputStream, File tempFolder) throws IOException {
    ZipInputStream zipInputStream = new ZipInputStream(inputStream);

    ZipEntry entry;
    boolean isEmpty = true;
    while ((entry = zipInputStream.getNextEntry()) != null) {
      isEmpty = false;
      File newFile = new File(tempFolder, entry.getName());
      if (newFile.getParentFile().mkdirs() && !entry.isDirectory()) {
        FileOutputStream fos = new FileOutputStream(newFile);
        IOUtils.copy(zipInputStream, fos);
        IOUtils.closeQuietly(fos);
      }
    }
    IOUtils.closeQuietly(zipInputStream);

    return !isEmpty;
  }

}
