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
//            sClient = new MqttClient("tcp://localhost:1888", sClientId, null);
            sClient = new MqttClient("tcp://broker.hivemq.com", sClientId, null);
            pClient = new MqttClient("tcp://broker.hivemq.com", pClientId, null);

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
                // Called when the client lost the connection to the broker
                public void connectionLost(Throwable cause) {
                    Logger.logged("client lost connection " + cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage m) throws JsonProcessingException {
//                    Logger.logged("The Subscription from " + topic + ": " + m.toString());
                    ObjectMapper objectMapper = new ObjectMapper();
                    elevatorClass = objectMapper.readValue(m.toString(), ElevatorClass.class);

                    elevartor.elevatorChange(elevatorClass.getDoorStatus(), elevatorClass.getCurrentFloor(), elevatorClass.getErrorState());

//                    Logger.logged("DatafromC1: " + elevatorClass.getCurrentFloor() + elevatorClass.getDoorStatus() + elevatorClass.getErrorState() + elevatorClass.getTimestamp());
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

    public void PublishAction(RequestJSON request){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String m = objectMapper.writeValueAsString(request);
            MqttMessage message = new MqttMessage();
            message.setPayload(m.getBytes());
            pClient.publish(publishTopic, message);
        } catch (MqttException m){
            Logger.logged(m.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    //{"doorStatus":"open","currentFloor":2,"errorState":"NO Error!","timestamp":"Sat Jan 14 17:38:52 CET 2023"}
        public void FakeData(String state, Integer loc){
        try{
            PublishJson publishJson = new PublishJson();
            publishJson.setCurrentFloor(loc);
            publishJson.setDoorStatus(state);
            publishJson.setErrorState("NO Error!");
            publishJson.setTimestamp(String.valueOf(new Date()));
            ObjectMapper objectMapper = new ObjectMapper();
            String m = objectMapper.writeValueAsString(publishJson);
            MqttMessage message = new MqttMessage();
            System.out.println(m);
            message.setPayload(m.getBytes());
            pClient.publish(publishTopic, message);
        } catch (MqttException m){
            Logger.logged(m.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        }

}
