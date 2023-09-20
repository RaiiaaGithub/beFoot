package com.example.befoot.modules.shared.interfaces;

import androidx.annotation.NonNull;

import com.example.befoot.modules.apiClient.interfaces.ApiResource;
import com.example.befoot.modules.apiClient.interfaces.Deserializer;
import com.example.befoot.modules.apiClient.interfaces.Serializer;

public interface DataServices {

    public <T extends Deserializer> T get(T objResponse, String url);
    public <T extends Deserializer> T get(T objResponse, int id, String url);
    public <T extends Serializer> void post(@NonNull T objRequest, String url);
    public <T extends ApiResource> T put(@NonNull T objRequest, String url);
    public <T extends ApiResource> T put(@NonNull T objRequest, int id, String url);
    public <T extends ApiResource> void delete(@NonNull T objRequest, String url);
    public <T extends Serializer> void delete(int id, String url);
}
