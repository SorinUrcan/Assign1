/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.data;

/**
 *
 * @author Sorin
 */
public class Client extends User{
    protected String username;
    protected String password;

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
