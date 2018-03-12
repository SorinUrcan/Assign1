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
import org.data.Client;

/**
 *
 * @author Sorin
 */
public class AdminGateway extends ClientGateway{
    public List<Client> readAllUsers(){
        List<Client> users = new ArrayList<>();
        try {
            ResultSet readUser;
            StringBuilder query = new StringBuilder();
            query.append("select * from Clients;");
            readUser = DB.getDBInstance().executeQuery(query.toString());
            while (readUser.next()){
                Client user = new Client();
                user.setIDclient(readUser.getInt("IDclient"));
                user.setFirstName(readUser.getString("firstName"));
                user.setLastName(readUser.getString("lastName"));
                user.setIDcardNumber(readUser.getInt("IDcardNumber"));
                user.setPersonalNumericalCode(readUser.getLong("personalNumericalCode"));
                user.setAddress(readUser.getString("address"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public Client createUser(Client user){
        
        StringBuilder query = new StringBuilder();
        query.append("insert into Clients (firstName, lastName, IDcardNumber, personalNumericalCode, address)  values");
        query.append(" ('");
        query.append(user.getFirstName());
        query.append("','");
        query.append(user.getLastName());
        query.append("','");
        query.append(Integer.toString(user.getIDcardNumber()));
        query.append("','");
        query.append(Long.toString(user.getPersonalNumericalCode()));
        query.append("','");
        query.append(user.getAddress());
        query.append("');");
        DB.getDBInstance().executeUpdate(query.toString());
        Client clientCreated;
        ClientGateway clientGate = new ClientGateway();
        clientCreated = clientGate.readUserByPersCode(user.getPersonalNumericalCode());
        int id = clientCreated.getIDclient();
        
        query = new StringBuilder();
        query.append("insert into UserAccounts (ID, userName, password, userType) values ('");
        query.append(id);
        query.append("','");
        query.append(user.getUsername());
        query.append("','");
        query.append(user.getPassword());
        query.append("','");
        query.append(user.getUserType());
        query.append("');");
        DB.getDBInstance().executeUpdate(query.toString());
        return readUser(id);
    }
    
    public void deleteUser(int IDuser){        
        StringBuilder query = new StringBuilder();
        query.append("delete from Clients where IDclient = '");
        query.append(Integer.toString(IDuser));
        query.append("';");
        
        DB.getDBInstance().executeUpdate(query.toString());
        
        query = new StringBuilder();
        query.append("delete from UserAccounts where ID = '");
        query.append(Integer.toString(IDuser));
        query.append("';");
        DB.getDBInstance().executeUpdate(query.toString());
        
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
