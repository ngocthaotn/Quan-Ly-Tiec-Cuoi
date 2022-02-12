/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import quanlytieccuoi.models.Orders;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML_changepaymentController implements Initializable {

    @FXML
    private RadioButton rdoPayOff;
    @FXML
    private RadioButton rdoPrepayment;
    @FXML
    private Button btnCancel;
    Orders orders;
    UtilsDao  utils = new UtilsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCancel.setOnAction(o ->{
            FXMLLoader loader = utils.changeSceneHandlers("FXML_order.fxml", o);
            FXML_orderController controller = loader.getController();
            controller.setOrders(this.orders);
        });
    } 
    
    public void setPayment(Orders o){
        this.orders = o;
        if(o.getPayment() == 1)
            rdoPrepayment.setSelected(true);
        else
            rdoPayOff.setSelected(true);
        
    }
    
    public void updatePayment(ActionEvent e){
        if(rdoPayOff.isSelected()){
            orders.setPayment(0); // trả hết
            utils.addOrUpdateOrder(this.orders);
            Alert a = utils.alertInformationHandler("Notification",
                                "successfully updateed!");
            a.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        FXMLLoader loader = utils.changeSceneHandlers("FXML_order.fxml", e);
                        FXML_orderController controller = loader.getController();
                        controller.setOrders(this.orders);
                    }
            });
        }else{
            Alert a = utils.alertInformationHandler("Notification",
                                "Nothing changes!");
            a.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        FXMLLoader loader = utils.changeSceneHandlers("FXML_order.fxml", e);
                        FXML_orderController controller = loader.getController();
                        controller.setOrders(this.orders);
                    }
            });
        }
            
            
    }
    
}
