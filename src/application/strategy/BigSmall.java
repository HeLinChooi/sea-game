package application.strategy;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BigSmall implements SpecialMoves {

	@Override
	public void special(ImageView creatureImage) {
		// TODO Auto-generated method stub
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(creatureImage);
		scale.setDuration(Duration.millis(5000));
		scale.setCycleCount(TranslateTransition.INDEFINITE);
		scale.setInterpolator(Interpolator.LINEAR);
		scale.setByX(1.0);
		scale.setByY(1.0);
		scale.setAutoReverse(true);
		scale.play();
		
	}

}
