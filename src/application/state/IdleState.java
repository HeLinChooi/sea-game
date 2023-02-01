package application.state;

// dock is idle and no ship is berthing at it
// or there is a ship is returning
public class IdleState implements DockState {

    static Dock dock;
    static Ship ship;

    public IdleState(Dock dock) {
        IdleState.dock = dock;
        System.out.println(dock.getDOCK_NAME() + " is idle and peaceful.");
    }

    public IdleState(Dock dock, Ship ship) {
        IdleState.dock = dock;
        IdleState.ship = ship;
        System.out.println(dock.getDOCK_NAME() + " is waiting " + ship.getSHIP_NAME() + "'s returning");
    }

    @Override
    public boolean addShip(Ship ship) {
        if (!dock.isAnyShipReturning()) {
            System.out.println("A ship name " + ship.getSHIP_NAME() + " is add to the " + dock.getDOCK_NAME() + ".");
            ship.setState(new BerthingState(ship));
            dock.setState(new BerthingState(dock));
            return true;
        } else {
            System.out.println("Cannot add new ship because the " + IdleState.ship.getSHIP_NAME()
                    + " is returning to the " + dock.getDOCK_NAME());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Idle State ";
    }

    @Override
    public boolean makeShippingWork() {
        if (!dock.isAnyShipReturning()) {
            System.out.println(dock.getIDLE_ERROR_MSG());
            return false;
        }
        ship.setState(new BerthingState(ship));
        dock.setState(new BerthingState(dock));
        return true;
    }
}