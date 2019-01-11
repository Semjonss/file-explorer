package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import models.filters.FilterState;

public class FilterListListener extends MouseAdapter {

  @Override
  public void mouseClicked(MouseEvent e) {

    if (!SwingUtilities.isLeftMouseButton(e)) {
      return;
    }

    JList list = (JList) e.getSource();
    if (!list.isEnabled() || (!(list.getModel() instanceof FilterState))) {
      return;
    }

    int index = list.locationToIndex(e.getPoint());
    if (index < 0) {
      return;
    }

    FilterState model = (FilterState) list.getModel();

    model.changeStateIndex(index);

    e.consume();
  }

}
