package listeners;

import javax.swing.DefaultRowSorter;
import javax.swing.JTable;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import models.FileTableModel;
import models.filters.ExtensionsFilter;

public class FilterChangedListener implements ListDataListener {

  private final JTable table;
  private final ExtensionsFilter filter;

  public FilterChangedListener(JTable table, ExtensionsFilter filter) {
    this.table = table;
    this.filter = filter;

  }

  @Override
  public void intervalAdded(ListDataEvent e) {

  }

  @Override
  public void intervalRemoved(ListDataEvent e) {

  }

  @Override
  @SuppressWarnings("unchecked")
  public void contentsChanged(ListDataEvent e) {
    DefaultRowSorter<FileTableModel, Object> defaultRowSorter = (DefaultRowSorter<FileTableModel, Object>) table
        .getRowSorter();
    defaultRowSorter.setRowFilter(filter);

    table.repaint();
  }
}
