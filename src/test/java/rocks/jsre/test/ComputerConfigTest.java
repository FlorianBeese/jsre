package rocks.jsre.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Map;

import javax.script.ScriptException;

import org.junit.Test;

import rocks.jsre.TypedRuleEngine;
import rocks.jsre.builder.RuleEngineBuilder;
import rocks.jsre.exception.InputValidationException;
import rocks.jsre.test.configuration.model.ComputerConverterProvider;
import rocks.jsre.test.configuration.model.ComputerDocument;
import rocks.jsre.test.util.ExecutionHelper;
import rocks.jsre.test.util.InputHelper;
import rocks.jsre.test.util.ResourceFileHelper;


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
