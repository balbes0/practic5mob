package com.example.practic55;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText descriptionEditText;
    private Button createButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        createButton = findViewById(R.id.create_button);
        dbHelper = new DatabaseHelper(this);

        createButton.setOnClickListener(v -> {
            createState();
        });
    }

    private void createState(){
        String newState = nameEditText.getText().toString();
        String newDescr = descriptionEditText.getText().toString();

        if (!newState.isEmpty() && !newDescr.isEmpty()){
            dbHelper.addState(newState, newDescr);
            goHome();
        }
    }

    private void goHome(){
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}