
public class Cluster {

	private int axisXCentroid;
	private int axisYCentroid;
	private int clusterNum;
	
	
	
	public Cluster(int clusterNum, int axisXCentroid, int axisYCentroid) {
		super();
		this.clusterNum = clusterNum;
		this.axisXCentroid = axisXCentroid;
		this.axisYCentroid = axisYCentroid;
	}
	
	public int getAxisXCentroid() {
		return axisXCentroid;
	}
	public void setAxisXCentroid(int axisXCentroid) {
		this.axisXCentroid = axisXCentroid;
	}
	public int getAxisYCentroid() {
		return axisYCentroid;
	}
	public void setAxisYCentroid(int axisYCentroid) {
		this.axisYCentroid = axisYCentroid;
	}
	public int getClusterNum() {
		return clusterNum;
	}
	public void setClusterNum(int clusterNum) {
		this.clusterNum = clusterNum;
	}

	@Override
	public String toString() {
		return "Cluster [Xc=" + axisXCentroid + ", Yc=" + axisYCentroid + ", cluster=" + clusterNum + "]";
	}
	
	public double calculateDistance(Coordinate coordinate) {
		return Math.sqrt(Math.pow((getAxisXCentroid() - coordinate.getAxisX()), 2) + Math.pow((getAxisYCentroid() - coordinate.getAxisY()),2));
    }
	
	public void defineCentroid(Coordinate coordinate) {
		setAxisXCentroid(coordinate.getAxisX());
		setAxisYCentroid(coordinate.getAxisY());
		System.out.println("(" + coordinate.getId()+ ") " + "     Xc:" + getAxisXCentroid() + " + " + coordinate.getAxisX() +  "       Yc:" + getAxisYCentroid() + " + " + coordinate.getAxisY());
	}
	
	public void updateCentroid(Coordinate coordinate) {
		System.out.println("(" + coordinate.getId()+ ") " + "     Xc:" + getAxisXCentroid() + " + " + coordinate.getAxisX() +  "       Yc:" + getAxisYCentroid() + " + " + coordinate.getAxisY());
		setAxisXCentroid((getAxisXCentroid()+coordinate.getAxisX()));
		setAxisYCentroid((getAxisYCentroid()+coordinate.getAxisY()));
	}
	
	public void calculateCentroid(int coordinatesNumb) {
		System.out.println("Update centroid Cluster " + getClusterNum());
		System.out.println(getClusterNum() + " -> " + "   Xc:" + getAxisXCentroid() + " / " + coordinatesNumb + " - Yc:" + getAxisYCentroid() + " / " + coordinatesNumb);
		setAxisXCentroid(getAxisXCentroid()/coordinatesNumb);
		setAxisYCentroid(getAxisYCentroid()/coordinatesNumb);
		System.out.println(getClusterNum() + " -> " + "   Xc:" + getAxisXCentroid() + " X:" + " - Yc:" + getAxisYCentroid());
	}


}
