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
import java.sql.*;

public class FillGrades {
    private static String examid=null;
    private static String courseid=null;
    private static ObservableList<SCT> data= FXCollections.observableArrayList();
    Connection con = DataBaseConnection.getConnection();
    @FXML
    private TableView<SCT> tableGrades;
    @FXML
    private TextField cid,xid;
    @FXML
    private Text text1,text2,text3;

    @FXML
    public void searchGrade(ActionEvent event) throws IOException{
        String tid=DataBaseConnection.ID;

        if(cid.getText().isEmpty() || xid.getText().isEmpty()){
            text2.setVisible(true);
        }else{
            String query2="select * from exam where xid = "+xid.getText();
            String query="select * from sct where tid = "+tid+" and cid =" + cid.getText();
            Statement statement = null;
            Statement statement1=null;
            try {
                statement1=con.createStatement();
                statement = con.createStatement();
                ResultSet resultSet =statement.executeQuery(query);
                ResultSet res=statement1.executeQuery(query2);
                data.clear();

                if(resultSet.next()==false || res.next()==false){
                    text1.setVisible(true);
                }
                else{
                    tableGrades.getItems().clear();
                    tableGrades.getColumns().clear();
                    TableColumn sidCol=new TableColumn("sid");
                    TableColumn snameCol=new TableColumn("sname");
                    TableColumn gradeCol=new TableColumn("grades");

                    sidCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("sid"));
                    snameCol.setCellValueFactory(new PropertyValueFactory<SCT,String>("sname"));
                     gradeCol.setCellValueFactory(new PropertyValueFactory<SCT,TextField>("grades"));

                    try{
                        while(resultSet.next()){

                            SCT row=new SCT(resultSet.getString("sid"),resultSet.getString("cid"),resultSet.getString("cname"),resultSet.getString("sname"),resultSet.getString("tid"),resultSet.getString("tname"),resultSet.getString("nb_credits"),"","");

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
        String x,c,s,g;

        if(cid.getText().isEmpty() || xid.getText().isEmpty()){
            text2.setVisible(true);
        }else{
            x=xid.getText();
            c=cid.getText();
            for(SCT row:data){
                if(row.getGrades().getText().isEmpty())
                    text2.setVisible(true);
            }
            if(text2.isVisible())
                return;
            else{
                for(SCT row:data){
                    s=row.getSid();
                    g=row.getGrades().getText();
                    String query = "insert into  markregister (sid,xid,cid,mark) values ('"+s+"','"+x+"','"+c+"','"+g+"')";
                    PreparedStatement preparedStmt = null;
                    try {
                        preparedStmt = con.prepareStatement(query);
                        preparedStmt.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                text3.setVisible(true);
            }

        }
    }
}
