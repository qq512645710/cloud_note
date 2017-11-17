//弹出添加笔记本对话框
function alertAddBookWindow(){
			  $("#can").load("alert/alert_notebook.html");
			  $(".opacity_bg").show();
		  };
function closeAlertWindow(){
	  $("#can").html("");//清空对话框内容
	  $(".opacity_bg").hide();//隐藏背景色
};
function alertAddNoteWindow(){
	var $li=$("#book_ul a.checked").parent();
	if($li.length==0){
		alert("请选择笔记本！");
	}else{
	$("#can").load("alert/alert_note.html");
	$(".opacity_bg").show();
	}
};
function alertRemoveNoteWindow(){
	  $("#can").load("alert/alert_delete_note.html");
	  $(".opacity_bg").show();
};
function alertShareNoteWindow(){
	  $("#can").load("alert/alert_delete_note.html");
	  $(".opacity_bg").show();
};
function alertDeleteWindow(){
	  $("#can").load("alert/alert_delete_rollback.html");
	  $(".opacity_bg").show();
};
function alertReplayWindow(){
	  $("#can").load("alert/alert_replay.html",function(){
		  //加载对话框中的option选项
		  var bookLi=$("#book_ul li");
		  for(var i=0;i<bookLi.length;i++){
			  var $li =$(bookLi[i]);//将li转成jquery对象
			  var bookId=$li.data("bookId");
			  var bookName=$li.text().trim();
			  //拼一个option
			  var opt="";
			  opt+="<option value='"+bookId+"'>";
			  opt+=bookName;
			  opt+="</option>";
			  //将option添加到select中
			  $("#replaySelect").append(opt);
		  }
	  });
	  $(".opacity_bg").show();
};
function alertMoveNoteWindow(){
	  $("#can").load("alert/alert_move.html",function(){
		  //加载对话框中的option选项
		  var bookLi=$("#book_ul li");
		  for(var i=0;i<bookLi.length;i++){
			  var $li =$(bookLi[i]);//将li转成jquery对象
			  var bookId=$li.data("bookId");
			  var bookName=$li.text().trim();
			  //拼一个option
			  var opt="";
			  opt+="<option value='"+bookId+"'>";
			  opt+=bookName;
			  opt+="</option>";
			  //将option添加到select中
			  $("#moveSelect").append(opt);
		  }
	  });
	  $(".opacity_bg").show();
};