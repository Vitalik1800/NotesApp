package com.vitaliyxo.notesapp;

public class Note {
    private int id;
    private String name;
    private String desc;
    private String date;

    public Note(int id, String name, String desc, String date){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public Note() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

}
