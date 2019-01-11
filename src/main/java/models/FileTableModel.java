package models;

import java.util.Set;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;
import models.files.FileModel;

public class FileTableModel extends AbstractTableModel {

  private static String[] columns = {"Name", "Extension"};

  private FileModel[] files;
  private Set<String> extensions;



  public FileTableModel(FileModel[] files) {
    this.files = files;
    collectDistinctColumnItems();
  }

  public FileModel getFile(int row) {
    return files[row];
  }


  public void setFiles(FileModel[] files) {
    this.files = files;
    collectDistinctColumnItems();
    fireTableDataChanged();
  }


  @Override
  public int getRowCount() {
    return files.length;
  }

  @Override
  public int getColumnCount() {
    return columns.length;
  }


  @Override
  public Object getValueAt(int row, int column) {
    FileModel file = files[row];
    switch (column) {
      case 0:
        return file.getName();
      case 1:
        return file.getExtension();
      default:
        System.err.println("Logic Error");
    }
    return "";
  }

  public Class<?> getColumnClass(int column) {
    return String.class;
  }

  public String getColumnName(int column) {
    return columns[column];
  }

  public Set<String> getExtension(){
    return extensions;
  }

  private void collectDistinctColumnItems() {
    clearExtensions();
    for (FileModel file : files) {
      extensions.add(file.getExtension());
    }
  }

  private void clearExtensions(){
    if(extensions == null){
      extensions = new TreeSet<>();
    }else{
      extensions.clear();
    }
  }



}
