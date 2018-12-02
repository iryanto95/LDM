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
	
	public boolean checkContains(double[] point) {
		if (point[0] < this.xmin || point[0] > this.xmax)
			return false;
		if (point[1] < this.ymin || point[1] > this.ymax)
			return false;
		if (point[2] < this.zmin || point[2] > this.zmax)
			return false;
		return true;
	}
}
