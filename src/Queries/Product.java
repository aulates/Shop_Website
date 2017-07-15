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

/**
 **
 ** @author Ana Elena Ulate Salas
 **/

public class Product {
    
    public int createProduct(DataAccess da, String code, String state, String country, String productName, int price) {
        ResultSetCustomized result;
        String sql = "INSERT INTO" + da.getSchema() + "Products(code, state, country, productName, price) VALUES (?, ?, ?, ?,?) returning id;";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setString(1, code);
            stmt.setString(2, state);
            stmt.setString(3, country);
            stmt.setString(4, productName);
            stmt.setInt(5, price); 
            result = da.executeSqlQuery(stmt);
            
            if (!result.isError() && result.getResultSet().next()) {
                int id = result.getResultSet().getInt("id");
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
    public Result createUserProducts(DataAccess da, int id_User, int id_Product, int amount) {
        Result result;
        String sql = "INSERT INTO " + da.getSchema() + "UserProducts(id_User, id_Product, amount) VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, id_User);
            stmt.setInt(2, id_Product);
            stmt.setInt(3, amount);
            result = da.executeSQL(stmt);
        } catch (SQLException e) {
            result = new Result();
            result.setError(e.getLocalizedMessage());
        }
        return result;
    }
    public Result updateUserProductAmount(DataAccess da, int amount, int id){
        Result result;
        String sql = "UPDATE " + da.getSchema() + "UserProducts SET amount = ? WHERE id_User = ?";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, amount);
            stmt.setInt(2, id);
            result = da.executeSQL(stmt);
        } catch (SQLException ex) {
            result = new Result();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public Result updateProductPrice(DataAccess da, int price, String productName){
        Result result;
        String sql = "UPDATE " + da.getSchema() + "Products SET price = ? WHERE productName = ?";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, price);
            stmt.setString(2, productName);
            result = da.executeSQL(stmt);
        } catch (SQLException ex) {
            result = new Result();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public ResultSetCustomized searchProductByName(DataAccess da, String productName, String code) {
        ResultSetCustomized result;
        PreparedStatement stmt;
        String sql = "SELECT p.id, p.code, p.productName, p.price, p.state, up.amount, p.country FROM " 
                + da.getSchema() + "Products p JOIN " + da.getSchema() 
                + "UserProducts up ON p.id = up.id_Product JOIN" + da.getSchema()
                + "Users u ON up.id_User = u.id WHERE p.productName LIKE '" 
                + productName + "' OR p.code = '" + code + "';";
        try {
            stmt = da.getConnection().prepareStatement(sql);
            result = da.executeSqlQuery(stmt);
            
        } catch (SQLException ex) {
            result = new ResultSetCustomized();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public ResultSetCustomized getAllProduct(DataAccess da) {
        ResultSetCustomized result;
        PreparedStatement stmt;
        String sql = "SELECT p.id, p.code, p.productName, p.price, p.state, up.amount, p.country FROM " 
                + da.getSchema() + "Products p JOIN " + da.getSchema() 
                + "UserProducts up ON p.id = up.id_Product JOIN" + da.getSchema()
                + "Users u ON up.id_User = u.id;"; 
        try {
            stmt = da.getConnection().prepareStatement(sql);
            result = da.executeSqlQuery(stmt);
            
        } catch (SQLException ex) {
            result = new ResultSetCustomized();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public ResultSetCustomized getAllSellerProduct(DataAccess da, int id) {
        ResultSetCustomized result;
        PreparedStatement stmt;
    String sql = "SELECT p.id, p.code, p.productName, p.price, p.state, up.amount, p.country FROM " 
                + da.getSchema() + "Products p JOIN " + da.getSchema() 
                + "UserProducts up ON p.id = up.id_Product JOIN" + da.getSchema()
                + "Users u ON up.id_User = u.id WHERE u.id = '" + id + "';"; 
        try {
            stmt = da.getConnection().prepareStatement(sql);
            result = da.executeSqlQuery(stmt);
            
        } catch (SQLException ex) {
            result = new ResultSetCustomized();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public ResultSetCustomized getAllSellersProductForCatalogue(DataAccess da, String code) {
        ResultSetCustomized result;
        PreparedStatement stmt;
    String sql = "SELECT p.id, p.code, p.productName, p.price, p.state, up.amount, p.country FROM " 
                + da.getSchema() + "Products p JOIN " + da.getSchema() 
                + "UserProducts up ON p.id = up.id_Product JOIN" + da.getSchema()
                + "Users u ON up.id_User = u.id WHERE p.code = '" + code + "' AND u.user_type = 'Seller';"; 
        try {
            stmt = da.getConnection().prepareStatement(sql);
            result = da.executeSqlQuery(stmt);
            
        } catch (SQLException ex) {
            result = new ResultSetCustomized();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public Result deleteProduct(DataAccess da,int id){
        Result result;
        String sql = "DELETE FROM " + da.getSchema() + "Products WHERE id = ?;";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            result = da.executeSQL(stmt);
        } catch (SQLException ex) {
            result = new Result();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public Result deleteUserProduct(DataAccess da,int id){
        Result result;
        String sql = "DELETE FROM " + da.getSchema() + "UserProducts WHERE id_Product = ?;";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            result = da.executeSQL(stmt);
        } catch (SQLException ex) {
            result = new Result();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public String[] getIdentifiers(){
        String[] identifiers = {"Id","Code","Product Name","Price","State","Amount","Country"};
        return identifiers;
    }
}
 