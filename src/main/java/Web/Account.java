package web;

import java.util.Date;
import java.util.ArrayList;
import util.DateRange;
import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.driver.AccountStorageDriver;



public class Account implements StorableInterface{

    private String password;
    private String userID;
    private int userType;


    public Account() {
    }

    public Account(String userID, String password) {
        this.userID=userID;
        this.password = password;

    }

    public String getPassword() {
        return password;
    }

    public String getuserID() {
        return userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setuserID(String userID){
        this.userID=userID;
    }

    public void setuserType(int type){
      userType = type;
    }

    public int getuserType(){
      return userType;
    }

    @Override
    public StorageDriverBase getStorageDriver(){
        //return new AccountStorageDriver();
        return new AccountStorageDriver();
    }

    @Override
     public ArrayList<Object> getStorageValues(){
        ArrayList<Object> temp = new ArrayList<>();
        temp.add((Object)userID);
        temp.add((Object)password);
        temp.add((Object)userType);
        return temp;
    }

    @Override
    public String getStorageLocation(){
        return "accounts";
    }
}
