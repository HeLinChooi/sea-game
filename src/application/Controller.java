package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import application.command.AppearCommand;
import application.command.DisappearCommand;
import application.command.VisibilityManager;
import application.logic.Sea;
import application.rubbish.Rubbish;
import application.rubbish.SimpleRubbishFactory;
import application.state.Dock;
import application.state.Ship;
import application.strategy.BigSmall;
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
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller implements Initializable {
  // the name of field annotated with @FXML must be same as fx:id
  @FXML
  private AnchorPane backgroundAnchorPane;
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
  @FXML
  private ImageView dockImage;
  @FXML
  private ImageView boatImage;

  VisibilityManager visibilityManager;

  private File directory;
  private File[] files;
  private ArrayList<File> songs;
  private int songNumber;
  private Media media;
  private MediaPlayer mediaPlayer;

  private SimpleRubbishFactory simpleRubbishFactory = new SimpleRubbishFactory();

  private Dock dock = new Dock("Peaceful Dock");
  List<String> shipNames = new ArrayList<>(Arrays.asList("Serenity Voyager", "Tranquility Explorer", "Calm Seas",
      "Peaceful Journey", "Relaxation Voyager", "Serene Voyager", "Gentle Waves", "Ocean Oasis",
      "Paradise Explorer", "Island Retreat", "Coastal Breeze", "Lazy Days", "Sunny Seas", "Paradise Cruiser",
      "Relaxing Oceans", "Ocean Bliss", "Tropical Retreat", "Peaceful Waters", "Relax & Sail",
      "Island Escape", "Beachcomber", "Ocean Harmony", "Sea of Tranquility", "Restful Voyage",
      "Serenity Explorer", "Peaceful Explorer", "Tranquil Voyager", "Calming Seas", "Relaxing Waters",
      "Ocean Sanctuary"));
  Random rand = new Random();

  // creating sea creature objects
  Fish fish = new Fish(new HorizontalMove(), new NormalRotate(), new Fade());
  StarFish starFish = new StarFish(new VerticalMove(), new SpinRotate(), new BigSmall());
  JellyFish jellyFish = new JellyFish(new VerticalMove(), new SpinRotate(), new Fade());
  Crab crab = new Crab(new HorizontalMove(), new NormalRotate(), new BigSmall());
  Turtle turtle = new Turtle(new HorizontalMove(), new NormalRotate(), new Fade());

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // playMedia();
    // songLabel.setText(songs.get(songNumber).getName());

    myFish.setVisible(false);
    myStarFish.setVisible(false);
    myJellyFish.setVisible(false);
    myCrab.setVisible(false);
    myTurtle.setVisible(false);
    visibilityManager = new VisibilityManager();

    Sea sea = Sea.getInstance();
    // set value of dirtyness
    dirtyness.textProperty().bind(Bindings.convert(sea.dirtynessProperty()));
    points.textProperty().bind(Bindings.convert(sea.pointsProperty()));

    // add rubbish
    generateRubbish();
    changeBackgroundBasedOnDirtyness();
  }

  public void generateRubbish() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {

      @Override
      public void run() { // Function runs every 5 seconds.
        // Run the code you want here
        Platform.runLater(() -> {
          Rubbish r1 = simpleRubbishFactory.createRubbish("bottle", rubbishAnchorPane);
          Rubbish r2 = simpleRubbishFactory.createRubbish("bag", rubbishAnchorPane);

          rubbishAnchorPane.getChildren().addAll(r1.getImageView(), r2.getImageView());
        });
      }
    }, 0, 5000);
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
        } else {
          Platform.runLater(() -> {
            BackgroundImage bgImg = new BackgroundImage(
                new Image(getClass().getResourceAsStream("images/background.jpg"),
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
        // System.out.println(file);
      }
    }

    media = new Media(songs.get(songNumber).toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
  }

  private void fishVisibilityControl() {
    if (!myFish.isVisible()) {
      visibilityManager.setCommand(new AppearCommand(myFish));
      visibilityManager.process();
    } else {
      visibilityManager.setCommand(new DisappearCommand(myFish));
      visibilityManager.process();
    }
  }

  private void starFishVisibilityControl() {
    if (!myStarFish.isVisible()) {
      visibilityManager.setCommand(new AppearCommand(myStarFish));
      visibilityManager.process();
    } else {
      visibilityManager.setCommand(new DisappearCommand(myStarFish));
      visibilityManager.process();
    }
  }

  private void jellyFishVisibilityControl() {
    if (!myJellyFish.isVisible()) {
      visibilityManager.setCommand(new AppearCommand(myJellyFish));
      visibilityManager.process();
    } else {
      visibilityManager.setCommand(new DisappearCommand(myJellyFish));
      visibilityManager.process();
    }
  }

  private void crabVisibilityControl() {
    if (!myCrab.isVisible()) {
      visibilityManager.setCommand(new AppearCommand(myCrab));
      visibilityManager.process();
    } else {
      visibilityManager.setCommand(new DisappearCommand(myCrab));
      visibilityManager.process();
    }
  }

  private void turtleVisibilityControl() {
    if (!myTurtle.isVisible()) {
      visibilityManager.setCommand(new AppearCommand(myTurtle));
      visibilityManager.process();
    } else {
      visibilityManager.setCommand(new DisappearCommand(myTurtle));
      visibilityManager.process();
    }
  }

  public void fishButtonOnKeyboard(KeyEvent e) throws IOException {
    KeyCode code = e.getCode();
    if (code == KeyCode.ENTER) {
      fishVisibilityControl();
    }
  }

  public void starFishButtonOnKeyboard(KeyEvent e) throws IOException {
    KeyCode code = e.getCode();
    if (code == KeyCode.ENTER) {
      starFishVisibilityControl();
    }
  }

  public void jellyFishButtonOnKeyboard(KeyEvent e) throws IOException {
    KeyCode code = e.getCode();
    if (code == KeyCode.ENTER) {
      jellyFishVisibilityControl();
    }
  }

  public void crabButtonOnKeyboard(KeyEvent e) throws IOException {
    KeyCode code = e.getCode();
    if (code == KeyCode.ENTER) {
      crabVisibilityControl();
    }
  }

  public void turtleButtonOnKeyboard(KeyEvent e) throws IOException {
    KeyCode code = e.getCode();
    if (code == KeyCode.ENTER) {
      turtleVisibilityControl();
    }
  }

  // Fish Button Method
  public void fishButton() throws IOException {
    fishVisibilityControl();
  }

  // StarFish Button Method
  public void starFishButton() throws IOException {
    starFishVisibilityControl();
  }

  // JellyFish Button Method
  public void jellyFishButton() throws IOException {
    jellyFishVisibilityControl();
  }

  // Crab Button Method
  public void crabButton() throws IOException {
    crabVisibilityControl();
  }

  // Turtle Button Method
  public void turtleButton() throws IOException {
    turtleVisibilityControl();
  }

  public void addShip() {
    int shipNameIndex = rand.nextInt(shipNames.size());
    Ship ship = new Ship(shipNames.get(shipNameIndex));
    dock.addShip(ship, backgroundAnchorPane);
    shipNames.remove(shipNameIndex);
  }
}
