package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;

public interface NoteDao {
     public List<Note> findAll();
     public List<Map> findByBookId(String id);
     public List<Note> findByUserId(String id);
     public Note  findById(String id);
//     public int  update(Note note);
     public int  delete(String id);
     public void addNote(Note note);
//     public int remove(String noteId);
//     public int move(Note note);
     public int shared(Note note);
     public List<Note> findNotes(Map map);
     public int Dupdate(Note note);
     public int deleteNotes(String[] array);
}
