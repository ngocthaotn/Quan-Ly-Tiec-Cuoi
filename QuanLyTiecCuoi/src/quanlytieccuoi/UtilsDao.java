/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Loader;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import quanlytieccuoi.models.Admin;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.Customers;
import quanlytieccuoi.models.DateOfBooking;
import quanlytieccuoi.models.Positions;
import quanlytieccuoi.models.MenuGroup;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.OrderDetail;
import quanlytieccuoi.models.OrderMenuId;
import quanlytieccuoi.models.Orders;
import quanlytieccuoi.models.Users;

/**
 *
 * @author PC
 */
public class UtilsDao {
    SessionFactory factory; 
    //d??ng cho Address, Qualification
    public int checkLongString(String str){
        int i = checkSymbol1(str, "^\\S[\\p{L}[0-9]\\s[.,/]]+\\S$"); // \\p{L} d??ng cho ph??p nh???p unicode
        if (str.trim().isEmpty()) {
            return 2; // false chu???i r???ng
        } else if (i == 1) {
            return 1; //false khong ????ng ?????nh d???ng, kh??ng ch???a ky tu dac biet 
        } else// 0
            return 0; // h???p l???  
    }
    
    // d??ng cho t??n food drink
    public int checkName(String str){
        int i = checkSymbol(str, "^\\S[\\p{L}\\s]+\\S$", 50); // \\p{L} d??ng cho ph??p nh???p unicode
        if (str.trim().isEmpty()) {
            return 2; // false chu???i r???ng
        } else if (i == 1) {
            return 1; //false khong ????ng ?????nh d???ng, ch???a ky tu dac biet
        } else if (-1 == i) {
            return -1; // kh??ng hop l??? qu?? d??i, ????ng ?????nh d???ng
        } else// 0
            return 0; // h???p l???  
    }
    
    //d??ng cho firstName, lastname, unit
    public int checkFirstnameLastName(String str) {

        int i = checkSymbol(str, "^[\\p{L}]+$", 15); //cho nhap unicode, khong khoang trang
        if (str.trim().isEmpty()) {
            return 2; // false chu???i r???ng
        } else if (i == 1) {
            return 1; //false khong ????ng ?????nh d???ng, kh??ng ch???a ky tu dac biet, kho???ng tr???ng, 
        } else if (-1 == i) {
            return -1; // kh??ng hop l??? qu?? d??i, ????ng ?????nh d???ng
        } else// 0
            return 0; // h???p l???  
    }
    // d??ng cho username
    public int checkUserName(String str) {
        int i = checkSymbol(str, "^[a-z[0-9][A-Z]]+$", 15); // k ky tu dat biet, k kho???ng tr???ng
        if (str.trim().isEmpty()) {
            return 2; // false chu???i r???ng
        } else if (i == 1) {
            return 1; //false khong ????ng ?????nh d???ng, kh??ng ch???a ky tu dac biet, kho???ng tr???ng, 
        } else if (-1 == i) {
            return -1; // kh??ng hop l??? qu?? d??i, ????ng ?????nh d???ng
        } else// 0
            return 0; // h???p l???  }
    }
    // d??ng ????? x??t chu???i
    public int checkSymbol(String str, String p, int limit) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches() && str.trim().length() > limit) {
            return -1;// kh??ng hop l??? qu?? d??i, ????ng ?????nh d???ng
        } else if (matcher.matches() && str.trim().length() <= limit) {
            return 0;// h???p l??? , gi???ng ?????nh d???ng chu???i truy???n v??o   
        } else {
            return 1; // false k d??ng?????nh d???ng
        }
    }
 
    public int checkSymbol1(String str, String p) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return 0;//true  hop le
        }
        return 1; // khong hop le
    }

    public int checkPassword(String str) {
        int i = checkSymbol(str, "[\\S]+", 15); // Chu???i k ch???a kho???ng tr???ng
        if (str.trim().isEmpty()) {
            return 2; // false chu???i r???ng
        } else if (i == 1) {
            return 1; //khong chua kho???ng tr???ng, ch??? 15 k?? t??? 
        } else if (-1 == i) {
            return -1; // kh??ng hop l??? qu?? d??i, ????ng ?????nh d???ng
        } else// 0
        {
            return 0; // h???p l???
        }
    }
    // hi???n tooltip
    public Tooltip tooltipHandler() {
        Tooltip tooltip = new Tooltip();
        tooltip.setFont(new Font("Arial", 13));
        tooltip.setStyle("-fx-background-color: white; -fx-text-fill: red;");
        return tooltip;
    }
//    public boolean checkEmpty(String str){
//        boolean kq = false;  // khong r??ng
//        Tooltip tooltip = tooltipHandler();
//        if(str.trim().isEmpty()){
//            kq = true;// rong
//        }  
//        else{
//            kq = false;//k rong
//        }
//        return kq;
//    }

    public int checkEmail(String str) {
        boolean flag;
        String emailPattern = "\\w+[.]?[\\w]+?@[a-z]+[.][a-z]+([.][a-z]+)?";
        flag = str.matches(emailPattern);
        if (str.trim().isEmpty()){
            return 1; // khong hop le, rong
        } else if (!flag){
            return 2;// email khong hop le, chua ky tu dat biet [.,@$]
        }
        return 0; // hop le
    }
    // show l???i
    public void showError(Label lb, Tooltip tooltip){
        lb.setVisible(true);
        Image image = new Image("quanlytieccuoi/Images/error.png");
        ImageView image1 = new ImageView(image);
        lb.setGraphic(image1);
        lb.setTooltip(tooltip);
    }
    
    

    public int checkEmpty(String str) {
        if(str.trim().isEmpty())
            return 1;
        return 0;
    }

    public Alert alertErrorHandler(String strTitle, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(strTitle);
        a.setContentText(content);
        //a.showAndWait();
        return a;
    }
    public Alert alertInformationHandler(String strTitle, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(strTitle);
        a.setContentText(content);
        //a.showAndWait();
        return a;
    }

    
    // price, Acreage, max people, tien Khach tra 
    public int checkPrice(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(str);
        if(str.trim().isEmpty()){
            return 2;
        }else if (!matcher.matches()) {
            return 1; //chu???i kh??ng h???p l??? c?? k?? t???
        } else {
            return 0; //chuoi hop le
        }
    }

    // check s??t
    public int checkNumber(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(str);
        if (str.trim().isEmpty()) {
            return -2; // chu???i r???ng
        } else {
            if (!matcher.matches()) {
                return 0; //chu???i kh??ng h???p l??? c?? k?? t???
            } else if (str.trim().length() == 10) {
                if (str.trim().substring(0, 1).equals("0")) {
                    return 1; // so dien thoai hop le
                } else {
                    return 2; // sdt phai bat dau bang 0
                }
            } else {
                return -1; // s??t ch??? g???m 10 s???
            }
        }

    }

    public int checkLog(String uName, String pass) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
            
        if (!uName.trim().isEmpty() && !pass.trim().isEmpty()) {// user va pass ?????u # empty, 
            //List<Users> l = getUser(uName);
            List<Users> q = session.createQuery("from Users u where u.userName = :n")
                    .setParameter("n", uName).list();
            
            if(q.size() != 0){ //size ll = 1
                for(Users u : q){
                    String hash = u.getPassword();
                    boolean match = BCrypt.checkpw(pass, hash);
                    if (match) {
                        return 0; //true successfull
                    } else {
                        return 1;  //false, true username but false password       
                    }
                }
            }else
                return -1; // password false or true => false
        }
        return 2; // empty
        
        
    }


    public List<FoodDrinkService> getFoodDrinkService() {
        List<FoodDrinkService> f = new ArrayList<>();
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("FROM FoodDrinkService");
        List l = q.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            FoodDrinkService p = (FoodDrinkService) iterator.next();
            f.add(p);
        }
        session.close();
        return f;
    }

    public List<FoodDrinkService> getFoodDrinkService(String str) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(FoodDrinkService.class);
        if(!str.trim().isEmpty())
            cr.add(Restrictions.like("name", str));
        List<FoodDrinkService> admin  = null;
        if(cr.list().size() != 0)
            admin =  cr.list();
        session.close();
        return admin;
    }
    
    
    public boolean deleteFoodDrinkService(String dataID) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = null;
        boolean kq = false; // delete khong thanh cong
        if(!dataID.trim().isEmpty()){
            try{
                trans = session.beginTransaction();
                List<FoodDrinkService> foo = getFoodDrinkService();
                for(FoodDrinkService f: foo){
                    if(f.getID().equals(dataID)){
                        session.delete(f);
                        trans.commit();
                        kq = true;
                    }
                }
                
            }catch (HibernateException ex){
                kq = false;
                System.out.println("L???i: " + ex.getMessage());
            }finally{
                session.close();
            }
        }
        return kq;
    }
    
    public void addOrUpdateData(FoodDrinkService f){
        //this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(f);

        trans.commit();

        session.close();
    }

    public List<Admin> getAdmin() {
        List<Admin> f = new ArrayList<>();
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("FROM Admin");
        List l = q.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            Admin p = (Admin) iterator.next();
            f.add(p);
        }
        session.close();
        return f;
    }
    public List<Admin> getAdmin(String keyword){
        
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Admin.class);
        
        if (!keyword.isEmpty())
            cr.add(Restrictions.ilike("firstName", String.format("%%%s%%", keyword)));
        
        List<Admin> admin = cr.list();
        //questions.forEach();
        
        session.close();
        
        return admin;
    
    }
    
    public List<Admin> getEmailAdmin(String str){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Admin.class);
        if(!str.trim().isEmpty())
            cr.add(Restrictions.like("email", str));
        List<Admin> admin  = null;
        if(cr.list().size() != 0)
            admin =  cr.list();
        session.close();
        return admin;
    }
    
    public List<Admin> getNameAdmin(String str){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Admin.class);
        if(!str.trim().isEmpty())
            cr.add(Restrictions.like("adminId", str));
        List<Admin> admin  = null;
        if(cr.list().size() != 0)
            admin =  cr.list();
        session.close();
        return admin;
    }
    
    public void addOrUpdateAdmin(Admin ad){
        //this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(ad);

        trans.commit();

        session.close();
    }
    
    public void deleteAdmin(Admin ad) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        String str = ad.getAdminId();
        session.delete(ad);
        deleteUser(str);
        //trans = session.beginTransaction();
        trans.commit();
        
        session.close();
    }
    
    public void deleteUser(String str) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        List<Users> u = getUserId(str);
        Transaction trans = session.beginTransaction();
        if(u != null){
            session.delete(u.get(0));
            trans.commit();
        }
        session.close();
    }
    
    public List<Users> getUserId(String str) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        String sql = "FROM Users U WHERE U.id = :idUser";
        Query q = session.createQuery(sql);
        if(!str.trim().isEmpty())
            q.setParameter("idUser", str);
        List<Users> users = null;
        if(q.list().size() != 0)
            users =  q.list();
        session.close();
        return users;
    }
    
    public List<Customers> getCustomers() {
        
        List<Customers> f = new ArrayList<>();
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("FROM Customers");
        List l = q.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            Customers p = (Customers) iterator.next();
            f.add(p);
        }
        session.close();
        return f;
    }
    public List<Customers> getCustomer(String keyword){
        
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Customers.class);
        
        if (!keyword.isEmpty())
            cr.add(Restrictions.ilike("firstName", String.format("%%%s%%", keyword)));
        
        List<Customers> cus = cr.list();
        //questions.forEach();
        
        session.close();
        
        return cus;
    
    }
    
    public List<Users> getUser(String str) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Users.class);
        if(!str.trim().isEmpty())
            cr.add(Restrictions.like("userName", str));
        List<Users> users = null;
        if(cr.list().size() != 0)
            users =  cr.list();
        session.close();
        return users;
    }
    
    public List<DateOfBooking> getDateOfBooking(BanquetHall str) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(DateOfBooking.class);
        List<DateOfBooking> l1 = cr.list();
        cr.add(Restrictions.like("hallId", str));
        List<DateOfBooking> dob0 = null;
        if(cr.list().size() != 0)
            dob0 =  cr.list();
        session.close();
        return dob0;
    }
    
    public List<Users> getUser() {
        this.factory = HibernateUtil.getSessionFactory();
        List<Users> f = new ArrayList<>();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Users.class);
        List l = cr.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            Users p = (Users) iterator.next();
            f.add(p);
        }
        session.close();
        return f;    
    }
    
    
    
    public List<FoodDrinkService> getFds() {
        
        List<FoodDrinkService> f = new ArrayList<>();
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("FROM FoodDrinkService");
        List l = q.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            FoodDrinkService p = (FoodDrinkService) iterator.next();
            f.add(p);
        }
        session.close();
        return f;
    }
    
    public List<FoodDrinkService> getFds(String keyword){
        
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(FoodDrinkService.class);
        
        if (!keyword.isEmpty())
            cr.add(Restrictions.ilike("name", String.format("%%%s%%", keyword)));
        
        List<FoodDrinkService> f = cr.list();
        //questions.forEach();
        
        session.close();
        
        return f;
    
    }
    //update
    public boolean addOrUpdateCustomer(String cusId, String fn, String ln, String ns,
            String phone, String email, String gender, String address){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = null;
        boolean kq = false; // delete khong thanh cong
        if(!cusId.trim().isEmpty()){
            try{
                trans = session.beginTransaction();
                List<Customers> cus = getCustomers();
                for(Customers c : cus){
                    if(c.getCustomerId().equals(cusId)){
                        c.setFirstName(fn);
                        c.setLastName(ln);
                        c.setPhoneNumber(phone);
                        c.setEmail(email);
                        c.setGender(gender);
                        c.setAddress(address);
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            c.setDateOfBirth(f.parse(ns));
                        } catch (ParseException ex) {
                            System.out.println("ERROR: " + ex.getMessage());
                        }
                        session.saveOrUpdate(c);
                        trans.commit();
                        kq = true;
                    }
                }
                
            }catch (HibernateException ex){
                kq = false;
                System.out.println("ERROR: " + ex.getMessage());
            }finally{
                session.close();
            }
        }
        return kq;
    }
    //delete
    public boolean deleteCustomer(String cusId) {
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = null;
        boolean kq = false; // delete khong thanh cong
        if(!cusId.trim().isEmpty()){
            try{
                trans = session.beginTransaction();
                List<Customers> cus = getCustomers();
                for(Customers c : cus){
                    if(c.getCustomerId().equals(cusId)){
                        session.delete(c);
                        trans.commit();
                        kq = true;
                    }
                }
                
            }catch (HibernateException ex){
                kq = false;
                System.out.println("L???i: " + ex.getMessage());
            }finally{
                session.close();
            }
        }
        return kq;
                        
    }
    
    public List<Positions> getPositions() {
        List<Positions> pos = new ArrayList<>();
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("FROM Positions");
        List l = q.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            Positions p = (Positions) iterator.next();
            pos.add(p);
        }
        session.close();
        return pos;
    }

    public List<MenuGroup> getMenuGroup() {
        List<MenuGroup> mArr = new ArrayList<>();
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("FROM MenuGroup");
        List l = q.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            MenuGroup mi = (MenuGroup) iterator.next();
            mArr.add(mi);
        }
        session.close();
        return mArr;
    }

    public List<BanquetHall> getBanquetHall() {
        List<BanquetHall> mArr = new ArrayList<>();
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("FROM BanquetHall");
        List l = q.list();
        Iterator iterator = l.iterator();
        while (iterator.hasNext()) {
            BanquetHall mi = (BanquetHall) iterator.next();
            mArr.add(mi);
        }
        session.close();
        return mArr;
    }
    
    public List<BanquetHall> getBanquetHall(String str){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(BanquetHall.class);
        if(!str.trim().isEmpty())
            cr.add(Restrictions.like("name", str));
        List<BanquetHall> b = null;
        if(cr.list().size() != 0)
            b =  cr.list();
        session.close();
        return b;
    
    }
    
    public void  AddOrUpdateHall(BanquetHall f){
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(f);

        trans.commit();

        session.close();
    }
    
    public List<OrderDetail> getOrders(OrderMenuId odId){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        List q = session.createQuery("from OrderDetail o where o.primaryKey.orders = :id")
                .setParameter("id", odId.getOrders()).list();
        session.close();
        return q;
    }
    
    public List<Orders> getOrders(){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Orders.class);
        List<Orders> od = cr.list(); 
        session.close();
        return od;
    }
    public void addOrUpdateOrder(Orders od){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(od);
        trans.commit();
        session.close();
    }
    public List<Orders> getOrders(String str){
        this.factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Orders.class);
        
        if (!str.isEmpty())
            cr.add(Restrictions.like("payment",Integer.parseInt(str)));
        
        List<Orders> o = cr.list(); 
        session.close();
        return o;
    }
    
    // check trung user
    public boolean checkUserHandler(String str){
        List<Users> l = getUser();
        for(Users f : l)
            if(f.getUserName().equals(str))// x??t t??n tr??ng
                return false; // tr??ng
        return true; // k tr??ng
    }
    // check tr??ng email
    public boolean checkEmailHandler(String str,int i){
        //1 admin
        //2 customers
        if(1 == i){
            List<Admin> l = getAdmin();
            for(Admin f : l)
                if(f.getEmail().equals(str))// x??t mail tr??ng
                    return false; // tr??ng
            return true; // k tr??ng
        }else{
            List<Customers> l = getCustomers();
            for(Customers f : l)
                if(f.getEmail().equals(str))// x??t mail tr??ng
                    return false; // tr??ng
            return true; // k tr??ng
        }
        
    }
    
    public int checkSaveCustomers(String fn,String ln, String ns, String gt, String s??t,
            String dc, String email){
        if(!fn.trim().isEmpty() && !ln.trim().isEmpty() && !ns.trim().isEmpty()
                && !gt.trim().isEmpty() && !dc.trim().isEmpty() && !email.trim().isEmpty()
                && !s??t.trim().isEmpty()){
            if(checkEmailHandler(email, 2)){
                try {
                    String id = UUID.randomUUID().toString();
                    Session session = factory.openSession();
                    Transaction trans;
                    trans = session.beginTransaction();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    Customers customer = new Customers(id, fn, ln, f.parse(ns), gt, s??t
                            , email, dc);
                    session.save(customer);
                    trans.commit();
                    session.close();
                    return 0; // luu thanh cong
                    //factory.close();
                    
                } catch (ParseException ex) {
                    System.out.println("LOi:" + ex.getMessage()); // loi dinh dang ngay
                }
            }else
                return -1; // email trung
        }
        return 2; // txt rong
    }
    
    public FXMLLoader changeSceneHandlers(String nameFxml, ActionEvent e){
        FXMLLoader loader = new FXMLLoader();
        try {
            Stage t = (Stage)((Node)(e.getSource())).getScene().getWindow();
            
            loader.setLocation(getClass().getResource(nameFxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //FXML_homeController h = loadder.getController();
            //h.setTenAdmin(str);
            t.setScene(scene);
            t.centerOnScreen();
//            stage.getIcons().add(new Image("quanlytieccuoi/Images/iconapp2.png"));
//            stage.setTitle("KINGDOM");
//            stage.setResizable(false);
//            stage.setScene(scene);
            //Ch??? ?????nh modality (th??? th???c) cho c???a s??? m???i
            //NONE:c???a s??? m???i s??? ?????c l???p v???i c???a s??? cha, t????ng t??c dc hai c??i
            //WINDOW_MODAL: c???a s??? m???i kh??a c???a s??? cha
            //APPLICATION_MODAL: n?? s??? kh??a m???i c???a s??? kh??c c???a ???ng d???ng
//            stage.initModality(Modality.APPLICATION_MODAL);
            
//            t.close();
            //stage.initOwner(t);
            
            
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return loader;
    }
    public void changeSceneHandler(String nameFxml, ActionEvent e){
        
        try {
            Stage t = (Stage)((Node)(e.getSource())).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nameFxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //FXML_homeController h = loadder.getController();
            //h.setTenAdmin(str);
            t.setScene(scene);
            t.centerOnScreen();
//            stage.getIcons().add(new Image("quanlytieccuoi/Images/iconapp2.png"));
//            stage.setTitle("KINGDOM");
//            stage.setResizable(false);
//            stage.setScene(scene);
            //Ch??? ?????nh modality (th??? th???c) cho c???a s??? m???i
            //NONE:c???a s??? m???i s??? ?????c l???p v???i c???a s??? cha, t????ng t??c dc hai c??i
            //WINDOW_MODAL: c???a s??? m???i kh??a c???a s??? cha
            //APPLICATION_MODAL: n?? s??? kh??a m???i c???a s??? kh??c c???a ???ng d???ng
//            stage.initModality(Modality.APPLICATION_MODAL);
            
//            t.close();
            //stage.initOwner(t);
            
            
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    
    
    public void changeSceneHandler(String nameFxml, MouseEvent e){
        try {
            Stage t = (Stage)((Node)(e.getSource())).getScene().getWindow();
            FXMLLoader loadder = new FXMLLoader();
            loadder.setLocation(getClass().getResource(nameFxml));
            Parent root = loadder.load();
            Scene scene = new Scene(root);

//            stage.getIcons().add(new Image("quanlytieccuoi/Images/iconapp2.png"));
//            stage.setTitle("KINGDOM");
//            stage.setResizable(false);
//            stage.setScene(scene);
            //Ch??? ?????nh modality (th??? th???c) cho c???a s??? m???i
            //NONE:c???a s??? m???i s??? ?????c l???p v???i c???a s??? cha, t????ng t??c dc hai c??i
            //WINDOW_MODAL: c???a s??? m???i kh??a c???a s??? cha
            //APPLICATION_MODAL: n?? s??? kh??a m???i c???a s??? kh??c c???a ???ng d???ng
//            stage.initModality(Modality.APPLICATION_MODAL);
            
//            t.close();
            //stage.initOwner(t);

            t.setScene(scene);
            t.centerOnScreen();
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    public void buttonPrevious(Button btn){
        ImageView imageView = new ImageView(new Image
        ("quanlytieccuoi/Images/previous.png"));
        imageView.setFitWidth(32);
        btn.setGraphic(imageView);

    }
    
    // copy file ???nh vao project
    public void copyFile( File file){
        try {
            File f1 = new File("");
            Path sourcePath = FileSystems.getDefault().getPath(file.getAbsolutePath()); //l???y ??c ???????ng d???n file c???n coppy
            Path targetPath = FileSystems.getDefault().getPath(f1.getAbsolutePath() // f1.getAbslutpath l?? l???y ???????ng d???n d???n project
                    + "\\src\\quanlytieccuoi\\Images\\"+ file.getName()); //???????ng d???n ????? image, file.getName() ????? l???y ten c???a file coppy (c??ng c?? th??? ?????t t??n kh??c)
            Files.copy(sourcePath, targetPath); // coppy
        } catch (IOException ex) {
            System.err.println("ERROR: "+ ex.getMessage());
        }
    
    }
    
    public File chooseImage(StackPane p, File file, Text txtImage,ImageView imageView,
            Label lb){
        Stage stage = (Stage)p.getScene().getWindow();// thiet lap id cho pane. v?? sau do lay Stage ra de khi hien file chi cho hien mot c??i.
        FileChooser fileChooser = new FileChooser(); //tao file chooser
        fileChooser.setTitle("Choose a Image");//thiet lap title cho filechooser
        FileChooser.ExtensionFilter imageFilter = 
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");//ch???  cho fileChooser hi???n image 
        fileChooser.getExtensionFilters().add(imageFilter);// add vao 
        file = fileChooser.showOpenDialog(stage);// chi cho chon 1 file, n???u ch???n cancel th?? file l?? null
        if(file != null)// khi c?? ch???n file
        {
            txtImage.setText(file.getName()); // lay ten file 
            Image image = new Image(file.toURI().toString(), 219, 215, false, true); // l???y t??? file ch???n
            imageView.setImage(image);//b??? ???nh v??o imageView
            File f1 = new File("");
            Path pathImage = FileSystems.getDefault().getPath(f1.getAbsolutePath() // f1.getAbslutpath l?? l???y ???????ng d???n d???n project
                    + "\\src\\quanlytieccuoi\\Images");
            File dir = new File(pathImage.toUri()); //chuy???n path sang URI
            if (dir.isDirectory()) { // x??t c?? ph???i th?? m???c k
                File[] children = dir.listFiles(); // l??u c??c file trong m???ng
                for (File child : children){ // duy???t qua c??c ph???n t???
                    if(child.getName().equals(txtImage.getText())){ // x??t t??n n???u tr??ng th?? xu???t th??ng b??o
                        Tooltip tooltip = tooltipHandler();
                        tooltip.setText("File name is used! Please chosse another file!");
                        showError(lb, tooltip);
                    }
                }
            }
        }
        return file;
    }
    
    public void backHandler(Button btn){
        ImageView imageView = new ImageView(new Image
                ("quanlytieccuoi/Images/previous.png"));
            imageView.setFitWidth(32);
            btn.setGraphic(imageView);
    }
     public static void goLink(String link) throws URISyntaxException{
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     
     public int priceHandler(String s){
        Pattern pattern = Pattern.compile("[^0-9]"); 
        Matcher matcher = pattern.matcher(s); 
        String number = matcher.replaceAll(""); 
        return Integer.parseInt(number);
    }
     public String[] editDate(String n){  // yyyy-mm-dd
        String[] kq = n.split("[-]");
        return kq;
    }
     public boolean checkNam(String s){
        Pattern pattern = Pattern.compile("^[0-9]{4}$");
        Matcher mat = pattern.matcher(s);
        if (mat.matches()){
            return true;
        }
        return false;
    }
    
    public List<Integer> listDoanhThuT(int nam) throws ParseException{
       this.factory = HibernateUtil.getSessionFactory();
       Session session = factory.openSession();
       ArrayList<Integer> ls =  new ArrayList<>(12);
       int t = 0;
       for (int i = 1; i <= 12; i++){
            t = 0;
            int thang = i;
            Query q = session.createQuery("from Orders o "
                   + "where o.payment = 0");
            //q.setParameter("n", 0);
            List<Orders> r = q.list();
            
            if(r.size() != 0){
                for (Orders od : r){
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                    
                    cal.setTime(od.getOrderDate());
                    int month = cal.get(Calendar.MONTH); // 5
                    int year = cal.get(Calendar.YEAR); // 2016
                    if(month == thang && year == nam)
                        t += priceHandler(od.getTotalMoney());
                }
                ls.add(i - 1, t);
            }
       }
       return ls;
    }
}
