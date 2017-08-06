/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package Objects;

import Logic.SubnetHelper;
import java.util.Arrays;

/**
 *
 * @author louisdhauwe
 */
public class Address {
    
    private short[] address = new short[4]; 

    /**
     * 
     * @param address
     */
    public Address(short[] address) {
        this.address = address;
    }
    
    /**
     * Returns long because primitives (such as int) are signed in Java
     * @return decimal value of the address
     */
    
    public long decimalValue() {
        return SubnetHelper.decimalValueForBinaryBitString(SubnetHelper.binaryStringForAddress(this));
    }

    @Override
    public String toString() {
        return address[0] + "." + address[1] + "." + address[2] + "." + address[3];
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof  Address)) {
            return false;
        }
        for (int i = 0; i < address.length; i++) {
            if (((Address)o).getAddress()[i] != getAddress()[i]) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Arrays.hashCode(this.address);
        return hash;
    }
    
    
    /**
     * @return the address
     */
    public short[] getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(short[] address) {
        this.address = address;
    }
       
}
