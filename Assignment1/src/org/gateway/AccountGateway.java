/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.database.DB;
import org.data.Account;

/**
 *
 * @author Sorin
 */
public class AccountGateway {
    
    public List<Account> readAccounts(int IDclient){
        List<Account> accounts = new ArrayList<>();
        ResultSet queryResult;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select * from clientAccounts where IDclient='");
            query.append(IDclient);
            query.append("';");
            queryResult = DB.getDBInstance().executeQuery(query.toString());
            while(queryResult.next()){
                Account cont = new Account();
                cont.setIDclient(IDclient);
                cont.setIDaccount(queryResult.getInt("IDaccount"));
                cont.setAmountMoney(queryResult.getInt("ammountMoney"));
                String accountType = queryResult.getString("accountType");
                if(accountType.equals("ron"))
                    cont.setAccType(Account.ACCTYPE.RON);
                else
                    cont.setAccType(Account.ACCTYPE.EUR);
                cont.setDate(queryResult.getDate("dateCreated"));
                
                accounts.add(cont);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }
    
    public Account readAccount(int IDaccount){
        Account account = new Account();
        try {
            ResultSet res;
            StringBuilder query = new StringBuilder();
            query.append("select * from clientAccounts where IDaccount='");
            query.append(IDaccount);
            query.append("';");
            res = DB.getDBInstance().executeQuery(query.toString());
            res.first();
            account.setIDclient(res.getInt("IDclient"));
            account.setAmountMoney(res.getInt("ammountMoney"));
            account.setIDaccount(IDaccount);
            account.setDate(res.getDate("dateCreated"));
            if(res.getString("accountType").equals("ron")){
                account.setAccType(Account.ACCTYPE.RON);
            }
            else{
                account.setAccType(Account.ACCTYPE.EUR);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    
    public void createAccount(Account cont, int IDclient){
        StringBuilder query = new StringBuilder();
        query.append("insert into clientAccounts (IDaccount,accountType,ammountMoney,IDclient,dateCreated) values ('");
        query.append(cont.getIDaccount());
        query.append("','");
        if(cont.getAccType() == Account.ACCTYPE.RON){
            query.append("ron");
        }
        else{
            query.append("eur");
        }
        query.append("','");
        query.append(cont.getAmountMoney());
        query.append("','");
        query.append(IDclient);
        query.append("','");
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        query.append(sqlDate);
        query.append("');");
        DB.getDBInstance().executeUpdate(query.toString());
    }
    
    public void updateAccount(Account newCont){
        StringBuilder query = new StringBuilder();
        query.append("update clientAccounts set accountType='");
        if(newCont.getAccType() == Account.ACCTYPE.RON){
            query.append("ron");
        }
        else{
            query.append("eur");
        }
        query.append("', ammountMoney='");
        query.append(newCont.getAmountMoney());
        query.append("' where IDaccount='");
        query.append(newCont.getIDaccount());
        query.append("';");
        DB.getDBInstance().executeUpdate(query.toString());
    }
    
    public void deleteAccount(int IDaccount){
        StringBuilder query = new StringBuilder();
        query.append("delete from clientAccounts where IDaccount='");
        query.append(IDaccount);
        query.append("';");
        DB.getDBInstance().executeUpdate(query.toString());
    }
}
