module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}