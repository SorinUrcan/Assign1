/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gateway;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.database.DB;
import org.data.Transfer;

/**
 *
 * @author Sorin
 */
public class TransferGateway {
    public void registerTransfer(int IDclient, int fromAccountID, String utility, int amountMoney){
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        StringBuilder query = new StringBuilder();
        query.append("insert into transactionHistory (IDclient,fromAccount,toAccount,date,ammountTransfered) values ('");
        query.append(IDclient);
        query.append("','");
        query.append(fromAccountID);
        query.append("','");
        query.append(utility);
        query.append("','");
        query.append(sqlDate);
        query.append("','");
        query.append(amountMoney);
        query.append("');");
        DB.getDBInstance().executeUpdate(query.toString());
    }
    
    public void registerTransfer(int IDclient, int fromAccountID, int toAccountID, int amountMoney){
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String toAccID;
        toAccID = Integer.toString(toAccountID);
        
        StringBuilder query = new StringBuilder();
        query.append("insert into transactionHistory (IDclient,fromAccount,toAccount,date,ammountTransfered) values ('");
        query.append(IDclient);
        query.append("','");
        query.append(fromAccountID);
        query.append("','");
        query.append(toAccID);
        query.append("','");
        query.append(sqlDate);
        query.append("','");
        query.append(amountMoney);
        query.append("');");
        DB.getDBInstance().executeUpdate(query.toString());
    }
    
    public List<Transfer> readTransfers(int IDclient, Date sqlDate){
        List<Transfer> transfers = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("select * from transactionHistory where IDclient='");
            query.append(Integer.toString(IDclient));
            query.append("' and date>='");
            query.append(sqlDate);
            query.append("';");
            ResultSet res = DB.getDBInstance().executeQuery(query.toString());
            while(res.next()){
                Transfer transfer = new Transfer();
                transfer.setIDclient(IDclient);
                transfer.setFromAccountID(res.getInt("fromAccount"));
                transfer.setToAccountID(res.getString("toAccount"));
                transfer.setDate(res.getDate("date"));
                transfer.setAmountTransfered(res.getInt("ammountTransfered"));
                transfers.add(transfer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransferGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transfers;
    }
    
    public List<Transfer> readTransfers(int IDclient){
        List<Transfer> transfers = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("select * from transactionHistory where IDclient='");
            query.append(Integer.toString(IDclient));
            query.append("';");
            ResultSet res = DB.getDBInstance().executeQuery(query.toString());
            while(res.next()){
                Transfer transfer = new Transfer();
                transfer.setIDclient(IDclient);
                transfer.setFromAccountID(res.getInt("fromAccount"));
                transfer.setToAccountID(res.getString("toAccount"));
                transfer.setDate(res.getDate("date"));
                transfer.setAmountTransfered(res.getInt("ammountTransfered"));
                transfers.add(transfer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransferGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transfers;
    }
}
