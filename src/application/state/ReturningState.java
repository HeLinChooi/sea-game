package application.state;

import java.util.Random;

import javafx.scene.control.TextArea;

// State for ship deciding whether returning to the dock or leaving
// if ship is returning to the dock, the dock need reserve an IdleState for it
public class ReturningState implements DockState {

  Dock dock;
  static Ship ship;
  Random rand = new Random();

  public ReturningState(Ship ship, TextArea dockStatus) {
    ReturningState.ship = ship;
    String str = ship.getSHIP_NAME() + " is deciding whether returning to the dock...";
    dockStatus.setText(str);
    System.out.println(str);
  }

  public ReturningState(Dock dock) {
    this.dock = dock;
  }

  @Override
  public final boolean makeShippingWork(TextArea dockStatus) {
    if (rand.nextBoolean()) {
      ship.setIsThisShipReturning(Ship.RETURNING);
      System.out.println(ship.getSHIP_NAME() + " is returning to the dock.");
      dock.setState(new IdleState(dock, ship, dockStatus));
      return true;
    }
    ship.setIsThisShipReturning(Ship.LEAVING);
    dock.setState(new IdleState(dock, dockStatus));
    System.out.println("No ship returning");
    String str2 = ship.getSHIP_NAME() + " is going some where else.";
    dockStatus.setText(str2);
    System.out.println(str2);
    return false;
  }

  @Override
  public String toString() {
    return "Returning State ";
  }

  @Override
  public boolean addShip(Ship Ship, TextArea dockStatus) {
    System.out.println("Cannot add new ship due to the " + ship.getSHIP_NAME()
        + " haven't make returning decision to dock yet.");
    return false;
  }

}
