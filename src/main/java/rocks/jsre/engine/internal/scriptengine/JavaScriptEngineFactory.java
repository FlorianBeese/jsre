package rocks.jsre.engine.internal.scriptengine;

import rocks.jsre.monitoring.PerformanceMarkerMgr;


public interface JavaScriptEngineFactory {

	public void enableStandardSecurity(boolean enableSecurity);

	public JavaScriptEngine getEngine();

	public void registerPerformanceMarkerMgr(PerformanceMarkerMgr mgr);
}
