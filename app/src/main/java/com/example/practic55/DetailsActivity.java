package com.example.practic55;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText descriptionEditText;
    private DatabaseHelper dbHelper;
    private Button editButton;
    private Button deleteButton;
    private int stateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Инициализация элементов интерфейса
        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        editButton = findViewById(R.id.edit_button);
        deleteButton = findViewById(R.id.delete_button);

        // Получение переданного ID из Intent
        stateID = getIntent().getIntExtra("STATE_ID", -1);

        // Инициализация базы данных
        dbHelper = new DatabaseHelper(this);

        // Загрузка данных по ID
        Cursor cursor = dbHelper.getStateById(stateID); //это убери
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION));

            // Устанавливаем текст в TextView
            nameEditText.setText(name);
            descriptionEditText.setText(description);
        }
        cursor.close();

        editButton.setOnClickListener(v -> {
            EditState();
        });

        deleteButton.setOnClickListener(v -> {
            DeleteState();
        });
    }

    private void EditState(){
        String newName = nameEditText.getText().toString();
        String newDescription = descriptionEditText.getText().toString();

        if (!newName.isEmpty() && !newDescription.isEmpty()){
            dbHelper.updateState(stateID, newName, newDescription);
            goMain();
        }
    }

    private void DeleteState(){
        dbHelper.deleteState(stateID);
        goMain();
    }

    private void goMain(){
        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
