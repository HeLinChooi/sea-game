package application.state;

public class BerthingState implements DockState { // dock is occupied by a berthing ship

    static Dock dock;
    static Ship ship;

    public BerthingState(Dock dock) {
        BerthingState.dock = dock;
        System.out.println(dock.getDOCK_NAME() + " is occupied and chilling.");
    }

    public BerthingState(Ship ship) {
        BerthingState.ship = ship;
        if (ship.isThisShipReturning() == Ship.RETURNING) {
            ship.setIsThisShipReturning(Ship.NEUTRAL);
        }
        System.out.println(ship.getSHIP_NAME() + " is berthing at the dock.");
    }

    @Override
    public boolean addShip(Ship ship) {
        System.out.println(dock.getBERTHING_ERROR_MSG(BerthingState.ship.getSHIP_NAME()));
        return false;
    }

    @Override
    public boolean makeShippingWork() {
        ship.setState(new CargoLoadingState(ship));
        dock.setState(new CargoLoadingState(dock));
        return true;
    }

    @Override
    public String toString() {
        return "Berthing State ";
    }

}
