package application.strategy;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class VerticalMove implements Moves{

	@Override
	public void performMove(ImageView creatureImage) {
		// TODO Auto-generated method stub
		
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(creatureImage);
		translate.setDuration(Duration.millis(5000));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		translate.setAutoReverse(true);
		translate.setByY(-50);
		translate.play();
		
	}

}
