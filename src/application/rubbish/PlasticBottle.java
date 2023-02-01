package application.rubbish;

import java.util.Random;

import application.logic.Sea;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class PlasticBottle implements Rubbish {

  Image image;
  ImageView imageView;
  AnchorPane rubbishAnchorPane;
  int dirtiness = 1;

  public PlasticBottle(AnchorPane rubbishAnchorPane) {
    this.rubbishAnchorPane = rubbishAnchorPane;
    image = new Image(getClass().getResourceAsStream("/application/images/plastic-bottle.png"));
    imageView = new ImageView(image);
    imageView.setFitHeight(50);
    imageView.setPreserveRatio(true);
    int randomX = new Random().nextInt((int) Math.round(rubbishAnchorPane.getWidth()));
    int randomY = new Random().nextInt((int) Math.round(rubbishAnchorPane.getHeight()));
    imageView.setX(randomX);
    imageView.setY(randomY);

    // animation
    Path path = new Path();
    path.getElements().add(new MoveTo(randomX, randomY));
    path.getElements().add(new CubicCurveTo(randomX, 0, 0, randomY, randomX, randomY));

    PathTransition pathT = new PathTransition();
    pathT.setDuration(Duration.millis(10000));
    pathT.setPath(path);
    pathT.setNode(imageView);
    pathT.setCycleCount(Timeline.INDEFINITE);
    pathT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pathT.setAutoReverse(true);
    pathT.play();

    addDirtiness();
    listenToRemove();
  }

  @Override
  public void listenToRemove() {
    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        System.out.println("Bottle removed");
        rubbishAnchorPane.getChildren().remove(getImageView());
        removeDirtiness();
        addPoint();
        event.consume();
      }
    });
  }

  @Override
  public ImageView getImageView() {
    return imageView;
  }

  public void addDirtiness() {
    Sea.getInstance().setDirtyness(Sea.getInstance().getDirtyness() + dirtiness);
  }

  public void addPoint() {
    Sea.getInstance().setPoints(Sea.getInstance().getPoints() + 2);
  }

  public void removeDirtiness() {
    Sea.getInstance().setDirtyness(Sea.getInstance().getDirtyness() - dirtiness);
  }

}
