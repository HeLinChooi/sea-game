package application.state;

import javafx.scene.control.TextArea;

// dock is idle and no ship is berthing at it
// or there is a ship is returning
public class IdleState implements DockState {

  static Dock dock;
  static Ship ship;

  public IdleState(Dock dock, TextArea dockStatus) {
    IdleState.dock = dock;
    String str = dock.getDOCK_NAME() + " is idle and peaceful.";
    dockStatus.setText(str);
    System.out.println(str);
  }

  public IdleState(Dock dock, Ship ship, TextArea dockStatus) {
    IdleState.dock = dock;
    IdleState.ship = ship;
    String str = dock.getDOCK_NAME() + " is waiting " + ship.getSHIP_NAME() + "'s returning";
    dockStatus.setText(str);
    System.out.println(str);
    System.out.println();
  }

  @Override
  public boolean addShip(Ship ship, TextArea dockStatus) {
    if (!dock.isAnyShipReturning()) {
      String str = "A ship name " + ship.getSHIP_NAME() + " is add to the " + dock.getDOCK_NAME() + ".";
      dockStatus.setText(str);
      System.out.println(str);
      ship.setState(new BerthingState(ship, dockStatus));
      dock.setState(new BerthingState(dock, dockStatus));
      return true;
    } else {
      String str = "Cannot add new ship because the " + IdleState.ship.getSHIP_NAME()
          + " is returning to the " + dock.getDOCK_NAME();
      dockStatus.setText(str);
      System.out.println(str);
    }
    return false;
  }

  @Override
  public String toString() {
    return "Idle State ";
  }

  @Override
  public boolean makeShippingWork(TextArea dockStatus) {
    if (!dock.isAnyShipReturning()) {
      String str = dock.getIDLE_ERROR_MSG();
        dockStatus.setText(str);
        System.out.println(str);
      return false;
    }
    ship.setState(new BerthingState(ship, dockStatus));
    dock.setState(new BerthingState(dock, dockStatus));
    return true;
  }
}