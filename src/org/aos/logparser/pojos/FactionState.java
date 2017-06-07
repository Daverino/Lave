package org.aos.logparser.pojos;

public enum FactionState {
	BOOM("Boom"),
  BUST("Bust"),
  CIVILWAR("Civil War"),
  ELECTION("Election"),
  EXPANSION("Expansion"),
  FAMINE("Famine"),
  LOCKDOWN("Lockdown"),
  OUTBREAK("Outbreak"),
  NONE("None"),
  RETREAT("Retreat"),
  WAR("Independent");

	private String value;
	private FactionState(String value)  {
	   this.value = value;
	}
	
	public String toString()  {
	   return this.value; 
	}
}
