/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gateway;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.database.DB;
import org.data.Account;
import org.data.Client;
/**
 *
 * @author Sorin
 */
public class ClientGateway {

    public Client readUser(int ID){
        Client user = new Client();
        try {
            ResultSet readUser;
            StringBuilder query = new StringBuilder();
            query.append("select * from Clients where IDclient = '");
            query.append(ID);
            query.append("';");
            readUser = DB.getDBInstance().executeQuery(query.toString());
            readUser.first();
            user.setIDclient(readUser.getInt("IDclient"));
            user.setFirstName(readUser.getString("firstName"));
            user.setLastName(readUser.getString("lastName"));
            user.setIDcardNumber(readUser.getInt("IDcardNumber"));
            user.setPersonalNumericalCode(readUser.getLong("personalNumericalCode"));
            user.setAddress(readUser.getString("address"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(ClientGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public Client readUserByPersCode(long persNumCode){
        Client user = new Client();
        try {
            ResultSet readUser;
            StringBuilder query = new StringBuilder();
            query.append("select * from Clients where personalNumericalCode = '");
            query.append(persNumCode);
            query.append("';");
            readUser = DB.getDBInstance().executeQuery(query.toString());
            readUser.first();
            user.setIDclient(readUser.getInt("IDclient"));
            user.setFirstName(readUser.getString("firstName"));
            user.setLastName(readUser.getString("lastName"));
            user.setIDcardNumber(readUser.getInt("IDcardNumber"));
            user.setPersonalNumericalCode(readUser.getLong("personalNumericalCode"));
            user.setAddress(readUser.getString("address"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(ClientGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void transferMoney(Account fromAccount, Account toAccount, int amount){
        if(fromAccount.getAmountMoney() >= amount){
            AccountGateway accountGate = new AccountGateway();
            int tempMoney;
            tempMoney = fromAccount.getAmountMoney();
            fromAccount.setAmountMoney(tempMoney - amount);
            tempMoney = toAccount.getAmountMoney();
            toAccount.setAmountMoney(tempMoney + amount);
            accountGate.updateAccount(fromAccount);
            accountGate.updateAccount(toAccount);

            TransferGateway transferGate = new TransferGateway();
            transferGate.registerTransfer(fromAccount.getIDclient(), fromAccount.getIDaccount(), toAccount.getIDaccount(), amount);
        }
    }
    
    public void payUtilitiesBill (Account account,String utility, int amount){
        if(account.getAmountMoney() >= amount){
            int tempMoney;
            tempMoney = account.getAmountMoney();
            AccountGateway accountGate = new AccountGateway();
            account.setAmountMoney(tempMoney - amount);
            accountGate.updateAccount(account);
            
            TransferGateway transferGate = new TransferGateway();
            transferGate.registerTransfer(account.getIDclient(), account.getIDaccount(), utility, amount);
        }
    }
    
    public void updateUser(Client user){
        
        StringBuilder query = new StringBuilder();
        query.append("update Clients set firstName='");
        query.append(user.getFirstName());
        query.append("',lastName='");
        query.append(user.getLastName());
        query.append("',IDcardNumber='");
        query.append(Integer.toString(user.getIDcardNumber()));
        query.append("',personalNumericalCode='");
        query.append(Long.toString(user.getPersonalNumericalCode()));
        query.append("',address='");
        query.append(user.getAddress());
        query.append("' where IDclient='");
        query.append(Integer.toString(user.getIDclient()));
        query.append("';");
        DB.getDBInstance().executeUpdate(query.toString());
        
        query = new StringBuilder();
        query.append("update UserAccounts set userName='");
        query.append(user.getUsername());
        query.append("',password='");
        query.append(user.getPassword());
        query.append("',userType='");
        query.append(user.getUserType());
        query.append("' where ID='");
        query.append(Integer.toString(user.getIDclient()));
        query.append("';");
        DB.getDBInstance().executeUpdate(query.toString());

    }
}
