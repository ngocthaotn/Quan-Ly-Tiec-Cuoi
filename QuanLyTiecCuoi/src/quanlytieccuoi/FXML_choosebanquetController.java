/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import org.hibernate.SessionFactory;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.Customers;
import quanlytieccuoi.models.DateOfBooking;

/**
 *
 * @author PC
 */
public class FXML_choosebanquetController implements Initializable {
    String id;
    Customers cus;
    @FXML
    private RadioButton rdoTheAfternoon;
    @FXML
    private RadioButton rdoTheEvening;
    @FXML
    private ComboBox<BanquetHall> chooseBanquetHall;
    @FXML
    private DatePicker chooseDate;
    @FXML
    private Label lbChooseBanquetHall;
    @FXML
    private Label lbPrice;
    @FXML
    private Label lbChooseDate;
    @FXML
    private Label lbName;
    @FXML
    private Label lbPhoneNumber;
    @FXML
    private Button btnBack;
    SessionFactory factory;  
    UtilsDao utils = new UtilsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.factory = HibernateUtil.getSessionFactory();
        utils.backHandler(btnBack);
        // add items combobox
        List<BanquetHall> ban = utils.getBanquetHall();
        this.chooseBanquetHall.getItems().addAll(ban);
        editDatePicker(chooseDate);
        chooseDate.valueProperty().addListener(ov -> {
            Tooltip tooltip = utils.tooltipHandler();
            if(chooseDate.getValue() != null){
                int kq = utils.checkEmpty(chooseDate.getValue().toString());
                if(1 == kq){ // rỗng  
                    tooltip.setText("Required Information, Please enter!");
                    utils.showError(lbChooseDate, tooltip);
                }else 
                    lbChooseDate.setVisible(false); 
            }
            
        });
        
        rdoTheAfternoon.setSelected(true);
        chooseBanquetHall.valueProperty().addListener(ov ->{
            Tooltip tooltip = utils.tooltipHandler();
            //if(chooseBanquetHall.)
            int kq = utils.checkEmpty(chooseBanquetHall.getSelectionModel()
                    .getSelectedItem().toString());
            if(1 == kq){ // rỗng  
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbChooseBanquetHall, tooltip);
                //chooseDate.setDisable(true);
                lbPrice.setText("");
            }else {
                lbChooseBanquetHall.setVisible(false);  // ẩn lb
                chooseDate.setDisable(false); // bỏ vô hiệu hóa
                String priceHall = chooseBanquetHall.getSelectionModel()
                        .getSelectedItem().getPrice();
                lbPrice.setText(String.format("%,d", Integer.parseInt(priceHall)));
            } 
        });
        
        btnBack.setOnAction(ov ->{
            FXMLLoader loader = utils.changeSceneHandlers("FXML_choosecustomer.fxml", ov);
            FXML_choosecustomerController controller = loader.getController();
            controller.setChooseCustomer(cus);
        });
    }
    
    public void editDatePicker(DatePicker d){
        // k cho nhập trên textfield của datepicker
        d.getEditor().setDisable(true);
        //vô hiệu hóa ngày quá khứ
        d.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
                if(date.compareTo(today) < 0)
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                
                //vô hiệu hóa ngày đã đặt và tô màu cho nó
                List<String[]> l = dateHandler();
                if(l != null){
                    for(String[] str : l){ // yyyy-mm-dd
                        if(date.compareTo(LocalDate.of(Integer.parseInt(str[0]),
                                Integer.parseInt(str[1]), Integer.parseInt(str[2]))) == 0){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                        }
                    }
                }     
            }
        });    
    }
    
    public String[] editDate(String n){  // yyyy-mm-dd
        String[] kq = n.split("[-]");
        return kq;
    }
    
    public List<String[]> dateHandler(){
        BanquetHall hall = chooseBanquetHall.getSelectionModel().getSelectedItem();
        List<DateOfBooking> l = utils.getDateOfBooking(hall);
        if(l != null){
            ArrayList<String[]> str = new ArrayList<>();
            for(DateOfBooking d : l){
                String[] str1 = editDate(d.getDateAction()); // tách chuỗi;
                str.add(str1); //add vào list, mỗi phần tử trong list là String[]
            } 
            return str;
        }
        return null;   
    }
    
    public void buttonNextHandler(ActionEvent event){
        if(!lbChooseBanquetHall.isVisible()
            && chooseBanquetHall.getSelectionModel().getSelectedItem() != null){
            if(!chooseDate.isDisable() && chooseDate.getValue() != null){
                FXMLLoader loader = utils.changeSceneHandlers("FXML_choosemenu.fxml", event);

                FXML_choosemenuController controller = loader.getController();
                BanquetHall hall = chooseBanquetHall.getSelectionModel()
                        .getSelectedItem();
                
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                String str = f.format(Date.from(chooseDate.getValue()
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()));
                // qui ước nếu 1 ca trưa, 2 ca tối
                int i = 1; // mặc định là ca trưa
                if(rdoTheEvening.isSelected())
                    i = 2; // ca tối
                controller.setChooseMenu(hall, str, id, cus, i);
            }else{
                if(chooseDate.getValue() == null){
                Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbChooseDate, tooltip);
            }

            }
        }else{
            if(chooseBanquetHall.getSelectionModel().getSelectedItem()==null){
               Tooltip tooltip = utils.tooltipHandler();
                tooltip.setText("Required Information, Please enter!");
                utils.showError(lbChooseBanquetHall, tooltip);
            }
        }
    } 
    
    public void setChooseBanquetHall(String n, String pr, String d, Customers c,
            String idOrder, int timeOfDay){
        for(int i = 0; i < chooseBanquetHall.getItems().size(); i++){
            if(chooseBanquetHall.getItems().get(i).getName().equals(n)){
                chooseBanquetHall.getSelectionModel().select(i);
                break;
            }
        }
        String[] str = editDate(d);
        chooseDate.setValue(LocalDate.of(Integer.parseInt(str[0]),
                Integer.parseInt(str[1]),Integer.parseInt(str[2])));
        cus = c;
        this.id = idOrder;
        lbName.setText(cus.getFirstName() + " " + cus.getLastName());
        lbPhoneNumber.setText(cus.getPhoneNumber());
        if(timeOfDay == 1)
            rdoTheAfternoon.setSelected(true);
        else
            rdoTheEvening.setSelected(true);
    }
    
    public void setChooseBanquetFromCus(Customers c, String id){
        cus = c;
        this.id = id;
        lbName.setText(cus.getFirstName() + " " + cus.getLastName());
        lbPhoneNumber.setText(cus.getPhoneNumber());
    }
}
