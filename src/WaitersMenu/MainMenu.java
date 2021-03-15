/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WaitersMenu;

import java.awt.Color;
import static java.lang.String.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Tinny
 */
public class MainMenu extends javax.swing.JFrame {

    //The day I actually learned how to use get and set
    private String loggedInUsername;
    public String getUsername() {
        return loggedInUsername;
    }
    public void setUsername(String username) {
        this.loggedInUsername = username;
    }
    
    //Variables in which the details of each drink/whatever are stored.
    //Only need the getter function because to set you call `getItem_Names().add("unicorn dreams")`
    private ArrayList<String> item_Names = new ArrayList<>();
    public ArrayList getItem_Names() {
        return item_Names;
    }
    private ArrayList<Integer> item_IDs = new ArrayList<>();
    public ArrayList getItem_IDs() {
        return item_IDs;
    }
    private ArrayList<Float> item_Prices = new ArrayList<>();
    public ArrayList getItem_Prices() {
        return item_Prices;
    }
    
    //Used to create trash icon labels for the article rows in the current receipt
    private ArrayList<JLabel> listOfLabels = new ArrayList<>();
    public ArrayList getListOfLabels() {
        return listOfLabels;
    }
    

    
    /**
     * Creates new form MainMenu
     */
    
    public MainMenu() {
        //INITIALIZED WITHOUT LOGIN---------------------------------------------
        initComponents();
        //INITIALIZED WITHOUT LOGIN---------------------------------------------
        this.setExtendedState(MainMenu.MAXIMIZED_BOTH);
        //INITIALIZED WITHOUT LOGIN---------------------------------------------
        getAvailabaleGoods();
        //INITIALIZED WITHOUT LOGIN---------------------------------------------
    }
    
    public MainMenu(String username) {
        //Need to set username because the initComponents() method needs it
        setUsername(username);
        initComponents();
        
        //Can't fill the ComboBox before it exists
        getAvailabaleGoods();
    }

    
    public void calculateSum() {
        DecimalFormat df = new DecimalFormat("0.00");
        DefaultTableModel model = (DefaultTableModel) currentReceiptTable.getModel();
        int rowCount = currentReceiptTable.getRowCount();
        float sum = 0;
        
        //To calculate the sum we have to get the current quantity of items, and multiply it with the unit price.
        //That is because df.format() outputs floats with a comma, which aren't usable for summing
        for (int i = 0; i < rowCount; i++) {
            //Get name of item (to get the price) + Qty and redo the calculations for SUM
            String nameOfItem = (String) model.getValueAt(i, 0);
            
            int currentValue = (int) model.getValueAt(i, 1);
            sum += currentValue * Float.parseFloat((String) getItem_Prices().get(getItem_Names().indexOf(nameOfItem)));

        }
        sumDisplayLabel.setText(valueOf(df.format(sum)));
    }
    
    public void getAvailabaleGoods() {
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT * FROM `items`";
        try {
            st = My_CNX.getConnection().prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                //Remeber it for manipulation
                getItem_Names().add(rs.getString("item_name"));
                getItem_IDs().add(rs.getString("item_id"));
                getItem_Prices().add(rs.getString("item_price"));
                //Add it to the box for selection
                selectWhatToAdd.addItem(rs.getString("item_name"));                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error! \n Unable to fetch items!", "Database Error", 2);
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int existsInTable(JTable table, String entry) {
        int rowCount = table.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            String rowEntry = table.getValueAt(i, 0).toString();
            if (entry.equals(rowEntry)) {
                return i;
            }
        }
        return -1;
    }
    
    //This function only updates the Quantity column
    public void updateIfExists(int rowNumber) {
        DecimalFormat df = new DecimalFormat("0.00");
        
        DefaultTableModel model = (DefaultTableModel) currentReceiptTable.getModel();
        String nameOfItem = (String) model.getValueAt(rowNumber, 0);
        //"updating" rows is deleting the old one and inserting a new one
        int currentValue = (int) model.getValueAt(rowNumber, 1) + 1;
        float newPrice = Float.parseFloat((String) getItem_Prices().get(getItem_Names().indexOf(nameOfItem)));
        model.removeRow(rowNumber);
        
        //New row creation: remove item name, quantity, total price for that row
        model.addRow(new Object[] { 
            getItem_Names().get(getItem_Names().indexOf(nameOfItem)), 
            currentValue, 
            df.format(newPrice * currentValue) 
        });
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
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        logoutIcon = new javax.swing.JLabel();
        usernamePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        selectWhatToAdd = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        sumDisplayLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        currentReceiptTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        addToReceipt = new javax.swing.JLabel();
        removeFromReceipt = new javax.swing.JLabel();
        clearCurrentReceipt = new javax.swing.JButton();
        finalizeCurrentReceipt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        nameLabel.setText("Caffe Name");
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WaitersMenu/images/trash.png"))); // NOI18N
        logoutIcon.setBackground(new Color (0, 0, 0, 0));
        logoutIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutIconMouseClicked(evt);
            }
        });

        jLabel1.setText(getUsername());
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout usernamePanelLayout = new javax.swing.GroupLayout(usernamePanel);
        usernamePanel.setLayout(usernamePanelLayout);
        usernamePanelLayout.setHorizontalGroup(
            usernamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usernamePanelLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        usernamePanelLayout.setVerticalGroup(
            usernamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usernamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(usernamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        selectWhatToAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        selectWhatToAdd.setToolTipText("");
        AutoCompletion.enable(selectWhatToAdd);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Previous receipts:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 486, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 337, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Add a new item to the receipt:");

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setText("SUM");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sumDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sumDisplayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        currentReceiptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Name", "Quantity", "Price"
            }
        ));
        currentReceiptTable.setEnabled(false);
        jScrollPane2.setViewportView(currentReceiptTable);
        currentReceiptTable.getTableHeader().setReorderingAllowed(false);
        currentReceiptTable.setRowHeight(50);
        currentReceiptTable.getColumnModel().getColumn(0).setMinWidth(250);
        currentReceiptTable.getColumnModel().getColumn(0).setMaxWidth(300);
        currentReceiptTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        currentReceiptTable.getColumnModel().getColumn(1).setMinWidth(75);
        currentReceiptTable.getColumnModel().getColumn(1).setMaxWidth(75);
        currentReceiptTable.getColumnModel().getColumn(1).setPreferredWidth(75);
        currentReceiptTable.getColumnModel().getColumn(2).setMinWidth(100);
        currentReceiptTable.getColumnModel().getColumn(2).setMaxWidth(100);
        currentReceiptTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel9.setText("Current receipt");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        addToReceipt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        addToReceipt.setText("+");
        addToReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToReceiptMouseClicked(evt);
            }
        });

        removeFromReceipt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        removeFromReceipt.setText("-");
        removeFromReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeFromReceiptMouseClicked(evt);
            }
        });

        clearCurrentReceipt.setText("Clear");
        clearCurrentReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearCurrentReceiptActionPerformed(evt);
            }
        });

        finalizeCurrentReceipt.setText("Finalize");
        finalizeCurrentReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizeCurrentReceiptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(selectWhatToAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addToReceipt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeFromReceipt))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 380, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(clearCurrentReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalizeCurrentReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clearCurrentReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(finalizeCurrentReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(selectWhatToAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addToReceipt)
                                .addComponent(removeFromReceipt)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addToReceiptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToReceiptMouseClicked
       int indexOfSelectedItem = getItem_Names().indexOf(selectWhatToAdd.getSelectedItem());
       DefaultTableModel model = (DefaultTableModel) currentReceiptTable.getModel();
       
       //Check if it already exists in the table. If it does then just """update""" it
       int rowNumber = existsInTable(currentReceiptTable, (String) getItem_Names().get(indexOfSelectedItem));
       if( rowNumber != -1){
           updateIfExists(rowNumber);
       } else {
           //New row creation: remove item name, quantity, total price for that row
           model.addRow(new Object[] {
               getItem_Names().get(indexOfSelectedItem), 
               1, 
               getItem_Prices().get(indexOfSelectedItem)
           });
       }
       calculateSum();
    }//GEN-LAST:event_addToReceiptMouseClicked

    private void logoutIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutIconMouseClicked
        JOptionPane.showMessageDialog(null, "Successfully logged out!", "Logged out", 2);
        System.exit(0);
    }//GEN-LAST:event_logoutIconMouseClicked

    private void removeFromReceiptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeFromReceiptMouseClicked
        int indexOfSelectedItem = getItem_Names().indexOf(selectWhatToAdd.getSelectedItem());
        DefaultTableModel model = (DefaultTableModel) currentReceiptTable.getModel();
       
       //Check if it already exists in the table. If it does then just """update""" it
       int rowNumber = existsInTable(currentReceiptTable, (String) getItem_Names().get(indexOfSelectedItem));
       if(rowNumber != -1){
           int currentValue = (int) model.getValueAt(rowNumber, 1);
           if (currentValue != 1) {
               //If there are multiple of the same items remove only one from the total Quantity
               model.removeRow(rowNumber);
               model.addRow(new Object[] {
                    getItem_Names().get(indexOfSelectedItem), 
                    currentValue - 1, 
                    getItem_Prices().get(indexOfSelectedItem)
                });
           } else {
               //if there is only one item in the row just remove it
               model.removeRow(rowNumber);
           }
       } else {
           JOptionPane.showMessageDialog(null, "No more items of that type on receipt!", "Already empty", 2);
       }
       calculateSum();
    }//GEN-LAST:event_removeFromReceiptMouseClicked

    private void clearCurrentReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearCurrentReceiptActionPerformed
        clearCurrentReceipt();
    }//GEN-LAST:event_clearCurrentReceiptActionPerformed

    private void clearCurrentReceipt() {
        DefaultTableModel model = (DefaultTableModel) currentReceiptTable.getModel();
        if (currentReceiptTable.getRowCount() > 0) {
            for(int i = currentReceiptTable.getRowCount(); i > 0; i--) {
                model.removeRow(i - 1);
            }
            sumDisplayLabel.setText("0");
        } else {
            JOptionPane.showMessageDialog(null, "Current receipt is empty!", "Receipt empty", 2);
        }
    }
    private void finalizeCurrentReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizeCurrentReceiptActionPerformed
        DefaultTableModel model = (DefaultTableModel) currentReceiptTable.getModel();
        
        PreparedStatement st;
        ResultSet rs;
        String query;
        int status = -2;
        int newReceiptId = -1;
        
        String itemName;
        int itemQuantity;
        int indexOfItem;
        int itemId;
        
        if(currentReceiptTable.getRowCount() > 0) {
            
            //Make a new receipt in receipts (get its ID later)
            query = "INSERT INTO `receipts` VALUES ()";
            
            try {
                st = My_CNX.getConnection().prepareStatement(query);
                status = st.executeUpdate();
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Could not create a new receipt!\nExiting program.", "Critical Error!", 2);
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //This block should never happen but just in case...
            if (status == -2) {
                JOptionPane.showMessageDialog(null, "Receipt created unsuccessfully!\nExiting program.", "Critical Error!", 2);
                System.exit(0);
            }
            
            //Get the receipt ID of the newly created receipt
            query = "SELECT * FROM receipts ORDER BY `receipt_id` DESC LIMIT 1 ";
            try {
                st = My_CNX.getConnection().prepareStatement(query);
                rs = st.executeQuery();
                while (rs.next()) {
                    newReceiptId = Integer.parseInt(rs.getString("receipt_id"));
                }
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Could not get receipt ID!\nExiting program.", "Critical Error!", 2);
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
            
            //If a new receipt has been successfully created:
            //Get all of the contents of the current receipt from the table, and send them to the DB
            for (int i = 0; i < currentReceiptTable.getRowCount(); i++) {
                //Get all of the contents of the current row, and get the item ID and Quantity to send to DB
                itemQuantity = (int) model.getValueAt(i, 1);
                itemName = (String) model.getValueAt(i, 0);
                indexOfItem = getItem_Names().indexOf(itemName);
                itemId = Integer.parseInt((String) getItem_IDs().get(indexOfItem));
                
                //Add the appropriate item and quantity to the receipt_items, under the beforementioned receipt ID                
                query = "INSERT INTO `receipt_items`(`rececipt_itemList_id`, `item_id`, `item_quantity`) VALUES (?, ?, ?)";
                try {
                    st = My_CNX.getConnection().prepareStatement(query);
                    st.setString(1, String.valueOf(newReceiptId));
                    st.setString(2, String.valueOf(itemId));
                    st.setString(3, String.valueOf(itemQuantity));
                    st.executeUpdate();
                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Could not finalize receipt!\nExiting program.", "Critical Error!", 2);
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(0);
                }
            }
            clearCurrentReceipt();
            JOptionPane.showMessageDialog(null, "Receipt successfuly saved!", "Success", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Current receipt is empty!", "Receipt empty", 2);
        }
    }//GEN-LAST:event_finalizeCurrentReceiptActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addToReceipt;
    private javax.swing.JButton clearCurrentReceipt;
    private javax.swing.JTable currentReceiptTable;
    private javax.swing.JButton finalizeCurrentReceipt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel removeFromReceipt;
    private javax.swing.JComboBox<String> selectWhatToAdd;
    private javax.swing.JLabel sumDisplayLabel;
    private javax.swing.JPanel usernamePanel;
    // End of variables declaration//GEN-END:variables
}
