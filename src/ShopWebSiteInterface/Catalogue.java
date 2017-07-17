/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopWebSiteInterface;
// imports
import Queries.Product;
import Queries.User;
import databaseConnection.DataAccess;
import databaseConnection.DatabaseUtils;
import databaseConnection.ResultSetCustomized;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
public class Catalogue extends javax.swing.JFrame {
   // Variables
    private DataAccess dataAccess;
    private int id;
    private int idUserProducts;
    private int amoutProduct;
    Product product = new Product();
    User user = new User();
    /**
     ** Creates new form ViewProducts
     **/
    //Constructor
    public Catalogue(DataAccess dataAccess) {
        initComponents();
        setLocationRelativeTo(null);
        this.dataAccess = dataAccess;
        loadData();
        enableRowSelectionListener();
    }
    //method to go back to principal menu
    public void menuBack(){
        setVisible(false);
        MainMenu mm = new MainMenu(dataAccess);
        mm.setVisible(true);
    }
    // method to load data into the catalog table
    public void loadData(){
        String productCodeFilter;
        productCodeFilter = cbProducts.getSelectedItem().toString();
        ResultSetCustomized rs;
        rs = product.getAllSellersProductForCatalogue(dataAccess, productCodeFilter);
        if(rs.isError()){
            JOptionPane.showMessageDialog(this, rs.getErrorDescription(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = DatabaseUtils.getDefaultTableModel(rs.getResultSet(), product.getIdentifiers());
            tProducts.setModel(model);
            model.fireTableDataChanged();
            tProducts.removeColumn(tProducts.getColumnModel().getColumn(1));
            tProducts.removeColumn(tProducts.getColumnModel().getColumn(0));
        }
    }
    // Method to allow selection of a row
    private void enableRowSelectionListener(){
        ListSelectionModel lsm = tProducts.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener((ListSelectionEvent e) -> {
            if (tProducts.getSelectedRow() >= 0)
                updateWithSelectedRow(tProducts.getSelectedRow());
        });
        this.selectFirstRowifExist();
    }
    // method to select the fisrt row if this exist
    private void selectFirstRowifExist(){
        if(tProducts.getRowCount() > 0)
            tProducts.setRowSelectionInterval(0, 0);
    }
    // method to update the selected row
    private void updateWithSelectedRow(int index){
        try {
            this.idUserProducts = (Integer)tProducts.getModel().getValueAt(index, 0);
            this.id = (Integer) tProducts.getModel().getValueAt(index, 1);
            this.amoutProduct = (Integer) tProducts.getModel().getValueAt(index, 6);
        } catch (Exception e) {
            id = 0;
        }
    }
    //method to add products in the shopping cart
    public void addProductShoppingCart(){
        ResultSetCustomized result;
        result = user.currentUser(dataAccess);
        int idUser = 0;
        int amount = Integer.parseInt(cbAmount.getSelectedItem().toString());
                try {
                    if (result.getResultSet().next()) {
                    idUser = result.getResultSet().getInt("id");
                    
                     }
        } catch (Exception e) {
        }
        int remaning = this.amoutProduct - amount;
        if(remaning < 0){
            JOptionPane.showMessageDialog(this, "Not enough amount of this product.", "Information!", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            product.createUserProducts(dataAccess, idUser, this.id, Integer.parseInt(cbAmount.getSelectedItem().toString()));
            //product.updateUserProductAmountByUserProductId(dataAccess, remaning, idUserProducts);
            JOptionPane.showMessageDialog(this, "Added to cart", "Information!!", JOptionPane.INFORMATION_MESSAGE);
        }
        if (remaning == 0) {
            product.deleteUserProduct(dataAccess,idUserProducts);
        }
        loadData();
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
        jLabel1 = new javax.swing.JLabel();
        cbProducts = new javax.swing.JComboBox<>();
        btAddShoppingCart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tProducts = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbAmount = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miBack = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Products:");

        cbProducts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbProducts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Home, Garden & Tools", "Sports & Outdoors", "Food & Grocery", "Automotive & Industrial", "Beauty & Health", "Books & Audible", "Toys, Kids & Baby", "Clothing, Shoes & Jewelry", "Electronics, Computers & Office", "Tablets", "Music" }));
        cbProducts.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProductsItemStateChanged(evt);
            }
        });

        btAddShoppingCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/documento-de-archivo-g2176-papel-azul-icono-7267-48.png"))); // NOI18N
        btAddShoppingCart.setText("Add");
        btAddShoppingCart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAddShoppingCart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAddShoppingCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddShoppingCartActionPerformed(evt);
            }
        });

        tProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tProducts);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Amount:");

        cbAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbAmount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAddShoppingCart, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAddShoppingCart)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cbProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jMenu1.setText("Options");

        miBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/go-back-icon.png"))); // NOI18N
        miBack.setText("Back");
        miBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBackActionPerformed(evt);
            }
        });
        jMenu1.add(miBack);

        miExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/liberar-icono-5826-32.png"))); // NOI18N
        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBackActionPerformed
        menuBack();
    }//GEN-LAST:event_miBackActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_miExitActionPerformed

    private void btAddShoppingCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddShoppingCartActionPerformed
        addProductShoppingCart();
        
    }//GEN-LAST:event_btAddShoppingCartActionPerformed

    private void cbProductsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProductsItemStateChanged
        loadData();
    }//GEN-LAST:event_cbProductsItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        JOptionPane.showMessageDialog(null, "The program most be started in main class", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddShoppingCart;
    private javax.swing.JComboBox<String> cbAmount;
    private javax.swing.JComboBox<String> cbProducts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem miBack;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JTable tProducts;
    // End of variables declaration//GEN-END:variables
}