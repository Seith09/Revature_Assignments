package com.RevatureAssignments.Customer;
import com.RevatureAssignments.ConnectionFactory;
import java.sql.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class CustomerDaoImpl implements CustomerDao {

    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public CustomerDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "insert into customer (email, name, password, checking, savings, approved) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getEmail());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getPassword());
        preparedStatement.setInt(4, customer.setChecking(50));
        preparedStatement.setInt(5, customer.setSavings(50));
        preparedStatement.setBoolean(6, customer.isApproved(false));
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("User saved");
        } else {
            System.out.println("Something went wrong.");
        }
    }

    @Override
    public void depositMoney(int depositAmount, int id) throws SQLException {
        String sql = "update customer set checking = checking +"+depositAmount+" where id = "+id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Money deposited successfully.");
            System.out.println("\n========================");
        } else {
            System.out.println("Something went wrong.");
        }
        String sqlTransaction = "insert into transactions (account_owner, amount) values ("+ id +", "+depositAmount+ ")";
        PreparedStatement preparedStatementTransactionRecord = connection.prepareStatement(sqlTransaction);
        preparedStatementTransactionRecord.executeUpdate();
    }

    @Override
    public void withdrawMoney(int withdrawAmount, int id) throws SQLException {
        String sql = "update customer set checking = checking - "+withdrawAmount+" where id = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Money withdrawn successfully.");
            System.out.println("\n========================");
        } else {
            System.out.println("Something went wrong.");
        }
        String sqlTransaction = "insert into transactions (account_owner, amount) values (" + id + ", -"+ withdrawAmount+ ")";
        PreparedStatement preparedStatementTransactionRecord = connection.prepareStatement(sqlTransaction);
        preparedStatementTransactionRecord.executeUpdate();
    }

    @Override
    public void transferFromChecking(int withdrawAmount, int depositAmount, int id) throws SQLException {
        String sql = "update customer set checking = checking - "+withdrawAmount+" where id = "+id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("...");
        } else {
            System.out.println("Something went wrong.");
        }
        String sql2 = "update customer set savings = savings + "+depositAmount+" where id = "+id;
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        int count2 = preparedStatement2.executeUpdate();
        if (count2 > 0) {
            System.out.println("Money transferred successfully.");
        } else {
            System.out.println("Something went wrong.");
        }
    }

    @Override
    public void transferFromSavings(int withdrawAmount, int depositAmount, int id) throws SQLException {
        String sql = "update customer set savings = savings - "+withdrawAmount+" where id = "+id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("...");
        } else {
            System.out.println("Something went wrong.");
        }
        String sql2 = "update customer set checking = checking + "+depositAmount+" where id = "+id;
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        int count2 = preparedStatement2.executeUpdate();
        if (count2 > 0) {
            System.out.println("Money transferred successfully.");
        } else {
            System.out.println("Something went wrong.");
        }



    }
}
