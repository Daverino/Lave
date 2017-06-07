package org.aos.logparser.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinorFactionPresence {

	private MinorFaction faction;
	private int minorFactionId;
	private String influence;
	private String state;
	
	public MinorFaction getFaction() {
		return faction;
	}

	public void setFaction(MinorFaction faction) {
		this.faction = faction;
	}
	
	public int getMinorFactionId() {
		return minorFactionId;
	}

	@JsonProperty("minor_faction_id")
	public void setMinorFactionId(int minorFactionId) {
		this.minorFactionId = minorFactionId;
	}

	public float getInfluence() {
		try  {
			return (Float.parseFloat(influence));
		} catch (NumberFormatException | NullPointerException nfe)  {
			return 0;
		}
	}
	
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

}
