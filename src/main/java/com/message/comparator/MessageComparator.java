package com.message.comparator;

import java.util.Comparator;

import com.message.pojo.Entity;

public class MessageComparator implements Comparator<Entity>{

	public int compare(Entity o1, Entity o2) {
		return -(o1.getPriceInUSD().compareTo(o2.getPriceInUSD())); // for calculating rank
	}
	
}
