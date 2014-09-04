package com.amazonws.codesamples;

import java.util.Arrays;
import java.util.HashSet;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBMapperExample {
	
	public static void main(String[] args) {
		
		AWSCredentials awsCredentials = new AWSCredentials() {
			@Override
			public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return "...";
			}
			
			@Override
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return "...";
			}
		};
		
		AmazonDynamoDBClient client = new AmazonDynamoDBClient(awsCredentials); //new AmazonDynamoDBClient(new ProfileCredentialsProvider());
		client.setEndpoint("http://localhost:8000");
		

	DynamoDBMapper mapper = new DynamoDBMapper(client);

	CatalogItem item = new CatalogItem();
	item.setId(102);
	item.setTitle("Book 102 Title");
	item.setISBN("222-2222222222");
	item.setBookAuthors(new HashSet<String>(Arrays.asList("Author 1", "Author 2")));
	item.setSomeProp("Test");

	mapper.save(item);
	}
}
