package com.RevatureAssignments.Customer;

import java.sql.SQLException;

public class Customer {

    private int id;
    private String email;
    private String name;
    private int checking;
    private int savings;
    private String password;
    private boolean isApproved;

    public Customer(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChecking() {
        return checking;
    }

    public int setChecking(int checking) {
        this.checking = checking;
        return checking;
    }

    public int getSavings() {
        return savings;
    }

    public int setSavings(int savings) {
        this.savings = savings;
        return savings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void loginCustomer() throws SQLException {

    }

    public boolean isApproved(boolean approval) {
        return isApproved;
    }

    public boolean setApproved(boolean approved) {
        this.isApproved = approved;
        return approved;
    }
}
