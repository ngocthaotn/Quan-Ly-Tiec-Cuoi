/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import quanlytieccuoi.models.Admin;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.Customers;
import quanlytieccuoi.models.DateOfBooking;
import quanlytieccuoi.models.Positions;
import quanlytieccuoi.models.Users;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.MenuGroup;
import quanlytieccuoi.models.OrderDetail;
import quanlytieccuoi.models.OrderMenuId;
import quanlytieccuoi.models.Orders;
/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author PC
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;
    
    static {
        Configuration cfgs = new Configuration();
        cfgs.configure("hibernate.cfg.xml");
        
        cfgs.addAnnotatedClass(Users.class);
        cfgs.addAnnotatedClass(Customers.class);
        cfgs.addAnnotatedClass(Positions.class);
        cfgs.addAnnotatedClass(Admin.class);
        cfgs.addAnnotatedClass(MenuGroup.class);
        cfgs.addAnnotatedClass(FoodDrinkService.class);
        cfgs.addAnnotatedClass(BanquetHall.class);
        cfgs.addAnnotatedClass(Orders.class);
        cfgs.addAnnotatedClass(DateOfBooking.class);
        cfgs.addAnnotatedClass(OrderMenuId.class);
        cfgs.addAnnotatedClass(OrderDetail.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfgs.getProperties());
        FACTORY = cfgs.buildSessionFactory(builder.build());
    }
    
    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
