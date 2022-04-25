package com.example.booklist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText user_id_input, establishment_name_input, establishment_type_input, food_served_input, location_input, review_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        user_id_input = findViewById(R.id.user_id_input);
        establishment_name_input = findViewById(R.id.establishment_name_input);
        establishment_type_input = findViewById(R.id.establishment_type_input);
        food_served_input = findViewById(R.id.food_served_input);
        location_input = findViewById(R.id.location_input);
        review_input = findViewById(R.id.review_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(user_id_input.getText().toString().trim(),
                        establishment_name_input.getText().toString().trim(),
                        establishment_type_input.getText().toString().trim(),
                        food_served_input.getText().toString().trim(),
                        location_input.getText().toString().trim(),
                        review_input.getText().toString().trim());
            }
        });
    }
}