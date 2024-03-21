package com.vitaliyxo.notesapp.mvp.main;

import android.content.Context;

import com.vitaliyxo.notesapp.DBHelper;
import com.vitaliyxo.notesapp.Note;

import java.util.List;

public class MainModel {


    DBHelper dbHelper;

    public MainModel(Context context){
        dbHelper = new DBHelper(context);
    }

    public List<Note> getNotes(){
        return dbHelper.getAllNotes();
    }

    public void addNote(String noteName, String noteDesc, String noteDate){
        dbHelper.insertNote(noteName, noteDesc, noteDate);
    }

    public void updateNote(Note note){
        dbHelper.updateNote(note);
    }

    public void deleteNote(int noteId){
        dbHelper.deleteNote(noteId);
    }

}
