package rocks.jsre.test;

import java.util.Map;

import javax.script.ScriptException;

import org.junit.Test;

import rocks.jsre.RuleEngine;
import rocks.jsre.builder.RuleEngineFactory;
import rocks.jsre.exception.InputValidationException;
import rocks.jsre.test.actions.LogAction;
import rocks.jsre.test.util.InputHelper;
import rocks.jsre.test.util.ResourceFileHelper;


public class PerformanceMonitoringTest {

	private static final String TEST_CONFIGURATION = "cpuconfig.json";

	@Test
	public void test() throws Exception {
		Map<String, Object> input = InputHelper.getTestDocumentInput();
		String json = ResourceFileHelper.getFileContentNoException("testconfig.json");
		RuleEngine re = RuleEngineFactory.getPerformanceMonitoredEngine(json);
		re.registerAction("LogAction", new LogAction());

		re.setInput(input);
		re.executeRules();
		re.printPerformanceMonitoring();

		// reset monitoring data:
		re.enablePerformanceMonitoring();

		re.setInput(input);
		re.executeRules();
		re.printPerformanceMonitoring();
	}

	@Test
	public void testOtherConfiguration() throws Exception {
		Map<String, Object> input = InputHelper.getComputerConfigurationInput();
		String json = ResourceFileHelper.getFileContentNoException(TEST_CONFIGURATION);
		RuleEngine re = RuleEngineFactory.getPerformanceMonitoredEngine(json);

		re.setInput(input);
		re.executeRules();
		re.printPerformanceMonitoring();

		// reset monitoring data:
		re.enablePerformanceMonitoring();

		re.setInput(input);
		re.executeRules();
		re.printPerformanceMonitoring();
	}

	@Test
	public void testMultipleRuns() throws Exception {
		Map<String, Object> input = InputHelper.getComputerConfigurationInput();
		String json = ResourceFileHelper.getFileContentNoException(TEST_CONFIGURATION);
		RuleEngine re = RuleEngineFactory.getEngine(json);
		measureMultipleRuns(re, input, 100);
	}

	@Test
	public void testMultipleRunsWithWarming() throws Exception {
		Map<String, Object> input = InputHelper.getComputerConfigurationInput();
		String json = ResourceFileHelper.getFileContentNoException(TEST_CONFIGURATION);
		RuleEngine re = RuleEngineFactory.getEngine(json);
		re.setInput(input);
		re.executeRules();
		measureMultipleRuns(re, input, 100);
	}

	private void measureMultipleRuns(RuleEngine re, Map<String, Object> input, int runCount) throws InputValidationException, ScriptException {
		long totalRuntime = 0;
		long minTime = 1000000000;
		int minRun = 0;
		long maxTime = 0;
		int maxRun = 0;
		long secondMaxTime = 0;
		int secondMaxRun = 0;
		long[] allTimes = new long[runCount];

		for (int i = 1; i <= runCount; i++) {
			long start = System.nanoTime();

			re.setInput(input);
			re.executeRules();

			long executionTime = System.nanoTime() - start;
			if (executionTime < minTime) {
				minTime = executionTime;
				minRun = i;
			}
			if (executionTime > maxTime) {
				maxTime = executionTime;
				maxRun = i;
			}
			if (executionTime > secondMaxTime && executionTime < maxTime) {
				secondMaxTime = executionTime;
				secondMaxRun = i;
			}
			totalRuntime += executionTime;
			allTimes[i - 1] = executionTime;
		}

		System.out.println("Execution statistics: ");
		System.out.println("Runs: " + runCount);
		System.out.println("Total time: " + nanoToMilli(totalRuntime) + " ms");
		System.out.println("Avg time: " + nanoToMilli(totalRuntime / runCount) + " ms");
		System.out.println("Min time: " + nanoToMilli(minTime) + " ms (in run: " + minRun + ")");
		System.out.println("Max time: " + nanoToMilli(maxTime) + " ms (in run: " + maxRun + ")");
		System.out.println("2nd Max time: " + nanoToMilli(secondMaxTime) + " ms (in run: " + secondMaxRun + ")");

		System.out.print("All times [");
		for (int i = 0; i < allTimes.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(Math.round(nanoToMilli(allTimes[i])));
		}
		System.out.println("]");

		// re.enablePerformanceMonitoring();
		// re.setInput(input);
		// re.executeRules();
		// re.printPerformanceMonitoring();
	}

	private double nanoToMilli(long nanoSeconds) {
		return (double) (nanoSeconds / 1000) / 1000;
	}

}
