/*
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */

package Localization.Dutch;

/**
 *
 * @author louisdhauwe
 */
public class Messages {
    
    /* General */
    public final static String DELETE = "Verwijder";
    public final static String EDIT = "wijzig";
    public final static String CLOSE = "Sluit";
    public final static String CANCEL = "Annuleer";

    /* SubnetPanel */
    public final static String NAME_SUBNET = "Naam:";
    public final static String NETWORKADDRESS_SUBNET = "Netwerkadres:";
    public final static String SUBNETMASK_SUBNET = "Subnetmask:";
    public final static String HOSTS_SUBNET = "Hosts:";
    public final static String ADD_NEW_HOST_SUBNET = "Voeg host toe";

    public final static String ADD_NEW_SUBNET_NAME = "Geef een naam voor het nieuwe subnet:";
    public final static String ADD_NEW_SUBNET = "Voeg subnet toe";
    public final static String ADD_NEW_SUBNET_NETWORK_ADDRESS = "Geef een netwerkadres voor het nieuwe subnet \"%@\":";
    public final static String ADD_NEW_SUBNET_SUBNETMASK = "Geef een subnetmask voor het nieuwe subnet \"%@\":";
    public final static String NO_SUBNETS = "Er zijn geen subnetten, open een databank of maak een nieuw subnet aan";

    public final static String NEW_SUBNET_NAME = "Geef een nieuwe naam voor het subnet \"%@\":";
    public final static String NEW_SUBNET_NETWORK_ADDRESS = "Geef een nieuw netwerkadres voor het subnet \"%@\":";
    public final static String NEW_SUBNET_SUBNETMASK = "Geef een nieuw subnetmask voor het subnet \"%@\":";
    
    public final static String NEW_HOST_NAME = "Geef de hostnaam van de toe te voegen host:";
    public final static String NEW_HOST_IP_ADDRESS = "Geef het ip-adres van de toe te voegen host \"%@\":";
    public final static String NEW_HOST_IP_ADDRESS_ERROR_IS_NETWORKADDRESS = "Kan niet toevoegen. Het gegeven adres is het netwerkadres";
          
    /* Errors */
    public final static String ERROR = "Ongeldig";
    public final static String SUBNETMASK_NOT_VALID = "Geen geldig subnetmask.";
    public final static String NETWORKADDRESS_NOT_VALID = "Geen geldig netwerkadres.";
    public final static String NAME_NOT_VALID = "Geen geldige naam.";
    public final static String HOST_NOT_VALID = "Geen geldige host.";
    public final static String ADDRESS_NOT_VALID = "Geen geldig adres.";
    public final static String NO_ROUTERS = "Er zijn geen routers beschikbaar.";
    public final static String ADDRESS_ALREADY_USED = "Adres is al gebruikt.";

    /* Tabs */
    public final static String NETWORKS_TAB = "Netwerken";
    public final static String ROUTERS_TAB = "Routers";
    public final static String NETWORKS_TAB_TOOLTIP = "Bekijk hier all netwerken";
    public final static String ROUTERS_TAB_TOOLTIP = "Bekijk hier alle routers verbonden aan de netwerken";
    
    /* Menu */
    public final static String DATABASE_MENU = "Databank";
    public final static String OPEN_DATABASE_MENU = "Open databank";
    public final static String SAVE_DATABASE_MENU = "Sla databank op";
    public final static String NEW_DATABASE_MENU = "Nieuwe databank";
    
    /* Routers */
    public final static String ROUTER = "Router";
    public final static String NEW_CONNECTION = "Connectie";
    public final static String ADD_ROUTER_NAME = "Geef een naam voor de nieuwe router:";
    
    /* Connection */
    public final static String SELECT_ROUTER = "Kies een router:";
    public final static String SELECT_SUBNET = "Kies een subnet:";
    public final static String SELECT_IP_ADDRESS_ROUTER = "Kies een ip-adres voor de router binnen het gekozen subnet:";

    /* Save prompt */
    public final static String DATABASE_NOT_SAVED_TITLE = "Database is niet opgeslagen";
    public final static String DATABASE_NOT_SAVED_MESSAGE = "De huidige database is niet opgeslagen, onopgeslagen veranderingen zullen verloren gaan.";
    public final static String SAVE = "Sla op";

}
