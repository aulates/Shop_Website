/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//imports
package ShopWebSiteInterface;
import Queries.CommentsAndStars;
import Queries.Product;
import Queries.User;
import databaseConnection.DataAccess;
import databaseConnection.DatabaseUtils;
import databaseConnection.ResultSetCustomized;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
public class Profile extends javax.swing.JFrame {
    private DataAccess dataAccess;
    private int id;
    private int idUser;
    private int idUserProducts;
    private String userType;
    Product product = new Product();
    User user = new User();
    CommentsAndStars cmm = new CommentsAndStars();
    /**
     ** Creates new form Profile
     **/
    //Constructor
    public Profile(DataAccess dataAccess) {
        initComponents();
        setLocationRelativeTo(null);
        this.dataAccess = dataAccess;
        loadData();
        enableRowSelectionListener();
        loadLbl();
        checkUserType();
    }
    //method to go back to the principal menu
    public void menuBack(){
        setVisible(false);
        MainMenu mm = new MainMenu(dataAccess);
        mm.setVisible(true);
    }
    public void loadLbl(){
        ResultSetCustomized rsc;
        rsc= user.getName(dataAccess);
        String res = "";
        try {
            if (rsc.getResultSet().next()) {
                res = rsc.getResultSet().getString("email");
            }
        } catch (Exception e) {
        };
        lblNameProfile.setText(res);
        lblNameProfile.setHorizontalAlignment(lblNameProfile.CENTER);
        lblNameProfile.setVerticalAlignment(lblNameProfile.CENTER);
    }
    
    public void loadData(){
        ResultSetCustomized rs;
        rs = user.currentUser(dataAccess);
        int idUser = 0;
                try {
                    if (rs.getResultSet().next()) {
                    idUser = rs.getResultSet().getInt("id");
                    userType = rs.getResultSet().getString("user_type");
                     }
        } catch (Exception e) {
        }
        if (userType.equals("Seller")) {
            rs = cmm.getSellerComments(dataAccess, idUser);
        }else{
            rs = cmm.getBuyerComments(dataAccess);
        }
        
        if(rs.isError()){
            JOptionPane.showMessageDialog(this, rs.getErrorDescription(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = DatabaseUtils.getDefaultTableModel(rs.getResultSet(), cmm.getIdentifiers());
            tComments.setModel(model);
            model.fireTableDataChanged();
            tComments.removeColumn(tComments.getColumnModel().getColumn(0));
        }
    }
    // Method to allow selection of a row
    private void enableRowSelectionListener(){
        ListSelectionModel lsm = tComments.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener((ListSelectionEvent e) -> {
            if (tComments.getSelectedRow() >= 0)
                updateWithSelectedRow(tComments.getSelectedRow());
        });
        this.selectFirstRowifExist();
    }
    // method to select the fisrt row if this exist
    private void selectFirstRowifExist(){
        if(tComments.getRowCount() > 0)
            tComments.setRowSelectionInterval(0, 0);
    }
    // method to update the selected row
    private void updateWithSelectedRow(int index){
        try {
            this.idUserProducts = (Integer)tComments.getModel().getValueAt(index, 5);
            this.id = (Integer) tComments.getModel().getValueAt(index, 0);
        } catch (Exception e) {
            id = 0;
        }
    }
    
    public void checkUserType(){
        ResultSetCustomized result; 
        result = user.currentUser(dataAccess);
        String userType = "";
                try {
                    if (result.getResultSet().next()) {
                    userType = result.getResultSet().getString("user_type");
                    idUser = result.getResultSet().getInt("id");
                     }
        } catch (Exception e) {
        }
        if(userType.equals("Consumer")){
            taComments.setEnabled(false);
            btAnswerComment.setEnabled(false);

        }
    } 
    //methos to go to aswer comment 
    public void aswerComment(){
        setVisible(false);
        AswerComment ac = new AswerComment(dataAccess,idUserProducts );
        ac.setVisible(true);   
    }
    //method to the stars
    public void stars(){
        
    }
    public void shoppingRecords(){
        ShoppingRecords sr = new ShoppingRecords(dataAccess);
        setVisible(false);
        sr.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblNameProfile = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tComments = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        taComments = new javax.swing.JTextArea();
        btAnswerComment = new javax.swing.JButton();
        lbStars = new javax.swing.JLabel();
        bShoppingRecords = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNameProfile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNameProfile.setText("Ana Elena Ulate Salas");
        lblNameProfile.setToolTipText("");
        lblNameProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblNameProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/profile-icon (1).png"))); // NOI18N

        tComments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tComments);

        taComments.setColumns(20);
        taComments.setRows(5);
        jScrollPane3.setViewportView(taComments);

        btAnswerComment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Comment-add-icon.png"))); // NOI18N
        btAnswerComment.setText("  Aswer Comment");
        btAnswerComment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAnswerComment.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAnswerComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnswerCommentActionPerformed(evt);
            }
        });

        lbStars.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbStars.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/five-Stars.png"))); // NOI18N

        bShoppingRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/product-icon.png"))); // NOI18N
        bShoppingRecords.setText("Shopping Records");
        bShoppingRecords.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bShoppingRecords.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bShoppingRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bShoppingRecordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bShoppingRecords, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lbStars)
                                .addGap(21, 21, 21))
                            .addComponent(lblNameProfile, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAnswerComment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(35, 35, 35))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNameProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbStars, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(bShoppingRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btAnswerComment)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Option");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/go-back-icon.png"))); // NOI18N
        jMenuItem1.setText("Back");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        menuBack();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btAnswerCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnswerCommentActionPerformed
        aswerComment();
    }//GEN-LAST:event_btAnswerCommentActionPerformed

    private void bShoppingRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bShoppingRecordsActionPerformed
        shoppingRecords();
    }//GEN-LAST:event_bShoppingRecordsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       JOptionPane.showMessageDialog(null, "The program most be started in main class", "Warning", JOptionPane.WARNING_MESSAGE);
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bShoppingRecords;
    private javax.swing.JButton btAnswerComment;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbStars;
    private javax.swing.JLabel lblNameProfile;
    private javax.swing.JTable tComments;
    private javax.swing.JTextArea taComments;
    // End of variables declaration//GEN-END:variables
}
