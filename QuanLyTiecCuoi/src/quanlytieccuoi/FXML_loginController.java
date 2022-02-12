/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import quanlytieccuoi.models.Admin;
import quanlytieccuoi.models.Users;

/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class FXML_loginController implements Initializable {   
    @FXML
    private TextField userName;
    @FXML
    private Label lbUserName;
    @FXML
    private Label lbPassword;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnExit;
    @FXML
    private PasswordField password; 
    

    SessionFactory factory;
    UtilsDao utils = new UtilsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.factory = HibernateUtil.getSessionFactory();
        // thiet lap nut previous home
        ImageView imageView = new ImageView(new Image
        ("quanlytieccuoi/Images/previous.png"));
        imageView.setFitHeight(32);
        btnExit.setGraphic(imageView);
        userName.textProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkUserName(userName.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbUserName, tooltip);
            }else if(-1 == kq){
                tooltip.setText("The string is too long, only enter about "
                        + "15 charaters!");
                utils.showError(lbPassword, tooltip);
            }else if(0 == kq)//0
                lbUserName.setVisible(false); // hợp lệ, ẩn lb  
        });
        
        password.textProperty().addListener(i ->{
            Tooltip tooltip = utils.tooltipHandler();
            int kq = utils.checkPassword(password.getText());
            if(2 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbPassword, tooltip);
            }else if(-1 == kq){
                tooltip.setText("The string is too long, only enter about "
                        + "15 charaters!");
                utils.showError(lbPassword, tooltip);
            }else if(0 == kq)//0
                lbPassword.setVisible(false); // hợp lệ, ẩn lb  
            
        });
        btnCreate.setOnAction(ov ->{
            //Admin a = getAdminUser(userName.getText());
            utils.changeSceneHandler("FXML_createadmin.fxml", ov);
        });
        btnExit.setOnAction(ov ->{
            //Admin a = getAdminUser(userName.getText());
            utils.changeSceneHandler("FXML_home.fxml", ov);
        });
        
//        btnAccept.setOnAction(ov ->{
//            acceptHandler(ov);
//        });
        
    } 
    
    public boolean getAdminUser(String str){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        List<Users> q = session.createQuery("from Users u where u.userName = :n")
                    .setParameter("n", str).list();
        String idUser = "";
        for(Users u : q){
            idUser = u.getId();
        }
        boolean kq = false; // mặc định k phải là manager
        List <Admin> ad = utils.getNameAdmin(idUser);
        for(Admin a : ad)
            if(a.getPositions().getpName().equalsIgnoreCase("Manager"))
                kq = true;
        return kq;
    }
    
    public void acceptHandler(ActionEvent event){
        if(!lbUserName.isVisible() && !lbPassword.isVisible()) 
        {
            int kq = utils.checkLog(userName.getText(), password.getText());
            if(0 == kq){
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Logged in successfully");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        boolean a = getAdminUser(userName.getText());
                        FXMLLoader loader = utils.changeSceneHandlers("FXML_home.fxml", event);
                        FXML_homeController controller = loader.getController();
                        if(a){
                            controller.setHome(true);
                        }else{
                            controller.setHome(false);
                        }
                        
                    }
                });
            }else if(1 == kq){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Password incorrect, please re-enter!");
                utils.showError(lbPassword, tooltip);
            }else if(-1 == kq){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("UseName incorrect, please re-enter!");
                utils.showError(lbUserName, tooltip);
                
                Tooltip tooltip2 = utils.tooltipHandler();
                tooltip2.setText("Password incorrect, please re-enter!");
                utils.showError(lbPassword, tooltip2);
            }else{
                if(userName.getText().isEmpty()){ 
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbUserName, tooltip);
                }
                if(password.getText().isEmpty()){
                    Tooltip tooltip = utils.tooltipHandler();
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbPassword, tooltip);
                }
            }
        }
    }
    
    
    public void formKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
        }
    }
}
