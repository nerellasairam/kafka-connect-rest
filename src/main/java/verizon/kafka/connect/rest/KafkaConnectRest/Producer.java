package verizon.kafka.connect.rest.KafkaConnectRest;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {
	
   
         public  void produceMessage(String message, String topic, String bootstrapServers)  {
              
            // System.out.println("produceMessage");
             //Configure the Producer
             Properties configProperties = new Properties();
             configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
             configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
             configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

             org.apache.kafka.clients.producer.Producer producer = new KafkaProducer<String, String>(configProperties);
         
             if(message!=null && message.length()>0) {
                 ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topic, message);
                 producer.send(rec);
                
             }
            
             producer.close();
         }
       

}