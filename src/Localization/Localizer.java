/*
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */

package Localization;

/* change package name for different language */
import Localization.English.Messages;

/**
 *
 * @author louisdhauwe
 */
public class Localizer {
    
    /* General */
    public final static String DELETE = Messages.DELETE;
    public final static String EDIT = Messages.EDIT;
    public final static String CLOSE = Messages.CLOSE;
    public final static String CANCEL = Messages.CANCEL;

    /* SubnetPanel */
    public final static String NAME_SUBNET = Messages.NAME_SUBNET;
    public final static String NETWORKADDRESS_SUBNET = Messages.NETWORKADDRESS_SUBNET;
    public final static String SUBNETMASK_SUBNET = Messages.SUBNETMASK_SUBNET;
    public final static String HOSTS_SUBNET = Messages.HOSTS_SUBNET;
    public final static String ADD_NEW_HOST_SUBNET = Messages.ADD_NEW_HOST_SUBNET;

    public final static String ADD_NEW_SUBNET_NAME = Messages.ADD_NEW_SUBNET_NAME;
    public final static String ADD_NEW_SUBNET = Messages.ADD_NEW_SUBNET;
    public final static String ADD_NEW_SUBNET_NETWORK_ADDRESS = Messages.ADD_NEW_SUBNET_NETWORK_ADDRESS;
    public final static String ADD_NEW_SUBNET_SUBNETMASK = Messages.ADD_NEW_SUBNET_SUBNETMASK;
    public final static String NO_SUBNETS = Messages.NO_SUBNETS;
    
    public final static String NEW_SUBNET_NAME = Messages.NEW_SUBNET_NAME;
    public final static String NEW_SUBNET_NETWORK_ADDRESS = Messages.NEW_SUBNET_NETWORK_ADDRESS;
    public final static String NEW_SUBNET_SUBNETMASK = Messages.NEW_SUBNET_SUBNETMASK;
    
    public final static String NEW_HOST_NAME = Messages.NEW_HOST_NAME;
    public final static String NEW_HOST_IP_ADDRESS = Messages.NEW_HOST_IP_ADDRESS;
    public final static String NEW_HOST_IP_ADDRESS_ERROR_IS_NETWORKADDRESS = Messages.NEW_HOST_IP_ADDRESS_ERROR_IS_NETWORKADDRESS;       
    
    /* Errors */
    public final static String ERROR = Messages.ERROR;
    public final static String SUBNETMASK_NOT_VALID = Messages.SUBNETMASK_NOT_VALID;
    public final static String NETWORKADDRESS_NOT_VALID = Messages.NETWORKADDRESS_NOT_VALID;
    public final static String NAME_NOT_VALID = Messages.NAME_NOT_VALID;
    public final static String HOST_NOT_VALID = Messages.HOST_NOT_VALID;
    public final static String ADDRESS_NOT_VALID = Messages.ADDRESS_NOT_VALID;
    public final static String NO_ROUTERS = Messages.NO_ROUTERS;
    public final static String ADDRESS_ALREADY_USED = Messages.ADDRESS_ALREADY_USED;

    /* Tabs */
    public final static String NETWORKS_TAB = Messages.NETWORKS_TAB;
    public final static String ROUTERS_TAB = Messages.ROUTERS_TAB;
    public final static String NETWORKS_TAB_TOOLTIP = Messages.NETWORKS_TAB_TOOLTIP;
    public final static String ROUTERS_TAB_TOOLTIP = Messages.ROUTERS_TAB_TOOLTIP;
    
    /* Menu */
    public final static String DATABASE_MENU = Messages.DATABASE_MENU;
    public final static String OPEN_DATABASE_MENU = Messages.OPEN_DATABASE_MENU;
    public final static String SAVE_DATABASE_MENU = Messages.SAVE_DATABASE_MENU;
    public final static String NEW_DATABASE_MENU = Messages.NEW_DATABASE_MENU;
    
    /* Routers */
    public final static String ROUTER = Messages.ROUTER;
    public final static String NEW_CONNECTION = Messages.NEW_CONNECTION;
    public final static String ADD_ROUTER_NAME = Messages.ADD_ROUTER_NAME;

    /* Connection */
    public final static String SELECT_ROUTER = Messages.SELECT_ROUTER;
    public final static String SELECT_SUBNET = Messages.SELECT_SUBNET;
    public final static String SELECT_IP_ADDRESS_ROUTER = Messages.SELECT_IP_ADDRESS_ROUTER;
    
    /* Save prompt */
    public final static String DATABASE_NOT_SAVED_TITLE = Messages.DATABASE_NOT_SAVED_TITLE;
    public final static String DATABASE_NOT_SAVED_MESSAGE = Messages.DATABASE_NOT_SAVED_MESSAGE;
    public final static String SAVE = Messages.SAVE;

    
}
