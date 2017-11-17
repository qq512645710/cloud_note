package com.tedu.cloudnote.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("noteService")//扫描到spring容器
@Transactional
public class NoteServiceImpl implements NoteService {
	 @Resource
     private NoteDao dao;
	public NoteResult<List<Map>> LoadNotes(String BookId) {
		List<Map> list=dao.findByBookId(BookId);
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		result.setStatus(0);
		result.setMsg("查询笔记完成");
		result.setData(list);
		return result;
	}
	public NoteResult<Note> LoadNote(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note note=dao.findById(noteId);
		if(note==null){
			result.setStatus(1);
			result.setMsg("未找到数据！");
			return result;
		}else{
		result.setStatus(0);
		result.setData(note);
		result.setMsg("笔记加载完成！");
		return result;
		}
	}
	public NoteResult saveNote(String NoteId, String title, String body) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_body(body);
		note.setCn_note_id(NoteId);
		note.setCn_note_tittle(title);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
//		int rows=dao.update(note);
		int rows=dao.Dupdate(note);
		if(rows==1){
		result.setStatus(0);
		result.setMsg("保存笔记成功！");
		}else{
			result.setStatus(1);
			result.setMsg("保存笔记失败！");
		}
		return result;
	}
	public NoteResult delNote(String NoteId) {
		NoteResult result=new NoteResult();
		int rows=dao.delete(NoteId);
		if(rows==1){
			result.setStatus(0);
			result.setMsg("删除笔记成功！");
			}else{
				result.setStatus(1);
				result.setMsg("删除笔记失败！");
			}
			return result;
	}
	public NoteResult<Note> addNote(String bookId, String title, String userId) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");
		note.setCn_note_tittle(title);
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_body("");
		dao.addNote(note);
		result.setData(note);
		result.setMsg("添加笔记成功");
		result.setStatus(0);
		return result;
	}
	public NoteResult<Object> removeNote(String noteId) {
		NoteResult<Object> result=new NoteResult<Object>();
//		int rows=dao.remove(noteId);
		Note note=new Note();
		note.setCn_note_status_id("2");
		note.setCn_note_id(noteId);
		int rows=dao.Dupdate(note);
		if(rows==1){
			result.setStatus(0);
			result.setMsg("删除至回收站成功！");
		}else{
		result.setStatus(1);
		result.setMsg("删除至回收站失败！");
		}
		return result;
	}
	public NoteResult<Object> moveNote(String noteId, String bookId) {
		NoteResult<Object> result=new NoteResult<Object>();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
//		int rows=dao.move(note);
		int rows=dao.Dupdate(note);
		if(rows==1){
			result.setStatus(0);
			result.setMsg("移动成功！");
		}else{
			result.setStatus(1);
			result.setMsg("移动失败！");
		}
		return result;
	}
	public NoteResult<List<Note>> loadManager(String title, String status, String begin, String end,String userId) {
		//根据传入的参数设置SQL参数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId",userId);
		//不是空时有效
		if(!"".equals(title) && title!=null){
			map.put("title","%"+title+"%");
		}
		//不是"全部"时有效
		if(status!=null&&!"0".equals(status)){
			map.put("status",status);
		}
		//不是空时有效
		if(begin!=null&&!"".equals(begin)){
			map.put("begin",Date.valueOf(begin).getTime());
		}
		//不是空时有效
		if(end!=null&&!"".equals(end)){
			map.put("end",Date.valueOf(end).getTime());
		}
		List<Note> list=dao.findNotes(map);
		NoteResult<List<Note>> result=new NoteResult<List<Note>>();
		result.setStatus(0);
		result.setData(list);
		result.setMsg("查询成功！");
		return result;
	}
	public NoteResult<List<Note>> loadRemove() {
		NoteResult<List<Note>> result=new NoteResult<List<Note>>();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status","2");
		List<Note> list=dao.findNotes(map);
		result.setData(list);
		result.setStatus(0);
		return result;
	}
	public NoteResult replayNote(String noteId, String bookId) {
		NoteResult  result=new NoteResult();
		Note note=new Note();
		if(!"".equals(bookId)){
			note.setCn_notebook_id(bookId);
		}
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("1");
		 int rows=dao.Dupdate(note);
		 if(rows!=0){
			 result.setStatus(0);
			 result.setMsg("恢复成功！");
		 }else{
			 result.setStatus(1);
			 result.setMsg("恢复失败！");
		 }
		return result;
	}
       
}
