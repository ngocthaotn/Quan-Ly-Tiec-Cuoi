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
@Table (name = "customers")
public class Customers {
    @Id
    @Column (name = "customer_id")
    private String CustomerId;
    @Column (name = "firstName")
    private String firstName;
    @Column (name = "lastName")
    private String lastName;
    @Column (name = "dateOfBirth")
    private Date dateOfBirth;
    @Column (name = "gender")
    private String gender;
    @Column (name = "phoneNumber")
    private String phoneNumber;
    @Column (name = "email")
    private String email;
    @Column (name = "address")
    private String address;
    @Column (name = "openingDate")
    private Date openingDate;
    
    public Customers(){
        
    }
    
    public Customers(String id, String fn, String ln, Date ns, String gt,
            String sdt, String email,
            String address){
        this.CustomerId = id;
        this.firstName = fn;
        this.lastName = ln;
        this.dateOfBirth = ns;
        this.gender = gt;
        this.phoneNumber = sdt;
        //this.userName = userName;
        //this.password = pas;
        this.email = email;
        this.address = address;
        this.openingDate = new Date();
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }

    

    /**
     * @return the peopleId
     */
    public String getCustomerId() {
        return CustomerId;
    }

    /**
     * @param people_id the people_id to set
     */
    public void setCustomerId(String customerId) {
        this.CustomerId = customerId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the openingDate
     */
    public Date getOpeningDate() {
        return openingDate;
    }

    /**
     * @param openingDate the openingDate to set
     */
    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
}
