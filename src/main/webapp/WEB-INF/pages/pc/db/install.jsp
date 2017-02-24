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
</head>
<body>
    <%
// 访问路径为http://localhost:8080/ssh/db/install
// http://localhost:8080/ssh/WEB-INF/pages/pc/db/install.jsp
// /ssh/WEB-INF/pages/pc/db/install.jsp
// /ssh
// /WEB-INF/pages/pc/db/install.jsp
        out.print(request.getRequestURL() + "<br>");
        out.print(request.getRequestURI() + "<br>");
        out.print(request.getContextPath() + "<br>");
        out.print(request.getServletPath() + "<br>");
    %>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" action="<%out.print(hostName);%>/db/installDb" method="POST">
                    <div class="form-group">
                        <label for="path">path</label><input type="text" class="form-control" id="path" name="path"/>
                    </div>
                    <div class="form-group">
                        <label for="port">port</label><input type="text" class="form-control" id="port" name="port"/>
                    </div>
                    <div class="form-group">
                        <label for="serviceName">serviceName</label><input type="text" class="form-control" id="serviceName" name="serviceName"/>
                    </div>
<!--                     <div class="form-group"> -->
<!--                         <label for="dbPath">File input</label><input type="file" id="dbPath" name="dbPath"/> -->
<!--                     </div> -->
                     <button type="submit" class="btn btn-default" >Submit</button>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
       
    </script>
</body>
</html>