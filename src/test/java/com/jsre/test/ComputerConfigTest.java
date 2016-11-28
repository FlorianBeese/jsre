package com.jsre.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Map;

import javax.script.ScriptException;

import org.junit.Test;

import com.jsre.TypedRuleEngine;
import com.jsre.builder.RuleEngineBuilder;
import com.jsre.exception.InputValidationException;
import com.jsre.test.configuration.model.ComputerConverterProvider;
import com.jsre.test.configuration.model.ComputerDocument;
import com.jsre.test.lib.ExecutionHelper;
import com.jsre.test.lib.InputHelper;
import com.jsre.test.lib.ResourceFileHelper;


public class ComputerConfigTest {

	@Test
	public void testConverterProvider() throws IOException, ScriptException, InputValidationException {
		String json = ResourceFileHelper.getFileContent("cpuconfig.json");

		RuleEngineBuilder reb = new RuleEngineBuilder();
		reb.withJsonConverterProvider(new ComputerConverterProvider());
		reb.withJsonConfiguration(json);

		TypedRuleEngine<ComputerDocument> re = reb.buildTyped(ComputerDocument.class);
		assertNotNull(re);
		// execute(re, getRequest());
	}

	@Test
	public void typedTest() throws Exception {
		ExecutionHelper.execEngineWithComputerDocument("cpuconfig.json", InputHelper.getComputerConfigurationInput());
	}

	@Test
	public void testInputValidationTypes() throws ScriptException {
		Map<String, Object> input = InputHelper.getComputerConfigurationInput();
		input.put("ghzOption", new Integer(1));
		ExecutionHelper.execEngineWithComputerDocumentInputValidationError("cpuconfig.json", input, "ghzOption");
	}

}
