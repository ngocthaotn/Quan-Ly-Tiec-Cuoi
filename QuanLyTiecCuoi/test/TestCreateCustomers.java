/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
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
public class TestCreateCustomers {
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
    public void testNhapUsernameCoKhoangTrangDau() {
        UtilsDao utils = new UtilsDao();
        String str = " doubleThao";
        int actual = utils.checkUserName(str);

        Assert.assertEquals(1, actual);
    }

    @Test
    public void testNhapUsernameCoKhoangTrangSau() {
        UtilsDao utils = new UtilsDao();
        String str = " doubleThao";
        int actual = utils.checkUserName(str);

        Assert.assertEquals(1, actual);
    }

    @Test
    public void testNhapUsernameCoKhoangChinhGiua() {
        UtilsDao utils = new UtilsDao();
        String str = " double   Thao";
        int actual = utils.checkUserName(str);

        Assert.assertEquals(1, actual);
    }

    @Test       
    public void testPassWordNhapKhoangTrangDau() {
        UtilsDao utils = new UtilsDao();
        String str = "   123456";
        int actual = utils.checkPassword(str);

        Assert.assertEquals(1, actual);
    }
    
    @Test       
    public void testPassWordNhapKhoangTrangCuoi() {
        UtilsDao utils = new UtilsDao();
        String str = "   123456";
        int actual = utils.checkPassword(str);

        Assert.assertEquals(1, actual);
    }
    @Test       
    public void testPassWordNhapKhoangTrangGiua() {
        UtilsDao utils = new UtilsDao();
        String str = "1 234 56";
        int actual = utils.checkPassword(str);

        Assert.assertEquals(1, actual);
    }

//test kiểm tra email
    @Test
    public void testSaiDinhDangEmail() {
        UtilsDao utils = new UtilsDao();
        
        String email = "vothivi";
        int actual = utils.checkEmail(email);
        Assert.assertEquals(2, actual); // email sai định dạng
    }

    @Test
    public void testDungDinhDangEmail(){
        UtilsDao utils = new UtilsDao();
        
        String email = "vothivi@gmail.com";
        int actual = utils.checkEmail(email);
        Assert.assertEquals(0, actual);
    }
    @Test
    public void testKhongNhapEmail(){
        UtilsDao utils = new UtilsDao();
        
        String email = "";
        int actual = utils.checkEmail(email);
        Assert.assertEquals(1, actual);
    }
    
    @Test
    public void testNhapEmailChuaKhoangTrangDau(){
        UtilsDao utils = new UtilsDao();
        
        String email = "       thaone@gmail.com";
        int actual = utils.checkEmail(email);
        Assert.assertEquals(2, actual);
    }
    
    @Test
    public void testNhapEmailChuaKhoangTrangCuoi(){
        UtilsDao utils = new UtilsDao();
        
        String email = "thaone@gmail.com             ";
        int actual = utils.checkEmail(email);
        Assert.assertEquals(2, actual);
    }
    
    @Test
    public void testNhapEmailChuaKhoangTrangGiua(){
        UtilsDao utils = new UtilsDao();
        
        String email = "thao     ne@gmail.com";
        int actual = utils.checkEmail(email);
        Assert.assertEquals(2, actual);
    }
    
    
    //test Nhâp địa chỉ
    @Test
    public void testNhapDungDiaChi() {
        UtilsDao utils = new UtilsDao();
        String address = "5 Phan Đình Phùng,phuong 1 ,Quận 1";
        int actual = utils.checkLongString(address);
        Assert.assertEquals(0, actual); 
    }
    
    @Test
    public void testNhapDiaChiNhungChuaKiTuDacBiet() {
        UtilsDao utils = new UtilsDao();
        String address = "5 Phan Đình Phùng,? ,Quận 1";
        int actual = utils.checkLongString(address);
        Assert.assertEquals(1, actual); // Địa chỉ chứa kí tự đặc biệt
    }
    @Test
    public void testKhongNhapDiaChi() {
        UtilsDao utils = new UtilsDao();
        String address = "";
        int actual = utils.checkLongString(address);
        Assert.assertEquals(2, actual); 
    }
    
    @Test
    public void testNhapDiaChiChuaKhoangTrangDau() {
        UtilsDao utils = new UtilsDao();
        String address = "      5 Phan Đình Phùng,phuong 1 ,Quận 1";
        int actual = utils.checkLongString(address);
        Assert.assertEquals(1, actual); 
    }

    @Test
    public void testNhapDiaChiChuaKhoangTrangSau() {
        UtilsDao utils = new UtilsDao();
        String address = "5 Phan Đình Phùng,phuong 1 ,Quận 1               ";
        int actual = utils.checkLongString(address);
        Assert.assertEquals(1, actual); 
    }
    
    //test kiểm tra số điện thoại
    @Test
    public void testKhongNhapSoDienThoai(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(-2, actual);
    }
    
    @Test
    public void testSDTChuaKiTuDacBiet(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "!*#@";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(0, actual);
    }
    
    @Test
    public void testNhapSDTVaKiTuDacBiet(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "0988535*#@";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(0, actual);
    }
    
    @Test
    public void testSDTDung(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "0988198111";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(1, actual);//đủ 10 chữ số
    }
    
    @Test
    public void testSDTPhaiBatDauBang0(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "2988198111";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(2, actual);
    }
    
    @Test
    public void testSDTKhongDu10ChuSo(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "09881";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(-1, actual);
    }
    
    @Test
    public void testSDTChuaKiTuChu(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "09881ddaaasda";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(0, actual);
    }
    @Test
    public void testSDTQuaDaiVaBatDauBang0(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "0988599123456";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(-1, actual);
    }
    
    @Test
    public void testSDTQuaDaiVaKhongDaquBang0(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "3332988599123456";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(-1, actual);
    }
    
    @Test
    public void testSDTCoKhoangTrangDau(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "   0913254323";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(0, actual);
    }
    @Test
    public void testSDTCoKhoangTrangSau(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "0913254323       ";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(0, actual);
    }
    
    @Test
    public void testSDTCoKhoangGiua(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "0913 254 323";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(0, actual);
    }
    
    @Test
    public void testSDTToan0(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "0000000000";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(2, actual);
    }
    
    @Test
    public void testSDTTDau00(){
        UtilsDao utils = new UtilsDao();
        
        String phoneNumber = "0014923678";
        int actual = utils.checkNumber(phoneNumber);
        Assert.assertEquals(2, actual);
    }
    
    // Test Nhập firstname
    @Test
    public void testNhapDungFirstName(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Thao";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(0, actual);
    }
    
    @Test
    public void testNhapFirstNameCoKhoangTrangGiua(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "T h a o";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    @Test
    public void testNhapFirstNameCoKhoangTrangDau(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "            Thao";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    @Test
    public void testNhapFirstNameCoKhoangTrangCuoi(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Thao                           ";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    @Test
    public void testNhapFirstNameChuaKiTuDacBiet(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Thao@!!";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    @Test
    public void testNhapFirstNameQuaDai(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "ThaoTHaoTHaoThaoThaoTHaoTHao";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(-1, actual);
    }
    
    @Test
    public void testNhapFirstNameBỉRong(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(2, actual);
    }
    
    @Test
    public void testNhapFirstNameChuaSo(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "1234567";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    @Test
    public void testNhapFirstNameChuaSoVaChu(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Thao1234567";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    //test Nhập lastname
    @Test
    public void testNhapDungLastName(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Luong";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(0, actual);
    }
    
    @Test
    public void testNhapLastNameCoKhoangTrang(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "L u o n g";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    @Test
    public void testNhapLastNameCoKhoangTrangDau(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "          Luong";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    @Test
    public void testNhapLastNameCoKhoangTrangCuoi(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Luong             ";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    
    @Test
    public void testNhapLastNameChuaKiTuDacBiet(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Luong@!!";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    @Test
    public void testNhapLastNameQuaDai(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "Luonggggggggggggg";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(-1, actual);
    }
    
    @Test
    public void testNhapLastNameBỉRong(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(2, actual);
    }
    
    @Test
    public void testNhapLastNameChuaSo(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "1234567";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
    
    
    @Test
    public void testNhapLastNameChuaSoVaCHu(){
        UtilsDao utils = new UtilsDao();
        
        String firstName = "ThaoThao1234567";
        int actual = utils.checkFirstnameLastName(firstName);
        Assert.assertEquals(1, actual);
    }
        // test add customers 4 test case
    @Test
    public void testTatCaThongTinRong(){
        UtilsDao utils = new UtilsDao();
        String firstName = ""; 
        String lastName = "";
        String dateOfBirth = "";
        String gender = "";
        String phoneNumber = "";
        String address = "";
        String email = "";
        
        int actual = utils.checkSaveCustomers(firstName, lastName, dateOfBirth,
                gender, phoneNumber, address, email);
        Assert.assertEquals(2, actual); // 2 rong
        
    }
    
    @Test
    public void testNhapThieuMotThongTin(){
        UtilsDao utils = new UtilsDao();
        String firstName = "vo"; 
        String lastName = "vi";
        String dateOfBirth = "1998-08-26";
        String gender = "nu";
        String phoneNumber = "0857727702";
        String address = "dsffd";
        String email = "";
        
        int actual = utils.checkSaveCustomers(firstName, lastName, dateOfBirth,
                gender, phoneNumber, address, email);
        Assert.assertEquals(2, actual); // 2 rong
        
    }
    
    @Test
    public void testTrungEmail(){
        UtilsDao utils = new UtilsDao();
        String firstName = "Thao"; 
        String lastName = "Thao";
        String dateOfBirth = "1998-08-26";
        String gender = "nu";
        String phoneNumber = "0857727702";
        String address = "dsffd";
        String email = "thao@gmail.com";
        
        int actual = utils.checkSaveCustomers(firstName, lastName, dateOfBirth,
                gender, phoneNumber, address, email);
        
        
        Assert.assertEquals(-1, actual); // email trung
        
    }
    
    @Test
    public void testSaveThanhCong(){
        UtilsDao utils = new UtilsDao();
        String firstName = "vo"; 
        String lastName = "vi";
        String dateOfBirth = "1998-08-26";
        String gender = "nu";
        String phoneNumber = "0857727702";
        String address = "dsffd";
        String email = "ffghf@gmail.com";
        
        int actual = utils.checkSaveCustomers(firstName, lastName, dateOfBirth,
                gender, phoneNumber, address, email);
        Assert.assertEquals(0, actual); // email trung
        
    }
    
    //Test Delete Customers 3 test case
    @Test
    public void testDeleteCustomersKhongCoId(){
        UtilsDao utils = new UtilsDao();
        String id = "fdfhf";
        
        boolean actual = utils.deleteCustomer(id);
        Assert.assertEquals(false, actual); // k co id trong csdl
        
    }
    
    @Test
    public void testDeleteCustomersSuccessfull(){
        UtilsDao utils = new UtilsDao();
        String id = "9573714d-3240-47f9-96cf-54e8e0ef761f";
        
        boolean actual = utils.deleteCustomer(id);
        Assert.assertEquals(true, actual); // xoa thanh cong
        
    }
    
  @Test
    public void testDeleteCustomersIdNull(){
        UtilsDao utils = new UtilsDao();
        String id = "";
        
        boolean actual = utils.deleteCustomer(id);
        Assert.assertEquals(false, actual); // xoa false
        
    }  
    
    //test Update customers
    @Test
    public void testUpdateIdNull(){
        UtilsDao utils = new UtilsDao();
        String id = "";
        String fn = "Nguyễn";
        String ln = "Thảo";
        String phone = "0123654789";
        String email = "1@gmail.com";
        String address = "nguyễn oanh";
        String gender = "Female";
        String ns = "1998-11-08";        
        
        boolean actual = utils.addOrUpdateCustomer(id, fn,ln, ns, phone, email,
                gender, address);
        Assert.assertEquals(false, actual); // Update false
        
    }
    

    
    @Test
    public void testUpdateKhongCoId(){
        UtilsDao utils = new UtilsDao();
        String id = "d2f5f28a6";
        String fn = "Nguyễn";
        String ln = "Thảo";
        String phone = "0123654789";
        String email = "1@gmail.com";
        String address = "nguyễn oanh";
        String gender = "Female";
        String ns = "1998-11-08";        
        
        boolean actual = utils.addOrUpdateCustomer(id, fn,ln, ns, phone, email,
                gender, address);
        Assert.assertEquals(false, actual); // Update false
        
    }
    
    //test tra cuu
    @Test
    public void testFind(){
        UtilsDao utils = new UtilsDao();
        
        String fn = "n";
             
        
        List<Customers> actual = utils.getCustomer(fn);
        
        Assert.assertEquals(1, actual.size()); // có hai ng v
        
    }
    
}
