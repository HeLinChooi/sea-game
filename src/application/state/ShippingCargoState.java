package application.state;

import javafx.scene.control.TextArea;

// the dock will turn to IdleState
public class ShippingCargoState implements DockState { // the ship is taking off for shipping cargo

    static Dock dock;
    static Ship ship;

    public ShippingCargoState(Ship ship, TextArea dockStatus) {
        ShippingCargoState.ship = ship;
        String str = ship.getSHIP_NAME() + " is setting sail from the dock.";
        dockStatus.setText(str);
        System.out.println(str);
    }

    public ShippingCargoState(Dock dock, TextArea dockStatus) {
        ShippingCargoState.dock = dock;
    }

    @Override
    public final boolean makeShippingWork(TextArea dockStatus) {
        ship.setState(new ReturningState(ship, dockStatus));
        dock.setState(new ReturningState(dock));
        return true;
    }

    @Override
    public String toString() {
        return "Shipping Cargo State ";
    }

    @Override
    public boolean addShip(Ship ship, TextArea dockStatus) {
        ShippingCargoState.ship.setIsThisShipReturning(Ship.LEAVING);
        System.out.println(ShippingCargoState.ship.getSHIP_NAME() + " is going other dock.");
        System.out.println("A ship name " + ship.getSHIP_NAME() + " is add to the " + dock.getDOCK_NAME() + ".");
        ship.setState(new BerthingState(ship, dockStatus));
        dock.setState(new BerthingState(dock, dockStatus));
        return true;
    }

}
