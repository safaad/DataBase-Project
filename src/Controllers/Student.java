package Controllers;

import Model.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student {
    Connection con = DataBaseConnection.getConnection();

    @FXML public TableView<SCT> tableStd;

    @FXML
    public void ChangePass (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../GUI/ChangePassword.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage S = new Stage();
        S.setScene(new Scene(root1));
        S.setTitle("Change Password");
        S.show();
    }

    @FXML
    public void RegistCourse(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("../GUI/RegistCourse.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage S = new Stage();
        S.setScene(new Scene(root1));
        S.setTitle("Regist Course");
        S.show();
    }
    @FXML
    public void viewCourseS(ActionEvent event)throws Exception{
        String stid=DataBaseConnection.ID;
        tableStd.getItems().clear();
        tableStd.getColumns().clear();
        TableColumn cidCol=new TableColumn("cid");
        TableColumn cnameCol=new TableColumn("Crs Name");
        TableColumn tnameCol=new TableColumn("Tch Name");
        TableColumn nbCol=new TableColumn("nb_credits");
        cidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cid"));
        cnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cname"));
        tnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("tname"));
        nbCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("nb_credits"));

        ObservableList<SCT> data= FXCollections.observableArrayList();
        String cid,nb_credits;
        String sname,sid,cname,tname;
        String query="select * from sct where sid = "+stid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){
                    SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),resultSet.getString("tid"),resultSet.getString("tname"),resultSet.getString("nb_credits"));
                    data.add(row);
                }
                tableStd.setItems(data);
                tableStd.getColumns().addAll(cidCol,cnameCol,tnameCol,nbCol);

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    public void viewTeacherS(ActionEvent event)throws Exception {
        String stid=DataBaseConnection.ID;
        tableStd.getItems().clear();
        tableStd.getColumns().clear();
        TableColumn tidCol=new TableColumn("tid");

        TableColumn tnameCol=new TableColumn("Tch Name");


        tnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("tname"));
        tidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("tid"));

        ObservableList<SCT> data= FXCollections.observableArrayList();

        String query="select * from sct where sid = "+stid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){
                    SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),resultSet.getString("tid"),resultSet.getString("tname"),resultSet.getString("nb_credits"));
                    data.add(row);
                }
                tableStd.setItems(data);
                tableStd.getColumns().addAll(tidCol,tnameCol);

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void viewAllGradesS(ActionEvent event) throws IOException{
        String stid=DataBaseConnection.ID;
        tableStd.getItems().clear();
        tableStd.getColumns().clear();

        TableColumn cidCol=new TableColumn("cid");
        TableColumn cnameCol=new TableColumn("Crs name");
        TableColumn xidCol=new TableColumn("xid");
        TableColumn xlabelCol=new TableColumn("Exam label");
        TableColumn xdateCol=new TableColumn("Exam date");
        TableColumn markonCol=new TableColumn("Mark on");
        TableColumn markCol=new TableColumn("Mark");


        cidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cid"));
        cnameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("cname"));
        xidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("xid"));
        xlabelCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("xlabel"));
        xdateCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("xdate"));
        markonCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("mark_on"));
        markCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("mark"));

        ObservableList<SCT> data= FXCollections.observableArrayList();

        String query="select * from gradestudents where sid = "+stid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){


                        SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),"",resultSet.getString("xid"),resultSet.getString("xlabel"),resultSet.getString("xdate"),resultSet.getString("mark_on"),resultSet.getString("mark"));
                    data.add(row);
                }
                tableStd.setItems(data);
                tableStd.getColumns().addAll(cidCol,cnameCol,xdateCol,xidCol,xlabelCol,markCol,markonCol);

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
        Stage S =(Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        S.setScene(new Scene(root));
        S.show();
    }
}