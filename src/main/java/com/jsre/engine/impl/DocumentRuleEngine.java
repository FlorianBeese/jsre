package com.jsre.engine.impl;

import com.jsre.TypedRuleEngine;
import com.jsre.configuration.Document;
import com.jsre.configuration.converter.JsonConverter;


public class DocumentRuleEngine extends AbstractRuleEngine implements TypedRuleEngine<Document> {

	@Override
	public Document getDocument() {
		String doc = getJsonDocument();
		JsonConverter<Document> converter = getConverterProvider().getConverter(Document.class);
		Document d = converter.fromJson(doc);
		return d;
	}

}
