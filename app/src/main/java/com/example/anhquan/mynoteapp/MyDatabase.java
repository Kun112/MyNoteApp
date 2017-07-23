package com.example.anhquan.mynoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Anh Quan on 7/22/2017.
 */

public class MyDatabase extends SQLiteOpenHelper {

    Context context;
    // Database name
    private static final String DATABASE_NAME = "Note_Manager";

    //Database version
    private static final int VERSION = 1;

    //Table name
    private static final String TABLE_NAME = "Note";
    // Table column
    private static String NOTE_ID  = "Note_ID";
    private static final String NOTE_TITLE   = "Note_Title";
    private static final String NOTE_CONTENT = "Note_Content";


    public MyDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE  " + TABLE_NAME + "(" + NOTE_ID + " INTEGER PRIMARY KEY," + NOTE_TITLE + " TEXT," + NOTE_CONTENT + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public void addNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTE_ID, note.getNoteId());
        values.put(NOTE_TITLE, note.getNoteTitle());
        values.put(NOTE_CONTENT, note.getNoteContent());

        db.insert(TABLE_NAME, null, values);
        db.close();
        //Toast.makeText(this, "Them note thanh cong!", Toast.LENGTH_SHORT).show();
    }

    public Note getNoteById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{NOTE_ID, NOTE_TITLE, NOTE_CONTENT}, NOTE_ID += "?", new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        Note newNote = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return newNote;
    }

    public ArrayList<Note> getAllNote()
    {
        ArrayList<Note> arr = new ArrayList<Note>();
        SQLiteDatabase db = this.getReadableDatabase();

        //cau lenh
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        //duyet cursor
        if(cursor!=null)
        {
            cursor.moveToFirst();
            do {
                Note newNote = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                arr.add(newNote);
            }while(cursor.moveToNext());
        }

        //tra ve array
        return arr;
    }

    public int updateNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTE_TITLE, note.getNoteTitle());
        values.put(NOTE_CONTENT, note.getNoteContent());
        return db.update(TABLE_NAME, values, NOTE_ID+="?", new String[]{String.valueOf(note.getNoteId())});

    }

    public void deleteNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NOTE_ID +="?",new String[]{String.valueOf(note.getNoteId())} );
        db.close();
    }
}
