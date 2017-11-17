package com.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;
@Controller
@RequestMapping("/note")
public class RemoveNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/remove.do")
    @ResponseBody
    public NoteResult execute(String noteId){
    	NoteResult result=noteService.removeNote(noteId);
		return result;
    }
    @RequestMapping("/delete.do")
    @ResponseBody
    public NoteResult execute1(String noteId){
    	NoteResult result=noteService.delNote(noteId);
		return result;
    }
    @RequestMapping("/replay.do")
    @ResponseBody
    public NoteResult execute2(String noteId){
    	NoteResult result=noteService.replayNote(noteId,"");
		return result;
    }
    @RequestMapping("/replayToNew.do")
    @ResponseBody
    public NoteResult execute3(String noteId,String bookId){
    	NoteResult result=noteService.replayNote(noteId,bookId);
		return result;
    }
}
