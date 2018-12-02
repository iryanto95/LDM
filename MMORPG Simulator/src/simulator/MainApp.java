package simulator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {

	public static void main(String[] args) {
		final int noOfPlayers = 1000;
		final int timesteps = 2000;
		final int timePeriod = 1;
		
		double mapHeight = 100000;
		double mapWidth  = 100000;
		List<Player> players     = new ArrayList<Player>();
		List<Zone>   zones       = new ArrayList<Zone>();
		
		Random rand = new Random();
		
		int zoneId = 0;
        double heightIterator = 0, widthIterator = 0;
		String[] mapTypes = new String[] {"town", "wilderness", "dungeon"};
		

        int[][] map = new int[][]{
        	{2, 2, 0, 0, 2, 1, 0, 0, 0, 0},
        	{2, 0, 2, 2, 1, 2, 1, 2, 1, 2},
        	{2, 1, 1, 0, 0, 2, 0, 1, 1, 0},
        	{1, 1, 0, 2, 2, 2, 1, 1, 0, 0},
        	{1, 1, 1, 0, 1, 2, 1, 1, 1, 2},
        	{1, 0, 0, 1, 1, 2, 2, 2, 1, 0},
        	{1, 1, 0, 2, 2, 2, 1, 0, 1, 1},
        	{2, 2, 0, 0, 1, 0, 1, 1, 1, 0},
        	{2, 0, 2, 0, 0, 0, 1, 0, 2, 0},
        	{2, 2, 2, 1, 2, 1, 0, 0, 2, 1}
        };
        while(heightIterator < mapHeight) {
            widthIterator = 0;
            while(widthIterator < mapWidth) {
                zones.add(new Zone(zoneId, new Double[]{widthIterator, heightIterator}, ZoneManager.getZoneWidth(), mapTypes[map[(int) (heightIterator/ZoneManager.getZoneWidth())][(int) (widthIterator/ZoneManager.getZoneWidth())]]));
                zoneId++;
                widthIterator += ZoneManager.getZoneWidth();
            }
            heightIterator += ZoneManager.getZoneWidth();
        }
		ZoneManager.setZones(zones);
 
        // Get the actual height and width of the map in terms of zones
        int rows = (int) Math.ceil(mapHeight / ZoneManager.getZoneWidth());
        int cols = (int) Math.ceil(mapWidth / ZoneManager.getZoneWidth());
        ZoneManager.setRows(rows);
        ZoneManager.setCols(cols);
		
        // Initialize players
        for(int i = 0; i < noOfPlayers; i++) {
        	double x = mapWidth  * rand.nextDouble();
        	double y = mapHeight * rand.nextDouble();
            Zone zone = ZoneManager.getZone(x, y);
            Player p = new Player(i, new double[] {x, y, 0}, new int[] {25, 75, 500});
            ZoneManager.getZones().get(zone.getId()).addPlayer(p);
            players.add(p);
        }
        
        // Generate dataset
        try {
			PrintWriter out = new PrintWriter("dataset.txt");
			for(int i = 0; i < timesteps; i++) {
	        	// Randomly assign player behavior
	        	for(int j = 0; j < players.size(); j++) {
	        		Player p = players.get(j);
	            	int behavior = rand.nextInt(2); // 0: random walk, 1: follow player
	            	if (behavior == 0)
	            		p.setFollowing(null);

	            	else if (behavior == 1) {
	            		List<Player> neighbors = ZoneManager.getZone(p.getPosition()[0], p.getPosition()[1]).getPlayers(); // Other players in the same zone
	            		if (neighbors.size() - 1 > 0) { // Exclude self
	            			
	            			// Remove self from list of neighbors
	            			for (int k = 0; k < neighbors.size(); k++) {
	            				if (p.getId() == neighbors.get(k).getId())
	            					neighbors.remove(k);
	            			}
	            			
		            		Player leader = neighbors.get(rand.nextInt(neighbors.size()));
		            		p.setFollowing(leader);
	            		}
	            		else
	            			p.setFollowing(null);
	            	}
	            	else
//	            		System.out.println("Unhandled behavior: " + behavior);

	        		p.iterate(i, timePeriod);
//	        		System.out.println(p.getId() + ": " + p.getPosition()[0] + ", " +  p.getPosition()[1] + ", " +  p.getPosition()[2]);
	        		out.println(p.getId() + ": " + p.getPosition()[0] + ", " +  p.getPosition()[1] + ", " +  p.getPosition()[2]);
	        	}
	        }
			out.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        
        
	}
	
}
