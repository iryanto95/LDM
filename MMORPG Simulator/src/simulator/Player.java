package simulator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Player extends Entity {
	private int id;
	private Player following = null;
	private HashMap<Entity, Integer[]> cache;
	private double cleanupPeriod = 60;

	public Player(int id, double[] position, int[] polygons) {
		super(id, position, new double[]{0, 0}, new double[]{0, 0}, polygons);
//		super(id, position, new double[]{0, 0, 0}, new double[]{0, 0, 0}, polygons);
		this.id = id;
		this.cache = new HashMap<Entity, Integer[]>();
	}
	
	public Player getFollowing() {
		return this.following;
	}
	
	public void setFollowing(Player player) {
		this.following = player;
	}
	
	public void iterate(double time, double period) {
		double[] direction;
		if (following == null) {
			// Random walk
			Random rand   = new Random();
			Zone prevZone = null;
			Zone newZone  = null;
			
			// Check if destination zone type is same as current zone type
			do {
		        // Check if posx posy still in bounds
				double posx, posy;
				do {
					direction = new double[] {rand.nextInt(200)-100, rand.nextInt(200)-100};
					double magnitude = Math.sqrt(direction[0]*direction[0] + direction[1]*direction[1]);
					direction[0] = direction[0] * 2 / magnitude;
					direction[1] = direction[1] * 2 / magnitude;
					posx = this.getPosition()[0] + direction[0] * period + 0.5 * direction[0] * period * period;
			        posy = this.getPosition()[1] + direction[1] * period + 0.5 * direction[1] * period * period;
				} while (posx < 0 || posx >= 100000 || posy < 0 || posy >= 100000);
				
		        prevZone = ZoneManager.getZone(this.getPosition()[0], this.getPosition()[1]);
				newZone  = ZoneManager.getZone(posx, posy);
			} while (prevZone == null || prevZone.getType() != newZone.getType());
		}
		else {
			double[] leaderPosition = following.getPosition();
			direction = new double[] {leaderPosition[0] - this.getPosition()[0], leaderPosition[1] - this.getPosition()[1]};
			double magnitude = Math.sqrt(direction[0]*direction[0] + direction[1]*direction[1]);
			direction[0] = direction[0] * 2 / magnitude;
			direction[1] = direction[1] * 2 / magnitude;
		}
		
		this.setVelocity(direction);
		this.move(period);
		
		// Check interest
		List<Zone> subscribedZones = this.getSubscribedZones();
		
		// Check encounter
		
		// Check cache
		if (time % cleanupPeriod == 0) {
			this.cleanupCache();
		}
	}
	
	public List<Zone> getSubscribedZones() {
		List<Zone> zones = new ArrayList<Zone>();
		
		return zones;
	}
	
	public HashMap<Entity, Integer[]> getCache() { return this.cache; }
	public void addCache(Entity entity, int resolution, int time) { this.cache.put(entity, new Integer[] {resolution, time}); }
	public void removeCache(int id) {
		// Remove by id instead of index
		// TODO
	}
	public void cleanupCache() {
		
	}
}
