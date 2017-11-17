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
 * Transactional���ĸ�����
 * 1. readOnly    �ɶ�д����  (Ĭ�Ͽɶ���д),����select�����ɲ���ֻ������(readOnly=true)
 * 2. rollbackFor �ع�����   Ĭ��RuntimeException�ع��������쳣���ع�����Ҫʹ����ָ��(rollbackFor=IOException)
 * 3. propagation ��������   Ĭ�ϲ��ô�������REQUIRED(һ������������һ������ʱ�������õķ��������Ӱ����㷽���ع�)
 *                 (propagation=REQUIRES_NEW)����ǰ���񵱳�һ���µĶ�������
 * 4. isolation   ��������(ԭ��:����Ĳ���,���ײ������,�ö�,) ����ΪREAD_UNCOMMITTED(��δ�ύ)
 *                Ĭ��READ_COMMITTED(�����ύ) REPEATABLE_READ(���ظ���) SERIALIZABLE(���л�����)
 *                DEFAULT(�Զ�) �������ݿ��Զ�ѡ��READ_COMMITTED��REPEATABLE_READ һ��ʹ��ʱѡ��DEFAULT
 *                ֮�����а�ȫ������,���ɳ���Ա��д�����߼����
 *                (isolation=isolation.READ_COMMITED)  ����Խ�߰�ȫ��Խ��,��������������Խ��
 * @author lenovo
 *
 */
@Transactional //3.ָ����ǣ�����ǵķ���   �൱�ڽ����൱��һ�������������
public class ShareServiceImpl implements ShareService {
    @Resource
    private ShareDao dao;
    @Resource
    private NoteDao nd; 
    //�����DataSourceTransactionManager��������
	public NoteResult<Object> saveShare(String noteId) throws RuntimeException {
		NoteResult<Object> result=new NoteResult<Object>();
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_id(NoteUtil.createId());
		//���ݱʼ�id����title��body
		Note note=nd.findById(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
//		String string=null;
//		string.length();
		note.setCn_note_type_id("1");
	    int rows=dao.save(share);   
	    int row=nd.shared(note);
	    if(rows==1&&row==1){
	    	result.setMsg("����ɹ���");
	    	result.setStatus(0);
	    }else{
	    	result.setMsg("����ʧ�ܣ�");
	    	result.setStatus(1);
	    }
		return result;
	}
	public NoteResult<List<Share>> searchShare(String title,int page) {
		String name="%"+title.trim()+"%";
		int pageSize=5;//ÿҳ����
		int start=(page-1)*pageSize;//��ʼλ��
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name",name);
		map.put("pageSize",pageSize);
		map.put("start",start);
		List<Share> list=dao.findByTitle(map);//ģ������
		//�������ض���
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
		result.setMsg("��ѯ��ϣ�");
		return result;
	}
}