package com.vitaliyxo.notesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    OnNoteDeleteListener onNoteDeleteListener;
    OnNoteEditListener onNoteEditListener;
    Context context;
    List<Note> noteList;

    public NoteAdapter(Context context, List<Note> noteList,OnNoteEditListener onNoteEditListener, OnNoteDeleteListener onNoteDeleteListener){
        this.context = context;
        this.noteList = noteList;
        this.onNoteEditListener = onNoteEditListener;
        this.onNoteDeleteListener = onNoteDeleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteId.setText("№: " + note.getId());
        holder.noteName.setText("Нотатка: " + note.getName());
        holder.noteDesc.setText("Опис: " + note.getDesc());
        holder.noteDate.setText("Дата: " + note.getDate());
        holder.buttonDelete.setOnClickListener(v -> {
            int noteId = note.getId();
            onNoteDeleteListener.onNoteDelete(noteId);
        });
        holder.itemView.setOnClickListener(v -> onNoteEditListener.onNoteEdit(note));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteId;
        TextView noteName;
        TextView noteDesc;
        TextView noteDate;
        Button buttonDelete;

        public ViewHolder(@NotNull View view){
            super(view);
            noteId = view.findViewById(R.id.noteId);
            noteName = view.findViewById(R.id.noteName);
            noteDesc = view.findViewById(R.id.noteDesc);
            noteDate = view.findViewById(R.id.noteDate);
            buttonDelete = view.findViewById(R.id.buttonDelete);
        }
    }
}
