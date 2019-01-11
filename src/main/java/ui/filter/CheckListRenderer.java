package ui.filter;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import models.filters.FilterState;

public class CheckListRenderer extends JCheckBox implements ListCellRenderer {


  public CheckListRenderer() {
    setBorder(new EmptyBorder(1, 1, 1, 1));
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index,
      boolean isSelected,
      boolean cellHasFocus) {
    setComponentOrientation(list.getComponentOrientation());
    setText(getObjectAsText(value));
    setSelected(isChecked(list, index));
    setEnabled(list.isEnabled());
    return this;
  }

  private String getObjectAsText(Object obj) {
    return (obj == null) ? "" : obj.toString();
  }

  private boolean isChecked(JList list, int index) {
    if (list.getModel() instanceof FilterState) {
      return ((FilterState) list.getModel()).isCheckedIndex(index);
    } else {
      return false;
    }
  }
}
