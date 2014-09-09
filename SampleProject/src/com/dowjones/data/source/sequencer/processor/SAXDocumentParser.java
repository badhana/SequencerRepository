package com.dowjones.data.source.sequencer.processor;

import static com.dowjones.data.source.sequencer.util.SequencerConstants.ACTION_CODE_MAP;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

/**
 * The Handler for SAX Events.
 */
public class SAXDocumentParser extends DefaultHandler {

	AccessionRevision accessionRevision = null;

	public SAXDocumentParser(AccessionRevision accessionRevision) {
		super();
		this.accessionRevision = accessionRevision;
	}

	String content = null;

	@Override
	// Triggered when the start of tag is found.
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		switch (qName) {
		case "DistDoc":
			accessionRevision.setActionCode(ACTION_CODE_MAP.get(attributes
					.getValue("action")));
			System.out.println(" action code is --> "
					+ attributes.getValue("action") + "action code value is "
					+ ACTION_CODE_MAP.get(attributes.getValue("action")));
			break;
		case "Property":
			if ("accessionno".equals(attributes.getValue("name"))) {
				accessionRevision.setAccessionNumber(attributes
						.getValue("value"));
				System.out.println(" accessionno value is --> "
						+ attributes.getValue("value"));
			}
			if ("revisionno".equals(attributes.getValue("name"))) {
				accessionRevision.setRevisionNumber(Integer.parseInt(attributes
						.getValue("value")));
				System.out.println(" revisionno value is --> "
						+ attributes.getValue("value"));
			}
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

}