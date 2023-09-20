package com.example.befoot.modules.apiClient.models;

import com.example.befoot.modules.apiClient.interfaces.ApiResource;
import com.example.befoot.modules.apiClient.interfaces.Deserializer;
import com.example.befoot.modules.apiClient.interfaces.Serializer;

import org.json.JSONException;
import org.json.JSONObject;


public class Organization implements ApiResource<Organization> {
    private int id;
    private String name;
    private String initials;
    private String image;

    public Organization(int id, String name, String initials, String image) {
        this.id = id;
        this.name = name;
        setInitials(initials);
        this.image = image;
    }

    public Organization() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        initials = initials.toUpperCase();

        if (initials.length() > 3) {
            this.initials = initials.substring(0, 2);
            return;
        }

        if (initials.length() < 2) {
            this.initials = new StringBuilder()
                    .append(initials.charAt(0))
                    .append(initials.charAt(0))
                    .toString();
            return;
        }
        this.initials = initials;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Organization deserialize(JSONObject object) throws JSONException {
        this.id = object.getInt("id");
        this.name = object.getString("name");
        setInitials(object.getString("initials"));
        return this;
    }

    @Override
    public JSONObject serialize() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("name", this.name);
        object.put("initials", this.initials);
        return object;
    }
}
