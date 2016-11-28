package com.jsre.configuration.converter;

import com.jsre.configuration.Configuration;
import com.jsre.configuration.Document;


// TODO change interface to this one
@SuppressWarnings("rawtypes")
public interface JsonConverterProviderNew {

	public <TConf extends Configuration> JsonConverter<TConf> getConfigurationConverter();

	public <TDoc extends Document> JsonConverter<TDoc> getDocumentConverter();

}
