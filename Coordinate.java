
public class Coordinate {
	
	private int id;
	private int axisX;
	private int axisY;
	private int clusterNum;
	
	
	public Coordinate(int id, int axisX, int axisY) {
		super();
		this.id = id;
		this.axisX = axisX;
		this.axisY = axisY;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAxisX() {
		return axisX;
	}
	public void setAxisX(int axisX) {
		this.axisX = axisX;
	}
	public int getAxisY() {
		return axisY;
	}
	public void setAxisY(int axisY) {
		this.axisY = axisY;
	}

	public int getClusterNum() {
		return clusterNum;
	}
	public void setClusterNum(int clusterNum) {
		this.clusterNum = clusterNum;
	}
	@Override
	public String toString() {
		//return "Coordinate [id=" + id + ", X=" + axisX + ", Y=" + axisY + ", cluster=" + clusterNum + "]";
		return "Coordinate [id=" + id + "]";
	}

	

}
