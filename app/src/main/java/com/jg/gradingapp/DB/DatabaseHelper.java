package com.jg.gradingapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jg.gradingapp.Model.Students;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "students";
    private static final String COL1 = "id";
    private static final String COL2 = "name";
    private static final String COL3 = "program_code";
    private static final String COL4 = "grade";
    private static final String COL5 = "duration";
    private static final String COL6 = "fees";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME+ " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2 + " TEXT, "+
                COL3 + " TEXT, "+
                COL4 + " TEXT, "+
                COL5 + " TEXT, "+
                COL6 + " TEXT)"
                ;
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
// Add student grade Entry
    public boolean addData(String name,String program_code, String grade, String duration, String fees){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,program_code);
        contentValues.put(COL4,grade);
        contentValues.put(COL5,duration);
        contentValues.put(COL6,fees);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    // Get All data
    public ArrayList<Students> getAllData() {
        ArrayList<Students> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String program_code = cursor.getString(2);
            String grade = cursor.getString(3);
            String duration = cursor.getString(4);
            String fees = cursor.getString(5);
            Students student = new Students(id, name, program_code, grade, duration,fees);
            arrayList.add(student);

        }
        return arrayList;
    }
//search data by Id
    public ArrayList<Students> getIDData(String ID){
        ArrayList<Students> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where id = ?",new String[]{ID});

        //populate array list
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String program_code = cursor.getString(2);
            String grade = cursor.getString(3);
            String duration = cursor.getString(4);
            String fees = cursor.getString(5);
            Students student = new Students(id, name, program_code, grade, duration,fees);
            arrayList.add(student);

        }
        return arrayList;

    }
    // search data by program code
    public ArrayList<Students> getCodeData(String code){
        ArrayList<Students> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where program_code = ?",new String[]{code});

        //populate array list
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String program_code = cursor.getString(2);
            String grade = cursor.getString(3);
            String duration = cursor.getString(4);
            String fees = cursor.getString(5);
            Students student = new Students(id, name, program_code, grade, duration,fees);
            arrayList.add(student);

        }
        return arrayList;

    }

}
