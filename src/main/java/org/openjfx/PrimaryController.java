package org.openjfx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PrimaryController implements ControllerEventListener{

    private Elevator elevator = new Elevator();

    private static MQTTConnection mqtt;
    static Logging Logger = new Logging();

    @FXML
    private TextField username, error;
    @FXML
    private PasswordField password;

    @FXML
    private ImageView level1;

    @FXML
    private ImageView level2;

    @FXML
    private ImageView level3;

    @FXML
    private ImageView level4;

    @FXML
    private Text flooridc;

    @FXML
    private Text outidc1, outidc2, outidc3, outidc4;

    Image doorOpenImage = new Image(new FileInputStream("src/main/resources/img/open.png"));
    Image doorClosedImage = new Image(new FileInputStream("src/main/resources/img/closed.png"));


    public PrimaryController() throws FileNotFoundException {
        mqtt = new MQTTConnection();
        elevator.setListener(this);
        mqtt.SubToMqtt(elevator);
        mqtt.FakeData("open", 2);
    }

//    public static void test() {
//        System.out.println("TEST!");
//    }

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
        RequestJSON json = new RequestJSON();
        json.setFloorSelection(1);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Go Floor 1!");
//        mqtt.FakeData("open", 1);
    }

    @FXML
    void send2() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setFloorSelection(2);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Go Floor 2!");
//        mqtt.FakeData("open", 2);
    }

    @FXML
    void send3() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setFloorSelection(3);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Go to Floor 3!");
//        mqtt.FakeData("open", 3);
    }

    @FXML
    void send4() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setFloorSelection(4);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Go to Floor 4!");
//        mqtt.FakeData("open", 4);
    }

    @FXML
    void closeDoor() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setDoorButton("close");
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Closing Door!");
    }

    @FXML
    void openDoor() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setDoorButton("open");
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Opening Door!");
    }

    @FXML
    void Stop() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setEmergencyStop(true);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("STOP!");
    }
    //-----------------------------------------------------------------//

    //Control  outside
    //-----------------------------------------------------------------//
    @FXML
    void sendUp1() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setStopButtonUp(1);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Call up 1!");
    }

    @FXML
    void sendUp2() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setStopButtonUp(2);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Call up 2!");
    }

    @FXML
    void sendUp3() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setStopButtonUp(3);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Call up 3!");
    }

    @FXML
    void sendDown4() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setStopButtonDown(4);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Call down 4!");
    }

    @FXML
    void sendDown3() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setStopButtonDown(3);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Call down 3!");
    }

    @FXML
    void sendDown2() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setStopButtonDown(2);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Call down 2!");
    }
    //-----------------------------------------------------------------//

    //ADMIN
    @FXML
    void manualDoorOpenStart() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setManualDoor("open");
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Manual Opening door!");
    }

    @FXML
    void manualDoorCloseStart() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setManualDoor("close");
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Manual Closing door!");
    }

    @FXML
    void manualDoorStop() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setManualDoor("stop");
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Manual door Stopping!");
    }

    @FXML
    void reset() throws IOException {
        RequestJSON json = new RequestJSON();
        json.setReset(true);
        json.setTimestamp(String.valueOf(new Date()));
        mqtt.PublishAction(json);
        Logger.logged("Reset!");
    }


    @FXML
    void exitAdmin() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void viewChanges(String changes, String errorText) {
        switch (changes){
            case "pos1closed":
                System.out.println("At Level 1");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 1");
                        level1.setImage(doorClosedImage);
                        level2.setImage(doorClosedImage);
                        level3.setImage(doorClosedImage);
                        level4.setImage(doorClosedImage);
                        outidc1.setText("F:1");
                        outidc2.setText("F:1");
                        outidc3.setText("F:1");
                        outidc4.setText("F:1");
                        error.setText(errorText);
                    }
                });
                break;
            case "pos2closed":
                System.out.println("At Level 2");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 2");
                        level1.setImage(doorClosedImage);
                        level2.setImage(doorClosedImage);
                        level3.setImage(doorClosedImage);
                        level4.setImage(doorClosedImage);
                        outidc1.setText("F:2");
                        outidc2.setText("F:2");
                        outidc3.setText("F:2");
                        outidc4.setText("F:2");
                        error.setText(errorText);
                    }
                });
                break;
            case "pos3closed":
                System.out.println("At Level 3");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 3");
                        level1.setImage(doorClosedImage);
                        level2.setImage(doorClosedImage);
                        level3.setImage(doorClosedImage);
                        level4.setImage(doorClosedImage);
                        outidc1.setText("F:3");
                        outidc2.setText("F:3");
                        outidc3.setText("F:3");
                        outidc4.setText("F:3");
                        error.setText(errorText);
                    }
                });
                break;
            case "pos4closed":
                System.out.println("At Level 4");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 4");
                        level1.setImage(doorClosedImage);
                        level2.setImage(doorClosedImage);
                        level3.setImage(doorClosedImage);
                        level4.setImage(doorClosedImage);
                        outidc1.setText("F:4");
                        outidc2.setText("F:4");
                        outidc3.setText("F:4");
                        outidc4.setText("F:4");
                        error.setText(errorText);
                    }
                });
                break;
            case "pos1open":
                System.out.println("At Level 1");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 1");
                        level1.setImage(doorOpenImage);
                        level2.setImage(doorClosedImage);
                        level3.setImage(doorClosedImage);
                        level4.setImage(doorClosedImage);
                        outidc1.setText("F:1");
                        outidc2.setText("F:1");
                        outidc3.setText("F:1");
                        outidc4.setText("F:1");
                        error.setText(errorText);
                    }
                });
                break;
            case "pos2open":
                System.out.println("At Level 2");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 2");
                        level1.setImage(doorClosedImage);
                        level2.setImage(doorOpenImage);
                        level3.setImage(doorClosedImage);
                        level4.setImage(doorClosedImage);
                        outidc1.setText("F:2");
                        outidc2.setText("F:2");
                        outidc3.setText("F:2");
                        outidc4.setText("F:2");
                        error.setText(errorText);
                    }
                });
                break;
            case "pos3open":
                System.out.println("At Level 3");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 3");
                        level1.setImage(doorClosedImage);
                        level2.setImage(doorClosedImage);
                        level3.setImage(doorOpenImage);
                        level4.setImage(doorClosedImage);
                        outidc1.setText("F:3");
                        outidc2.setText("F:3");
                        outidc3.setText("F:3");
                        outidc4.setText("F:3");
                        error.setText(errorText);
                    }
                });
                break;
            case "pos4open":
                System.out.println("At Level 4");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        flooridc.setText("At Floor: 4");
                        level1.setImage(doorClosedImage);
                        level2.setImage(doorClosedImage);
                        level3.setImage(doorClosedImage);
                        level4.setImage(doorOpenImage);
                        outidc1.setText("F:4");
                        outidc2.setText("F:4");
                        outidc3.setText("F:4");
                        outidc4.setText("F:4");
                        error.setText(errorText);
                    }
                });
                break;
        }
    }
    //-----------------------------------------------------------------//
}
