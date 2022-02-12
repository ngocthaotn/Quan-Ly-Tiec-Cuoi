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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.Customers;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.OrderDetail;
import quanlytieccuoi.models.OrderMenuId;
import quanlytieccuoi.models.Orders;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXML_orderController implements Initializable {
    String id;
    @FXML
    private TableView<OrderDetail> tbOrder;
    @FXML
    private TableColumn clName;
    @FXML
    private TableColumn clDiscount;
    @FXML
    private TableColumn clQuantity;
    @FXML
    private TableColumn clUnitPrice;
    TableRow row;
    @FXML
    private Label totalM;
    @FXML
    private Label lbDateOfPayment;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label lbAmercement;
    @FXML
    private Label lbPriceAmercement;
    @FXML
    private Label lbPriceAmercement1;
    @FXML
    private Label lbDiscount;
    @FXML
    private Label lbPay;
    @FXML
    private Label lbFeastDay;
    @FXML
    private Label lbBanquetHall;
    @FXML
    private Label lbOrderDate;
    @FXML
    private Label lbName;
    
    @FXML
    private Label lbDateLater1;
    @FXML
    private Label lbDateLater2;
    @FXML
    private Label lbDateLater;
    @FXML
    private Label lbPhoneNumber;
    @FXML
    private Label lbTotalFinal;
    @FXML
    private Label lbTotalFinal1;
    @FXML
    private Label lbTotalFinal2;
    @FXML 
    private Button btnClose;
    SessionFactory factory;
    UtilsDao utils = new UtilsDao();
    Orders orders;
    
    int i = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.factory = HibernateUtil.getSessionFactory();
        btnClose.setOnAction(ov ->{
            //Admin a = getAdminUser(userName.getText());
            utils.changeSceneHandler("FXML_demoorder.fxml", ov);
            
        });
        lbAmercement.setVisible(false);
        lbDateLater.setVisible(false);
        lbPriceAmercement1.setVisible(false);
        lbTotalFinal.setVisible(false);
        lbTotalFinal2.setVisible(false);
        lbDateLater2.setVisible(false);
        btnUpdate.setOnAction(e ->{
            FXMLLoader loader = utils.changeSceneHandlers("FXML_changepayment.fxml", e);
            FXML_changepaymentController controller = loader.getController();
            controller.setPayment(this.orders);
        });
    }
    
    public void loadOrdersInTableView() throws SQLException{
        OrderMenuId orderMenuId = new OrderMenuId();
            orderMenuId.setOrders(orders);
            List<OrderDetail> orders1 = utils.getOrders(orderMenuId);
            clName.setCellValueFactory(new PropertyValueFactory("fds"));
            clQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
            clUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
            clDiscount.setCellValueFactory(new PropertyValueFactory("discount"));

            this.tbOrder.getItems().addAll(orders1);
            Orders od = orders1.get(0).getOrders();
            lbName.setText(od.getCustomerId().getFirstName()+ " " 
                    + od.getCustomerId().getLastName());
            lbPhoneNumber.setText(od.getCustomerId().getPhoneNumber());
            Date d = od.getOrderDate(); // yyyy/MM/dd
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
            Date s;
            lbOrderDate.setText(f1.format(d));
           
            // xử lý ngày đãi tiệc
            String s1 = od.getDateId().getDateAction(); // yyyy-MM-dd
            String s2 = "";
            Date d1 = null;
            try {
                d1 = f.parse(s1);
                s2 = f1.format(d1);
                lbFeastDay.setText(s2); // ngay dai tiec
            } catch (ParseException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }

            lbBanquetHall.setText(od.getHallId().getName());
            
            lbDiscount.setText(String.format("%.2f", orders1.get(0).getDiscount()));
            totalM.setText(od.getTotalMoney());
            if(od.getPayment() == 0){
                lbPay.setText("Payment: ");
                lbDateOfPayment.setText("Paid"); // đã thanh toán
            }else{
                lbDateOfPayment.setText(s2); // ngày trả tiền bằng ngày đãi tiệc
                if(d1 != null){
                    long n = compareDate(d1); // tính sô ngày trễ hẹn
                    
                    //lbPriceAmercement.setVisible(true);
                    if(n != 0){ //bị phạt
                        //tinh ngày trễ hẹn và tiền phạt
                        lbDateLater1.setText(Long.toString(n));
                        lbDateLater.setVisible(true);
                        lbDateLater2.setVisible(true);
                        int money = utils.priceHandler(od.getTotalMoney());
                        double m = (double)0.01 * money * n;
                        lbAmercement.setVisible(true);
                        lbPriceAmercement.setText(String.format("%,.0f", m)); // tiền phạt
                        lbPriceAmercement1.setVisible(true);
                        // tổng tiền + tiền phạt
                        int t1 = utils.priceHandler(od.getTotalMoney());
                        lbTotalFinal.setVisible(true);
                        lbTotalFinal1.setText(String.format("%,.0f", t1 + m));
                        lbTotalFinal2.setVisible(true);
                    }
                }
                
                
            }
                
            
    }
    
    public long compareDate(Date d){
        Date today = new Date(); // ngày hiện tại
        long n = 0;

        if(d != null)
        {
            SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
            String s = f1.format(today);
            try {
                today = f1.parse(s); // yyyy-MM-dd
            } catch (ParseException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
            if (today.before(d) || today.equals(d)) { // ngày hiện tại < || = ngày đãi, d là tương lai
                return 0; //k phạt
            }else{
                n = daysBetween2Dates(d, today);
            }
            
        }
        
         return n; 
    }
    
    public  long daysBetween2Dates(Date d, Date today) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(d);
        c2.setTime(today);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        return noDay;

    }
    
    public void setOrders(Orders od){
        this.orders = od;
        if(orders.getPayment() == 0)
            btnUpdate.setDisable(true);
        try {
            loadOrdersInTableView();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
}

