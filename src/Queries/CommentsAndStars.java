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
 *
 * @author Ana Elena Ulate Salas
 */
public class CommentsAndStars {
        private DataAccess dataAccess;

    public Result createCommentsAndStars(DataAccess da, String commentary, int starsSeller, int id_UserProducts) {
        Result result;
        String sql = "INSERT INTO " + da.getSchema() + "CommentsAndStars(commentary, starsSeller, id_UserProducts) VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setString(1, commentary);
            stmt.setInt(2, starsSeller);
            stmt.setInt(3, id_UserProducts);
            result = da.executeSQL(stmt);
        } catch (SQLException e) {
            result = new Result();
            result.setError(e.getLocalizedMessage());
        }
        return result;
    }
    public Result updateCommentsAndStars(DataAccess da, String response, int starsConsumer,int id){
        Result result;
        String sql = "UPDATE " + da.getSchema() + "CommentsAndStars SET response = ?, starsConsumer = ? WHERE id_UserProducts = ?";
        try {
            PreparedStatement stmt = da.getConnection().prepareStatement(sql);
            stmt.setString(1, response);
            stmt.setInt(2, starsConsumer);
            stmt.setInt(3, id);
            result = da.executeSQL(stmt);
        } catch (SQLException ex) {
            result = new Result();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public String[] getIdentifiers(){
        String[] identifiers = {"Id","Commentary","Stars Seller","Response","Stars Consummer","Sale"};
        return identifiers;
    }
    public ResultSetCustomized getSellerComments(DataAccess da, int idSeller) {
        ResultSetCustomized result;
        PreparedStatement stmt;
        String sql = "SELECT cmm.id,cmm.commentary,cmm.starsSeller,cmm.response,cmm.starsConsumer, cmm.id_UserProducts FROM" +
"                CommentsAndStars cmm JOIN UserProducts up ON cmm.id_UserProducts = up.id JOIN Products p ON p.id = up.id_Product WHERE p.id_Seller = " + idSeller; 
        try {
            stmt = da.getConnection().prepareStatement(sql);
            result = da.executeSqlQuery(stmt);
            
        } catch (SQLException ex) {
            result = new ResultSetCustomized();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
    public ResultSetCustomized getBuyerComments(DataAccess da) {
        ResultSetCustomized result;
        PreparedStatement stmt;
        String sql = "select cmm.id,cmm.commentary,cmm.starsSeller,cmm.response,cmm.starsConsumer, cmm.id_UserProducts from CommentsAndStars cmm JOIN UserProducts up ON cmm.id_UserProducts = up.id JOIN Users u On up.id_User = u.id WHERE u.isActive = true"; 
        try {
            stmt = da.getConnection().prepareStatement(sql);
            result = da.executeSqlQuery(stmt);
            
        } catch (SQLException ex) {
            result = new ResultSetCustomized();
            result.setError(ex.getLocalizedMessage());
        }
        return result;
    }
}
