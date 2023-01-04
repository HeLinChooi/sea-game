package application.logic;

public class Sea {
	private static Sea uniqueInstance;
	private int dirtyness = 0;
	private int points = 0;
	private Sea() {}
	public static Sea getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Sea();
		}
		return uniqueInstance;
	}
	public int getDirtyness() {
		return dirtyness;
	}
	public void setDirtyness(int dirtyness) {
		this.dirtyness = dirtyness;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
}
