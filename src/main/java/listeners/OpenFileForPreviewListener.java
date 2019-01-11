package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import managers.file.FileManager;
import managers.openFile.FileOpenFactory;
import models.FileTableModel;
import ui.fileView.FileReader;

public class OpenFileForPreviewListener extends MouseAdapter {

  final private FileManager fileManager;

  public OpenFileForPreviewListener(FileManager fileManager) {
    this.fileManager = fileManager;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {

      JTable table = (JTable) e.getSource();
      if (!table.isEnabled() || (!(table.getModel() instanceof FileTableModel))) {
        return;
      }

      int row = table.rowAtPoint(e.getPoint());
      if (row < 0) {
        return;
      }

      FileTableModel model = (FileTableModel) table.getModel();

      FileReader reader = FileOpenFactory.getOpenFileFor(fileManager, model.getFile(row));
      if (reader != null) {
        reader.setVisible(true);
      }
      e.consume();

    }
  }

}
