package databaseConnection;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ana Elena Ulate Salas
 */
public class Result {  
    // Attributes to determinate errors 
    private boolean error;
    private String errorDescription;
    //Constructor
    public Result() {
        this.error = false; //by default starts without error
    }
    //second constructor
    public Result(boolean error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }
    //getters and setters
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }
    public void setError(String errorDescription) {
        this.error = true;
        this.errorDescription = errorDescription;
    }
    public String getErrorDescription() {
        return errorDescription;
    }
}