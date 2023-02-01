package application.state;

public class CargoLoadingState implements DockState { // the ship is loading cargo

    static Dock dock;
    static Ship ship;

    public CargoLoadingState(Dock dock) {
        CargoLoadingState.dock = dock;
        System.out.println(dock.getDOCK_NAME() + " is busy and bustling.");
    }

    public CargoLoadingState(Ship ship) {
        CargoLoadingState.ship = ship;
        System.out.println(CargoLoadingState.ship.getSHIP_NAME() + " is loading cargo at the dock.");
    }

    @Override
    public boolean makeShippingWork() {
        ship.setState(new ShippingCargoState(ship));
        dock.setState(new ShippingCargoState(dock));
        return true;
    }

    @Override
    public String toString() {
        return "Cargo Loading State ";
    }

    @Override
    public boolean addShip(Ship ship) {
        System.out.println(dock.getCARGO_LOADING_ERROR_MSG(CargoLoadingState.ship.getSHIP_NAME()));
        return false;
    }

}
