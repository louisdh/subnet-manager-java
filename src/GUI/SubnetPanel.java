/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package GUI;

import DatabaseConnection.DatabaseUtil;
import Localization.Localizer;
import Logic.SubnetHelper;
import Objects.HostAddress;
import Objects.Subnet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author louisdhauwe
 */
public class SubnetPanel extends javax.swing.JPanel {
    
    private Subnet subnet;

    /**
     * Listiner to update UI (when data changes)
     */
    public UpdateListener listener;
    
    private DefaultListModel model;
    
    /**
     * Creates new form SubnetPanel
     */
    public SubnetPanel() {
        initComponents();
    }
    
    /**
     *
     * @param subnet
     */
    public SubnetPanel(Subnet subnet) {               
        initComponents();
        
        this.subnet = subnet;
        nameLbl.setText(Localizer.NAME_SUBNET);
        networkAddressLbl.setText(Localizer.NETWORKADDRESS_SUBNET);
        subnetmaskLbl.setText(Localizer.SUBNETMASK_SUBNET);
        hostsLbl.setText(Localizer.HOSTS_SUBNET);
        
        changeNameBtn.setText(Localizer.EDIT);
        changeNetworkAddressBtn.setText(Localizer.EDIT);
        changeSubnetMaskBtn.setText(Localizer.EDIT);
        
        addHostBtn.setText(Localizer.ADD_NEW_HOST_SUBNET);
        deleteHostBtn.setText(Localizer.DELETE);
        
        nameField.setText(subnet.getName());
        networkAddressField.setText(subnet.getNetworkAddress().toString());
        subnetMaskField.setText(subnet.getSubnetMask().toString());
        
        model = new DefaultListModel();
        hostsList.setModel(model);
        
        for (HostAddress h : subnet.getHosts()) {
            if (!h.getHostName().equals("router")) {
                model.addElement(h.toString());
            }
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // if performance is bad: disable anti alias by commenting this line
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawBackground(g2);
    }
    
    private void drawBackground(Graphics2D g2) {
        g2.setColor(getBackground().brighter());
        for (int i = 0; i < getHeight()/4; i++) {
            g2.drawLine(0, i*20, getWidth(), i*4);
            g2.drawLine(0, i*4, getWidth(), i*-6);
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

        nameLbl = new javax.swing.JLabel();
        networkAddressLbl = new javax.swing.JLabel();
        subnetmaskLbl = new javax.swing.JLabel();
        hostsLbl = new javax.swing.JLabel();
        changeNetworkAddressBtn = new javax.swing.JButton();
        changeNameBtn = new javax.swing.JButton();
        changeSubnetMaskBtn = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        networkAddressField = new javax.swing.JTextField();
        subnetMaskField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        hostsList = new javax.swing.JList();
        addHostBtn = new javax.swing.JButton();
        deleteHostBtn = new javax.swing.JButton();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 153), 1, true));
        setMinimumSize(new java.awt.Dimension(456, 282));

        nameLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        nameLbl.setForeground(new java.awt.Color(255, 255, 255));
        nameLbl.setText("Naam:");

        networkAddressLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        networkAddressLbl.setForeground(new java.awt.Color(255, 255, 255));
        networkAddressLbl.setText("Netwerkadres:");

        subnetmaskLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        subnetmaskLbl.setForeground(new java.awt.Color(255, 255, 255));
        subnetmaskLbl.setText("Subnetmask:");

        hostsLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        hostsLbl.setForeground(new java.awt.Color(255, 255, 255));
        hostsLbl.setText("Hosts:");

        changeNetworkAddressBtn.setText("wijzig");
        changeNetworkAddressBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeNetworkAddressBtnActionPerformed(evt);
            }
        });

        changeNameBtn.setText("wijzig");
        changeNameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeNameBtnActionPerformed(evt);
            }
        });

        changeSubnetMaskBtn.setText("wijzig");
        changeSubnetMaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSubnetMaskBtnActionPerformed(evt);
            }
        });

        nameField.setEditable(false);
        nameField.setBackground(new java.awt.Color(204, 204, 204));
        nameField.setText("test");
        nameField.setToolTipText("");

        networkAddressField.setEditable(false);
        networkAddressField.setBackground(new java.awt.Color(204, 204, 204));
        networkAddressField.setText("test");
        networkAddressField.setToolTipText("");

        subnetMaskField.setEditable(false);
        subnetMaskField.setBackground(new java.awt.Color(204, 204, 204));
        subnetMaskField.setText("test");
        subnetMaskField.setToolTipText("");

        hostsList.setToolTipText("");
        hostsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                hostsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(hostsList);

        addHostBtn.setText("Voeg host toe");
        addHostBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHostBtnActionPerformed(evt);
            }
        });

        deleteHostBtn.setText("delete");
        deleteHostBtn.setEnabled(false);
        deleteHostBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHostBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(networkAddressLbl)
                    .addComponent(nameLbl)
                    .addComponent(subnetmaskLbl)
                    .addComponent(hostsLbl))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addHostBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteHostBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subnetMaskField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeSubnetMaskBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeNameBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(networkAddressField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeNetworkAddressBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(changeNameBtn)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(networkAddressLbl)
                            .addComponent(changeNetworkAddressBtn)
                            .addComponent(networkAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(changeSubnetMaskBtn)
                        .addComponent(subnetMaskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(subnetmaskLbl)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addHostBtn)
                            .addComponent(deleteHostBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hostsLbl)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changeNetworkAddressBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeNetworkAddressBtnActionPerformed
        String networkAddress = (String)JOptionPane.showInputDialog(null, Localizer.NEW_SUBNET_NETWORK_ADDRESS.replace("%@", subnet.getName()),
            "Input", JOptionPane.QUESTION_MESSAGE, null, null, networkAddressField.getText());
        if (networkAddress == null) return;

        while (!SubnetHelper.isValidNetworkAddress(networkAddress, subnet)) {    
     
            networkAddress = (String)JOptionPane.showInputDialog(null, Localizer.NETWORKADDRESS_NOT_VALID,
            Localizer.ERROR, JOptionPane.ERROR_MESSAGE, null, null, networkAddress);

            if (networkAddress == null) return;

        }

        this.subnet.setNetworkAddress(SubnetHelper.addressFromString(networkAddress));
        listener.update();
        
    }//GEN-LAST:event_changeNetworkAddressBtnActionPerformed

    private void changeNameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeNameBtnActionPerformed
        String name = (String)JOptionPane.showInputDialog(null, Localizer.NEW_SUBNET_NAME.replace("%@", subnet.getName()),
            "Input", JOptionPane.QUESTION_MESSAGE, null, null, nameField.getText());
        if (name == null) return;
        
        while (!SubnetHelper.isValidSubnetNameForSubnet(name, subnet)) {    
     
            name = (String)JOptionPane.showInputDialog(null, Localizer.NAME_NOT_VALID,
            Localizer.ERROR, JOptionPane.ERROR_MESSAGE, null, null, name);

            if (name == null) return;

        }

        this.subnet.setName(name);
        listener.update();
        
    }//GEN-LAST:event_changeNameBtnActionPerformed

    private void changeSubnetMaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSubnetMaskBtnActionPerformed
        String subnetmask = (String)JOptionPane.showInputDialog(null, Localizer.NEW_SUBNET_SUBNETMASK.replace("%@", subnet.getName()),
            "Input", JOptionPane.QUESTION_MESSAGE, null, null, subnetMaskField.getText());
        if (subnetmask == null) return;

        while (!SubnetHelper.isValidSubnetMask(subnetmask)) {       
            
            subnetmask = (String)JOptionPane.showInputDialog(null, Localizer.SUBNETMASK_NOT_VALID,
            Localizer.ERROR, JOptionPane.ERROR_MESSAGE, null, null, subnetmask);
            
            if (subnetmask == null) return;
            
        }
        
        this.subnet.setSubnetMask(SubnetHelper.addressFromString(subnetmask));
        listener.update();
        
    }//GEN-LAST:event_changeSubnetMaskBtnActionPerformed

    private void addHostBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHostBtnActionPerformed
        
        String name = JOptionPane.showInputDialog(Localizer.NEW_HOST_NAME);
        if (name == null) return;
        
        while (!SubnetHelper.isValidHostNameInSubnet(name, subnet)) {    
     
            name = (String)JOptionPane.showInputDialog(null, Localizer.NAME_NOT_VALID,
            Localizer.ERROR, JOptionPane.ERROR_MESSAGE, null, null, name);

            if (name == null) return;

        }
        
        
        String hostAddressString = (String)JOptionPane.showInputDialog(null, Localizer.NEW_HOST_IP_ADDRESS.replace("%@", name),
            "Input", JOptionPane.QUESTION_MESSAGE, null, null, SubnetHelper.networkPartForSubnet(subnet));
        if (hostAddressString == null) return;
        
        while (true) {    
            try {
                SubnetHelper.isValidHostAddress(hostAddressString, subnet);
            } catch (Exception e) {
                hostAddressString = (String)JOptionPane.showInputDialog(null, e.getMessage(),
                Localizer.ERROR, JOptionPane.ERROR_MESSAGE, null, null, hostAddressString);
                if (hostAddressString == null) return;
                continue;
            }
            if (hostAddressString == null) return;
            break;
        }

        HostAddress h = new HostAddress(SubnetHelper.addressFromString(hostAddressString).getAddress(), name);

        // update model

        subnet.addHost(h);
        DatabaseUtil.getInstance().setSaved(false);
        model.addElement(h.toString());
        hostsList.revalidate();
        listener.update();
        
    }//GEN-LAST:event_addHostBtnActionPerformed

    private void deleteHostBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHostBtnActionPerformed
        
        int index = hostsList.getSelectedIndex();
        subnet.removeHost(subnet.getHosts()[index]);
        model.addElement(hostsList.getSelectedValue());
        DatabaseUtil.getInstance().setSaved(false);
        hostsList.revalidate();
        listener.update();
        deleteHostBtn.setEnabled(false);
        
    }//GEN-LAST:event_deleteHostBtnActionPerformed

    private void hostsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_hostsListValueChanged
        deleteHostBtn.setEnabled(true);
    }//GEN-LAST:event_hostsListValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addHostBtn;
    private javax.swing.JButton changeNameBtn;
    private javax.swing.JButton changeNetworkAddressBtn;
    private javax.swing.JButton changeSubnetMaskBtn;
    private javax.swing.JButton deleteHostBtn;
    private javax.swing.JLabel hostsLbl;
    private javax.swing.JList hostsList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JTextField networkAddressField;
    private javax.swing.JLabel networkAddressLbl;
    private javax.swing.JTextField subnetMaskField;
    private javax.swing.JLabel subnetmaskLbl;
    // End of variables declaration//GEN-END:variables
}
