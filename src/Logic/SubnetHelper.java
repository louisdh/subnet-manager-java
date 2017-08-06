/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package Logic;

import DatabaseConnection.DatabaseUtil;
import Localization.Localizer;
import Objects.Address;
import Objects.HostAddress;
import Objects.Router;
import Objects.RouterConnection;
import Objects.Subnet;
import java.util.ArrayList;

/**
 *
 * @author louisdhauwe
 */
public class SubnetHelper {
    
    /**
     * Gives network part of subnet in decimal dot notation
     * @param s Subnet
     * @return network part
     */
    public static String networkPartForSubnet(Subnet s) {
        return decimalDotNotationFromBinaryString(binaryNetworkPartForSubnet(s));
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static String binaryNetworkPartForSubnet(Subnet s) {
        
        String networkPart = "";
        char[] networkAddress = binaryStringForAddress(s.getNetworkAddress()).toCharArray();
        char[] subnetmask = binaryStringForAddress(s.getSubnetMask()).toCharArray();
        
        for (int i = 0; i < networkAddress.length; i++) {
            if (subnetmask[i] == '0') {
                break;
            }
            networkPart += networkAddress[i];
        }

        return networkPart;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static String binaryBroadcastAddress(Subnet s) {
        String binaryString = binaryNetworkPartForSubnet(s);
        int bitsToAdd = 32-binaryString.length();
        for (int i = 0; i < bitsToAdd; i++) {
            binaryString = binaryString + "1";
        }
        return binaryString;
    }
    
    /**
     *
     * @param a
     * @param s
     * @return
     */
    public static boolean hostAddressIsPartOfSubnet(Address a, Subnet s) {
        long networkAddress = decimalValueForBinaryBitString(binaryStringForAddress(s.getNetworkAddress()));
        long broadcastAddress = decimalValueForBinaryBitString(binaryBroadcastAddress(s));
        long address = decimalValueForBinaryBitString(binaryStringForAddress(a));
        
        return (address > networkAddress && address < broadcastAddress);
    }
	
    /**
     *
     * @param binaryString
     * @return
     */
    public static String decimalDotNotationFromBinaryString(String binaryString) {
        String decimalNotation = "";
        int j = binaryString.length() / 8;
        for (int i = 0; i < binaryString.length() / 8; i++) {
            String part = binaryString.substring(i*8, (i+1)*8);
            decimalNotation += decimalValueForBinaryBitString(part);
            if (i < j-1) {
                decimalNotation += ".";
            }	
        }

        return decimalNotation;

    }
    
    /**
     *
     * @param value
     * @return
     */
    public static String decimalDotNotationFromDecimalValue(long value) {
        String dotNotationString = SubnetHelper.binary32BitString(value);
        dotNotationString = SubnetHelper.decimalDotNotationFromBinaryString(dotNotationString);
        return dotNotationString;
    }
            
    /**
     *
     * @param address
     * @return
     */
    public static String binaryStringForAddress(Address address) {
        String noDots = binary8BitString(address.getAddress()[0]);
        noDots += binary8BitString(address.getAddress()[1]);
        noDots += binary8BitString(address.getAddress()[2]);
        noDots += binary8BitString(address.getAddress()[3]);
        
        return noDots;
    }
    
    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String andBinaryStrings(String s1, String s2) {
        
        String result = "";
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == '1' && c2 == '1') {
                result += "1";
            } else {
                result += "0";

            }
            
        }
        return result;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static long decimalValueForBinaryBitString(String s) {
        int i = s.length()-1;
        long decimalValue = 0;
        for (char c : s.toCharArray()) {
            int ci = Integer.parseInt(c + "");
            if (ci != 0) {
                decimalValue += Math.pow(ci*2, i);
            }
            i--;
        }
        
        return decimalValue;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static String binary8BitString(short s) {        
        return binaryString(s, 8);
    }
    
    /**
     *
     * @param l
     * @return
     */
    public static String binary32BitString(long l) {        
        return binaryString(l, 32);
    }
    
    private static String binaryString(long l, int bits) {
        String bitString = "";
        while (l > 0) {            
            String bit = (l % 2) + "";
            l /= 2;
            bitString = bit + bitString;
        }
        
        int extraZerosNeeded = bits-bitString.length();
        for (int i = 0; i < extraZerosNeeded; i++) {
            bitString = 0 + bitString;
        }
        
        return bitString;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static boolean isValidRouterName(String s) {
        
        if (s.equals("")) return false;
        
        for (Router router : DatabaseUtil.getInstance().getRouters()) {
            
            if (router.getName().equals(s)) {
                return false;
            }
            
        }
        
        return true;
    }   
    
    /**
     *
     * @param hostname
     * @param subnet
     * @return
     */
    public static boolean isValidHostNameInSubnet(String hostname, Subnet subnet) {
        
        if (hostname.equals("")) return false;

        for (HostAddress host : subnet.getHosts()) {
            if (hostname.equals(host.getHostName())) {
                return false;
            }
        }
        
        return true;
        
    }
    
    /**
     *
     * @param name
     * @param subnet
     * @return
     */
    public static boolean isValidSubnetNameForSubnet(String name, Subnet subnet) {
        
        if (name.equals("")) return false;
        
        if (subnet != null && subnet.getName().equals(name)) {
            return true;
        }
        
        for (Subnet s : DatabaseUtil.getInstance().getSubnets()) {        

            if (s.getName().equals(name)) {
                return false;
            }
            
        }
        
        return true;
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isValidSubnetMask(String s) {
        
        if (!SubnetHelper.isValidIPv4Address(s)) return false;

        Address a = addressFromString(s);
        String bitString = "";
        for (int i = 0; i < a.getAddress().length; i++) {
            
            bitString += SubnetHelper.binary8BitString(a.getAddress()[i]);

        }
                
        boolean foundZero = false;
        for (char c : bitString.toCharArray()) {
            
            if (c == '1') {
                if (foundZero) return false;
            } else if (c == '0') {
                foundZero = true;
                
            }
            
        }
        
        return true;
                    
    }
    
    private static boolean stringContainsThreeDots(String s) {
        int i = 0;
        for (char c : s.toCharArray()) {
            if (c == '.') {
                i++;
            }
        }

        return i == 3;
    } 
    
    /**
     *
     * @param s
     * @return
     */
    public static boolean isValidIPv4Address(String s) {
        
        if (!stringContainsThreeDots(s)) return false;

        try {
            addressFromString(s);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    /**
     *
     * @param s
     * @param subnet
     * @return
     */
    public static boolean isValidHostAddress(String s, Subnet subnet) {
        
        boolean validIP = SubnetHelper.isValidIPv4Address(s);
        if (!validIP || !hostAddressIsPartOfSubnet(addressFromString(s), subnet)) {
            throw new IllegalArgumentException(Localizer.HOST_NOT_VALID);
        }
        
        if (subnet.getNetworkAddress().equals(SubnetHelper.addressFromString(s))) {
            throw new IllegalArgumentException(Localizer.NEW_HOST_IP_ADDRESS_ERROR_IS_NETWORKADDRESS);
        }
        
        for (HostAddress h : subnet.getHosts()) {
            Address extistingAddress = new Address(h.getAddress());
            if (extistingAddress.equals(SubnetHelper.addressFromString(s))) {
                throw new IllegalArgumentException(Localizer.ADDRESS_ALREADY_USED);
            }
        }

        return true;

    }
        
    /**
     *
     * @param s
     * @param subnet
     * @return
     */
    public static boolean isValidNetworkAddress(String s, Subnet subnet) {
        
        boolean validIP = SubnetHelper.isValidIPv4Address(s);
        if (!validIP) throw new IllegalArgumentException(Localizer.NETWORKADDRESS_NOT_VALID);
        
        for (Subnet su : DatabaseConnection.DatabaseUtil.getInstance().getSubnets()) {
            
            if (subnet != null && subnet.equals(su)) {
                continue;
            }
               
            if (SubnetHelper.addressFromString(s).equals(su.getNetworkAddress())) {
                throw new IllegalArgumentException(Localizer.ADDRESS_ALREADY_USED);
            }
            
        }

        return true;

    }
    
    /**
     *
     * @param router
     * @return
     */
    public static Subnet[] possibleSubnetsForNewConnectionWithRouter(Router router) {
        ArrayList<Subnet> subnetList = new ArrayList<>();
        
        for (Subnet subnet : DatabaseUtil.getInstance().getSubnets()) {
            boolean add = true;
            for (RouterConnection rc : DatabaseUtil.getInstance().getConnections()) {
                if (rc.getSubnet().equals(subnet) && rc.getRouter().equals(router)) {
                    add = false;
                    break;
                }
            }
            if (add) subnetList.add(subnet);
            
        }
        
        Subnet[] subnetArray = new Subnet[subnetList.size()];
        return subnetList.toArray(subnetArray);   
    }
    
    /**
     *
     * @return
     */
    public static Router[] possibleRoutersForNewConnection() {
        ArrayList<Router> routerList = new ArrayList<>();
        int subnetCount = DatabaseUtil.getInstance().getSubnets().length;
        for (Router router : DatabaseUtil.getInstance().getRouters()) {
            int i = 0;
            for (RouterConnection rc : DatabaseUtil.getInstance().getConnections()) {
                if (rc.getRouter().equals(router)) {
                    i++;
                }
            }
            if (i < subnetCount) {
                routerList.add(router);
            }
        }
        
        Router[] routerArray = new Router[routerList.size()];
        return routerList.toArray(routerArray);
        
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static Address addressFromString(String s) {
   
        // '.' is a REGEX character, escape for use in literal case
        String[] split = s.split("\\.");
        
        short[] arr = new short[4];
        
        for (int i = 0; i < 4; i++) {
            arr[i] = Short.parseShort(split[i]);
            if (arr[i] > 255 || arr[i] < 0) {
                throw new IllegalArgumentException();
            }
        }
        
        Address a = new Address(arr);
        return a;
    }

}
