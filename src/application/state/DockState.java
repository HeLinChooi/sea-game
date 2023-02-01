package application.state;

public interface DockState {

    // initialState of the dock will be IdleState
    public static DockState InitialState(Dock dock) {
        return (DockState) new IdleState(dock);
    }

    public boolean makeShippingWork();

    public boolean addShip(Ship Ship);

}
