/* this POJO class is for holding the memes hashmap
 * 
 * 
 * 
 */

package com.comcast.json;

import java.util.HashMap;
import java.util.Map;

public class Memes implements memesInterface{
	private Map<String, String> memesMap = new HashMap<String,String>();
	
	
	public Map<String, String> getMemesMap() {
		return memesMap;
	}

	public void setMemesMap(Map<String, String> memesMap) {
		this.memesMap = memesMap;
	}
	
	@Override
	public String toString() {
		return "{" + getMemesMap().toString() +  "}";		
	}


}
