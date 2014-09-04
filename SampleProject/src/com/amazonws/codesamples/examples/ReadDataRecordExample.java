package com.amazonws.codesamples.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dowjones.data.source.sequencer.dao.impl.SequencerDAOImpl;
import com.dowjones.data.source.sequencer.domain.AccessionRevisionItem;
import com.dowjones.data.source.sequencer.util.DynamoDBConnection;

public class ReadDataRecordExample {

	public static void main(String[] args) {
		ArrayList<Object> accessionRevisionItems = new ArrayList<Object>();

		AccessionRevisionItem accessionRevisionItem = new AccessionRevisionItem();
		accessionRevisionItem.setId("101");
		accessionRevisionItems.add(accessionRevisionItem);
		
		accessionRevisionItem = new AccessionRevisionItem();
		accessionRevisionItem.setId("102");
		accessionRevisionItems.add(accessionRevisionItem);
		
		accessionRevisionItem = new AccessionRevisionItem();
		accessionRevisionItem.setId("103");
		accessionRevisionItems.add(accessionRevisionItem);
		
		accessionRevisionItem = new AccessionRevisionItem();
		accessionRevisionItem.setId("104");
		accessionRevisionItems.add(accessionRevisionItem);
		
		accessionRevisionItem = new AccessionRevisionItem();
		accessionRevisionItem.setId("105");
		accessionRevisionItems.add(accessionRevisionItem);

		/*ThreadItem threadItem = new ThreadItem();
		threadItem.setForumName("Amazon DynamoDB");
		threadItem.setSubject("Amazon DynamoDB thread 1 message text");
		itemsToGet.add(threadItem);*/
		SequencerDAOImpl sequenceDAO = new SequencerDAOImpl(DynamoDBConnection.getConnectionClient());
		Map<String, List<Object>> accessionRevisionItemList =  sequenceDAO.getAccessionRevisionItems(accessionRevisionItems);
		
		for(Map.Entry<String, List<Object>> entry: accessionRevisionItemList.entrySet()){
			int count=1;
			for(Object obj:entry.getValue()){
			System.out.println("Record No. -->"+ count++ + ". "+obj+"\n");
			}
		}
		
		
		/*AccessionRevisionItem accessionRevisionItemForListResult = new AccessionRevisionItem();
		accessionRevisionItemForListResult.setId(103);
		List<AccessionRevisionItem> accessionRevisionList =  sequenceDAO.getAccessionRevisionList(accessionRevisionItemForListResult);
		
		for (AccessionRevisionItem acRevisionItem: accessionRevisionList) {
		    System.out.println("id= "+acRevisionItem.getId()+" , accessionNumber= "+acRevisionItem.getAccessionNumber()+" , revisionNumber= "+acRevisionItem.getRevisionNumber()+" , enrichmentLevel= "+acRevisionItem.getEnrichmentLevel()+" , actionCode= "+acRevisionItem.getActionCode());
		}*/
		
	}
}
