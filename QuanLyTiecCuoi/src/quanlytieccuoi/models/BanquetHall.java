/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author PC
 */
@Entity
@Table (name = "banquethall")
public class BanquetHall {

    @Id
    @Column (name = "hallID")
    private String id;
    
    
    @Column (name = "name")
    private String name;
    @Column (name = "acreage")
    private String acreage;
    @Column (name = "maxPeople")
    private String maxPeople;
    @Column (name = "price")
    private String price;
    @Column (name = "image")
    private String image;
    @Column (name = "openDate")
    private Date openDate;
    @Column (name = "status")
    private String status;
    
    public BanquetHall(){
        
    }
    
    public BanquetHall(String id, String name, String ac, String max,
            String pric, String image){
        this.id = id;
        this.name = name;
        this.acreage = ac;
        this.maxPeople = max;
        this.price = pric;
        this.image = image;
        this.openDate = new Date();
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
    
    
    /**
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param ID the ID to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the acreage
     */
    public String getAcreage() {
        return acreage;
    }

    /**
     * @param acreage the acreage to set
     */
    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    /**
     * @return the maxPeople
     */
    public String getMaxPeople() {
        return maxPeople;
    }

    /**
     * @param maxPeople the maxPeople to set
     */
    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
