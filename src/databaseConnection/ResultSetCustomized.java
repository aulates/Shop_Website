package databaseConnection;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.ResultSet;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
// inheritance from class Result
public class ResultSetCustomized extends Result { 
    private ResultSet resultSet;
    // Getter and Setter
    public ResultSetCustomized() {
        this.resultSet = null;
    }
    public ResultSet getResultSet() {
        return resultSet;
    }
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}