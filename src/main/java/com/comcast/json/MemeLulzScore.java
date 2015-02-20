/*
 * It is the main package for reading json file from system variable as file  or class path file
 * and convert the json into java object and vise versa
 * the output will be stored in log file which can be customized in log4j.properities
 * 
 */


package com.comcast.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class MemeLulzScore {
	static Logger log = Logger.getLogger(MemeLulzScore.class.getName());

	/*
	 * Getting abs path from class path file
	 */
	private String getAbsPath(String fileName) throws URISyntaxException {

		URL url = this.getClass().getClassLoader().getResource(fileName);
		if (url == null) {
			throw new RuntimeException("Cannot find resource on classpath: '"
					+ fileName + "'");
		}
		log.info(url);

		String filePath = new File(url.toURI()).getAbsolutePath();
		log.info("File Path = " + filePath);

		return filePath;
	}

	/*
	 * Getting path from system variable 
	 */
	private String getAbsPathFromSysParam(String fileName) {

		String filePath = System.getProperty(fileName);
		log.info("File Path = " + filePath);

		return filePath;
	}

	/*
	 * Reading JSON file and convert into Java Object and get the map out of java object
	 */
	private Map<String, String> readMemeJsonFromFile(String fileName)
			throws URISyntaxException {
		System.out.print("File Name = " + fileName);

		ObjectMapper mapper = new ObjectMapper();
		Memes meme = null;
		try {
			meme = mapper.readValue(new File(fileName), Memes.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Memes Json read " + meme.toString());

		Map<String, String> memesMap = meme.getMemesMap();
		return memesMap;
	}

	/*
	 * Writing back to json files with scores
	 */
	private void writeToJsonFile(Map<String, String> memeMap,
			Map<String, Integer> lulzMap, String fileName) {

		MemeLulz memelulz = new MemeLulz();
		ObjectMapper mapper = new ObjectMapper();
		try {
			memelulz.setMemesMap(memeMap);
			memelulz.setLulMap(lulzMap);
			mapper.writeValue(new File(fileName), memelulz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * getting the score based on names
	 */
	private int getLulzScore(String name, String val) {

		int lulZScore = 0;
		lulZScore = (val.hashCode() % 10 + 1);

		return lulZScore;
	}

	/*
	 * Generating LulScore map
	 */
	private Map<String, Integer> generateLulzScoreMap(
			Map<String, String> memeMap) {
		Map<String, Integer> lulzScoreMap = new HashMap<String, Integer>();
		Set<String> memeNames = memeMap.keySet();
		for (String memeName : memeNames) {
			int lulzScore = getLulzScore(memeName, memeMap.get(memeName));
			lulzScoreMap.put(memeName, lulzScore);
			log.info("lulz score: memeName = " + memeName + " and lulzScore= "
					+ lulzScore);
		}
		return lulzScoreMap;
	}

	/*
	 * Sorting the hashmap using the list
	 */
	private void sortList(Map<String, String> memeMap) {

		List<String> sortedKeys = new ArrayList<String>(memeMap.size());
		sortedKeys.addAll(memeMap.keySet());
		Collections.sort(sortedKeys);
		log.info("Sorted Keys " + sortedKeys);
	}

	public static void main(String[] args) throws URISyntaxException {
		
		if (args.length == 3) {
			String memeJsonFileName = args[1];
			int pathType = Integer.parseInt(args[2]);
			MemeLulzScore memeProcessor = new MemeLulzScore();
			log.info(memeJsonFileName);

			/*
			 * 1. Get Absolute path
			 */
			String fileAbsPath = null;
			if (pathType == 1) {
				// getAbsPath method needs json file "memes.json" in class file
				// directory
				fileAbsPath = memeProcessor.getAbsPath(memeJsonFileName);
			} else {
				fileAbsPath = memeProcessor
						.getAbsPathFromSysParam(memeJsonFileName);
			}

			/*
			 * 2. Read Json and convert into Java Object and get respective Map
			 */
			Map<String, String> memeMap = null;
			try {
				memeMap = memeProcessor.readMemeJsonFromFile(fileAbsPath);
			} catch (URISyntaxException e) {
				log.error(e.getStackTrace());
			}

			/*
			 * 3. Sort the Key values and print in log
			 */
			memeProcessor.sortList(memeMap);

			/*
			 * 4. Prepare the LulzScore based on memes Key
			 */
			Map<String, Integer> lulzScoreMap = memeProcessor
					.generateLulzScoreMap(memeMap);

			/*
			 * 5. Load both hash maps into memelulz object and writing back to JSON
			 */
			memeProcessor.writeToJsonFile(memeMap, lulzScoreMap, fileAbsPath);

		} else {
			log.error("Wrong Number of Arugment");

		}
	}

}
