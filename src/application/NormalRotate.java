package application;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class NormalRotate implements RotateMoves{

	@Override
	public void rotate(ImageView creatureImage) {
		// TODO Auto-generated method stub
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(creatureImage);
		rotate.setDuration(Duration.millis(5000));
		rotate.setCycleCount(TranslateTransition.INDEFINITE);
		rotate.setAutoReverse(true);
		rotate.setFromAngle(-15);
		rotate.setToAngle(15);
		rotate.play();
		
	}

}
