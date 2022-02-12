/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import quanlytieccuoi.models.Customers;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML_choosecustomerController implements Initializable {
    TableRow row;
    @FXML
    private TableView<Customers> tbCus;
    @FXML
    private TableColumn clFirstName;
    @FXML
    private TableColumn clLastName;
    @FXML
    private TableColumn ClNumberPhone;
    @FXML
    private TextField txtSearchBox;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnChoose;
    @FXML
    private Label lbFirstName;
    @FXML
    private Label lbLastName;
    @FXML
    private Label lbPhoneNumber;
    @FXML
    private Button btnBack;
    public String id;
    public Customers cus;
    UtilsDao utils = new UtilsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            utils.backHandler(btnBack);
            loadCustomers();
            reloadTableView(utils.getCustomer(""));
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        
        this.txtSearchBox.textProperty().addListener(et -> {
            List<Customers> cus = utils.getCustomer(this.txtSearchBox.getText());
            reloadTableView(cus);
            
        });
        btnCreate.setOnAction(e->{
            utils.changeSceneHandler("FXML_createcustomer.fxml", e);
        });
        
        this.tbCus.setRowFactory(param -> {
            row = new TableRow();
            row.setOnMouseClicked(et -> {
                btnChoose.setOnAction(ov ->{
                    btnChooseHandler();
                });
            });
            return row;
        });
        
        btnBack.setOnAction(ov ->{
            FXMLLoader loader = utils.changeSceneHandlers("FXML_home.fxml", ov);
            FXML_homeController controller = loader.getController();
            controller.setHome(true);
        });

    } 
    
    private void loadCustomers() throws SQLException {  
        clFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        clLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        ClNumberPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
    }
    
    private void reloadTableView(List<Customers> cus) {
        this.tbCus.getItems().clear();
        this.tbCus.getItems().addAll(cus);
    }
    
    public void buttonNextHandler(ActionEvent event){
        if(!lbFirstName.getText().isEmpty() && !lbLastName.getText().isEmpty()
                && !lbPhoneNumber.getText().isEmpty()){
            // id orders
            id = UUID.randomUUID().toString();
            FXMLLoader loader = utils.changeSceneHandlers("FXML_choosebanquet.fxml", event);
            FXML_choosebanquetController controller = loader.getController();
            controller.setChooseBanquetFromCus(cus, id);
        }else{
            Alert a = utils.alertErrorHandler("ERROR", "You have not selected the customer!");
            a.showAndWait();
        }
    }
    
    public void setChooseCustomer(Customers c){
        int i = 0;
        lbFirstName.setText(c.getFirstName());
        lbLastName.setText(c.getLastName());
        lbPhoneNumber.setText(c.getPhoneNumber());
        for(Customers customer : this.tbCus.getItems()){
            if(customer.getCustomerId().equals(c.getCustomerId())){
                this.tbCus.getSelectionModel().select(i);
                btnChooseHandler();
            }  
            i++;
        }
    }
    
    public void btnChooseHandler(){
        cus = this.tbCus.getSelectionModel().getSelectedItem();
        lbFirstName.setText(cus.getFirstName());
        lbLastName.setText(cus.getLastName());
        lbPhoneNumber.setText(cus.getPhoneNumber());
    }
}
