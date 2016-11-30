package rocks.jsre.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.ParameterizedType;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.junit.Test;

import rocks.jsre.configuration.Configuration;
import rocks.jsre.configuration.Document;
import rocks.jsre.configuration.converter.JsonConverter;
import rocks.jsre.configuration.converter.impl.TypedConverterProvider;
import rocks.jsre.configuration.impl.BasicConfiguration;
import rocks.jsre.test.configuration.model.TestDocument;
import rocks.jsre.test.util.ResourceFileHelper;


public class ConverterTest {

	private String getTestDocJson() {
		return "{ 'test' : false, 'testText' : 'hello' }";
	}

	private String getTestConfigJson() {
		return ResourceFileHelper.getFileContentNoException("testconfig.json");
	}

	@Test
	public void test() {
		TypedConverterProvider<TestDocument> prov = new TypedConverterProvider<TestDocument>();
		ParameterizedType configType = TypeUtils.parameterize(BasicConfiguration.class, TestDocument.class);
		prov.setConfigurationType(configType);
		prov.setDocumentType(TestDocument.class);
		@SuppressWarnings("rawtypes")
		JsonConverter<Configuration> configConverter = prov.getConverter(Configuration.class);
		JsonConverter<Document> docConverter = prov.getConverter(Document.class);
		TestDocument testDoc = (TestDocument) docConverter.fromJson(getTestDocJson());
		assertFalse(testDoc.getTest());
		assertEquals("hello", testDoc.getTestText());

		@SuppressWarnings("unchecked")
		BasicConfiguration<TestDocument> testConfig = (BasicConfiguration<TestDocument>) configConverter.fromJson(getTestConfigJson());
		assertEquals("1.1", testConfig.getVersion());
		TestDocument testDoc2 = testConfig.getDocument();
		assertFalse(testDoc.getTest());
		assertEquals("no clue", testDoc2.getTestText());
	}
}
