package application.state;

// the dock will turn to IdleState
public class ShippingCargoState implements DockState { // the ship is taking off for shipping cargo

    static Dock dock;
    static Ship ship;

    public ShippingCargoState(Ship ship) {
        ShippingCargoState.ship = ship;
        System.out.println(ship.getSHIP_NAME() + " is setting sail from the dock.");
    }

    public ShippingCargoState(Dock dock) {
        ShippingCargoState.dock = dock;
    }

    @Override
    public final boolean makeShippingWork() {
        ship.setState(new ReturningState(ship));
        dock.setState(new ReturningState(dock));
        return true;
    }

    @Override
    public String toString() {
        return "Shipping Cargo State ";
    }

    @Override
    public boolean addShip(Ship ship) {
        ShippingCargoState.ship.setIsThisShipReturning(Ship.LEAVING);
        System.out.println(ShippingCargoState.ship.getSHIP_NAME() + " is going other dock.");
        System.out.println("A ship name " + ship.getSHIP_NAME() + " is add to the " + dock.getDOCK_NAME() + ".");
        ship.setState(new BerthingState(ship));
        dock.setState(new BerthingState(dock));
        return true;
    }

}
