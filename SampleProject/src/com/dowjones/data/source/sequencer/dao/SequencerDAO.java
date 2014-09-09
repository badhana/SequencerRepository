package com.dowjones.data.source.sequencer.dao;

import java.util.List;
import java.util.Map;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

public interface SequencerDAO {

	public boolean insertRecord(AccessionRevision accessionRevisionItem );
	
	public boolean updateRecord(AccessionRevision accessionRevisionItem );
	
	public Map<String, List<Object>> getAccessionRevisionItems(List<Object> accessionRevisionItem);
	
	public boolean deleteRecord(AccessionRevision accessionRevisionItem);
	
	public List<AccessionRevision> getAccessionRevisionList(AccessionRevision hashKeyValues);
	
	public List<AccessionRevision> findAccessionRevisionLatestToSpecifiedValue(String revisionNumber,String accessionNumber) throws Exception;
	
	public List<AccessionRevision> findRevisionEnrichmentLatestToSpecifiedValue(String revisionNumber,String enrichmentLevel, String accessionNumber) throws Exception;
	
	public List<AccessionRevision> findAllRecordsByAccessionNo(String accessionNumber) throws Exception;
	
	public boolean markedForDeleteAccessionNo(String accessionNumber);
	
	public List<AccessionRevision> fetchRecordsByAccessionNo(String accessionNumber) throws Exception;
	
	public boolean isAccessionRevisionMarkedForDeletion(String accessionNumber) throws Exception;
}
