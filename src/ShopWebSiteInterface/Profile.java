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
import javax.swing.ImageIcon;
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
    // variables
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
        if (tComments.getRowCount() > 0){
            setStarsImage();
        }
    }
    //method to go back to the principal menu
    public void menuBack(){
        setVisible(false);
        MainMenu mm = new MainMenu(dataAccess);
        mm.setVisible(true);
    }
    //method to load label with the username
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
    // method to load comments
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
    // method to check the user type
    public void checkUserType(){
        ResultSetCustomized result; 
        result = user.currentUser(dataAccess);
        //String userType = "";
                try {
                    if (result.getResultSet().next()) {
                    userType = result.getResultSet().getString("user_type");
                    idUser = result.getResultSet().getInt("id");
                     }
        } catch (Exception e) {
        }
        if(userType.equals("Consumer")){
            btAnswerComment.setEnabled(false);

        }
    }
    public int rating(){
        int rows = tComments.getRowCount();
        int sellerStars=0;
        int consumerStars=0;
        for (int i = 0; i < tComments.getRowCount(); i++){
            if ((Integer)tComments.getModel().getValueAt(i, 2) != null) {
                sellerStars += (Integer)tComments.getModel().getValueAt(i, 2);// 3rd column . row column indexes are 0 based
                System.out.println(sellerStars);
            }  
        }
        for (int i = 0; i < tComments.getRowCount(); i++){
            if ((Integer)tComments.getModel().getValueAt(i, 2) != null) {
                consumerStars += (Integer)tComments.getModel().getValueAt(i, 4);// 3rd column . row column indexes are 0 based
            System.out.println(consumerStars);
            } 
        }
        if(userType.equals("Consumer")){
            return  consumerStars/rows;
        }
        else{
            return sellerStars/rows;
        }
    }
    //methos to go to aswer comment 
    public void aswerComment(){
        setVisible(false);
        AswerComment ac = new AswerComment(dataAccess,idUserProducts );
        ac.setVisible(true);   
    }
    //method to get stars
    public int stars(){
        ResultSetCustomized rsc = null;
        int starAmount = 0;
        if (userType.equals("Seller")) {
            rsc = product.getTotalSold(dataAccess, idUser);
            int res = 0;
            try {
                if (rsc.getResultSet().next()) {
                    res = rsc.getResultSet().getInt("sum");
                }
            } catch (Exception e) {
            };
            int rating = rating();
            if (res > 0 && res <= 1000) {
                starAmount += 1;
            }
            if (res > 1000 && res <= 2000) {
                starAmount += 2;
            }
            if (res > 2000 && res <= 3000) {
                starAmount += 3;
            }
            if (res > 3000 && res <= 4000) {
                starAmount += 4;
            }
            if (res > 4000) {
                starAmount += 5;
            }
            return  rating + starAmount;
        }
        else{
            return rating();
        }
    }
    
    public void setStarsImage(){
        int stars = stars();
        if (stars == 1) {
              lbStars1.setVisible(false);
              lbStars2.setVisible(false);
              lbStars3.setVisible(false);
              lbStars4.setVisible(false);
//            ImageIcon iconLogo = new ImageIcon("one-Star.png");
//            lbStars.setIcon(iconLogo);
        }
        if (stars == 2) {
             lbStars1.setVisible(false);
             lbStars2.setVisible(false);
             lbStars3.setVisible(false);
//            ImageIcon iconLogo = new ImageIcon("two-Stars.png");
//            lbStars.setIcon(iconLogo);
        }
        if (stars == 3) {
             lbStars1.setVisible(false);
             lbStars2.setVisible(false);
//            ImageIcon iconLogo = new ImageIcon("three-Stars.png");
//            lbStars.setIcon(iconLogo);
        }
        if (stars == 4 ) {
              lbStars1.setVisible(false);
//            ImageIcon iconLogo = new ImageIcon("four-Stars.png");
//            lbStars.setIcon(iconLogo);
        }
        else{
             
//            ImageIcon iconLogo = new ImageIcon("five-Stars.png");
//            lbStars.setIcon(iconLogo);
        }
    }
    
    // Method to view shopping records
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
        btAnswerComment = new javax.swing.JButton();
        bShoppingRecords = new javax.swing.JButton();
        lbStars1 = new javax.swing.JLabel();
        lbStars2 = new javax.swing.JLabel();
        lbStars3 = new javax.swing.JLabel();
        lbStars4 = new javax.swing.JLabel();
        lbStars5 = new javax.swing.JLabel();
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

        btAnswerComment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Comment-add-icon.png"))); // NOI18N
        btAnswerComment.setText("  Aswer Comment");
        btAnswerComment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAnswerComment.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAnswerComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnswerCommentActionPerformed(evt);
            }
        });

        bShoppingRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/product-icon.png"))); // NOI18N
        bShoppingRecords.setText("Shopping Records");
        bShoppingRecords.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bShoppingRecords.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bShoppingRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bShoppingRecordsActionPerformed(evt);
            }
        });

        lbStars1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/one-Star.png"))); // NOI18N

        lbStars2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/one-Star.png"))); // NOI18N

        lbStars3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/one-Star.png"))); // NOI18N

        lbStars4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/one-Star.png"))); // NOI18N

        lbStars5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/one-Star.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btAnswerComment))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bShoppingRecords, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbStars1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbStars2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbStars3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbStars4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbStars5))
                                .addComponent(lblNameProfile)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNameProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbStars1)
                                .addComponent(lbStars2)
                                .addComponent(lbStars3))
                            .addComponent(lbStars4)
                            .addComponent(lbStars5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bShoppingRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAnswerComment))
                .addContainerGap(20, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbStars1;
    private javax.swing.JLabel lbStars2;
    private javax.swing.JLabel lbStars3;
    private javax.swing.JLabel lbStars4;
    private javax.swing.JLabel lbStars5;
    private javax.swing.JLabel lblNameProfile;
    private javax.swing.JTable tComments;
    // End of variables declaration//GEN-END:variables
}
