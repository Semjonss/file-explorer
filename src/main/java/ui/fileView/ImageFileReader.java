package ui.fileView;

import constants.Constants;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import managers.file.FileManager;
import models.files.FileModel;

public class ImageFileReader extends FileReader<JLabel> {

  public ImageFileReader(FileManager fileManager, FileModel file) {
    super(fileManager, file);
  }

  @Override
  protected JLabel load(FileModel file) throws Exception {
    JLabel result = new JLabel();
    BufferedImage bufferedImage;
    try (InputStream inputStream = fileManager.getInputStream(file)) {
      bufferedImage = ImageIO.read(inputStream);
    }

    ImageIcon image = resizeIcon(bufferedImage);

    result.setIcon(image);
    result.setMinimumSize(new Dimension(Constants.MIN_READER_SIZE, Constants.MIN_READER_SIZE));
    result.setPreferredSize(
        new Dimension(Constants.PREFER_READER_SIZE, Constants.PREFER_READER_SIZE));

    return result;
  }


  @Override
  protected void initUI(JLabel result) {
    add(new JScrollPane(result), SwingConstants.CENTER);
  }

  private ImageIcon resizeIcon(BufferedImage bufferedImage) {

    double imageWidth = bufferedImage.getWidth();
    double imageHeight = bufferedImage.getWidth();
    double maxSize = imageWidth > imageHeight ? imageWidth : imageHeight;
    double scale = maxSize / Constants.PREFER_READER_SIZE;

    return new ImageIcon(
        bufferedImage.getScaledInstance((int) (imageWidth / scale), (int) (imageHeight / scale),
            Image.SCALE_SMOOTH));


  }
}
