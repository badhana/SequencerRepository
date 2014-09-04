package com.amazonws.codesamples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.naming.InitialContext;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

public class Sequencer1 {
	
	private Map<String, Map<String,Map<String,AccessionRevision>>> getEnrichedSequencedData(){
		//TODO: provide method definition
		return new HashMap<String, Map<String,Map<String,AccessionRevision>>>();
	}
	
	private Map<String, Map<String,Map<String,AccessionRevision>>> getIgnoredAccessionRevisionData(){
		//TODO: provide method definition - queue will be populated from context
		return new HashMap<String, Map<String,Map<String,AccessionRevision>>>();
	}
	
	private Map<String, Map<String,Map<String,AccessionRevision>>> getDeletedAccessionRevisionData(){
		//TODO: provide method definition
		return new HashMap<String, Map<String,Map<String,AccessionRevision>>>();
	}
	
	public void interceptRequest() {
		// TODO Auto-generated method stub
	}
	
	public boolean isDeleteRequest(AccessionRevision accessionRevision){
		//TODO: provide enum based value match for this action code
		if(accessionRevision.getActionCode()==1){
			return true;
		}
		return false;
	}
	
	public boolean isAccessionRVNCELStale(AccessionRevision accessionRevision){
		//TODO: provide method definition
		
		Map<String, Map<String,Map<String,AccessionRevision>>> accessionRevisionsMap = getEnrichedSequencedData();
		
		Map<String, Map<String,Map<String,AccessionRevision>>> ignoredAccessionRevisionDataMap = getIgnoredAccessionRevisionData();
		
		if(accessionRevisionsMap!=null && accessionRevisionsMap.size()>0)
		{
			if(!accessionRevisionsMap.containsKey(accessionRevision.getAccessionNumber()+accessionRevision.getRevisionNumber()+1))
			{
					if(null==accessionRevisionsMap.get(accessionRevision.getAccessionNumber())){
						Map<String,AccessionRevision> accessRevEnrichMap = new HashMap<String,AccessionRevision>();
						accessRevEnrichMap.put(accessionRevision.getAccessionNumber()+","+accessionRevision.getRevisionNumber()+","+accessionRevision.getEnrichmentLevel(),
								accessionRevision);
						Map<String,Map<String,AccessionRevision>> accessRevMap= new HashMap<String,Map<String,AccessionRevision>>();
						accessRevMap.put(accessionRevision.getAccessionNumber()+","+accessionRevision.getRevisionNumber(),accessRevEnrichMap);
						
						accessionRevisionsMap.put(accessionRevision.getAccessionNumber(), accessRevMap);
					}else{
						
						//accessionRevisionsMap.get(accessionRevision.getAccessionNumber()).put();
						
								Map<String,Map<String,AccessionRevision>> accessRevMap= new HashMap<String,Map<String,AccessionRevision>>();
							//	accessRevMap.put(accessionRevision.getAccessionNumber()+","+accessionRevision.getRevisionNumber(),accessRevEnrichMap);
								
								//accessionRevision.getAccessionNumber()+","+accessionRevision.getRevisionNumber()+","+accessionRevision.getEnrichmentLevel(),
								//accessionRevision);
					}
					
					
					
		
					//ignoredAccessionRevisionDataMap.put(accessionRevision.getAccessionNumber(),accessRevMap);
			}
		 return false;
		}
		
		//accessionRevisions.
		
		
		return true;
	}
}
