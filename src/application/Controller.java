package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import application.logic.Sea;
import application.rubbish.Rubbish;
import application.rubbish.SimpleRubbishFactory;
import application.strategy.Appear;
import application.strategy.BigSmall;
import application.strategy.Disappear;
import application.strategy.Fade;
import application.strategy.HorizontalMove;
import application.strategy.NormalRotate;
import application.strategy.SpinRotate;
import application.strategy.VerticalMove;
import application.strategy.model.Crab;
import application.strategy.model.Fish;
import application.strategy.model.JellyFish;
import application.strategy.model.StarFish;
import application.strategy.model.Turtle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller implements Initializable {
  // the name of field annotated with @FXML must be same as fx:id
  @FXML
  private AnchorPane backgroundAnchorPane;
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
  private int[] speeds = { 50, 100, 125, 150 };

  private Timer timer;
  private TimerTask task;

  private boolean running;

  private Media media;
  private MediaPlayer mediaPlayer;

  private SimpleRubbishFactory simpleRubbishFactory = new SimpleRubbishFactory();
  // creating sea creature objects and setting their initial visibility
  // 1
  private boolean fishVisible = false;
  Fish fish = new Fish(new Disappear(), new HorizontalMove(), new NormalRotate(), new Fade());
  // 2
  private boolean starFishVisible = false;
  StarFish starFish = new StarFish(new Disappear(), new VerticalMove(), new SpinRotate(), new BigSmall());
  // 3
  private boolean jellyFishVisible = false;
  JellyFish jellyFish = new JellyFish(new Disappear(), new VerticalMove(), new SpinRotate(), new Fade());
  // 4
  private boolean crabVisible = false;
  Crab crab = new Crab(new Disappear(), new HorizontalMove(), new NormalRotate(), new BigSmall());
  // 5
  private boolean turtleVisible = false;
  Turtle turtle = new Turtle(new Disappear(), new HorizontalMove(), new NormalRotate(), new Fade());

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    playMedia();
    // songLabel.setText(songs.get(songNumber).getName());

    myFish.setVisible(false);
    myStarFish.setVisible(false);
    myJellyFish.setVisible(false);
    myCrab.setVisible(false);
    myTurtle.setVisible(false);

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
    dirtyness.textProperty().bind(Bindings.convert(sea.dirtynessProperty()));
    points.appendText(String.valueOf(sea.getPoints()));

    changeBackgroundBasedOnDirtyness();
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
    }, 0, 3000);
  }

  public void changeBackgroundBasedOnDirtyness() {
    Sea.getInstance().dirtynessProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if ((int) newValue > 6) {
          Platform.runLater(() -> {

            BackgroundImage bgImg = new BackgroundImage(
                new Image(getClass().getResourceAsStream("images/background-polluted.jpg"),
                    backgroundAnchorPane.getWidth(), backgroundAnchorPane.getHeight(), true, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
            backgroundAnchorPane.setBackground(new Background(bgImg));
          });
        } else if ((int) newValue > 4) {
          Platform.runLater(() -> {

            BackgroundImage bgImg = new BackgroundImage(
                new Image(getClass().getResourceAsStream("images/background-semi-polluted.jpg"),
                    backgroundAnchorPane.getWidth(), backgroundAnchorPane.getHeight(), true, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
            backgroundAnchorPane.setBackground(new Background(bgImg));
          });
        } else if ((int) newValue > 2) {
          Platform.runLater(() -> {

            BackgroundImage bgImg = new BackgroundImage(
                new Image(getClass().getResourceAsStream("images/background-little-polluted.jpg"),
                    backgroundAnchorPane.getWidth(), backgroundAnchorPane.getHeight(), true, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
            backgroundAnchorPane.setBackground(new Background(bgImg));
          });
        }

      }
    });
  }

  public void playMedia() {
    songs = new ArrayList<File>();
    directory = new File("music");

    // files will get all music from music folder
    files = directory.listFiles();

    if (files != null) {
      for (File file : files) {
        songs.add(file);
        System.out.println(file);
      }
    }

    media = new Media(songs.get(songNumber).toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
  }

  public void pauseMedia() {

  }

  // Fish Button Method
  public void fishButton(ActionEvent e) throws IOException {

    // if Fish is visible
    if (fishVisible) {
      fish.setBehaviour(new Appear());
      fishVisible = (boolean) fish.performAction(fishVisible, myFish);
    } else {
      fish.setBehaviour(new Disappear());
      fishVisible = (boolean) fish.performAction(fishVisible, myFish);
    }

    // end of FishButton Method
  }

  // StarFish Button Method
  public void starFishButton(ActionEvent e) throws IOException {

    // if StarFish is visible
    if (starFishVisible) {
      starFish.setBehaviour(new Appear());
      starFishVisible = (boolean) starFish.performAction(starFishVisible, myStarFish);
    } else {
      starFish.setBehaviour(new Disappear());
      starFishVisible = (boolean) starFish.performAction(starFishVisible, myStarFish);
    }

    // end of StarFishButton Method
  }

  // JellyFish Button Method
  public void jellyFishButton(ActionEvent e) throws IOException {

    // if StarFish is visible
    if (jellyFishVisible) {
      jellyFish.setBehaviour(new Appear());
      jellyFishVisible = (boolean) jellyFish.performAction(jellyFishVisible, myJellyFish);
    } else {
      jellyFish.setBehaviour(new Disappear());
      jellyFishVisible = (boolean) jellyFish.performAction(jellyFishVisible, myJellyFish);
    }

    // end of JellyFishButton Method
  }

  // Crab Button Method
  public void crabButton(ActionEvent e) throws IOException {

    // if StarFish is visible
    if (crabVisible) {
      crab.setBehaviour(new Appear());
      crabVisible = (boolean) crab.performAction(crabVisible, myCrab);
    } else {
      crab.setBehaviour(new Disappear());
      crabVisible = (boolean) crab.performAction(crabVisible, myCrab);
    }

    // end of CrabButton Method
  }

  // Turtle Button Method
  public void turtleButton(ActionEvent e) throws IOException {

    // if StarFish is visible
    if (turtleVisible) {
      turtle.setBehaviour(new Appear());
      turtleVisible = (boolean) turtle.performAction(turtleVisible, myTurtle);
    } else {
      turtle.setBehaviour(new Disappear());
      turtleVisible = (boolean) turtle.performAction(turtleVisible, myTurtle);
    }

    // end of TurtleButton Method
  }

}
