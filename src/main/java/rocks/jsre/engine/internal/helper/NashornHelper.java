package rocks.jsre.engine.internal.helper;

import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import rocks.jsre.engine.internal.scriptengine.JavaScriptEngine;
import rocks.jsre.engine.internal.scriptengine.JavaScriptEngineFactory;
import rocks.jsre.engine.internal.scriptengine.impl.JavaScriptEngineFactoryImpl;

@SuppressWarnings("removal")
public final class NashornHelper {

	//before java 9 this value could be accessed via jdk.nashorn.internal.runtime.ScriptRuntime.UNDEFINED;
	//due to a design flaw this is not part of the public API and therefore the below workaround is needed
	private static Object undefined = null; 
	
	@SuppressWarnings("deprecation")
	public final static synchronized Object getUndefinedValue() {
		if (undefined == null) {
			JavaScriptEngineFactory factory = new JavaScriptEngineFactoryImpl();
			factory.enableStandardSecurity(false);
			JavaScriptEngine jsengine = factory.getEngine();
			
			try {
				
			    ScriptObjectMirror arrayMirror = (ScriptObjectMirror) jsengine.eval("[undefined]");
			    undefined = arrayMirror.getSlot(0);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return undefined;
	}
}
