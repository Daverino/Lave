package org.aos.logparser.pojos;

public enum Government {
	ANARCHY("Anarchy"),
	COMMUNISM("Communism"),
	CONFEDERACY("Confederacy"),
	COOPERATIVE("Cooperative"),
	CORPORATE("Corporate"),
	DEMOCRACY("Democracy"),
	DICTATORSHIP("Dictatorship"),
	ENGINEER("Engineer"),
	FEUDAL("Feudal"),
	PATRONAGE("Patronage"),
	PRISON("Prison Colony"),
	THEOCRACY("Theocracy");
	
	private String value;
	private Government(String value)  {
	   this.value = value;
	}
	
	public String toString()  {
	   return this.value; 
	}
}
