package Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Teacher {

    @FXML
    public void ExamPage (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AddExam.fxml"));
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
        S.setScene(new Scene(root));
        S.show();
    }

}
