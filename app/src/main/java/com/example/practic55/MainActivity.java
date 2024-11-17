package com.example.practic55;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<State> states;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем экземпляр DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Инициализация данных из базы данных
        setInitialData();

        RecyclerView recyclerView = findViewById(R.id.list);

        // Создаем адаптер с обработчиком кликов
        StateAdapter adapter = new StateAdapter(this, states, id -> {
            showDetails(id);
        });

        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

    }

    private void showDetails(int id){
        // Создаем Intent для перехода к DetailsActivity
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("STATE_ID", id); // Передаем ID выбранной страны
        startActivity(intent); // Запускаем DetailsActivity
    }

    private void setInitialData() {
        states = new ArrayList<>();

        // Открыть базу данных для чтения
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Получить все записи
        Cursor cursor = dbHelper.getAllStates();
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                        String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION));

                        // Добавить объект State в список
                        states.add(new State(id, name, description));
                    } while (cursor.moveToNext());
                }
            } finally {
                // Закрыть курсор
                cursor.close();
            }
        }
    }
}

