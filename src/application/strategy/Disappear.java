package application.strategy;

import javafx.scene.image.ImageView;

public class Disappear implements VisibilityBehaviour{

	@Override
	public boolean action(boolean creatureVisibility, ImageView creatureImage) {
		creatureImage.setVisible(true);
		return true;
	}

}
