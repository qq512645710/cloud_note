function sureReplay(){
			  var $li=$("#remove_ul a.checked").parent();
			  var noteId=$li.data("noteId");
			  //获取转入的笔记本id
			  var toId=$("#replaySelect").val();
			  if(toId=="none"){
				  $.ajax({
					  url:path+"/note/replay.do",
					  type:"post",
					  data:{"noteId":noteId},
					  dataType:"json",
					  success:function(result){
						  if(result.status==0){
							  $li.remove();
							  alert(result.msg);
						  }else{
							  alert(result.msg);
						  }
					  },
					  error:function(){
						  alert("恢复失败！");
					  }
				  });
			  }else{
				  $.ajax({
					  url:path+"/note/replayToNew.do",
					  type:"post",
					  data:{"noteId":noteId,"bookId":toId},
					  dataType:"json",
					  success:function(result){
						  if(result.status==0){
							  $li.remove();
							  alert(result.msg);
						  }else{
							  alert(result.msg);
						  }
					  },
					  error:function(){
						  alert("恢复失败！");
					  }
				  });
			  }
		  }
//点确定删除回收站笔记
function sureDelNote(){
			  var $li=$("#remove_ul a.checked").parent();
			  var noteId=$li.data("noteId");
			  $.ajax({
				  url:path+"/note/delete.do",
				  type:"post",
				  data:{"noteId":noteId},
				  dataType:"json",
				  success:function(result){
					  if(result.status==0){
						  $li.remove();
						  alert(result.msg);
					  }
				  },
				  error:function(){
					  alert("删除失败！");
				  }
			  });
		  }
//加载回收站笔记列表
function loadRemove(){
			  $.ajax({
				  url:path+"/note/loadRemove.do",
				  type:"post",
				  data:{},
				  dataType:"json",
				  success:function(result){
					  if(result.status==0){
						  $("#remove_ul li").remove();
						  var notes=result.data;
						  for(var i=0;i<notes.length;i++){
							  var title=notes[i].cn_note_title;
							  var noteId=notes[i].cn_note_id;//获取笔记id
							  var s="";
							  s+="<li class='disable'><a ><i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>";
							  s+=title;
							  s+="<button type='button' class='btn btn-default btn-xs btn_position btn_delete'>";
							  s+="<i class='fa fa-times'></i>";
							  s+="</button><button type='button' class='btn btn-default btn-xs btn_position_2 btn_replay'><i class='fa fa-reply'></i></button></a></li>";
							  var $li=$(s);
							  $li.data("noteId",noteId);//将笔记Id绑定到元素
							  $("#remove_ul").append($li);
						  }
					  }
				  },
				  error:function(){
					  alert("加载回收站失败!");
				  }
			  });
			  $("#pc_part_2").hide();
			  $("#pc_part_4").show();
		  }
//点确定移动笔记
function sureMoveNote(){
			  var $noteLi=$("#note_ul a.checked").parent();
			  var noteId=$noteLi.data("noteId");
			  var $bookLi=$("#book_ul a.checked").parent();
			  var bookId=$bookLi.data("bookId");
			  //获取转入的笔记本id
			  var toId=$("#moveSelect").val();
			  //检查转入和转出的笔记本id是否一致
			  //发送ajax请求
			  $.ajax({
				  url:path+"/note/move.do",
				  type:"post",
				  data:{"noteId":noteId,"bookId":toId},
				  dataType:"json",
				  success:function(result){
					  if(result.status==0){
						  $noteLi.remove();
						  alert(result.msg);
					  }
				  },
				  error:function(){
					  alert("移动笔记失败！");
				  }
			  });
			  
		  }
//点确定移除笔记至回收站
function sureRemoveNote(){
			  var $li=$("#note_ul a.checked").parent();
			  var noteId=$li.data("noteId");
			  $.ajax({
				  url:path+"/note/remove.do",
				  type:"post",
				  data:{"noteId":noteId},
				  dataType:"json",
				  success:function(result){
					  if(result.status==0){
						  $li.remove();
						  alert(result.msg);
					  }
				  },
				  error:function(){
					  alert("删除至回收站失败！");
				  }
			  });
		  }
//点击确定添加笔记
function sureAddNote(){
			  var userId=getCookie("userId");
			  var $li=$("#book_ul a.checked").parent();
			  var bookId=$li.data("bookId");
			  var ok=true;
				 if(userId==null){
					 window.location.href="log_in.html";
					 ok=false;
				 }else{
					 //用户已登录
					 var title=$("#input_note").val().trim();
					 //笔记本名的非空检测
					 if(title==""){
						 $("#name_span").html("笔记名为空！");
						 ok=false;
					 }
				 }
				 if(ok){
					 $.ajax({
						 url:path+"/note/add.do",
						 type:"post",
						 data:{"title":title,"bookId":bookId,"userId":userId},
						 dataType:"json",
						 success:function(result){
							 if(result.status==0){
								var note=result.data;
								var noteId=note.cn_note_id;//获取笔记id
							    var noteName=note.cn_note_title;//获取笔记标题
								 closeAlertWindow();
							    createNoteLi(noteId,noteName);
								 alert(result.msg);
							 }
						 },
						 error:function(){
							 alert("添加笔记失败！");
						 }
					 });
				 }
		  }
//修改更新笔记
function updateNote(){
			  var body=um.getContent();
			  var $li=$("#note_ul a.checked").parent();
			  var noteId=$li.data("noteId");
			  var title=$("#input_note_title").val().trim();
		 $.ajax({
				 url:path+"/note/update.do",
				 type:"post",
				 data:{"noteId":noteId,"title":title,"body":body},
				 dataType:"json",
				 success:function(result){
					 if(result.status==0){
						 //更新标题
						 var s1="";
						 s1+="  <i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'>";
							s1+="  </i>";
							s1+=title;
							s1+="  <button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'>";
							s1+="    <i class='fa fa-chevron-down'>";
							s1+="    </i>";
							s1+="  </button>";
						$li.find("a").html(s1);
					 alert(result.msg);	 
					 }else{
						 alert(result.msg);
					 }
				 },
				 error:function(){
					 alert("修改笔记失败！");
				 }
			 }); 
		  }
//加载笔记内容
function loadNote(){
			  //设置选中效果
			  $("#note_ul a").removeClass("checked");
			  $(this).find("a").addClass("checked");
			  $("#pc_part_5").hide();
				 $("#pc_part_3").show();
			  var noteId=$(this).data("noteId");
			 // um.setContent("");
			  $.ajax({
				  url:path+"/note/load.do",
				  type:"post",
				  data:{"noteId":noteId},
				  dataType:"json",
				  success:function(result){
					   if(result.status==0){
						   var note=result.data;
						   var body=note.cn_note_body;
						   var title=note.cn_note_title;
					$("#input_note_title").val(title);
						um.setContent(body+"");
					}
				  },
				  error:function(){
					  alert("笔记显示失败！");
				  }
			  });
		  }
//加载笔记列表
function loadNotes(){
			  //选中前移除样式
			  $("#book_ul a").removeClass("checked");
			  um.setContent("");
			  //设置选中效果
			  $(this).find("a").addClass("checked");
			  //获取请求参数
			  var bookId=$(this).data("bookId");
			  //发送ajax请求
			  $.ajax({
				  url:path+"/note/loadnotes.do",
				  type:"post",
				  data:{"bookId":bookId},
				  dataType:"json",
				  success:function(result){
					  	  if(result.status==0){
						  var notes=result.data;//获取笔记信息
						  $("#note_ul li").remove();
					  for(var i=0;i<notes.length;i++){
								var noteId=notes[i].cn_note_id;//获取笔记id
								var noteName=notes[i].cn_note_title;//获取笔记标题
								var body=notes[i].cn_note_body;
								var status=notes[i].cn_note_status_id;
								var type=notes[i].cn_note_type_id;
								if(status=='1'){
								//创建一个笔记列表li元素
							    var s="";
								s+="<li  class='online'>";
								s+=" <a>";
								s+="  <i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'>";
								s+="  </i>";
								s+=noteName;
								s+="  <button type='button' id='' class='btn btn-default btn-xs btn_position btn_slide_down'>";
								s+="    <i class='fa fa-chevron-down'>";
								s+="    </i>";
								s+="  </button>";
								if(type!=null){
									s+="<i class='fa fa-sitemap'></i>";
								}
								s+="</a>";
								s+="<div class='note_menu' tabindex='-1'>";
								s+="  <dl>";
								s+="    <dt>"+
								         "<button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'>"+
								         "<i class='fa fa-random'>"+
								         "</i></button></dt>";
								if(type!="1"){	
									s+="    <dt>"+
									"<button type='button' class='btn btn-default btn-xs btn_share' title='分享'>"+
									"<i class='fa fa-sitemap'>"+
									"</i></button></dt>";
								}
								s+="    <dt>"+
								        "<button type='button' class='btn btn-default btn-xs btn_delete' title='删除'>"+
								        "<i class='fa fa-times'>"+
								        "</i></button></dt>";
								s+="  </dl>";
								s+=" </div>";
								s+="</li>";	
								var $li=$(s);//将s字符串转成jquery对象
								$li.data("noteId",noteId);//将笔记Id绑定到元素
								$("#note_ul").append($li);
								}
					  } 
					  $("#pc_part_2").show();
					  $("#pc_part_6").hide();
					  $("#pc_part_4").hide();
				  }
			      },
				  error:function(){
					  alert("加载笔记列表失败");
				  }
			  });
		  }
//创建笔记本li
function createBookLi(bookId,bookName){
	var s="";
	s+="<li class='online'>";
	s+="  <a>";
	s+="    <i class='fa fa-book' title='online' rel='tooltip-bottom'>";
	s+="    </i>";
	s+=bookName;
	s+="  </a>";
	s+="</li>";
	var $li=$(s);//将s字符串转成jquery对象
	$li.data("bookId",bookId);//将值与jQuery元素绑定
	//将li元素添加到笔记本ul列表区
	$("#book_ul").append($li);
}
//创建笔记li
function createNoteLi(noteId,title){
	//创建一个笔记列表li元素
    var s="";
	s+="<li  class='online'>";
	s+=" <a>";
	s+="  <i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'>";
	s+="  </i>";
	s+=title;
	s+="  <button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'>";
	s+="    <i class='fa fa-chevron-down'>";
	s+="    </i>";
	s+="  </button>";
	s+="</a>";
	s+="<div class='note_menu' tabindex='-1'>";
	s+="  <dl>";
	s+="    <dt>"+
	         "<button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'>"+
	         "<i class='fa fa-random'>"+
	         "</i></button></dt>";
	s+="    <dt>"+
	        "<button type='button' class='btn btn-default btn-xs btn_share' title='分享'>"+
	        "<i class='fa fa-sitemap'>"+
	        "</i></button></dt>";
	s+="    <dt>"+
	        "<button type='button' class='btn btn-default btn-xs btn_delete' title='删除'>"+
	        "<i class='fa fa-times'>"+
	        "</i></button></dt>";
	s+="  </dl>";
	s+=" </div>";
	s+="</li>";	
	var $li=$(s);//将s字符串转成jquery对象
	$li.data("noteId",noteId);//将笔记Id绑定到元素
	$("#note_ul").append($li);
}
//笔记菜单
function showNoteMenu(){
	 $("#note_ul").on("click",".btn_slide_down",function(){
		  $("#note_ul div").hide();
		  var note_menu=$(this).parent().next();
		  note_menu.slideDown(1000);
		  //设置笔记li选中效果
		  $("#note_ul a").removeClass("checked");
		  $(this).parent().addClass("checked");
		  //阻止li和body的事件冒泡
		  return false;//返回false阻止
	  });
	  //点击body范围，将笔记菜单隐藏
	  $("body").click(function(){
		  $("#note_ul div").hide();
	  });
}