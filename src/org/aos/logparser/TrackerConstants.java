package org.aos.logparser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

public class TrackerConstants  {
	
	private static String [] trackedSystems =  {"Kurrama", "BD+14 2955", "Deili", "Umbrogo", "Yum Kicha", "LTT 5873", "Ross 1028b",
																			"Losna", "LHS 3026", "BD+26 2723", "Waru", "Ix Tun", "Awawa", "BD+11 2853", 
																			"LTT 14398", "LHS 2983", "Iyaka", "HR 5243", "Tang Warri", "LP 442-37", 
																			"Mong Kung", "BD+11 2925", "Lalande 27958", "Ragnorak", "Velim", "Lalande 27958", "LHS 3026",
																			"CN Bootis", "Helvetitj", "LHS 2995"};
	
	private static String [] Helvetitj = {"BD+14 2955","Deili","BD+16 2722","BD+09 3000",	"Kucub Hua","Ix Tun",	"BD+11 2853",	"Himbalbaye",
																				"BD+08 3000",	"Tzotzil","LP 442-37","Taritra","Ragnorak","BD+15 2847","Helvetitj","Tepehuacoc"};

	private static String [] ThreeUO = {"Edindui", "Susan","LTT 6431","Naita","HIP 78883","Nicassee","Krataula","Parens","Calamari",
																			"Pushis","3 Upsilon Ophiuchi","Boveltibes","Mehen","HR 6189","Dhathaut","Paledawa","HIP 78983",
																			"Miromi"};
	
	private static String [] Arany = {"Chans","Amala","Teurbi","Dakshu","Ratatosk","Malja","Tsim Binavi","Cupen","Xamentii","Jurawadbad",
																		"Osans","Osiris","BD+15 3090","Hmar","Cantjarisni","Pandra","Chih Zhi","Arany"};

	private static String [] Tricorii = {"Andana","Campi","Sibitia","Aisoyiman","Basty","Lalande 30044","Yu Kop","Paucatec",
																			 "Turots","Gucub Baja","Alaw","Heitjmaramu","Tricorii","HIP 78399","Panik","Vuldrajo","Maljang",
																			 "Dhathaut","Tpheirset"};
	
	private static String [] Tikurua = {"Almudjing","Wu Ku","Haoki","Makuluk","Malali","Pijan","44 Eta Librae","HIP 77534","Akua",
																			"HIP 76768","HIP 76203","Savaroju","Arek","Kitchosat","HIP 75762","Tikurua"};

	private static String [] Contien = {"Tlapiapo","Jurati","HIP 78267","HIP 78716","Wille","Cynetek","Djamitra","Delta Normae",
																			"Kwangatjali","Jongsang","Kasya","Ega","HR 5975","LTT 6714","Contien","Wiljanherbi",
																			"CD-44 10336","HIP 79322","Kui Xiani","Yansisma","Poemaku","Purunsi","Kaukai","Camazotz"};

	private static String [] Alioth = {"Sobek","Alyuto","Tiethay","LP 131-55","Pie","Sheela Na Gig","Doris","LHS 2789","Amber",
																	 "LP 131-66","Alcor","HDS 1879","LTT 14019","Ethgreze","Phekda","LHS 2637","Khernidjal","Kuk",
																	 "Mizar","Megrez","LHS 331","STF 1774","Alioth","78 Ursae Majoris","He Bo"};
	
	private static String[] Koala = {"G 165-13","Chaac","Nicollos","Tellus","LHS 2430","Nanditi","Boreas","LHS 2936","Gateway",
																	 "Nzambassee"};

	private static String[] HIP55118 = {"Seedi","LP 431-71","Atius","Vincika","81 Leonis","Iyati","Mehit","HIP 53898","60 b Leonis","Arawotyan","HIP 55118"};

	private static String[] LTT14478 = {"HO Bootis","Dea Motrona","Berkanan","G 167-35","AC +26 37030","G 166-21","LHS 2948","LHS 2931","Gliese 563.1",
																		  "45 c Bootis","LFT 1231","LHS 2925","Lalande 27055","LTT 14478","Jinicoch","Eta Coronae Borealis"};
	
	private static String[] MongKung = {"Asherah", "Xevioso", "LP 499-54", "Ross 835", "BD+23 2640"};


	private static String[] allTracked = Stream.of(trackedSystems,  Arany, Tricorii, Tikurua, ThreeUO, Helvetitj, Koala, HIP55118, LTT14478, MongKung)
																						 .flatMap(Stream::of).toArray(String[]::new);

	private static String[] SanTu = {"Wolf 393","LHS 295","LHS 304","LFT 698","Nahundi","Wolf 369","Helios","LP 493-64",
																	 "San Tu","LP 672-42","Zavijah","LP 732-94","LP 673-13","LHS 2471","Al Mina","LHS 2259",
																	 "LFT 709","Pangluya"};
	
	private static String[] Ining = {"Ining", "Shottunche", "Yucan","Rusani","LTT 11958", "Malpar Paha", "HR 2365", "HIP 34024", 
																	 "HIP 36954", "Loharii", "Bango", "LHS 2001", "Songlalis", "HIP 35274", "BD+73 447",  "Turmatis",
																	 "Bielegua", "Haritis"};

	private static HashSet <String> systemLookup = new HashSet<String>(Arrays.asList(allTracked));
	
	public static boolean isTracked (String system)  {
		return (systemLookup.contains(system));
	}
}
