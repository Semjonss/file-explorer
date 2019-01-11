package ui;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FileExplorerMenuBar extends JMenuBar {

  private final FileExplorer fileExplorer;

  public FileExplorerMenuBar(FileExplorer fileExplorer) {
    super();
    super.add(buildFileMenu());
    this.fileExplorer = fileExplorer;
  }


  private JMenu buildFileMenu() {
    JMenu file = new JMenu("File");
    file.setMnemonic('f');
    file.add(buildFTPItem());
    file.add(buildQuitItem());
    return file;
  }

  private JMenuItem buildQuitItem() {
    JMenuItem quit = new JMenuItem("Quit");
    quit.setMnemonic('q');
    quit.addActionListener(e -> System.exit(0));
    return quit;
  }

  private JMenu buildFTPItem() {

    JMenu menuNew = new JMenu("New");
    menuNew.setMnemonic('n');

    JMenuItem localCreateButton = new JMenuItem("Local");
    localCreateButton.setMnemonic('l');
    localCreateButton.addActionListener(e -> fileExplorer.setLocalModel());
    menuNew.add(localCreateButton);

    JMenuItem ftpCreateButton = new JMenuItem("FTP");
    ftpCreateButton.addActionListener(e -> new FtpCetClientDialog(fileExplorer));

    ftpCreateButton.setMnemonic('t');
    menuNew.add(ftpCreateButton);

    return menuNew;
  }


}
