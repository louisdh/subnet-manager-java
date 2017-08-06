/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package Objects;

import java.util.Objects;

/**
 *
 * @author louisdhauwe
 */
public class HostAddress extends Address {
    
    private String hostName;

    /**
     *
     * @param address
     * @param hostname
     */
    public HostAddress(short[] address, String hostname) {
        super(address);
        this.hostName = hostname;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HostAddress)) {
            return false;
        }
        
        HostAddress h = (HostAddress)o;

        if (super.equals(o) && hostName.equals(h.hostName)) {
            return true;
        }
        
        return false;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.hostName);
        return hash;
    }
    

    @Override
    public String toString() {
        return hostName + " [" + super.toString() + "]";
    }
    
    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    
}
