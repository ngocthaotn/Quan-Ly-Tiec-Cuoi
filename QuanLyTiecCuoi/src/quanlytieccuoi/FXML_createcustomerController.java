/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections. ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.hibernate.SessionFactory;
import quanlytieccuoi.models.Customers;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML_createcustomerController implements Initializable {
    
    private String id;
    
    SessionFactory factory;  
    
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private DatePicker dpDateOfBirth;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtAddress;
    @FXML
    private Label lbFirstName;
    @FXML
    private Label lbLastName;
    @FXML
    private Label lbGender;
    @FXML
    private Label lbDateOfBirth;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbPhoneNumber;
    @FXML
    private Label lbAddress;
    @FXML
    private Button btn;
    @FXML
    private ComboBox<String> comboboxGenDer;
    
    @FXML
    private TableView<Customers> tbCus;
    @FXML
    private TextField txtSearchBox;
    
    @FXML
    private Button btUpdate;
    @FXML
    private Button btDelete;
    @FXML
    private Button btnExit;
    
    //Tạo đố tượng Utils
    UtilsDao utils = new UtilsDao();
    TableRow row;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.btUpdate.setVisible(false);
        this.btDelete.setVisible(false);
        ImageView imageView = new ImageView(new Image
        ("quanlytieccuoi/Images/previous.png"));
        imageView.setFitWidth(32);
        btnExit.setGraphic(imageView);
        // vô hiệu hóa ô nhập liệu datepicker
        dpDateOfBirth.getEditor().setDisable(true);
        //Mở sesionFactory
        this.factory = HibernateUtil.getSessionFactory();
        //add giá trị cho comboboxGender
        ObservableList<String> list = FXCollections.observableArrayList("Male",
                "Female","Other");
        comboboxGenDer.setItems(list);
        //comboBoxPosition.setItems(list1);
        reloadTableView(utils.getCustomer(""));
        txtFirstName.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkFirstnameLastName(txtFirstName.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbFirstName, tooltip);
            }else if(-1 == kq){ // firstname k hop le
                tooltip.setText("FirstName quá dài,\nFirstName khoảng 15 ký tự"
                        + "\nVí dụ: Vo");
                utils.showError(lbFirstName, tooltip); 
            }else if(1 == kq){
                tooltip.setText("FirstName không hợp lệ\nFirstName không chứa ký "
                        + "tự đặc biet và khoảng trắng\nVí dụ: Vo");
                utils.showError(lbFirstName, tooltip); 
            }else//0
                lbFirstName.setVisible(false); // hợp lệ, ẩn lb  
        });
        
        txtLastName.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkFirstnameLastName(txtLastName.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbLastName, tooltip);
            }else if(-1 == kq){ // firstname k hop le
                tooltip.setText("LastName quá dài,\nLastName khoảng 15 ký tự\n"
                        + "Ví dụ: Vo");
                utils.showError(lbLastName, tooltip); 
            }else if(1 == kq){
                tooltip.setText("LastName không hợp lệ\nLastName không chứa ký "
                        + "tự đặc biet và khoảng trắng\nVí dụ: Vo");
                utils.showError(lbLastName, tooltip); 
            }else//0
                lbLastName.setVisible(false); // hợp lệ, ẩn lb  
        });
        txtAddress.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkLongString(txtAddress.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbAddress, tooltip);
            }else if(1 == kq){
                tooltip.setText("Address không hợp lệ\nAddress không chứa ký "
                        + "tự đặc biệt\nVí dụ: 799/22/37 Nguyễn Kiệm, p3, gò vấp");
                utils.showError(lbAddress, tooltip); 
            }else//0
                lbAddress.setVisible(false); // hợp lệ, ẩn lb 
        });
        
        txtEmail.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkEmail(txtEmail.getText());
            if(kq == 1){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbEmail, tooltip);
            }else if(2 == kq){ // sai định dạng 
                tooltip.setText("Email không hợp lệ\n Ví dụ: vtv98@gmail.com!!");
                utils.showError(lbEmail, tooltip); 
            }else
                lbEmail.setVisible(false); // hợp lệ, ẩn lb
        });
        
        txtPhoneNumber.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkNumber(txtPhoneNumber.getText());
            if(0 == kq){ // chuỗi k hợp lệ, có ký tự
                tooltip.setText("Phone Number không hợp lệ, không đươc chứa ký "
                        + "tự\nVí dụ: 0857724561!!");
                utils.showError(lbPhoneNumber, tooltip);
            }else if(2 == kq){ //sđt phai băt dau bang 0
                tooltip.setText("Phone Number không hợp lệ\n Phải bắt đầu bằng "
                        + "0\nVí dụ: 0857724561!!");
                utils.showError(lbPhoneNumber, tooltip);
                tooltip.setText("Phone Number không hợp lệ\nVí dụ: vtv98@gmail.com!!");
                utils.showError(lbPhoneNumber, tooltip); 
            }else if(-1 == kq){//sđt phải gồm 10 số
                tooltip.setText("Phone Number gồm 10 số\nVí dụ: 0857724561!!");
                utils.showError(lbPhoneNumber, tooltip); 
            }else if(-2 == kq){ // chuỗi rỗng
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPhoneNumber, tooltip); 
            }else
                lbPhoneNumber.setVisible(false); // hợp lệ, ẩn lb
        });
        comboboxGenDer.valueProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkEmpty(comboboxGenDer.getSelectionModel()
                    .getSelectedItem().toString());
            if(1 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbGender, tooltip);
            }else 
                lbGender.setVisible(false); 
        });
        dpDateOfBirth.valueProperty().addListener(ov -> {
            Tooltip tooltip = utils.tooltipHandler();
            
            if(dpDateOfBirth.getValue() == null){
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbDateOfBirth, tooltip);
            }else
                lbDateOfBirth.setVisible(false); 
            
        });
        
        dpDateOfBirth.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) > -1);
            }
        });
        
        this.tbCus.setRowFactory(param -> {
            row = new TableRow();
            
            row.setOnMouseClicked(et -> {
                this.btUpdate.setVisible(true);
                this.btDelete.setVisible(true);
                
                btn.setDisable(true);
                
                Customers cus = this.tbCus.getSelectionModel().getSelectedItem();
                
                this.txtFirstName.setText(cus.getFirstName());
                this.txtLastName.setText(cus.getLastName());
                this.txtEmail.setText(cus.getEmail());
                this.txtAddress.setText(cus.getAddress());
                this.txtPhoneNumber.setText(cus.getPhoneNumber());
                //this.dpDateOfBirth.set
                this.comboboxGenDer.getSelectionModel().select(cus.getGender());
                
            });
            
            return row;
        });
        
        this.txtSearchBox.textProperty().addListener(et -> {
            List<Customers> cus = utils.getCustomer(this.txtSearchBox.getText());
            reloadTableView(cus);
        });
        try{
            this.loadCustomers();
        }
        catch(SQLException ex){
            System.err.println("ERROR:" + ex.getMessage());
        }
        
        
        btnExit.setOnAction(ov ->{
            utils.changeSceneHandler("FXML_choosecustomer.fxml", ov);
        });
        
    }
    
    @FXML
    public void comboboxchanged (ActionEvent e){
        
    }
    private void loadCustomers() throws SQLException {
         
        List<Customers> cus = utils.getCustomer("");
        //tao cot 
        TableColumn clFName = new TableColumn("First Name");
        TableColumn clLName = new TableColumn("Last Name");
        TableColumn clGender = new TableColumn("Gender");
        TableColumn clDOB = new TableColumn("Date of birth");
        TableColumn clPhone = new TableColumn("Phone Number");
        TableColumn clEmail = new TableColumn("Email");
        TableColumn clAddr = new TableColumn("Address");
        TableColumn clODate = new TableColumn("Opening Date");
       
        clFName.setCellValueFactory(new PropertyValueFactory("firstName"));
        clLName.setCellValueFactory(new PropertyValueFactory("lastName"));
        clDOB.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        clGender.setCellValueFactory(new PropertyValueFactory("gender"));
        clPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        clEmail.setCellValueFactory(new PropertyValueFactory("email"));
        clAddr.setCellValueFactory(new PropertyValueFactory("address"));
        clODate.setCellValueFactory(new PropertyValueFactory("openingDate"));
        // do data vao moi cot
        this.tbCus.getColumns().addAll(clFName,clLName,clDOB,clGender,clPhone,
                clEmail,clAddr,clODate);
        
        //this.tbCus.getItems().addAll(cus);
        btn.setDisable(false);
    }
     
    private void reloadTableView(List<Customers> cus) {
        
        this.tbCus.getItems().clear();
        this.tbCus.getItems().addAll(cus);
        txtAddress.clear();
        txtEmail.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtPhoneNumber.clear();
        comboboxGenDer.setValue(null);
        dpDateOfBirth.setValue(null);
    }
    public void updateCustomer(ActionEvent event) {
        if(this.tbCus.getSelectionModel().getSelectedItem() != null){
            Customers cus = this.tbCus.getSelectionModel().getSelectedItem();
            if(!lbFirstName.isVisible() && !lbLastName.isVisible() 
                && !lbGender.isVisible() && !lbPhoneNumber.isVisible() 
                && !lbAddress.isVisible() && !lbEmail.isVisible()){
                String fn = this.txtFirstName.getText();
                String ln = this.txtLastName.getText();
                String phone = this.txtPhoneNumber.getText();
                String email = this.txtEmail.getText();
                String address = this.txtAddress.getText();
                String gender = this.comboboxGenDer.getSelectionModel().getSelectedItem();
                String ns = cus.getDateOfBirth().toString();
                if(dpDateOfBirth.getValue() != null)
                    ns = dpDateOfBirth.getValue().toString(); 
                
                
                utils.addOrUpdateCustomer(cus.getCustomerId(),
                        fn, ln, ns, phone, email, gender, address);
                utils.alertInformationHandler("Notification",
                            "successfully updateed!");
                reloadTableView(utils.getCustomer(""));
                reloadTableView(utils.getCustomer(""));
                
                // bỏ vô hiệu hóa
                btn.setDisable(false);    
            }else{
                if(txtFirstName.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbFirstName, tooltip);
                }
                if(txtLastName.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbLastName, tooltip);
                }
                if(txtAddress.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbAddress, tooltip);
                }
                if(txtEmail.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbEmail, tooltip);
                }
                if(txtPhoneNumber.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbPhoneNumber, tooltip); 
                }
                if(comboboxGenDer.getSelectionModel().getSelectedItem()==null){

                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbGender, tooltip);
                }
                if(dpDateOfBirth.getValue() == null){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbDateOfBirth, tooltip);
                }
            }
                
            
        }else{
           Alert a = utils.alertErrorHandler("Notification", "You have not selected updates");
           a.showAndWait();
        }
        
    }

    public void deleteCustomer(ActionEvent event){
        if(this.tbCus.getSelectionModel().getSelectedItem() != null){
            Customers cus = this.tbCus.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete this row ?");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    utils.deleteCustomer(cus.getCustomerId());
                    utils.alertInformationHandler("Notification",
                                "Successfully Deleted");
                    reloadTableView(utils.getCustomer(""));
                    reloadTableView(utils.getCustomer(""));
                    btn.setDisable(false);
                }
            });
        }else{
            Alert a = utils.alertErrorHandler("Notification", "You have not selected the"
                    + " deletion information!");
            a.showAndWait();
        }
    }
     
    public void saveCustomer(){
        if(!lbFirstName.isVisible() && !lbLastName.isVisible() 
                && !lbGender.isVisible()
                && !lbPhoneNumber.isVisible() && !lbAddress.isVisible() 
                && !lbEmail.isVisible() && !lbDateOfBirth.isVisible()){
            String ns = "";
            if(dpDateOfBirth.getValue() != null)
                ns = dpDateOfBirth.getValue().toString();
                
            int kq = utils.checkSaveCustomers(txtFirstName.getText(), txtLastName.getText()
                    , ns, comboboxGenDer.getValue(),txtPhoneNumber.getText() ,
                    txtAddress.getText(), txtEmail.getText());
            if(-1 == kq){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Email is used! Please chosse another Email!");
                utils.showError(lbEmail, tooltip);
            }else if(0 == kq){
                utils.alertInformationHandler("Notification",
                                "Account successfully created!");
                reloadTableView(utils.getCustomer(""));
                reloadTableView(utils.getCustomer(""));
                
            }else{
                if(txtFirstName.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbFirstName, tooltip);
                }
                if(txtLastName.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbLastName, tooltip);
                }
                if(txtAddress.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbAddress, tooltip);
                }
                if(txtEmail.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbEmail, tooltip);
                }
                if(txtPhoneNumber.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbPhoneNumber, tooltip); 
                }
                if(comboboxGenDer.getSelectionModel().getSelectedItem()==null){

                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbGender, tooltip);
                }
                if(dpDateOfBirth.getValue() == null){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbDateOfBirth, tooltip);
                }
            }
                
        } 
    }  
    
    public void customerKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            try {
                saveCustomer();
                this.loadCustomers();
            } catch (SQLException ex) {
                System.out.println("ERROR:" + ex.getMessage());
            }
        }
    }
}
