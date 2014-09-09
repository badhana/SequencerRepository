package com.dowjones.data.source.sequencer.dao.impl;

import java.util.ArrayList;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.TableStatus;
import com.dowjones.data.source.sequencer.dao.TableCreator;

public class TableCreatorImpl implements TableCreator {
	
	private AmazonDynamoDBClient client;
	public TableCreatorImpl(AmazonDynamoDBClient client) {
		this.client=client;
	}

	 public void createTable(String tableName, long readCapacityUnits, long writeCapacityUnits,
	            String hashKeyName, String hashKeyType) {
	        createTable(tableName, readCapacityUnits, writeCapacityUnits, hashKeyName,  hashKeyType, null, null);    
	   }
	 
	  private void createTable(String tableName, long readCapacityUnits, long writeCapacityUnits,
	            String hashKeyName, String hashKeyType, String rangeKeyName, String rangeKeyType) {
	        
	        try {
	            System.out.println("Creating table " + tableName);
	    		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
	    		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

	    		keySchema.add(new KeySchemaElement().withAttributeName(
	    				hashKeyName).withKeyType(KeyType.HASH));
	       		attributeDefinitions.add(new AttributeDefinition().withAttributeName(
	       				hashKeyName).withAttributeType(hashKeyType));
	 
	            if (rangeKeyName != null){
	           		keySchema.add(new KeySchemaElement().withAttributeName(
	        				rangeKeyName).withKeyType(KeyType.RANGE));
	        		attributeDefinitions.add(new AttributeDefinition().withAttributeName(
	        				rangeKeyName).withAttributeType(rangeKeyType));
	            }
	     		        	            
	            // Provide initial provisioned throughput values as Java long data types
	            ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput()
	                .withReadCapacityUnits(readCapacityUnits)
	                .withWriteCapacityUnits(writeCapacityUnits);
	            
	            CreateTableRequest request = new CreateTableRequest()
	                .withTableName(tableName)
	                .withKeySchema(keySchema)
	                .withProvisionedThroughput(provisionedthroughput);
	            
	            request.setAttributeDefinitions(attributeDefinitions);

	            client.createTable(request);
	            
	        } catch (AmazonServiceException ase) {
	            System.err.println("Failed to create table " + tableName + " " + ase);
	        }
	    }
	  
	 public boolean isTableAvailable(String tableName) {
	        
		 	System.out.println("Waiting for " + tableName + " to become ACTIVE...");

	        long startTime = System.currentTimeMillis();
	        long endTime = startTime + (10 * 60 * 1000);
	        while (System.currentTimeMillis() < endTime) {
	            DescribeTableRequest request = new DescribeTableRequest()
	                    .withTableName(tableName);
	            TableDescription tableDescription = client.describeTable(
	                    request).getTable();
	            String tableStatus = tableDescription.getTableStatus();
	            System.out.println("  - current state: " + tableStatus);
	            if (tableStatus.equals(TableStatus.ACTIVE.toString()))
	                return true;
	            try { Thread.sleep(1000 * 20); } catch (Exception e) { }
	        }
	        throw new RuntimeException("Table " + tableName + " never went active");
	    }
	 
	 public boolean deleteTable(String tableName){
	        try {
	            DeleteTableRequest request = new DeleteTableRequest()
	                .withTableName(tableName);
	            
	            client.deleteTable(request);
	            return true;
	               
	        } catch (AmazonServiceException ase) {
	            System.err.println("Failed to delete table " + tableName + " " + ase);
	            return false;
	        }

	    }
	 
	  public boolean isTableDeleted(String tableName) {
	        System.out.println("Waiting for " + tableName + " while status DELETING...");

	        long startTime = System.currentTimeMillis();
	        long endTime = startTime + (10 * 60 * 1000);
	        while (System.currentTimeMillis() < endTime) {
	            try {
	                DescribeTableRequest request = new DescribeTableRequest().withTableName(tableName);
	                TableDescription tableDescription = client.describeTable(request).getTable();
	                String tableStatus = tableDescription.getTableStatus();
	                System.out.println("  - current state: " + tableStatus);
	                if (tableStatus.equals(TableStatus.ACTIVE.toString())) return true;
	            } catch (ResourceNotFoundException e) {
	                System.out.println("Table " + tableName + " is not found. It was deleted.");
	                return true;
	            }
	            try {Thread.sleep(1000 * 20);} catch (Exception e) {}
	        }
	        throw new RuntimeException("Table " + tableName + " was never deleted");
	    }
}
