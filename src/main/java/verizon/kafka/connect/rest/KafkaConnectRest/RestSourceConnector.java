package verizon.kafka.connect.rest.KafkaConnectRest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestSourceConnector extends SourceConnector {
  private static Logger log = LoggerFactory.getLogger(RestSourceConnector.class);
  
  RestSourceConnectorConfig config;
  private Map<String, String> settings;
  
  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> settings) {
    this.config = new RestSourceConnectorConfig(settings);
    this.settings = settings;

    //TODO: Add things you need to do to setup your connector.
  }

  @Override
  public Class<? extends Task> taskClass() {
    //TODO: Return your task implementation.
    return RestSourceTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int i) {
	 
	  if (i > 1) {
	      log.warn("This task only supports one instance of the connector.");
	    }
	  
	  return Arrays.asList(this.settings);
  }

  @Override
  public void stop() {
    //TODO: Do things that are necessary to stop your connector.
  }

  @Override
  public ConfigDef config() {
    return RestSourceConnectorConfig.conf();
  }
}
