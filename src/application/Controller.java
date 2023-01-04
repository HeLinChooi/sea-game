package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.logic.Sea;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Controller implements Initializable{
	// the name of field annotated with @FXML must be same as fx:id
	@FXML
	private ImageView myImage;
	@FXML
	private TextArea dirtyness;
	@FXML
	private TextArea points;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Sea sea = Sea.getInstance();
		// animation
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(myImage);
		translate.setDuration(Duration.millis(2000));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		translate.setAutoReverse(true);
		translate.setByX(-250);
		translate.play();
		
		// set value of dirtyness
		dirtyness.appendText(String.valueOf(sea.getDirtyness()));
		points.appendText(String.valueOf(sea.getPoints()));
	}

}
