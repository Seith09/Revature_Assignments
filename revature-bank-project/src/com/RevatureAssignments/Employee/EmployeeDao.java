package com.RevatureAssignments.Employee;

import java.sql.SQLException;

public interface EmployeeDao {

    void viewAccount(int id) throws SQLException;
    void deleteCustomer(int id) throws SQLException;
    void approveAccount(int id) throws  SQLException;
    void viewPendingAccounts() throws SQLException;
    void viewTransactions(int id) throws SQLException;

}
