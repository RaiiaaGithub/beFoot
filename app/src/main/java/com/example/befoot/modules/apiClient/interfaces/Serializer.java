package com.example.befoot.modules.apiClient.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface Serializer {
    public JSONObject serialize() throws JSONException;
}
