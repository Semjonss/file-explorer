package models;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import managers.file.FileManager;
import models.files.FileModel;

public class FileTreeModel extends DefaultTreeModel {


  private FileModel root;
  private FileManager fileManager;
  private EventListenerList listenerList = new EventListenerList();

  public FileTreeModel(FileModel root, FileManager fileManager) {
    super(new DefaultMutableTreeNode(root));
    this.root = root;
    this.fileManager = fileManager;
  }

  @Override
  public Object getRoot() {
    return root;
  }

  @Override
  public Object getChild(Object parent, int index) {
    FileModel[] children = fileManager.getChildFolders(((FileModel) parent));
    if ((children == null) || (index >= children.length)) {
      return null;
    }
    return children[index];
  }


  public int getChildCount(Object parent) {
    return fileManager.getChildCount((FileModel) parent);
  }


  @Override
  public boolean isLeaf(Object node) {
    return fileManager.isLastFolderLevel((FileModel) node);
  }


  @Override
  public void valueForPathChanged(TreePath path, Object newValue) {

  }

  @Override
  public int getIndexOfChild(Object parent, Object child) {
    return ((FileModel) child).getIndex();
  }

  @Override
  public void addTreeModelListener(TreeModelListener l) {
    listenerList.add(TreeModelListener.class, l);
  }

  @Override
  public void removeTreeModelListener(TreeModelListener l) {
    listenerList.remove(TreeModelListener.class, l);
  }


}
