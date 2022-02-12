/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table (name = "admin")
public class Admin {
    //@Column (name = "admin_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "admin_id")
    private String adminId;
        
    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "adminUser", cascade = CascadeType.ALL)
    private  Users userAdmin;
    
    @Column (name = "firstName")
    private String firstName;
    
    @Column (name = "lastName")
    private String lastName;
    
    @Column (name = "dateOfBirth")
    private String dateOfBirth;
            
    @Column (name = "gender")
    private String gender;
    
    @Column (name = "phoneNumber")
    private String phoneNumber;
    
    @Column (name = "email")
    private String email;
    
    @Column (name = "address")
    private String address;
    
    @Column (name = "openingDate")
    private String openingDate;
    
    @Column (name = "qualification")
    private  String qualification;
    
    @Column (name = "startingDate")
    private  String startingDate;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "positions")
    private  Positions positions;
    
    
    public Admin(){
        
    }
    
    public Admin(String id, String fn, String ln, String ns, String gt,
            String sdt, String email, String qua, Positions posi, String sta,
           String address, Users userAdmin){
        this.adminId = id;
        this.firstName = fn;
        this.lastName = ln;
        this.dateOfBirth = ns;
        this.gender = gt;
        this.phoneNumber = sdt;
        this.userAdmin = userAdmin;
        this.email = email;
        this.address = address;
        Date p = new Date();
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        String pd = sf1.format(p);
        this.openingDate = pd;
        this.positions = posi;
        this.qualification = qua;
        this.startingDate = sta;
    }
    
    
    

    /**
     * @return the adminId
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
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
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
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
    public String getOpeningDate() {
        return openingDate;
    }

    /**
     * @param openingDate the openingDate to set
     */
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    /**
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * @return the startingDate
     */
    public String getStartingDate() {
        return startingDate;
    }

    /**
     * @param startingDate the startingDate to set
     */
    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * @return the position
     */
    public Positions getPositions() {
        return positions;
    }

    /**
     * @param position the position to set
     */
    public void setPositions(Positions positions) {
        this.positions = positions;
    }
    /**
     * @return the userAdmin
     */
    public Users getUserAdmin() {
        return userAdmin;
    }

    /**
     * @param userAdmin the userAdmin to set
     */
    public void setUserAdmin(Users userAdmin) {
        this.userAdmin = userAdmin;
    }
    
    
}
