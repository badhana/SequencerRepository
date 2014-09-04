package com.dowjones.data.source.sequencer.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class DynamoDBConnection {
		  private static AmazonDynamoDBClient connection;
		  public static AmazonDynamoDBClient getConnectionClient(){
			   if(connection==null){
			   AWSCredentials awsCredentials = new BasicAWSCredentials("...","...");
			   connection = new AmazonDynamoDBClient(awsCredentials);
			   connection.setEndpoint("http://localhost:8000");
			   }
			   return connection;
		   }
}