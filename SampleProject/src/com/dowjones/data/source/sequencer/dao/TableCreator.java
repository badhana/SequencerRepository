package com.dowjones.data.source.sequencer.dao;

public interface TableCreator {

	 public void createTable(String tableName, long readCapacityUnits, long writeCapacityUnits,
            String hashKeyName, String hashKeyType);
	
	 public boolean isTableAvailable(String tableName);
	
	 public boolean deleteTable(String tableName);
	
	 public boolean isTableDeleted(String tableName);
}
