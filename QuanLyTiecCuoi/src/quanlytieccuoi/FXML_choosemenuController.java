/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.Customers;
import quanlytieccuoi.models.DateOfBooking;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.OrderDetail;
import quanlytieccuoi.models.Orders;

/**
 *
 * @author PC
 */
public class FXML_choosemenuController implements Initializable {
    String id;
    Customers cus;
    int timeOfDay;
    Orders od;
    BanquetHall banquetHall;
    @FXML
    private TableView<FoodDrinkService> tbInfor;
    @FXML
    private TableColumn clName;
    @FXML
    private TableColumn clPrice;
    @FXML
    private TableColumn clName1;
    @FXML
    private TableColumn clPrice1;
    @FXML
    private TableColumn clAmount;
    @FXML
    private TableColumn clUnitPrice;
    @FXML
    private TableView<FoodDrinkService> tbOrder;
    @FXML
    private RadioButton rdoPayOff;
    @FXML
    private RadioButton rdoPrepayment;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnChoose;
    @FXML
    private Button btnNext;
    @FXML
    private Label lbFeastDay;
    @FXML
    private Label lbBanquetHall;
    @FXML
    private Label lbPriceHall;
    @FXML
    private  Button btnDelete;
    @FXML
    private Label lbName;
    @FXML
    private Label lbPhoneNumber;
    @FXML
    private Label lbId;
    TableRow row;
    @FXML
    private Label totalM;

    @FXML
    private TextField txtSearch;
    @FXML
    private StackPane p;
    SessionFactory factory;
    UtilsDao utils = new UtilsDao();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.factory = HibernateUtil.getSessionFactory();
        rdoPayOff.setSelected(true);
        btnBack.setOnAction(ov ->{
            try {
                FXMLLoader loader = utils.changeSceneHandlers("FXML_choosebanquet.fxml", ov);
                FXML_choosebanquetController controller = loader.getController();

                String str = lbFeastDay.getText();
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                Date d = f.parse(str);
                String str1 = f1.format(d);
                controller.setChooseBanquetHall(lbBanquetHall.getText(),
                        lbPriceHall.getText(), str1, cus, id, timeOfDay);
            } catch (ParseException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        });
        try {
            loadInfor();
        } catch (SQLException ex) {
            System.out.println("ERROR: "+ ex.getMessage());
        }
        
        this.tbInfor.setRowFactory(param -> {
            row = new TableRow();
            row.setOnMouseClicked(et -> {
                btnChoose.setOnAction(ov ->{
                    orderHandler();
                });
            });
            return row;
        });
        this.btnDelete.setDisable(true);
        this.tbOrder.setRowFactory(param -> {
            row = new TableRow();
            row.setOnMouseClicked(et -> {
                this.btnDelete.setDisable(false);
            });
            return row;
        });
        
        this.txtSearch.textProperty().addListener(et -> {
            List<FoodDrinkService> f = utils.getFds(this.txtSearch.getText());
            reloadTableView(f);
        }); 
        
        btnNext.setOnAction(e -> {
            saveOrder();
            FXMLLoader loader = new FXMLLoader();
            
            loader = utils.changeSceneHandlers("FXML_order.fxml", e);
            FXML_orderController controller = loader.getController();
            controller.setOrders(od);
            
            
        });
    }
    
    public void setChooseMenu(BanquetHall hall, String str, String id,
        Customers c, int i){
        banquetHall = hall;
        lbFeastDay.setText(str);
        lbBanquetHall.setText(banquetHall.getName());
        String str1 = String.format("%,d", Integer.parseInt(banquetHall.getPrice()));
        lbPriceHall.setText(str1);
        totalM.setText(str1);
        this.id = id;
        this.cus = c;
        lbId.setText(this.id);
        lbName.setText(cus.getFirstName() + " " + cus.getLastName());
        lbPhoneNumber.setText(cus.getPhoneNumber()); 
        timeOfDay = i; // ca buổi tiệc
    }
    
    private void loadInfor() throws SQLException {
        List<FoodDrinkService> fds = utils.getFds("");
        clName.setCellValueFactory(new PropertyValueFactory("name"));
        clPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.tbInfor.getItems().addAll(fds);
    }
    
    public void loadOrder(FoodDrinkService f, int sl) throws SQLException {
        FoodDrinkService ds = f;
        f.setAmount(sl);
        int p = utils.priceHandler(f.getPrice());
        f.setUnitPrice(String.format("%,d", p * f.getAmount()));
        clName1.setCellValueFactory(new PropertyValueFactory("name"));
        clPrice1.setCellValueFactory(new PropertyValueFactory("price"));
        clAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        clUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        this.tbOrder.getItems().addAll(ds);
    }   
    
    private void reloadTableView(List<FoodDrinkService> cus) {

        this.tbInfor.getItems().clear();
        this.tbInfor.getItems().addAll(cus);
    }
    
    public void  deleteOrder(){
        if(this.tbOrder.getSelectionModel().getSelectedItem() != null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete this row ?");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    String str = tbOrder.getSelectionModel().getSelectedItem().getUnitPrice();
                    int unitPrice = utils.priceHandler(str); // giá của fds xóa * amount
                    tbOrder.getItems().remove(tbOrder.getSelectionModel().getSelectedItem());
                    int totalMonney = utils.priceHandler(totalM.getText()); // lấy giá hiện tại của totalMoney
                    totalM.setText(String.format("%,d",totalMonney - unitPrice)); // trừ tiền khi xóa
                }
            });
            this.tbOrder.getSelectionModel().clearSelection();
        }else{
            Alert a = utils.alertErrorHandler("ERROR", "You have not selected"
                    + " the information to be deleted!");
            a.showAndWait();
        }
    }
    
        
    public void orderHandler(){
        try {
            boolean kq1 = false; // mặc định món k trùng
            FoodDrinkService f = this.tbInfor.getSelectionModel().getSelectedItem();
            int z = 0;
            for(FoodDrinkService f1 : tbOrder.getItems()){
                if(f.getName().equalsIgnoreCase(f1.getName()))
                {
                    kq1 = true; // món trùng
                    break;
                }
                z++;
            }
            int k = z;
            if(kq1){ // món trùng
                editQuantityFds(k);
            }else{
                String s = JOptionPane.showInputDialog("Enter Amount:");
                
                if(s != null){
                   int kq = checkAmount(s);
                    if(kq == 0){ // hơp lệ
                        int x = Integer.parseInt(s);
                        if(x != 0){
                            loadOrder(f, x); 
                            //tinh tien
                            int unitPrice = utils.priceHandler(f.getUnitPrice()); // this price * amount
                            int totalMoney = utils.priceHandler(totalM.getText());
                            totalM.setText(String.format("%,d", totalMoney + unitPrice));
                            this.tbInfor.getSelectionModel().clearSelection();
                        }else{
                            Alert a = utils.alertErrorHandler("Error",
                                "The number must be greater than zero");
                            a.showAndWait().ifPresent(r ->{
                                if (r == ButtonType.OK) {
                                    orderHandler();

                                }
                            });
                        }

                    }else if(kq == 2)
                    {
                        Alert a = utils.alertErrorHandler("Error",
                        "Information required, please enter!");
                        a.showAndWait().ifPresent(r ->{
                            if (r == ButtonType.OK) {
                                orderHandler();
                            }
                        });

                    }else{
                        Alert a = utils.alertErrorHandler("Error",
                        "The quantity is not valid!");
                        a.showAndWait().ifPresent(r ->{
                            if (r == ButtonType.OK) {
                                orderHandler();
                            }
                        });
                    } 
                }
            }
                
                
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
     
    public void editQuantityFds(int k){
        Alert a1 = utils.alertErrorHandler("WARMING: "
                , "Has been selected, please enter the quantity!");
        a1.showAndWait().ifPresent(r ->{
            if (r == ButtonType.OK) {
                this.tbOrder.getSelectionModel().select(k); // Chọn dòng bị trùng
                FoodDrinkService foo = this.tbOrder.getSelectionModel()
                        .getSelectedItem();
                //int q = foo.getAmount(); // sl món hiện tại
                
            }
        });
                
    }    
    
       
    public int checkAmount(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(str);
        if(str.trim().isEmpty()){
            return 2;
        }else if (!matcher.matches()) {
            return 1; //chuỗi không hợp lệ có ký tự
        }else {
            return 0; //chuoi hop le
        }
    }
    
    public void saveOrder(){
        if(this.tbOrder.getItems().size() != 0){
            try {
                Session session = factory.openSession();
                Transaction trans;
                trans = session.beginTransaction();
                
                DateOfBooking dob = new DateOfBooking();
                String id1 = UUID.randomUUID().toString();
                String str = lbFeastDay.getText();
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                Date d = f.parse(str);
                String dateAction = f1.format(d);
                dob.setDateId(id1);
                dob.setDateAction(dateAction);
                dob.setHallId(banquetHall);

                Orders orders = new Orders();
                orders.setOrderId(this.id);
                orders.setCustomerId(cus);
                orders.setHallId(banquetHall);
                orders.setOrderDate(new Date());
                orders.setTotalMoney(String.format("%,d", utils.priceHandler(totalM.getText())));
                orders.setDateId(dob);
                orders.setTimeOfDay(timeOfDay);
                
                // qui ước nếu 0 trả rồi, 1 chua trả
                int i = 0; // mặc định là trả rồi
                if(rdoPrepayment.isSelected())
                    i = 1; // đãi tiệc trả
                orders.setPayment(i);
                session.save(dob);
                
                Set<OrderDetail> fo = new HashSet<OrderDetail>();
                
                for(FoodDrinkService fds : tbOrder.getItems()){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrders(orders);
                    orderDetail.setFds(fds);
                    orderDetail.setDiscount(0.15);
                    orderDetail.setQuantity(fds.getAmount());
                    
                    int quantity = fds.getAmount();
                    int p= utils.priceHandler(fds.getPrice());
                    String s = String.format("%,d", p * quantity);
                    orderDetail.setUnitPrice(s);
                    fo.add(orderDetail);
                }

                orders.setOrderDetail(fo);
                session.save(orders);
                this.od = orders;
                trans.commit();
                
                session.close();
                //utils.alertInformationHandler("Notification", "Save orders successfully!!");
            } catch (ParseException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }else{
            Alert a = utils.alertErrorHandler("ERROR", 
                    "You have not ordered food, drinks and services.\n"
                            + "Please order food, drinks and services!");
            a.showAndWait();
        }
    }

}
