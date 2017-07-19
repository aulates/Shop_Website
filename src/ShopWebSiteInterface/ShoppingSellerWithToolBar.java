/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopWebSiteInterface;
//imporst
import Queries.Product;
import Queries.User;
import databaseConnection.DataAccess;
import databaseConnection.DatabaseUtils;
import databaseConnection.Result;
import databaseConnection.ResultSetCustomized;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
// class that extends to Tool Bar Interface
public class ShoppingSellerWithToolBar extends ToolBarInterface{
    // Variables
    private DataAccess dataAccess;
    private Integer id = null;
    private String productNameFilter;
    private String productCodeFilter;
    Product product = new Product();
    User user = new User();
    /**
     ** Creates new form ShoppingSeller
     **/
    //Constructor
    public ShoppingSellerWithToolBar(DataAccess dataAccess) {
        initComponents();
        setLocationRelativeTo(null);        
        this.dataAccess = dataAccess; 
        ssToolbar.setToolBarListeners();
        this.setStateOfGroup( jpAtributtes, false);
        this.refreshData(false);
        this.enableRowSelectionListener();
    }
    //Metehod to go go back to ma principal menu
    public void menuBack(){
        setVisible(false);
        MainMenu mm = new MainMenu(dataAccess);
        mm.setVisible(true);
    }
    // Method to allow selection of a row
    private void enableRowSelectionListener(){
        ListSelectionModel lsm = tAddProducts.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener((ListSelectionEvent e) -> {
            if (tAddProducts.getSelectedRow() >= 0)
                updateWithSelectedRow(tAddProducts.getSelectedRow());
        });
        this.selectFirstRowifExist();
    }    
    // method to update the selected row
    private void updateWithSelectedRow(int index){
        try {
            this.id = (Integer) tAddProducts.getModel().getValueAt(index, 0);
            cbCode.setSelectedItem(tAddProducts.getModel().getValueAt(index, 1).toString());
            tfProductName.setText(tAddProducts.getModel().getValueAt(index, 2).toString());
            tfPrice.setText(tAddProducts.getModel().getValueAt(index, 3).toString());
            cbState.setSelectedItem(tAddProducts.getModel().getValueAt(index, 4).toString());
            cbAmount.setSelectedItem(tAddProducts.getModel().getValueAt(index, 5).toString());
            cbCountry.setSelectedItem(tAddProducts.getModel().getValueAt(index, 6).toString());
        } catch (Exception e) {
            id = null;
            clearTextField();
        }
    }
    // method to select the fisrt row if this exist
    private void selectFirstRowifExist(){
        if(tAddProducts.getRowCount() > 0)
            tAddProducts.setRowSelectionInterval(0, 0);
    }
    //Method for concentrating the table in name and code
    private void primaryControlRequestFocus(){
        this.tfProductName.requestFocus();
        this.cbCode.requestFocus();
    }
    //Method to initialize the state of the buttons
    private void setStateOfGroup(JPanel panelGroup, Boolean state){
        for (Component c: panelGroup.getComponents()){
            c.setEnabled(state);
        }
        this.tAddProducts.setEnabled(!state);
    }
    //Method to refresh table data showing the products being added
    private void refreshData(boolean isFiltered){
        productNameFilter = tfProductName.getText();
        productCodeFilter = cbCode.getSelectedItem().toString();
        int idUser = 0;
        ResultSetCustomized rs;
        if(!isFiltered){
            rs = user.currentUser(dataAccess);
            try {
                if(rs.getResultSet().next()){
                    idUser = Integer.parseInt(rs.getResultSet().getString("id"));
                }
            } catch (Exception e) {
            }
            
            rs = product.getAllSellerProduct(dataAccess, idUser);
        }
        else{
            rs = product.searchProductByName(dataAccess, productNameFilter, productCodeFilter);
        }
        if(rs.isError()){
            JOptionPane.showMessageDialog(this, rs.getErrorDescription(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = DatabaseUtils.getDefaultTableModel(rs.getResultSet(), product.getIdentifiers());
            tAddProducts.setModel(model);
            model.fireTableDataChanged();
            tAddProducts.removeColumn(tAddProducts.getColumnModel().getColumn(1)); 
            tAddProducts.removeColumn(tAddProducts.getColumnModel().getColumn(0));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAddProducts = new javax.swing.JTable();
        ssToolbar = new ShopWebSiteInterface.Toolbar();
        jpAtributtes = new javax.swing.JPanel();
        cbState = new javax.swing.JComboBox<>();
        tfPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbCountry = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tfProductName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbAmount = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbCode = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miBack = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tAddProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tAddProducts);

        cbState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New", "Used" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Country:");

        cbCountry.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic (CAR)", "Chad", "Chile", "China", "Colombia", "Comoros", "Democratic Republic of the Congo", "Republic of the Congo", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia ", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar ", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates (UAE)", "United Kingdom (UK)", "United States of America (USA)", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Product Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Amount:");

        cbAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbAmount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Code:");

        cbCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Home, Garden & Tools", "Sports & Outdoors", "Food & Grocery", "Automotive & Industrial", "Beauty & Health", "Books & Audible", "Toys, Kids & Baby", "Clothing, Shoes & Jewelry", "Electronics, Computers & Office", "Tablets", "Music" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("State:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Price :");

        javax.swing.GroupLayout jpAtributtesLayout = new javax.swing.GroupLayout(jpAtributtes);
        jpAtributtes.setLayout(jpAtributtesLayout);
        jpAtributtesLayout.setHorizontalGroup(
            jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAtributtesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jpAtributtesLayout.setVerticalGroup(
            jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAtributtesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAtributtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ssToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpAtributtes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ssToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpAtributtes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBackActionPerformed
       menuBack();
    }//GEN-LAST:event_miBackActionPerformed
    /**
     ** @param args the command line arguments
     **/
    public static void main(String args[]) {
      JOptionPane.showMessageDialog(null, "The program most be started in main class", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbAmount;
    private javax.swing.JComboBox<String> cbCode;
    private javax.swing.JComboBox<String> cbCountry;
    private javax.swing.JComboBox<String> cbState;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpAtributtes;
    private javax.swing.JMenuItem miBack;
    private ShopWebSiteInterface.Toolbar ssToolbar;
    private javax.swing.JTable tAddProducts;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfProductName;
    // End of variables declaration//GEN-END:variables
    // methods of tool bar method
    // method to create new product
    @Override
    public boolean jbNewActionPerfomed(ActionEvent evt) {
        this.clearTextField();
        this.setStateOfGroup(jpAtributtes, true);
        this.tfProductName.requestFocus();
        this.tfPrice.requestFocus();
        return true;
    }
    //method to edit product
    @Override
    public boolean jbEditActionPerfomed(ActionEvent evt) {
        if(tAddProducts.getSelectedRow() >= 0){
            this.setStateOfGroup(jpAtributtes, true);
            this.primaryControlRequestFocus();
            return true;
        }
        else{
            JOptionPane.showMessageDialog(this, "You most select the row you want to modify", "Information!", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    //method to save products
    @Override
    public boolean jbSaveActionPerfomed(ActionEvent evt) {
        ResultSetCustomized result; 
        result = user.currentUser(dataAccess);
        int idUser = 0;
                try {
                    if (result.getResultSet().next()) {
                    idUser = result.getResultSet().getInt("id");
                        System.out.println(idUser);
                     }
        } catch (Exception e) {
        }
        if(validationLetters() && validationNum()){
            
            if(ssToolbar.isInserting()){
                int idProduct = product.createProduct(dataAccess, cbCode.getSelectedItem().toString(), 
                        cbState.getSelectedItem().toString(), cbCountry.getSelectedItem().toString(),
                        tfProductName.getText(), Integer.parseInt(tfPrice.getText()),idUser);
                        product.createUserProducts(dataAccess,idUser ,
                        idProduct, Integer.parseInt(cbAmount.getSelectedItem().toString()));
            }
            else{
                product.updateProductPrice(dataAccess, Integer.parseInt(tfPrice.getText().toString()), tfProductName.getText());
                product.updateUserProductAmount(dataAccess, Integer.parseInt(cbAmount.getSelectedItem().toString())
                        , idUser);
            }
        }
        this.clearTextField();
        this.setStateOfGroup(jpAtributtes, false);
        this.refreshData(this.ssToolbar.isFiltered());
        this.selectFirstRowifExist();
        return true;
    }
    //method to cancel actions
    @Override
    public boolean jbCancelActionPerfomed(ActionEvent evt) {
        this.clearTextField();
        this.setStateOfGroup(jpAtributtes, false);
        this.updateWithSelectedRow(tAddProducts.getSelectedRow());
        return true;
    }
    //method to delete products
    @Override
    public boolean jbDeleteActionPerfomed(ActionEvent evt) {
        if(tAddProducts.getSelectedRow() >= 0){
            if(JOptionPane.showConfirmDialog(this, "Are you sure, you want to delet this product?", "Information!",
                    JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE ) == JOptionPane.NO_OPTION){
                return false;
            }
            else{
                int temp = this.id;
                Result result = product.deleteUserProduct(dataAccess, temp);
                if(!result.isError()){
                    product.deleteProduct(dataAccess, temp);
                }
                else{
                    JOptionPane.showMessageDialog(this, result.getErrorDescription(), "Error", JOptionPane.ERROR_MESSAGE);
                   }
            }
            this.clearTextField();
            this.refreshData(ssToolbar.isFiltered());
            this.selectFirstRowifExist();
            return true;
        }
        else{
            JOptionPane.showMessageDialog(this, "You must select the row to delete it", "Information!", JOptionPane.INFORMATION_MESSAGE);
            return false;    
        }
    }
    //method to search products
    @Override
    public boolean jbFindActionPerfomed(ActionEvent evt) {
        this.clearTextField();
        this.setStateOfGroup(jpAtributtes, true);
        this.primaryControlRequestFocus();
        return true;
    }
    // method to filter the search
    @Override
    public boolean jbFilterActionPerfomed(ActionEvent evt) {
        refreshData(true);
        clearTextField();
        setStateOfGroup(jpAtributtes, false);
        selectFirstRowifExist();
        return true;
    }
    //method too reload 
    @Override
    public boolean jbReloadActionPerfomed(ActionEvent evt) {
        clearTextField();
        this.refreshData(false);
        selectFirstRowifExist();
        return true;
    }
    //method to exit the action
    @Override
    public boolean jbExitActionPerfomed(ActionEvent evt) {
        return true;
    }
    //method to validation of numbers
    @Override
    boolean validationNum() {
        char[] num;
        boolean state;
        state = true;
        num = tfPrice.getText().toLowerCase().toCharArray();
        for (char i = 'a'; i <= 'z'; i++) {
             for (int j = 0; j < num.length; j++) {
                if(num[j] == i || tfPrice.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Price is empty or contains a letter", "Information!", JOptionPane.INFORMATION_MESSAGE);
                    clearTextField();
                    state = false;
                }
            }
        }
        return state;
    }
    //validation to validation letters
    @Override
    boolean validationLetters() {
        boolean state;
        state = true;
        if(tfProductName.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Product name is empty", "Information!", JOptionPane.INFORMATION_MESSAGE);
            clearTextField();
            state = false;
        }
        return state;
    }
    @Override
    void clearTextField() {
        tfPrice.setText("");
        tfProductName.setText("");
    }
    @Override
    public void addBuy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void cancelBuy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void reloadBuy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}