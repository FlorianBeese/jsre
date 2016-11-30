package com.jsre.configuration.converter.impl;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.jsre.configuration.Configuration;
import com.jsre.configuration.Document;
import com.jsre.configuration.converter.JsonConverter;
import com.jsre.configuration.converter.JsonConverterProvider;
import com.jsre.configuration.impl.BasicConfiguration;
import com.jsre.configuration.impl.BasicDocument;


public class BasicConverterProvider implements JsonConverterProvider {

	@SuppressWarnings("rawtypes")
	private Type getClassType(final Class interfaceType) {
		if (interfaceType == Configuration.class) {
			return new TypeToken<BasicConfiguration<BasicDocument>>() {}.getType();
		}
		if (interfaceType == Document.class) {
			return new TypeToken<BasicDocument>() {}.getType();
		}
		throw new UnsupportedOperationException("...");
	}

	@Override
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
