/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author PC
 */
@Entity
@Table (name = "users")
public class Users {
    @GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "adminUser"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column (name = "ID")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Admin adminUser;
    
    @Column (name = "userName")
    private String userName;
    
    @Column (name = "password")
    private  String password;
    
    public Users(){
        
    }
    public Users(Admin admin, String us, String pass){
        this.adminUser = admin;
        this.userName = us;
        this.password = pass;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the adminUser
     */
    public Admin getAdminUser() {
        return adminUser;
    }

    /**
     * @param adminUser the adminUser to set
     */
    public void setAdminUser(Admin adminUser) {
        this.adminUser = adminUser;
    }
}
