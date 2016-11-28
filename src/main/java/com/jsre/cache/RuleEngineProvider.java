package com.jsre.cache;

import java.io.IOException;

import com.jsre.RuleEngine;
import com.jsre.configuration.Configuration;

public interface RuleEngineProvider {

	public String getConfigurationContent(String filename) throws IOException;

	@SuppressWarnings("rawtypes")
	public Configuration deserialize(String json);

	@SuppressWarnings("rawtypes")
	public RuleEngine getEngine(Configuration configuration);
}
