package com.dowjones.data.source.sequencer.processor;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

public class DocumentParser {

	  public static void main(String[] args) throws Exception {
	    
		//SAX Parser
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
	    SAXParser parser = parserFactor.newSAXParser();
	    AccessionRevision accessionRevision = new AccessionRevision();
	    SAXDocumentParser handler = new SAXDocumentParser(accessionRevision);
	    parser.parse(ClassLoader.getSystemResourceAsStream("PAPRIN2000-09-09_rep_832038189.xml"), 
	                 handler);
	    
	    System.out.println(accessionRevision);
	  }
	}
