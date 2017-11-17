	$(function(){
		//给登录按钮绑定单击
		$("#login").click(userLogin);
		//给注册按钮绑定单击处理
		$("#regist_button").click(userRegist);
	});
//用户登录		
function userLogin(){
			$("#count_span").html("");
			$("#password_span").html("");
			//获取请求参数
			var name=$("#count").val().trim();
			var pwd=$("#password").val().trim();
			//检测格式
			var ok=true;
			if(name==""){
				$("#count_span").html("用户名为空");
				ok=false;
			}
			if(pwd==""){
				$("#password_span").html("密码为空");
				ok=false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
				url:path+"/user/login.do",
				type:"post",
				data:{"name":name,"password":pwd},
				dataType:"json",
				success:function(result){
					if(result.status==0){
						//成功
						var userId=result.data.cn_user_id;
						var token=result.data.cn_user_token;
						addCookie("userId",userId,2);
						addCookie("userToken",token);
						window.location.href="edit.html";
					}else if(result.status==1){
						//用户名错误
						$("#count_span").html(result.msg);
					}else if(result.status==2){
						//密码错误
						$("#password_span").html(result.msg);
					}
				},
				error:function(){
					alert("登录失败");}
				});
			}	
			}
function userRegist(){
	//清楚提示信息
	$(".warning span").html("");
	//获取请求参数
	var name=$("#regist_username").val().trim();
	var nick=$("#nickname").val().trim();
	var password=$("#regist_password").val().trim();
	var final_password=$("#final_password").val().trim();
	//检查格式
	var ok=true;
	if(name==""){
		$("#warning_1 span").html("用户名为空！");
		ok=false;
		$("#warning_1").show();
	}
	if(password==""){
		$("#warning_2 span").html("密码为空！");
		ok=false;
		$("#warning_2").show();
	}
	else if(password.length>0&&password.length<6){
		$("#warning_2 span").html("密码长度小于6位！");
		ok=false;
		$("#warning_2").show();
	}
	//检测确认密码
	if(final_password==""){
		$("#warning_3 span").html("确认密码为空！");
		ok=false;
		$("#warning_3").show();
	}else if(final_password!=password){
		$("#warning_3 span").html("与密码不一致！");
		ok=false;
		$("#warning_3").show();
	}
	//通过检测发送ajax请求
	$.ajax({
		url:path+"/user/add.do",
		type:"post",
		data:{"name":name,"nick":nick,"password":password},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//成功
				alert(result.msg);
				$("#back").click();
			}else if(result.status==1){
				//用户名重复
				$("#warning_1 span").html(result.msg);
				$("#warning_1").show();
			}
		},
		error:function(){
			alert("注册失败");
		}
	});
}