package org.openjfx;

import java.util.Objects;

public class Elevator implements ElevatorListener {

    private ControllerEventListener controllerEventListener;

    public void ConnectingListener(ControllerEventListener listener) {
        this.controllerEventListener = listener;
    }


    @Override
    public void elevatorChange(String doorStatus, Integer level, String error) {
        if(level != null){
            if(level == 1){
                controllerEventListener.viewChanges("pos1", error);
            }else if(level == 2){
                controllerEventListener.viewChanges("pos2", error);
            }else if(level == 3){
                controllerEventListener.viewChanges("pos3", error);
            }else if(level == 4){
                controllerEventListener.viewChanges("pos4", error);
            }
        }

        if(doorStatus !=null){
            if(Objects.equals(doorStatus, "closed")){
                controllerEventListener.viewChanges("closed", error);
            }
            if(Objects.equals(doorStatus, "open")){
                controllerEventListener.viewChanges("open", error);
            }
            if(Objects.equals(doorStatus, "moving")){
                controllerEventListener.viewChanges("moving", error);
            }
        }
    }
}
