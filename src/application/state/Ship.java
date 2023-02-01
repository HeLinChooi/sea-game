package application.state;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Ship {

  private DockState objState;
  private final String SHIP_NAME;
  public static final int RETURNING = 1;
  public static final int NEUTRAL = 0;
  public static final int LEAVING = -1;
  private int isThisShipReturning;
  Image image;
  ImageView imageView;

  public Ship(String shipName) {
    this.SHIP_NAME = shipName;
    isThisShipReturning = NEUTRAL;
    // JAVAFX
    image = new Image(getClass().getResourceAsStream("/application/images/boat.png"));
    imageView = new ImageView(image);
    imageView.setFitHeight(50);
    imageView.setPreserveRatio(true);
    imageView.setX(721);
    imageView.setY(224);
  }

  public ImageView getImageView() {
    return imageView;
  }

  public void setState(DockState newState) {
    objState = newState;
    if (newState instanceof ShippingCargoState) {
      // animation
      TranslateTransition translate2 = new TranslateTransition();
      translate2.setNode(imageView);
      translate2.setDuration(Duration.millis(5000));
      translate2.setByX(-850);
      translate2.play();
    } else if (newState instanceof IdleState) {
      // TranslateTransition translate = new TranslateTransition();
      // translate.setNode(imageView);
      // translate.setDuration(Duration.millis(5000));
      // translate.setByX(850);
      // translate.play();
    }
  }

  public int isThisShipReturning() {
    return isThisShipReturning;
  }

  public void setIsThisShipReturning(int isThisShipReturning) {
    switch (isThisShipReturning) {
      case RETURNING:{

        this.isThisShipReturning = RETURNING;
        TranslateTransition translate = new TranslateTransition();
      translate.setNode(imageView);
      translate.setDuration(Duration.millis(5000));
      translate.setByX(850);
      translate.play();
        break;
      }
      case NEUTRAL:
        this.isThisShipReturning = NEUTRAL;
        break;
      case LEAVING:
        this.isThisShipReturning = LEAVING;
        break;
    }
  }

  public String getSHIP_NAME() {
    return SHIP_NAME;
  }

  @Override
  public String toString() {
    return this.getSHIP_NAME() + " --- current state: " + this.objState.toString() + " \n";
  }
}
