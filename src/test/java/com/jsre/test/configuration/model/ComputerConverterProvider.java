package com.jsre.test.configuration.model;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.jsre.configuration.Configuration;
import com.jsre.configuration.Document;
import com.jsre.configuration.converter.JsonConverter;
import com.jsre.configuration.converter.JsonConverterProvider;
import com.jsre.configuration.converter.impl.JsonHelper;
import com.jsre.configuration.impl.BasicConfiguration;


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
