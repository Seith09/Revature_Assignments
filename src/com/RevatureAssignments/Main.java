package com.RevatureAssignments;

import com.RevatureAssignments.Customer.Customer;
import com.RevatureAssignments.Customer.CustomerDao;
import com.RevatureAssignments.Customer.CustomerDaoFactory;
import com.RevatureAssignments.Employee.EmployeeDao;
import com.RevatureAssignments.Employee.EmployeeDaoFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static int userId;
    static boolean closeSession = true;
    static boolean programOpen = true;

    static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = ConnectionFactory.getConnection();
        Scanner scanner = new Scanner(System.in);
        CustomerDao dao = CustomerDaoFactory.getUserDao();
        EmployeeDao edao = EmployeeDaoFactory.getEmployeeDao();

        do {
            System.out.println("Welcome to BankApp!\nPlease select a number that corresponds to your desired function");
            System.out.println("\n1. Create Account 2. Login 3. Close Program");
            int createAccount = scanner.nextInt();


            if (createAccount == 1) {
                System.out.println("Please enter your email:");
                String email = scanner.next();
                System.out.println("Please enter your full name:");
                String name = scanner.next() + " " + scanner.next();
                System.out.println("Please enter a safe password:");
                String password = scanner.next();

                Customer newCustomer = new Customer(email, name, password);

                System.out.println("Congratulations on your new checking and savings account! You have a starting balance of $50 deposited into your checking and savings account\n");

                dao.addCustomer(newCustomer);
            } else if (createAccount == 2) {
                boolean logInFailed = true;
                while (logInFailed) {
                    System.out.println("Email: ");
                    String emailLogin = scanner.next();
                    System.out.println("Password: ");
                    String passwordLogin = scanner.next();
                    Statement statement = connection.createStatement();
                    String sql = "select * from customer where email = '" + emailLogin + "' and password = '" + passwordLogin + "'";
                    ResultSet resultSet = statement.executeQuery(sql);
                    if (resultSet.next()) {
                        logInFailed = false;
                        userId = resultSet.getInt(1);
                    } else {
                        System.out.println("Something went wrong! Please try again");
                        System.out.println("\n=================================");
                    }
                }

                System.out.println("Welcome to your account! Please select the number that corresponds with your desired action...\n");

                while (closeSession) {
                    System.out.println("\n1. Deposit 2. Withdraw 3. Transfer 4. View Balance 5. Log Off");

                    int userInput = scanner.nextInt();

                    switch (userInput) {
                        case 1:
                            System.out.println("Please input deposit amount: ");
                            int depositAmount = scanner.nextInt();
                            dao.depositMoney(depositAmount, userId);
                            break;
                        case 2:
                            System.out.println("Please input withdraw amount");
                            int withdrawAmount = scanner.nextInt();
                            Statement withdrawStatement = connection.createStatement();
                            String sqlWithdraw = "select checking from customer where id = " + userId;
                            ResultSet resultSetWithdraw = withdrawStatement.executeQuery(sqlWithdraw);
                            while(resultSetWithdraw.next()){
                                int currentCheckingAmount = resultSetWithdraw.getInt(1);
                                if(currentCheckingAmount > withdrawAmount){
                                    dao.withdrawMoney(withdrawAmount, userId);
                                } else{
                                    System.out.println("\nInsufficient funds");
                                    System.out.println("=====================");
                                }
                            }
                            break;
                        case 3:
                            boolean continueTransfer = true;
                            while (continueTransfer) {
                                System.out.println("\nSelect account to transfer from:");
                                System.out.println("\nChecking | Savings");
                                String transferOption = scanner.next();
                                if (transferOption.equalsIgnoreCase("checking")) {
                                    System.out.println("\nInput amount to transfer");
                                    int fromChecking = scanner.nextInt();
                                    System.out.println("\nTransferring " + fromChecking + " to Savings account from Checking account...");
                                    Statement statement = connection.createStatement();
                                    String sqlChecking = "select checking from customer where id = " + userId;
                                    ResultSet resultSetChecking = statement.executeQuery(sqlChecking);
                                    while (resultSetChecking.next()) {
                                        int currentCheckingAmount = resultSetChecking.getInt(1);
                                        if (currentCheckingAmount > fromChecking) {
                                            dao.transferFromChecking(fromChecking, fromChecking, userId);
                                            System.out.println("\nTransfer Complete");
                                            continueTransfer = false;
                                        } else {
                                            System.out.println("Insufficient Funds");
                                        }
                                    }
                                } else if (transferOption.equalsIgnoreCase("savings")) {
                                    System.out.println("\nInput amount to transfer");
                                    int fromSavings = scanner.nextInt();
                                    System.out.println("\nTransferring " + fromSavings + " to Savings account from Checking account...");
                                    Statement statement = connection.createStatement();
                                    String sqlSavings = "select savings from customer where id = " + userId;
                                    ResultSet resultSavings = statement.executeQuery(sqlSavings);
                                    while (resultSavings.next()) {
                                        int currentSavingsAccount = resultSavings.getInt(1);
                                        if (currentSavingsAccount > fromSavings) {
                                            dao.transferFromSavings(fromSavings, fromSavings, userId);
                                            System.out.println("\nTransfer Complete");
                                            continueTransfer = false;
                                        } else {
                                            System.out.println("\nInsufficient Funds");
                                        }
                                    }
                                }
                            }
                            break;
                        case 4:
                            Statement statement = connection.createStatement();
                            String sql = "select checking, savings from customer where id= " + "'" + userId + "'";
                            ResultSet resultSet = statement.executeQuery(sql);
                            while(resultSet.next()){
                                System.out.println("==============");
                                System.out.println("Checking: "+resultSet.getInt(1)+", Savings: "+ resultSet.getString(2));
                                System.out.println("==============");
                            }
                            break;
                        case 5:
                            System.out.println("\n==============");
                            System.out.println("Thank you! Have a great day!");
                            closeSession = false;
                    }
                }
            } else if (createAccount == 4) {
                boolean atWork = true;
                boolean adminLogInFailed = true;

                while (adminLogInFailed) {
                    System.out.println("Username: ");
                    String adminUsername = scanner.next();
                    System.out.println("Password: ");
                    String adminPassword = scanner.next();
                    Statement statement = connection.createStatement();
                    String sql = "select * from employee where username = '" + adminUsername + "' and password = '" + adminPassword + "'";
                    ResultSet resultSet = statement.executeQuery(sql);
                    if (resultSet.next()) {
                        adminLogInFailed = false;
                    } else {
                        System.out.println("\nSomething went wrong! Please try again");
                    }
                }
                System.out.println("\nWelcome! please select your desired function");
                while (atWork) {
                    System.out.println("1. Look Up Account\n2. Delete Account\n3. Review Unapproved Accounts\n4. List Transaction\n5. Approve Account");

                    int adminInput = scanner.nextInt();

                    switch (adminInput) {

                        case 1:
                            System.out.println("\nInput account ID number to view");
                            int viewAccount = scanner.nextInt();
                            edao.viewAccount(viewAccount);
                            System.out.println("\n===================\n");
                            break;
                        case 2:
                            System.out.println("\nInput account ID number to delete");
                            int deleteAccount = scanner.nextInt();
                            System.out.println("\nType in account number to confirm deletion");
                            int confirmDeletion = scanner.nextInt();
                            if (confirmDeletion == deleteAccount) {
                                edao.deleteCustomer(deleteAccount);
                                System.out.println("\nAccount deleted");
                            } else {
                                System.out.println("\nDeletion cancelled");
                            }
                            break;
                        case 3:
                            System.out.println("=====================");
                            edao.viewPendingAccounts();
                            System.out.println("=====================");
                            break;
                        case 4:
                            break;
                        case 5:
                            System.out.println("\nInput account ID to approve");
                            int accountToApprove = scanner.nextInt();
                            edao.approveAccount(accountToApprove);
                            System.out.println("\nAccount approved");
                            break;
                        case 6:
                            System.out.println("\nHave a good day!");
                            atWork = false;
                    }
                    System.out.println("\nPlease select your next action");
                }
            } else if(createAccount == 3) {
                System.out.println("Closing program...");
                programOpen = false;
                scanner.close();
            }
        } while(programOpen);
    }
}