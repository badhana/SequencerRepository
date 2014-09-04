package com.amazonws.codesamples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.naming.InitialContext;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

public class Sequencer {
	
	private Map<String, Queue<AccessionRevision>> getEnrichedSequencedData(){
		//TODO: provide method definition
		//Map<AccessionRevision, List<AccessionRevision>> enrichedSequencedQueue = new PriorityQueue<AccessionRevision>();
		return new HashMap<String, Queue<AccessionRevision>>();
	}
	
	private Map<String, Queue<AccessionRevision>> getIgnoredAccessionRevisionData(){
		//TODO: provide method definition - queue will be populated from context
		//Queue<AccessionRevision> ignoredAccessionNoQueue = new PriorityQueue<AccessionRevision>();
		return new HashMap<String, Queue<AccessionRevision>>();
	}
	
	private Map<String, Queue<AccessionRevision>> getDeletedAccessionRevisionData(){
		//TODO: provide method definition
		//Queue<AccessionRevision> deletedAccessionQueue = new PriorityQueue<AccessionRevision>();
		return new HashMap<String, Queue<AccessionRevision>>();
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
		
		Map<String, Queue<AccessionRevision>> accessionRevisions = getEnrichedSequencedData();
		Map<String, Queue<AccessionRevision>> ignoredAccessionRevisionDataMap = getIgnoredAccessionRevisionData();
		if(accessionRevisions.containsKey(accessionRevision.getAccessionNumber()+accessionRevision.getRevisionNumber()+1)){
			//ignoredAccessionRevisionDataMap.put(accessionRevision.getAccessionNumber()+accessionRevision.getRevisionNumber(), accessionRevision);
		}
		
		//accessionRevisions.
		
		
		return false;
	}
}
