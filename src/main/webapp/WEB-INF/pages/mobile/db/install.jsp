<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
    String hostName = request.getContextPath();
%>
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
                <form role="form">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label><input type="email" class="form-control" id="exampleInputEmail1" />
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label><input type="password" class="form-control" id="exampleInputPassword1" />
                    </div>
                    <div class="form-group">
                        <label for="exampleInputFile">File input</label><input type="file" id="exampleInputFile" />
                        <p class="help-block">Example block-level help text here.</p>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" />Check me out</label>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <button type="button" class="btn btn-default">按钮</button>
            </div>
        </div>
    </div>
</body>
</html>