package org.aos.logparser.pojos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PopulatedSystem {
	private int id;
	private long edsm_id;
	private String name;
	private long population;
	private String government;
	private String allegiance;
	private String power;
	private String power_state;
	private String controlling_minor_faction;
	private long lastUpdated;
	private String state;
	private List<MinorFactionPresence> minor_faction_presences;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public long getEdsm_id() {
		return edsm_id;
	}
	
	public void setEdsm_id(long edsm_id) {
		this.edsm_id = edsm_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPopulation() {
		return population;
	}
	
	public void setPopulation(long population) {
		this.population = population;
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
	
	public String getPower_state() {
		return power_state;
	}
	
	public void setPower_state(String power_state) {
		this.power_state = power_state;
	}
	
	public String getControlling_minor_faction() {
		return controlling_minor_faction;
	}
	
	public void setControlling_minor_faction(String controlling_minor_faction) {
		this.controlling_minor_faction = controlling_minor_faction;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public List<MinorFactionPresence> getMinor_faction_presences() {
		return minor_faction_presences;
	}
	
	public void setMinor_faction_presences(List<MinorFactionPresence> minor_faction_presences) {
		this.minor_faction_presences = minor_faction_presences;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
	public void setUpdated_at (long date)  {
		this.lastUpdated = date;
	}
	
	public long getUpdated_at ()  {
		return this.lastUpdated;
	}
	
	public Date getUpdatedDate ()  {
		return( new Date (lastUpdated * 1000));
	}
}


/*

"id":1,
"edsm_id":12695,
"name":"1 G. Caeli",
"x":80.90625,
"y":-83.53125,
"z":-30.8125,
"population":6544826,
"is_populated":true,
"government_id":144,
"government":"Patronage",
"allegiance_id":2,
"allegiance":"Empire",
"state_id":16,"state":"Boom",
"security_id":32,"security":"Medium",
"primary_economy_id":4,
"primary_economy":"Industrial",
"power":"Arissa Lavigny-Duval",
"power_state":"Exploited",
"power_state_id":32,
"needs_permit":false,
"updated_at":1491584521,
"simbad_ref":"",
"controlling_minor_faction_id":31816,
"controlling_minor_faction":"1 G. Caeli Empire League",
"reserve_type_id":3,"reserve_type":"Common",
"minor_faction_presences
*/