<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
    String hostName = request.getContextPath();
%>
<meta http-equiv="Content-Type"  content="text/html; charset=utf-8" />
<meta name="viewport" content=" height = device-height , width = device-width , initial-scale = 1 ,
         minimum-scale = 1 , maximum-scale = 1 , user-scalable = no" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<%out.print(hostName);%>/resources/js/bootstrap-select-1.12.2/dist/css/bootstrap-select.css">
<script src="<%out.print(hostName);%>/resources/js/bootstrap-select-1.12.2/js/jquery-3.1.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%out.print(hostName);%>/resources/js/bootstrap-select-1.12.2/js/bootstrap-select.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="<%out.print(hostName);%>/resources/js/bootstrap-select-1.12.2/js/i18n/defaults-zh_CN.js"></script>
<style type="text/css">
.delete {
    display: none;
}
.float-right {
    float: right;
}
</style>
</head>
<body>
    <%
// 访问路径为http://localhost:8080/ssh/db/install
// http://localhost:8080/ssh/WEB-INF/pages/pc/db/install.jsp
// /ssh/WEB-INF/pages/pc/db/install.jsp
// /ssh
// /WEB-INF/pages/pc/db/install.jsp
//         out.print(request.getRequestURL() + "<br>");
//         out.print(request.getRequestURI() + "<br>");
//         out.print(request.getContextPath() + "<br>");
//         out.print(request.getServletPath() + "<br>");
    %>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label for="srcPath" >srcPath</label>
<!--                         <div class="col-sm-10"> -->
                            <input type="text" class="form-control" id="srcPath" name="srcPath" value="D:\mysql-5.6.35-winx64.zip"/>
<!--                         </div> -->
                    </div>
                    <div class="form-group">
                        <label for="unZipPath">unZipPath</label><input type="text" class="form-control" id="unZipPath" name="unZipPath" value="D:\" />
                    </div>
                    <div class="form-group">
                        <label for="port">port</label><input type="text" class="form-control" id="port" name="port" value="3308"/>
                    </div>
                    <div class="form-group">
                        <label for="serviceName">serviceName</label><input type="text" class="form-control" id="serviceName" name="serviceName" value="mysql_3308"/>
                    </div>
                    <div class="form-group">
                        <label for="root-password">root password</label><input type="text" class="form-control" id="rootPassword" name="rootPassword" value="654321"/>
                    </div>
<!--                     <div class="form-group"> -->
<!--                         <label for="dbPath">File input</label><input type="file" id="dbPath" name="dbPath"/> -->
<!--                     </div> -->
                    <div class="form-group">
                        <div class="form-group">
                            <label for="log">log</label>
                            <button type="button" class="btn e float-right clearLog" onclick="clearLog()">clear log</button>
                        </div>
                        <div id="log" class="form-control" style="overflow-y: scroll;height: 200px" disabled="disabled" ></div>
                    </div>
                     <button type="button" class="btn e install" onclick="installDb()">install</button>
                     <button type="button" class="btn e delete" onclick="deleteDb()">delete service and mysqlfile</button>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    function getLog() {
       $.post('<%out.print(hostName);%>/db/getInstallLog', function(result) {
           if(result.code == 0) {
               $("#log").append(result.body);
               $("#log").scrollTop($("#log")[0].scrollHeight);
           }
       });
    }
    var mysqlPath = "";
    function installDb() {
       $(".install").attr("disabled", true); 
       $.post('<%out.print(hostName);%>/db/installDb', $("form").serialize(), function(result) {
                   clearInterval(interval);
    //                if(result.code == 0) {
    //                }
                    mysqlPath = result.body;
                    $(".delete").show();
                    $(".delete").attr("disabled", false); 
                   //暂停后有可能最后一次日志没打印出来
                   getLog();
               });
               var interval = setInterval(getLog, 300); //getLog()无效
           }
           function deleteDb() {
               $(".delete").attr("disabled",true); 
               $.post('<%out.print(hostName);%>/db/deleteDb', {"serviceName" : $("#serviceName").val(), "unZipPath" : mysqlPath }, function(result) {
           clearInterval(interval);
           if(result.code == 0) {
               $(".install").attr("disabled", false); 
           }
           //暂停后有可能最后一次日志没打印出来
           getLog();
       });
       var interval = setInterval(getLog, 300); //getLog()无效
    }
    function clearLog() {
       $("#log").html("");
    }
    </script>
</body>
</html>