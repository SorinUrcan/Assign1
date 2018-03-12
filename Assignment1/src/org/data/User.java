/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.data;

import java.util.List;

/**
 *
 * @author Sorin
 */
public abstract class User {
    protected int IDclient;
    protected String firstName;
    protected String lastName;
    protected int IDcardNumber;
    protected long personalNumericalCode;
    protected String address;
    protected String userType;
    protected List<Account> account;
    
    public int getIDclient() {
        return IDclient;
    }

    public void setIDclient(int IDclient) {
        this.IDclient = IDclient;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIDcardNumber() {
        return IDcardNumber;
    }

    public void setIDcardNumber(int IDcardNumber) {
        this.IDcardNumber = IDcardNumber;
    }

    public long getPersonalNumericalCode() {
        return personalNumericalCode;
    }

    public void setPersonalNumericalCode(long personalNumericalCode) {
        this.personalNumericalCode = personalNumericalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public void setCont(List<Account> cont){
        this.account = cont;
    }
    
    public List<Account> getCont(){
        return this.account;
    }

   

}
