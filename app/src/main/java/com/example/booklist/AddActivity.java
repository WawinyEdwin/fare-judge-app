package com.example.booklist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    EditText user_id_input, establishment_name_input, establishment_type_input, food_served_input, location_input, review_input;
//    AutoCompleteTextView  establishment_name_input;
    Button add_button;
    SQLiteDatabase db;


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

//        establishment_name_input = (AutoCompleteTextView) findViewById(R.id.establishment_name_input);
//        MyDatabaseHelper myDB = new MyDatabaseHelper(this);
//        db = myDB.getReadableDatabase();
//
//        final String [] mydata;
//        ArrayList<String> array = new ArrayList<>();
//
//        String sql = "SELECT establishment_name FROM reviews";
//        //exec query
//        Cursor cr = db.rawQuery(sql, null);
//
////        mydata = new String[cr.getCount()]; //array string based on data
//
//        while(cr.moveToNext()) {
//            cr.getString(cr.getColumnIndex('establishment_name'));
//        }
//
//        //set the adapter to AutoCompleteTextView
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mydata);
//
//        //populate the list to the controls
//        establishment_name_input.setAdapter(adapter);



        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_id_input.getText().toString().isEmpty() || establishment_name_input.getText().toString().isEmpty() ||
                        establishment_type_input.getText().toString().isEmpty() || review_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                } else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(user_id_input.getText().toString().trim(),
                            establishment_name_input.getText().toString().trim(),
                            establishment_type_input.getText().toString().trim(),
                            food_served_input.getText().toString().trim(),
                            location_input.getText().toString().trim(),
                            review_input.getText().toString().trim());
                }
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //inflate the menu: add items to the action bar
//        getMenuInflater().inflate(R.menu.menu_main);
//        return super.onCreateOptionsMenu(menu);
//    }
}