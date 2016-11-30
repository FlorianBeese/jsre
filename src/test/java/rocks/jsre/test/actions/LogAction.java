package com.jsre.test.actions;

import com.jsre.action.ParameterizedAction;
import com.jsre.test.RuleEngineTest;


public class LogAction implements ParameterizedAction<String> {

	@Override
	public void execute(String param) {
		// System.out.println("Executing LogAction with param '" + param + "'");
		RuleEngineTest.logActionExecuted = true;
	}

}
