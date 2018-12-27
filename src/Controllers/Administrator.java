package Controllers;

import Model.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Administrator {

    @FXML public Label t1;
    @FXML public Label t2;
    @FXML public Label t3;
    @FXML public Label t4;
    @FXML public Label t5;
    @FXML public Label t6;
    @FXML public TextField b1;
    @FXML public TextField b2;
    @FXML public TextField b3;

    Connection con = DataBaseConnection.getConnection();

    @FXML
    public void AddStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AddStudent.fxml"));
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
        S.setScene(new Scene(root));
        S.show();
    }

    @FXML
    public void AddTeacher(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AddTeacher.fxml"));
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
        S.setScene(new Scene(root));
        S.show();
    }

    @FXML
    public void AddCourse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AddCourse.fxml"));
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
        S.setScene(new Scene(root));
        S.show();
    }

    @FXML
    public void submit (ActionEvent event) throws Exception {
        if(b1.getText().isEmpty()){
            t5.setVisible(true);
            t6.setVisible(false);
        }
        else {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Student where SID="+Integer.parseInt(b1.getText()));
            if(resultSet.next()==false){
                t5.setVisible(true);
                t6.setVisible(false);
            }
            else{
                String query = " DELETE FROM Student WHERE SID ="+b1.getText();
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM ATTEMPTS WHERE SID ="+b1.getText();
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM MARKREGISTER WHERE SID ="+b1.getText();
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                t5.setVisible(false);
                t6.setVisible(true);
            }
        }
        if(b2.getText().isEmpty()){
            t3.setVisible(true);
            t4.setVisible(false);
        }
        else{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Teacher where TID="+Integer.parseInt(b2.getText()));
            if(resultSet.next()==false){
                t3.setVisible(true);
                t4.setVisible(false);
            }
            else {
                String query = " DELETE FROM Teacher WHERE TID ="+b2.getText();
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                statement = con.createStatement();
                resultSet = statement.executeQuery("select * from Course where TID="+Integer.parseInt(b2.getText()));
                resultSet.next();
                String CID=resultSet.getString("CID");
                query = " DELETE FROM Course WHERE TID ="+b2.getText();
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM MARKREGISTER WHERE CID ="+CID;
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM REQUIRES WHERE CID ="+CID;
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM REQUIRES WHERE COU_CID ="+CID;
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM ATTEMPTS WHERE CID ="+CID;
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                t3.setVisible(false);
                t4.setVisible(true);
            }
        }
        if(b3.getText().isEmpty()){
            t1.setVisible(true);
            t2.setVisible(false);
        }
        else {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Course where CID="+Integer.parseInt(b3.getText()));
            if(resultSet.next()==false){
                t1.setVisible(true);
                t2.setVisible(false);
            }
            else {
                String query = " DELETE FROM Course WHERE TID ="+b3.getText();
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM MARKREGISTER WHERE CID ="+b3.getText();
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM REQUIRES WHERE CID ="+b3.getText();
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM REQUIRES WHERE COU_CID ="+b3.getText();
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                query = " DELETE FROM ATTEMPTS WHERE CID ="+b3.getText();
                preparedStmt = con.prepareStatement(query);
                preparedStmt.execute();
                t1.setVisible(false);
                t2.setVisible(true);
            }
        }
    }

}