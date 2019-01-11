package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import managers.FTPClientFactory;
import models.FTPConectData;
import org.apache.commons.net.ftp.FTPClient;

public class FtpCetClientDialog extends JDialog {

  private final FileExplorer fileExplorer;
  private JTextField hostname;
  private JTextField port;
  private JTextField username;
  private JTextField password;

  public FtpCetClientDialog(FileExplorer fileExplorer) {
    super();
    setModal(true);
    setTitle("FTP");
    this.fileExplorer = fileExplorer;

    add(getPanelWithText(), BorderLayout.NORTH);
    addButtons();
    setBounds(200, 200, 250, 250);
    setVisible(true);
  }

  JPanel getPanelWithText() {
    JPanel panel = new JPanel(new GridLayout(6, 2, 6, 6));
    JLabel hostnameLabel = new JLabel("hostname", JLabel.RIGHT);
    panel.add(hostnameLabel);

    hostname = new JTextField();
    panel.add(hostname);

    JLabel portLabel = new JLabel("port", JLabel.RIGHT);
    panel.add(portLabel);

    port = new JTextField();
    panel.add(port);

    JLabel usernameLabel = new JLabel("username", JLabel.RIGHT);
    panel.add(usernameLabel);

    username = new JTextField();
    panel.add(username);

    JLabel passwordLabel = new JLabel("password", JLabel.RIGHT);
    panel.add(passwordLabel);

    password = new JPasswordField();
    panel.add(password);

    return panel;
  }

  void addButtons() {
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    bottomPanel.add(okButton());
    bottomPanel.add(cancelButton());
    add(bottomPanel, BorderLayout.SOUTH);
  }

  private JButton cancelButton() {
    return new JButton(new AbstractAction("Cancel") {
      @Override
      public void actionPerformed(ActionEvent e) {
        FtpCetClientDialog.this.setVisible(false);
      }
    });
  }

  private JButton okButton() {
    return new JButton(new AbstractAction("Ok") {
      @Override
      public void actionPerformed(ActionEvent e) {
        FTPConectData ftpFileModel = new FTPConectData(hostname.getText(), port.getText(),
            username.getText(),
            password.getText());
        FTPClient client;
        try {
          client = FTPClientFactory.buildSimpleClient(ftpFileModel);
        } catch (Exception e1) {
          return;
        }
        fileExplorer.setFTPModel(client);
        FtpCetClientDialog.this.setVisible(false);
      }
    });
  }

}
