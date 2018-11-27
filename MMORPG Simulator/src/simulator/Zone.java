package simulator;

import java.util.ArrayList;
import java.util.List;
 
public class Zone {
    private int id;
    private List<Player> players;
    private List<Zone> adjacentZones; // Up to 8 adjacent zones
    private Double[] tl,tr,bl,br,center; // Coordinates of the 4 corners
    private String type;
 
    public Zone(int id, Double[] tl, double zoneWidth, String type) {
        this.id = id;
        this.players = new ArrayList<Player>();
        this.adjacentZones = new ArrayList<Zone>();
        
        this.tl = tl;
        this.tr = tl.clone();
        this.tr[0] += zoneWidth;
        this.bl = tl.clone();
        this.bl[1] += zoneWidth;
        this.br = this.bl.clone();
        this.br[0] += zoneWidth;
        
        this.center = tl.clone();
        this.center[0] += zoneWidth/2;
        this.center[1] += zoneWidth/2;
        
        this.type = type;
    }
 
    public int getId() { return id; }
    public List<Zone> getAdjacentZones() { return adjacentZones;  }
    public void addAdjacentZones(Zone zone) {  this.adjacentZones.add(zone); }
    public List<Player> getPlayers() { return this.players; }
    public String getType() { return this.type; }
 
    public void addPlayer(Player player) {
        if(!this.players.contains(player))
            this.players.add(player);
    }
 
    public void removePlayer(Player player) {
        this.players.remove(player);
    }
     
    public List<Double[]> getCoordinates() {
        List<Double[]> res = new ArrayList<Double[]>();
        res.add(this.tl);
        res.add(this.tr);
        res.add(this.bl);
        res.add(this.br);
        res.add(this.center);
         
        return res;
    }
}