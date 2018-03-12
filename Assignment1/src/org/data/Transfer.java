/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.data;

import java.sql.Date;

/**
 *
 * @author Sorin
 */
public class Transfer {
    int amountTransfered;
    int IDclient;
    int fromAccountID;
    String toAccountID;
    Date date;

    public int getAmountTransfered() {
        return amountTransfered;
    }

    public void setAmountTransfered(int amountTransfered) {
        this.amountTransfered = amountTransfered;
    }

    public int getIDclient() {
        return IDclient;
    }

    public void setIDclient(int IDclient) {
        this.IDclient = IDclient;
    }

    public int getFromAccountID() {
        return fromAccountID;
    }

    public void setFromAccountID(int fromAccountID) {
        this.fromAccountID = fromAccountID;
    }

    public String getToAccountID() {
        return toAccountID;
    }

    public void setToAccountID(String toAccountID) {
        this.toAccountID = toAccountID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
   
}
