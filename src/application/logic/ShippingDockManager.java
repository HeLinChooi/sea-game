/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author CWH
 */
interface DockState {

    // initialState of the dock will be IdleState
    public static DockState InitialState(Dock dock) {
        return (DockState) new IdleState(dock);
    }

    public boolean makeShippingWork();

    public boolean addShip(Ship Ship);

}

// dock is idle and no ship is berthing at it
// or there is a ship is returning
class IdleState implements DockState {

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

class BerthingState implements DockState { // dock is occupied by a berthing ship

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

class CargoLoadingState implements DockState { // the ship is loading cargo

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

// the dock will turn to IdleState
class ShippingCargoState implements DockState { // the ship is taking off for shipping cargo

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

// State for ship deciding whether returning to the dock or leaving
// if ship is returning to the dock, the dock need reserve an IdleState for it
class ReturningState implements DockState {

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
        System.out.println(ship.getSHIP_NAME() + " is deciding whether returning to the dock.");
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

class Dock {

    private DockState objState;
    private final String DOCK_NAME;
    private List<Ship> shipsList = new ArrayList<>();
    private final String IDLE_ERROR_MSG;
    private final String ERROR_MSG = "Cannot add new ship due to the ";
    private final String BERTHING_ERROR_MSG;
    private final String CARGO_LOADING_ERROR_MSG;

    public void setState(DockState newState) {
        objState = newState;
    }

    public boolean isAnyShipReturning() {
        dropLeavingShip();
        return shipsList.stream().anyMatch((ship) -> (ship.isThisShipReturning() == Ship.RETURNING));
    }

    public void dropLeavingShip() {
        setShipsList(shipsList.stream().filter(ship -> ship.isThisShipReturning() != Ship.LEAVING)
                .collect(Collectors.toList()));
    }

    public Dock(String dockName) {
        this.DOCK_NAME = dockName;
        objState = DockState.InitialState(this);
        IDLE_ERROR_MSG = "There is no ship at the " + dockName + " dock right now. \n"
                + "Please try again after adding a ship. \n";
        BERTHING_ERROR_MSG = " is berthing at the " + dockName + " \n";
        CARGO_LOADING_ERROR_MSG = " is loading cargo at the " + dockName + " \n";
    }

    public String getDOCK_NAME() {
        return DOCK_NAME;
    }

    public void setShipsList(List<Ship> shipsList) {
        this.shipsList = shipsList;
    }

    public boolean addShip(Ship ship) {
        if (objState.addShip(ship)) {
            shipsList.add(ship);
            return true;
        }
        return false;
    }

    public boolean makeShippingWork() {
        return objState.makeShippingWork();
    }

    public String getERROR_MSG() {
        return ERROR_MSG;
    }

    public String getIDLE_ERROR_MSG() {
        return IDLE_ERROR_MSG;
    }

    public String getBERTHING_ERROR_MSG(String shipName) {
        return this.getERROR_MSG() + shipName + BERTHING_ERROR_MSG;
    }

    public String getCARGO_LOADING_ERROR_MSG(String shipName) {
        return this.getERROR_MSG() + shipName + CARGO_LOADING_ERROR_MSG;
    }

    @Override
    public String toString() {
        dropLeavingShip();
        String tempStr = "\n";
        tempStr += "---------------- " + this.getDOCK_NAME() + " Dock state report ----------------\n";
        tempStr += "Dock current state: " + objState.toString() + " \n";
        tempStr += "Ship List under management: \n";
        for (int i = 0; i < shipsList.size(); i++) {
            tempStr += i + 1 + ". " + shipsList.get(i).toString();
        }
        return tempStr;
    }
}

class Ship {

    private DockState objState;
    private final String SHIP_NAME;
    public static final int RETURNING = 1;
    public static final int NEUTRAL = 0;
    public static final int LEAVING = -1;
    private int isThisShipReturning;

    public Ship(String shipName) {
        this.SHIP_NAME = shipName;
        isThisShipReturning = NEUTRAL;
    }

    public void setState(DockState newState) {
        objState = newState;
    }

    public int isThisShipReturning() {
        return isThisShipReturning;
    }

    public void setIsThisShipReturning(int isThisShipReturning) {
        switch (isThisShipReturning) {
            case RETURNING:
                this.isThisShipReturning = RETURNING;
                break;
            case NEUTRAL:
                this.isThisShipReturning = NEUTRAL;
                break;
            case LEAVING:
                this.isThisShipReturning = LEAVING;
                break;
        }
    }

    public String getSHIP_NAME() {
        return SHIP_NAME;
    }

    @Override
    public String toString() {
        return this.getSHIP_NAME() + " --- current state: " + this.objState.toString() + " \n";
    }
}

// A small shipping dock only can manage one ship.
// But the dock can have new ship if the old ship decide to leave.
// Or adding a new ship when the old ship is shipping cargo.
public class ShippingDockManager {

    public static void main(String[] args) {
        Dock tranquilHarbor = new Dock("Tranquil Harbor");
        List<String> shipNames = new ArrayList<>(Arrays.asList("Serenity Voyager", "Tranquility Explorer", "Calm Seas",
                "Peaceful Journey", "Relaxation Voyager", "Serene Voyager", "Gentle Waves", "Ocean Oasis",
                "Paradise Explorer", "Island Retreat", "Coastal Breeze", "Lazy Days", "Sunny Seas", "Paradise Cruiser",
                "Relaxing Oceans", "Ocean Bliss", "Tropical Retreat", "Peaceful Waters", "Relax & Sail",
                "Island Escape", "Beachcomber", "Ocean Harmony", "Sea of Tranquility", "Restful Voyage",
                "Serenity Explorer", "Peaceful Explorer", "Tranquil Voyager", "Calming Seas", "Relaxing Waters",
                "Ocean Sanctuary"));
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int choiceNum = -1;

        OUTER: while (choiceNum != 0) {
            System.out
                    .print("Enter 1 to display, 2 to add new ship to the dock, 3 to make shipping working 0 to exit: ");
            try {
                choiceNum = Integer.parseInt(scanner.next());
                switch (choiceNum) {
                    case 0:
                        break OUTER;
                    case 1:
                        System.out.println(tranquilHarbor.toString());
                        break;
                    case 2:
                        System.out.println("");
                        int shipNameIndex = rand.nextInt(shipNames.size());
                        tranquilHarbor.addShip(new Ship(shipNames.get(shipNameIndex)));
                        shipNames.remove(shipNameIndex);
                        System.out.println(tranquilHarbor.toString());
                        break;
                    case 3:
                        System.out.println("");
                        tranquilHarbor.makeShippingWork();
                        System.out.println(tranquilHarbor.toString());
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }

}
