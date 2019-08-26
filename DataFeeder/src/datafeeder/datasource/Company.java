/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datafeeder.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Adam
 */

@Entity (name = "COMPANY_TABLE")
public class Company 
{
    @Id
    private long ico;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    private String address;
    private String employeeEmail;

    
    
    
    //getters + setters
    public long getIco() {
        return ico;
    }

    public void setIco(long ico) {
        this.ico = ico;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    
    
    
    
    
    
}
