package Controllers;


import Model.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teacher {
    private static String examid=null;
    private static String courseid=null;
    Connection con = DataBaseConnection.getConnection();
    @FXML
    private TableView<SCT> tableTch;
    @FXML
    public void viewCourseT(ActionEvent event) throws IOException{
        String tchid= DataBaseConnection.ID;
        tableTch.getItems().clear();
        tableTch.getColumns().clear();
        TableColumn cidCol=new TableColumn("cid");
        TableColumn cnameCol=new TableColumn("Crs Name");
        //TableColumn tnameCol=new TableColumn("Tch Name");
        TableColumn nbCol=new TableColumn("nb_credits");
        cidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cid"));
        cnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cname"));
        //tnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("tname"));
        nbCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("nb_credits"));

        ObservableList<SCT> data= FXCollections.observableArrayList();
        String cid,nb_credits;
        String sname,sid,cname,tname;
        String query="select * from sct where tid = "+tchid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){

                    SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),resultSet.getString("tid"),resultSet.getString("tname"),resultSet.getString("nb_credits"));
                    data.add(row);
                }
                tableTch.setItems(data);
                tableTch.getColumns().addAll(cidCol,cnameCol,nbCol);

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void viewStudentT(ActionEvent event) throws IOException{
        String tchid= DataBaseConnection.ID;
        tableTch.getItems().clear();
        tableTch.getColumns().clear();
        TableColumn cidCol=new TableColumn("Cid");
        TableColumn cnameCol=new TableColumn("Crs Name");
        TableColumn sidCol=new TableColumn("Sid");
        TableColumn snameCol=new TableColumn("Student Name");
        TableColumn nbCol=new TableColumn("nb_credits");
        cidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cid"));
        sidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("sid"));

        cnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cname"));
        snameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("sname"));
        nbCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("nb_credits"));

        ObservableList<SCT> data= FXCollections.observableArrayList();
        String cid,nb_credits;
        String sname,sid,cname,tname;
        String query="select * from sct where tid = "+tchid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){

                    SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),resultSet.getString("tid"),resultSet.getString("tname"),resultSet.getString("nb_credits"));
                    data.add(row);
                }
                tableTch.setItems(data);
                tableTch.getColumns().addAll(cidCol,cnameCol,sidCol,snameCol,nbCol);

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void viewExamT(ActionEvent event){
        String tchid= DataBaseConnection.ID;
        tableTch.getItems().clear();
        tableTch.getColumns().clear();
        TableColumn cidCol=new TableColumn("Cid");
        TableColumn cnameCol=new TableColumn("Crs Name");
        TableColumn xidCol=new TableColumn("Xid");
        TableColumn xlabelCol=new TableColumn("Exam Label");
        TableColumn dateCol=new TableColumn("Exam Date");
        cidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cid"));
        xidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("xid"));

        cnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cname"));
        xlabelCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("xlabel"));
        dateCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("xdate"));

        ObservableList<SCT> data= FXCollections.observableArrayList();
        String cid,nb_credits;
        String sname,sid,cname,tname;
        String query="select * from teacherview where tid = "+tchid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){

                    SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),resultSet.getString("nb_credits"),resultSet.getString("xid"),resultSet.getString("xlabel"),resultSet.getString("xdate"),resultSet.getString("mark_on"),resultSet.getString("mark"));
                    data.add(row);
                }
                tableTch.setItems(data);
                tableTch.getColumns().addAll(cidCol,cnameCol,xidCol,xlabelCol,dateCol);

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void ExamPage (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AddExam.fxml"));
        Stage S =(Stage)((Node) event.getSource()).getScene().getWindow();
        S.close();
        S.setScene(new Scene(root));
        S.show();
    }
    @FXML
    public void fillGrades(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/FillGrades.fxml"));
        Stage S =new Stage();
        S.setScene(new Scene(root));
        S.show();
    }

}
