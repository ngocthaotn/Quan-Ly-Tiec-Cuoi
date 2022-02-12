/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML_monthchartController implements Initializable {

    @FXML 
    TextField tfnam;
    @FXML
    StackPane bd = new StackPane();
    UtilsDao utils = new UtilsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void thongkeThangHandler(ActionEvent e) throws ParseException{
        this.bd.getChildren().clear();
        String nam = tfnam.getText();
        if(!tfnam.getText().isEmpty()){
            if (!utils.checkNam(nam)){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("ERROR: ");
                a.setContentText("You need to enter a valid year. For example: 2019!");
                a.show();
            }else{
                int n = Integer.parseInt(tfnam.getText());
                List<Integer> ls = utils.listDoanhThuT(n);


                CategoryAxis xAxis = new CategoryAxis();
                xAxis.setLabel("Month");
                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Revenue");
                BarChart chart = new BarChart (xAxis, yAxis);
                for (int i = 1; i <= 12; i++){
                    XYChart.Series s = new XYChart.Series();
                    String thang = String.format("%s", i);
                    s.setName(thang);
                    s.getData().add(new XYChart.Data(thang, ls.get(i - 1)));
                    chart.getData().add(s);
                }
                bd.getChildren().add(chart);
            }
        }else{
            Alert a = utils.alertErrorHandler("ERROR: ", "Please enter the year of statistics!");
            a.showAndWait();
        }

            
        
        
    }
    
    
    public void thongkeQuyHandler(ActionEvent e) throws ParseException{
        this.bd.getChildren().clear();
        String nam = tfnam.getText();
        if(!tfnam.getText().isEmpty()){
            if (!utils.checkNam(nam)){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("ERROR ");
                a.setContentText("You need to enter a valid year. For example: 2019!");
                a.show();
            }else{
                  int n = Integer.parseInt(nam);
                  List<Integer> ls = utils.listDoanhThuT(n);
                ArrayList<Integer> lsK =  new ArrayList<>(4);

                for (int i = 0; i <= 3; i++){
                    int tien = 0;
                    for(int t = 0; t < 3; t++)
                        tien += ls.get(i * 3 + t);
                    lsK.add(i, tien);
                }
                CategoryAxis xAxis = new CategoryAxis();
                xAxis.setLabel("Quarters");
                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Revenue");
                 BarChart chart = new BarChart (xAxis, yAxis);
                 for (int i = 1; i <= 4; i++){
                    XYChart.Series s = new XYChart.Series();
                    String thang = String.format("%s", i);
                    s.setName(thang);
                    s.getData().add(new XYChart.Data(thang, lsK.get(i - 1)));
                    chart.getData().add(s);
                    bd.getChildren().add(chart);
                }
             }
        }else{
            Alert a = utils.alertErrorHandler("ERROR: ", "Please enter the year of statistics!");
            a.showAndWait();
        }
            
    }
    public void quayLaiHandler(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXML_home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
