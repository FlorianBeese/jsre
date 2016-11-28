package com.jsre.test.configuration.model;

import java.util.Map;
import java.util.Map.Entry;

import com.jsre.configuration.Document;


public class ComputerDocument implements Document {

	// @JsonDeserialize(using = ArrayMapDeserializer.class)
	// @JsonSerialize(using = ArrayMapSerializer.class)
	Map<String, GHz> ghz;

	public Map<String, GHz> getGhz() {
		return ghz;
	}

	public ComputerDocument() {}

	@Override
	public String toString() {
		String msg = "";
		for (Entry<String, GHz> e : ghz.entrySet()) {
			String sGhz = e.getKey();
			GHz ghz = e.getValue();
			msg += sGhz + ":" + ghz.price + ", ";
		}
		return "indexes : {" + msg + "}";
	}
}
