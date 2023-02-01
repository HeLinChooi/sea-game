package application.state;

import javafx.scene.control.TextArea;

public class CargoLoadingState implements DockState { // the ship is loading cargo

    static Dock dock;
    static Ship ship;

    public CargoLoadingState(Dock dock, TextArea dockStatus) {
        CargoLoadingState.dock = dock;
        String str = dock.getDOCK_NAME() + " is busy and bustling.";
        dockStatus.setText(str);
        System.out.println(str);
    }

    public CargoLoadingState(Ship ship, TextArea dockStatus) {
        CargoLoadingState.ship = ship;
        String str = CargoLoadingState.ship.getSHIP_NAME() + " is loading cargo at the dock.";
        dockStatus.setText(str);
        System.out.println(str);
    }

    @Override
    public boolean makeShippingWork(TextArea dockStatus) {
        ship.setState(new ShippingCargoState(ship, dockStatus));
        dock.setState(new ShippingCargoState(dock, dockStatus));
        return true;
    }

    @Override
    public String toString() {
        return "Cargo Loading State ";
    }

    @Override
    public boolean addShip(Ship ship, TextArea dockStatus) {
        System.out.println(dock.getCARGO_LOADING_ERROR_MSG(CargoLoadingState.ship.getSHIP_NAME()));
        return false;
    }

}
