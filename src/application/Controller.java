package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.logic.Sea;
import application.rubbish.Rubbish;
import application.rubbish.SimpleRubbishFactory;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Controller implements Initializable {
	// the name of field annotated with @FXML must be same as fx:id
	@FXML
	private ImageView myImage;
	@FXML
	private ImageView rubbishImage;
	@FXML
	private TextArea dirtyness;
	@FXML
	private TextArea points;
	@FXML
	private AnchorPane rubbishAnchorPane;

	private SimpleRubbishFactory simpleRubbishFactory = new SimpleRubbishFactory();

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

		// add rubbish
		generateRubbish();

		// set value of dirtyness
		dirtyness.appendText(String.valueOf(sea.getDirtyness()));
		points.appendText(String.valueOf(sea.getPoints()));
	}

	public void generateRubbish() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() { // Function runs every MINUTES minutes.
				// Run the code you want here
				Platform.runLater(() -> {
					Rubbish r1 = simpleRubbishFactory.createRubbish("bottle", rubbishAnchorPane);
					Rubbish r2 = simpleRubbishFactory.createRubbish("bag", rubbishAnchorPane);

					rubbishAnchorPane.getChildren().addAll(r1.getImageView(), r2.getImageView());
				});
			}
		}, 0, 10000);
	}

}
