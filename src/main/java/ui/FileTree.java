package ui;

import javax.swing.JTree;

public class FileTree extends JTree {


  public FileTree() {
    super();
    init();
  }

  private void init(){
    setShowsRootHandles(true);
    setRootVisible(false);
    expandRow(0);
  }



}
