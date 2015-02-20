/*
 * This POJO class is for holding the Lulz score hash map
 */

package com.comcast.json;

import java.util.HashMap;
import java.util.Map;

public class LulzScore implements lulzInterface {
	private Map<String, Integer> lulzMap = new HashMap<String,Integer>();
	
	public Map<String, Integer> getLulzMap() {
		return lulzMap;
	}
	public void setLulMap(Map<String, Integer> lulzMap) {
		this.lulzMap = lulzMap;
	}
	
	@Override
	public String toString() {
		return "{" + getLulzMap().toString() +  "}";		
	}
}
