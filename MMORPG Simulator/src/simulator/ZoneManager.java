package simulator;

import java.util.ArrayList;
import java.util.List;

public class ZoneManager {
    private static double zoneWidth = 10000;
    private static List<Zone> zones = new ArrayList<Zone>();
    private static int rows;
    private static int cols;
 
    public static List<Zone> getZones() {
        return zones;
    }
    
    public static void setZones(List<Zone> z) {
    	zones = z;
    }
     
    public static void setRows(int r) {
        rows = r;
    }
     
    public static void setCols(int c) {
        cols = c;
    }
 
    public static Zone getZone(double xpos, double ypos) {
        int hZone = (int)Math.floor(xpos / zoneWidth); // zone col
        int vZone = (int)Math.floor(ypos / zoneWidth); // zone row
        int zone = hZone + vZone * cols;
 
        return zones.get(zone);
    }
    public static double getZoneWidth() {
        return zoneWidth;
    }
     
    public static int getCols() {
        return cols;
    }
     
    public static int getRows() {
        return rows;
    }
}