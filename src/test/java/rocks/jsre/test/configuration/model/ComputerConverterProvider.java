package rocks.jsre.test.configuration.model;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import rocks.jsre.configuration.Configuration;
import rocks.jsre.configuration.Document;
import rocks.jsre.configuration.converter.JsonConverter;
import rocks.jsre.configuration.converter.JsonConverterProvider;
import rocks.jsre.configuration.converter.impl.JsonHelper;
import rocks.jsre.configuration.impl.BasicConfiguration;


public class ComputerConverterProvider implements JsonConverterProvider {

	@SuppressWarnings("rawtypes")
	private Type getClassType(final Class interfaceType) {
		if (interfaceType == Configuration.class) {
			return new TypeToken<BasicConfiguration<ComputerDocument>>() {}.getType();
		}
		if (interfaceType == Document.class) {
			return new TypeToken<ComputerDocument>() {}.getType();
		}
		throw new UnsupportedOperationException("...");
	}

	public <T> JsonConverter<T> getConverter(final Class<T> interfaceType) {
		final Type classType = getClassType(interfaceType);

		return new JsonConverter<T>() {

			@SuppressWarnings("unchecked")
			public T fromJson(final String json) {
				return (T) JsonHelper.fromJson(json, classType);
			}

			public String toJson(final T object) {
				return JsonHelper.toJson(object);
			}
		};
	}
}
