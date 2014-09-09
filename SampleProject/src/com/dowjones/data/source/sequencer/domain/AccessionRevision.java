package com.dowjones.data.source.sequencer.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * 
 *
 */
@DynamoDBTable(tableName="AccessionRevision")
public class AccessionRevision implements Comparable<AccessionRevision>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accessionNumber;
	private int revisionNumber;
	private int enrichmentLevel;
	private int actionCode;
	private long updateDateTime; 
	
	/**
	 * @return the accessionNumber
	 */
	@DynamoDBHashKey(attributeName = "AccessionNumber")
	public String getAccessionNumber() {
		return accessionNumber;
	}
	/**
	 * @param accessionNumber the accessionNumber to set
	 */
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
	/**
	 * @return the revisionNumber
	 */
	@DynamoDBAttribute(attributeName="RevisionNumber")  
	public int getRevisionNumber() {
		return revisionNumber;
	}
	/**
	 * @param revisionNumber the revisionNumber to set
	 */
	public void setRevisionNumber(int revisionNumber) {
		this.revisionNumber = revisionNumber;
	}
	/**
	 * @return the enrichmentLevel
	 */
	@DynamoDBAttribute(attributeName="EnrichmentLevel")  
	public int getEnrichmentLevel() {
		return enrichmentLevel;
	}
	/**
	 * @param enrichmentLevel the enrichmentLevel to set
	 */
	public void setEnrichmentLevel(int enrichmentLevel) {
		this.enrichmentLevel = enrichmentLevel;
	}
	
	/**
	 * @return the actionCode
	 */
	@DynamoDBAttribute(attributeName="ActionCode")  
	public int getActionCode() {
		return actionCode;
	}
	/**
	 * @param actionCode the actionCode to set
	 */
	public void setActionCode(int actionCode) {
		this.actionCode = actionCode;
	}
	
	/**
	 * @return the updateDateTime
	 */
	@DynamoDBAttribute(attributeName="UpdateDateTime")  
	public long getUpdateDateTime() {
		return updateDateTime;
	}
	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(long updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	@Override
	public int compareTo(AccessionRevision accessionRevision2) {
		return new CompareToBuilder().append(this.accessionNumber, accessionRevision2.accessionNumber).append(this.revisionNumber, accessionRevision2.revisionNumber).append(this.enrichmentLevel, accessionRevision2.enrichmentLevel).toComparison();  
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccessionRevision [accessionNumber="
				+ accessionNumber + ", revisionNumber=" + revisionNumber
				+ ", enrichmentLevel=" + enrichmentLevel + ", actionCode="
				+ actionCode + ", updateDateTime=" + updateDateTime + "]";
	}
	
}
