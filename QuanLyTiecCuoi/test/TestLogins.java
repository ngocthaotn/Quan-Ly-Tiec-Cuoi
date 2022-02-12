/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import quanlytieccuoi.UtilsDao;
import quanlytieccuoi.models.Admin;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.Customers;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.MenuGroup;
import quanlytieccuoi.models.Orders;
import quanlytieccuoi.models.Positions;
import quanlytieccuoi.models.Users;

/**
 *
 * @author PC
 */
public class TestLogins {
    private static SessionFactory sessionFactory;
    private  static Session session;
    @BeforeClass
    public  static void testStart(){
        Configuration cfgs = new Configuration();
        cfgs.addAnnotatedClass(Users.class);
        cfgs.addAnnotatedClass(Customers.class);
        cfgs.addAnnotatedClass(Positions.class);
        cfgs.addAnnotatedClass(Admin.class);
        cfgs.addAnnotatedClass(MenuGroup.class);
        cfgs.addAnnotatedClass(FoodDrinkService.class);
        cfgs.addAnnotatedClass(BanquetHall.class);
        cfgs.addAnnotatedClass(Orders.class);
        cfgs.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfgs.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfgs.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/quanlytieccuoi?zeroDateTimeBehavior=convertToNull");
        cfgs.setProperty("hibernate.connection.username", "root");
        cfgs.setProperty("hibernate.connection.password", "123456");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfgs.getProperties());
        sessionFactory = cfgs.buildSessionFactory(builder.build());
        session = sessionFactory.openSession();
    }
    @AfterClass
    public static void testEnd(){
        session.close();
        sessionFactory.close();
        
    }
    
    @Test
    public void testTaiKhoanVaMatKhauRong(){
            UtilsDao utils = new UtilsDao();
        String userName1 = ""; 
        String password1= "";
        try {
            int actual = utils.checkLog(userName1, password1);
            Assert.assertEquals(2, actual); // 2 rong
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        
    }
    @Test
    public void testDungUserNameSaiPassword(){
            UtilsDao utils = new UtilsDao();
        String userName1 = "vivi"; 
        String password1= "gdgdgf";
        try {
            int actual = utils.checkLog(userName1, password1);
            Assert.assertEquals(1, actual); // 1 sai pass, đúng username
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        
    }
    
    @Test
    public void testDungUsernameDungPassword(){
            UtilsDao utils = new UtilsDao();
        String userName1 = "vivi"; 
        String password1= "123456";
        try {
            int actual = utils.checkLog(userName1, password1);
            System.out.println(actual);
            Assert.assertEquals(0, actual); // 0 đúng pass, đúng username
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        
    }
    
    @Test
    public void testSaiUsernameDungPassword(){
            UtilsDao utils = new UtilsDao();
        String userName1 = "sdgsd"; 
        String password1= "123456";
        try {
            int actual = utils.checkLog(userName1, password1);
            System.out.println(actual);
            Assert.assertEquals(-1, actual); // -1 k khớp userName bất chấp password
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        
    }
    
    @Test
    public void testKhongUsernameNhapPassword(){
            UtilsDao utils = new UtilsDao();
        String userName1 = ""; 
        String password1= "123456";
        try {
            int actual = utils.checkLog(userName1, password1);
            System.out.println(actual);
            Assert.assertEquals(2, actual); // 2 một ttrong 2 hai cả hai rỗng
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        
    }
    
    @Test
    public void testDungUsernameKhongPassword(){
            UtilsDao utils = new UtilsDao();
        String userName1 = "vivi"; 
        String password1= "";
        try {
            int actual = utils.checkLog(userName1, password1);
            System.out.println(actual);
            Assert.assertEquals(2, actual); // 2 : một trong 2 hay cả hai rỗng
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        
    }
    
     
    
    
    
    
//    @Test
//    public void testUseName1(){
//        Utils utils = new Utils();
//        String str = ""; // chuoi rong
//        int actual = utils.checkUserName(str);
//        
//        Assert.assertEquals(2, actual);
//    }
//    
//    @Test
//    public void testUseName2(){
//        Utils utils = new Utils();
//        String str = ",./;"; // chua ky tu dat biet
//        int actual = utils.checkUserName(str);
//        
//        Assert.assertEquals(1, actual); //
//    }
//    
//    @Test
//    public void testUseName3(){
//        Utils utils = new Utils();
//        String str = "vothivi98"; // true
//        int actual = utils.checkUserName(str);
//        
//        Assert.assertEquals(0, actual);
//    }
//    
//    @Test
//    public void testUseName4(){
//        Utils utils = new Utils();
//        String str = "     "; // 
//        int actual = utils.checkUserName(str);
//        
//        Assert.assertEquals(2, actual); 
//    }
//    
//    @Test
//    public void testUseName5(){
//        Utils utils = new Utils();
//        String str = "gfgfdewsaqwert35667212"; 
//        
//        int actual = utils.checkUserName(str);
//        
//        Assert.assertEquals(-1, actual); // dung dinh dang, dai
//    }
    

}
