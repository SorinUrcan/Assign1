/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.presentation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.database.DB;
import org.gateway.AdminGateway;
import org.gateway.AccountGateway;
import org.gateway.ClientGateway;
import org.gateway.TransferGateway;
import org.data.Client;
import org.data.Account;
import org.data.Transfer;
/**
 *
 * @author Sorin
 */
public class UInterf {
    static UserInterface UI;
    
    String username;
    String password;
    String userType;
    int IDclient;
    
    protected void drawClientAccountsListing(List<Account> accounts){
        StringBuilder output = new StringBuilder();
        output.append("Account ID | Account Type | Amount $ | Date created\n");
        for (Account account : accounts) {
            output.append(account.getIDaccount());
            output.append("  ");
            output.append(account.getAccType());
            output.append("  ");
            output.append(account.getAmountMoney());
            output.append("  ");
            output.append(account.getDate());
            output.append("\n");
        }
        UI.setClientAccountsListing(output.toString());
    }
    
    protected void redrawClientAccountsListing(){
        List<Account> clientAccounts;
        AccountGateway clientAccountsGate = new AccountGateway();
        clientAccounts = clientAccountsGate.readAccounts(this.IDclient);
        this.drawClientAccountsListing(clientAccounts);
    }
    
    protected void drawAdminClientsListing(List<Client> clients){
        StringBuilder output = new StringBuilder();
        output.append("Client ID | first name | last name | ID card no. | CNP | address\n");
        for (Client client : clients) {
            output.append(client.getIDclient());
            output.append("  ");
            output.append(client.getFirstName());
            output.append("  ");
            output.append(client.getLastName());
            output.append("  ");
            output.append(client.getIDcardNumber());
            output.append("  ");
            output.append(client.getPersonalNumericalCode());
            output.append("  ");
            output.append(client.getAddress());
            output.append("\n");
        }
        UI.setAdminClientsListing(output.toString());
    }
    
    protected void redrawAdminClientsListing(){
        AdminGateway adminGate = new AdminGateway();
        List<Client> allClients;
        allClients = adminGate.readAllUsers();
        this.drawAdminClientsListing(allClients);
    }
    
    protected void drawAdminClientAccountsListing(List<Account> accounts){
        StringBuilder output = new StringBuilder();
        output.append("Account ID | Account Type | Amount $ | Date created\n");
        for (Account account : accounts) {
            output.append(account.getIDaccount());
            output.append("  ");
            output.append(account.getAccType());
            output.append("  ");
            output.append(account.getAmountMoney());
            output.append("  ");
            output.append(account.getDate());
            output.append("\n");
        }
        UI.setAdminClientAccountsListing(output.toString());
    }
    
    protected void redrawAdminClientAccountsListing(){
        AccountGateway contGate = new AccountGateway();
        List<Account> clientAccounts;
        clientAccounts = contGate.readAccounts(IDclient);
        this.drawAdminClientAccountsListing(clientAccounts);
    }
    
    protected void printToFile(List<Transfer> transfers){
        BufferedWriter fileOutput = null;
        try {
            File file = new File("transferReport.txt");
            file.canWrite();
            fileOutput = new BufferedWriter(new FileWriter(file));
            fileOutput.write("Transactions:\nFrom Account | To Account | Date | Amount Transfered");
            fileOutput.newLine();
            for(Transfer transfer:transfers){
                fileOutput.write(Integer.toString(transfer.getFromAccountID()) + "  " + transfer.getToAccountID() + "  " + transfer.getDate() + "  " + Integer.toString(transfer.getAmountTransfered()));
                fileOutput.newLine();
            }            
        } catch (IOException ex) {
            Logger.getLogger(UInterf.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOutput.close();
            } catch (IOException ex) {
                Logger.getLogger(UInterf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void drawClientInfoList() {
        ClientGateway clientGateway = new ClientGateway();
        Client client = clientGateway.readUser(this.IDclient);
        UI.setClientFromAccountNo1(client.getFirstName());
        UI.setClientFromAccountNo2(client.getLastName());
        UI.setClientFromAccountNo3(Integer.toString(client.getIDcardNumber()));
        UI.setClientFromAccountNo4(Long.toString(client.getPersonalNumericalCode()));
        UI.setClientFromAccountNo5(client.getAddress());
    }

    public void loginButtonClicked() {
        this.username = UI.getUsernameField();
        this.password = UI.getPasswordField();
        this.userType = DB.getDBInstance().login(this.username, this.password);
        if(userType.equals("client")){
            this.IDclient = DB.getDBInstance().userId(username);
            List<Account> clientAccounts;
            AccountGateway clientAccountsGate = new AccountGateway();
            clientAccounts = clientAccountsGate.readAccounts(this.IDclient);
            this.drawClientAccountsListing(clientAccounts);
            this.drawClientInfoList();
            UI.showClientWindow();

        }
        else if(userType.equals("admin")){
            AdminGateway adminGate = new AdminGateway();
            List<Client> allClients;
            allClients = adminGate.readAllUsers();
            this.drawAdminClientsListing(allClients);
            UI.showAdminWindow();
        }
        else {
            //wrong username - password
            UI.showLoginWindow1();
        }
    }

    public void clientTransferButtonClicked() {
        Account fromAccount;
        Account toAccount;
        AccountGateway accountGate = new AccountGateway();
        int fromID, toID, amount;
        fromID = Integer.parseInt(UI.getClientFromAccountNo());
        toID = Integer.parseInt(UI.getClientToAccountNo());
        fromAccount = accountGate.readAccount(fromID);
        toAccount = accountGate.readAccount(toID);
        amount = Integer.parseInt(UI.getClientTransferAmount());
        ClientGateway clientGate = new ClientGateway();
        clientGate.transferMoney(fromAccount, toAccount, amount);
        this.redrawClientAccountsListing();
    }

    public void clientPayUtilitiesButtonClicked() {
        String utility;
        utility = UI.getClientSelectedUtilityBill();
        int amount, accountID;
        accountID = Integer.parseInt(UI.getClientBillFromAccountNo());
        amount = Integer.parseInt(UI.getClientBillAmount());
        Account account;
        AccountGateway accountGate = new AccountGateway();
        account = accountGate.readAccount(accountID);
        ClientGateway clientGate = new ClientGateway();
        clientGate.payUtilitiesBill(account, utility, amount);
        this.redrawClientAccountsListing();
    }

    public void clientGenerateReportButtonClicked() {
        List<Transfer> transfers;
        TransferGateway transferGate = new TransferGateway();
        String period = UI.getClientReportTimePeriod();
        if(period.equals("All")){
            transfers = transferGate.readTransfers(this.IDclient);
        }
        else{
            int timePeriod;
            timePeriod = Integer.parseInt(period);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -timePeriod);
            java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
            transfers = transferGate.readTransfers(this.IDclient, sqlDate);
        }
        
        this.printToFile(transfers);
    }
    
    public void adminGenerateReportButtonClicked() {
        int client = Integer.parseInt(UI.getAdminClientID());
        List<Transfer> transfers;
        TransferGateway transferGate = new TransferGateway();
        String period = UI.getAdminReportTimePeriod();
        if(period.equals("All")){
            transfers = transferGate.readTransfers(client);
        }
        else{
            int timePeriod;
            timePeriod = Integer.parseInt(period);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -timePeriod);
            java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
            transfers = transferGate.readTransfers(client, sqlDate);
        }
        
        this.printToFile(transfers);
    }

    public void adminGetClientAccountsButtonClicked() {
        this.IDclient = Integer.parseInt(UI.getAdminClientID());
        AccountGateway contGate = new AccountGateway();
        List<Account> clientAccounts;
        clientAccounts = contGate.readAccounts(IDclient);
        this.drawAdminClientAccountsListing(clientAccounts);
    }

    public void adminCreateClientButtonClicked() {
        Client newClient = new Client();
        AdminGateway clientGate = new AdminGateway();
        
        newClient.setFirstName(UI.getAdminClientName());
        newClient.setLastName(UI.getAdminClientLastName());
        newClient.setIDcardNumber(Integer.parseInt(UI.getAdminClientIDcardNo()));
        newClient.setPersonalNumericalCode(Long.parseLong(UI.getAdminClientPersNumCode()));
        newClient.setAddress(UI.getAdminClientAddress());
        newClient.setUsername(UI.getAdminClientUsername());
        newClient.setPassword(UI.getAdminClientPassword());
        newClient.setUserType("client");
        clientGate.createUser(newClient);
        this.redrawAdminClientsListing();
    }

    public void adminUpdateClientButtonClicked() {
        Client newClient = new Client();
        AdminGateway clientGate = new AdminGateway();
        newClient.setIDclient(Integer.parseInt(UI.getAdminClientID()));
        newClient.setFirstName(UI.getAdminClientName());
        newClient.setLastName(UI.getAdminClientLastName());
        newClient.setIDcardNumber(Integer.parseInt(UI.getAdminClientIDcardNo()));
        newClient.setPersonalNumericalCode(Long.parseLong(UI.getAdminClientPersNumCode()));
        newClient.setAddress(UI.getAdminClientAddress());
        newClient.setUsername(UI.getAdminClientUsername());
        newClient.setPassword(UI.getAdminClientPassword());
        newClient.setUserType("client");
        clientGate.updateUser(newClient);
        this.redrawAdminClientsListing();
    }
    
    public void clientUpdateClientButtonClicked() {
        ClientGateway clientGateway = new ClientGateway();
        Client client = clientGateway.readUser(this.IDclient);
        //UI.setClientFromAccountNo1(client.getFirstName());
        client.setPassword(this.password);
        client.setUsername(this.username);
        client.setUserType(this.userType);
        
        client.setFirstName(UI.getClientFromAccountNo1());
        client.setLastName(UI.getClientFromAccountNo2());
        client.setIDcardNumber(Integer.parseInt(UI.getClientFromAccountNo3()));
        
        //if (!CNP.validate(UI.getClientFromAccountNo4())) {
        //    UI.showLoginWindow2();
        //}
        
        client.setPersonalNumericalCode(Long.parseLong(UI.getClientFromAccountNo4()));
        client.setAddress(UI.getClientFromAccountNo5());
        
        clientGateway.updateUser(client);
    }
    
        public void clientViewClientButtonClicked() {
        Client newClient = new Client();
        ClientGateway clientGate = new ClientGateway();
        //newClient.setIDclient(Integer.parseInt(UI.getAdminClientID()));
        newClient.setFirstName(UI.getClientFromAccountNo1());
        newClient.setLastName(UI.getClientFromAccountNo2());
        newClient.setIDcardNumber(Integer.parseInt(UI.getClientFromAccountNo3()));
        newClient.setPersonalNumericalCode(Long.parseLong(UI.getClientFromAccountNo4()));
        newClient.setAddress(UI.getClientFromAccountNo5());
        //newClient.setUsername(UI.getAdminClientUsername());
        //newClient.setPassword(UI.getAdminClientPassword());
        //newClient.setUserType("client");
        clientGate.updateUser(newClient);
        //this.redrawAdminClientsListing();
    }

    public void adminDeleteClientButtonClicked() {
        AdminGateway clientGate = new AdminGateway();
        int id = Integer.parseInt(UI.getAdminClientID());
        clientGate.deleteUser(id);
        this.redrawAdminClientsListing();
    }

    public void adminCreateClientAccountButtonClicked() {
        Account cont = new Account();
        this.IDclient = Integer.parseInt(UI.getAdminClientID());
        cont.setAmountMoney(Integer.parseInt(UI.getAdminClientAmmount()));
        if(UI.getAdminClientAccountType().equals("ron")){
            cont.setAccType(Account.ACCTYPE.RON);
        }
        else{
            cont.setAccType(Account.ACCTYPE.EUR);
        }
        AccountGateway adminGate = new AccountGateway();
        adminGate.createAccount(cont, IDclient);
        this.redrawAdminClientAccountsListing();
    }

    public void adminUpdateClientAccountButtonClicked() {
        Account cont = new Account();
        int accNo = Integer.parseInt(UI.getAdminClientAccountNo());
        cont.setIDaccount(accNo);
        cont.setAmountMoney(Integer.parseInt(UI.getAdminClientAmmount()));
        if(UI.getAdminClientAccountType().equals("ron")){
            cont.setAccType(Account.ACCTYPE.RON);
        }
        else{
            cont.setAccType(Account.ACCTYPE.EUR);
        }
        AccountGateway adminGate = new AccountGateway();
        adminGate.updateAccount(cont);
        this.redrawAdminClientAccountsListing();
    }

    public void adminDeleteClientAccountButtonClicked() {
        int accNo = Integer.parseInt(UI.getAdminClientAccountNo());
        AccountGateway adminGate = new AccountGateway();
        adminGate.deleteAccount(accNo);
        this.redrawAdminClientAccountsListing();
    }
    
    
    
    public static void main(String[] args){
        UInterf control = new UInterf();
        UI = new UserInterface(control);
        
        UI.showLoginWindow();
    }

    public void clientDeleteClientButtonClicked() {
        clientUpdateClientButtonClicked();
    }

}
