package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController {


    static Logging Logger = new Logging();
    static MQTTConnection mqtt = new MQTTConnection();

    public PrimaryController() {
        mqtt.SubToMqtt();
    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    //Navigation part of the Code
    //-----------------------------------------------------------------//
    @FXML
    void cancelLogin() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    void submitLogin() throws IOException {
        checkLoging();
    }

    // Login check
    private void checkLoging() throws IOException {
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            App.setRoot("primary_admin");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            System.out.println("No Input!");
        }
        else {
            System.out.println("Wrong Password!");
        }
    }
    //-----------------------------------------------------------------//

    //Control Panel inside
    //-----------------------------------------------------------------//
    @FXML
    void send1() throws IOException {
        // MQTT CAll here
        mqtt.PublishExample("Floor 1");
        Logger.logged("Go Floor 1!");
    }

    @FXML
    void send2() throws IOException {
        // MQTT CAll here
        mqtt.PublishExample("Floor 2");
        Logger.logged("Go Floor 2!");
    }

    @FXML
    void send3() throws IOException {
        // MQTT CAll here
        mqtt.PublishExample("Floor 3");
        Logger.logged("Go to Floor 3!");
    }

    @FXML
    void send4() throws IOException {
        // MQTT CAll here
        mqtt.PublishExample("Floor 4");
        Logger.logged("Go to Floor 4!");
    }

    @FXML
    void closeDoor() throws IOException {
        // MQTT CAll here
        Logger.logged("Closing Door!");
    }

    @FXML
    void openDoor() throws IOException {
        // MQTT CAll here
        Logger.logged("Opening Door!");
    }

    @FXML
    void Stop() throws IOException {
        // MQTT CAll here
        Logger.logged("STOP!");
    }
    //-----------------------------------------------------------------//

    //Control  outside
    //-----------------------------------------------------------------//
    @FXML
    void sendUp1() throws IOException {
        // MQTT CAll here
        Logger.logged("Call up 1!");
    }

    @FXML
    void sendUp2() throws IOException {
        // MQTT CAll here
        Logger.logged("Call up 2!");
    }

    @FXML
    void sendUp3() throws IOException {
        // MQTT CAll here
        Logger.logged("Call up 3!");
    }

    @FXML
    void sendDown4() throws IOException {
        // MQTT CAll here
        Logger.logged("Call down 4!");
    }

    @FXML
    void sendDown3() throws IOException {
        // MQTT CAll here
        Logger.logged("Call down 3!");
    }

    @FXML
    void sendDown2() throws IOException {
        // MQTT CAll here
        Logger.logged("Call down 3!");
    }
    //-----------------------------------------------------------------//

    //ADMIN
    @FXML
    void manualDoorOpen() throws IOException {
        // MQTT CAll here
        Logger.logged("Manual Opening door!");
    }

    @FXML
    void manualDoorClose() throws IOException {
        // MQTT CAll here
        Logger.logged("Manual Closing door!");
    }

    @FXML
    void manualUp() throws IOException {
        // MQTT CAll here
        Logger.logged("Manual up!");
    }

    @FXML
    void manualDown() throws IOException {
        // MQTT CAll here
        Logger.logged("Manual down!");
    }

    @FXML
    void manualUp2() throws IOException {
        // MQTT CAll here
        Logger.logged("Manual up 2!");
    }

    @FXML
    void manualDown2() throws IOException {
        // MQTT CAll here
        Logger.logged("Manual down 2!");
    }

    @FXML
    void exitAdmin() throws IOException {
        App.setRoot("primary");
    }
    //-----------------------------------------------------------------//
}
