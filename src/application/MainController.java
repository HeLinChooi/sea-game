package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.logic.Sea;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MainController implements Initializable{
	
	
	
	// the name of field annotated with @FXML must be same as fx:id
	@FXML
	private ImageView myFish;
	@FXML
	private ImageView myStarFish;
	@FXML
	private ImageView myJellyFish;
	@FXML
	private ImageView myCrab;
	@FXML
	private ImageView myTurtle;
	
	private File directory;
	private File[] files;
	
	private ArrayList<File> songs;
	
	private int songNumber;
	private int[] speeds = {50, 100, 125, 150};
	
	private Timer timer;
	private TimerTask task;
	
	private boolean running;
	
	private Media media;
	private MediaPlayer mediaPlayer;
	
	@FXML
	private TextArea dirtyness;
	@FXML
	private TextArea points;
	
	

	// creating sea creature objects and setting their initial visibility
	//1
	private boolean fishVisible = false;
	Fish fish = new Fish(new Disappear(), new HorizontalMove(), new NormalRotate(), new Fade());
	//2
	private boolean starFishVisible = false;
	StarFish starFish = new StarFish(new Disappear(), new VerticalMove(), new SpinRotate(), new BigSmall());
	//3
	private boolean jellyFishVisible = false;
	JellyFish jellyFish = new JellyFish(new Disappear(), new VerticalMove(), new SpinRotate(), new Fade());
	//4
	private boolean crabVisible = false;
	Crab crab = new Crab(new Disappear(), new HorizontalMove(), new NormalRotate(), new BigSmall());
	//5
	private boolean turtleVisible = false;
	Turtle turtle = new Turtle(new Disappear(), new HorizontalMove(), new NormalRotate(), new Fade());
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		songs = new ArrayList<File>();
		
		directory = new File("music");
		
		// files will get all music from music folder
		files = directory.listFiles();
		
		if(files != null) {
			
			for(File file : files) {
				songs.add(file);
				System.out.println(file);
				
			}
			
		}
		
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
//		songLabel.setText(songs.get(songNumber).getName());
		
		myFish.setVisible(false);
		myStarFish.setVisible(false);
		myJellyFish.setVisible(false);
		myCrab.setVisible(false);
		myTurtle.setVisible(false);
		
		Sea sea = Sea.getInstance();

		fish.performMove(myFish);
		fish.performRotate(myFish);
		starFish.performRotate(myStarFish);
		starFish.performSpecials(myStarFish);
		jellyFish.performMove(myJellyFish);
		jellyFish.performSpecials(myJellyFish);
		turtle.performMove(myTurtle);
		
		crab.performRotate(myCrab);
		
		// set value of dirtyness
		dirtyness.appendText(String.valueOf(sea.getDirtyness()));
		points.appendText(String.valueOf(sea.getPoints()));
	}
	

}
