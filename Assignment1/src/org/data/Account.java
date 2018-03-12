/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.data;

import java.util.Date;

/**
 *
 * @author Sorin
 */
public class Account {
    public enum ACCTYPE{
        RON, EUR;
    }
    private int IDaccount;
    private ACCTYPE accType;
    private int amountMoney;
    private Date date;
    private int IDclient;

    public int getIDclient() {
        return IDclient;
    }

    public void setIDclient(int IDclient) {
        this.IDclient = IDclient;
    }

    public int getIDaccount() {
        return IDaccount;
    }

    public void setIDaccount(int id) {
        this.IDaccount = id;
    }

    public ACCTYPE getAccType() {
        return accType;
    }

    public void setAccType(ACCTYPE accType) {
        this.accType = accType;
    }

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
