package application.strategy.model;

import application.strategy.HoriVertiMove;
import application.strategy.RotateMoves;
import application.strategy.SpecialMoves;

public class JellyFish extends SeaCreature {

  public JellyFish(HoriVertiMove horiVertiMove,
      RotateMoves rotateMoves, SpecialMoves specialMoves) {

    super(horiVertiMove, rotateMoves, specialMoves);

  }

}
