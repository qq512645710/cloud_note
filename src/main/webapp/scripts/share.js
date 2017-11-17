function moreSearchShare(){
			  //获取请求参数
			  var title=$("#search_note").val().trim();
			  page=page+1;//计算要加载的下一页数
			  //发送ajax请求加载搜索列表
			  SearchAjax(title,page);
		  }
function searchShare(event){
			  var code=event.keyCode;//获取键盘的code码
			  if(code==13){//回车码
				  //清空原有搜索列表
				  $("#search_ul li").remove();
				  var title=$("#search_note").val().trim();
				  page=1;//每次搜索置1，即每次新搜索显示第一页
				  //发送ajax请求
		          SearchAjax(title,page);
			  }
		  }
//分页加载搜索结果(发送ajax请求)
function SearchAjax(title,page){
    $.ajax({
  	  url:path+"/share/search.do",
        type:"post",
        data:{"title":title,"page":page},
        dataType:"json",
        success:function(result){
      	  if(result.status==0){
      		  var shares=result.data;
      		  for(var i=0;i<shares.length;i++){
      			var shareId=shares[i].cn_share_id;
      			//获取分享标题
      			var title=shares[i].cn_share_title;
      			var s="";
      			s+="<li  class='online'>";
					s+=" <a>";
					s+="  <i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'>";
					s+="  </i>";
					s+=title;
					s+="  <button type='button' id='' class='btn btn-default btn-xs btn_position btn_slide_down'>";
					s+="<i class='fa fa-star' style='font-size:20px;line-height:20px;'></i>";
					s+="  </button>";
					//s+="<i class='fa fa-sitemap'></i>";
					s+="</a>";
					s+="</li>";	
					var $li=$(s);
					$li.data("shareId",shareId);
      			$("#search_ul").append($li);
      			//切换列表显示区
      			$("#pc_part_2").hide();	
      			$("#pc_part_6").show();
      		  }
      	  }
        },
        error:function(){
      	  alert("搜索失败！");
        }
    });
}
function loadShare(){
	 //设置选中效果
	 $("#search_ul a").removeClass("checked");
	 $(this).find("a").addClass("checked");
	 var shareId=$(this).data("shareId");
	 $.ajax({
		 url:path+"/share/load.do",
		 type:"post",
		 data:{"shareId":shareId},
		 dataType:"json",
		 success:function(result){
			 if(result.status==0){
				 var share=result.data;
				 var title=share.cn_share_title;//分享标题
				 var body=share.cn_share_body;//分享内容
				 //设置预览区
				 $("#noput_note_title").html(title);
				 $("#noput_note_body").html(body);
			 }
		 },
		 error:function(){
			 alert("加载分享笔记失败！");
		 }
	 });
	 $("#pc_part_5").show();//预览区
	 $("#pc_part_3").hide();//编辑区
	  
 }
function sureShareNote(){
			  var $li=$(this).parents("li");
			  var noteId=$li.data("noteId");
			  $.ajax({
				  url:path+"/share/add.do",
				  type:"post",
				  data:{"noteId":noteId},
				  dataType:"json",
				  success:function(result){
					  if(result.status==0){
						  $("#note_ul a.checked").append("<i class='fa fa-sitemap'></i>");
						  $("#note_ul a.checked").next().find(".btn_share").parent().remove();
						  alert(result.msg);
					  }
				  },
				  error:function(){
					  alert("分享失败！");
				  }
			  });
		  }