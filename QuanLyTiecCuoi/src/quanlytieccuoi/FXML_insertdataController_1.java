/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.io.File;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.models.MenuGroup;

import quanlytieccuoi.models.FoodDrinkService;


/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class FXML_insertdataController_1 implements Initializable {
    
    private String id;
    File file;
    SessionFactory factory;  
    @FXML
    private ComboBox<MenuGroup> comboboxMenuGroup;
    @FXML
    private TableView<FoodDrinkService> tbInfor;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtUnit;
    @FXML
    private TextField txtDes;
    @FXML
    private TextField txtPri;
    @FXML
    private Button btnChooseImage;
    @FXML
    private Label lbName;
    @FXML
    private Label lbUnit;
    @FXML
    private Label lbDes;
    @FXML
    private Label lbPri;
    @FXML
    private Label lbChooseImage;
    @FXML
    private Label lbMenuGroup;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField txtSearchBox;
    
    @FXML
    private Button btnDelete;
    
//    @FXML
//    private Button btnInsert;
    
    @FXML
    private Button btnUpdate;
    @FXML
    private StackPane p;
    
    @FXML
    private Button btnback;
    
    @FXML
    private Text txtImage;
    TableRow row;
    UtilsDao utils = new UtilsDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.factory = HibernateUtil.getSessionFactory();
        List<MenuGroup> meGr = utils.getMenuGroup();
        this.btnUpdate.setDisable(false);
        this.btnDelete.setDisable(true);
        this.btnUpdate.setDisable(true);
        this.comboboxMenuGroup.getItems().addAll(meGr);
        
        
        txtName.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkName(txtName.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbName, tooltip);
            }else if(1 == kq){
                tooltip.setText("Name is not valid\nFor example: hamburger");
                utils.showError(lbName, tooltip); 
            }else if(-1 == kq){
                tooltip.setText("Name is too long\nName is about 50 characters\n"
                        + "For example: Hamburger");
                utils.showError(lbName, tooltip);
            }else//0
                lbName.setVisible(false); // hợp lệ, ẩn lb  
        });
        txtUnit.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkFirstnameLastName(txtUnit.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbUnit, tooltip);
            }else if(-1 == kq){ // firstname k hop le
                tooltip.setText("Unit is too long,\nUnit is about 15 characters"
                        + "\nExample: bottle");
                utils.showError(lbUnit, tooltip); 
            }else if(1 == kq){
                tooltip.setText("Unit is not valid\nUnit does not"
                        + " contain special characters and spaces\nExample: bottle");
                utils.showError(lbUnit, tooltip); 
            }else//0
                lbUnit.setVisible(false); // hợp lệ, ẩn lb  
        });
        txtDes.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkName(txtDes.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbDes, tooltip);
            }else if(1 == kq){
                tooltip.setText("Description is not valid\nExample: Carbonated water....");
                utils.showError(lbDes, tooltip); 
            }else if(-1 == kq){
                tooltip.setText("Description is to long\n"
                        + "Description is about 100 characters\n"
                        + "Example: Carbonated water....");
                utils.showError(lbDes, tooltip);
            }else//0
                lbDes.setVisible(false); // hợp lệ, ẩn lb  
        });
        txtPri.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkPrice(txtPri.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPri, tooltip);
            }else if(1 == kq){
                tooltip.setText("Price is not valid\nExample: 8000");
                utils.showError(lbPri, tooltip); 
            }else//0
                lbPri.setVisible(false); // hợp lệ, ẩn lb  
        });
        
        comboboxMenuGroup.valueProperty().addListener(ov ->{
           Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkEmpty(comboboxMenuGroup.getSelectionModel()
                    .getSelectedItem().toString());
            if(1 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbMenuGroup, tooltip);
            }else 
                lbMenuGroup.setVisible(false); 
        });
        txtImage.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            if(txtImage.getText().isEmpty()){
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbChooseImage, tooltip);
            }else
                lbChooseImage.setVisible(false);
                
        });
         this.tbInfor.setRowFactory(param -> {
            row = new TableRow();
            
            row.setOnMouseClicked(et -> {
                //btn.setDisable(true);
                
                FoodDrinkService fds = this.tbInfor.getSelectionModel().getSelectedItem();
                
                this.txtName.setText(fds.getName());
                this.txtUnit.setText(fds.getUnit());
                this.txtDes.setText(fds.getDescri());
                this.txtPri.setText(fds.getPrice());
                this.txtImage.setText(fds.getImage());
                String i = "quanlytieccuoi/Images/" + fds.getImage();
                Image image = new Image(i);
                imageView.setImage(image);
                this.btnUpdate.setDisable(false);
                this.btnDelete.setDisable(false);
                
                //this.dpDateOfBirth.set
                this.comboboxMenuGroup.getSelectionModel().select(fds.getIDType());
                
            });
            
            return row;
        });
        btnChooseImage.setOnAction(e ->{
            file = utils.chooseImage(p, file, txtImage, imageView, lbChooseImage);
        });
        this.txtSearchBox.textProperty().addListener(et -> {
            List<FoodDrinkService> fds = utils.getFds(this.txtSearchBox.getText());
            reloadTableView(fds);
        });
        
        try{
            this.loadInfor();
        }
        catch(SQLException ex){
            System.err.println("ERROR:" + ex.getMessage());
        }
        btnback.setOnAction(ov ->{
            utils.changeSceneHandlers("FXML_insertdata.fxml", ov);
        });
    }    
    
     @FXML
    public void comboboxchanged (ActionEvent e){
    }

    
    public void FoodKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            
        }
    }
    
    public boolean checkNameHandler(){
        List<FoodDrinkService> l = utils.getFoodDrinkService(txtName.getText());
        boolean kq = true; // mặc định tên k trùng
        if(l != null){
            Tooltip tooltip = utils.tooltipHandler();
            tooltip.setText("Name is used! Please chosse another Name!");
            utils.showError(lbName, tooltip);
            kq = false; // tên trùng false
        }
        return kq;
    }
    private void loadInfor() throws SQLException {
        List<FoodDrinkService> fds = utils.getFds("");
        

        
        TableColumn clName = new TableColumn("Name");
        TableColumn clImage = new TableColumn("Image");
        TableColumn clUnit = new TableColumn("Unit");
        TableColumn clPrice = new TableColumn("Price");
        TableColumn clDes = new TableColumn("Description");
        TableColumn clType = new TableColumn("Type");
        
        
        clName.setCellValueFactory(new PropertyValueFactory("name"));
        clImage.setCellValueFactory(new PropertyValueFactory("image"));
        clUnit.setCellValueFactory(new PropertyValueFactory("unit"));
        clPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clType.setCellValueFactory(new PropertyValueFactory("IDType"));
        clDes.setCellValueFactory(new PropertyValueFactory("descri"));
        
        this.tbInfor.getColumns().addAll(clName,clType,clUnit,clPrice,clDes,clImage);
        
        
        this.tbInfor.getItems().addAll(fds);
    }
    
    private void reloadTableView(List<FoodDrinkService> cus) {

        this.tbInfor.getItems().clear();
        this.tbInfor.getItems().addAll(cus);
        
    }
    public void deleteData(ActionEvent event){
        if(this.tbInfor.getSelectionModel().getSelectedItem() != null){
            FoodDrinkService f = this.tbInfor.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete this row ?");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    utils.deleteFoodDrinkService(f.getID());
                    utils.alertInformationHandler("Notification",
                                "Successfully Deleted");
                    reloadTableView(utils.getFoodDrinkService(""));
                    reloadTableView(utils.getFoodDrinkService(""));
                    btnDelete.setDisable(true);
                    //btnInsert.setDisable(true);
                    btnUpdate.setDisable(true);
                }
            });
        }else{
            Alert a = utils.alertErrorHandler("Notification", "You have not selected the"
                    + " deletion information!");
            a.showAndWait();
        }
    }
    public void updateData(ActionEvent event) {
        if(this.tbInfor.getSelectionModel().getSelectedItem() != null){
            FoodDrinkService fds = this.tbInfor.getSelectionModel().getSelectedItem();
//             if(!lbName.isVisible() && !lbUnit.isVisible() && !lbPri.isVisible()
            if(!lbName.isVisible() && !lbUnit.isVisible() && !lbPri.isVisible()
                &&!lbDes.isVisible() && !lbMenuGroup.isVisible() && !lbChooseImage.isVisible()
                && comboboxMenuGroup.getSelectionModel().getSelectedItem()!=null
                && !txtDes.getText().isEmpty() && !txtImage.getText().isEmpty()
                && !txtName.getText().isEmpty() && !txtPri.getText().isEmpty()
                && !txtUnit.getText().isEmpty()){ // kiểm ra các label đều ẩn thì mới cho lưu   
                
                //if(checkNameHandler()){
                    //id = UUID.randomUUID().toString();
                    Session session = factory.openSession();
                    Transaction trans;
                    trans = session.beginTransaction();
                    
                    //MenuGroup type = this.comboboxMenuGroup.getSelectionModel().getSelectedItem();
                    fds.setName(this.txtName.getText());
                    fds.setDescri(this.txtDes.getText());
                    fds.setUnit(this.txtUnit.getText());
                    fds.setImage(this.txtImage.getText());
                    fds.setPrice(this.txtPri.getText());

                    fds.setIDType(this.comboboxMenuGroup.getSelectionModel().getSelectedItem());
                    //utils.copyFile(file);
                    utils.addOrUpdateData(fds);
   
                     // lưu thành công mới copy file ảnh vào project
                    trans.commit();
                    utils.alertInformationHandler("Notification",
                            "Update data successfully");
                    reloadTableView(utils.getFoodDrinkService(""));
                    // xóa hết các dữ liệu đã nhập
//                    txtName.clear();
//                    txtDes.clear();
//                    txtPri.clear();
//                    txtUnit.clear();
//                    comboboxMenuGroup.valueProperty().setValue(null);
//                    imageView.setImage(null);
//                    txtImage.setText(null);
//                    btnDelete.setDisable(false);
//                    //btnInsert.setDisable(false);
//                    btnUpdate.setDisable(false);
//                    
                 
            }else{
                if(txtName.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbName, tooltip);
                }
                if(txtUnit.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbUnit, tooltip);
                }
                if(txtDes.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbDes, tooltip);
                }
                if(txtPri.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbPri, tooltip);
                }
                if(comboboxMenuGroup.getSelectionModel().getSelectedItem()==null){

                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbMenuGroup, tooltip);
                }
                if(txtImage.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbChooseImage, tooltip);
                }
            }
            
        }
        else{
           Alert a = utils.alertErrorHandler("Notification", "You have not selected to update");
           a.showAndWait();
        }
        
    }
}
