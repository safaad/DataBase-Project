package Controllers;

import Model.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FillGrades {
    private static String examid=null;
    private static String courseid=null;
    Connection con = DataBaseConnection.getConnection();
    @FXML
    private TableView<SCT> tableGrades;
    @FXML
    private TextField cid,xid;
    @FXML
    private Text text1,text2;
    @FXML
    private TableColumn sidCol,snameCol,gradeCol;
    @FXML
    public void searchGrade(ActionEvent event) throws IOException{
        String tid=DataBaseConnection.ID;
        if(cid.getText().isEmpty() || xid.getText().isEmpty()){
            text2.setVisible(true);
        }else{
            String query="select * from teacherView where tid = "+tid+" and cid =" + cid.getText() + " and xid = " + xid.getText();
            Statement statement = null;
            try {
                statement = con.createStatement();
                ResultSet resultSet =statement.executeQuery(query);
                ObservableList<SCT> data= FXCollections.observableArrayList();

                if(resultSet.next()==false){
                    text1.setVisible(true);
                }
                else{
                    sidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("sid"));
                    snameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("sname"));
                    gradeCol.setCellValueFactory(new PropertyValueFactory<SCT,TextField>("grades"));

                    try{
                        while(resultSet.next()){

                            SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),resultSet.getString("tid"),resultSet.getString("xlabel"),resultSet.getString("xdate"));

                            data.add(row);
                        }
                        tableGrades.setItems(data);
                        tableGrades.getColumns().addAll(sidCol,snameCol,gradeCol);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }

    public void submitGrade(javafx.event.ActionEvent event) {
    }
}
