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
@Service("noteService")//ɨ�赽spring����
@Transactional
public class NoteServiceImpl implements NoteService {
	 @Resource
     private NoteDao dao;
	public NoteResult<List<Map>> LoadNotes(String BookId) {
		List<Map> list=dao.findByBookId(BookId);
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		result.setStatus(0);
		result.setMsg("��ѯ�ʼ����");
		result.setData(list);
		return result;
	}
	public NoteResult<Note> LoadNote(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note note=dao.findById(noteId);
		if(note==null){
			result.setStatus(1);
			result.setMsg("δ�ҵ����ݣ�");
			return result;
		}else{
		result.setStatus(0);
		result.setData(note);
		result.setMsg("�ʼǼ�����ɣ�");
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
		result.setMsg("����ʼǳɹ���");
		}else{
			result.setStatus(1);
			result.setMsg("����ʼ�ʧ�ܣ�");
		}
		return result;
	}
	public NoteResult delNote(String NoteId) {
		NoteResult result=new NoteResult();
		int rows=dao.delete(NoteId);
		if(rows==1){
			result.setStatus(0);
			result.setMsg("ɾ���ʼǳɹ���");
			}else{
				result.setStatus(1);
				result.setMsg("ɾ���ʼ�ʧ�ܣ�");
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
		result.setMsg("��ӱʼǳɹ�");
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
			result.setMsg("ɾ��������վ�ɹ���");
		}else{
		result.setStatus(1);
		result.setMsg("ɾ��������վʧ�ܣ�");
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
			result.setMsg("�ƶ��ɹ���");
		}else{
			result.setStatus(1);
			result.setMsg("�ƶ�ʧ�ܣ�");
		}
		return result;
	}
	public NoteResult<List<Note>> loadManager(String title, String status, String begin, String end,String userId) {
		//���ݴ���Ĳ�������SQL����
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId",userId);
		//���ǿ�ʱ��Ч
		if(!"".equals(title) && title!=null){
			map.put("title","%"+title+"%");
		}
		//����"ȫ��"ʱ��Ч
		if(status!=null&&!"0".equals(status)){
			map.put("status",status);
		}
		//���ǿ�ʱ��Ч
		if(begin!=null&&!"".equals(begin)){
			map.put("begin",Date.valueOf(begin).getTime());
		}
		//���ǿ�ʱ��Ч
		if(end!=null&&!"".equals(end)){
			map.put("end",Date.valueOf(end).getTime());
		}
		List<Note> list=dao.findNotes(map);
		NoteResult<List<Note>> result=new NoteResult<List<Note>>();
		result.setStatus(0);
		result.setData(list);
		result.setMsg("��ѯ�ɹ���");
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
			 result.setMsg("�ָ��ɹ���");
		 }else{
			 result.setStatus(1);
			 result.setMsg("�ָ�ʧ�ܣ�");
		 }
		return result;
	}
       
}
