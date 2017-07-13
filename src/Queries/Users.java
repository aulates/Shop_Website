/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queries;

import databaseConnection.DataAccess;
import databaseConnection.Result;
import databaseConnection.ResultSetCustomized;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana Elena Ulate Salas
 */
public class Users {

    private DataAccess dataAccess;

    public int createUser(DataAccess da, String email, String userPassword, String userType, String country) {
        ResultSetCustomized result;
        String sql = "INSERT INTO" + da.getSchema() + "Users( email, userPassword, user_type, country) VALUES (?, ?, ?, ?) returning id;";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, userPassword);
            stmt.setString(3, userType);
            stmt.setString(4, country);
            result = da.executeSqlQuery(stmt);
            
            if (!result.isError() && result.getResultSet().next()) {
                int id = result.getResultSet().getInt("id");
                System.out.println(id);
                return id;
            }
           else if(result.isError()){
                throw new RuntimeException(result.getErrorDescription());
            }
        } catch (SQLException e) {
            return -1;
        }
        return -1;
    }
    public Result updateUserState(DataAccess da, boolean isActive, String email){
        Result result;
        String sql = "UPDATE " + da.getSchema() + "Users SET isActive = ? WHERE email = ?";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setBoolean(1, isActive);
            stmt.setString(2, email);
            result = da.executeSQL(stmt);
        } catch (SQLException ex) {
            result = new Result();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public ResultSetCustomized LogIn(DataAccess da, String email, String Password) {
        ResultSetCustomized result;
        PreparedStatement stmt;
        String sql = "SELECT * FROM " + da.getSchema() + "Users WHERE email = '" + email + "' AND userPassword = '" + Password + "';";
        try {
            stmt = da.getConnection().prepareStatement(sql);
            result = da.executeSqlQuery(stmt);
            
        } catch (SQLException ex) {
            result = new ResultSetCustomized();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }

    public Result createBuyerPerson(DataAccess da, int id_User, String buyerName, String Identification, int age) {
        Result result;
        String sql = "INSERT INTO " + da.getSchema() + "BuyerPerson(id_User, buyerName, identification, age, total) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, id_User);
            stmt.setString(2, buyerName);
            stmt.setString(3, Identification);
            stmt.setInt(4, age);
            stmt.setInt(5, 0);
            result = da.executeSQL(stmt);
        } catch (SQLException e) {
            result = new Result();
            result.setError(e.getLocalizedMessage());
        }
        return result;
    }

    public Result createSellerPerson(DataAccess da, int id_User, String sellerName, String Identification, int age) {
        Result result;
        String sql = "INSERT INTO " + da.getSchema() + "SellerPerson(id_User, buyerName, identification, age, total) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, id_User);
            stmt.setString(2, sellerName);
            stmt.setString(3, Identification);
            stmt.setInt(4, age);
            stmt.setInt(5, 0);
            result = da.executeSQL(stmt);
        } catch (SQLException e) {
            result = new Result();
            result.setError(e.getLocalizedMessage());
        }
        return result;
    }

    public Result createSellerCompany(DataAccess da, int id_User, String companyIdentification, String socialReason, String commercialReason) {
        Result result;
        String sql = "INSERT INTO " + da.getSchema() + "SellerCompany(id_User, companyIdentification, socialReason, commercialReason, total) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, id_User);
            stmt.setString(2, companyIdentification);
            stmt.setString(3, socialReason);
            stmt.setString(4, commercialReason);
            stmt.setInt(5, 0);
            result = da.executeSQL(stmt);
        } catch (SQLException e) {
            result = new Result();
            result.setError(e.getLocalizedMessage());
        }
        return result;
    }
}
