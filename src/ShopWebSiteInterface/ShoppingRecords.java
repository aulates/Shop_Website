/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopWebSiteInterface;
import Queries.Product;
import Queries.User;
import databaseConnection.DataAccess;
import databaseConnection.DatabaseUtils;
import databaseConnection.ResultSetCustomized;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
public class ShoppingRecords extends javax.swing.JFrame {
    private DataAccess dataAccess;
    private int id;
    private int idUser;
    private int idUserProducts;
    private int amountProduct;
    private String productName;
    private int price;
    private int total;
    private String userType;
    Product product = new Product();
    User user = new User();
    /**
     ** Creates new form ShoppingRecords
     **/
     //Constructor
    public ShoppingRecords(DataAccess dataAccess) {
        initComponents();
        setLocationRelativeTo(null);
        this.dataAccess = dataAccess;
        loadData();
        enableRowSelectionListener();
        loadLbl();
    }
    //Method to go back to profile
    public void menuBack(){
        setVisible(false);
        Profile pf = new Profile(dataAccess);
        pf.setVisible(true);
    }
    // method to load label witn total in shops
    public void loadLbl(){
        ResultSetCustomized rsc = null;
        if (userType.equals("Consumer")) {
            rsc = product.getTotal(dataAccess, idUser);
        }
        else{
            rsc = product.getTotalSold(dataAccess, idUser);
        }
        int res = 0;
        try {
            if (rsc.getResultSet().next()) {
                res = rsc.getResultSet().getInt("sum");
            }
        } catch (Exception e) {
        };
        lbTotal.setText("Total in shops: $" +Integer.toString(res));
        
    }
    // method to load data in table records
    public void loadData(){
        ResultSetCustomized rs;
        rs = user.currentUser(dataAccess);;
                try {
                    if (rs.getResultSet().next()) {
                    idUser = rs.getResultSet().getInt("id");
                    userType = rs.getResultSet().getString("user_type");
                     }
        } catch (Exception e) {
        }
        if (userType.equals("Consumer")) {
            rs = product.getBoughtProducts(dataAccess, idUser );
        }
        else{
            rs = product.SelectTotalSold(dataAccess, idUser);
            taShoppings.setVisible(false);
        }
        
        if(rs.isError()){
            JOptionPane.showMessageDialog(this, rs.getErrorDescription(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = null;
            if (userType.equals("Consumer")) {
                model = DatabaseUtils.getDefaultTableModel(rs.getResultSet(), product.getIdentifiers());
                tRecords.setModel(model);
                model.fireTableDataChanged();
                tRecords.removeColumn(tRecords.getColumnModel().getColumn(7));
                tRecords.removeColumn(tRecords.getColumnModel().getColumn(6));
                tRecords.removeColumn(tRecords.getColumnModel().getColumn(5));
                tRecords.removeColumn(tRecords.getColumnModel().getColumn(4));
                tRecords.removeColumn(tRecords.getColumnModel().getColumn(3));
                tRecords.removeColumn(tRecords.getColumnModel().getColumn(2));
                tRecords.removeColumn(tRecords.getColumnModel().getColumn(1));
            }
            else{
                model = DatabaseUtils.getDefaultTableModel(rs.getResultSet(), product.getIdentifiersTotalSold());
                tRecords.setModel(model);
                model.fireTableDataChanged();
            }
            
        }
    }
    // Method to allow selection of a row
    private void enableRowSelectionListener(){
        ListSelectionModel lsm = tRecords.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener((ListSelectionEvent e) -> {
            if (tRecords.getSelectedRow() >= 0)
                updateWithSelectedRow(tRecords.getSelectedRow());
        });
        this.selectFirstRowifExist();
    }
    // method to select the fisrt row if this exist
    private void selectFirstRowifExist(){
        if(tRecords.getRowCount() > 0)
            tRecords.setRowSelectionInterval(0, 0);
    }
    // method to update the selected row
    private void updateWithSelectedRow(int index){
        try {
            this.idUserProducts = (Integer)tRecords.getModel().getValueAt(index, 0);
            this.id = (Integer) tRecords.getModel().getValueAt(index, 1);
            this.amountProduct = (Integer) tRecords.getModel().getValueAt(index, 6);
            this.productName = (String) tRecords.getModel().getValueAt(index, 3);
            this.price = (Integer) tRecords.getModel().getValueAt(index, 4); 
            this.total = this.price * this.amountProduct;
            String message = "Product: " + productName + "\nAmount: " + amountProduct 
                    + "\nPrice: " + price + "\nTotal: " + total;
            taShoppings.setText(message);
        } catch (Exception e) {
            id = 0;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tRecords = new javax.swing.JTable();
        lbTotal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taShoppings = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miBack = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tRecords.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tRecords.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tRecords);

        lbTotal.setText("$0.000.000.000");

        taShoppings.setColumns(20);
        taShoppings.setRows(5);
        jScrollPane2.setViewportView(taShoppings);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lbTotal)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(54, 54, 54)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(lbTotal)
                .addContainerGap())
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBackActionPerformed
        menuBack();
    }//GEN-LAST:event_miBackActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       JOptionPane.showMessageDialog(null, "The program most be started in main class", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JMenuItem miBack;
    private javax.swing.JTable tRecords;
    private javax.swing.JTextArea taShoppings;
    // End of variables declaration//GEN-END:variables
}