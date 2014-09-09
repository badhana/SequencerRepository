package com.dowjones.data.source.sequencer.processor;

import java.util.List;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;
import com.dowjones.data.source.sequencer.service.SequencerService;
import com.dowjones.data.source.sequencer.service.impl.SequencerServiceImpl;
import com.dowjones.data.source.sequencer.util.DateUtil;

public class SequenceProcessor {

	public static void main(String[] args) {
		SequenceProcessor processor = new SequenceProcessor();
		
		AccessionRevision accessionRevision = new AccessionRevision();
		accessionRevision.setAccessionNumber("AN1");
		accessionRevision.setRevisionNumber(3);
		accessionRevision.setEnrichmentLevel(1);
		accessionRevision.setActionCode(0);
		
		processor.insertAccessionRevisionRecord(accessionRevision);

		//processor.markedForDeleteAccessionNo(accessionRevision.getAccessionNumber());
		
		List<AccessionRevision> listAccessionNos= processor.fetchAllRecordForAccession(accessionRevision.getAccessionNumber());
		
		int count=1;
		for(AccessionRevision accessionRevisionitr: listAccessionNos){
			System.out.println("Record No. "+ count++ +" --> [Accession Number = "+accessionRevisionitr.getAccessionNumber()+"] [Revision Number = "+accessionRevisionitr.getRevisionNumber()+"] [Enrichment Level = "+accessionRevisionitr.getEnrichmentLevel()+"] [Action Code = "+accessionRevisionitr.getActionCode()+"] [Update Date Time = "+DateUtil.formatDateFromDateTime(accessionRevisionitr.getUpdateDateTime())+"]");
    	}
	}
	
    public boolean insertAccessionRevisionRecord(AccessionRevision accessionRevision)
    {
    	SequencerService sequencerService = new SequencerServiceImpl();
		return sequencerService.insertAccessionRevisionRecord(accessionRevision);
    }
    
    public List<AccessionRevision> fetchAllRecordForAccession(String acessionNumber){
    	//fetch all the records by accession number
    	SequencerService sequencerService = new SequencerServiceImpl();
    	return sequencerService.findAllRecordsByAccessionNo(acessionNumber);
    }
    
    public boolean markedForDeleteAccessionNo(String acessionNumber){
    	SequencerService sequencerService = new SequencerServiceImpl();
    	return sequencerService.markedForDeleteAccessionNo(acessionNumber);
    }
}
