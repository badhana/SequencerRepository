package com.dowjones.data.source.sequencer.dao.impl;


import java.util.Date;
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
import com.dowjones.data.source.sequencer.dao.SequencerDAO;
import com.dowjones.data.source.sequencer.domain.AccessionRevision;

import static com.dowjones.data.source.sequencer.util.SequencerConstants.ACTION_CODE_MAP;
import static com.dowjones.data.source.sequencer.util.SequencerConstants.ACTION_CODE_DELETE;


public class SequencerDAOImpl implements SequencerDAO {
	
	 private AmazonDynamoDBClient connection;
	 
	 public SequencerDAOImpl(AmazonDynamoDBClient connection) {
		 this.connection=connection;
	}
	
	@Override
	 public boolean insertRecord(AccessionRevision accessionRevisionItem ) {
		boolean recordInserted=false;
		try {
        	DynamoDBMapper mapper = new DynamoDBMapper(connection);
        	mapper.save(accessionRevisionItem);
        	recordInserted=true;
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + ase);
            recordInserted=false;
        }
		return recordInserted;
	}

	@Override
	public boolean updateRecord(AccessionRevision accessionRevisionItem) {
		boolean recordUpdated=false;
		try {
        	DynamoDBMapper mapper = new DynamoDBMapper(connection);
        	mapper.save(accessionRevisionItem);
        	recordUpdated= true;
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to update item  " + ase);
            recordUpdated= false;
        }
		return recordUpdated;
	}

	@Override
	public Map<String, List<Object>> getAccessionRevisionItems(
			List<Object> accessionRevisionItem) {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
		Map<String, List<Object>> items = mapper.batchLoad(accessionRevisionItem);
		return items;
	}

	@Override
	public boolean deleteRecord(AccessionRevision accessionRevisionItem) {
		 try {
	        	DynamoDBMapper mapper = new DynamoDBMapper(connection);
	        	mapper.save(accessionRevisionItem);
	        	return true;
	        }   catch (AmazonServiceException ase) {
	            System.err.println("Failed to create item in " + ase);
	            return false;
	        }
	}

	@Override
	public List<AccessionRevision> getAccessionRevisionList(AccessionRevision hashKeyValues) {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
		DynamoDBQueryExpression<AccessionRevision> queryExpression = new DynamoDBQueryExpression<AccessionRevision>()
		    .withHashKeyValues(hashKeyValues);
		queryExpression.setHashKeyValues(hashKeyValues);
		
		return mapper.query(AccessionRevision.class, queryExpression);
	}
	  
	
	public List<AccessionRevision> findAccessionRevisionLatestToSpecifiedValue(String revisionNumber,String accessionNumber) throws Exception {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
        //System.out.println("Inside method -->findAccessionRevisionLatestToSpecifiedValue: Scanning Accession Revision Level.");
                
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("RevisionNumber", 
                new Condition()
                   .withComparisonOperator(ComparisonOperator.GT)
                   .withAttributeValueList(new AttributeValue().withN(revisionNumber)));
        scanExpression.addFilterCondition("AccessionNumber", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(accessionNumber)));
       
        /*scanExpression.addFilterCondition("updateDateTime", 
                new Condition()  
        .withComparisonOperator(ComparisonOperator.BETWEEN)
		.withAttributeValueList(new AttributeValue().withSS(""+DateUtil.getStartOfDayTime(Calendar.getInstance().getTime()),""+DateUtil.getEndOfDayTime(Calendar.getInstance().getTime()))));*/
        
        return mapper.scan(AccessionRevision.class, scanExpression);
    }
	
	
	public List<AccessionRevision> findRevisionEnrichmentLatestToSpecifiedValue(String revisionNumber,String enrichmentLevel, String accessionNumber) throws Exception {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
       // System.out.println("Inside method -->findRevisionEnrichmentLatestToSpecifiedValue: Scanning Revision Enrichment Level ....\n");
                
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
        
        /*scanExpression.addFilterCondition("updateDateTime", 
                new Condition()  
        .withComparisonOperator(ComparisonOperator.BETWEEN)
		.withAttributeValueList(new AttributeValue().withSS(""+DateUtil.getStartOfDayTime(Calendar.getInstance().getTime()),""+DateUtil.getEndOfDayTime(Calendar.getInstance().getTime()))));*/
        
        return mapper.scan(AccessionRevision.class, scanExpression);
    }
	
	public List<AccessionRevision> findAllRecordsByAccessionNo(String accessionNumber) throws Exception {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
        //System.out.println("Inside method --> findAllRecordsByAccessionNo: Scan By Accession Number \n");
                
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("AccessionNumber", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(accessionNumber)));
        //mapper.
        return mapper.scan(AccessionRevision.class, scanExpression);
    }

	@Override
	public boolean markedForDeleteAccessionNo(String accessionNumber) {
		boolean recordDeleted = false;
		try {
			DynamoDBMapper mapper = new DynamoDBMapper(connection);
			AccessionRevision accessionRevision = mapper.load(AccessionRevision.class, accessionNumber);

			accessionRevision.setAccessionNumber(accessionNumber);
			accessionRevision.setUpdateDateTime(new Date().getTime());
			accessionRevision.setActionCode(ACTION_CODE_MAP.get(ACTION_CODE_DELETE));

			mapper.save(accessionRevision);
			recordDeleted = true;
		} catch (AmazonServiceException ase) {
			System.err.println("Failed to create item in " + ase);
			recordDeleted = false;
		}
		return recordDeleted;
	}
	
	@Override
	public List<AccessionRevision> fetchRecordsByAccessionNo(String accessionNumber) throws Exception {
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
        //System.out.println("Inside method -->findAccessionRevisionLatestToSpecifiedValue: Scanning Accession Revision Level.");
                
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("AccessionNumber", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(accessionNumber)));
        
        return mapper.scan(AccessionRevision.class, scanExpression);
    }

	@Override
	public boolean isAccessionRevisionMarkedForDeletion(String accessionNumber)
			throws Exception {
		boolean accessionMarkedForDeletion=false;
		DynamoDBMapper mapper = new DynamoDBMapper(connection);
        //System.out.println("Inside method -->isAccessionRevisionMarkedForDeletion for accessionNumber --> "+accessionNumber);
                
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("AccessionNumber", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(accessionNumber)));
        scanExpression.addFilterCondition("ActionCode", 
                new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withN(ACTION_CODE_MAP.get(ACTION_CODE_DELETE).toString())));
        
        List<AccessionRevision> accessionRevisionList = mapper.scan(AccessionRevision.class, scanExpression);
        if(accessionRevisionList!=null && accessionRevisionList.size()>0){
        	accessionMarkedForDeletion=true;
        }
		return accessionMarkedForDeletion;
	}
}
