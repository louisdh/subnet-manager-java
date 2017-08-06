/*
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */

package Localization.English;

/**
 *
 * @author louisdhauwe
 */
public class Messages {
    
    /* General */
    public final static String DELETE = "Delete";
    public final static String EDIT = "edit";
    public final static String CLOSE = "Close";
    public final static String CANCEL = "Cancel";

    /* SubnetPanel */
    public final static String NAME_SUBNET = "Name:";
    public final static String NETWORKADDRESS_SUBNET = "Networkaddress:";
    public final static String SUBNETMASK_SUBNET = "Subnetmask:";
    public final static String HOSTS_SUBNET = "Hosts:";
    public final static String ADD_NEW_HOST_SUBNET = "Add new host";

    public final static String ADD_NEW_SUBNET_NAME = "Enter a name for the new subnet:";
    public final static String ADD_NEW_SUBNET = "Add new subnet";
    public final static String ADD_NEW_SUBNET_NETWORK_ADDRESS = "Enter a network address for the new subnet \"%@\":";
    public final static String ADD_NEW_SUBNET_SUBNETMASK = "Enter a subnetmask for the new subnet \"%@\":";
    public final static String NO_SUBNETS = "There are no subnets, open a database or add a new subnet";
    
    public final static String NEW_SUBNET_NAME = "Enter a new name for the subnet:";
    public final static String NEW_SUBNET_NETWORK_ADDRESS = "Enter a new network address for the subnet:";
    public final static String NEW_SUBNET_SUBNETMASK = "Enter a new subnetmask for the subnet:";
    
    public final static String NEW_HOST_NAME = "Enter a name for the new host:";
    public final static String NEW_HOST_IP_ADDRESS = "Enter the ip address for the new host \"%@\":";
    public final static String NEW_HOST_IP_ADDRESS_ERROR_IS_NETWORKADDRESS = "Can't add host. The entered address is the network address.";
        
    /* Errors */
    public final static String ERROR = "Invalid";
    public final static String SUBNETMASK_NOT_VALID = "Not a valid subnetmask.";
    public final static String NETWORKADDRESS_NOT_VALID = "Not a valid network address.";
    public final static String NAME_NOT_VALID = "Not a valid name.";
    public final static String HOST_NOT_VALID = "Not a valid host.";
    public final static String ADDRESS_NOT_VALID = "Not a valid address.";
    public final static String NO_ROUTERS = "There are no routers.";
    public final static String ADDRESS_ALREADY_USED = "Address is already used.";

    /* Tabs */
    public final static String NETWORKS_TAB = "Networks";
    public final static String ROUTERS_TAB = "Routers";
    public final static String NETWORKS_TAB_TOOLTIP = "View all networks here";
    public final static String ROUTERS_TAB_TOOLTIP = "View all routers here";
    
    /* Menu */
    public final static String DATABASE_MENU = "Database";
    public final static String OPEN_DATABASE_MENU = "Open database";
    public final static String SAVE_DATABASE_MENU = "Save database";
    public final static String NEW_DATABASE_MENU = "New database";
   
    /* Routers */
    public final static String ROUTER = "Router";
    public final static String NEW_CONNECTION = "Connection";
    public final static String ADD_ROUTER_NAME = "Enter a name for the new router:";

    /* Connection */
    public final static String SELECT_ROUTER = "Select a router:";
    public final static String SELECT_SUBNET = "Select a subnet:";
    public final static String SELECT_IP_ADDRESS_ROUTER = "Enter an ip address for the router in the chosen subnet:";

    /* Save prompt */
    public final static String DATABASE_NOT_SAVED_TITLE = "Database is not saved";
    public final static String DATABASE_NOT_SAVED_MESSAGE = "The current database is unsaved, all changes will de discarded.";
    public final static String SAVE = "Save";
    
}
