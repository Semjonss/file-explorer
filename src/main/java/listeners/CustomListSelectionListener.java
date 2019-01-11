package listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import managers.file.FileManager;

public class CustomListSelectionListener implements ListSelectionListener {


  final private FileManager fileManager;

  public CustomListSelectionListener(FileManager fileManager) {
    this.fileManager = fileManager;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {

  }
}
