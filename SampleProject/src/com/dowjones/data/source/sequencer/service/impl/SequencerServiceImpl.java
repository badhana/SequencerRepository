package com.dowjones.data.source.sequencer.service.impl;

import java.util.Date;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.dowjones.data.source.sequencer.dao.SequencerDAO;
import com.dowjones.data.source.sequencer.dao.impl.SequencerDAOImpl;
import com.dowjones.data.source.sequencer.domain.AccessionRevision;
import com.dowjones.data.source.sequencer.service.SequencerService;
import com.dowjones.data.source.sequencer.util.DynamoDBConnection;

public class SequencerServiceImpl implements SequencerService {

	@Override
	public synchronized boolean insertAccessionRevisionRecord(AccessionRevision accessionRevisionItem) {
		System.out.println("Inside method -->insertAccessionRevisionRecord for accessionNumber : ["+accessionRevisionItem.getAccessionNumber()+"]...\n");
		boolean insertStatus=false;
		AmazonDynamoDBClient client=DynamoDBConnection.getConnectionClient();
		SequencerDAO sequenceDAO = new SequencerDAOImpl(client);
		List<AccessionRevision> scanResult;
		try {
			
			if(sequenceDAO.isAccessionRevisionMarkedForDeletion(accessionRevisionItem.getAccessionNumber())){
				//marked for deletion so no record can be inserted 
				//discard insert
				System.out.println("Accession Number : ["+accessionRevisionItem.getAccessionNumber()+"] is already marked for deletion. No insert...\n");
				insertStatus=false;
			}else{
			
				scanResult = sequenceDAO.findAccessionRevisionLatestToSpecifiedValue(Integer.toString(accessionRevisionItem.getRevisionNumber()), accessionRevisionItem.getAccessionNumber());
				
				/*for (AccessionRevision accessionRevisionItr : scanResult) {
		            System.out.println(accessionRevisionItr+"\n");
			    }*/
				
				if(scanResult==null || scanResult.size()==0){
					scanResult = sequenceDAO.findRevisionEnrichmentLatestToSpecifiedValue(Integer.toString(accessionRevisionItem.getRevisionNumber()), Integer.toString(accessionRevisionItem.getEnrichmentLevel()), accessionRevisionItem.getAccessionNumber());
					
					if(scanResult==null || scanResult.size()==0){
						System.out.println("No revision and enrichment record latest to this present. Record is inserted...\n");
						//insert here
						accessionRevisionItem.setUpdateDateTime((new Date()).getTime());
						sequenceDAO.insertRecord(accessionRevisionItem);
						//return status
						insertStatus=true;
					}else{
						//add to discarded
						//return status
						System.out.println("Latest enrichment & revision record already present. No record inserted...\n");
						insertStatus=false;
					}
					/*for (AccessionRevision accessionRevisionItr : scanResult) {
			            System.out.println(accessionRevisionItr+"\n");
					}*/
				}else{
					System.out.println("Latest revision record already present. No record inserted...\n");
					//add to discarded
					//return status
					insertStatus=false;
				}
			}
			
		} catch (AmazonServiceException ase) {
			// TODO Auto-generated catch block
			ase.printStackTrace();
		}
		catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return insertStatus;
	}
	
	
	public List<AccessionRevision> findAllRecordsByAccessionNo(String accessionNumber){
		System.out.println("Inside method -->findAllRecordsByAccessionNo for accessionNumber : ["+accessionNumber+"] ...\n");
		SequencerDAO sequenceDAO = new SequencerDAOImpl(DynamoDBConnection.getConnectionClient());
		List<AccessionRevision> accessionRevisionItemList=null;
		try {
			accessionRevisionItemList = sequenceDAO.findAllRecordsByAccessionNo(accessionNumber);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accessionRevisionItemList;
	}


	@Override
	public boolean markedForDeleteAccessionNo(String accessionNumber) {
		boolean recordDeleted=false;
		System.out.println("Inside method -->removeRecordsForAccessionNo for accessionNumber : ["+accessionNumber+"] ...\n");
		SequencerDAO sequenceDAO = new SequencerDAOImpl(DynamoDBConnection.getConnectionClient());
		try {
			if(!sequenceDAO.isAccessionRevisionMarkedForDeletion(accessionNumber)){
				recordDeleted = sequenceDAO.markedForDeleteAccessionNo(accessionNumber);
			}
			else{
				System.out.println("Accession Number : ["+ accessionNumber +"] is already marked for deletion....\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordDeleted;
	}

}
