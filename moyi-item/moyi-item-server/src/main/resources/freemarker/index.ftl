<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8" />
    <title></title>
</head>
<script src="/static/js/jquery-3.1.0.min.js"></script>
<body>
<h1>首页</h1>欢迎<p id="pId"><p/>登录
<a href="http://www.moyi.com/category/getCategory">点击本域名下链接</a>
<a href="http://api.moyi.com/item/category/getCategory">点击其他域名下链接</a>
<button onclick="doSend()">发送ajax请求到其他域名下</button>
<form method="post" id="formId">
    <input type="file" name="file"/>
</form>
<button onclick="doUpload()">发送ajax上传图片</button>

<form method="post" id="loginFormId">
    <input id="nameId" type="text" name="username"/>
    <input id="pwdId" type="text" name="password"/>
</form>
<button onclick="doLogin()">登录</button>
<button onclick="getCook()">getCook</button>
<button onclick="verfy()">校验是否登录</button>
</body>
<script>
$(function(){
})
    function doSend() {
        $.post("http://api.moyi.com/item/category/getCategory",{"id":1},function(data){
            console.log(data)
        })
    }
//表单ajax提交
function doUpload() {
    //formdata对象封装form表单参数(ajax带文件上传方式)
    var form = new FormData(document.getElementById("formId"));
    $.ajax({
        // url:"http://127.0.0.1:8002/file/upload",
        url:"http://api.moyi.com/uploadFile/file/upload",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        error : function(request) {
        },
        success : function(data) {
            console.log(data)
        }
    });
}
//登录
function doLogin() {
    $.ajax({
        url:"http://api.moyi.com/authCenter/auth/login",
        data: {"username":"zhangsan","password":"123"},
        type:"post",
        // data : $('#loginFormId').serialize(),
        processData:false,
        contentType:false,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        error : function(request) {
        },
        success : function(data) {
            console.log(data)
            if(data != null) {
                $("#pId").html(data.username);
                alert(data.msg);
            }
        }
    });
}
function getCook() {
    $.ajax({
        url:"http://api.moyi.com/authCenter/auth/getCook",
        // data: {"username":"zhangsan","password":"123"},
        type:"post",
        data : $('#loginFormId').serialize(),
        processData:false,
        contentType:false,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        error : function(request) {
        },
        success : function(data) {
            alert(data)
            console.log(data)
        }
    });
}
function verfy() {
    $.ajax({
        url:"http://api.moyi.com/authCenter/auth/verfy",
        // data: {"username":"zhangsan","password":"123"},
        type:"post",
        data : $('#loginFormId').serialize(),
        processData:false,
        contentType:false,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        error : function(request) {
        },
        success : function(data) {
            alert(data)
            console.log(data)
        }
    });
}
</script>
</html>