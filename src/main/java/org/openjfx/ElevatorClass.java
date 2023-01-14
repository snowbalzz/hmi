package org.openjfx;

public class ElevatorClass {
    private String doorStatus;
    private Integer currentFloor;
    private String errorState;
    private String timestamp;

    private ElevatorListener pop;

//    private static ElevatorClass Answer = new ElevatorClass();
//
//    public static ElevatorClass giveDoc(){
//        return Answer;
//    }

    public void setListener(ElevatorListener listener) {
        this.pop = listener;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
//        this.pop.elevatorChange(this.doorStatus);
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setErrorState(String errorState) {
        this.errorState = errorState;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    public String getErrorState() {
        return errorState;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
