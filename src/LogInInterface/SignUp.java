/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInInterface;
//Imports
import javax.swing.JOptionPane;
import databaseConnection.DataAccess;
import Queries.User;
import databaseConnection.ResultSetCustomized;
import java.sql.ResultSet;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
public class SignUp extends javax.swing.JFrame {
    //Variables
    private final DataAccess dataAccess;
    private final User user = new User();
    //constructor of Sig Up
    public SignUp(DataAccess dataAccess) {
        initComponents();
        setLocationRelativeTo(null);
        this.dataAccess = dataAccess;
        tfAge.setEditable(false);
    }
    //Back to Main Frame
    public void menuBack() {
        setVisible(false);
        MainFrame mf = new MainFrame(dataAccess);
        mf.setVisible(true);
    }
    //Method to clear text fields
    public void clearTF() {
        tfName.setText("");
        tfId.setText("");
        tfAge.setText("");
        tfEmail.setText("");
        tfCommercialReason.setText("");
        tfSocialReasons.setText("");
        pfPassword.setText("");
    }
    //Method to show/hide frame components
    public void SellerType() {
        String anwser = cbSellerType.getSelectedItem().toString();
        if (anwser.equals("Company")) {
            tfSocialReasons.setEditable(true);
            tfCommercialReason.setEditable(true);
            tfAge.setEditable(false);
        } else {
            tfSocialReasons.setEditable(false);
            tfCommercialReason.setEditable(false);

            tfAge.setEditable(true);
        }
    }
    //Method to show/hide frame components
    public void UserType() {
        String anwser = cbUserType.getSelectedItem().toString();
        if (anwser.equals("Seller")) {
            cbSellerType.setVisible(true);
            SellerTypelbl.setVisible(true);
            tfSocialReasons.setEditable(false);
            tfCommercialReason.setEditable(false);
            tfSocialReasons.setEditable(false);
        } else {
            cbSellerType.setVisible(false);
            SellerTypelbl.setVisible(false);
            tfSocialReasons.setEditable(false);
            tfCommercialReason.setEditable(false);
            tfAge.setEditable(true);
        }
    }
    //Method to Registrate  User
    public void Registrate() {
        try {
            char [] pass = pfPassword.getPassword();
            String password = "";
            System.out.println(pass);
            for (char p : pass ){
                password += p;
            }
            int id = user.createUser(dataAccess, tfEmail.getText(), password , cbUserType.getSelectedItem().toString(), cbCountry.getSelectedItem().toString());
            if (cbUserType.getSelectedItem().equals("Consumer")) {
                user.createBuyerPerson(dataAccess, id, tfName.getText(), tfId.getText(), Integer.parseInt(tfAge.getText()));
            } else if (cbUserType.getSelectedItem().equals("Seller") && cbSellerType.getSelectedItem().equals("Person")) {
                user.createSellerPerson(dataAccess, id, tfName.getText(), tfId.getText(), Integer.parseInt(tfAge.getText()));
            } else if (cbSellerType.getSelectedItem().equals("Company")) {
                user.createSellerCompany(dataAccess, id, tfId.getText(), tfSocialReasons.getText(), tfCommercialReason.getText());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Registrate tha user, show message and back to the main frame
    public void addUser() {
        Registrate();
        JOptionPane.showMessageDialog(this, "The User is your email", "Welcome to Anita's Shop Website", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);
        MainFrame mf = new MainFrame(dataAccess);
        mf.setVisible(true);
    }
    //Validation they all data be not empty
    public void validation(){
        String userType = cbUserType.getSelectedItem().toString();
        String sellerType = cbSellerType.getSelectedItem().toString();
        if(userType.equals("Seller") && sellerType.equals("Company")){
            if(tfName.getText().equals("") || tfId.getText().equals("") || tfEmail.getText().equals("") || tfSocialReasons.getText().equals("") || tfCommercialReason.getText().equals("") || pfPassword.getPassword().toString().equals("")){
                JOptionPane.showMessageDialog(this, "Type all needed data", "Information!", JOptionPane.INFORMATION_MESSAGE);
                clearTF();
            }
            else{
                addUser();
            }
        }
        if (userType.equals("Seller") && sellerType.equals("Person")){
            if(tfName.getText().equals("") || tfId.getText().equals("") || tfEmail.getText().equals("") || tfAge.getText().equals("") || pfPassword.getPassword().toString().equals("")){
                JOptionPane.showMessageDialog(this, "Type all needed data", "Information!", JOptionPane.INFORMATION_MESSAGE);
                clearTF();
            }
            else{
              addUser();  
            }
        }
        if(userType.equals("Consumer")){
           if(tfName.getText().equals("") || tfId.getText().equals("") || tfEmail.getText().equals("") || tfAge.getText().equals("") || pfPassword.getPassword().toString().equals("")){
                JOptionPane.showMessageDialog(this, "Type all needed data", "Information!", JOptionPane.INFORMATION_MESSAGE);
                clearTF();
            }
            else{
              addUser(); 
            }
        } 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbSignUp = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        lbAge = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbCountry = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfId = new javax.swing.JTextField();
        tfAge = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbUserType = new javax.swing.JComboBox<>();
        SellerTypelbl = new javax.swing.JLabel();
        cbSellerType = new javax.swing.JComboBox<>();
        lbSocialReason = new javax.swing.JLabel();
        tfSocialReasons = new javax.swing.JTextField();
        lbCommercialReason = new javax.swing.JLabel();
        tfCommercialReason = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        btAdd = new javax.swing.JButton();
        cbCountry = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuBack = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Sign Up");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jLabel1)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbSignUp.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbSignUp.setText("Sign Up");

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Name:");

        lbID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbID.setText("ID:");

        lbAge.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAge.setText("Age:");

        lbEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbEmail.setText("Email:");

        lbCountry.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbCountry.setText("Country:");

        jLabel8.setText("User Type:");

        cbUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seller", "Consumer" }));
        cbUserType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbUserTypeItemStateChanged(evt);
            }
        });
        cbUserType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUserTypeActionPerformed(evt);
            }
        });

        SellerTypelbl.setText("Seller Type:");

        cbSellerType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Company", "Person" }));
        cbSellerType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSellerTypeItemStateChanged(evt);
            }
        });
        cbSellerType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSellerTypeActionPerformed(evt);
            }
        });

        lbSocialReason.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSocialReason.setText("Social Reasons: ");

        lbCommercialReason.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbCommercialReason.setText("Commercial Reason:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Password:");

        btAdd.setText("Add");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        cbCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic (CAR)", "Chad", "Chile", "China", "Colombia", "Comoros", "Democratic Republic of the Congo", "Republic of the Congo", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia ", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar ", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates (UAE)", "United Kingdom (UK)", "United States of America (USA)", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btAdd)
                .addGap(58, 58, 58))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbAge)
                                    .addComponent(lbEmail)
                                    .addComponent(lbCountry)
                                    .addComponent(lbSocialReason)
                                    .addComponent(lbCommercialReason)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfEmail)
                                    .addComponent(tfAge)
                                    .addComponent(tfSocialReasons)
                                    .addComponent(tfCommercialReason, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(pfPassword)
                                    .addComponent(cbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SellerTypelbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbSellerType, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(lbID)
                                        .addGap(15, 15, 15))
                                    .addComponent(lbName, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfName)
                                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbSignUp)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SellerTypelbl)
                    .addComponent(cbSellerType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAge))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCountry)
                    .addComponent(cbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSocialReasons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSocialReason))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCommercialReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCommercialReason))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jMenu1.setText("Option");

        menuBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/go-back-icon.png"))); // NOI18N
        menuBack.setText("Back");
        menuBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBackActionPerformed(evt);
            }
        });
        jMenu1.add(menuBack);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBackActionPerformed
        menuBack();
    }//GEN-LAST:event_menuBackActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        validation();
    }//GEN-LAST:event_btAddActionPerformed

    private void cbSellerTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSellerTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSellerTypeActionPerformed

    private void cbSellerTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSellerTypeItemStateChanged
        SellerType();
    }//GEN-LAST:event_cbSellerTypeItemStateChanged

    private void cbUserTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUserTypeActionPerformed
    
    }//GEN-LAST:event_cbUserTypeActionPerformed

    private void cbUserTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbUserTypeItemStateChanged
        UserType();
    }//GEN-LAST:event_cbUserTypeItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        JOptionPane.showMessageDialog(null, "The program most be started in main class", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SellerTypelbl;
    private javax.swing.JButton btAdd;
    private javax.swing.JComboBox<String> cbCountry;
    private javax.swing.JComboBox<String> cbSellerType;
    private javax.swing.JComboBox<String> cbUserType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbAge;
    private javax.swing.JLabel lbCommercialReason;
    private javax.swing.JLabel lbCountry;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbSignUp;
    private javax.swing.JLabel lbSocialReason;
    private javax.swing.JMenuItem menuBack;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfAge;
    private javax.swing.JTextField tfCommercialReason;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfSocialReasons;
    // End of variables declaration//GEN-END:variables
}