/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package GUI;

import Localization.Localizer;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author louisdhauwe
 */
public class TabPanel extends JPanel {
        
    /**
     * Shows and manages subnets (networks)
     */
    public NetworksPanel networksPanel;

    /**
     * Shows and manages routers and connections
     */
    public RoutersPanel routersPanel;

    /**
     * Creates new form TabPanel
     */
    public TabPanel() {
        
        super(new GridLayout(1, 1));
         
        JTabbedPane tabbedPane = new JTabbedPane();
        
        networksPanel = new NetworksPanel();
        tabbedPane.addTab(Localizer.NETWORKS_TAB, null, networksPanel, Localizer.NETWORKS_TAB_TOOLTIP);
                
        routersPanel = new RoutersPanel();
        tabbedPane.addTab(Localizer.ROUTERS_TAB, null, routersPanel, Localizer.ROUTERS_TAB_TOOLTIP);
       
        add(tabbedPane);
         
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
