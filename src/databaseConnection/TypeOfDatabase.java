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
public enum TypeOfDatabase {
    MySQL(1, "MySQL Server", "com.mysql.jdbc.Driver", "jdbc:mysql://"),
    PostgreSQL(2, "PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://");
    
    private final int id;
    private final String description;
    private final String driver;
    private final String connectionString;
    //Constructor
    TypeOfDatabase(int id, String description, String driver, String connectionString)
    {
        this.id = id;
        this.description = description;
        this.driver = driver;
        this.connectionString = connectionString;
    }
    //getters and setter
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public String getDriver() {
        return driver;
    }
    public String getConnectionString() {
        return connectionString;
    }   
}