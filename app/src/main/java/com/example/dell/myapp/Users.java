package com.example.dell.myapp;

public class Users {
    String username;
    String userfrom;
    String userto;
    String userdesc;
    String usermail;
    public Users(){

    }
    public Users(String uname,String ufrom,String uto,String usermail,String userdesc){
        this.username=uname;
        this.userfrom=ufrom;
        this.userto=uto;
        this.usermail=usermail;
        this.userdesc=userdesc;
    }

    public String getUsername() {
        return username;
    }

    public String getUserfrom() {
        return userfrom;
    }

    public String getUserto() {
        return userto;
    }

    public String getUsermail() { return usermail;}

    public String getUserdesc() {
        return userdesc;
    }


}
