package application.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sea {
	private static Sea uniqueInstance;
//	private int dirtyness = 0;
	private SimpleIntegerProperty dirtyness = new SimpleIntegerProperty(this, "0");
	private int points = 0;

	private Sea() {
	}

	public static Sea getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Sea();
		}
		return uniqueInstance;
	}

	public IntegerProperty dirtynessProperty() {
		return dirtyness;
	}

	public int getDirtyness() {
		return dirtyness.get();
	}

	public void setDirtyness(int dirtyness) {
		this.dirtyness.set(dirtyness);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
