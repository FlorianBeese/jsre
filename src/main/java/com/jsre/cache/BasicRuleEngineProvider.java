package com.jsre.cache;

import java.io.IOException;

import com.jsre.RuleEngine;
import com.jsre.builder.RuleEngineBuilder;
import com.jsre.configuration.Configuration;
import com.jsre.configuration.converter.JsonConverter;
import com.jsre.configuration.converter.JsonConverterProvider;
import com.jsre.configuration.converter.impl.JsonDocumentConverterProvider;

public abstract class BasicRuleEngineProvider implements RuleEngineProvider {

	JsonConverterProvider converterProvider = new JsonDocumentConverterProvider();

	@SuppressWarnings("rawtypes")
	@Override
	public Configuration deserialize(String json) {
		JsonConverter<Configuration> converter = converterProvider.getConverter(Configuration.class);
		Configuration configuration = converter.fromJson(json);
		return configuration;
	}

	public abstract String getConfigurationContent(String filename) throws IOException;

	@Override
	public RuleEngine getEngine(@SuppressWarnings("rawtypes") Configuration configuration) {
		RuleEngineBuilder reb = new RuleEngineBuilder();
		reb.withConfiguration(configuration);
		reb.withJsonConverterProvider(converterProvider);
		RuleEngine ruleEngine = reb.buildUntyped();
		configureEngine(ruleEngine);
		return ruleEngine;
	}

	protected abstract void configureEngine(RuleEngine ruleEngine);

}
