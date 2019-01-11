package ui;

import constants.Constants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import listeners.CustomListSelectionListener;
import listeners.CustomTreeSelectionListener;
import listeners.OpenFileForPreviewListener;
import managers.file.impl.FTPFileManager;
import managers.file.impl.LocalFileManager;
import models.FileTableModel;
import models.FileTreeModel;
import models.files.FileModel;
import models.files.impl.FTPFileModel;
import org.apache.commons.net.ftp.FTPClient;
import utils.FileUtil;

public class FileExplorer extends JFrame {

  private FileTable fileTable;
  private FileTree fileTree;
  private JProgressBar progressBar;


  public FileExplorer() throws HeadlessException {

    initWindowProperties();
    setContentPane(getGui());
    setLocationByPlatform(true);
    setMinimumSize(getSize());

    setLocalModel();

    setJMenuBar(new FileExplorerMenuBar(this));

  }

  private Container getGui() {
    initFileTree();
    initFileTable();
    JPanel result = new JPanel(new BorderLayout(3, 3));
    result.setBorder(new EmptyBorder(5, 5, 5, 5));
    JPanel tableView = new JPanel(new BorderLayout(3, 3));
    JScrollPane tableScroll = new JScrollPane(fileTable);
    Dimension d = tableScroll.getPreferredSize();
    tableScroll.setPreferredSize(new Dimension((int) d.getWidth(), (int) d.getHeight() / 2));
    tableView.add(tableScroll, BorderLayout.CENTER);
    JScrollPane treeScroll = new JScrollPane(fileTree);
    Dimension preferredSize = treeScroll.getPreferredSize();
    Dimension widePreferred = new Dimension(
        250,
        (int) preferredSize.getHeight());
    treeScroll.setPreferredSize(widePreferred);

    JSplitPane splitPane = new JSplitPane(
        JSplitPane.HORIZONTAL_SPLIT,
        treeScroll,
        tableView);
    result.add(splitPane, BorderLayout.CENTER);

    progressBar = new JProgressBar();
    JPanel progressBarPanel = new JPanel(new BorderLayout(3, 3));
    progressBarPanel.add(progressBar, BorderLayout.EAST);
    progressBar.setVisible(false);

    result.add(progressBarPanel, BorderLayout.SOUTH);

    return result;
  }


  private void initWindowProperties() {
    setTitle(Constants.WINDOW_TITLE);
    setSize(new Dimension(Constants.WINDOW_START_WIDTH, Constants.WINDOW_START_HEIGHT));
    setMinimumSize(new Dimension(Constants.WINDOW_MIN_WIDTH, Constants.WINDOW_MIN_HEIGHT));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

  }


  private void initFileTree() {
    fileTree = new FileTree();
  }

  private void initFileTable() {
    fileTable = new FileTable();
  }

  public void setLocalModel() {
    LocalFileManager localFileManager = new LocalFileManager();
    FileModel root = localFileManager.getNewLocalFileModel(FileUtil.getRoot(), -1);

    fileTable.setModel(new FileTableModel(localFileManager.getAllChild(root)));
    fileTree.setModel(new FileTreeModel(root, localFileManager));
    fileTree.addTreeSelectionListener(
        new CustomTreeSelectionListener(fileTable, fileTree, progressBar, localFileManager));
    fileTable.getSelectionModel()
        .addListSelectionListener(new CustomListSelectionListener(localFileManager));
    fileTable.addMouseListener(new OpenFileForPreviewListener(localFileManager));

  }

  public void setFTPModel(FTPClient client) {
    FTPFileManager ftpFileManager = new FTPFileManager(client);

    FileModel root = new FTPFileModel("/", -1, Constants.FOLDER_EXTENSION, true);

    fileTable.setModel(new FileTableModel(ftpFileManager.getAllChild(root)));
    fileTree.setModel(new FileTreeModel(root, ftpFileManager));
    fileTree.addTreeSelectionListener(
        new CustomTreeSelectionListener(fileTable, fileTree, progressBar, ftpFileManager));
    fileTable.getSelectionModel()
        .addListSelectionListener(new CustomListSelectionListener(ftpFileManager));
    fileTable.addMouseListener(new OpenFileForPreviewListener(ftpFileManager));
  }


}
