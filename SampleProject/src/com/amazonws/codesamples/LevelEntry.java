package com.amazonws.codesamples;

import java.util.HashMap;
import java.util.Map;

public class LevelEntry {

	private static LevelEntry instance;
	private Map<String, String> accessionRevisionLevelMap = new HashMap<String, String>();
	
	private LevelEntry(){
		/**
		 * Default initalization
		 */
		accessionRevisionLevelMap.put("First", "");
		accessionRevisionLevelMap.put("Second", "0");
		accessionRevisionLevelMap.put("Third", "0");
	}
	
	public static LevelEntry getInstance() {
		if(instance==null){
			instance= LevelEntry.getInstance();
			return instance;
		}
		return instance;
	}
	
	public String getAccessionRevisionLevel(String level){
		return accessionRevisionLevelMap.get(level);
	}
	
	public void setAccessionRevisionLevel(String level, String value){
		accessionRevisionLevelMap.put(level,value);
	}
}
