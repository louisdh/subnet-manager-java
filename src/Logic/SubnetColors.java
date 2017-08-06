/*
 *  Subnet Manager – Java OO en Gegevens Banken Project
 */

package Logic;

import DatabaseConnection.DatabaseUtil;
import Objects.Subnet;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author louisdhauwe
 */
public class SubnetColors {
    
    /**
     * Random generator for colors
     */
    private static Random generator;
    
    /**
     * Seed for generating the same 'random' colors on each draw cycle
     */
    private static final int COLOR_SEED = 1212232532;
    
    /**
     * Creates array (with amount of subnets) of colors based on seed.
     * Will work for infinite subnets, 
     * and due to same seed give same color sequence on each call
     * @return color array
     */
    public static Color[] subnetColors() {
        Subnet[] subnets = DatabaseUtil.getInstance().getSubnets();

        // change seed for different colors
        generator = new Random(COLOR_SEED);
        
        Color[] colors = new Color[subnets.length];
        for (int i = 0; i < subnets.length; i++) {
            int rgb = Color.HSBtoRGB(randomGenerator()/255.0f, 1f, 1f);
            Color color = new Color(rgb);
            colors[i] = color.darker();
        }
        
        return colors;
        
    }
    
    private static int randomGenerator() {
        int num = Math.abs(generator.nextInt()) % 200;
        return num+56;
    }

}
