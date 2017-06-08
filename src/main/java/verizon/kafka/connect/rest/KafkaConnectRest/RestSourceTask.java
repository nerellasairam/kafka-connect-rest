package verizon.kafka.connect.rest.KafkaConnectRest;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

public class RestSourceTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(RestSourceTask.class);
  
    public  String topic;
    public  long pollInterval;
    public  String authToken;
    public  String bootstrapServers;
    
    public  String url1;
    public  String url1NodePath;
    public  String url1VariableElement;
    public  String url2;
    public  String url2NodePath;
    public  String url2VariableElement;
    public  String url3;
    public  String url3NodePath;
    public  String url3VariableElement;
    public  String url4;
    public  String url4NodePath;
    public  String url4VariableElement;
    public  String url5;
    public  String url5NodePath;
    public  String url5VariableElement;
    public  String url6;
    
	
	static final Map<String, Object> EMPTY = ImmutableMap.of();
  @Override
  public String version() {
    return VersionUtil.getVersion();
  }
  
   RestSourceConnectorConfig config;
   RestClient restclient=new RestClient();
  
   
  @Override
  public void start(Map<String, String> map) {

	  this.config = new RestSourceConnectorConfig(map);
	  topic=this.config.topic;
	  pollInterval=this.config.pollInterval;	  
	  authToken=this.config.authToken;
	  bootstrapServers=this.config.bootstrapServers;
	  
	  
	  url1=this.config.url1;
	  url1NodePath=this.config.url1NodePath;
	  url1VariableElement=this.config.url1VariableElement;
	  
	  url2=this.config.url2;
	  url2NodePath=this.config.url2NodePath;
	  url2VariableElement=this.config.url2VariableElement;
	  
	  url3=this.config.url3;
	  url3NodePath=this.config.url3NodePath;
	  url3VariableElement=this.config.url3VariableElement;
	  
	  url4=this.config.url4;
	  url4NodePath=this.config.url4NodePath;
	  url4VariableElement=this.config.url4VariableElement;
	  
	  url5=this.config.url5;
	  url5NodePath=this.config.url5NodePath;
	  url5VariableElement=this.config.url5VariableElement;
	  
	  url6=this.config.url6;
	
	  
  }

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
	  
	  try {
	  ArrayList<SourceRecord> records = null;
	  
	  log.info("kafkaconnectrest pollInterval", pollInterval);
		  synchronized (this) {			
				this.wait(pollInterval);}
		
	  
	  
		   restclient.callRestServicewithAuthToken( authToken, url1,url1NodePath,url1VariableElement,url2,url2NodePath,url2VariableElement,url3,url3NodePath,url3VariableElement
				  , url4,url4NodePath,url4VariableElement,url5,url5NodePath,url5VariableElement,url6,topic, bootstrapServers );
	
	  
	  return records;
	  
	  }catch (Exception e){
		  
		  log.info("kafkaconnectrest exception", e);
	  }
	return null;
  }

  @Override
  public void stop() {
    //TODO: Do whatever is required to stop your task.
  }
}