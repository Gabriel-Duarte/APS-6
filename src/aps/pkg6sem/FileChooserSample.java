package aps.pkg6sem;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class FileChooserSample extends Application {

    private final Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(final Stage stage) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(APS6SEM.stage1);
        if (file != null) {
            openFile(file);
        }

    }

    public static void teste() {
        Application.launch();
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    FileChooserSample.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
