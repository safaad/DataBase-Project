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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddCourse {

    Connection con = DataBaseConnection.getConnection();

    @FXML
    public void Back (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Administrator.fxml"));
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
        S.setScene(new Scene(root));
        S.show();
    }


    @FXML public TextField b1;
    @FXML public TextField b2;
    @FXML public TextField b3;
    @FXML public TextField b4;
    @FXML public Label t1;
    @FXML public Label t2;
    @FXML public Label t3;
    @FXML public Label t4;
    @FXML public Label t5;

    @FXML
    public void insert (ActionEvent event) throws Exception {
        if( b1.getText().isEmpty() || b2.getText().isEmpty() || b3.getText().isEmpty() || b4.getText().isEmpty()){
            t5.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t1.setVisible(false);
            t4.setVisible(false);
            return;
        }
        try {
            Integer.parseInt(b1.getText());
        }catch (Exception e){
            t5.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t1.setVisible(false);
            t4.setVisible(false);
            return;
        }
        try {
            Integer.parseInt(b4.getText());
        }catch (Exception e){
            t5.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t1.setVisible(false);
            t4.setVisible(false);
            return;
        }
        try {
            Integer.parseInt(b3.getText());
        }catch (Exception e){
            t5.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t1.setVisible(false);
            t4.setVisible(false);
            return;
        }
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from TEACHER where TID="+Integer.parseInt(b4.getText()));
        if(resultSet.next()==false){
            t5.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t1.setVisible(false);
            t4.setVisible(false);
            return;
        }
        resultSet = statement.executeQuery("select * from COURSE where CID="+Integer.parseInt(b1.getText()));
        if(resultSet.next()==true){
            t5.setVisible(true);
            t2.setVisible(false);
            t3.setVisible(false);
            t1.setVisible(false);
            t4.setVisible(false);
            return;
        }
        String query = " insert into Course (CID, CNAME, NB_CREDITS, TID) values ('"+b1.getText()+"','"+b2.getText()+"','"+b3.getText()+"','"+b4.getText()+"')";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.execute();
        t1.setVisible(false);
        t2.setVisible(true);
        t3.setVisible(true);
        t4.setVisible(false);
        t5.setVisible(false);
        b1.clear();
        b2.clear();
        b3.clear();
        b4.clear();
    }

    @FXML
    public void search (ActionEvent event) throws Exception {
        if(b1.getText().isEmpty()){
            t1.setVisible(true);
            t5.setVisible(false);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
            return;
        }
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from COURSE where CID="+Integer.parseInt(b1.getText()));
        if(resultSet.next()==false){
            t1.setVisible(true);
            t5.setVisible(false);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
        }
        b1.setText(resultSet.getString("CID"));
        b2.setText(resultSet.getString("cname"));
        b3.setText(resultSet.getString("NB_CREDITS"));
        b4.setText(resultSet.getString("TID"));
        t5.setVisible(false);
        t1.setVisible(false);
        t2.setVisible(false);
        t3.setVisible(false);
        t4.setVisible(false);

    }

    @FXML
    public void update (ActionEvent event) throws Exception {
        if(b1.getText().isEmpty() || b2.getText().isEmpty() || b3.getText().isEmpty() || b4.getText().isEmpty() ){
            t5.setVisible(true);
            t2.setVisible(false);
            t4.setVisible(false);
            t3.setVisible(false);
            t1.setVisible(false);
            return;
        }
        String query = " update course set CNAME='"+b2.getText()+"',NB_CREDITS='"+b3.getText()+"',TID='"+b4.getText()+"' where CID="+b1.getText();
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.execute();
        t1.setVisible(false);
        t2.setVisible(true);
        t4.setVisible(true);
        t3.setVisible(false);
        t5.setVisible(false);
        b1.clear();
        b2.clear();
        b3.clear();
        b4.clear();
    }
}
