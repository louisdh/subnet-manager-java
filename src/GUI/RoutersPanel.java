/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package GUI;

import DatabaseConnection.DatabaseUtil;
import Localization.Localizer;
import Logic.SubnetColors;
import Logic.SubnetHelper;
import Objects.Router;
import Objects.RouterConnection;
import Objects.Subnet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author louisdhauwe
 */

public class RoutersPanel extends javax.swing.JPanel {    

    private int mouseX;
    private int mouseY;
    private Timer timer;
    private float connectionOffset = 0;
    private float backgroundOffset = 0;
    private float xVar = 0;
    
    private final int ROUTER_HEIGHT = 40;
    private final int ROUTER_WIDTH = 50;
        
    
    /**
     * Creates new form RoutersPanel
     */
    public RoutersPanel() {
        initComponents();
        routerBtn.setText(Localizer.ROUTER);
        connectionBtn.setText(Localizer.NEW_CONNECTION);
        backgroundOffset = (float) (Math.sin(xVar)*15f+15.2);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                
                Subnet[] subnets = DatabaseUtil.getInstance().getSubnets();
                if (subnets.length == 0) {
                    return;
                }
                
                xVar += 0.003;
                connectionOffset += 0.2;
                if (connectionOffset > 10) {
                    connectionOffset = 0;
                }

                backgroundOffset = (float) (Math.sin(xVar)*15f+15.2);
                

                repaint();

            }
        }, 0, 16);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        Graphics2D g2 = (Graphics2D)g;
        // if performance is bad: disable anti alias by commenting this line
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawBackground(g2);
        
        g2.setColor(Color.BLACK);

        
        Subnet[] subnets = DatabaseUtil.getInstance().getSubnets();
        
        if (subnets.length > 0) {

            drawConnections(g2);
            
            drawSubnets(g2);
            
            g2.setClip(0, 0, getWidth(), getHeight());
            
            drawRouters(g2);
            
            connectionBtn.setEnabled(true);
            routerBtn.setEnabled(true);

        } else {
            connectionBtn.setEnabled(false);
            routerBtn.setEnabled(false);
        }
        
        g2.setClip(0, 0, getWidth(), getHeight());

        
    }
    
    private void drawBackground(Graphics2D g2) {
        
        g2.setClip(0, jToolBar1.getHeight(), getWidth(), getHeight()-jToolBar1.getHeight());
        Color backgroundColor = new Color(53, 139, (int) (100*(backgroundOffset/25.0)));
        g2.setColor(backgroundColor.brighter().brighter());
        g2.fillRect(0, jToolBar1.getHeight(), getWidth(), getHeight()-jToolBar1.getHeight());
        g2.setColor(backgroundColor.brighter());
        int amount = 10;
        for (int i = 0; i < getHeight()/amount; i++) {
            g2.drawLine(0, (int) (i*amount*5 * backgroundOffset), getWidth(), i*amount);
            g2.drawLine(0, i*amount, getWidth(), (int) (i*amount*5 * backgroundOffset));
        }
        
    }
    
    private void drawSubnets(Graphics2D g2) {
        
        Subnet[] subnets = DatabaseUtil.getInstance().getSubnets();

        int gap = 15;
        int widthSubnet = (getWidth() - (subnets.length+1 ) * gap) /subnets.length;
        int i = 0;
        int yOffset = 35;
        int cornerRadius = 15;

        for (Subnet subnet : subnets) {
            int xOffset = i*widthSubnet + (i+1)*gap;
            g2.setClip(xOffset, yOffset, widthSubnet+gap, 105);
            if (subnetSelected() != null && subnetSelected().equals(subnet)) {
                g2.setColor(SubnetColors.subnetColors()[i].brighter());
            } else {
                g2.setColor(SubnetColors.subnetColors()[i]);
            }
            
            g2.fillRoundRect(xOffset, yOffset, widthSubnet, 100, cornerRadius, cornerRadius);
            g2.setColor(Color.WHITE);
            g2.drawLine(xOffset, yOffset+30, xOffset+widthSubnet, yOffset+30);
            g2.drawRoundRect(xOffset, yOffset, widthSubnet, 100, cornerRadius, cornerRadius);

            g2.setClip(xOffset, yOffset, widthSubnet, 105);
            
            g2.setFont(new Font("default", Font.BOLD, 14));
            g2.drawString(subnet.getName(), xOffset + 10, yOffset+20);
            
            g2.setFont(new Font("default", Font.PLAIN, 14));
            g2.drawString(subnet.toString(), xOffset + 10, yOffset+50);

            i++;
        }
            
    }
    
    private void drawRouters(Graphics2D g2) {
        
        Router[] routers = DatabaseUtil.getInstance().getRouters();

        
        int i = 0;

        for (Router router : routers) {
            int widthRouter = ROUTER_WIDTH;
            int heightRouter = ROUTER_HEIGHT;
            
            if (routerSelected() != null && routerSelected().equals(router)) {
                g2.setColor(new Color(0, 255, 163));
                widthRouter *= 1.2;
                heightRouter *= 1.2;
            } else {
                g2.setColor(new Color(0, 162, 89));
            }
            
            int yOffset = getHeight()-100;
            int gap = (getWidth() - routers.length*widthRouter) / (routers.length+1);
            int xOffset = i*widthRouter + (i+1)*gap;
            yOffset += 10;

                    
            g2.fillOval(xOffset, yOffset, widthRouter, heightRouter);

            g2.setColor(Color.BLACK);
            g2.drawOval(xOffset, yOffset, widthRouter, heightRouter);

            yOffset -= 10;
            g2.setColor(Color.WHITE);
            g2.fillOval(xOffset, yOffset, widthRouter, heightRouter);

            g2.setColor(Color.BLACK);
            g2.drawOval(xOffset, yOffset, widthRouter, heightRouter);
            
            Ellipse2D circle = new Ellipse2D.Float(xOffset, yOffset, widthRouter, heightRouter);
            g2.setClip(circle);

            g2.drawLine(xOffset, yOffset, xOffset+widthRouter, yOffset+heightRouter);
            g2.drawLine(xOffset, yOffset+heightRouter, xOffset+widthRouter, yOffset);
            g2.setClip(0, 0, getWidth(), getHeight());

            // calculate middle to center text
            int stringLen = (int)g2.getFontMetrics().getStringBounds(router.getName(), g2).getWidth();  
            int start = (stringLen > widthRouter) ? xOffset-(stringLen-widthRouter)/2 : xOffset-(stringLen-widthRouter)/2;

            g2.setColor(Color.WHITE);
            g2.drawString(router.getName(), start, yOffset+heightRouter+27);  

            g2.setColor(Color.BLACK);
            g2.drawString(router.getName(), start, yOffset+heightRouter+25);  
            
    
            i++;

        }
    }
    
    private void drawConnections(Graphics2D g2) {
        
        RouterConnection[] connections = (RouterConnection[]) DatabaseUtil.getInstance().getConnections();
        Subnet[] subnets = DatabaseUtil.getInstance().getSubnets();
        Router[] routers = DatabaseUtil.getInstance().getRouters();
        int yOffset = getHeight()-100;

        int gapRouter = (getWidth() - routers.length*ROUTER_WIDTH) / (routers.length+1);
        
        
        int gapSubnet = 15;
        int widthSubnet = (getWidth() - (subnets.length+1 ) * gapSubnet) /subnets.length;

        float dash[] = {5.0f};
        BasicStroke dashed = new BasicStroke(3.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash, connectionOffset);
        g2.setStroke(dashed);
  
        for (RouterConnection connection : connections) {
            
            int subnetIndex = Arrays.asList(subnets).indexOf(connection.getSubnet());
            int routerIndex = Arrays.asList(routers).indexOf(connection.getRouter());
            
            int xOffsetRouter = routerIndex*ROUTER_WIDTH + (routerIndex+1)*gapRouter;
            int xOffsetSubnet = subnetIndex*widthSubnet + (subnetIndex+1)*gapSubnet;

            int x1 = xOffsetSubnet+widthSubnet/2;
            int y1 = 55;
            int x2 = xOffsetRouter+ROUTER_WIDTH/2;
            int y2 = yOffset+ROUTER_HEIGHT/2;
            
            if (routerSelected() != null) {
                if (routerSelected().equals(connection.getRouter())) {
                    g2.setColor(Color.WHITE);
                    g2.setStroke(new BasicStroke(5f));
                    g2.drawLine(x1, y1, x2, y2);
                    g2.setStroke(dashed);
                    g2.setColor(SubnetColors.subnetColors()[subnetIndex]);

                } else {
                    g2.setColor(SubnetColors.subnetColors()[subnetIndex]);
                }
            } else if (subnetSelected() != null) {
                if (subnetSelected().equals(connection.getSubnet())) {
                    g2.setColor(Color.WHITE);
                    g2.setStroke(new BasicStroke(5f));
                    g2.drawLine(x1, y1, x2, y2);
                    g2.setStroke(dashed);
                    g2.setColor(SubnetColors.subnetColors()[subnetIndex]);
                } else {
                    g2.setColor(SubnetColors.subnetColors()[subnetIndex]);
                }
                    
            } else {
                g2.setColor(SubnetColors.subnetColors()[subnetIndex]);

            }   
            

            g2.drawLine(x1, y1, x2, y2);
            
        }
        
        // reset to no stroke
        g2.setStroke(new BasicStroke());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        routerBtn = new javax.swing.JButton();
        connectionBtn = new javax.swing.JButton();

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setForeground(new java.awt.Color(51, 255, 255));
        jToolBar1.setRollover(true);

        routerBtn.setText("Router");
        routerBtn.setFocusable(false);
        routerBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        routerBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        routerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routerBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(routerBtn);

        connectionBtn.setText("Connectie");
        connectionBtn.setFocusable(false);
        connectionBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectionBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        connectionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(connectionBtn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 275, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void routerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routerBtnActionPerformed
        
        String name = JOptionPane.showInputDialog(Localizer.ADD_ROUTER_NAME);
        if (name == null) return;
        
        while (!SubnetHelper.isValidRouterName(name)) {    
     
            name = (String)JOptionPane.showInputDialog(null, Localizer.NAME_NOT_VALID,
            Localizer.ERROR, JOptionPane.ERROR_MESSAGE, null, null, name);

            if (name == null) return;

        }
        
        DatabaseUtil.getInstance().addRouter(new Router(name));
        repaint();
        
    }//GEN-LAST:event_routerBtnActionPerformed

    private void connectionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionBtnActionPerformed
        
        Router[] routers = SubnetHelper.possibleRoutersForNewConnection();
        
        if (routers.length == 0) {
            JOptionPane.showMessageDialog(null,
            Localizer.NO_ROUTERS,
            Localizer.ERROR,
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Router router = (Router) JOptionPane.showInputDialog(null, 
                                        Localizer.SELECT_ROUTER, 
                                        null, 
                                        JOptionPane.QUESTION_MESSAGE, 
                                        null,
                                        routers, 
                                        routers[0]);

        if (router == null) return;
        
        Subnet[] subnets = SubnetHelper.possibleSubnetsForNewConnectionWithRouter(router);
        Subnet subnet = (Subnet) JOptionPane.showInputDialog(null, 
                                        Localizer.SELECT_SUBNET, 
                                        null, 
                                        JOptionPane.QUESTION_MESSAGE, 
                                        null,
                                        subnets, 
                                        subnets[0]);
        
        if (subnet == null) return;

        
        String hostAddressString = (String)JOptionPane.showInputDialog(null, Localizer.SELECT_IP_ADDRESS_ROUTER,
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

        
        RouterConnection c = new RouterConnection(router, SubnetHelper.addressFromString(hostAddressString), subnet);
        DatabaseUtil.getInstance().addConnection(c);
        repaint();
        
    }//GEN-LAST:event_connectionBtnActionPerformed
    
    private Router routerSelected() {
        
        Router[] routers = DatabaseUtil.getInstance().getRouters();

        if (mouseY > getHeight()-100) {
            int i = 0;
            int gap = (getWidth() - routers.length*ROUTER_WIDTH) / (routers.length+1);

            for (Router router : routers) {
                int xOffset = i*ROUTER_WIDTH + (i+1)*gap;
                if (mouseX > xOffset && mouseX < xOffset+ROUTER_WIDTH) {
                    return router;
                }
                i++;
            }

        }
        
        return null;
    }
    
    private Subnet subnetSelected() {
        Subnet[] subnets = DatabaseUtil.getInstance().getSubnets();

        if (mouseY < 135) {
            int gap = 15;
            int widthSubnet = (getWidth() - (subnets.length+1 ) * gap) /subnets.length;
            int i = 0;

            for (Subnet subnet : subnets) {
                int xOffset = i*widthSubnet + (i+1)*gap;
                if (mouseX > xOffset && mouseX < xOffset+widthSubnet) {
                    return subnet;
                }
                i++;
            }

        }
        
        return null;
    }
    
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        mouseX = evt.getX();
        mouseY = evt.getY();        
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        mouseX = -100;
        mouseY = -100;
    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectionBtn;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton routerBtn;
    // End of variables declaration//GEN-END:variables
}
