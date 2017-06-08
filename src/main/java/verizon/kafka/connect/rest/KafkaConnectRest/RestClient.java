package verizon.kafka.connect.rest.KafkaConnectRest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RestClient {

	 
	 static { 
		 HttpsURLConnection.setDefaultHostnameVerifier(new  HostnameVerifier() 
		 		{ public boolean verify(String hostname, SSLSession  session)
		 			{	  if (hostname.equals("192.168.0.3")) return true; return false;
		 			 }
		 		}); 
	 		}
	
	
	public static void main(String[] args) {
		//callRest();
	//	callRestUrlVoid("http://www.mocky.io/v2/592c0f1a10000062153898d2");	
	
		
//		System.out.println(getAuthToken("https://192.168.0.3:5000/v3/auth/tokens"));
	}

	
public String callRestServicewithAuthToken( String authToken,String url1,String url1NodePath,String url1VariableElement,String url2, String url2NodePath, String url2VariableElement ,
		 String url3, String url3NodePath, String url3VariableElement ,  String url4, String url4NodePath, String url4VariableElement, String url5, String url5NodePath, 
		 String url5VariableElement, String url6, String topic, String bootstrapServers ){
	//System.out.println("inside callRestServicewithAuthToken ");
	String authenticationToken=null;
	
	if(authToken!=null && authToken.length()>9){
	 authenticationToken=getAuthToken(authToken);
	}
	
	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	WebTarget webTarget = client.target(url1);
	Invocation.Builder invocationBuilder;
	
	if (authenticationToken!=null){
     invocationBuilder =  webTarget.request(
			MediaType.APPLICATION_JSON).header("X-Auth-Token", authenticationToken);
	}else {
		 invocationBuilder =  webTarget.request(
					MediaType.APPLICATION_JSON);
	}
	Response response = invocationBuilder.get();
	if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
		}
	Producer producer=new Producer();
	
	
	try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root1 = mapper.readTree(InputSteramtoString(response.readEntity(InputStream.class)));
							
				if (url1NodePath==null || url1NodePath.isEmpty() ||url1NodePath.length()<2 ) 
				{					
				producer.produceMessage(root1.toString(),topic,bootstrapServers);	
				return null;
				}
		
				JsonNode nodes1 = root1.at(url1NodePath);
				Map<String, String> url1VariableElementMap =parseStringtoMap(url1VariableElement);
				for (JsonNode node1 : nodes1) {
					
					Map<String, String> url1VariableElementMapParsedElements = parseElementfromJsonNode(node1,url1VariableElementMap);			    					
					String mofifiedurl2 = replaceStringMap(url2,url1VariableElementMapParsedElements);
					
					if(url2NodePath==null || url2NodePath.isEmpty() ||url2NodePath.length()<2 ){				
						producer.produceMessage(mofifiedurl2,topic,bootstrapServers);	
						continue;
					}
										
					JsonNode root2 = makeanothercall(mofifiedurl2,authenticationToken );
					JsonNode nodes2 = root2.at(url2NodePath);	
					Map<String, String> url2VariableElementMap =parseStringtoMap(url2VariableElement);
							for (JsonNode node2 : nodes2) {
	
								Map<String, String> url2VariableElementMapParsedElements = parseElementfromJsonNode(node2,url2VariableElementMap);		
								String mofifiedurl3 = replaceStringMap( replaceStringMap(url3,url1VariableElementMapParsedElements),url2VariableElementMapParsedElements);
							
								if(url3NodePath==null || url3NodePath.isEmpty() ||url3NodePath.length()<2 ){
						producer.produceMessage(makeanothercall(mofifiedurl3,authenticationToken ).toString(),topic,bootstrapServers);	
								continue;
								}
								
								JsonNode root3 = makeanothercall(mofifiedurl3,authenticationToken );
								JsonNode nodes3 = root3.path(url3NodePath);	
								Map<String, String> url3VariableElementMap =parseStringtoMap(url3VariableElement);
								for (JsonNode node3 : nodes3) {
											
									Map<String, String> url3VariableElementMapParsedElements = parseElementfromJsonNode(node3,url3VariableElementMap);	
									String mofifiedurl4 = replaceStringMap( replaceStringMap(replaceStringMap(url4,url3VariableElementMapParsedElements),url2VariableElementMapParsedElements),url1VariableElementMapParsedElements);
																					
											if(url4NodePath==null || url4NodePath.isEmpty() ||url4NodePath.length()<2 ){
												
											producer.produceMessage(makeanothercall(mofifiedurl4,authenticationToken ).toString(),topic,bootstrapServers);	
											continue;
											}
											
											JsonNode root4 = makeanothercall(mofifiedurl4,authenticationToken );
											JsonNode nodes4 = root4.path(url4NodePath);	
											Map<String, String> url4VariableElementMap =parseStringtoMap(url4VariableElement);
													for (JsonNode node4 : nodes4) {													
														Map<String, String> url4VariableElementMapParsedElements = parseElementfromJsonNode(node4,url4VariableElementMap);	
														String mofifiedurl5 = replaceStringMap( replaceStringMap(replaceStringMap(replaceStringMap(url5,url4VariableElementMapParsedElements),url3VariableElementMapParsedElements),url2VariableElementMapParsedElements),url1VariableElementMapParsedElements);
													
														if(url5NodePath==null || url5NodePath.isEmpty() ||url5NodePath.length()<2 ){
															
														producer.produceMessage(makeanothercall(mofifiedurl5,authenticationToken ).toString(),topic,bootstrapServers);	
														continue;
														}
																																							
														JsonNode root5 = makeanothercall(mofifiedurl5,authenticationToken );
														JsonNode nodes5 = root5.path(url5NodePath);
														Map<String, String> url5VariableElementMap =parseStringtoMap(url5VariableElement);
														
																for (JsonNode node5 : nodes5) {																	
																	Map<String, String> url5VariableElementMapParsedElements = parseElementfromJsonNode(node5,url5VariableElementMap);	
																	String mofifiedurl6 = replaceStringMap( replaceStringMap(replaceStringMap(replaceStringMap(replaceStringMap(url6,url5VariableElementMapParsedElements),url4VariableElementMapParsedElements),url3VariableElementMapParsedElements),url2VariableElementMapParsedElements),url1VariableElementMapParsedElements);																
																	producer.produceMessage(makeanothercall(mofifiedurl6,authenticationToken ).toString(),topic,bootstrapServers);	
																																
																}		
														
											
													}									
														
							
										}
																
					
							}
				
			
				}
		
	} catch (JsonParseException e) {
		
		e.printStackTrace();
	} catch (JsonMappingException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
     
return null;
}

private Map<String, String>  parseStringtoMap(String stringtoParse){

	Map<String, String> map = new HashMap<String, String>();
	String[] parts = stringtoParse.split(",");
	
	   for(String part:parts){	   
		   String[] newpart = part.split(":"); 
		   map.put(newpart[0], newpart[1]);
	   }
			return map;
}


private String replaceStringMap(String toReplace,Map<String, String> mapValues ){
	
	String result="";
	for (Map.Entry<String, String> mapValue : mapValues.entrySet()) {						    
	    result = toReplace.replace(mapValue.getKey(), mapValue.getValue());
	    toReplace = result;					   
	}
	return result;
}

private Map<String, String> parseElementfromJsonNode(JsonNode node,  Map<String, String> mapValues ){
	
				Map<String, String> parsedValueMap = new HashMap<String, String>();
				
			for (Map.Entry<String, String> url1MapValues : mapValues.entrySet()) {
				if(!node.path(url1MapValues.getValue()).asText().isEmpty())
				parsedValueMap.put(url1MapValues.getKey(), node.path(url1MapValues.getValue()).asText());
			}
			return parsedValueMap;
}

private JsonNode makeanothercall(String replace, String authenticationToken) {
	

	try {

	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	WebTarget webTarget = client.target(replace);
	Invocation.Builder invocationBuilder;
	
	if(authenticationToken==null){
     invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);}
	else{
		invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON).header("X-Auth-Token", authenticationToken);}
	
	Response response = invocationBuilder.get();
 
	ObjectMapper mapper = new ObjectMapper();
	JsonNode root = mapper.readTree(InputSteramtoString(response.readEntity(InputStream.class)));
	
	return root;	
		
		
	} catch (JsonParseException e) {		
		e.printStackTrace();
	} catch (JsonMappingException e) {		
		e.printStackTrace();
	} catch (IOException e) {		
		e.printStackTrace();
	} catch (Exception e) {		
		e.printStackTrace();
	}
	return null;
     
	
}


private static String getAuthToken(String authToken) {
	
Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	
	WebTarget webTarget = client.target(authToken);

	Invocation.Builder invocationBuilder = webTarget
			.request(MediaType.APPLICATION_JSON);
		
	String body="{\r\n    \"auth\": {\r\n        \"identity\": {\r\n            \"methods\": [\r\n                \"password\"\r\n            ],\r\n            \"password\": {\r\n                \"user\": {\r\n                    \"id\": \"3abd1c4b579e4a019afa0dc6e64c4a62\",\r\n                    \"password\": \"sdnadminos@123\"\r\n                }\r\n            }\r\n        },\r\n        \"scope\": {\r\n            \"project\": {\r\n                \"id\": \"1f9f615c8d784dbba027f6d73262064a\"\r\n            }\r\n        }\r\n    }\r\n}";

	Response response = invocationBuilder.post(Entity.json(body));
	
	String token =response.getHeaderString("X-Subject-Token");
	System.out.println(token);
	
	
	return token;
}



public static String callRest(){
	
	BufferedReader reader = new BufferedReader(new  InputStreamReader(callRestUrl("http://www.mocky.io/v2/592c0f1a10000062153898d2"), StandardCharsets.UTF_8));		  
	 StringBuilder result = new StringBuilder();		  
	 String line;
	 				  
	  try {
	  
	  while ((line = reader.readLine()) != null)
	  		{
		  	result.append(line);
	  			}
	  
	  			System.out.println(result);
	 
	  		} catch (IOException e) {
	  			System.out.println("Exception is " +e);  
	  								}
	return result.toString();
}

public static InputStream callRestUrl(String urltoCall) {

	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	
	WebTarget webTarget = client.target(urltoCall);

	Invocation.Builder invocationBuilder = webTarget
			.request(MediaType.APPLICATION_JSON);
	
	Response response = invocationBuilder.get();

	
	
	return response.readEntity(InputStream.class);

}

public static void callRestUrlVoid(String urltoCall) {

	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	
	WebTarget webTarget = client.target(urltoCall);

	Invocation.Builder invocationBuilder = webTarget
			.request(MediaType.APPLICATION_JSON);
	
	Response response = invocationBuilder.get();

	
	System.out.println(response.getHeaderString("Content-Type"));
	
	System.out.println(response.getHeaders().toString());
	
	ObjectMapper mapper = new ObjectMapper();
	try {
	 
		
		JsonNode root = mapper.readTree(InputSteramtoString(response.readEntity(InputStream.class)));
	
		
		JsonNode servers = root.path("servers");
		
		for (JsonNode node : servers) {
			String type = node.path("status").asText();
			String ref = node.path("hostId").asText();
			System.out.println("type : " + type);
			System.out.println("ref : " + ref);

		}
		
	} catch (JsonParseException e) {
		 
		e.printStackTrace();
	} catch (JsonMappingException e) {
	 
		e.printStackTrace();
	} catch (IOException e) {
		 
		e.printStackTrace();
	}
     
		
	

}


public static String InputSteramtoString(InputStream stream){
	
	BufferedReader reader = new BufferedReader(new  InputStreamReader(stream, StandardCharsets.UTF_8));		  
	 StringBuilder result = new StringBuilder();		  
	 String line;
	 				  
	  try {
	  
	  while ((line = reader.readLine()) != null)
	  		{
		  	result.append(line);
	  			}
	  
	  			System.out.println(result);
	 
	  		} catch (IOException e) {
	  			System.out.println("Exception is " +e);  
	  								}
	return result.toString();
}

}