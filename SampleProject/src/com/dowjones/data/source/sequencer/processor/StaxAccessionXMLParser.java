package com.dowjones.data.source.sequencer.processor;

import static com.dowjones.data.source.sequencer.util.SequencerConstants.ACTION_CODE_MAP;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
 

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
 

import com.dowjones.data.source.sequencer.domain.AccessionRevision;
 
public class StaxAccessionXMLParser {
     
    public static void main(String[] args) {
        String fileName = "/Users/pankaj/employees.xml";
        AccessionRevision accessionRevision = parseXML(fileName);
            System.out.println(accessionRevision);
    }
 
    private static AccessionRevision parseXML(String fileName) {
        AccessionRevision accessionRevision = new AccessionRevision();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            int event = xmlStreamReader.getEventType();
            while(true){
                switch(event) {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("DistDoc")){
                    	//accessionRevision.setActionCode(ACTION_CODE_MAP.get(xmlStreamReader.get .getValue("action")));
            			/*System.out.println(" action code is --> "
            					+ attributes.getValue("action") + "action code value is "
            					+ ACTION_CODE_MAP.get(attributes.getValue("action")));*/
                    }
                    break;
                }
              
               if (!xmlStreamReader.hasNext())
                    break;
 
              event = xmlStreamReader.next();
        } 
        }catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return accessionRevision;
    }
}
