package com.vitaliyxo.notesapp.mvp.main;

import com.vitaliyxo.notesapp.Note;

import java.util.List;

public class MainPresenter {

    private final MainView view;
    private final MainModel model;

    public MainPresenter(MainView view, MainModel model){
        this.view = view;
        this.model = model;
    }

    public void loadNotes(){
        List<Note> notes = model.getNotes();
        view.showNotes(notes);
    }

    public void addNote(String noteName, String noteDesc, String noteDate){
        model.addNote(noteName, noteDesc, noteDate);
        loadNotes();
    }

    public void updateNote(Note note){
        model.updateNote(note);
        loadNotes();
    }

    public void deleteNote(int noteId){
        model.deleteNote(noteId);
        loadNotes();
    }

}
