package com.RevatureAssignments.Employee;
import com.RevatureAssignments.ConnectionFactory;
import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao {

    Connection connection;


    public EmployeeDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void viewAccount(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from customer where id= " + "'" + id + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println("ID: "+resultSet.getInt(1)+", email: "+ resultSet.getString(2)
            + ", name: " + resultSet.getString(3)+ ", checking: " + resultSet.getInt(4)+ ", savings: "+
                    resultSet.getInt(5));
        }
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        String sql = "Delete from customer where id= " + "'" + id + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("User deleted");
        } else {
            System.out.println("Something went wrong.");
        }
    }

    @Override
    public void approveAccount(int id) throws SQLException {
        String sql = "update customer set approved = true where id = "+id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if(count > 0) {
            System.out.println("User approved!");
        }else {
            System.out.println("Something went wrong.");
        }
    }

    @Override
    public void viewPendingAccounts() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from customer where approved = 0";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println("ID: "+resultSet.getInt(1)+"| Email: "+resultSet.getString(2)
            +"| Name: "+resultSet.getString(3));
        }
    }
}
