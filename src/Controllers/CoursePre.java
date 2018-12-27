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

public class CoursePre {

    Connection con = DataBaseConnection.getConnection();

    @FXML public TextField b1;
    @FXML public TextField b2;
    @FXML public Label t1;
    @FXML public Label t2;

    @FXML
    public void Cancel(ActionEvent event) throws Exception {
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
    }

    @FXML
    public void Submit(ActionEvent event) throws Exception {
        if(b1.getText().isEmpty() || b2.getText().isEmpty()){
            t1.setVisible(true);
            t2.setVisible(false);
            return;
        }
        try{
            Integer.parseInt(b1.getText());
            Integer.parseInt(b2.getText());
        }catch (Exception e){
            t1.setVisible(true);
            t2.setVisible(false);
            return;
        }
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Course where CID="+Integer.parseInt(b1.getText()));
        if(resultSet.next()==false){
            t1.setVisible(true);
            t2.setVisible(false);
            return;
        }
        resultSet = statement.executeQuery("select * from Course where CID="+Integer.parseInt(b2.getText()));
        if(resultSet.next()==false){
            t1.setVisible(true);
            t2.setVisible(false);
            return;
        }
        String query = " insert into REQUIRES (CID, COU_CID) values ('"+b1.getText()+"','"+b2.getText()+"')";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.execute();
        t2.setVisible(true);
        t1.setVisible(false);
        return;
    }

}
