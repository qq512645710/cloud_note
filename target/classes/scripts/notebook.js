		function addSureBook(){
			 var userId=getCookie("userId");//获取请求参数
			 console.log("id:"+userId);
			 var ok=true;
			 if(userId==null){
				 window.location.href="log_in.html";
				 ok=false;
			 }else{
				 //用户已登录
				 var bookname=$("#input_notebook").val().trim();
				 //笔记本名的非空检测
				  console.log("name:"+bookname);
				 if(bookname==""){
					 $("#name_span").html("笔记本名为空！");
					 ok=false;
				 }
			 }
			 //发送ajax请求
			 if(ok){
			 $.ajax({
				 url:path+"/book/add.do",
				 type:"post",
				 data:{"bookname":bookname,"userId":userId},
				 dataType:"json",
				 success:function(result){
					 if(result.status==0){
						 var book=result.data;
						 //关闭对话框
						 closeAlertWindow();
						 //添加一个笔记本li
							var id=book.cn_notebook_id;//获取笔记本id
							var name=book.cn_notebook_name;//获取笔记本名字
							createBookLi(id,name);

						 //提示消息
					 alert(result.msg);
					 }
				 },
				 error:function(){
					 alert("添加笔记本失败！");
				 }
			 });
			 }
		  }
function loadNoteBooks(){
            //获取cookie中的userId值
			var userId=getCookie("userId");
			if(userId==null){//未找到
			window.location.href="log_in.html";
			}else{//登陆过使用userId的其他操作
				//1.发送ajax请求加载笔记本列表
				$.ajax({
					url:path+"/book/loadbooks.do",
					type:"post",
					data:{"userId":userId},
					dataType:"json",
					success:function(result){
						if(result.status==0){
						//获取返回笔记本的集合
						var books=result.data;
						for(var i=0;i<books.length;i++){
							var bookId=books[i].cn_notebook_id;//获取笔记本id
							var bookName=books[i].cn_notebook_name;//获取笔记本名字
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
						}
					},
					erroe:function(){
						alert("加载笔记本失败！");
					}
				});
			}	};