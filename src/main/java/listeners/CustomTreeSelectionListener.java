package listeners;

import javax.swing.JProgressBar;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import managers.file.FileManager;
import models.FileTableModel;
import models.files.FileModel;
import ui.FileTable;
import ui.FileTree;

public class CustomTreeSelectionListener implements TreeSelectionListener {

  final private FileTable fileTable;
  final private FileTree fileTree;
  final private JProgressBar progressBar;
  final private FileManager fileManager;


  public CustomTreeSelectionListener(FileTable fileTable, FileTree fileTree,
      JProgressBar progressBar, FileManager fileManager) {
    super();
    this.fileTable = fileTable;
    this.fileTree = fileTree;
    this.progressBar = progressBar;
    this.fileManager = fileManager;
  }

  @Override
  public void valueChanged(TreeSelectionEvent e) {
    changeTableData((FileModel) e.getPath().getLastPathComponent());

  }

  private void changeTableData(FileModel node) {
    fileTree.setEnabled(false);
    progressBar.setVisible(true);
    progressBar.setIndeterminate(true);

    fileTable.setModel(new FileTableModel(fileManager.getAllChild(node)));

    progressBar.setIndeterminate(false);
    progressBar.setVisible(false);
    fileTree.setEnabled(true);

  }
}
