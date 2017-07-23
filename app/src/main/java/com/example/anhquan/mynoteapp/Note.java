package com.example.anhquan.mynoteapp;

import java.io.Serializable;

/**
 * Created by Anh Quan on 7/22/2017.
 */

public class Note implements Serializable {
    private  int noteID;
    private String noteTitle;
    private String noteContent;

    public Note(){};
    public Note(String title, String content)
    {
        noteTitle = title;
        noteContent = content;
    }

    public Note(int id, String title, String content)
    {
        noteID = id;
        noteTitle = title;
        noteContent = content;
    }
    public int getNoteId() {
        return noteID;
    }

    public void setNoteId(int noteId) {
        this.noteID = noteId;
    }
    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }


    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }


    @Override
    public String toString()  {
        return this.noteTitle;
    }

}
