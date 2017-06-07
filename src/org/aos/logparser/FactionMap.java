package org.aos.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.aos.logparser.pojos.MinorFaction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FactionMap {

	private String factionFileName;
	private Map <Integer, MinorFaction> factionMap;
	private static FactionMap map;
	
	private FactionMap (String factionFileName)  {
		this.factionFileName = factionFileName;	
		factionMap = new HashMap<Integer, MinorFaction> ();
	}
	
	public static FactionMap factionMap (String fileName) throws IOException  {
		if (map == null || !fileName.equals(map.factionFileName))  {
			map = new FactionMap(fileName);
			map.initialize();
		}
		return map;
	}
	
	private void initialize () throws IOException  {
		File factionFile = new File (factionFileName);
		BufferedReader reader = new BufferedReader (new FileReader (factionFile)); 
		for (String line = reader.readLine(); line != null; line = reader.readLine())  {
			ObjectMapper mapper = new ObjectMapper ();
			MinorFaction faction = mapper.readValue(line, MinorFaction.class);
			factionMap.put(faction.getId(), faction);
		}
		reader.close();
	}	
	
	public MinorFaction getMinorFaction (int id)  {
		return (factionMap.get(id));
	}
}
