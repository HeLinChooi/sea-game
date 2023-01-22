package application.rubbish;

import javafx.scene.layout.AnchorPane;

public class SimpleRubbishFactory {
	public Rubbish createRubbish(String type, AnchorPane rubbishAnchorPane) {
		Rubbish rubbish;

		if (type.equals("bottle")) {
			rubbish = new PlasticBottle(rubbishAnchorPane);
		} else {
			rubbish = new PlasticBag(rubbishAnchorPane);
		}

		return rubbish;
	}
}
