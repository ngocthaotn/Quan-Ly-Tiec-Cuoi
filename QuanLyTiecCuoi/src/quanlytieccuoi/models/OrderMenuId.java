/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
/**
 *
 * @author PC
 */
@Embeddable
public class OrderMenuId implements Serializable{
    @ManyToOne(cascade = CascadeType.ALL)
    private Orders orders;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private FoodDrinkService fds;

    /**
     * @return the orders
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    /**
     * @return the fds
     */
    public FoodDrinkService getFds() {
        return fds;
    }

    /**
     * @param fds the fds to set
     */
    public void setFds(FoodDrinkService fds) {
        this.fds = fds;
    }
    
}
