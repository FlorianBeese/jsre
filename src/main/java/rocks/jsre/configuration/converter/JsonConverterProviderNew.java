package rocks.jsre.configuration.converter;

import rocks.jsre.configuration.Configuration;
import rocks.jsre.configuration.Document;


// TODO change interface to this one
@SuppressWarnings("rawtypes")
public interface JsonConverterProviderNew {

	public <TConf extends Configuration> JsonConverter<TConf> getConfigurationConverter();

	public <TDoc extends Document> JsonConverter<TDoc> getDocumentConverter();

}
