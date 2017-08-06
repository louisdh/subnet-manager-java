/* 
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */
package Objects;

import java.util.Objects;

/**
 *
 * @author louisdhauwe
 */
public class Router {
    
    private String name;
    
    /**
     *
     * @param name
     */
    public Router(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Router)) {
            return false;
        }
        
        Router r1 = (Router)o;

        return name.equals(r1.getName());
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    @Override
    public String toString() {
        return name;
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

    
}
