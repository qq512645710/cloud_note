package com.tedu.cloudnote.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("shareService")
/**
 * Transactional有四个属性
 * 1. readOnly    可读写属性  (默认可读可写),若有select操作可采用只读事务(readOnly=true)
 * 2. rollbackFor 回滚特性   默认RuntimeException回滚，其他异常不回滚，若要使用则指明(rollbackFor=IOException)
 * 3. propagation 传播特性   默认采用传播类型REQUIRED(一个方法调用另一个方法时，被调用的方法出错会影响外层方法回滚)
 *                 (propagation=REQUIRES_NEW)将当前事务当成一个新的独立事物
 * 4. isolation   隔离特性(原因:事务的并发,容易产生脏读,幻读,) 级别为READ_UNCOMMITTED(读未提交)
 *                默认READ_COMMITTED(读已提交) REPEATABLE_READ(可重复读) SERIALIZABLE(序列化操作)
 *                DEFAULT(自动) 根据数据库自动选择READ_COMMITTED或REPEATABLE_READ 一般使用时选择DEFAULT
 *                之后若有安全性需求,会由程序员编写代码逻辑解决
 *                (isolation=isolation.READ_COMMITED)  级别越高安全性越好,但并发处理能力越低
 * @author lenovo
 *
 */
@Transactional //3.指定标记，带标记的方法   相当于将此类当成一个整体的完整性
public class ShareServiceImpl implements ShareService {
    @Resource
    private ShareDao dao;
    @Resource
    private NoteDao nd; 
    //会采用DataSourceTransactionManager控制事物
	public NoteResult<Object> saveShare(String noteId) throws RuntimeException {
		NoteResult<Object> result=new NoteResult<Object>();
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_id(NoteUtil.createId());
		//根据笔记id查找title和body
		Note note=nd.findById(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
//		String string=null;
//		string.length();
		note.setCn_note_type_id("1");
	    int rows=dao.save(share);   
	    int row=nd.shared(note);
	    if(rows==1&&row==1){
	    	result.setMsg("分享成功！");
	    	result.setStatus(0);
	    }else{
	    	result.setMsg("分享失败！");
	    	result.setStatus(1);
	    }
		return result;
	}
	public NoteResult<List<Share>> searchShare(String title,int page) {
		String name="%"+title.trim()+"%";
		int pageSize=5;//每页条数
		int start=(page-1)*pageSize;//起始位置
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name",name);
		map.put("pageSize",pageSize);
		map.put("start",start);
		List<Share> list=dao.findByTitle(map);//模糊搜索
		//创建返回对象
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setData(list);
		return result;
	}
	public NoteResult<Share> loadShare(String id){
		Share share=dao.findById(id);
		NoteResult<Share> result=new NoteResult<Share>();
		result.setStatus(0);
		result.setData(share);
		result.setMsg("查询完毕！");
		return result;
	}
}