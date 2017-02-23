<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="/ssh/resources/js/common/common.js"></script>
<script type="text/javascript" src="/ssh/resources/js/jquery/jquery-1.10.2.js"></script>
<body>
    <div>
        <span>第<input type="text" name="page" id="page">w页
            <input type="button" value="确定" onclick="getSelectTime()">
        </span>
    </div>
    <div id="info"></div>
</body>
<script type="text/javascript">
function getSelectTime(){
    var p = Number($("#page").val()) * 10000;
    $.post(hostName + "/i18n/getSelectTest",{page:p}, function(result) {
        if(result.code == 0) {
            $("#info").html(result.body);
        }
    });
}
</script>
</html>
