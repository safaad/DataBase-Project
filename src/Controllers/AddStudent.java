package Controllers;

import Model.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddStudent {

    Connection con= DataBaseConnection.getConnection();

    @FXML
    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Administrator.fxml"));
        Stage S = (Stage) ((Node) event.getSource()).getScene().getWindow();
        S.close();
        S.setScene(new Scene(root));
        S.show();
    }

    @FXML public TextField b1;
    @FXML public TextField b2;
    @FXML public TextField b3;
    @FXML public TextField b4;
    @FXML public TextArea b5;
    @FXML public Label t1;
    @FXML public Label t3;
    @FXML public Label t2;
    @FXML public Label t4;
    @FXML public Label t5;

    @FXML
    public void insert(ActionEvent event) throws Exception {
        if(b2.getText().isEmpty() || b3.getText().isEmpty() || b4.getText().isEmpty() || b5.getText().isEmpty()){
            t1.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
            t5.setVisible(false);
            return;
        }
        if(b3.getText().charAt(4)!='-' || b3.getText().charAt(7)!='-'){
            t1.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
            t5.setVisible(false);
            return;
        }
        if(b4.getText().length()!=8){
            t1.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
            t5.setVisible(false);
            return;
        }
        if(!b1.getText().isEmpty()){
            t1.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
            t5.setVisible(false);
            return;
        }
        String query = " insert into STUDENT (SNAME, BDATE, PHONE, ADDRESS) values ('"+b2.getText()+"','"+b3.getText()+"','"+b4.getText()+"','"+b5.getText()+"')";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.execute();
        t1.setVisible(false);
        t2.setVisible(true);
        t3.setVisible(true);
        t4.setVisible(false);
        t5.setVisible(false);
    }

    @FXML
    public void search(ActionEvent event) throws Exception{
        if(b1.getText().isEmpty()){
            t5.setVisible(true);
            t1.setVisible(false);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
            return;
        }
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student where SID="+Integer.parseInt(b1.getText()));
        if(resultSet.next()==false){
            t5.setVisible(true);
            t1.setVisible(false);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
        }
        b2.setText(resultSet.getString("Sname"));
        b3.setText(resultSet.getString("BDate"));
        b4.setText(resultSet.getString("PHONE"));
        b5.setText(resultSet.getString("Address"));
        t5.setVisible(false);
        t1.setVisible(false);
        t2.setVisible(false);
        t3.setVisible(false);
        t4.setVisible(false);
    }

    @FXML
    public void update(ActionEvent event) throws Exception{
        if(b1.getText().isEmpty() || b2.getText().isEmpty() || b3.getText().isEmpty() || b4.getText().isEmpty() || b5.getText().isEmpty()){
            t1.setVisible(true);
            t2.setVisible(false);
            t4.setVisible(false);
            t3.setVisible(false);
            t5.setVisible(false);
            return;
        }
        String query = " update STUDENT set Sname='"+b2.getText()+"',BDate='"+b3.getText()+"',phone='"+b4.getText()+"',address='"+b5.getText()+"' where SID="+b1.getText();
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.execute();
        t1.setVisible(false);
        t2.setVisible(true);
        t4.setVisible(true);
        t3.setVisible(false);
        t5.setVisible(false);
    }
}