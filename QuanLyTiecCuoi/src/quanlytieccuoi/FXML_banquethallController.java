/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.models.BanquetHall;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML_banquethallController implements Initializable {

    /**
     * Initializes the controller class.
     */
    File file;
    private String id;
    SessionFactory factory; 
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAcreage;
    @FXML
    private TextField txtMaxPeople;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnChooseImage;
    @FXML
    private Label lbName;
    @FXML
    private Label lbAcreage;
    @FXML
    private Label lbMaxPeople;
    @FXML
    private Label lbPrice;
    @FXML
    private Button btnBack;
     @FXML
    private Button btnEdit;
    @FXML
    private Text txtImage;
    @FXML
    private Label lbChooseImage;
  
    @FXML
    private ImageView imageView;
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
        btnChooseImage.setOnAction(e ->{
            utils.chooseImage(p, file, txtImage, imageView, lbChooseImage);
        });
        btnBack.setOnAction(ov ->{
            FXMLLoader loader = utils.changeSceneHandlers("FXML_home.fxml", ov);
            FXML_homeController controller = loader.getController();
            controller.setHome(true);
        });
        btnEdit.setOnAction(ov ->{
           utils.changeSceneHandlers("FXML_banquethall.fxml", ov);
        });
        
    }  
    
    public void saveBanquetHall(){
        
        if(!lbName.isVisible() && !lbAcreage.isVisible() && !lbMaxPeople.isVisible()
            &&!lbPrice.isVisible() && !txtAcreage
                    .getText().isEmpty() && !txtAcreage.getText().isEmpty() 
                && !txtMaxPeople.getText().isEmpty()
                && !txtPrice.getText().isEmpty() && !lbChooseImage.isVisible() 
                && !txtImage.getText().isEmpty()){ // kiểm ra các label đều ẩn thì mới cho lưu   
            if(checkNameBanquetHallHandler()){// true là không trùng tên, cho phép lưu
                id = UUID.randomUUID().toString();
                Session session = factory.openSession();
                Transaction trans;
                trans = session.beginTransaction();

                BanquetHall fsd = new BanquetHall(id,txtName.getText()
                        ,txtAcreage.getText(),txtMaxPeople.getText(),txtPrice.getText(),
                         txtImage.getText());
                session.save(fsd);
                utils.copyFile(file);
                //copyFileHandler(); // lưu thành công mới copy file ảnh vào project
                trans.commit();
                utils.alertInformationHandler("Notification",
                            "Data added successfully");
                // xóa hết các dữ liệu đã nhập
                txtName.clear();
                txtAcreage.clear();
                txtMaxPeople.clear();
                txtPrice.clear();
                txtImage.setText(null);
                imageView.setImage(null);
            }else{
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Name is used! Please chosse another Name!");
                utils.showError(lbName, tooltip);
            }
        
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
        
    }
    
    public void BanquetHallKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            saveBanquetHall();
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
}
