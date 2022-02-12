/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
/**
 *
 * @author PC
 */
@Entity
@Table (name = "orders", catalog = "quanlytieccuoi")
public class Orders {

    
    
    @Id
    @Column (name = "orderId")
    private String orderId;
    @OneToMany(mappedBy = "primaryKey.orders",
            cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetail = new HashSet<OrderDetail>();
//    @ManyToMany
//    private List<OrderDetail> fdsList = new ArrayList<>();
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "customerID")
    private Customers customerId;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "hallID")
    private BanquetHall hallId;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "dateID")
    private DateOfBooking dateId;
    
    @Column (name = "orderDate")
    private Date orderDate;
    
    @Column (name = "totalMoney")
    private String totalMoney;

    @Column (name = "timeOfDay")
    private int timeOfDay;
    @Column (name = "payment")
    private int payment;
    public Orders(){
        
    }
    
    public  Orders(String orId,Customers cusId,
            BanquetHall hallId,  DateOfBooking d,int to, int t){
        this.orderId = orId;
        this.customerId = cusId;
        this.hallId = hallId;
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        this.orderDate = new Date();
        this.dateId = d;
        this.totalMoney = String.format("%d", to);
        this.timeOfDay = t;
    }
    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    /**
     * @return the payment
     */
    public int getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */

    /**
     * @return the customerId
     */
    public Customers getCustomerId() {
        return customerId;
    }
    public void setPayment(int payment) {
        this.payment = payment;
    }
    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the hallId
     */
    public BanquetHall getHallId() {
        return hallId;
    }

    /**
     * @param hallId the hallId to set
     */
    public void setHallId(BanquetHall hallId) {
        this.hallId = hallId;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the totalPayableAmount
     */
    public String getTotalMoney() {
        return totalMoney;
    }

    /**
     * @param totalMoney the totalPayableAmount to set
     */
    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    
    /**
     * @return the dateId
     */
    public DateOfBooking getDateId() {
        return dateId;
    }

    /**
     * @param dateId the dateId to set
     */
    public void setDateId(DateOfBooking dateId) {
        this.dateId = dateId;
    }

    
    /**
     * @return the timeOfDay
     */
    public int getTimeOfDay() {
        return timeOfDay;
    }

    /**
     * @param timeOfDay the timeOfDay to set
     */
    public void setTimeOfDay(int timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    /**
     * @return the orderDetail
     */
    public Set<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    /**
     * @param orderDetail the orderDetail to set
     */
    public void setOrderDetail(Set<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    
    
}
