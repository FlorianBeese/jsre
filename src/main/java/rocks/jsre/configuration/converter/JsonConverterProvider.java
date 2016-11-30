package rocks.jsre.configuration.converter;

public interface JsonConverterProvider {

	public <T> JsonConverter<T> getConverter(final Class<T> interfaceType);

}
