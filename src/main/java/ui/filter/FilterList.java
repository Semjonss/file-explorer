package ui.filter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import listeners.FilterListListener;


public class FilterList extends JList {

  public FilterList(JPopupMenu popupFilterWindow) {
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    setCellRenderer(new CheckListRenderer());
    addMouseListener(new FilterListListener());
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        popupFilterWindow.setVisible(false);
      }
    });
    setVisibleRowCount(8);

  }

}
