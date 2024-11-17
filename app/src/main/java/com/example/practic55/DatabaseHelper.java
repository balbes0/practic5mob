package com.example.practic55;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "states.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "States"; // название таблицы в бд

    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "State";
    public static final String COLUMN_DESCRIPTION = "Description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем таблицу
        db.execSQL("CREATE TABLE " + TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_DESCRIPTION + " TEXT NOT NULL);");

        // Добавляем начальные данные
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") " +
                "VALUES ('Бразилия', 'Описание описание описание описание описание описание описание описание описание описание');");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") " +
                "VALUES ('Аргентина', 'Буэнос-Айрес');");
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ") " +
                "VALUES ('Чили', 'Сантьяго');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаляем таблицу при обновлении схемы
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // Метод для получения всех записей
    public Cursor getAllStates() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE, null, null, null, null, null, COLUMN_NAME + " ASC");
    }

    // Метод для добавления нового элемента
    public long addState(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        return db.insert(TABLE, null, values);
    }

    // Метод для получения записи по ID
    public Cursor getStateById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
    }

    // Метод для обновления записи
    public int updateState(int id, String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        return db.update(TABLE, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Метод для удаления записи
    public int deleteState(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
