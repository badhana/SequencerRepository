package com.amazonws.codesamples;

import java.util.Comparator;

import com.dowjones.data.source.sequencer.domain.AccessionRevision;

public class AccessionRevisionComparator implements Comparator<AccessionRevision>{

	@Override
	public int compare(AccessionRevision o1, AccessionRevision o2) {
		int i=o1.getRevisionNumber()>o2.getRevisionNumber()?1:0;
		return i;
	}

	public int compareTo(AccessionRevision p1, AccessionRevision p2)
	{
	    int i = p1.getAccessionNumber().compareTo(p2.getAccessionNumber());
	    if (i != 0) return i;
		return i;

	   /* i = p2.lastName.compareTo(p2.lastName);
	    if (i != 0) return i;*/

	    //return Integer.valueOf(p1.age).compareTo(Integer.valueOf(p2.age);
	}
}
