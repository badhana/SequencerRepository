package com.amazonws.codesamples;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

public class DBQueryExpressionExample {
	static AmazonDynamoDBClient client;
	private DynamoDBMapper mapper;

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
		
		CatalogItem hashKeyValues = new CatalogItem();

		hashKeyValues.setId(102);
		DynamoDBQueryExpression<CatalogItem> queryExpression = new DynamoDBQueryExpression<CatalogItem>()
		    .withHashKeyValues(hashKeyValues);

		queryExpression.setHashKeyValues(hashKeyValues);

		PaginatedQueryList<CatalogItem> itemList = mapper.query(CatalogItem.class, queryExpression);

		for (int i = 0; i < itemList.size(); i++) {
		    System.out.println(itemList.get(i).getTitle());
		    System.out.println(itemList.get(i).getBookAuthors());
		}
	}
	
/*	private void init() {
		 //  PRODUCT_ID = new Random().nextInt(1000);
		   AWSCredentials credentials = new ClasspathPropertiesFileCredentialsProvider()
		     .getCredentials();
		   client = new AmazonDynamoDBClient(credentials);
		   Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		   client.setRegion(usWest2);
		   mapper = new DynamoDBMapper(client);
	 }*/

	
	/*private static void CreateClient()
	{
	  AmazonDynamoDBConfig config = new AmazonDynamoDBConfig();
	  config.ServiceURL = "http://dynamodb.us-east-1.amazonaws.com";
	  client = new AmazonDynamoDBClient(config);
	}       */
}
