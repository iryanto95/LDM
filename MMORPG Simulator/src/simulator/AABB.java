package simulator;

public class AABB {
	private double xmin, ymin, zmin, xmax, ymax, zmax;
	
	public AABB() {}
	
	public void setAABB(double xi, double yi, double zi, double xa, double ya, double za) {
		this.xmin = xi;
		this.ymin = yi;
		this.zmin = zi;
		this.xmax = xa;
		this.ymax = ya;
		this.zmax = za;
	}
	
	public double[] getAABB() {
		return new double[] {this.xmin, this.ymin, this.zmin, this.xmax, this.ymax, this.zmax};
	}
}
