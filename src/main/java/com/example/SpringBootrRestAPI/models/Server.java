package com.example.SpringBootrRestAPI.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document
public class Server implements Comparable<Server> {

    @Id
    private String serverID ;
    private int serverCapacity ;
    private Active isActive ;
    private HashMap<String,Integer> customers  = new HashMap<>();


    public Server(String serverID, int serverCapacity, Active isActive, HashMap<String, Integer> customers) {
        this.serverID = serverID;
        this.serverCapacity = serverCapacity;
        this.isActive = isActive;
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Server{" +
                "serverID='" + serverID + '\'' +
                ", serverCapacity=" + serverCapacity +
                ", isActive=" + isActive +
                ", customers=" + customers +
                '}';
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public int getServerCapacity() {
        return serverCapacity;
    }

    public void setServerCapacity(int serverCapacity) {
        this.serverCapacity = serverCapacity;
    }

    public Active getIsActive() {
        return isActive;
    }

    public void setIsActive(Active isActive) {
        this.isActive = isActive;
    }

    public HashMap<String, Integer> getCustomers() {
        return customers;
    }

    public void setCustomers(HashMap<String, Integer> customers) {
        this.customers = customers;
    }

    @Override
    public int compareTo(Server o) {

        if(serverCapacity > o.serverCapacity){

            return 1 ;
        }else if (serverCapacity < o.serverCapacity){

            return -1 ;

        }else {

            return  0 ;
        }
    }
}
