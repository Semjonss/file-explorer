package ui.fileView;

import constants.Constants;
import java.awt.Dimension;
import java.io.InputStream;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import managers.file.FileManager;
import models.files.FileModel;

public class TextFileReader extends FileReader<JTextPane> {

  public TextFileReader(FileManager fileManager, FileModel file) {
    super(fileManager, file);
  }

  @Override
  protected JTextPane load(FileModel file) throws Exception {
    JTextPane textPane = new JTextPane();
    InputStream stream = fileManager.getInputStream(file);
    textPane.read(stream, null);
    stream.close();

    textPane.setEditable(false);
    textPane.setMinimumSize(new Dimension(Constants.MIN_READER_SIZE, Constants.MIN_READER_SIZE));
    textPane.setPreferredSize(new Dimension(Constants.PREFER_READER_SIZE, Constants.PREFER_READER_SIZE));

    return textPane;
  }

  @Override
  protected void initUI(JTextPane textPane) {
    add(new JScrollPane(textPane));
  }
}
