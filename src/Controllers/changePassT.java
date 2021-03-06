package Controllers;

import Model.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class changePassT {

    Connection con = DataBaseConnection.getConnection();

    @FXML
    public void Back (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Teacher.fxml"));
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.setScene(new Scene(root));
        S.close();
    }

    @FXML public TextField b1;
    @FXML public PasswordField b2;
    @FXML public PasswordField b3;
    @FXML public Label t1;

    @FXML
    public void submit(ActionEvent event) throws Exception{
        if(b1.getText().isEmpty() || b2.getText().isEmpty() || b3.getText().isEmpty()){
            t1.setVisible(true);
            return;
        }
        if(!b2.getText().equals(b3.getText())){
            t1.setVisible(true);
            return;
        }
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Teacher where TID="+DataBaseConnection.ID);
        resultSet.next();
        if( resultSet.getString("PASS").equals(b1.getText())){
            String query = " update Teacher set PASS ="+b2.getText()+"  where TID="+DataBaseConnection.ID;
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Teacher.fxml"));
            Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
            S.setScene(new Scene(root));
            S.close();
        }else{
            t1.setVisible(true);
            return;
        }

    }

}
