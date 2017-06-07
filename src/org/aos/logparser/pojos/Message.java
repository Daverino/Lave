package org.aos.logparser.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
	private String starSystem;
	private FactionState factionState;
	private String systemFaction;
	private String timestamp;
	private String systemSecurity;
	private Allegiance systemAllegiance;
	private Economy systemEconomy;
	private double[] starPos;
	private Faction[] factions;
	private String event;
	private Government government;
	
}
