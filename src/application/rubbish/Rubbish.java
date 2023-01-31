package application.rubbish;

import javafx.scene.image.ImageView;

public interface Rubbish {

	public abstract ImageView getImageView();

	public abstract void listenToRemove();
}
