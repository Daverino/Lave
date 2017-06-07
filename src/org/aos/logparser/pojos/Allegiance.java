package org.aos.logparser.pojos;

public enum Allegiance {
	ALLIANCE("Alliance"),
  FEDERATION("Federation"),
  EMPIRE("Empire"),
  INDEPENDENT("Independent");

	private String value;
	private Allegiance(String value)  {
	   this.value = value;
	}
	
	public String toString()  {
	   return this.value; 
	}
}
