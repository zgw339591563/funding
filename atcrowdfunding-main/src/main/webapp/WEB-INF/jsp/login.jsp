<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="${APP_PATH}/" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">
      <h1>${errorMsg}</h1>
      <form method="post" action="${APP_PATH}/dologin.htm" class="form-signin" role="form">
        <h2 class="form-signin-heading" ><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="loginacct" name="loginacct" value="zhangsan" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="userpswd" name="userpswd" value="123123" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select id="usertype" class="form-control" >
                <option value="member" selected>会员</option>
                <option value="user" >管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="${APP_PATH}/reg.htm">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH}/script/layer/layer.js"></script>
    <script>
    function dologin() {
    	// 非空判断
    	// JQuery对象
    	var loginacct = $("#loginacct");
    	// 表单元素的取值不可能为null,如果为空，表示取值为空字符串
    	if ( loginacct.val() == "" ) {
    		//alert("登陆账号不能为空，请输入");
    		layer.msg("登陆账号不能为空，请输入", {time:1000, icon:5, shift:6}, function(){
        		loginacct.focus();
    		});
    		return;
    	}
    	
    	var userpswd = $("#userpswd");
    	// 表单元素的取值不可能为null,如果为空，表示取值为空字符串
    	if ( $.trim(userpswd.val()) == "" ) {
    		//alert("登陆密码不能为空，请输入");
    		layer.msg("登陆密码不能为空，请输入", {time:1000, icon:5, shift:6}, function(){
    			userpswd.focus();
    		});
    		return;
    	}
    	
    	// 使用AJAX提交表单，进行用户登录
    	// JS AJAX : XMLHttpRequest
    	// $.post, $.get, $.ajax, $.getJSON
    	
    	// var s = new object();
    	// JSON对象 ： JavaScript Object Notation
    	// JSON字符串 ： { "name" : "zhangsan", age : 20 }
    	// var obj = { "name" : "zhangsan", age : 20 };
    	// var objs = [{}, {}];
    	var loadingIndex = -1;
    	var url = "";
    	if ( "member" == $("#usertype").val() ) {
    		url = "${APP_PATH}/doMemberLogin.do";
    	} else {
    		url = "${APP_PATH}/dologin.do";
    	}
    	
    	$.ajax({
    		async:true,
    		url : url,
    		type : "POST",
    		data : {
    			"loginacct" : loginacct.val(),
    			"userpswd"  : userpswd.val(),
    			"usertype"  : $("#usertype").val()
    		},
    		beforeSend : function() {
    			//loadingIndex = layer.msg('处理中', {icon: 16});
    			loadingIndex = layer.load(2, {time: 10*1000})
    			return true;
    		},
    		success : function(result) {
    			layer.close(loadingIndex);
    			// 通过返回的响应结果跳转页面
    			if ( result.success ) {
    				if ( "member" == $("#usertype").val() ) {
    					window.location.href = "${APP_PATH}/member.htm";
    				} else {
    					window.location.href = "${APP_PATH}/main.htm";
    				}
    			} else {
    	    		layer.msg(result.errorMsg, {time:1500, icon:5, shift:6});
    			}
    		},
    		error : function() {
    			
    		}
    	});
    }
    </script>
  </body>
</html>