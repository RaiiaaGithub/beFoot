package com.example.befoot.modules.sqlite.helpers;

import android.database.sqlite.SQLiteDatabase;

import com.example.befoot.modules.apiClient.interfaces.Deserializer;

import java.lang.reflect.Field;
import java.util.Arrays;

public class DbHelper {

    private final SQLiteDatabase db;

    public DbHelper(SQLiteDatabase db) {
        this.db = db;
    }

    public <T extends Deserializer> void add(final Class<T> clazz) {
        String tableName = clazz.getSimpleName();
        Field[] columns = clazz.getFields();

        String query = "CREATE TABLE " + tableName + " (";
        for (int i = 0; i < columns.length; i++) {
            Field column = columns[i];

            if (column.getName().equals("id")) {
                query += column.getName() + " " + getColumnTypeName(column);
            }

            query += column.getName() + " " + getColumnTypeName(column);

            if (i == columns.length - 1) {
                query += ");";
            }
        }
        this.db.execSQL(query);
    }
    public <T extends Deserializer> void drop(final Class<T> clazz) {
        String tableName = clazz.getSimpleName();

        String query = "DROP TABLE IF EXISTS " + tableName;
        this.db.execSQL(query);
    }

    private String getColumnTypeName(Field column) {
        String columnType = column.getType().getTypeName().toUpperCase();
        switch (columnType) {
            case "STRING":
                return "TEXT";
            case "INT":
                return "INTEGER";
            case "DOUBLE":
            case "FLOAT":
                return "REAL";
            default:
                return "BLOB";
        }
    }

}
