module rocks.jsre1 {
    requires java.base;
    requires jdk.scripting.nashorn;
	requires org.apache.commons.lang3;
	//TODO rename gson module, when fix is released
	//https://github.com/google/gson/issues/1315
	requires gson;
	requires com.google.common;
    
    exports rocks.jsre;
    exports rocks.jsre.builder;
    exports rocks.jsre.cache;
    exports rocks.jsre.configuration;
    exports rocks.jsre.configuration.converter;
    exports rocks.jsre.exception;
    exports rocks.jsre.monitoring;
}