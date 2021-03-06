package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String STUDENT_NAME = "STUDENTName";
    public static final String STUDENT_AGE = "STUDENTAge";
    public static final String ACTIVE_STUDENT = "ActiveSTUDENT";
    public static final String STUDENT_ID = "STUDENTID";
    public static final String STUDENT_TABLE = "StudentTable";

    public DBHelper(@Nullable Context context)
    {
        super(context, "studentDB.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTableSTatementOne = "CREATE TABLE CustTable(STUDENTID Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME_FIRST + " Text, STUDENTAge Int, ActiveSTUDENT BOOL) ";
        String createTableSTatement = "CREATE TABLE " + STUDENT_TABLE + "(" + STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text, " + STUDENT_AGE + " Int, " + ACTIVE_STUDENT + " BOOL) ";
        db.execSQL(createTableSTatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    public void  addStudent(Student STUDENTModel){
        SQLiteDatabase db = this.getWritableDatabase();
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, STUDENTModel.getName());
        cv.put(STUDENT_AGE, STUDENTModel.getAge());
        cv.put(ACTIVE_STUDENT, STUDENTModel.isActive());
        db.insert(STUDENT_TABLE, null, cv);
        db.close();

        //NullCoumnHack
        //long insert =
        //if (insert == -1) { return false; }
        //else{return true;}
    }

    public ArrayList<String> getAllStudents() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + STUDENT_TABLE, null);

        ArrayList<String> studentArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
               Student obj= new Student(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false);
                studentArrayList.add("id: "+cursorCourses.getInt(0)+", Name: "+obj.getName()+", Age: "+obj.getAge()+", isActive: "+obj.isActive());
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return studentArrayList;
    }

    public boolean delete(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorCourses=db.rawQuery("SELECT * FROM "+ STUDENT_TABLE+" where STUDENTName=?", new String[] {name});
        if(cursorCourses.moveToFirst())
        {
            db.delete("StudentTable", "STUDENTName=?",new String[] {name} );
            return true;
        }

        else
            return false;
    }

    public boolean update(String name, String newname)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("STUDENTName" , newname);
        Cursor c=db.rawQuery("select * from StudentTable where STUDENTName=?", new String[] {name});
        if(c.getCount()>0)
        {
            db.update("StudentTable", cv,  "STUDENTName=?",new String[] {name} );
            return true;
        }

        else
            return false;
    }

}
