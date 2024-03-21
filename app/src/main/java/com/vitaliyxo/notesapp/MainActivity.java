package com.vitaliyxo.notesapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.vitaliyxo.notesapp.databinding.ActivityMainBinding;
import com.vitaliyxo.notesapp.mvp.main.MainModel;
import com.vitaliyxo.notesapp.mvp.main.MainPresenter;
import com.vitaliyxo.notesapp.mvp.main.MainView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, OnNoteDeleteListener, OnNoteEditListener {

    ActivityMainBinding binding;
    EditText editTextNoteName, editTextNoteDesc;
    Button choose_date, button_cancel, button_save;
    MainPresenter presenter;
    List<Note> noteList = new ArrayList<>();
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteAdapter = new NoteAdapter(this, noteList, this,this);
        binding.recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewNotes.setAdapter(noteAdapter);
        presenter = new MainPresenter(this, new MainModel(this));
        presenter.loadNotes();
        binding.add.setOnClickListener(v -> showAddNoteDialog());
    }

    @SuppressLint("MissingInflatedId")
    public void showAddNoteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        builder.setTitle("Додати нотатку");
        builder.setView(dialogView);
        editTextNoteName = dialogView.findViewById(R.id.editTextNoteName);
        editTextNoteDesc = dialogView.findViewById(R.id.editTextNoteDesc);
        choose_date = dialogView.findViewById(R.id.choose_date);
        button_cancel = dialogView.findViewById(R.id.button_cancel);
        button_save = dialogView.findViewById(R.id.button_save);
        AlertDialog dialog = builder.create();

        choose_date.setOnClickListener(v -> showDatePickerDialog());
        button_cancel.setOnClickListener(v -> dialog.dismiss());
        button_save.setOnClickListener(v -> {
            String noteName = editTextNoteName.getText().toString();
            String noteDesc = editTextNoteDesc.getText().toString();
            String noteDate = choose_date.getText().toString();
            presenter.addNote(noteName, noteDesc, noteDate);
            presenter.loadNotes();
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showEditNoteDialog(Note note){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        builder.setTitle("Редагувати нотатку");
        builder.setView(dialogView);
        editTextNoteName = dialogView.findViewById(R.id.editTextNoteName);
        editTextNoteDesc = dialogView.findViewById(R.id.editTextNoteDesc);
        choose_date = dialogView.findViewById(R.id.choose_date);
        button_cancel = dialogView.findViewById(R.id.button_cancel);
        button_save = dialogView.findViewById(R.id.button_save);
        button_save.setText("Редагувати");
        AlertDialog dialog = builder.create();

        choose_date.setOnClickListener(v -> showDatePickerDialog());
        editTextNoteName.setText(note.getName());
        editTextNoteDesc.setText(note.getDesc());
        choose_date.setText(note.getDate());
        button_cancel.setOnClickListener(v -> dialog.dismiss());
        button_save.setOnClickListener(v -> {
            note.setName(editTextNoteName.getText().toString());
            note.setDesc(editTextNoteDesc.getText().toString());
            note.setDate(choose_date.getText().toString());
            presenter.updateNote(note);
            presenter.loadNotes();
            dialog.dismiss();
        });

        dialog.show();
    }

    public void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, year1, month1, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    choose_date.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onNoteDelete(int noteId) {
        presenter.deleteNote(noteId);
    }

    @Override
    public void onNoteEdit(Note note) {
        showEditNoteDialog(note);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showNotes(List<Note> notes) {
        noteList.clear();
        noteList.addAll(notes);
        noteAdapter.notifyDataSetChanged();
    }
}