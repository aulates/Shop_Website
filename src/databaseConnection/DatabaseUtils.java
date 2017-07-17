package databaseConnection;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
public class DatabaseUtils {
    //First method works for making a table model by default by getting and placing the identifiers inside the model
    static public DefaultTableModel getDefaultTableModel(ResultSet rs, String[] indetifiers)
    {
        DefaultTableModel dtm = new DefaultTableModel()
        {
            @Override //Checks if the cell can be changed 
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        };
        try { // try to set columns with the identifiers 
            dtm.setColumnIdentifiers(getIndetifiers(rs, indetifiers));
            while (rs.next())
            {
                Object[] rowData = new Object[indetifiers.length];
                for (int i = 0; i < rowData.length; ++i)
                {
                    rowData[i] = rs.getObject(i+1);
                }
                dtm.addRow(rowData.clone());
            }
            return dtm;
        } catch (SQLException e) { // if not able to execute SQLException 
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    // Static Getter
    static public String[] getIndetifiers(ResultSet rs, String[] indetifiers)
    {
        if (indetifiers == null)
        {
            try {// Calculates the amount of columns
                ResultSetMetaData metaData = rs.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                
                //Returns the name of the columns from the DataBase
                for (int column = 0; column < numberOfColumns; column++) {
                    indetifiers[column] = metaData.getColumnLabel(column + 1);    
                }
                return indetifiers;
            } catch (SQLException ex) {//if not able to execute SQLException
                Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        else
            return indetifiers;
    }
}