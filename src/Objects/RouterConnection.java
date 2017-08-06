/*
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */

package Objects;

import java.util.Objects;

/**
 *
 * @author louisdhauwe
 */
public class RouterConnection {
    
    private Router router;
    private Address routerAddress;
    private Subnet subnet;

    /**
     *
     * @param router
     * @param routerAddress
     * @param subnet
     */
    public RouterConnection(Router router, Address routerAddress, Subnet subnet) {
        this.router = router;
        this.routerAddress = routerAddress;
        this.subnet = subnet;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RouterConnection)) {
            return false;
        }
        
        RouterConnection rc = (RouterConnection)o;
        if (router.equals(rc.getRouter()) && subnet.equals(rc.subnet) && getRouterAddress().equals(rc.getRouterAddress())) {
            return true;
        }
        
        return false;
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.router);
        hash = 71 * hash + Objects.hashCode(this.getRouterAddress());
        hash = 71 * hash + Objects.hashCode(this.subnet);
        return hash;
    }
    

    /**
     * @return the router
     */
    public Router getRouter() {
        return router;
    }

    /**
     * @return the subnet
     */
    public Subnet getSubnet() {
        return subnet;
    }

    /**
     * @return the routerAddress
     */
    public Address getRouterAddress() {
        return routerAddress;
    }

    
    
}
