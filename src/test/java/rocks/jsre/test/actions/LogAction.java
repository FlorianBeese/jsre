package rocks.jsre.test.actions;

import rocks.jsre.action.ParameterizedAction;
import rocks.jsre.test.RuleEngineTest;


public class LogAction implements ParameterizedAction<String> {

	@Override
	public void execute(String param) {
		// System.out.println("Executing LogAction with param '" + param + "'");
		RuleEngineTest.logActionExecuted = true;
	}

}
