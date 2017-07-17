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
// Inheritance from class DataAccess
public class DataAccessPostgreSQL extends DataAccess {
    //Constructor
    public DataAccessPostgreSQL(String server,int port,String database,String schema,String username,String password) {
        super(server, port, database, schema, username, password, TypeOfDatabase.PostgreSQL);
        connectionString = TypeOfDatabase.PostgreSQL.getConnectionString() + server + ":" + port + "/" + database + "?searchpath=" + schema;
    }
}
