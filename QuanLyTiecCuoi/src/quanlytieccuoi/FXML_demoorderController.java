/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SessionFactory;
import quanlytieccuoi.models.Orders;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML_demoorderController implements Initializable {

    @FXML
    private TableView<Orders> tbOrders;
    @FXML
    private Button btnView;
    @FXML
    private Button btnBack;

    @FXML
    private Button  btnPaid;
    @FXML
    private Button btnPrepayment;
    Orders orders; 
    UtilsDao utils = new UtilsDao();
    SessionFactory factory;  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.factory = HibernateUtil.getSessionFactory();
        
        btnView.setOnAction(o -> {
            if(tbOrders.getSelectionModel().getSelectedItem() != null){
                FXMLLoader loader = utils.changeSceneHandlers("FXML_order.fxml", o);
                FXML_orderController controller = loader.getController();
                orders = this.tbOrders.getSelectionModel().getSelectedItem();
                controller.setOrders(orders);
            }else{
                Alert a = utils.alertErrorHandler("ERROR: ", 
                        "You have not selected viewing information!");
                a.showAndWait();
            }
        });
        loadOrderInformation();
        btnBack.setOnAction(ov ->{
           FXMLLoader loader = utils.changeSceneHandlers("FXML_home.fxml", ov);
            FXML_homeController controller = loader.getController();
            controller.setHome(true);
        });
        btnPaid.setOnAction(e ->{
            List<Orders> f = utils.getOrders("0");
            reloadTableView(f);
        });
        
        btnPrepayment.setOnAction(e ->{
            List<Orders> f = utils.getOrders("1");
            reloadTableView(f);
        });

    }
    
    private void reloadTableView(List<Orders> o) {

        this.tbOrders.getItems().clear();
        this.tbOrders.getItems().addAll(o);
    }
    
    

    public void loadOrderInformation(){
        TableColumn clCustomerName = new TableColumn("CustomerName");
        TableColumn clHall = new TableColumn("BanquetHall");
        TableColumn clHallDate = new TableColumn("FeastDay");
        TableColumn clOrderDate = new TableColumn("OrderDate");
        TableColumn clTotalMoney = new TableColumn("TotalMoney");
        TableColumn clTimeOfDay = new TableColumn("TimeOfDate");
        TableColumn clPayment = new TableColumn("Payment");
        clCustomerName.setCellValueFactory(new PropertyValueFactory("customerId"));
        clHall.setCellValueFactory(new PropertyValueFactory("hallId"));
        clHallDate.setCellValueFactory(new PropertyValueFactory("dateId"));
        clOrderDate.setCellValueFactory(new PropertyValueFactory("orderDate"));
        clTotalMoney.setCellValueFactory(new PropertyValueFactory("totalMoney"));
        clTimeOfDay.setCellValueFactory(new PropertyValueFactory("timeOfDay"));
        clPayment.setCellValueFactory(new PropertyValueFactory("payment"));
        this.tbOrders.getColumns().addAll(clCustomerName, clHall, clHallDate
                ,clOrderDate, clTotalMoney, clTimeOfDay, clPayment);
        List<Orders> list = utils.getOrders();
        this.tbOrders.getItems().addAll(list); 
    }
    
}
