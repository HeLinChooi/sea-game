package application.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dock {

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