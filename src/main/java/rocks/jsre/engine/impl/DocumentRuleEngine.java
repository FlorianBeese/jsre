package rocks.jsre.engine.impl;

import rocks.jsre.TypedRuleEngine;
import rocks.jsre.configuration.Document;
import rocks.jsre.configuration.converter.JsonConverter;


public class DocumentRuleEngine extends AbstractRuleEngine implements TypedRuleEngine<Document> {

	@Override
	public Document getDocument() {
		String doc = getJsonDocument();
		JsonConverter<Document> converter = getConverterProvider().getConverter(Document.class);
		Document d = converter.fromJson(doc);
		return d;
	}

}
