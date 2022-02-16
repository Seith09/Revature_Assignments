package com.RevatureAssignments.Customer;

import java.sql.SQLException;

public interface CustomerDao {

    void addCustomer(Customer customer) throws SQLException;
    void depositMoney(int depositAmount, int id) throws SQLException;
    void withdrawMoney(int withdrawAmount, int id) throws SQLException;
    void transferFromChecking(int withdrawAmount, int depositAmount, int id) throws SQLException;
    void transferFromSavings(int withdrawAmount, int depositAmount, int id) throws SQLException;
}
