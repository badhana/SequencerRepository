package com.amazonws.codesamples;

import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.dowjones.data.source.sequencer.domain.AccessionRevisionItem;

public class SequencerDAOImpl_Test {
	
	 private AmazonDynamoDBClient connection;
	 
	 public SequencerDAOImpl_Test(AmazonDynamoDBClient connection) {
		 this.connection=connection;
	}
	
	 public boolean insertRecord(AccessionRevisionItem accessionRevisionItem ) {
        try {
        	
        	DynamoDBMapper mapper = new DynamoDBMapper(connection);
        	// Make the request
        	
        	/*PutItemResult result = mapper.putItem(new PutItemRequest()
        	    .withTableName("AccessionRevisionItem")
        	    .withItem(new ImmutableDescriptor<String, AttributeValue>()
        	        .put("Id", new AttributeValue().withN("104"))
        	        .put("Title", new AttributeValue("Book 104 Title"))
        	        .put("ISBN", new AttributeValue("111-1111111111"))
        	        .put("Price", new AttributeValue().withN("25"))
        	        .put("Authors", new AttributeValue()
        	            .withSS(Arrays.asList("Author1", "Author2")))
        	        .build())
        	    .withExpected(new ImmutableDescriptor(String, ExpectedAttributeValue>().
        	        .put("Price", new ExpectedAttributeValue()
        	            .withValue(new AttributeValue().withN("26")))
        	        .build())
        	    .withReturnValues(ReturnValue.ALL_OLD));
        	//dynamoDBSaveExpression.
*/        	mapper.save(accessionRevisionItem);
        	return true;
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + ase);
            return false;
        }
	}

	public boolean updateRecord(AccessionRevisionItem accessionRevisionItem) {
		// TODO Auto-generated method stub
		return false;
	}

	public Map<String, List<Object>> getAccessionRevisionItems(
			List<Object> accessionRevisionItem) {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
		Map<String, List<Object>> items = mapper.batchLoad(accessionRevisionItem);
		return items;
	}

	public boolean deleteRecord(AccessionRevisionItem accessionRevisionItem) {
		 try {
	        	
	        	DynamoDBMapper mapper = new DynamoDBMapper(connection);
	        	mapper.save(accessionRevisionItem);
	        	return true;
	        }   catch (AmazonServiceException ase) {
	            System.err.println("Failed to create item in " + ase);
	            return false;
	        }
	}
	
	
	
	public List<AccessionRevisionItem> getAccessionRevisionList(AccessionRevisionItem hashKeyValues) {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
		DynamoDBQueryExpression<AccessionRevisionItem> queryExpression = new DynamoDBQueryExpression<AccessionRevisionItem>()
		    .withHashKeyValues(hashKeyValues);
		queryExpression.setHashKeyValues(hashKeyValues);
		
		return mapper.query(AccessionRevisionItem.class, queryExpression);
	}


	public List<AccessionRevisionItem> findAccessionRevisionLatestToSpecifiedValue(
			String revisionNumber, 
			String accessionNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AccessionRevisionItem> findRevisionEnrichmentLatestToSpecifiedValue(
			String revisionNumber, String enrichmentLevel,
			String accessionNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AccessionRevisionItem> findAllRecordsByAccessionNo(
			String accessionNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	  
}
