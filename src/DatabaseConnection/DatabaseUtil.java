/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package DatabaseConnection;

import Logic.SubnetHelper;
import Objects.Address;
import Objects.Company;
import Objects.HostAddress;
import Objects.Router;
import Objects.RouterConnection;
import Objects.Subnet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author louisdhauwe
 */
public class DatabaseUtil {
    
    private Company company;
    private static DatabaseUtil instance = null;
    private Connection con;
    private boolean saved = true;
    
    /**
     * Prevents instantiation
     */
    protected DatabaseUtil() {
        
    }
     
    /**
     * Get shared instance (singleton) to handle all data in memory
     * @return shared instance
     */
    public static DatabaseUtil getInstance() {    
        if (instance == null) {
            instance = new DatabaseUtil();
            instance.openConnection();
        }
        
        return instance;
    }
    
    /**
     * Creates new company instance
     */
    public void newDatabase() {
        company = new Company();
        setSaved(true);
    }
    
    private void deleteDatabse() {
        
        try {
            Statement stmt;
            stmt = con.createStatement();         
            stmt.execute("delete from RouterConnecties");
            stmt.execute("delete from Routers;");
            stmt.execute("delete from HostAdressen;");
            stmt.execute("delete from Subnetten;"); 
            stmt.execute("delete from Adressen;");
            System.out.println("database deleted");
   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    /**
     * Read all selects from SQL database
     */
    public void openDatabase() {
        company = new Company();    
        company.setSubnets(selectSubnets());
        company.setRouters(selectRouters());
        selectHostAddresses();
        selectRouterConnections();
        setSaved(true);
    }
    
    /**
     * Select all routers from the SQL database
     * @return router list
     */
    public ArrayList<Router> selectRouters() {
        Statement stmt;
        try {
            stmt = con.createStatement();
            ArrayList<Router> routers = new ArrayList<>();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Routers");
            
            while (rs.next()) {
                String name = rs.getString("naam");
                Router r = new Router(name);
                routers.add(r);
            }
                
            return routers;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
                
    }
    
    /**
     * Select all subnets from the SQL database
     * @return subnet list
     */
    public ArrayList<Subnet> selectSubnets() {
        Statement stmt;
        try {
            stmt = con.createStatement();
            ArrayList<Subnet> subnets = new ArrayList<>();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Subnetten");
            
            while (rs.next()) {
                
                Long networkAddresLong = rs.getLong("netwerk_adres");
                String networkAddressDotNotationString = SubnetHelper.decimalDotNotationFromDecimalValue(networkAddresLong);
                Address networkAddress = SubnetHelper.addressFromString(networkAddressDotNotationString);
                
                Long subnetmaskLong = rs.getLong("subnetmask");
                String subnetmaskDotNotationString = SubnetHelper.decimalDotNotationFromDecimalValue(subnetmaskLong);
                Address subnetmask = SubnetHelper.addressFromString(subnetmaskDotNotationString);
                
                ArrayList<HostAddress> h = new ArrayList<>();
                subnets.add(new Subnet(rs.getString("Naam"), networkAddress, subnetmask, h));
               
            }
            
            return subnets;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Select all host addresses from the SQL database
     * and add them to the corresponding subnet
     */
    public void selectHostAddresses() {
        Statement stmt;
        try {
            stmt = con.createStatement();
            ArrayList<Subnet> subnets = new ArrayList<>();

            ResultSet rs = stmt.executeQuery("SELECT * FROM HostAdressen");
            
            while (rs.next()) {
                
                Long addressLong = rs.getLong("adres");
                String addressString = SubnetHelper.decimalDotNotationFromDecimalValue(addressLong);
                short[] address = SubnetHelper.addressFromString(addressString).getAddress();
                
                String name = rs.getString("naam");
                
                Long networkAddresLong = rs.getLong("Subnetten_netwerk_adres");
                String networkAddressDotNotationString = SubnetHelper.decimalDotNotationFromDecimalValue(networkAddresLong);
                Address networkAddress = SubnetHelper.addressFromString(networkAddressDotNotationString);
                
                HostAddress host = new HostAddress(address, name);
                
                for (Subnet s : company.getSubnets()) {
                    if (s.getNetworkAddress().equals(networkAddress)) {
                        s.addHost(host);
                    }
                }
                
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
    /**
     * Select all router connections from the SQL database
     * and add to the company 
     */
    public void selectRouterConnections() {
        Statement stmt;
        try {
            stmt = con.createStatement();
            ArrayList<Subnet> subnets = new ArrayList<>();

            ResultSet rs = stmt.executeQuery("SELECT * FROM RouterConnecties");
            
            while (rs.next()) {
                
                Long addressLong = rs.getLong("adres");
                String addressString = SubnetHelper.decimalDotNotationFromDecimalValue(addressLong);
                short[] address = SubnetHelper.addressFromString(addressString).getAddress();
                
                String name = rs.getString("router_naam");
                
                Long networkAddresLong = rs.getLong("Subnetten_netwerk_adres");
                String networkAddressDotNotationString = SubnetHelper.decimalDotNotationFromDecimalValue(networkAddresLong);
                Address networkAddress = SubnetHelper.addressFromString(networkAddressDotNotationString);
                
                Router router = null;
                for (Router r : company.getRouters()) {
                    if (r.getName().equals(name)) {
                        router = r;
                    }
                }                
                
                Subnet subnet = null;
                for (Subnet s : company.getSubnets()) {
                    if (s.getNetworkAddress().equals(networkAddress)) {
                        subnet = s;
                    }
                }
                                
                RouterConnection rc = new RouterConnection(router, networkAddress, subnet);

                company.addConnection(rc);
                
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }          
        
    }
    
    /**
     * Delete SQL database and insert all objects in memory
     */
    public void saveDatabase() {
        deleteDatabse();
                        
        ArrayList<Address> savedAddresses = new ArrayList<>();
        for (Subnet s: company.getSubnets()) {
            
            ArrayList<Address> addressesToSave = new ArrayList<>();
            addressesToSave.add(s.getNetworkAddress());
            addressesToSave.add(s.getSubnetMask());
            
            for (HostAddress hostAdress : s.getHosts()) {
                addressesToSave.add(hostAdress);
            }
            
            // make sure we only save an address once
            for (Address address : addressesToSave) {
                if (!savedAddresses.contains(address)) {
                    if (insertAddress(address)) {
                        savedAddresses.add(address);
                    }
                }
            }

            insertSubnet(s);
            
            for (HostAddress hostAdress : s.getHosts()) {
                insertHostAdress(hostAdress, s);
            }
           
        }
        
        for (Router r : company.getRouters()) {
            insertRouter(r);
        }
        
        for (RouterConnection rc : company.getConnections()) {
            insertRouterConnection(rc);
        }
        
        setSaved(true);

    }
    
    /**
     * Insert object of type RouterConnection in the SQL database
     * @param rc Router connection to insert
     */
    public void insertRouterConnection(RouterConnection rc) {
        
        try {
            String insertTableSQL = "INSERT INTO RouterConnecties"
		+ "(adres, router_naam, Subnetten_netwerk_adres) VALUES "
		+ "(?, ?, ?);";
            
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            
            preparedStatement.setString(1, rc.getRouterAddress().decimalValue() + "");    
            preparedStatement.setString(2, rc.getRouter().getName());    
            preparedStatement.setString(3, rc.getSubnet().getNetworkAddress().decimalValue() + "");

            preparedStatement.executeUpdate();
            
            System.out.println("insert gelukt");
                
        } catch (SQLException e) {
            System.out.println("insertRouterConnection " + e.getMessage());
        }
                
    }

    /**
     * Insert object of type Router in the SQL database
     * @param router
     */
    public void insertRouter(Router router) {
        
        try {
            String insertTableSQL = "INSERT INTO Routers"
		+ "(naam) VALUES "
		+ "(?);";
            
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            
            preparedStatement.setString(1, router.getName());    

            preparedStatement.executeUpdate();
            
            System.out.println("insert gelukt");
                
        } catch (SQLException e) {
            System.out.println("insertRouter " + e.getMessage());
        }
        
    }
        
    /**
     * Insert object of type HostAddress in the SQL database
     * @param hostAddress
     * @param subnet
     */
    public void insertHostAdress(HostAddress hostAddress, Subnet subnet) {
        
        try {
            String insertTableSQL = "INSERT INTO HostAdressen"
		+ "(adres, naam, Subnetten_netwerk_adres) VALUES "
		+ "(?, ?, ?);";
            
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            
            preparedStatement.setString(1, hostAddress.decimalValue() + "");    
            preparedStatement.setString(2, hostAddress.getHostName()); 
            preparedStatement.setString(3, subnet.getNetworkAddress().decimalValue() + "");

            preparedStatement.executeUpdate();
            
            System.out.println("insert gelukt");
                
        } catch (SQLException e) {
            System.out.println("insertHostAdress " + e.getMessage());
        }
        
    }

    /**
     * Insert object of type Subnet in the SQL database
     * @param subnet
     */
    public void insertSubnet(Subnet subnet) {
        
        try {
            String insertTableSQL = "INSERT INTO Subnetten"
		+ "(naam, netwerk_adres, subnetmask) VALUES "
		+ "(?, ?, ?);";
            
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            
            preparedStatement.setString(1, subnet.getName()); 
            preparedStatement.setString(2, subnet.getNetworkAddress().decimalValue() + "");    
            preparedStatement.setString(3, subnet.getSubnetMask().decimalValue() + "");    

            preparedStatement.executeUpdate();
            
            System.out.println("insert gelukt");
                
        } catch (SQLException e) {
            System.out.println("insertSubnet " + e.getMessage());
        }
        
    }
    
    /**
     * Insert object of type Address in the SQL database
     * @param address
     * @return if succeeded
     */
    public boolean insertAddress(Address address) {
        
        try {
            
            String insertTableSQL = "INSERT INTO Adressen"
		+ "(adres) VALUES "
		+ "(?);";
                    
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            
            preparedStatement.setString(1, address.decimalValue() + "");

            preparedStatement.executeUpdate();
            
            System.out.println("insert gelukt");
            return true;
    
        } catch (SQLException e) {
            System.out.println("insertAddress " + e.getMessage());

        }
        
        return false;
    }
    
    /**
     * Get all subnets in array
     * @return subnets array
     */
    public Subnet[] getSubnets() {
        if (company == null) {
            Subnet[] empty = {};
            return empty;
        }
        
        return company.getSubnets();
    }
    
    /**
     * Get all routers in array
     * @return routers array
     */
    public Router[] getRouters() {
        if (company == null) {
            Router[] empty = {};
            return empty;
        }
        
        return company.getRouters();
    }
    
    /**
     * Get all router connections in array
     * @return routerConnections array
     */
    public RouterConnection[] getConnections() {
        if (company == null) {
            RouterConnection[] empty = {};
            return empty;
        }
        
        return company.getConnections();
    }
    
    /**
     * Add router to company
     * @param r
     */
    public void addRouter(Router r) {
        company.addRouter(r);
        setSaved(false);
    }
    
    /**
     * Add subnet to company
     * @param s
     */
    public void addSubnet(Subnet s) {
        company.addSubnet(s);
        setSaved(false);            
    }
    
    /**
     * Remove subnet from company
     * @param s subnet to remove
     */
    public void removeSubnet(Subnet s) {
        company.removeSubnet(s);
        setSaved(false);            
    }
    
    /**
     * Add router connection to company
     * @param c connection to add
     */
    public void addConnection(RouterConnection c) {
        company.addConnection(c);
        HostAddress h = new HostAddress(c.getRouterAddress().getAddress(), "router");
        c.getSubnet().addHost(h);
        setSaved(false);            
    }
    
    /**
     * Open SQL connection
     */
    public void openConnection() {
        company = new Company();
        setSaved(true);  
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LouisDhauwe", "root", "Azerty123");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Close SQL connection
     */
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return if database is saved
     */
    public boolean isSaved() {
        if (company == null || company.getSubnets().length == 0) {
            return true;
        }
        
        return saved;
    }

    /**
     * @param saved the database saved state
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
