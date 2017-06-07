package org.aos.logparser.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageHeader {
	private String softwareVersion;
	private String gatewayTimestamp;
	private String softwareName;
	private String uploaderID;
	
}
