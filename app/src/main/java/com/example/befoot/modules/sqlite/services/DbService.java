package com.example.befoot.modules.sqlite.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.befoot.modules.apiClient.interfaces.ApiResource;
import com.example.befoot.modules.apiClient.interfaces.Deserializer;
import com.example.befoot.modules.apiClient.interfaces.Serializer;
import com.example.befoot.modules.apiClient.models.Organization;
import com.example.befoot.modules.apiClient.models.Player;
import com.example.befoot.modules.apiClient.models.Team;
import com.example.befoot.modules.shared.helpers.ModelHelper;
import com.example.befoot.modules.shared.interfaces.DataServices;
import com.example.befoot.modules.sqlite.helpers.DbHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class DbService extends SQLiteOpenHelper implements DataServices {

    private final static String DB_NAME = "BeFootDB";
    private final static int DB_VERSION = 1;

    public DbService(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        DbHelper dbHelper = new DbHelper(sqLiteDatabase);
        dbHelper.add(Organization.class);
        dbHelper.add(Team.class);
        dbHelper.add(Player.class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        DbHelper dbHelper = new DbHelper(sqLiteDatabase);
        dbHelper.drop(Organization.class);
        dbHelper.drop(Team.class);
        dbHelper.drop(Player.class);

        onCreate(sqLiteDatabase);
    }


    public <T extends Deserializer> T get(T objResponse, String url) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = ModelHelper.getFieldsNames(objResponse.getClass());

        SQLiteCursor cursor = (SQLiteCursor)db.query(
                objResponse.getClass().getSimpleName(),
                null,
                null,
                null,
                null,
                null,
                null
        );

        JSONObject object = new JSONObject();

        while(cursor.moveToNext()) {
            try {
                object.put(cursor.getColumnName(cursor.getPosition()), getFromCursor(cursor));
            } catch (JSONException e) {
                e.printStackTrace();
                return objResponse;
            }
        }

        return objResponse;
        // SELECT * FROM table
    }

    // TODO: FINISH IMPLEMENTING THE METHODS
    public <T extends Deserializer> T get(T objResponse, int id, String url) {
        return null;
        // SELECT * FROM table WHERE id = id
    }

    public <T extends Serializer> void post(@NonNull T objRequest, String url) {
        // INSERT INTO table (columnNames...) VALUE (columnValues...) (columnValues...)
    }

    public <T extends ApiResource> T put(@NonNull T objRequest, String url) {
        return null;
        // UPDATE table SET column1 = new_value1, column2 = newValue2 WHERE id = id
    }

    public <T extends ApiResource> T put(@NonNull T objRequest, int id, String url) {
        // UPDATE table SET column1 = new_value1, column2 = newValue2 WHERE id = id
        return null;
    }

    public <T extends ApiResource> void delete(@NonNull T objRequest, String url) {
        // DELETE FROM table WHERE id = id
    }

    public <T extends Serializer> void delete(int id, String url) {
        // DELETE FROM table WHERE id = id
    }

    private Object getFromCursor(SQLiteCursor cursor) {
        int cursorPosition = Math.max(cursor.getPosition(), 0);
        switch (cursor.getType(cursorPosition)) {
            case SQLiteCursor.FIELD_TYPE_INTEGER:
                return cursor.getInt(cursorPosition);
            case SQLiteCursor.FIELD_TYPE_FLOAT:
                return (double) cursor.getFloat(cursorPosition);
            case SQLiteCursor.FIELD_TYPE_STRING:
                return cursor.getString(cursorPosition);
            case SQLiteCursor.FIELD_TYPE_BLOB:
                return cursor.getBlob(cursorPosition);
            default:
                if (cursor.isNull(cursorPosition)) {
                    return null;
                }
                return (Object) cursor;
        }
    }
}
