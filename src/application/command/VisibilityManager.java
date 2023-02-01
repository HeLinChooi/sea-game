package application.command;

public class VisibilityManager {
  private Command command;

  public void process() {
    if (command != null) {
      command.execute();
    } else {
      System.out.println("Command not set");
    }
  }

  public void setCommand(Command c) {
    this.command = c;
  }
}
