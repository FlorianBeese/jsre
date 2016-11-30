package com.jsre.configuration.converter;


public interface JsonConverter<T> {

    public T fromJson(String json);

    public String toJson(T object);
}
