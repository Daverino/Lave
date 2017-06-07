package org.aos.logparser.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Faction {
	private String allegiance;
	private String factionState;
	private double influence;
	private String government;
}
