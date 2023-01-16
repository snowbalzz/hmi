package org.openjfx;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.paho.client.mqttv3.*;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    private ElevatorClass elevatorClass;

    private static MqttClient pClient;
    private static MqttClient sClient;

    private String pClientId = "Subscriber_HMI!";
    private String sClientId = "Publisher_HMI!";

    private String publishTopic = "/22WS-SysArch/H1";
    private String subscribeTopic = "/22WS-SysArch/C1";

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
//            ssh -L 1888:localhost:1883 FHKN.da351ale@ea-pc165.ei.htwg-konstanz.de
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

    public void SubToMqtt(Elevator elevartor){
        try {
            sClient.subscribe(subscribeTopic);
            Logger.logged("Subscribed to:" + subscribeTopic + "");
            sClient.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    Logger.logged("client lost connection " + cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage m) throws JsonProcessingException {
                    ObjectMapper objectMapper = new ObjectMapper();
                    elevatorClass = objectMapper.readValue(m.toString(), ElevatorClass.class);
                    elevartor.elevatorChange(elevatorClass.getDoorStatus(), elevatorClass.getCurrentFloor(), elevatorClass.getErrorState());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Logger.logged("delivery complete " + token);
                }
            });
        } catch (MqttException m){
            Logger.logged(m.toString());
        }
    }

    public void PublishAction(RequestJSON request){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String m = objectMapper.writeValueAsString(request);
            MqttMessage message = new MqttMessage();
            message.setPayload(m.getBytes());
            pClient.publish(publishTopic, message);
            Logger.logged("Published to:" + publishTopic + "");
        } catch (MqttException m){
            Logger.logged(m.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
