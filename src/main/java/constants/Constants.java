package constants;

import java.util.HashSet;
import java.util.Set;

public class Constants {

  private Constants() {

  }

  public static String WINDOW_TITLE = "File Explorer";


  public static final int WINDOW_START_WIDTH = 700;
  public static final int WINDOW_START_HEIGHT = 700;

  public static final int WINDOW_MIN_WIDTH = 200;
  public static final int WINDOW_MIN_HEIGHT = 350;
  public final static int FILTERED_COLUMN = 1;


  public static final int MIN_READER_SIZE = 300;

  public static final int PREFER_READER_SIZE = 600;


  public static final Set<String> IMAGE_EXTENSION = new HashSet<>();
  public static final Set<String> TEXT_EXTENSION = new HashSet<>();
  public static final String ZIP_EXTENSION = "zip";

  static {
    IMAGE_EXTENSION.add("png");
    IMAGE_EXTENSION.add("jpg");
    IMAGE_EXTENSION.add("pic");
    IMAGE_EXTENSION.add("gif");
    IMAGE_EXTENSION.add("jpeg");
    IMAGE_EXTENSION.add("jpe");
    IMAGE_EXTENSION.add("wdp");

    TEXT_EXTENSION.add("txt");
    TEXT_EXTENSION.add("html");
    TEXT_EXTENSION.add("xml");
    TEXT_EXTENSION.add("vcf");
    TEXT_EXTENSION.add("vcs");
    TEXT_EXTENSION.add("java");
    TEXT_EXTENSION.add("curl");
    TEXT_EXTENSION.add("tsv");
    TEXT_EXTENSION.add("htm");
    TEXT_EXTENSION.add("css");


  }

  public static final String EMPTY_STRING = "";
  public static final String FOLDER_EXTENSION = "folder";


  //ftp
  public static final int FTP_TIMEOUT = 30000;
  public static final int FTP_ALIVE_TIMEOUT = 1000;
  public static final int FTP_SO_TIMEOUT = 300000;


}
