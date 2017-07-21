/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInInterface;
//imports
import ShopWebSiteInterface.MainMenu;
import databaseConnection.DataAccess;
import java.sql.ResultSet;
import databaseConnection.ResultSetCustomized;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Queries.User;
import com.sun.javafx.scene.control.skin.FXVK;

/**
 **
 ** @author Ana Elena Ulate Salas
 **/
// Class LogIn that extends of java swing 
public class LogIn extends javax.swing.JFrame {
    //Variable private of Data Base
    private final DataAccess dataAccess;
    /**
     * Creates new form LogIn
     */
    //Constructor
    public LogIn(DataAccess dataAccess) {
        initComponents();
        setLocationRelativeTo(null);
        this.dataAccess = dataAccess;  
    }
    // go back MainFrame
      public void MenuBack(){
        setVisible(false);
        MainFrame back = new MainFrame(dataAccess);
        back.setVisible(true);  
    }
     // method to clear text field
      public void clearTF(){
          tfUsername.setText("");
          pfPassword.setText("");
      }
      // method of access to the program, checking that data are the same data in database 
      public void access(){
        String email = "";
        String password = "";
        ResultSetCustomized Result;
        User users = new User();
         char [] pass = pfPassword.getPassword();
         String passwordText = "";
             for (char p : pass ){
                 passwordText += p;
             }
        Result =  users.LogIn(dataAccess, tfUsername.getText(), passwordText);
         try {
             if (Result.getResultSet().next()){
                 email = Result.getResultSet().getString("email");
                 password = Result.getResultSet().getString("userPassword");
             }  // Validation of the email(username) and password exist in Data Base
                 if (email.equals(tfUsername.getText()) && password.equals(passwordText)){
                     users.updateUserState(dataAccess, true, email);
                     setVisible(false);
                     MainMenu mm = new MainMenu(dataAccess);
                     mm.setVisible(true);
                 }
                 else{
                     JOptionPane.showMessageDialog(this, "Username or Password wrong", "Information", JOptionPane.ERROR_MESSAGE);
                     clearTF();
                 }

         } catch (SQLException ex) {
             Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
         }

      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbLogIn = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        btAccess = new javax.swing.JButton();
        pfPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miBack = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbLogIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbLogIn.setText("Log In");

        lbPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPassword.setText("Password:");

        btAccess.setText("Access");
        btAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAccessActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Username:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(lbLogIn)
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btAccess)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(lbPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(tfUsername))
                        .addGap(77, 77, 77))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbLogIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPassword))
                .addGap(30, 30, 30)
                .addComponent(btAccess)
                .addGap(59, 59, 59))
        );

        jMenu1.setText("Option");

        miBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/go-back-icon.png"))); // NOI18N
        miBack.setText("Back");
        miBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBackActionPerformed(evt);
            }
        });
        jMenu1.add(miBack);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBackActionPerformed
        MenuBack();
    }//GEN-LAST:event_miBackActionPerformed

    private void btAccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAccessActionPerformed
        access();
    }//GEN-LAST:event_btAccessActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        JOptionPane.showMessageDialog(null, "The program most be started in main class", "Warning", JOptionPane.WARNING_MESSAGE);
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAccess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbLogIn;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JMenuItem miBack;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}