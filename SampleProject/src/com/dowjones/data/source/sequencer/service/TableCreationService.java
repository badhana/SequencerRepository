package com.dowjones.data.source.sequencer.service;

import com.amazonaws.AmazonServiceException;
import com.dowjones.data.source.sequencer.dao.TableCreator;
import com.dowjones.data.source.sequencer.dao.impl.TableCreatorImpl;
import com.dowjones.data.source.sequencer.util.DynamoDBConnection;

public class TableCreationService {

	public static void main(String[] args) {
		TableCreator creator = new TableCreatorImpl(DynamoDBConnection.getConnectionClient());
		//for the first time only --- creator.createTable("AccessionRevisionItem",10L, 5L, "Id", "N");
		try{
			creator.deleteTable("AccessionRevision");
			creator.createTable("AccessionRevision",10L, 5L, "AccessionNumber", "S");
			if(creator.isTableAvailable("AccessionRevision")){
				System.out.println("Table is created \n");
				/*AccessionRevisionItem accessionRevisionItem = new AccessionRevisionItem(); 
				accessionRevisionItem.setId("101");
				accessionRevisionItem.setAccessionNumber("AN1");
				accessionRevisionItem.setRevisionNumber(0);
				accessionRevisionItem.setEnrichmentLevel(1);
				accessionRevisionItem.setActionCode(0);
				
				SequencerDAO sequenceDAO = new SequencerDAOImpl(DynamoDBConnection.getConnectionClient());
				sequenceDAO.insertRecord(accessionRevisionItem);*/
				
			}
		}catch (AmazonServiceException ase){
			 System.err.println("Failed to insert record for accessionRevisionItem with error: " + ase);
		}
	}
	
}
