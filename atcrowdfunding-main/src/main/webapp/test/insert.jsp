<%@page pageEncoding="UTF-8"%>
<html>
    <head>
        <title>多用户数据提交</title>
    </head>
    <body>
        <h1>多用户数据提交</h1>
        
        <button id="addUser">新增用户</button><br><br>
        <form method="post" action="${APP_PATH}/test/insertUsers.htm">
        <table style="border:1px solid #000">
            <tr>
                <td>账号</td>
                <td>名称</td>
                <td>操作</td>
            </tr>
        </table>
        </form>
        <br><br>
        <button id="saveUsers">提交数据</button>
        <script type="text/javascript" src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
        <script type="text/javascript">
            var count = 0;
            $("#addUser").click(function(){
            	
            	var newRow = "";
            	
                newRow = newRow + '<tr>';
	            newRow = newRow + '    <td><input type="text" name="datas['+count+'].loginacct"></td>';
	            newRow = newRow + '    <td><input type="text" name="datas['+count+'].username"></td>';
	            newRow = newRow + '    <td><button onclick="removeUser(this)">删除</button></td>';
	            newRow = newRow + '</tr>';
            	
            	$("table").append(newRow);
            	
            	count++;
            });
            
            function removeUser(obj) {
            	// $(DOM) ==> JQuery
            	$(obj).parent().parent().remove();
            }
        
            $("#saveUsers").click(function(){
            	$("form").submit();
            });
        </script>
    </body>

</html>