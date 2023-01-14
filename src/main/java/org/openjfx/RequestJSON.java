package org.openjfx;

public class RequestJSON {
    private Integer stopButtonDown;
    private Integer stopButtonUp;
    private Integer floorSelection;
    private Boolean emergencyStop;
    private String doorButton;
    private String manualDoor;
    private Boolean reset;
    private String timestamp;

    public Integer getStopButtonDown() {
        return stopButtonDown;
    }

    public Integer getStopButtonUp() {
        return stopButtonUp;
    }

    public Integer getFloorSelection() {
        return floorSelection;
    }

    public Boolean getEmergencyStop() {
        return emergencyStop;
    }

    public String getDoorButton() {
        return doorButton;
    }

    public String getManualDoor() {
        return manualDoor;
    }

    public Boolean getReset() {
        return reset;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setStopButtonDown(Integer stopButtonDown) {
        this.stopButtonDown = stopButtonDown;
    }

    public void setStopButtonUp(Integer stopButtonUp) {
        this.stopButtonUp = stopButtonUp;
    }

    public void setFloorSelection(Integer floorSelection) {
        this.floorSelection = floorSelection;
    }

    public void setEmergencyStop(Boolean emergencyStop) {
        this.emergencyStop = emergencyStop;
    }

    public void setDoorButton(String doorButton) {
        this.doorButton = doorButton;
    }

    public void setManualDoor(String manualDoor) {
        this.manualDoor = manualDoor;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
