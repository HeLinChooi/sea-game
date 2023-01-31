package application;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass(). getResource("Main.fxml"));
			Scene scene = new Scene(root, Color.LIGHTSKYBLUE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Image icon = new Image("fish.png");
			
			
			stage.setScene(scene);
			stage.setResizable(false);
			// stage.getIcons().add(icon);
			stage.setTitle("Sea Game");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}