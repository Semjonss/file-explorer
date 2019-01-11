package ui.filter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import models.filters.FilterState;

public class FilterPopup extends JPopupMenu {

  private FilterList filterList = new FilterList(this);

  public FilterPopup() {

    new JPanel(new BorderLayout(3, 3));
    setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    setPreferredSize(new Dimension(250, 150)); // default popup size
    add(new JScrollPane(filterList), BorderLayout.CENTER);


  }


  public void setModel(FilterState filterState) {
    filterList.setModel(filterState);
  }


}
