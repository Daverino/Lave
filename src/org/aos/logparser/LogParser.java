package org.aos.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aos.logparser.pojos.MinorFactionPresence;
import org.aos.logparser.pojos.PopulatedSystem;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class LogParser {
	private String systemFileName;
	private String factionFileName;
	private String systemUrl;
	private String factionUrl;
	private Map<String, PopulatedSystem> mahonSystems;
	
	public LogParser  (String systemFileName, String systemUrl, String factionFileName, String factionUrl)  {
		this.systemFileName = systemFileName;
		this.factionFileName = factionFileName;
		this.systemUrl = systemUrl;
		this.factionUrl = factionUrl;
		mahonSystems = new HashMap<String, PopulatedSystem> () ;
	}
	
	public static void main (String args[]) throws JsonParseException, JsonMappingException, IOException  {
		LogParser parser = new LogParser ("etc/systems_populated.jsonl", "https://eddb.io/archive/v5/systems_populated.jsonl", "etc/factions.jsonl", "https://eddb.io/archive/v5/factions.jsonl");
		boolean updateData = true;
		boolean allMahon = false;
		boolean conflictsOnly = false;
		boolean prettyPrint = false;
		parser.parse(updateData, allMahon, conflictsOnly, prettyPrint);
	}
	
	public void parse (boolean updateData, boolean allMahon, boolean conflictsOnly, boolean prettyPrint) throws JsonParseException, JsonMappingException, IOException  {
		if (updateData)  {
			updateData();
		}
		loadDataFromFiles();
		List <String> sortedSystems = new ArrayList<String>(mahonSystems.keySet());
		Collections.sort(sortedSystems);
		int dayFresh = 0;
		int twoDayFresh = 0;
		int weekFresh = 0;
		int count = 0;
		for (String systemName : sortedSystems)  {
			if (TrackerConstants.isTracked(systemName) || allMahon  )  {
				PopulatedSystem system = mahonSystems.get(systemName);
				boolean controlConflict = testControlConflict(system);
				boolean underwaterConflictPending = testUnderwaterPendingConflict(system, controlConflict);
				boolean pendingLeaderConflict = testPendingControlConflict(system, underwaterConflictPending);
				boolean underwater = testUnderwater(system);
				boolean pendingMinorConflict = false;
				boolean stale = true;
				if (daysSinceUpdate(system.getUpdatedDate()) <= 1)  {
					dayFresh ++;
					stale = false;
				}
				if (daysSinceUpdate(system.getUpdatedDate()) <= 2)  {
					twoDayFresh ++;
					stale = false;
				}
				if (daysSinceUpdate(system.getUpdatedDate()) <= 7)  {
					weekFresh ++;
				}
				if (stale)  {
					count ++;
					//continue;
				}
				count ++;
				if (controlConflict || underwaterConflictPending || pendingLeaderConflict || underwater || !conflictsOnly)  {
					if (prettyPrint)  {
						printHeader (system, controlConflict, underwaterConflictPending, pendingLeaderConflict, underwater);
					}
					for (MinorFactionPresence factionPresence : system.getMinor_faction_presences())  {
						if (prettyPrint)  {
							printReadable(factionPresence); 
						} else  {
							printCSV(system, factionPresence); 
						}
					}
					if (prettyPrint)  {
						System.out.println();
					}
				}
			}
		}
		System.out.println("Total systems tracked: " + count);
		System.out.println("Total systems updated in last day: " + dayFresh + "(" + String.format("%1$.2f",(double)dayFresh/(double)count*100) + "%)");
		System.out.println("Total systems updated in last two days: " + twoDayFresh+ "(" + String.format("%1$.2f",(double)twoDayFresh/(double)count*100) + "%)");
		System.out.println("Total systems updated in last week: " + weekFresh+ "(" + String.format("%1$.2f",(double)weekFresh/(double)count*100) + "%)");
		
	}
	
	private void printHeader(PopulatedSystem system, boolean controlConflict, boolean underwaterConflictPending, boolean pendingLeaderConflict, boolean underwater)  {
		System.out.println("System: " + system.getName() + " -- Current state: " + system.getMinor_faction_presences().get(0).getState());
		System.out.println("Data last updated at " + system.getUpdatedDate().toString());
		System.out.println(timeSinceUpdate(system.getUpdatedDate()));
		if (controlConflict)  {
			System.out.println("*** CONFLICT FOR SYSTEM CONTROL FOUND ***");
		} else if (underwaterConflictPending)  {
			System.out.println("*** PENDING CONFLICT FOR SYSTEM CONTROL FOUND -- 60% Rule ***");
		} else if (pendingLeaderConflict)  {
			System.out.println("*** PENDING CONFLICT FOR SYSTEM CONTROL FOUND -- Equalized ***");
		}
		if (underwater)  {
			System.out.println("*** CONTROLLING FACTION IS UNDERWATER ***");
		}
		System.out.println("Controlling faction:  " + system.getControlling_minor_faction());

	}
	
	private void printReadable(MinorFactionPresence factionPresence)  {
		System.out.println(factionPresence.getFaction().getName() + 
				 "," + factionPresence.getInfluence() +
				 "," + factionPresence.getState() + 
				 "," + factionPresence.getFaction().getAllegiance() +
				 "," + factionPresence.getFaction().getGovernment());
	}

	private void printCSV (PopulatedSystem system, MinorFactionPresence factionPresence)  {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		System.out.println(dt.format(system.getUpdatedDate()) + ";" +
		system.getName() + ";" +
		factionPresence.getFaction().getName() + ";" +
		factionPresence.getInfluence() / 100 + ";" +
		factionPresence.getState());
	}
	private void updateData () throws MalformedURLException  {
		DataFetcher systemFetcher = new DataFetcher (systemUrl, systemFileName);
		if (systemFetcher.fetch())  {
			System.out.println("System Data fetched successfully");
		}
		DataFetcher factionFetcher = new DataFetcher (factionUrl, factionFileName);
		if (factionFetcher.fetch())  {
			System.out.println("System Data fetched successfully");
		}

	}
	
	private boolean testPendingControlConflict (PopulatedSystem p, boolean alreadyPending)  {
		if (alreadyPending)  {
			return false;
		}
		if (p.getMinor_faction_presences().size() < 2 || p.getMinor_faction_presences().get(0).getInfluence() == 0)  {
			return false;
		}
		if (p.getMinor_faction_presences().get(0).getInfluence() == p.getMinor_faction_presences().get(1).getInfluence())  {
			return true;
		}
		return false;
	}
	
	private boolean testUnderwaterPendingConflict(PopulatedSystem p, boolean conflict)  {
		if (conflict) {
			return false;
		} 
		if  (!(p.getMinor_faction_presences().get(0).getFaction().getName().equals(p.getControlling_minor_faction())) &&
				p.getMinor_faction_presences().get(0).getInfluence() >= 60)  {
			return true;
		}
		return false;
	}
	
	private boolean testUnderwater(PopulatedSystem p)  {
		if  ( p.getMinor_faction_presences().get(0).getInfluence() > 0 &&
				!(p.getMinor_faction_presences().get(0).getFaction().getName().equals(p.getControlling_minor_faction())))  {
			return true;
		}
		return false;
	}
	
	private boolean testControlConflict(PopulatedSystem p)  {
		if (p.getState().equals("Civil War") ||
				p.getState().equals("War") ||
				p.getState().equals("Election"))  {
			return true;
		}
		return false;
	}
	
	private long daysSinceUpdate  (Date updateDate)  {
		long now = System.currentTimeMillis();
		long elapsed = now - updateDate.getTime();
		return (elapsed / (1000 * 60 * 60 *24));
	}
	
	private String timeSinceUpdate  (Date updateDate)  {
		long now = System.currentTimeMillis();
		long elapsed = now - updateDate.getTime();
		long second = (elapsed / 1000) % 60;
		long minute = (elapsed / (1000 * 60)) % 60;
		long hour = (elapsed / (1000 * 60 * 60)) % 24;
		long day = (elapsed / (1000 * 60 * 60 *24));

		return("Time elapsed since last update:  " + String.format("%d days:%02d:%02d:%02d",day, hour, minute, second));
	}
	
	private void loadDataFromFiles () throws IOException {
		File systemFile = new File (systemFileName);
		BufferedReader reader = new BufferedReader (new FileReader (systemFile));
		FactionMap factionMap = FactionMap.factionMap(factionFileName);
		for (String line = reader.readLine(); line != null; line = reader.readLine())  {
			ObjectMapper mapper = new ObjectMapper ();
			PopulatedSystem system = mapper.readValue(line, PopulatedSystem.class);
			if (system.getPower() != null && system.getPower().equals("Edmund Mahon"))  {
				for (MinorFactionPresence factionPresence : system.getMinor_faction_presences())  {
					factionPresence.setFaction(factionMap.getMinorFaction(factionPresence.getMinorFactionId()));
				}
				Collections.sort(system.getMinor_faction_presences(), new Comparator<MinorFactionPresence>(){
			    public int compare(MinorFactionPresence o1, MinorFactionPresence o2) {
		        return (o2.getInfluence() - o1.getInfluence() > 0 ? 1 : -1);
		    }});
				mahonSystems.put(system.getName(), system);
			}
		}
		reader.close();
	}
}

