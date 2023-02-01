package application.state;

import java.util.Random;

// State for ship deciding whether returning to the dock or leaving
// if ship is returning to the dock, the dock need reserve an IdleState for it
public class ReturningState implements DockState {

    Dock dock;
    static Ship ship;
    Random rand = new Random();

    public ReturningState(Ship ship) {
        ReturningState.ship = ship;
    }

    public ReturningState(Dock dock) {
        this.dock = dock;
    }

    @Override
    public final boolean makeShippingWork() {
        System.out.println(ship.getSHIP_NAME() + " is deciding whether returning to the dock...");
        if (rand.nextBoolean()) {
            ship.setIsThisShipReturning(Ship.RETURNING);
            System.out.println(ship.getSHIP_NAME() + " is returning to the dock.");
            dock.setState(new IdleState(dock, ship));
            return true;
        }
        ship.setIsThisShipReturning(Ship.LEAVING);
        dock.setState(new IdleState(dock));
        System.out.println("No ship returning");
        System.out.println(ship.getSHIP_NAME() + " is going some where else.");
        return false;
    }

    @Override
    public String toString() {
        return "Returning State ";
    }

    @Override
    public boolean addShip(Ship Ship) {
        System.out.println("Cannot add new ship due to the " + ship.getSHIP_NAME()
                + " haven't make returning decision to dock yet.");
        return false;
    }

}
