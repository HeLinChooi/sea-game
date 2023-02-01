package application.state;

import javafx.scene.control.TextArea;

public interface DockState {

    // initialState of the dock will be IdleState
    public static DockState InitialState(Dock dock, TextArea dockStatus) {
        return (DockState) new IdleState(dock, dockStatus);
    }

    public boolean makeShippingWork(TextArea dockStatus);

    public boolean addShip(Ship Ship, TextArea dockStatus);

}
