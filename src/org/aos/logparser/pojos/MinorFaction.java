package org.aos.logparser.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinorFaction {
	private int id;
	private String name;
	private String government;
	private String allegiance;
	private String globalState;
	private boolean isPlayerFaction;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGovernment() {
		return government;
	}
	
	public void setGovernment(String government) {
		this.government = government;
	}
	
	public String getAllegiance() {
		return allegiance;
	}
	
	public void setAllegiance(String allegiance) {
		this.allegiance = allegiance;
	}
	
	public String getGlobalState() {
		return globalState;
	}
	
	public void setGlobalState(String globalState) {
		this.globalState = globalState;
	}
	
	public boolean isPlayerFaction() {
		return isPlayerFaction;
	}
	
	public void setPlayerFaction(boolean isPlayerFaction) {
		this.isPlayerFaction = isPlayerFaction;
	}
}
