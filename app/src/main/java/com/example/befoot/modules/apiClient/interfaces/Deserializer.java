package com.example.befoot.modules.apiClient.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface Deserializer<T> {
    public T deserialize(JSONObject object) throws JSONException;
}
