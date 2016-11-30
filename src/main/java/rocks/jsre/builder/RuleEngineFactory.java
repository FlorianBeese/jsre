package com.jsre.builder;

import com.jsre.RuleEngine;
import com.jsre.TypedRuleEngine;
import com.jsre.configuration.Document;


public class RuleEngineFactory {

	public static RuleEngine getSecureEngine(String jsonConfiguration) {
		RuleEngineBuilder reb = new RuleEngineBuilder();
		reb.withJsonConfiguration(jsonConfiguration);
		reb.withSecurityEnabled();
		return reb.buildUntyped();
	}

	public static RuleEngine getEngine(String jsonConfiguration) {
		RuleEngineBuilder reb = new RuleEngineBuilder();
		reb.withJsonConfiguration(jsonConfiguration);
		return reb.buildUntyped();
	}

	public static <TDoc extends Document> TypedRuleEngine<TDoc> getTypedEngine(String jsonConfiguration, Class<TDoc> docType) {
		RuleEngineBuilder reb = new RuleEngineBuilder();
		reb.withJsonConfiguration(jsonConfiguration);
		return reb.buildTyped(docType);
	}

	public static RuleEngine getPerformanceMonitoredEngine(String jsonConfiguration) {
		RuleEngineBuilder reb = new RuleEngineBuilder();
		reb.withJsonConfiguration(jsonConfiguration);
		reb.withPerformanceMonitoring();
		return reb.buildUntyped();
	}
}
