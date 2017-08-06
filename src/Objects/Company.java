/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package Objects;

import java.util.ArrayList;

/**
 *
 * @author louisdhauwe
 */
public class Company {

    private ArrayList<Subnet> subnets = new ArrayList<>();
    private ArrayList<Router> routers = new ArrayList<>();
    private ArrayList<RouterConnection> connections = new ArrayList<>();

    /**
     *
     */
    public Company() {
        
    }
    
    /**
     *
     * @param s
     */
    public void addSubnet(Subnet s) {
        subnets.add(s);
    }
    
    /**
     *
     * @param s
     */
    public void removeSubnet(Subnet s) {
        subnets.remove(s);
    }
    
    /**
     * @return the subnets
     */
    public Subnet[] getSubnets() {
        // return copy
        Subnet[] array = new Subnet[subnets.size()];
        return subnets.toArray(array);
    }
    
    /**
     * @return the routers
     */
    public Router[] getRouters() {
        // return copy
        Router[] array = new Router[routers.size()];
        return routers.toArray(array);
    }

    /**
     *
     * @param r
     */
    public void addRouter(Router r) {
        routers.add(r);
    }
        
    /**
     * @return the connections
     */
    public RouterConnection[] getConnections() {
        // return copy
        RouterConnection[] array = new RouterConnection[connections.size()];
        return connections.toArray(array);
    }
    
    /**
     *
     * @param c
     */
    public void addConnection(RouterConnection c) {
        connections.add(c);
    }

    /**
     * @param subnets the subnets to set
     */
    public void setSubnets(ArrayList<Subnet> subnets) {
        this.subnets = subnets;
    }

    /**
     * @param routers the routers to set
     */
    public void setRouters(ArrayList<Router> routers) {
        this.routers = routers;
    }

    /**
     * @param connections the connections to set
     */
    public void setConnections(ArrayList<RouterConnection> connections) {
        this.connections = connections;
    }

}
