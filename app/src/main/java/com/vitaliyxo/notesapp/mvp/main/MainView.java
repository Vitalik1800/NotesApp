package com.vitaliyxo.notesapp.mvp.main;

import com.vitaliyxo.notesapp.Note;

import java.util.List;

public interface MainView {
    void showNotes(List<Note> notes);
}
