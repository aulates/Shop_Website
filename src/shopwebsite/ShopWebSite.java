/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopwebsite;
import LogInInterface.LogIn;
import LogInInterface.MainFrame;
import databaseConnection.DataAccess;
import databaseConnection.DataAccessPostgreSQL;
import databaseConnection.Result;
import javax.swing.JOptionPane;


/**
 **
 ** @author Ana Elena Ulate Salas
 **/
public class ShopWebSite {
    static DataAccess dataAccess;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // muestra el primer frame
        dataAccess = new DataAccessPostgreSQL("localhost", 5432, "JloveADataBase", "public", "postgres", "11042017");
        Result res = dataAccess.connect();
        if (res.isError()){
            JOptionPane.showMessageDialog(null, res.getErrorDescription());
        }
        else{
            java.awt.EventQueue.invokeLater(()-> {
                MainFrame view = new MainFrame(dataAccess); 
                view.setVisible(true);
            }); 
        }
    }  
}
