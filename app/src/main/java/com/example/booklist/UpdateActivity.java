package com.example.booklist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText user_id_input, establishment_name_input, establishment_type_input, food_served_input, location_input, review_input;
    Button update_button, delete_button;
    String id, user_id, establishment_name, establishment_type, food_served, location, review;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        user_id_input = findViewById(R.id.user_id_input2);
        establishment_name_input = findViewById(R.id.establishment_name_input2);
        establishment_type_input = findViewById(R.id.establishment_type_input2);
        food_served_input = findViewById(R.id.food_served_input2);
        location_input = findViewById(R.id.location_input2);
        review_input = findViewById(R.id.review_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(establishment_name);
        }


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_id_input.getText().toString().isEmpty() || establishment_name_input.getText().toString().isEmpty() ||
                        establishment_type_input.getText().toString().isEmpty() || review_input.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                } else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                    user_id = user_id_input.getText().toString().trim();
                    establishment_name = establishment_name_input.getText().toString().trim();
                    establishment_type = establishment_type_input.getText().toString().trim();
                    food_served = food_served_input.getText().toString().trim();
                    location = location_input.getText().toString().trim();
                    review = review_input.getText().toString().trim();
                    myDB.updateData(id, user_id, establishment_name, establishment_type, food_served, location, review);
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    //get data from previous intent and set the data to our intent.
    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("user_id") && getIntent().hasExtra("establishment_name")
        && getIntent().hasExtra("establishment_type") && getIntent().hasExtra("food_served")
                && getIntent().hasExtra("location") && getIntent().hasExtra("review")) {
            //get intent data

            id = getIntent().getStringExtra("id");
            user_id = getIntent().getStringExtra("user_id");
            establishment_name = getIntent().getStringExtra("establishment_name");
            establishment_type = getIntent().getStringExtra("establishment_type");
            food_served = getIntent().getStringExtra("food_served");
            location = getIntent().getStringExtra("location");
            review = getIntent().getStringExtra("review");

            //set intent data
            user_id_input.setText(user_id);
            establishment_name_input.setText(establishment_name);
            establishment_type_input.setText(establishment_type);
            food_served_input.setText(food_served);
            location_input.setText(location);
            review_input.setText(review);


        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    //delete dialog
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setTitle("Delete Review ?");
        builder.setMessage("Are you sure you want delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}