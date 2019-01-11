import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import ui.FileExplorer;

public class Main {

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Throwable e) {
      //silent

    }
    SwingUtilities.invokeLater(() -> {
          FileExplorer window = new FileExplorer();
          window.setVisible(true);
        }
    );

  }

}


