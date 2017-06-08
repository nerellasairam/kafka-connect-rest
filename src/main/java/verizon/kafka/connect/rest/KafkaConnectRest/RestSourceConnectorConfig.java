package verizon.kafka.connect.rest.KafkaConnectRest;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Width;

import java.util.Map;


public class RestSourceConnectorConfig extends AbstractConfig {

  public static final String TOPIC_CONFIG = "topic";
  private static final String TOPIC_DOC = " topic";
  
  public static final String POLL_INTERVAL_CONFIG = "poll.interval.milliseconds";
  private static final String POLL_INTERVAL_DOC =  "The amount of time in ms to wait between polling. ";
  static final long POLL_INTERVAL_DEFAULT = 60000;
  
  public static final String AUTH_TOKEN_URL_CONFIG = "authentication.token.url";
  private static final String AUTH_TOKEN_URL_DOC = "If needs authentication token, provide the url";
  static final String LISTEN_ADDRESS_DEFAULT = "optional";

  public static final String BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";
  private static final String BOOTSTRAP_SERVERS_DOC = "bootstrap.servers";
  
  public static final String URL_GROUP_1 = "rest.api.list";
  
  public static final String REST_URL1_CONFIG = "rest.url.1";
  private static final String REST_URL1_DOC = "rest.url.1";
  
  public static final String REST_URL1_NODE_PATH_CONFIG = "node.path.1";
  private static final String REST_URL1_NODE_PATH_DOC = "node.path.1";
  
  public static final String REST_URL1_VAR_ELEMENT_CONFIG = "variable.element.1";
  private static final String REST_URL1_VAR_ELEMENT_DOC = "variable.element.1";
  
 // public static final String URL_GROUP_2 = "rest.api.list.2";
  public static final String REST_URL2_CONFIG = "rest.url.2";
  private static final String REST_URL2_DOC = "rest.url.2";
  
  public static final String REST_URL2_NODE_PATH_CONFIG = "node.path.2";
  private static final String REST_URL2_NODE_PATH_DOC = "node.path.2";
  
  public static final String REST_URL2_VAR_ELEMENT_CONFIG = "variable.element.2";
  private static final String REST_URL2_VAR_ELEMENT_DOC = "variable.element.2";
  
 // public static final String URL_GROUP_3 = "rest.api.list.3";
  public static final String REST_URL3_CONFIG = "rest.url.3";
  private static final String REST_URL3_DOC = "rest.url.3";
  
  public static final String REST_URL3_NODE_PATH_CONFIG = "node.path.3";
  private static final String REST_URL3_NODE_PATH_DOC = "node.path.3";
  
  public static final String REST_URL3_VAR_ELEMENT_CONFIG = "variable.element.3";
  private static final String REST_URL3_VAR_ELEMENT_DOC = "variable.element.3";

 // public static final String URL_GROUP_4 = "rest.api.list.4";
  public static final String REST_URL4_CONFIG = "rest.url.4";
  private static final String REST_URL4_DOC = "rest.url.4";
  
  public static final String REST_URL4_NODE_PATH_CONFIG = "node.path.4";
  private static final String REST_URL4_NODE_PATH_DOC = "node.path.4";
  
  public static final String REST_URL4_VAR_ELEMENT_CONFIG = "variable.element.4";
  private static final String REST_URL4_VAR_ELEMENT_DOC = "variable.element.4";
  
 // public static final String URL_GROUP_5 = "rest.api.list.5";
  public static final String REST_URL5_CONFIG = "rest.url.5";
  private static final String REST_URL5_DOC = "rest.url.5";
  
  public static final String REST_URL5_NODE_PATH_CONFIG = "node.path.5";
  private static final String REST_URL5_NODE_PATH_DOC = "node.path.5";
  
  public static final String REST_URL5_VAR_ELEMENT_CONFIG = "variable.element.5";
  private static final String REST_URL5_VAR_ELEMENT_DOC = "variable.element.5";
  
 // public static final String URL_GROUP_6 = "rest.api.list.6";
  public static final String REST_URL6_CONFIG = "rest.url.6";
  private static final String REST_URL6_DOC = "rest.url.6";

  
  public final String topic;
  public final long pollInterval;
  public final String authToken;
  public final String bootstrapServers;
  
  
  
  public final String url1;
  public final String url1NodePath;
  public final String url1VariableElement;
  public final String url2;
  public final String url2NodePath;
  public final String url2VariableElement;
  public final String url3;
  public final String url3NodePath;
  public final String url3VariableElement;
  public final String url4;
  public final String url4NodePath;
  public final String url4VariableElement;
  public final String url5;
  public final String url5NodePath;
  public final String url5VariableElement;
  public final String url6;

  
  public RestSourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
    
    this.topic = this.getString(TOPIC_CONFIG);
    this.pollInterval = this.getLong(POLL_INTERVAL_CONFIG);
    this.authToken = this.getString(AUTH_TOKEN_URL_CONFIG);
    this.bootstrapServers = this.getString(BOOTSTRAP_SERVERS_CONFIG);
   
    
    this.url1 = this.getString(REST_URL1_CONFIG);
    this.url1NodePath = this.getString(REST_URL1_NODE_PATH_CONFIG);
    this.url1VariableElement = this.getString(REST_URL1_VAR_ELEMENT_CONFIG);
    this.url2 = this.getString(REST_URL2_CONFIG);
    this.url2NodePath = this.getString(REST_URL2_NODE_PATH_CONFIG);
    this.url2VariableElement = this.getString(REST_URL2_VAR_ELEMENT_CONFIG);
    this.url3 = this.getString(REST_URL3_CONFIG);
    this.url3NodePath = this.getString(REST_URL3_NODE_PATH_CONFIG);
    this.url3VariableElement = this.getString(REST_URL3_VAR_ELEMENT_CONFIG);
    this.url4 = this.getString(REST_URL4_CONFIG);
    this.url4NodePath = this.getString(REST_URL4_NODE_PATH_CONFIG);
    this.url4VariableElement = this.getString(REST_URL4_VAR_ELEMENT_CONFIG);
    this.url5 = this.getString(REST_URL5_CONFIG);
    this.url5NodePath = this.getString(REST_URL5_NODE_PATH_CONFIG);
    this.url5VariableElement = this.getString(REST_URL5_VAR_ELEMENT_CONFIG);
    this.url6 = this.getString(REST_URL6_CONFIG);
      
  }

  public RestSourceConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        
        .define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_DOC)
        .define(POLL_INTERVAL_CONFIG, Type.LONG, POLL_INTERVAL_DEFAULT, ConfigDef.Range.between(10, Integer.MAX_VALUE), Importance.MEDIUM, POLL_INTERVAL_DOC)
        .define(AUTH_TOKEN_URL_CONFIG, Type.STRING, LISTEN_ADDRESS_DEFAULT, Importance.LOW, AUTH_TOKEN_URL_DOC)
        .define(BOOTSTRAP_SERVERS_CONFIG, Type.STRING, Importance.HIGH, BOOTSTRAP_SERVERS_DOC)
        .define(REST_URL1_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL1_DOC, URL_GROUP_1, 1, Width.MEDIUM, REST_URL1_DOC)
        .define(REST_URL1_NODE_PATH_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL1_NODE_PATH_DOC, URL_GROUP_1, 1, Width.MEDIUM, REST_URL1_NODE_PATH_DOC)
        .define(REST_URL1_VAR_ELEMENT_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL1_VAR_ELEMENT_DOC, URL_GROUP_1, 1, Width.MEDIUM, REST_URL1_VAR_ELEMENT_DOC)
        .define(REST_URL2_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL2_DOC, URL_GROUP_1, 2, Width.MEDIUM, REST_URL2_DOC)
        .define(REST_URL2_NODE_PATH_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL2_NODE_PATH_DOC, URL_GROUP_1, 2, Width.MEDIUM, REST_URL2_NODE_PATH_DOC)
        .define(REST_URL2_VAR_ELEMENT_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL2_VAR_ELEMENT_DOC, URL_GROUP_1,2, Width.MEDIUM, REST_URL2_VAR_ELEMENT_DOC)
        .define(REST_URL3_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL3_DOC, URL_GROUP_1, 3, Width.MEDIUM, REST_URL3_DOC)
        .define(REST_URL3_NODE_PATH_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL3_NODE_PATH_DOC, URL_GROUP_1,3, Width.MEDIUM, REST_URL3_NODE_PATH_DOC)
        .define(REST_URL3_VAR_ELEMENT_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL3_VAR_ELEMENT_DOC, URL_GROUP_1, 3, Width.MEDIUM, REST_URL3_VAR_ELEMENT_DOC)
        .define(REST_URL4_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL4_DOC, URL_GROUP_1, 4, Width.MEDIUM, REST_URL4_DOC)
        .define(REST_URL4_NODE_PATH_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL4_NODE_PATH_DOC, URL_GROUP_1,5, Width.MEDIUM, REST_URL4_NODE_PATH_DOC)
        .define(REST_URL4_VAR_ELEMENT_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL4_VAR_ELEMENT_DOC, URL_GROUP_1, 6, Width.MEDIUM, REST_URL4_VAR_ELEMENT_DOC)
        .define(REST_URL5_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL5_DOC, URL_GROUP_1, 7, Width.MEDIUM, REST_URL5_DOC)
        .define(REST_URL5_NODE_PATH_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL5_NODE_PATH_DOC, URL_GROUP_1,7, Width.MEDIUM, REST_URL5_NODE_PATH_DOC)
        .define(REST_URL5_VAR_ELEMENT_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL5_VAR_ELEMENT_DOC, URL_GROUP_1, 7, Width.MEDIUM, REST_URL5_VAR_ELEMENT_DOC)
        .define(REST_URL6_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL6_DOC, URL_GROUP_1, 8, Width.MEDIUM, REST_URL6_DOC);
  }

  
}
