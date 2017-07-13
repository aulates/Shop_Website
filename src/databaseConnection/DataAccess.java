package databaseConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ana Elena Ulate Salas
 */
public abstract class DataAccess {
    
    // Data Base Attributes
    protected final String server;
    protected final int port;
    protected final String database;
    protected final String schema;
    protected final String username;
    protected final String password;
    
    // Connection management for the Data Base
    protected String connectionString;
    protected Connection connection;
    protected ResultSet resultSet = null;
    protected Statement statement;
    protected TypeOfDatabase typeOfDatabase;
    // Constructor of class DataAccess                                                                           // Enum class as attribute
    public DataAccess(String server,int port,String database,String schema,String username,String password,TypeOfDatabase typeOfDatabase) {  
        this.server = server; 
        this.port = port;
        this.database = database;
        this.schema = schema;
        this.username = username;
        this.password = password;
        this.typeOfDatabase = typeOfDatabase;
    }
    // Method that connects the program to a data base
    public Result connect() {
        Result result = new Result();
        try { // A way to do somenthing without fear of dropping the code
            Class.forName(typeOfDatabase.getDriver()); 
        } 
        catch (ClassNotFoundException ex) { //Executes the exceptions when a class is not found 
            result.setError(ex.getLocalizedMessage());
          }   
        try { //Trys to give connection its values 
            connection = DriverManager.getConnection(connectionString,
                    username, password);
        } 
        catch (SQLException ex) { // Executes the exception of SQL 
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    // Method that disconnects the program to a data base
    public Result disconnect() {
        Result result = new Result();
        try { // Try to close the connection
            connection.close();
        } catch (SQLException ex) { // If not able to close it the DataBase execute SQL Exception 
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    // Returns true or false values while checking the state of the connection
    public boolean isClosed() {
        try { //returns if data base is close
            return connection.isClosed();
        } catch (SQLException ex) { // if not able to check it the DataBase execute SQL Exceptiom
            return false;
        }
    }
    //Is a method that tries to execute and string that contains a query 
    public ResultSetCustomized executeSqlQuery(String sql) {
        ResultSetCustomized result = new ResultSetCustomized();
        try { // Add the ResultSet to the personalized class 
            statement = connection.createStatement();
            result.setResultSet(statement.executeQuery(sql));
        } catch (SQLException ex) { //If not able to do try, execute SQL Exception 
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    // the method with the same name that the one before this does not recive a PreparedStatement insted of an string 
    public ResultSetCustomized executeSqlQuery(PreparedStatement pstmt) {
        ResultSetCustomized result = new ResultSetCustomized();
        try {//Add the ResultSet to the personalized class
            result.setResultSet(pstmt.executeQuery());
        } catch (SQLException e) {//If not able to do try, execute SQL Exception 
            result.setError(e.getLocalizedMessage());
        }
        return result;
    }
    // the method executes an SQL statement while updating 
    public Result executeSQL(PreparedStatement pstmt) {
        Result result = new Result();
        try{ // executes an update and then closes it  
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (SQLException ex) // if not able to do try, execute SQL Exception
        {
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    //getters
    public String getSchema() {
        if (schema.isEmpty())
            return schema;
        else
            return "\"" + schema + "\".";
    }
    public Connection getConnection() {
        return connection;
    }
}
