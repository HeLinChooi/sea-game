package application.strategy.model;

import application.strategy.HoriVertiMove;
import application.strategy.RotateMoves;
import application.strategy.SpecialMoves;
import application.strategy.VisibilityBehaviour;
import javafx.scene.image.ImageView;

public abstract class SeaCreature {
	
	VisibilityBehaviour visibilitybehaviour;
	HoriVertiMove horiVertiMove;
	RotateMoves rotateMoves;
	SpecialMoves specialMoves;
	
	
	public SeaCreature(VisibilityBehaviour visibilitybehaviour, HoriVertiMove horiVertiMove,
			RotateMoves rotateMoves, SpecialMoves specialMoves) {
		this.visibilitybehaviour = visibilitybehaviour;
		this.horiVertiMove = horiVertiMove;
		this.rotateMoves = rotateMoves;
		this.specialMoves = specialMoves;
	}

	
	public boolean performAction(boolean creatureVisibility, ImageView creatureImage) {
		return visibilitybehaviour.action(creatureVisibility, creatureImage);
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
	
	public void setBehaviour(VisibilityBehaviour acb) {
		this.visibilitybehaviour = acb;
	}
}
