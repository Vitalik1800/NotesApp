package com.vitaliyxo.notesapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes_app.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NOTE_NAME = "note_name";
    public static final String COLUMN_NOTE_DESC = "note_desc";
    public static final String COLUMN_NOTE_DATE = "note_date";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOTE_NAME + " TEXT, " +
                    COLUMN_NOTE_DESC + " TEXT, " +
                    COLUMN_NOTE_DATE + " TEXT)";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void updateNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_NAME, note.getName());
        values.put(COLUMN_NOTE_DESC, note.getDesc());
        values.put(COLUMN_NOTE_DATE, note.getDate());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_NAME)));
                note.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DESC)));
                note.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DATE)));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }

    public long insertNote(String noteName, String noteDesc, String noteDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_NAME, noteName);
        values.put(COLUMN_NOTE_DESC, noteDesc);
        values.put(COLUMN_NOTE_DATE, noteDate);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public void deleteNote(int noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        db.close();
    }
}
