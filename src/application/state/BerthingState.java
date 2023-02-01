package application.state;

import javafx.scene.control.TextArea;

public class BerthingState implements DockState { // dock is occupied by a berthing ship

    static Dock dock;
    static Ship ship;

    public BerthingState(Dock dock, TextArea dockStatus) {
        BerthingState.dock = dock;
        String str = dock.getDOCK_NAME() + " is occupied and chilling.";
        dockStatus.setText(str);
        System.out.println(str);
    }

    public BerthingState(Ship ship, TextArea dockStatus) {
        BerthingState.ship = ship;
        if (ship.isThisShipReturning() == Ship.RETURNING) {
            ship.setIsThisShipReturning(Ship.NEUTRAL);
        }
        String str = ship.getSHIP_NAME() + " is berthing at the dock.";
        dockStatus.setText(str);
        System.out.println(str);
    }

    @Override
    public boolean addShip(Ship ship, TextArea dockStatus) {
        String str = dock.getBERTHING_ERROR_MSG(BerthingState.ship.getSHIP_NAME());
        dockStatus.setText(str);
        System.out.println(str);
        return false;
    }

    @Override
    public boolean makeShippingWork(TextArea dockStatus) {
        ship.setState(new CargoLoadingState(ship, dockStatus));
        dock.setState(new CargoLoadingState(dock, dockStatus));
        return true;
    }

    @Override
    public String toString() {
        return "Berthing State ";
    }

}
