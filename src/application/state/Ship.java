package application.state;

public class Ship {

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
