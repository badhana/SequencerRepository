package com.amazonws.codesamples;

import java.util.HashMap;
import java.util.Map;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

public class AccessionQueueSequencer {
	
	private Map<String,AccessionRevision> getEnrichedSequencedData(){
		//TODO: provide method definition
		return new HashMap<String, AccessionRevision>();
	}
	
	private Map<String, AccessionRevision> getIgnoredAccessionRevisionData(){
		//TODO: provide method definition - queue will be populated from context
		return new HashMap<String, AccessionRevision>();
	}
	
	private Map<String, AccessionRevision> getDeletedAccessionRevisionData(){
		//TODO: provide method definition
		return new HashMap<String, AccessionRevision>();
	}
	
	public void interceptRequest() {
		// TODO Auto-generated method stub
	}
	
	public boolean isDeleteRequest(AccessionRevision accessionRevision){
		//TODO: provide enum based value match for this action code
		/*if(accessionRevision.getActionCode()==SequencerEnum.ActionType.DELETE.getCode()){
			return true;
		}*/
		return false;
	}
	
	public boolean isAccessionRVNCELStale(AccessionRevision accessionRevision){
		//TODO: provide method definition
		
		Map<String,AccessionRevision> accessionRevisionsMap = getEnrichedSequencedData();
		
		if(accessionRevisionsMap!=null && accessionRevisionsMap.size()>0)
		{
			if(!accessionRevisionsMap.containsKey(accessionRevision.getAccessionNumber()+","+accessionRevision.getRevisionNumber())  
			  //&& accessionRevision.getAccessionNumber().equals(getLatestRevision(SequencerEnum.EntryLevel.FIRSTLEVEL.getCode()))
			  /*&& accessionRevision.getRevisionNumber()>=Integer.parseInt(getLatestRevision(SequencerEnum.EntryLevel.SECONDLEVEL.getCode()))
			  && accessionRevision.getEnrichmentLevel()>=Integer.parseInt(getLatestRevision(SequencerEnum.EntryLevel.THIRDLEVEL.getCode()))*/)
			{
		
					//getIgnoredAccessionRevisionData().put(--add ignored value here---); ignoredAccessionRevisionDataMap.put(accessionRevision.getAccessionNumber(),accessRevMap);
			}
		 return false;
		}
		
		return true;
	}
	
   private String getLatestRevision(String levelEntry){
	return LevelEntry.getInstance().getAccessionRevisionLevel(levelEntry);
   }
}
