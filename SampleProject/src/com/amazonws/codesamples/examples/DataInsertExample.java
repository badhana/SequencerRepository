package com.amazonws.codesamples.examples;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.dowjones.data.source.sequencer.util.DynamoDBConnection;

public class DataInsertExample {

	public static void main(String[] args) {
		
		AmazonDynamoDBClient client=DynamoDBConnection.getConnectionClient();
		
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("Id", new AttributeValue().withN("105"));
		item.put("AccessionNumber", new AttributeValue().withS("AN1"));
		item.put("RevisionNumber", new AttributeValue().withN("4"));
		item.put("EnrichmentLevel", new AttributeValue().withN("2"));
		item.put("ActionCode", new AttributeValue().withN("0"));

		ReturnValue retVal = ReturnValue.ALL_OLD;

		PutItemRequest putItemRequest = new PutItemRequest()
		    .withTableName("AccessionRevisionItem")
		    .withItem(item)
		   // .withExpected(expected)
		    .withReturnValues(retVal);
		PutItemResult result = client.putItem(putItemRequest);
		System.out.println("Result is "+result);
		 
	}
}
