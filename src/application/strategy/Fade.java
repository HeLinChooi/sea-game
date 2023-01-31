package application.strategy;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Fade implements SpecialMoves{

	@Override
	public void special(ImageView creatureImage) {
		// TODO Auto-generated method stub
		FadeTransition fade = new FadeTransition();
		fade.setNode(creatureImage);
		fade.setDuration(Duration.millis(5000));
		fade.setCycleCount(TranslateTransition.INDEFINITE);
		fade.setInterpolator(Interpolator.LINEAR);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.play();
	}

}
