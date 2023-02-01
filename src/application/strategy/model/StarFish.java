package application.strategy.model;

import application.strategy.HoriVertiMove;
import application.strategy.RotateMoves;
import application.strategy.SpecialMoves;

public class StarFish extends SeaCreature {

	public StarFish(HoriVertiMove horiVertiMove,
			RotateMoves rotateMoves, SpecialMoves specialMoves) {
		super(horiVertiMove, rotateMoves, specialMoves);
	}

}