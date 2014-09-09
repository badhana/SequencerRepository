package com.dowjones.data.source.sequencer.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.dowjones.data.source.sequencer.domain.AccessionRevisionItem;

public class FileScannerDemo {

	public static void main(String[] args) {
		FileScannerDemo fileScannerDemo = new FileScannerDemo();
		Queue<AccessionRevisionItem> accessionRevisionItems= fileScannerDemo.readFileAndCreateQueue("c:\\\\Users\\adhanab\\Desktop\\SampleInput.csv");
		System.out.println(accessionRevisionItems);
	}
	
	private Queue<AccessionRevisionItem> readFileAndCreateQueue(String fileName) {
		Queue<AccessionRevisionItem> accessionRevisionItems = new LinkedList<AccessionRevisionItem>();
	       try {
	         File file = new File(fileName);
	         Scanner scanner = new Scanner(file);
	         while (scanner.hasNextLine()) {
	           String scannerText = scanner.nextLine();
	           System.out.println(scannerText);
	           accessionRevisionItems.add(populateAccessionRevisonObject(scannerText));
	         }
	         scanner.close();
	       } catch (FileNotFoundException e) {
	         e.printStackTrace();
	       }
	       return accessionRevisionItems;
	}
	
	private AccessionRevisionItem populateAccessionRevisonObject(String scannerTextInput){
		AccessionRevisionItem accessionRevisionItem = new AccessionRevisionItem();
		if(scannerTextInput!=null && !"".equals(scannerTextInput)){
			String splittedText [] = scannerTextInput.split(",");
			for(String textItem : splittedText){
				if(textItem!=null && !"".equals(textItem)){
					String dataColumn [] = textItem.split("=");
					if("accessionNumber".equals(dataColumn[0].trim())){
						accessionRevisionItem.setAccessionNumber(dataColumn[1]);
					}
					if("revisionNumber".equals(dataColumn[0].trim())){
						accessionRevisionItem.setRevisionNumber(Integer.parseInt(dataColumn[1]));
					}
					if("enrichmentLevel".equals(dataColumn[0].trim())){
						accessionRevisionItem.setEnrichmentLevel(Integer.parseInt(dataColumn[1]));
					}
				}
			}
		}
		return accessionRevisionItem;
	}
}

