package com.tedu.cloudnote.controller.note;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.transform.Result;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.regexp.internal.recompile;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNotesController {
	  @Resource
      private NoteService noteService;
	   @RequestMapping("/loadnotes.do")
	   @ResponseBody
	  public NoteResult<List<Map>> execute(String bookId){
		  NoteResult<List<Map>> result=noteService.LoadNotes(bookId);
		return result;
	  }
	   @RequestMapping("/loadRemove.do")
	   @ResponseBody
	   public NoteResult<List<Note>> execute1(){
		   NoteResult<List<Note>> result=noteService.loadRemove();
		return result;
		   
	   }
}
