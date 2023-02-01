package application.strategy.model;

import application.strategy.HoriVertiMove;
import application.strategy.RotateMoves;
import application.strategy.SpecialMoves;

public class Fish extends SeaCreature {

	public Fish(HoriVertiMove horiVertiMove,
			RotateMoves rotateMoves, SpecialMoves specialMoves) {
		
		super( horiVertiMove, rotateMoves, specialMoves);
		
	}

}
