package com.tedu.cloudnote.service;

import java.util.List;

import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;

public interface ShareService {
   public NoteResult<Object> saveShare(String noteId);
   public NoteResult<List<Share>> searchShare(String title,int page);
   public NoteResult<Share> loadShare(String id);
}
