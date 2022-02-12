/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import quanlytieccuoi.models.Admin;
import quanlytieccuoi.models.Positions;
import quanlytieccuoi.models.Users;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML_createadminController implements Initializable {
    
    private String id;
    
    SessionFactory factory;  
    
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private DatePicker dpDateOfBirth;
    @FXML
    private DatePicker dpStartingDate;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtQualification;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField confirm;
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
    private Label lbUsername;
    @FXML
    private Label lbPassword;
    @FXML
    private Label lbAddress;
    @FXML
    private Label lbPosition;
    @FXML
    private Label lbstartingDate;
    @FXML
    private Label lbConfirm;
    @FXML
    private Label lbQualification;
    
    @FXML
    private ComboBox<String> comboboxGenDer;
    @FXML
    private ComboBox<Positions> comboboxPosition;
    
    @FXML
    private TableView<Admin> tbAdmin;
    @FXML
    private TextField txtSearchBox;
    
    @FXML
    private Button btn;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btDelete;
    @FXML
    private Button btnPre;
    
    
    //Tạo đố tượng Utils
    UtilsDao utils = new UtilsDao();
    TableRow row;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.btUpdate.setVisible(false);
        this.btDelete.setVisible(false);
        // tao nut previous
        utils.buttonPrevious(btnPre);
        //Mở sesionFactory
        this.factory = HibernateUtil.getSessionFactory();
        //tạo list gender
        ObservableList<String> list = FXCollections
                .observableArrayList("Male", "Female","Other");
        //add giá trị cho comboboxGender
        comboboxGenDer.setItems(list);
        // vô hiệu hóa o nhập liệu trong datepicker
        dpDateOfBirth.getEditor().setDisable(true);
        dpStartingDate.getEditor().setDisable(true);
        
        //Load csdl Positions lên pos
        List<Positions> pos = utils.getPositions();
        this.comboboxPosition.getItems().addAll(pos);
        
        reloadTableView(utils.getAdmin(""));
        
        // vô hiệu hóa ngày hiện tại và tương lai
        dpDateOfBirth.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) > -1);
            }
        });
        
        //Vô hiệu hóa ngày quá khứ
        dpStartingDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        //hiện list các lỗi khi nhập
        txtFirstName.textProperty().addListener(ov ->{
           Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkFirstnameLastName(txtFirstName.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbFirstName, tooltip);
            }else if(-1 == kq){ // firstname k hop le
                tooltip.setText("FirstName is too long"
                    + "\nFirstName is about 15 characters\nFor example: Vĩ");
                utils.showError(lbFirstName, tooltip); 
            }else if(1 == kq){
                tooltip.setText("FirstName is not valid\nFirstName does not "
                    + "contain special characters and spaces\nFor example: Vĩ");
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
                tooltip.setText("LastName is too long,"
                        + "\nLastName is about 15 characters\nFor example: Võ");
                utils.showError(lbLastName, tooltip); 
            }else if(1 == kq){
                tooltip.setText("LastName is not valid\nLastName does not "
                    + "contain special characters and spaces\nFor example: Võ");
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
                tooltip.setText("Address is not valid\nAddress does not contain"
                    + " special characters\n: 799/22/37 Nguyễn Kiệm, p3, Gò Vấp");
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
                tooltip.setText("Email is not valid\nFor example: vtv98@gmail.com!");
                utils.showError(lbEmail, tooltip); 
            }else
                lbEmail.setVisible(false); // hợp lệ, ẩn lb
        });
        txtPassword.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkPassword(txtPassword.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPassword, tooltip);
            }else if(1 == kq){  
                tooltip.setText("Password is not valid"
                    + "\nPassword does not contain spaces, consists of "
                        + "about 15 characters");
                utils.showError(lbPassword, tooltip); 
            }else if(-1 == kq){
                tooltip.setText("Password consists of about 15 characters");
                utils.showError(lbPassword, tooltip); 
            }else
                lbPassword.setVisible(false); // hợp lệ, ẩn lb
        });
        confirm.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkPassword(confirm.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbConfirm, tooltip);
            }else if(0 == kq)
                lbConfirm.setVisible(false); // hợp lệ, ẩn lb
        });
        txtPhoneNumber.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkNumber(txtPhoneNumber.getText());
            if(0 == kq){ // chuỗi k hợp lệ, có ký tự
                
                utils.showError(lbPhoneNumber, tooltip);
            }else if(2 == kq){ //sđt phai băt dau bang 0
                tooltip.setText("Phone Number is not valid"
                        + "\n Must start with 0\nExample: 0857724561!");
                utils.showError(lbPhoneNumber, tooltip); 
            }else if(-1 == kq){//sđt phải gồm 10 số
                tooltip.setText("Phone Number includes 10 numbers"
                        + "\nExample: 0857724561!!");
                utils.showError(lbPhoneNumber, tooltip); 
            }else if(-2 == kq){ // chuỗi rỗng
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPhoneNumber, tooltip); 
            }else
                lbPhoneNumber.setVisible(false); // hợp lệ, ẩn lb
        });
        txtQualification.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkLongString(txtQualification.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbQualification, tooltip);
            }else if(1 == kq){
                tooltip.setText("Qualification is not valid"
                        + "\nQualification does not contain special characters"
                        + "\n: Vui tính, thật thà");
                utils.showError(lbQualification, tooltip); 
            }else//0
                lbQualification.setVisible(false); // hợp lệ, ẩn lb 
        });
        txtUsername.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkUserName(txtUsername.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbUsername, tooltip);
            }else if(1 == kq){ //  
                tooltip.setText("UserName is not valid"
                        + "\nUserName does not contain special characters and spaces,"
                        + "\nConsists of about 15 characters");
                utils.showError(lbUsername, tooltip); 
            }else if(-1 == kq){
                tooltip.setText("UserName consists of about 15 characters");
                utils.showError(lbUsername, tooltip); 
            }else
                lbUsername.setVisible(false); // hợp lệ, ẩn lb
        });
        
        comboboxPosition.valueProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkEmpty(comboboxPosition.getSelectionModel()
                    .getSelectedItem().toString());
            if(1 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPosition, tooltip);
            }else 
                lbPosition.setVisible(false); 
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
            int kq = utils.checkEmpty(dpDateOfBirth.getValue().toString());
            if(1 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbDateOfBirth, tooltip);
            }else 
                lbDateOfBirth.setVisible(false); 
        });
        dpStartingDate.valueProperty().addListener(ov -> {
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkEmpty(dpStartingDate.getValue().toString());
            if(1 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbstartingDate, tooltip);
            }else 
                lbstartingDate.setVisible(false); 
        });
        
        btnPre.setOnAction(ov ->{
            utils.changeSceneHandler("FXML_login.fxml", ov);
        });
        //this.comboboxPosition.getItems().addAll(utils.getPositions());
        this.tbAdmin.setRowFactory(param -> {
            row = new TableRow();
            
            row.setOnMouseClicked(et -> {
                //hiện btUpdate và delete khi click vao row tỏng tableview
                this.btUpdate.setVisible(true);
                this.btDelete.setVisible(true);
                
                // vô hiệu hóa btnCreate account, username và pasword
                btn.setDisable(true);
                txtUsername.setDisable(true);
                txtPassword.setDisable(true);
                confirm.setDisable(true);
                dpStartingDate.setDisable(true);
                // ẩn label liên quan
                lbConfirm.setVisible(false);
                lbPassword.setVisible(false);
                lbUsername.setVisible(false);
                
                // lấy giá trị của row dang chọn
                Admin ad = this.tbAdmin.getSelectionModel().getSelectedItem();
                // gán giá trị row cho từng textfield
                this.txtFirstName.setText(ad.getFirstName());
                this.txtLastName.setText(ad.getLastName());
                this.txtEmail.setText(ad.getEmail());
                this.txtAddress.setText(ad.getAddress());
                this.txtQualification.setText(ad.getQualification());
                this.txtPhoneNumber.setText(ad.getPhoneNumber());
                //this.dpDateOfBirth.set
                this.comboboxPosition.getSelectionModel().select(ad.getPositions());
                this.comboboxGenDer.getSelectionModel().select(ad.getGender());
            });
            return row;
        });
        // lắng nghe sự kiện khi search
        this.txtSearchBox.textProperty().addListener(et -> {
            List<Admin> admin = utils.getAdmin(this.txtSearchBox.getText());
            reloadTableView(admin);
        });
        try{
            this.loadAdmin();
        }
        catch(SQLException ex){
            System.err.println("ERROR:" + ex.getMessage());
        }
        // chuyển sang sence customer
        
    }    
    
     @FXML
    public void comboboxchanged (ActionEvent e){
        
    }
    
    
    public void saveAdmin(){
        
        if(!lbFirstName.isVisible() && !lbLastName.isVisible() && !lbGender
                .isVisible() && !lbPhoneNumber.isVisible() && !lbAddress
                .isVisible() && !lbEmail.isVisible() && !lbPassword.isVisible() 
                && !lbPosition.isVisible() && !lbQualification.isVisible()
                && !lbUsername.isVisible()&& !lbDateOfBirth.isVisible() && 
                !lbConfirm.isVisible() && !lbstartingDate.isVisible() && 
                !txtFirstName.getText().isEmpty() && !txtLastName.getText()
                .isEmpty() && !txtAddress.getText().isEmpty() && !txtEmail.
                getText().isEmpty() && !txtPassword.getText().isEmpty()
                && !txtPhoneNumber.getText().isEmpty() && !txtQualification.
                getText().isEmpty() && !txtUsername.getText().isEmpty() 
                && !confirm.getText().isEmpty()
                && comboboxGenDer.getSelectionModel().getSelectedItem()!=null
                && comboboxPosition.getSelectionModel().getSelectedItem()!=null
                && dpDateOfBirth.getValue() != null 
                && dpStartingDate.getValue() != null){
            if(utils.checkEmailHandler(txtEmail.getText(), 1) 
                    && utils.checkUserHandler(txtUsername.getText())){
                if(txtPassword.getText().equals(confirm.getText()))
                {
                    id = UUID.randomUUID().toString();
                    Session session = factory.openSession();
                    Transaction trans;
                    trans = session.beginTransaction();
                    //Băm mat khau. BCrypt.gensalt() xac dinh so vong  dao dong tu 4-30
                    String hash = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt(5));
                    Admin admin = new Admin();
                    admin.setAdminId(id);
                    admin.setAddress(txtAddress.getText());
                    String ns = dpDateOfBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    admin.setDateOfBirth(ns);
                    admin.setEmail(txtEmail.getText());
                    admin.setFirstName(txtFirstName.getText());
                    admin.setLastName(txtLastName.getText());
                    admin.setGender(comboboxGenDer.getValue());
                    admin.setPhoneNumber(txtPhoneNumber.getText());
                    admin.setPositions((Positions)comboboxPosition.
                            getSelectionModel().getSelectedItem());
                    admin.setQualification(txtQualification.getText());
                    String d = dpStartingDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    admin.setStartingDate(d);
                    Date p1 = new Date();
                    SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
                    String pd = sf1.format(p1);
                    admin.setOpeningDate(pd);
                    Users user = new Users();
                    user.setUserName(txtUsername.getText());
                    user.setPassword(hash);
                    admin.setUserAdmin(user);
                    user.setAdminUser(admin);
                    session.save(admin);
                    trans.commit();
                    session.close();
                    
                    utils.alertInformationHandler("Notification",
                            "Account successfully created!");
                    reloadTableView(utils.getAdmin(""));
                    //factory.close();
                }else{
                    Alert a = utils.alertErrorHandler("Notification", 
                        "Confirm password does not match, please enter again!");
                    a.showAndWait();
                }
            }else{
                if(!utils.checkEmailHandler(txtEmail.getText(), 1)){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Email is used! Please chosse another Email!");
                    utils.showError(lbEmail, tooltip);
                }
                if(!utils.checkUserHandler(txtUsername.getText())){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("UserName is used! Please chosse another UserName!");
                    utils.showError(lbUsername, tooltip);
                }
            }
                
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
            if(txtPassword.getText().isEmpty()){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPassword, tooltip);
            }
            if(confirm.getText().isEmpty()){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbConfirm, tooltip);
            }

            if(txtPhoneNumber.getText().isEmpty()){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPhoneNumber, tooltip); 
            }
            if(txtQualification.getText().isEmpty()){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbQualification, tooltip);
            }
            if(txtUsername.getText().isEmpty()){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbUsername, tooltip);
            }
            if(comboboxGenDer.getSelectionModel().getSelectedItem()==null){

                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbGender, tooltip);
            }
            if(comboboxPosition.getSelectionModel().getSelectedItem()==null){
               Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPosition, tooltip);
            }

            if(dpDateOfBirth.getValue() == null){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbDateOfBirth, tooltip);
            }
            if(dpStartingDate.getValue() == null){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbstartingDate, tooltip);
            }      

        }

        
    }
    private void loadAdmin() throws SQLException {
        List<Admin> admin = utils.getAdmin("");
        //List<Positions> pos = utilss.getPositions();

        //tao cot 
        TableColumn clFName = new TableColumn("First Name");
        TableColumn clLName = new TableColumn("Last Name");
        TableColumn clGender = new TableColumn("Gender");
        TableColumn clDOB = new TableColumn("Date of birth");
        TableColumn clPhone = new TableColumn("Phone Number");
        TableColumn clEmail = new TableColumn("Email");
        TableColumn clPosition = new TableColumn("Position");
        TableColumn clAddr = new TableColumn("Address");
        TableColumn clODate = new TableColumn("Opening Date");
        TableColumn clQua = new TableColumn("Qualification");
        TableColumn clSDate = new TableColumn("Staring Date");
       
        
        clFName.setCellValueFactory(new PropertyValueFactory("firstName"));
        clLName.setCellValueFactory(new PropertyValueFactory("lastName"));
        clDOB.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        clGender.setCellValueFactory(new PropertyValueFactory("gender"));
        clPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        clEmail.setCellValueFactory(new PropertyValueFactory("email"));
        clAddr.setCellValueFactory(new PropertyValueFactory("address"));
        clPosition.setCellValueFactory(new PropertyValueFactory("positions"));
        clODate.setCellValueFactory(new PropertyValueFactory("openingDate"));
        clQua.setCellValueFactory(new PropertyValueFactory("qualification"));
        clSDate.setCellValueFactory(new PropertyValueFactory("startingDate"));
        // do data vao moi cot
        this.tbAdmin.getColumns().addAll(clFName,clLName,clDOB,clPosition,clGender,clPhone,
                clEmail,clAddr,clODate,clQua,clSDate);
        //this.tbAdmin.getItems().addAll(admin);
       // bỏ vo hieu hoa 
        btn.setDisable(false);
        txtUsername.setDisable(false);
        txtPassword.setDisable(false);
        confirm.setDisable(false);
        dpStartingDate.setDisable(false);
     }
    private void reloadTableView(List<Admin> admin) {
        this.tbAdmin.getItems().clear();
        this.tbAdmin.getItems().addAll(admin);
        txtAddress.clear();
        txtEmail.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtPassword.clear();
        txtPhoneNumber.clear();
        txtQualification.clear();
        txtUsername.clear();
        confirm.clear();
        comboboxGenDer.setValue(null);
        comboboxPosition.setValue(null);
        //dpDateOfBirth.setValue(null);
        //dpStartingDate.setValue(null);
    }
    public void updateAdmin(ActionEvent event) {
        if(this.tbAdmin.getSelectionModel().getSelectedItem() != null){
            Admin q = this.tbAdmin.getSelectionModel().getSelectedItem();
            if(!lbFirstName.isVisible() && !lbLastName.isVisible()
                && !lbGender.isVisible() && !lbPhoneNumber.isVisible() 
                && !lbAddress.isVisible() && !lbEmail.isVisible()
                && !lbPosition.isVisible() && !lbQualification.isVisible()){
                q.setFirstName(this.txtFirstName.getText());
                q.setLastName(this.txtLastName.getText());
                q.setPhoneNumber(this.txtPhoneNumber.getText());
                q.setEmail(this.txtEmail.getText());
                q.setQualification(this.txtQualification.getText());
                q.setAddress(this.txtAddress.getText());

                q.setPositions(this.comboboxPosition.getSelectionModel().getSelectedItem());
                q.setGender(this.comboboxGenDer.getSelectionModel().getSelectedItem());
                String ns = q.getDateOfBirth();
                if(dpDateOfBirth.getValue() != null){
                    String d = dpStartingDate.getValue().
                            format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    q.setDateOfBirth(d);
                }else
                    q.setDateOfBirth(ns);

                utils.addOrUpdateAdmin(q);
                utils.alertInformationHandler("Notification",
                                "Account successfully created!");
                reloadTableView(utils.getAdmin(""));
                // hiện các label liên quan và bỏ vô hiệu hóa
                btn.setDisable(false);
                txtUsername.setDisable(false);
                txtPassword.setDisable(false);
                dpStartingDate.setDisable(false);
                confirm.setDisable(false);
                lbConfirm.setVisible(true);
                lbPassword.setVisible(true);
                lbUsername.setVisible(true);
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
                if(txtQualification.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbQualification, tooltip);
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
                if(comboboxPosition.getSelectionModel().getSelectedItem()==null){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbPosition, tooltip);
                }
                if(dpDateOfBirth.getValue() == null){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbDateOfBirth, tooltip);
                }
                if(dpStartingDate.getValue() == null){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbstartingDate, tooltip);
                }
            }
                
        }else{
            Alert a = utils.alertErrorHandler("Notification", "You have not selected updates");
            a.showAndWait();
        } 
       
    }

    public void deleteAdmin(ActionEvent event){
        if(this.tbAdmin.getSelectionModel().getSelectedItem() != null){
            Admin ad = this.tbAdmin.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete this row ?");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    //utils.deleteUser(ad.getAdminId());
                    utils.deleteAdmin(ad);
                    
                    utils.alertInformationHandler("Notification",
                                "Successfully Deleted");
                    reloadTableView(utils.getAdmin(""));
                    btn.setVisible(true);
                    txtUsername.setDisable(false);
                    txtPassword.setDisable(false);
                    confirm.setDisable(false);
                    dpStartingDate.setDisable(false);
                    lbConfirm.setVisible(true);
                    lbPassword.setVisible(true);
                    lbUsername.setVisible(true);

                }
            });
        }else{
            Alert a = utils.alertErrorHandler("Notification", "You have not selected the"
                    + " deletion information!");
            a.showAndWait();
        }
    }
    
    public boolean checkUserHandler(){
        List<Users> l = utils.getUser(txtUsername.getText());
        boolean kq = true; // mặc định tên k trùng
        if(l != null){
            Tooltip tooltip = utils.tooltipHandler();
            tooltip.setText("UserName is used! Please chosse another UserName!");
            utils.showError(lbUsername, tooltip);
            kq = false; // tên trùng false
        }
        return kq;
    }
    
    public boolean checkEmailHandler(){
        List<Admin> l = utils.getEmailAdmin(txtEmail.getText());
        boolean kq = true; // mặc định email k trùng
        if(l != null){
            Tooltip tooltip = utils.tooltipHandler();
            tooltip.setText("Email is used! Please chosse another Email!");
            utils.showError(lbEmail, tooltip);
            kq = false; // tên trùng false
        }        
        return kq;
    }
    
    public void adminKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            saveAdmin();
        }
    } 
}