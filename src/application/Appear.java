package application;

import javafx.scene.image.ImageView;

public class Appear implements VisibilityBehaviour{

	@Override
	public boolean action(boolean creatureVisibility, ImageView creatureImage) {
		creatureImage.setVisible(false);
		return false;
	}

}