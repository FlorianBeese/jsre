package rocks.jsre.builder;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.TypeUtils;

import rocks.jsre.RuleEngine;
import rocks.jsre.TypedRuleEngine;
import rocks.jsre.action.Action;
import rocks.jsre.configuration.Configuration;
import rocks.jsre.configuration.Document;
import rocks.jsre.configuration.converter.JsonConverterProvider;
import rocks.jsre.configuration.converter.impl.JsonDocumentConverterProvider;
import rocks.jsre.configuration.converter.impl.TypedConverterProvider;
import rocks.jsre.configuration.impl.BasicConfiguration;
import rocks.jsre.engine.impl.TypedRuleEngineImpl;
import rocks.jsre.engine.impl.UntypedRuleEngineImpl;


public class RuleEngineBuilder {

	@SuppressWarnings("rawtypes")
	Configuration configuration;
	String jsonConfiguration;
	JsonConverterProvider converterProvider;
	Map<String, Action> actions = null;
	Boolean performanceMonitoring = false;
	Boolean securityEnabled = false;

	public RuleEngineBuilder() {

	}

	public RuleEngineBuilder withSecurityEnabled() {
		securityEnabled = true;
		return this;
	}

	public RuleEngineBuilder withPerformanceMonitoring() {
		performanceMonitoring = true;
		return this;
	}

	public RuleEngineBuilder withConfiguration(@SuppressWarnings("rawtypes") Configuration configuration) {
		this.configuration = configuration;
		return this;
	}

	public RuleEngineBuilder withJsonConfiguration(String jsonConfiguration) {
		this.jsonConfiguration = jsonConfiguration;
		return this;
	}

	public RuleEngineBuilder withJsonConverterProvider(JsonConverterProvider converterProvider) {
		this.converterProvider = converterProvider;
		return this;
	}

	public RuleEngineBuilder withAction(String name, Action action) {
		if (this.actions == null) {
			this.actions = new HashMap<String, Action>();
		}
		this.actions.put(name, action);
		return this;
	}

	public RuleEngine buildUntyped() {
		UntypedRuleEngineImpl re = new UntypedRuleEngineImpl();
		if (performanceMonitoring) {
			re.enablePerformanceMonitoring();
		}
		if (securityEnabled) {
			re.enableSecurity();
		}

		if (this.converterProvider == null) {
			this.converterProvider = new JsonDocumentConverterProvider();
		}
		re.setConverterProvider(this.converterProvider);
		if (this.configuration != null) {
			re.setConfiguration(this.configuration);
		}
		else if (jsonConfiguration != null) {
			re.setJsonConfiguration(jsonConfiguration);
		}
		if (this.actions != null) {
			for (Map.Entry<String, Action> entry : this.actions.entrySet()) {
				re.registerAction(entry.getKey(), entry.getValue());
			}
		}
		return re;
	}

	public <TDoc extends Document> TypedRuleEngine<TDoc> buildTyped(Class<TDoc> docClass) {
		TypedRuleEngine<TDoc> re = new TypedRuleEngineImpl<TDoc>() {};
		if (performanceMonitoring) {
			re.enablePerformanceMonitoring();
		}
		if (securityEnabled) {
			re.enableSecurity();
		}

		if (this.converterProvider == null) {
			TypedConverterProvider<TDoc> prov = new TypedConverterProvider<TDoc>();
			prov.setDocumentType(docClass);
			ParameterizedType configType = TypeUtils.parameterize(BasicConfiguration.class, docClass);
			prov.setConfigurationType(configType);
			this.converterProvider = prov;
		}
		re.setConverterProvider(this.converterProvider);
		if (this.configuration != null) {
			re.setConfiguration(this.configuration);
		}
		else if (jsonConfiguration != null) {
			re.setJsonConfiguration(jsonConfiguration);
		}
		if (this.actions != null) {
			for (Map.Entry<String, Action> entry : this.actions.entrySet()) {
				re.registerAction(entry.getKey(), entry.getValue());
			}
		}

		return re;
	}
}
