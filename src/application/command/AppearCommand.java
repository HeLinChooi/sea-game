package application.command;

import javafx.scene.image.ImageView;

public class AppearCommand implements Command {
  private ImageView creatureImage;

  public AppearCommand(ImageView creatureImage) {
    this.creatureImage = creatureImage;
  }

  @Override
  public boolean execute() {
    creatureImage.setVisible(true);
    return true;
  }
}