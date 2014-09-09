package com.dowjones.data.source.sequencer.service;

import java.util.List;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

public interface SequencerService {

	public boolean insertAccessionRevisionRecord(AccessionRevision accessionRevisionItems);
	
	public List<AccessionRevision> findAllRecordsByAccessionNo(String accessionNumber);
	
	public boolean markedForDeleteAccessionNo(String accessionNumber);
}
