package com.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

public interface NoteService {
    public NoteResult<List<Map>> LoadNotes(String BookId);
    public NoteResult<Note> LoadNote(String NoteId);
    public NoteResult  saveNote(String NoteId,String title,String body);
    public NoteResult  delNote(String NoteId);
    public NoteResult<Note> addNote(String bookId,String title,String userId);
    public NoteResult<Object>  removeNote(String noteId);
    public NoteResult<Object>  moveNote(String noteId,String bookId);
    public NoteResult<List<Note>> loadManager(String title,String status,String begin,String end,String userId);
    public NoteResult<List<Note>> loadRemove();
    public NoteResult      replayNote(String noteId,String bookId);
}
