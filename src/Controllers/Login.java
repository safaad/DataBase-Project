package Controllers;

import Model.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Login {

    @FXML public TextField b1;
    @FXML public TextField b2;
    @FXML public Label t1;

    @FXML
    public void Login(ActionEvent event) throws Exception{
        int i=0;
        if(b1.getText().isEmpty() || b2.getText().isEmpty()){
            t1.setVisible(true);
        }
        if(b1.getText().equals("admin")){
            if(b2.getText().equals("admin")){
                Parent root = FXMLLoader.load(getClass().getResource("../GUI/Administrator.fxml"));
                Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
                S.setScene(new Scene(root));
                S.show();
            }
        }
        try {
            i=Integer.parseInt(b1.getText());
        }catch (Exception e){
            t1.setVisible(true);
            return;
        }
        if(i>9999 && i<19999){
            Statement statement;
            try (Connection con = DataBaseConnection.getConnection()) {
                statement = con.createStatement();
            }
            ResultSet resultSet = statement.executeQuery("select * from Teacher where TID="+b1.getText()+" AND PASS="+b2.getText());
            if(resultSet.next()==false){
                t1.setVisible(true);
                return;
            }
            else {
                DataBaseConnection.username="teacher";
                DataBaseConnection.password="teacher";
                DataBaseConnection.ID=b1.getText();
                Parent root = FXMLLoader.load(getClass().getResource("../GUI/Teacher.fxml"));
                Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
                S.setScene(new Scene(root));
                S.show();
            }
        }
        else {
            if(i>=20000){
                Connection con =DataBaseConnection.getConnection();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from student where SID="+Integer.parseInt(b1.getText())+" AND PASS="+b2.getText());
                if(resultSet.next()==false){
                    t1.setVisible(true);
                    return;
                }
                else {
                    DataBaseConnection.username="student";
                    DataBaseConnection.password="student";
                    DataBaseConnection.ID=b1.getText();
                    Parent root = FXMLLoader.load(getClass().getResource("../GUI/Student.fxml"));
                    Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
                    S.setScene(new Scene(root));
                    S.show();
                }
            }
        }
    }
}
