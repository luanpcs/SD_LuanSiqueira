package br.inatel.labs.labmqtt.client;

public class SensorTemperaturaSubscriber {
    public static void main(String[] args) throws MqttException {
		IMqttClient publisher = null;
		
		try {
			String subscriberId = UUID.randomUUID().toString();
			IMqttClient subscriber = new MqttClient(MyConstants.URI_BROKER, subscriberId);
			
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			publisher.connect(options);
			
            subscriber.subscribe(MyConstants.TOPIC_SENSOR, (topic, msg)->
            {
                byte[] payload = msg.getPayload();
                System.out.println("Recebido: " + payload)
                System.out.println("Topico: " + topic)

            })
	
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			publisher.disconnect();
			publisher.close();
		}

	}
}
