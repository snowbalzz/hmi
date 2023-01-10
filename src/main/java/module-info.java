module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}