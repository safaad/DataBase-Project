package Controllers;

import Model.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student {
    Connection con = DataBaseConnection.getConnection();

    @FXML public TableView tableStd;

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

    public void viewCourseS(ActionEvent event)throws Exception{
        String sid=DataBaseConnection.ID;
        tableStd.getItems().clear();
        tableStd.getColumns().clear();
        TableColumn c1=new TableColumn("CID");
        TableColumn c2=new TableColumn("Course Name");
        TableColumn c3=new TableColumn("Number Of credits");
        TableColumn c4=new TableColumn("Teacher Name");
        tableStd.getColumns().addAll(c1,c2,c3,c4);
        ObservableList<ObservableList> data= FXCollections.observableArrayList();
        String cid,nb_credits;
        String cname,tname;
        String query="select cid , cname,nb_credits,tname from sct where sid = "+sid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){
                    cid=resultSet.getString("cid");
                    cname=resultSet.getString("cname");
                    nb_credits=resultSet.getString("nb_credits");
                    tname=resultSet.getString("tname");
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for(int i=1 ; i<=resultSet.getMetaData().getColumnCount(); i++){
                        row.add(resultSet.getString(i));
                    }
                    tableStd.setItems(data);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    public void viewTeacherS(ActionEvent event)throws Exception{
        String sid=DataBaseConnection.ID;
        tableStd.getItems().clear();
        tableStd.getColumns().clear();
        TableColumn c1=new TableColumn("My Teachers");
        tableStd.getColumns().addAll(c1);
        ObservableList<ObservableList> data= FXCollections.observableArrayList();

        String tname;
        String query="select tname from sct where sid = "+sid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){
                    tname=resultSet.getString("tname");
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for(int i=1 ; i<=resultSet.getMetaData().getColumnCount(); i++){
                        row.add(resultSet.getString(i));
                    }
                    data.add(row);

                }
                tableStd.setItems(data);
            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    public void viewAllGradesS(ActionEvent event) throws IOException{
        String sid=DataBaseConnection.ID;
        tableStd.getItems().clear();
        tableStd.getColumns().clear();
        TableColumn c1=new TableColumn("CID");
        TableColumn c2=new TableColumn("Course Name");
        TableColumn c3=new TableColumn("XID");
        TableColumn c4=new TableColumn("XLabel");
        TableColumn c5=new TableColumn("DateOn");
        TableColumn c6=new TableColumn("MarkOn");
        TableColumn c7=new TableColumn("Mark");
        tableStd.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7);
        ObservableList<ObservableList> data= FXCollections.observableArrayList();
        int cid,xid,markon;
        double mark;
        String cname,xlabel,date;
        String query="select cid , cname,xid,xlabel,xdate,mark_on,mark from gradesStudents where sid = "+sid;
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            try{
                while(resultSet.next()){
                    cid=resultSet.getInt("cid");
                    cname=resultSet.getString("cname");
                    xid=resultSet.getInt("xid");
                    xlabel=resultSet.getString("xlabel");
                    markon=resultSet.getInt("mark_on");
                    mark=resultSet.getDouble("mark");
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for(int i=1 ; i<=resultSet.getMetaData().getColumnCount(); i++){
                        row.add(resultSet.getString(i));
                    }
                    data.add(row);

                }
                tableStd.setItems(data);
            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}