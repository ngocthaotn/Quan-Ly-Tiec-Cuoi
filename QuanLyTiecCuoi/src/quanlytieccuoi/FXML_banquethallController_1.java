/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javax.persistence.Column;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.FoodDrinkService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML_banquethallController_1 implements Initializable {

    /**
     * Initializes the controller class.
     */
    File file;
    private String id;
    SessionFactory factory;
    
    @FXML
    private TableView<BanquetHall> tbHall;
    @FXML
    
    private TextField txtName;
    @FXML
    private TextField txtAcreage;
    @FXML
    private TextField txtMaxPeople;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnChooseImage1;
    @FXML
    private Label lbName;
    @FXML
    private Label lbAcreage;
    @FXML
    private Label lbMaxPeople;
    @FXML
    private Label lbPrice;
    
    @FXML
    private Text txtImage;
    @FXML
    private Label lbChooseImage;
    @FXML
    private TextField txtSearchBox;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btDelete;
    @FXML
    private Button btBack;
    @FXML
    TableRow row;
    @FXML
    
    private StackPane p;
    UtilsDao utils = new UtilsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.factory = HibernateUtil.getSessionFactory();
        txtName.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkName(txtName.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbName, tooltip);
            }else if(1 == kq){
                tooltip.setText("Name is not valid\nExample: Banquet Hall A");
                utils.showError(lbName, tooltip); 
            }else if(-1 == kq){
                tooltip.setText("Name is too long\n"
                        + "Name is about 50 characters\nExmple: Banquet Hall A");
                utils.showError(lbName, tooltip);
            }else//0
                lbName.setVisible(false); // hợp lệ, ẩn lb  
        });
        txtAcreage.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkPrice(txtAcreage.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbAcreage, tooltip);
            }else if(1 == kq){
                tooltip.setText("Acreage is not valid\nExample: 800");
                utils.showError(lbAcreage, tooltip); 
            }else//0
                lbAcreage.setVisible(false); // hợp lệ, ẩn lb  
        });
        
        txtPrice.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkPrice(txtPrice.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPrice, tooltip);
            }else if(1 == kq){
                tooltip.setText("Price invalid\nExample: 8000");
                utils.showError(lbPrice, tooltip); 
            }else//0
                lbPrice.setVisible(false); // hợp lệ, ẩn lb  
        });
        
        txtMaxPeople.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkPrice(txtMaxPeople.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbMaxPeople, tooltip);
            }else if(1 == kq){
                tooltip.setText("Max people  invalid\nExample: 300");
                utils.showError(lbMaxPeople, tooltip); 
            }else//0
                lbMaxPeople.setVisible(false); // hợp lệ, ẩn lb  
        });
        txtImage.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            if(txtImage.getText().isEmpty()){
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbChooseImage, tooltip);
            }else
                lbChooseImage.setVisible(false);
                
        });
        
//        btnChooseImage.setOnAction(e ->{
//            utils.chooseImage(p, file, txtImage, imageView, lbChooseImage);
//        });
        this.tbHall.setRowFactory(param -> {
            row = new TableRow();
            
            row.setOnMouseClicked(et -> {
                
                // lấy giá trị của row dang chọn
                BanquetHall hall = this.tbHall.getSelectionModel().getSelectedItem();
                
                // gán giá trị row cho từng textfield
                this.txtName.setText(hall.getName());
                this.txtAcreage.setText(hall.getAcreage());
                this.txtPrice.setText(hall.getPrice());
                this.txtMaxPeople.setText(hall.getMaxPeople());
                this.txtImage.setText(hall.getImage());
                String i = "quanlytieccuoi/Images/" + hall.getImage();
                Image image = new Image(i);
                imageView.setImage(image);
            });
            return row;
        });
        btnChooseImage1.setOnAction(e->{
            utils.chooseImage(p, file, txtImage, imageView, lbChooseImage);
        });
        this.txtSearchBox.textProperty().addListener(et -> {
            List<BanquetHall> hall = utils.getBanquetHall(this.txtSearchBox.getText());
            reloadTableView(hall);
        });
        try{
            this.loadHall();
        }
        catch(SQLException ex){
            System.err.println("ERROR:" + ex.getMessage());
        }
        btBack.setOnAction(ov ->{
           utils.changeSceneHandlers("FXML_banquethall.fxml", ov);
        });
    }  
    
    public void updateBanquetHall(){
        if(this.tbHall.getSelectionModel().getSelectedItem() != null){
            BanquetHall hall = this.tbHall.getSelectionModel().getSelectedItem();
            if(!lbName.isVisible() && !lbAcreage.isVisible() && !lbMaxPeople.isVisible()
                &&!lbPrice.isVisible() && !txtAcreage
                        .getText().isEmpty() && !txtAcreage.getText().isEmpty() 
                    && !txtMaxPeople.getText().isEmpty()
                    && !txtPrice.getText().isEmpty() && !lbChooseImage.isVisible() 
                    && !txtImage.getText().isEmpty()){ // kiểm ra các label đều ẩn thì mới cho lưu   

                    Session session = factory.openSession();
                    Transaction trans;
                    
                    trans = session.beginTransaction();
                    hall.setName(this.txtName.getText());
                    hall.setAcreage(this.txtAcreage.getText());
                    hall.setMaxPeople(this.txtMaxPeople.getText());
                    hall.setImage(this.txtImage.getText());
                    hall.setPrice(this.txtPrice.getText());
                 
                   // utils.copyFile(file);
                    utils.AddOrUpdateHall(hall);
                    utils.alertInformationHandler("Notification",
                                "successfully updateed!");
                    reloadTableView(utils.getBanquetHall(""));
                    reloadTableView(utils.getBanquetHall(""));

                    // bỏ vô hiệu hóa
                    btDelete.setDisable(false);  
                    btUpdate.setDisable(false);
                    //utils.addOrUpdateData();

                         // lưu thành công mới copy file ảnh vào project
                        //trans.commit();
                        utils.alertInformationHandler("Notification",
                                "Update data successfully");
                        //reloadTableView(utils.getFoodDrinkService(""));
                   //utils.copyFile(file);
                    //copyFileHandler(); // lưu thành công mới copy file ảnh vào project
////                    trans.commit();
////                    utils.alertInformationHandler("Notification",
////                                "Data added successfully");
////                

            }else{
                if(txtName.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbName, tooltip);
                }
                if(txtAcreage.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbAcreage, tooltip);
                }
                if(txtMaxPeople.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbMaxPeople, tooltip);
                }
                if(txtPrice.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbPrice, tooltip);
                }
                if(txtImage.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbChooseImage, tooltip);
                 }
            }
        }else{
            Alert a = utils.alertErrorHandler("Notification", "You have not selected updates");
            a.showAndWait();
        }
    }
    
    public void BanquetHallKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            //saveBanquetHall();
        }
    }
    
    public boolean checkNameBanquetHallHandler(){
        List<BanquetHall> l = utils.getBanquetHall(txtName.getText());
        boolean kq = true; // mặc định tên k trùng
        if(l != null){
            Tooltip tooltip = utils.tooltipHandler();
            tooltip.setText("Name is used! Please chosse another Name!");
            utils.showError(lbName, tooltip);
            kq = false; // tên trùng false
        }
        return kq;
    }
    private void loadHall() throws SQLException {
        List<BanquetHall> hall = utils.getBanquetHall("");
        

        
        TableColumn clName = new TableColumn("Name");
        TableColumn clAcreage = new TableColumn("Acreage");
        TableColumn clPeople = new TableColumn("Max People");
        TableColumn clPrice = new TableColumn("Price");
        TableColumn clImg = new TableColumn("Image");
        
        
        clName.setCellValueFactory(new PropertyValueFactory("name"));
        clAcreage.setCellValueFactory(new PropertyValueFactory("acreage"));
        clPeople.setCellValueFactory(new PropertyValueFactory("maxPeople"));
        clPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clImg.setCellValueFactory(new PropertyValueFactory("image"));
        
        this.tbHall.getColumns().addAll(clName,clAcreage,clPeople,clPrice, clImg);
        this.tbHall.getItems().addAll(hall);
    }
    private void reloadTableView(List<BanquetHall> h) {
        this.tbHall.getItems().clear();
        this.tbHall.getItems().addAll(h);
    }
    
     public void deleteData(ActionEvent event){
        if(this.tbHall.getSelectionModel().getSelectedItem() != null){
            BanquetHall bq = this.tbHall.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete this row ?");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    utils.deleteFoodDrinkService(bq.getId());
                    utils.alertInformationHandler("Notification",
                                "Successfully Deleted");
                    reloadTableView(utils.getBanquetHall(""));
                    reloadTableView(utils.getBanquetHall(""));
                    btDelete.setDisable(true);
                    //btnInsert.setDisable(true);
                    btUpdate.setDisable(true);
                }
            });
        }else{
            Alert a = utils.alertErrorHandler("Notification", "You have not selected the"
                    + " deletion information!");
            a.showAndWait();
        }
    }
}
