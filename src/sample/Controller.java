package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Controller {

    public static Stage stage;
    File file;

    @FXML
    TextArea textPane;
    @FXML
    MenuItem open;
    @FXML
    MenuItem save;
    @FXML
    MenuItem close;
    @FXML
    AnchorPane aboutPane;
    @FXML
    MenuItem about;



    public void openAction(ActionEvent actionEvent) {
        FileChooser  fc = new FileChooser();
        file = fc.showOpenDialog(null);
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            textPane.setText(new String(fileBytes, StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(file.getName());
        System.out.println("Ok!");
    }

    public void saveAction(ActionEvent actionEvent) {
        try {
            byte[] fileBytes = textPane.getText().getBytes();
            FileChooser fc = new FileChooser();
            file = fc.showSaveDialog(null);
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(fileBytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(file.getName());
    }

    public void closeAction(ActionEvent actionEvent) {
        textPane.setText("");
        file.exists();
        stage.setTitle("Note");
    }

    public void aboutAction(ActionEvent actionEvent) {
        aboutPane.setVisible(true);
        aboutPane.setDisable(false);
        aboutPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aboutPane.setDisable(true);
                aboutPane.setVisible(false);
            }
        });
    }
}
