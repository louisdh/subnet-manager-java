/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package Objects;

import Logic.SubnetHelper;
import java.util.ArrayList;

/**
 *
 * @author louisdhauwe
 */
public class Subnet {
    
    private String name;
    private Address networkAddress;
    private Address subnetMask;
    private ArrayList<HostAddress> hosts;
    
    /**
     *
     * @param name
     * @param networkAddress
     * @param subnetMask
     * @param hosts
     */
    public Subnet(String name, Address networkAddress, Address subnetMask, ArrayList<HostAddress> hosts) {
        this.name = name;
        this.networkAddress = networkAddress;
        this.subnetMask = subnetMask;
        this.hosts = hosts;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Subnet)) {
            return false;
        }
        
        Subnet s = (Subnet)o;
        
        if (hosts != null) {
            if (hosts.size() == s.hosts.size()) {
                for (int i = 0; i < hosts.size(); i++) {
                    if (!hosts.get(i).equals(s.hosts.get(i))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        
        if (name.equals(s.name) && 
            networkAddress.equals(s.networkAddress) && 
            subnetMask.equals(s.subnetMask)) {
            return true;
        }
        
        return false;
        
    }
    
    @Override
    public String toString() {
        
        int subnetMaskSlashNotation = 0;
        for (int i = 0; i < subnetMask.getAddress().length; i++) {
            String bitString = SubnetHelper.binary8BitString(subnetMask.getAddress()[i]);
            int bitsCount = 0;
            for (char c : bitString.toCharArray()) {
                if (c == '1') {
                    bitsCount++;
                }
            }
            
            subnetMaskSlashNotation += bitsCount;
        }
        
        return networkAddress + "/" + subnetMaskSlashNotation;
    }
        
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the networkAddress
     */
    public Address getNetworkAddress() {
        return networkAddress;
    }

    /**
     * @param networkAddress the networkAddress to set
     */
    public void setNetworkAddress(Address networkAddress) {
        this.networkAddress = networkAddress;
    }

    /**
     * @return the subnetMask
     */
    public Address getSubnetMask() {
        return subnetMask;
    }

    /**
     * @param subnetMask the subnetMask to set
     */
    public void setSubnetMask(Address subnetMask) {
        this.subnetMask = subnetMask;
    }

    /**
     * @return the hosts
     */
    public HostAddress[] getHosts() {
        HostAddress[] array = new HostAddress[hosts.size()];
        return hosts.toArray(array);
    }
    
    /**
     *
     * @param host
     */
    public void addHost(HostAddress host) {
        hosts.add(host);
    }
    
    /**
     *
     * @param host
     */
    public void removeHost(HostAddress host) {
        hosts.remove(host);
    }
    
}
