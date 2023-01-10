package org.openjfx;

import org.eclipse.paho.client.mqttv3.*;

public class MQTTConnection {

    private MqttClient client;

    public static MqttAsyncClient myClient;

    public MQTTConnection()  {

        try {
            client = new MqttClient("tcp://ea-pc165.ei.htwg-konstanz.de", MqttClient.generateClientId(), null);

            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setUserName("<your_username>");
            mqttConnectOptions.setPassword("<your_password>".toCharArray());

            //Log Connection
            client.connect(mqttConnectOptions);
        } catch (MqttException e) {

        }
    }


}
