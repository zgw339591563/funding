<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 流程定义维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<%@include file="/WEB-INF/jsp/common/userinfo.jsp" %>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
                <%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<button id="uploadProcDefBtn" type="button" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-upload"></i> 上传流程定义文件</button>

<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" onclick="allSel(this)"></th>
                  <th>名称</th>
                  <th>KEY</th>
                  <th>版本号</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
<%-- 	                <tr>
	                  <td>${status.count}</td>
					  <td><input type="checkbox"></td>
	                  <td>${role.loginacct}</td>
	                  <td>${role.rolename}</td>
	                  <td>${role.email}</td>
	                  <td>
					      <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
					      <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
						  <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
					  </td>
	                </tr> --%>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination"></ul>
					 </td>
				 </tr>

			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
    <form id="uploadForm" action="${APP_PATH}/process/upload.do" method="post" enctype="multipart/form-data">
        <input style="display:none;" id="procDefFile" type="file" name="procDefFile">
    </form>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/script/layer/layer.js"></script>
	<script src="${APP_PATH}/script/render.js"></script>
	<script src="${APP_PATH}/jquery/jquery.form.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    pageQuery(1);
            });
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            
            function changePageno( pageno ) {
            	pageQuery(pageno);
            }
            
            function pageQuery(pageno) {
            	var loadingIndex = -1;
            	// 使用AJAX异步分页查询流程定义数据
            	
            	var obj = {
           			"pageno" : pageno,
           			"pagesize" : 10
            	};
            	
            	$.ajax({
            		url : "${APP_PATH}/process/pageQuery.do",
            		type : "POST",
            		data : obj,
            		beforeSend : function() {
            			loadingIndex = layer.msg('数据查询中', {icon: 16});
            			return true;
            		},
            		success : function( result ) {
            			layer.close(loadingIndex);
            			if ( result.success ) {
            				// 将查询结果循环遍历，将数据展现出来
            				var pageObj = result.pageObj;
            				var procDefList = pageObj.datas;
            				
            				var content = "";
            				$.each(procDefList, function(i, procDef){
            					// 循环体
            					
            					// 相同类型的引号不能嵌套使用
            					// "a'b'c"
            					// 'a"b"c'
             				   content = content +  '<tr>';
          	                   content = content +  '  <td>'+(i+1)+'</td>';
          					   content = content +  '  <td><input type="checkbox" value="'+procDef.id+'"></td>';
          	                   content = content +  '  <td>'+procDef.name+'</td>';
          	                   content = content +  '  <td>'+procDef.key+'</td>';
          	                   content = content +  '  <td>'+procDef.version+'</td>';
          	                   content = content +  '  <td>';
          					   content = content +  '      <button type="button" onclick="window.location.href=\'${APP_PATH}/process/show.htm?id='+procDef.id+'\'"class="btn btn-success btn-xs"><i class="glyphicon glyphicon-eye-open"></i></button>';
          					   content = content +  '	   <button type="button" onclick="deleteProcDef(\''+procDef.id+'\', \''+procDef.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
          					   content = content +  '  </td>';
          	                   content = content +  '</tr>';
            				});
            				
            				var pageContent = "";
            				
            				if ( pageObj.pageno != 1 ) {
            					pageContent = pageContent + '<li><a href="#" onclick="changePageno('+(pageObj.pageno-1)+')">上一页</a></li>';
            				}
            				for ( var i = 1; i <= pageObj.totalno; i++ ) {
            					if ( i == pageObj.pageno ) {
            						pageContent = pageContent + '<li class="active"><a href="#" onclick="changePageno('+i+')">'+i+'</a></li>';	
            					} else {
            						pageContent = pageContent + '<li><a href="#" onclick="changePageno('+i+')">'+i+'</a></li>';
            					}
            					
            				}
            				if ( pageObj.pageno != pageObj.totalno ) {
            					pageContent = pageContent + '<li><a href="#" onclick="changePageno('+(pageObj.pageno+1)+')">下一页</a></li>';
            				}
            				
            				$(".pagination").html(pageContent);
            				
            				$("tbody").html(content);
            			} else {
            				layer.msg("流程定义分页查询数据失败", {time:1000, icon:5, shift:6});
            			}
            		}
            	});
            }
            var cond = false;
            function queryUser() {
            	var queryText = $("#queryText");
            	if ( queryText.val() == "" ) {
            		layer.msg("查询条件不能为空，请输入", {time:1000, icon:5, shift:6}, function(){
            			queryText.focus();
            		});
            		return;
            	}
            	cond = true;
            	pageQuery(1);
            }
            
            function deleteUser(id, name) {
    			layer.confirm("删除流程定义: "+name+", 是否继续？",  {icon: 3, title:'提示'}, function(cindex){
    				// 删除数据
    				$.ajax({
    					url : "${APP_PATH}/role/delete.do",
    					type : "POST",
    					data  : {
    						id : id
    					},
    					success : function(result) {
    						if ( result.success ) {
                				layer.msg("流程定义信息删除成功", {time:1000, icon:6}, function(){
                					pageQuery(1);
                				});
    						} else {
    							layer.msg("流程定义信息删除失败", {time:1000, icon:5, shift:6});
    						}
    					}
    				});
    				
    				
    			    layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            
            function allSel( obj ) {
            	// 获取全选框的勾选状态
            	var flg = obj.checked;
            	
            	var roleBox = $("tbody :checkbox");
            	
            	$.each(roleBox, function(i, n){
            		n.checked = flg;
            	});
            }
            
            function deleteUsers() {
            	var checkBox = $("tbody :checked");
            	if ( checkBox.length == 0 ) {
            		layer.msg("请选择需要删除的流程定义数据", {time:1000, icon:5, shift:6});
            	} else {
        			layer.confirm("删除选择的流程定义数据, 是否继续？",  {icon: 3, title:'提示'}, function(cindex){
        				// 删除数据
        				var obj = {}
        				$.each(checkBox, function(i, n){
        					obj["datas["+i+"].id"] = n.value;
        				});
        				$.ajax({
        					url : "${APP_PATH}/role/deletes.do",
        					type : "POST",
        					data  : obj,
        					success : function(result) {
        						if ( result.success ) {
                    				layer.msg("流程定义信息删除成功", {time:1000, icon:6}, function(){
                    					pageQuery(1);
                    				});
        						} else {
        							layer.msg("流程定义信息删除失败", {time:1000, icon:5, shift:6});
        						}
        					}
        				});
        			    layer.close(cindex);
        			}, function(cindex){
        			    layer.close(cindex);
        			});
            	}
            }
            
            // var sss = $("#username").val();
            // $("#username").val("123");
            
            $("#uploadProcDefBtn").click(function(){
            	$("#procDefFile").click();
            });
            
            $("#procDefFile").change(function(){
            	var options = {
               		beforeSubmit : function() {
               			loadingIndex = layer.msg('流程定义文件上传中', {icon: 16});
               		},
               		success : function(result) {
               			layer.close(loadingIndex);
               			// 重置文件域对象
               			$("#uploadForm")[0].reset();
               			if ( result.success ) {
               				layer.msg("流程定义文件上传成功", {time:1000, icon:6}, function(){
               					pageQuery(1);
               				});
               			} else {
               				layer.msg("流程定义文件上传失败", {time:1000, icon:5, shift:6});
               			}
               		}
               	}
               	$("#uploadForm").ajaxSubmit(options);
            });
            
            function deleteProcDef(id, name) {
    			layer.confirm("删除流程定义："+name+", 是否继续？",  {icon: 3, title:'提示'}, function(cindex){
    				$.ajax({
    					url : "${APP_PATH}/process/delete.do",
    					type : "POST",
    					data  : {
    						id : id
    					},
    					success : function(result) {
    						if ( result.success ) {
                				layer.msg("流程定义删除成功", {time:1000, icon:6}, function(){
                					pageQuery(1);
                				});
    						} else {
    							layer.msg("流程定义删除失败", {time:1000, icon:5, shift:6});
    						}
    					}
    				});
    			    layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
        </script>
        <script type="text/javascript" src="${APP_PATH}/script/menu.js"></script>
  </body>
</html>
