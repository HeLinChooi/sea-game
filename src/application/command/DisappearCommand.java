package application.command;

import javafx.scene.image.ImageView;

public class DisappearCommand implements Command {
  private ImageView creatureImage;

  public DisappearCommand(ImageView creatureImage) {
    this.creatureImage = creatureImage;
  }

  @Override
  public boolean execute() {
    creatureImage.setVisible(false);
    return true;
  }
}
