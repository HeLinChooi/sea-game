package application.strategy.model;

import application.strategy.Moves;
import javafx.scene.image.ImageView;

public abstract class SeaCreature {
	
	Moves moves;
	
	public SeaCreature(Moves moves) {
		this.moves = moves;
	}

	public void performMove(ImageView creatureImage) {
		moves.performMove(creatureImage);
	}
	
}
