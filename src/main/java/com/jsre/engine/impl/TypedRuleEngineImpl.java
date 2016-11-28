package com.jsre.engine.impl;

import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.jsre.TypedRuleEngine;
import com.jsre.action.Action;
import com.jsre.configuration.Configuration;
import com.jsre.configuration.Document;
import com.jsre.configuration.converter.JsonConverterProvider;
import com.jsre.exception.InputValidationException;
import com.jsre.monitoring.PerformanceMarker;


/**
 * The TypedRuleEngine is a typed wrapper around the untyped DocumentRuleEngine.
 * 
 * @author Florian Beese
 *
 * @param <TDoc> The java type of the document. This class is created by
 * yourself.
 */

public class TypedRuleEngineImpl<TDoc extends Document> implements TypedRuleEngine<TDoc> {

	TypedRuleEngine<TDoc> ruleEngine = null;

	@SuppressWarnings("unchecked")
	protected TypedRuleEngineImpl() {
		ruleEngine = (TypedRuleEngine<TDoc>) new DocumentRuleEngine();
	}

	public void setJsonConfiguration(String jsonConfig) {
		ruleEngine.setJsonConfiguration(jsonConfig);
	}

	@Override
	public void setConfiguration(@SuppressWarnings("rawtypes") Configuration configuration) {
		ruleEngine.setConfiguration(configuration);
	}

	@Override
	public void setConverterProvider(JsonConverterProvider converterProdiver) {
		ruleEngine.setConverterProvider(converterProdiver);
	}

	@Override
	public void registerAction(String name, Action action) {
		ruleEngine.registerAction(name, action);
	}

	@Override
	public void setInput(Map<String, Object> input) throws InputValidationException {
		ruleEngine.setInput(input);
	}

	@Override
	public void setInputStrings(Map<String, String> input) throws InputValidationException {
		ruleEngine.setInputStrings(input);
	}

	@Override
	public void executeRules() throws ScriptException {
		ruleEngine.executeRules();
	}

	@Override
	public String getJsonDocument() {
		return ruleEngine.getJsonDocument();
	}

	@Override
	public String getJsonDocumentPrettyPrinted() {
		return ruleEngine.getJsonDocumentPrettyPrinted();
	}

	@Override
	public TDoc getDocument() {
		return ruleEngine.getDocument();
	}

	@Override
	public void enablePerformanceMonitoring() {
		ruleEngine.enablePerformanceMonitoring();
	}

	@Override
	public void disablePerformanceMonitoring() {
		ruleEngine.disablePerformanceMonitoring();
	}

	@Override
	public List<PerformanceMarker> getPerformanceMonitoring() {
		return ruleEngine.getPerformanceMonitoring();
	}

	@Override
	public void printPerformanceMonitoring() {
		ruleEngine.printPerformanceMonitoring();
	}

	@Override
	public String getJsonInput() {
		return ruleEngine.getJsonInput();
	}

	@Override
	public String getJsonInputPrettyPrinted() {
		return ruleEngine.getJsonInputPrettyPrinted();
	}

	@Override
	public void enableSecurity() {
		ruleEngine.enableSecurity();
	}

	@Override
	public void disableSecurity() {
		ruleEngine.disableSecurity();
	}

}
