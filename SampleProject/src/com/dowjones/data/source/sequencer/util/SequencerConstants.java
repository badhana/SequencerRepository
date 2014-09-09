package com.dowjones.data.source.sequencer.util;

import java.util.HashMap;
import java.util.Map;


public final class SequencerConstants{

	public static final Map<String,Integer> ACTION_CODE_MAP = new HashMap<String,Integer>();
	static {
		ACTION_CODE_MAP.put("add", 0);
		ACTION_CODE_MAP.put("rep", 2);
		ACTION_CODE_MAP.put("del", 1);
    }
	
	public static final String ACTION_CODE_DELETE="del";
	public static final String ACTION_CODE_ADD="add";
	public static final String ACTION_CODE_REPEAT="rep";
	
}
