package com.example.befoot.modules.apiClient.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.befoot.modules.apiClient.interfaces.ApiResource;
import com.example.befoot.modules.apiClient.interfaces.Deserializer;
import com.example.befoot.modules.apiClient.interfaces.Serializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.Date;

public class Player implements ApiResource<Player> {

    private int id;
    private String name;
    private int position;
    private Date birthday;
    private Time totalTimePlayed;
    private int totalGoals;
    private double score;

    public Player(int id, String name, int position, Date birthday, Time totalTimePlayed, int totalGoals, double score) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.birthday = birthday;
        this.totalTimePlayed = totalTimePlayed;
        this.totalGoals = totalGoals;
        this.score = score;
    }

    public Player() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Time getTotalTimePlayed() {
        return totalTimePlayed;
    }

    public void setTotalTimePlayed(Time totalTimePlayed) {
        this.totalTimePlayed = totalTimePlayed;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public Player deserialize(JSONObject object) throws JSONException {
        this.id = object.getInt("id");
        this.name = object.getString("name");
        this.position = object.getInt("position");
        this.birthday = (Date) object.get("birthday");
        this.score = object.getDouble("score");
        this.totalGoals = object.getInt("totalGoals");
        this.totalTimePlayed = (Time) object.get("totalTimePlayed");
        return this;
    }

    @Override
    public JSONObject serialize() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("name", this.name);
        object.put("position", this.position);
        object.put("birthday", this.birthday);
        object.put("score", this.score);
        object.put("totalGoals", this.totalGoals);
        object.put("totalTimePlayed", this.totalTimePlayed);
        return object;
    }

    public static class Positions {
        public final static int GOALKEEPER = 0;
        public final static int LEFT_BACK = 1;
        public final static int CENTRAL_BACK = 2;
        public final static int RIGHT_BACK = 3;
        public final static int DEFENSIVE_MIDFIELDER = 4;
        public final static int LEFT_MIDFIELDER = 5;
        public final static int CENTRAL_MIDFIELDER = 6;
        public final static int RIGHT_MIDFIELDER = 7;
        public final static int ATTACKING_MIDFIELDER = 8;
        public final static int LEFT_WING = 9;
        public final static int RIGHT_WING = 10;
        public final static int STRIKER = 11;

        public static String getPositionName(int position) {
            switch(position) {
                case 0:
                    return "GK";
                case 1:
                    return "LB";
                case 2:
                    return "CB";
                case 3:
                    return "RB";
                case 4:
                    return "DM";
                case 5:
                    return "LM";
                case 6:
                    return "CM";
                case 7:
                    return "RM";
                case 8:
                    return "AM";
                case 9:
                    return "LW";
                case 10:
                    return "RW";
                case 11:
                    return "ST";
                default:
                    return "Error";
            }
        }
    }
}
