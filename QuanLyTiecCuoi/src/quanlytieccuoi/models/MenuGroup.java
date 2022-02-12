/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import javax.persistence.*;

/**
 *
 * @author HP
 */
@Entity
@Table (name = "menugroup")
public class MenuGroup {

    @Id
    @Column (name = "ID")
    private String id;
    
    
    @Column (name = "name")
    private String mName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public MenuGroup(){
        
    }
    
    public MenuGroup(String id, String name){
        this.id = id;
        this.mName = name;
    }

    @Override
    public String toString() {
        return this.getmName();
    }

    /**
     * @return the mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
