package simulator;

public class Entity {
	private int id;
	private double[] position;
	private double[] velocity;
	private double[] acceleration;
	
	private int[]    polygons;
	private double[] size; // size of bounding box (width, height)
	
    public Entity(int id, double[] position, double[] velocity, double[] acceleration, int[] polygons) {
    	this.id           = id;
        this.position     = position;
        this.velocity     = velocity;
        this.acceleration = acceleration;
        this.polygons     = polygons;
    }
    
    public int getId() { return this.id; }
 
    public double[] getPosition()     { return this.position; }
    public double[] getVelocity()     { return this.velocity; }
    public double[] getAcelleration() { return this.acceleration; }
 
    protected void setPosition(double[] position)         { this.position     = position; }
    protected void setVelocity(double[] velocity)         { this.velocity     = velocity; }
    protected void setAcceleration(double[] acceleration) { this.acceleration = acceleration; }
 
    protected void move(double period) {
        double posx = this.position[0] + this.velocity[0] * period + 0.5 * this.acceleration[0] * period * period;
        double posy = this.position[1] + this.velocity[1] * period + 0.5 * this.acceleration[1] * period * period;
//        double posz = this.position[2] + this.velocity[2] * period + 0.5 * this.acceleration[2] * period * period;
        double velx = this.velocity[0] + this.acceleration[0] * period;
        double vely = this.velocity[1] + this.acceleration[1] * period;
//        double velz = this.velocity[2] + this.acceleration[2] * period;
        
//        if (Double.isNaN(posx)) {
//        	System.out.println("NaN -> " + this.id + " - " + posx + " - " + posy);
//        	System.out.println(this.position[0] + " | " + this.velocity[0] + " | " + this.acceleration[0]);
//        	System.out.println(this.position[1] + " | " + this.velocity[1] + " | " + this.acceleration[1]);
//        }

        this.setPosition(new double[]{posx, posy});
        this.setVelocity(new double[]{velx, vely});
//        this.setPosition(new double[]{posx, posy, posz});
//        this.setVelocity(new double[]{velx, vely, velz});
    }
    
    protected Zone getZones() {
    	return null;
    }
     
    private double calculateDistance(Entity entity) {
        // Calculate the distance from this entity to an entity
        double xdif = this.getPosition()[0] - entity.getPosition()[0];
        double ydif = this.getPosition()[1] - entity.getPosition()[1];
 
        return Math.sqrt(xdif * xdif + ydif * ydif);
    }
     
    private double calculateDistance(Double[] point) {
        // Calculate the distance from this entity to a point
        double xdif = this.getPosition()[0] - point[0];
        double ydif = this.getPosition()[1] - point[1];
 
        return Math.sqrt(xdif * xdif + ydif * ydif);
    }
}
