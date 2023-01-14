package org.openjfx;

import java.util.Objects;

public class Elevator implements ElevatorListener {

    private ControllerEventListener controllerEventListener;
    private static Elevator elevator = new Elevator();

    public Elevator() {
//        ElevatorClass.giveDoc().setListener(this);
    }

    public static Elevator getElevator() {
        return elevator;
    }

    public void setListener(ControllerEventListener listener) {
        this.controllerEventListener = listener;
    }


    @Override
    public void elevatorChange(String doorStatus, Integer level) {
        if(Objects.equals(doorStatus, "closed")){
            if(level == 1){
                controllerEventListener.viewChanges("pos1closed");
            }else if(level == 2){
                controllerEventListener.viewChanges("pos2closed");
            }else if(level == 3){
                controllerEventListener.viewChanges("pos3closed");
            }else if(level == 4){
                controllerEventListener.viewChanges("pos4closed");
            }
        } else if (Objects.equals(doorStatus, "open")){
            if(level == 1){
                controllerEventListener.viewChanges("pos1open");
            }else if(level == 2){
                controllerEventListener.viewChanges("pos2open");
            }else if(level == 3){
                controllerEventListener.viewChanges("pos3open");
            }else if(level == 4){
                controllerEventListener.viewChanges("pos4open");
            }
        }

    }
}
