package org.openjfx;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Arrays;
import java.util.Date;

public class MQTTConnection {

    /**
     * Tue 10 Jan
     *
     * - Completed the UI basic;
     * - Completed the Controller basic;
     * - Created Mqtt connection class basic;
     *
     */
    private static MqttClient pClient;
    private static MqttClient sClient;

    private String pClientId = "Subscriber_HMI!";
    private String sClientId = "Publisher_HMI!";

    private String publishTopic = "/22WS-SysArch/H1";
    private String subscribeTopic = "/22WS-SysArch/H1";

    static Logging Logger = new Logging();

    public MQTTConnection()  {
        ServiceConnections();
    }

    /**
     * Resson because I use Try and Cath is because that
     * I dont have to implement "trows Exception"
     * in every function that uses this class
     */
    public void ServiceConnections()  {
        try {
            sClient = new MqttClient("tcp://localhost:1888", sClientId, null);
            pClient = new MqttClient("tcp://localhost:1888", pClientId, null);

            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setUserName("H1");
            mqttConnectOptions.setPassword("DF9".toCharArray());

            sClient.connect(mqttConnectOptions);
            pClient.connect(mqttConnectOptions);
            Logger.logged("Connected to MQTT Subscriber and Publisher");
        } catch (MqttException m){
            Logger.logged(m.toString());
        }
    }

    public void SubToMqtt(){
        try {
            sClient.subscribe(subscribeTopic);
            Logger.logged("Subscribed to:" + subscribeTopic + "");
            sClient.setCallback(new MqttCallback() {

                @Override
                // Called when the client lost the connection to the broker
                public void connectionLost(Throwable cause) {
                    Logger.logged("client lost connection " + cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage m) {
                    Logger.logged("The Subscription from " + topic + ": " + m.toString());
                }

                @Override
                // Called when an outgoing publish is complete
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Logger.logged("delivery complete " + token);
                }
            });
        } catch (MqttException m){
            Logger.logged(m.toString());
        }
    }

    public void PublishExample(String value){
        try{
            Date date = new Date();
            String m = "{FloorRequest:"+value+", Time: "+date+"}";
            MqttMessage message = new MqttMessage();
            message.setPayload(m.getBytes());
            pClient.publish(publishTopic, message);
        } catch (MqttException m){
            Logger.logged(m.toString());
        }

    }

}
