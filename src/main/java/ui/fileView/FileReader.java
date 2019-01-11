package ui.fileView;

import java.util.concurrent.ExecutionException;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
import managers.file.FileManager;
import models.files.FileModel;

public abstract class FileReader<T> extends JDialog {


  final protected FileModel file;
  final protected FileManager fileManager;

  public FileReader(FileManager fileManager , FileModel file) {

    this.fileManager = fileManager;
    this.file = file;

    setTitle(file.getName());
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    new SwingWorker<T, Void>() {
      @Override
      protected T doInBackground() throws Exception {
        synchronized (FileReader.this.file) {
          return load(FileReader.this.file);
        }
      }
      @Override
      protected void done() {
        try {
          FileReader.this.initUI(get());
          FileReader.this.pack();
        } catch (InterruptedException | ExecutionException e) {
        }
      }
    }.execute();
  }


  protected abstract T load(FileModel file) throws Exception;

  protected abstract void initUI(T result);


}
