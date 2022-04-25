package com.example.booklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static  final String DATABASE_NAME = "Farejudge.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "reviews";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERID = "user_id";
    private static final String COLUMN_ESTABLISHMENT_NAME = "establishment_name";
    private static final String COLUMN_ESTABLISHMENT_TYPE = "establishment_type";
    private static final String COLUMN_FOOD_SERVED = "food_served";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_REVIEW = "review";



     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERID + " TEXT, " +
                        COLUMN_ESTABLISHMENT_NAME + " TEXT, " +
                        COLUMN_ESTABLISHMENT_TYPE + " TEXT, " +
                        COLUMN_FOOD_SERVED + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_REVIEW + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addBook(String user_id, String establishment_name, String establishment_type, String food_served, String location, String review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERID, user_id);
        cv.put(COLUMN_ESTABLISHMENT_NAME, establishment_name);
        cv.put(COLUMN_ESTABLISHMENT_TYPE, establishment_type);
        cv.put(COLUMN_FOOD_SERVED, food_served);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_REVIEW, review);
        
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db  =  this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
           cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String user_id, String establishment_name, String establishment_type, String food_served, String location, String review){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();

         cv.put(COLUMN_USERID, user_id);
         cv.put(COLUMN_ESTABLISHMENT_NAME, establishment_name);
         cv.put(COLUMN_ESTABLISHMENT_TYPE, establishment_type);
         cv.put(COLUMN_FOOD_SERVED, food_served);
         cv.put(COLUMN_LOCATION, location);
         cv.put(COLUMN_REVIEW, review);

         long result = db.update(TABLE_NAME, cv, "_id=?", new String[] {row_id});

         if(result == -1) {
             Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
         } else {
             Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
         }
    }

    void deleteData(String row_id) {
         SQLiteDatabase db = this.getWritableDatabase();
         long result = db.delete(TABLE_NAME, "_id=?", new String[] {row_id});
         if(result == -1) {
             Toast.makeText(context, "Could Not Delete", Toast.LENGTH_SHORT).show();
         } else {
             Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
         }
    }

    void deleteAllData() {
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
