package org.aos.logparser.pojos;

public enum Economy {
	AGRICULTURE("Agriculture"),
	EXTRACTION("Extraction"),
	HIGHTECH("High Tech"),
	INDUSTRIAL("Industrial"),
	MILITARY("Military"),
	REFINERY("Refinery"),
	SERVICE("Service"),
	TERRAFORMING("Terraforming"),
	TOURISM("Tourism"),
	COLONY("Colony"),
	NONE("None");
	
	private String value;
	private Economy(String value)  {
	   this.value = value;
	}
	
	public String toString()  {
	   return this.value; 
	}
}
