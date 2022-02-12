/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.TextField;
import javax.persistence.*;

/**
 *
 * @author HP
 */
@Entity
@Table (name = "servicefooddrink", catalog = "quanlytieccuoi")
public class FoodDrinkService {

    
    @Id
    @Column (name = "ID")
    private String ID;
    
    @Column (name = "name")
    private String name;
    
    @Column (name = "image")
    private String image;
    
    @Column (name = "unit")
    private String unit;
    
    @Column (name = "description")
    private String descri;
    
    @Column (name = "price")
    private String price;
    @Column (name = "unitPrice")
    private String unitPrice;
    @Column (name = "amount")
    private int amount;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "IDType")
    private  MenuGroup IDType;
    
    @OneToMany(mappedBy = "primaryKey.fds",
            cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetail = new HashSet<OrderDetail>();
    
   
    public FoodDrinkService(){
        
    }
    public FoodDrinkService(String ID,String name,String im, String unit,String descri,
            int price, MenuGroup typeId){
        this.ID = ID;
        this.name=name;
        this.image = im;
        this.unit= unit;
        this.descri = descri;
        this.price= String.format("%,d", price);
        this.IDType = typeId;
    }
    
    public void addOrders(OrderDetail od){
        this.orderDetail.add(od);
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the descri
     */
    public String getDescri() {
        return descri;
    }

    /**
     * @param descri the descri to set
     */
    public void setDescri(String descri) {
        this.descri = descri;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     * @return the IDType
     */
    public MenuGroup getIDType() {
        return IDType;
    }

    /**
     * @param IDType the IDType to set
     */
    public void setIDType(MenuGroup IDType) {
        this.IDType = IDType;
    } 
    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
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
}
