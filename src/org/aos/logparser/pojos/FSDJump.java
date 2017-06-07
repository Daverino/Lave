package org.aos.logparser.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FSDJump {
	private MessageHeader header;
	private String schemaRef;
	private Message message;

}