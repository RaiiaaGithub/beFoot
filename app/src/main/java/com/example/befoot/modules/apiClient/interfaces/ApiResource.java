package com.example.befoot.modules.apiClient.interfaces;

public interface ApiResource<T> extends Deserializer<T>, Serializer {
    int getId();
}
