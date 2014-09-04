package com.dowjones.data.source.sequencer.dao.impl;

import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.dowjones.data.source.sequencer.domain.AccessionRevisionItem;

public class SequencerDAOImpl {
	
	 private AmazonDynamoDBClient connection;
	 
	 public SequencerDAOImpl(AmazonDynamoDBClient connection) {
		 this.connection=connection;
	}
	
	 public boolean insertRecord(AccessionRevisionItem accessionRevisionItem ) {
        try {
        	
        	DynamoDBMapper mapper = new DynamoDBMapper(connection);
        	mapper.save(accessionRevisionItem);
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
	  
	
	public List<AccessionRevisionItem> findAccessionRevisionLatestToSpecifiedValue(String revisionNumber,String accessionNumber) throws Exception {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
        System.out.println("Inside method -->findAccessionRevisionLatestToSpecifiedValue: Scanning Accession Revision Level.");
                
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("RevisionNumber", 
                new Condition()
                   .withComparisonOperator(ComparisonOperator.GT)
                   .withAttributeValueList(new AttributeValue().withN(revisionNumber)));
        scanExpression.addFilterCondition("AccessionNumber", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(accessionNumber)));
        
        return mapper.scan(AccessionRevisionItem.class, scanExpression);
    }
	
	
	public List<AccessionRevisionItem> findRevisionEnrichmentLatestToSpecifiedValue(String revisionNumber,String enrichmentLevel, String accessionNumber) throws Exception {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
        System.out.println("Inside method -->findRevisionEnrichmentLatestToSpecifiedValue: Scanning Revision Enrichment Level ....\n");
                
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        
        scanExpression.addFilterCondition("EnrichmentLevel", 
                new Condition()
                   .withComparisonOperator(ComparisonOperator.GE)
                   .withAttributeValueList(new AttributeValue().withN(enrichmentLevel)));
        
        scanExpression.addFilterCondition("RevisionNumber", 
                new Condition()
                   .withComparisonOperator(ComparisonOperator.EQ)
                   .withAttributeValueList(new AttributeValue().withN(revisionNumber)));
        
        scanExpression.addFilterCondition("AccessionNumber", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(accessionNumber)));
        
        return mapper.scan(AccessionRevisionItem.class, scanExpression);
    }
	
	public List<AccessionRevisionItem> findAllRecordsByAccessionNo(String accessionNumber) throws Exception {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
        System.out.println("Inside method --> findAllRecordsByAccessionNo: Scan By Accession Number \n");
                
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("AccessionNumber", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(accessionNumber)));
        //mapper.
        return mapper.scan(AccessionRevisionItem.class, scanExpression);
    }
}
