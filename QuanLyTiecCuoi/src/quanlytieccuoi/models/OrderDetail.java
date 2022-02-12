/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import java.util.Objects;
import javax.persistence.*;
/**
 *
 * @author PC
 */
@Entity
@Table(name = "orderdetail")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.orders",
        joinColumns = @JoinColumn(name = "order_id")),
    @AssociationOverride(name = "primaryKey.fds",
        joinColumns = @JoinColumn(name = "maSFD")) })
public class OrderDetail {
    //composite-id key
    @EmbeddedId
    private OrderMenuId primaryKey = new OrderMenuId();
    
    //additional fields
    private int quantity;
    private String unitPrice;
    private double discount;

    /**
     * @return the primaryKey
     */
    
    public OrderMenuId getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey the primaryKey to set
     */
    public void setPrimaryKey(OrderMenuId primaryKey) {
        this.primaryKey = primaryKey;
    }
    @Transient
    public Orders getOrders(){
        return getPrimaryKey().getOrders();
    }
    
    public void setOrders(Orders orders){
        getPrimaryKey().setOrders(orders);
    }
    @Transient
    public FoodDrinkService getFds(){
        return getPrimaryKey().getFds();
    }
    
    public void setFds(FoodDrinkService fds){
        getPrimaryKey().setFds(fds);
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitPrice
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
