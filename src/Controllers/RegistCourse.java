package Controllers;

import Model.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistCourse {

    @FXML public TextField b1;
    @FXML public Label t1;
    @FXML public Label t2;
    @FXML public Label t3;

    Connection con= DataBaseConnection.getConnection();

    @FXML
    public void Back(ActionEvent event) throws Exception{
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
    }

    @FXML
    public void Submit (ActionEvent event) throws Exception{
        if(b1.getText().isEmpty()){
            t1.setVisible(true);
            t2.setVisible(false);
            return;
        }
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Course where CID="+b1.getText());
        if(resultSet.next()==false){
            t1.setVisible(true);
            t2.setVisible(false);
            return;
        }
        Statement statement1 = con.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("select * from REQUIRES where CID = "+b1.getText());
        if(resultSet1.next()==false){
            String query = " insert into Attempts (SID, CID) values ('"+DataBaseConnection.ID+"','"+b1.getText()+"')";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            t3.setVisible(true);
            t1.setVisible(false);
            return;
        }else{
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from attempts where SID="+DataBaseConnection.ID+" AND CID ="+resultSet1.getString("COU_CID"));
            if(resultSet.next()==false){
                t2.setVisible(true);
                t1.setVisible(false);
                return;
            }
        }
        while (resultSet1.next()){
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from attempts where SID="+DataBaseConnection.ID+" AND CID ="+resultSet1.getString("COU_CID"));
            if(resultSet.next()==false){
                t2.setVisible(true);
                t1.setVisible(false);
                return;
            }
        }
        String query = " insert into Attempts (SID, CID) values ('"+DataBaseConnection.ID+"','"+b1.getText()+"')";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.execute();
        t1.setVisible(false);
        t2.setVisible(false);
        t3.setVisible(true);
    }

}
