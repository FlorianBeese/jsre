package com.jsre.engine.internal.scriptengine.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import com.jsre.engine.impl.SecurityClassFilter;
import com.jsre.engine.internal.scriptengine.JavaScriptEngine;
import com.jsre.engine.internal.scriptengine.JavaScriptEngineFactory;
import com.jsre.monitoring.PerformanceMarkerMgr;


public class JavaScriptEngineFactoryImpl implements JavaScriptEngineFactory {

	private PerformanceMarkerMgr performanceMarkerMgr = null;
	private boolean enableSecurity = false;

	@Override
	public void registerPerformanceMarkerMgr(PerformanceMarkerMgr mgr) {
		performanceMarkerMgr = mgr;
	}

	@Override
	public void enableStandardSecurity(boolean enableSecurity) {
		this.enableSecurity = enableSecurity;
	}

	@Override
	public JavaScriptEngine getEngine() {
		ScriptEngine engine = null;
		if (!enableSecurity) {
			if (performanceMarkerMgr != null) {
				performanceMarkerMgr.addMarker("getting engine factory");
			}
			ScriptEngineManager manager = new ScriptEngineManager();
			if (performanceMarkerMgr != null) {
				performanceMarkerMgr.addMarker("getting engine");
			}
			engine = manager.getEngineByName("JavaScript");
			return wrapEngine(engine);
		}

		if (performanceMarkerMgr != null) {
			performanceMarkerMgr.addMarker("getting engine factory");
		}
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngineFactory factory = null;
		for (ScriptEngineFactory f : manager.getEngineFactories()) {
			if (f == null) {
				continue;
			}
			if (f.getClass().getSimpleName().equals("NashornScriptEngineFactory")) {
				factory = f;
				break;
			}
			else {
				factory = f;
			}
		}
		if (performanceMarkerMgr != null) {
			performanceMarkerMgr.addMarker("getting engine");
		}
		if (factory instanceof jdk.nashorn.api.scripting.NashornScriptEngineFactory) {
			jdk.nashorn.api.scripting.NashornScriptEngineFactory nashorn = (jdk.nashorn.api.scripting.NashornScriptEngineFactory) factory;
			engine = nashorn.getScriptEngine(new SecurityClassFilter());
		}
		else {
			engine = factory.getScriptEngine();
			// TODO unset / remove java access objects
			// http://stackoverflow.com/questions/20793089/secure-nashorn-js-execution
		}
		return wrapEngine(engine);
	}

	public JavaScriptEngine wrapEngine(ScriptEngine engine) {
		return new JavaScriptEngineImpl(engine);
	}
}
