package ui;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import listeners.FilterChangedListener;
import listeners.FilterPopupListener;
import models.FileTableModel;
import models.filters.ExtensionsFilter;
import models.filters.FilterState;
import ui.filter.FilterPopup;

public class FileTable extends JTable {

  private TableRowSorter<TableModel> sorter;
  private FilterPopup filterPopup = new FilterPopup();


  public FileTable() {
    super();
    initTable();
  }


  public void setModel(FileTableModel dataModel) {
    super.setModel(dataModel);
    sorter.setModel(dataModel);
    FilterState filterState = new FilterState(dataModel.getExtension());
    filterPopup.setModel(filterState);
    ExtensionsFilter filter = new ExtensionsFilter(filterState);
    sorter.setRowFilter(filter);
    setRowSorter(sorter);
    filterState.addListDataListener(new FilterChangedListener(this, filter));

    setRowHeight(22);
    setColumnWidth(0, 150);
    setColumnMaxWidth(1,70);

  }

  private void initTable() {
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    setShowVerticalLines(false);
    setPreferredScrollableViewportSize(this.getPreferredSize());
    setAutoCreateRowSorter(true);
    getTableHeader().setReorderingAllowed(false);

    sorter = new TableRowSorter<>();
    sorter.setRowFilter(null);
    setRowSorter(sorter);
    getTableHeader().addMouseListener(new FilterPopupListener(filterPopup));


  }

  private void setColumnWidth(int column, int width) {
    TableColumn tableColumn = getColumnModel().getColumn(column);
    tableColumn.setPreferredWidth(width);
    tableColumn.setMinWidth(width);
  }
  private void setColumnMaxWidth(int column, int width) {
    TableColumn tableColumn = getColumnModel().getColumn(column);
    tableColumn.setMaxWidth(width);
  }


}
