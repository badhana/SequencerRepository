package com.dowjones.data.source.sequencer.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dowjones.data.source.sequencer.domain.AccessionRevisionItem;

public class AccessionProcessor {

	public static void main(String[] args) {
		List<AccessionRevisionItem> list = new ArrayList<AccessionRevisionItem>(); 
		AccessionRevisionItem accessionRevision = new AccessionRevisionItem();
		accessionRevision.setAccessionNumber("CNNWR00020140828ea8s005bq");
		accessionRevision.setRevisionNumber(0);
		accessionRevision.setEnrichmentLevel(1);
		list.add(accessionRevision);
		
		accessionRevision = new AccessionRevisionItem();
		accessionRevision.setAccessionNumber("CNNWR00020140828ea8s005bq");
		accessionRevision.setRevisionNumber(1);
		accessionRevision.setEnrichmentLevel(1);
		list.add(accessionRevision);
		
		accessionRevision = new AccessionRevisionItem();
		accessionRevision.setAccessionNumber("CNNWR00020140828ea8s005bq");
		accessionRevision.setRevisionNumber(0);
		accessionRevision.setEnrichmentLevel(2);
		list.add(accessionRevision);
		
		accessionRevision = new AccessionRevisionItem();
		accessionRevision.setAccessionNumber("CNNWR00020140828ea8s005bq");
		accessionRevision.setRevisionNumber(2);
		accessionRevision.setEnrichmentLevel(1);
		list.add(accessionRevision);
		
		accessionRevision = new AccessionRevisionItem();
		accessionRevision.setAccessionNumber("CNNWR00020140828ea8s005bq");
		accessionRevision.setRevisionNumber(1);
		accessionRevision.setEnrichmentLevel(2);
		list.add(accessionRevision);
		
		Collections.sort(list);
		
		System.out.println(list);
		
	}
}
