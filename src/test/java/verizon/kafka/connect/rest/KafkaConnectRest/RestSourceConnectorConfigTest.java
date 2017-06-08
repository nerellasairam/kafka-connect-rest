package verizon.kafka.connect.rest.KafkaConnectRest;

import org.junit.Test;

public class RestSourceConnectorConfigTest {
  @Test
  public void doc() {
    System.out.println(RestSourceConnectorConfig.conf().toRst());
  }
}