package listeners;

import constants.Constants;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class FilterPopupListener extends MouseAdapter {


  private JPopupMenu popupFilterWindow;

  public FilterPopupListener(JPopupMenu popupFilterWindow) {
    this.popupFilterWindow = popupFilterWindow;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.isPopupTrigger()) {
      showFilterPopup(e);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.isPopupTrigger()) {
      showFilterPopup(e);
    }
  }

  private void showFilterPopup(MouseEvent e) {
    JTable table = ((JTableHeader) e.getSource()).getTable();

    // Клик по нужной колонке
    int vColumnIndex = table.getColumnModel().getColumnIndexAtX(e.getX());
    if (vColumnIndex != Constants.FILTERED_COLUMN) {
      return;
    }

    JTableHeader header = (JTableHeader) (e.getSource());

    // положение по иксу
    Rectangle headerRect = table.getTableHeader()
        .getHeaderRect(Constants.FILTERED_COLUMN);

    //показываем
    popupFilterWindow.show(header, headerRect.x, header.getHeight());
  }

}
