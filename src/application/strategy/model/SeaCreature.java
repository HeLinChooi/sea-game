package application.strategy.model;

import application.strategy.HoriVertiMove;
import application.strategy.RotateMoves;
import application.strategy.SpecialMoves;
import javafx.scene.image.ImageView;

public abstract class SeaCreature {
	
	HoriVertiMove horiVertiMove;
	RotateMoves rotateMoves;
	SpecialMoves specialMoves;
	
	
	public SeaCreature(HoriVertiMove horiVertiMove,
			RotateMoves rotateMoves, SpecialMoves specialMoves) {
		this.horiVertiMove = horiVertiMove;
		this.rotateMoves = rotateMoves;
		this.specialMoves = specialMoves;
	}

	public void performMove(ImageView creatureImage) {
		horiVertiMove.move(creatureImage);
	}
	
	public void performRotate(ImageView creatureImage) {
		rotateMoves.rotate(creatureImage);
	}
	
	public void performSpecials(ImageView creatureImage) {
		specialMoves.special(creatureImage);
	}
}
