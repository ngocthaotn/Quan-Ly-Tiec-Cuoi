/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;


import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table (name = "dateofbooking")
public class DateOfBooking {
    @Id
    @Column (name = "date_id")
    private String dateId;
    
    @Column (name = "dateAction")
    private String dateAction;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "hall_id")
    private BanquetHall hallId;

    public DateOfBooking(){
        
    }
    
    public DateOfBooking(String id, String n, BanquetHall h){
        this.dateId = id;
        this.dateAction = n;
        this.hallId = h;
    }

    @Override
    public String toString() {
        return this.dateAction;
    }
    
    
    /**
     * @return the dateId
     */
    public String getDateId() {
        return dateId;
    }

    /**
     * @param dateId the dateId to set
     */
    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    /**
     * @return the dateAction
     */
    public String getDateAction() {
        return dateAction;
    }

    /**
     * @param dateAction the dateAction to set
     */
    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
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
}
