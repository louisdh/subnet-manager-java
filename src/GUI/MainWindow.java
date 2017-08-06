/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package GUI;

import DatabaseConnection.DatabaseUtil;
import Localization.Localizer;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author louisdhauwe
 */
public class MainWindow extends JFrame {
    private Component aComponent;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.setVisible(true);
        this.setTitle("Subnet Manager");
        menu.setText(Localizer.DATABASE_MENU);
        openDatabaseMenuItem.setText(Localizer.OPEN_DATABASE_MENU);
        saveDatabaseMenuItem.setText(Localizer.SAVE_DATABASE_MENU);
        newDatabaseMenuItem.setText(Localizer.NEW_DATABASE_MENU);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) { 
                if (DatabaseUtil.getInstance().isSaved()) {
                    DatabaseUtil.getInstance().closeConnection();
                    System.exit(0);
                }
                
                int result = showUnsavedPopup();
                if (result == 0) {
                    DatabaseUtil.getInstance().closeConnection();
                    System.exit(0);
                } else if (result == 1) {
                    // save here
                    DatabaseUtil.getInstance().saveDatabase();
                    DatabaseUtil.getInstance().closeConnection();
                    System.exit(0);
                }
            }
        });

    }
    
    /**
     * Show popup and returns index of clicked option
     * @return button index of clicked popup option
     */
    public int showUnsavedPopup() {
        
        String buttons[] = {Localizer.CLOSE, 
                                    Localizer.SAVE, 
                                    Localizer.CANCEL};
        String message = Localizer.DATABASE_NOT_SAVED_MESSAGE;
        String messageHTML = "<html><body><p style='width: 200px;'>" + message + "</body></html>";
        int result = JOptionPane.showOptionDialog(null,
                        messageHTML, 
                        Localizer.DATABASE_NOT_SAVED_TITLE,
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        buttons,
                        buttons[1]);
        
        return result;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanel1 = new GUI.TabPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        newDatabaseMenuItem = new javax.swing.JMenuItem();
        openDatabaseMenuItem = new javax.swing.JMenuItem();
        saveDatabaseMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(950, 400));
        setName("Subnet Manager"); // NOI18N
        setSize(new java.awt.Dimension(950, 700));

        tabPanel1.setBackground(new java.awt.Color(255, 255, 153));
        tabPanel1.setMinimumSize(new java.awt.Dimension(400, 346));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 204));

        menu.setText("Databank");

        newDatabaseMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newDatabaseMenuItem.setText("Nieuwe databank");
        newDatabaseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDatabaseMenuItemActionPerformed(evt);
            }
        });
        menu.add(newDatabaseMenuItem);

        openDatabaseMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openDatabaseMenuItem.setText("Open databank");
        openDatabaseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDatabaseMenuItemActionPerformed(evt);
            }
        });
        menu.add(openDatabaseMenuItem);

        saveDatabaseMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveDatabaseMenuItem.setText("Sla databank op");
        saveDatabaseMenuItem.setToolTipText("");
        saveDatabaseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDatabaseMenuItemActionPerformed(evt);
            }
        });
        menu.add(saveDatabaseMenuItem);

        jMenuBar1.add(menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tabPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tabPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void openDatabaseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDatabaseMenuItemActionPerformed
        DatabaseUtil.getInstance().openDatabase();
        tabPanel1.networksPanel.update();
        tabPanel1.routersPanel.repaint();
        
    }//GEN-LAST:event_openDatabaseMenuItemActionPerformed

    private void saveDatabaseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDatabaseMenuItemActionPerformed
        DatabaseUtil.getInstance().saveDatabase();

    }//GEN-LAST:event_saveDatabaseMenuItemActionPerformed

    private void newDatabaseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDatabaseMenuItemActionPerformed
        if (!DatabaseUtil.getInstance().isSaved()) {
            int result = showUnsavedPopup();
            if (result == 1) {
                // save here
                DatabaseUtil.getInstance().saveDatabase();
            } else if (result == 2) { 
                // cancel
                return;
            }
        }
                
        DatabaseUtil.getInstance().newDatabase();
        tabPanel1.networksPanel.update();
        tabPanel1.routersPanel.repaint();
    }//GEN-LAST:event_newDatabaseMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            new MainWindow().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem newDatabaseMenuItem;
    private javax.swing.JMenuItem openDatabaseMenuItem;
    private javax.swing.JMenuItem saveDatabaseMenuItem;
    private GUI.TabPanel tabPanel1;
    // End of variables declaration//GEN-END:variables
}