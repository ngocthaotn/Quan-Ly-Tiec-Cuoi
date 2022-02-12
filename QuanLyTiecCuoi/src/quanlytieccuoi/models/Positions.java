/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table (name = "positions")
public class Positions {
    
    @Id
    @Column (name = "position_id")
    private String positionId;
    
    @Column (name = "positionName")
    private String pName;
    //@OneToMany (mappedBy = "positions")
    //private List<Admin> listAdmin;
    
    public Positions(){
        
    }
    
    public Positions(String id, String t){
        this.positionId = id;
        this.pName = t;
    }

    @Override
    public String toString() {
        return this.pName;
    }
    

    /**
     * @return the positionId
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * @param positionId the positionId to set
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }


    /**
     * @return the pName
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName the pName to set
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

    

}
