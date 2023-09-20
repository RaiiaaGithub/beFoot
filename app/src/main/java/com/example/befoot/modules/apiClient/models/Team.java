package com.example.befoot.modules.apiClient.models;

import com.example.befoot.modules.apiClient.interfaces.ApiResource;
import com.example.befoot.modules.apiClient.interfaces.Deserializer;
import com.example.befoot.modules.apiClient.interfaces.Serializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Team implements ApiResource<Team> {
    private int id;
    private String name;
    private int organizationId;
    private ArrayList<Integer> playersIds = new ArrayList<>();

    public Team(int id, String name, int organizationId) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.playersIds = playersIds;
    }

    public Team() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public ArrayList<Integer> getPlayersIds() {
        return playersIds;
    }

    public void setPlayersIds(ArrayList<Integer> playersIds) {
        this.playersIds = playersIds;
    }

    public void addPlayer(int playerId) {
        this.playersIds.add(playerId);
    }

    public void populatePlayersIds(List<Integer> playersIds) {
        for (int i = 0; i < playersIds.size(); i++) {
            this.playersIds.add(playersIds.get(i));
        }
    }

    @Override
    public Team deserialize(JSONObject object) throws JSONException {
        this.id = object.getInt("id");
        this.name = object.getString("name");
        this.organizationId = object.getInt("organizationId");
        JSONArray playersIdsArray = object.getJSONArray("playersIds");
        for (int i = 0; i < playersIdsArray.length(); i++) {
            this.playersIds.add(playersIdsArray.getInt(i));
        }
        return this;
    }

    @Override
    public JSONObject serialize() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("name", this.name);
        object.put("organizationId", this.organizationId);
        object.put("playersIds", this.playersIds);
        return object;
    }
}
