<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
    String hostName = request.getContextPath();
%>
<meta http-equiv="Content-Type"  content="text/html; charset=utf-8" />
<meta name="viewport" content=" height = device-height , width = device-width , initial-scale = 1 ,
         minimum-scale = 1 , maximum-scale = 1 , user-scalable = no" />
<script src="<%out.print(hostName);%>/resources/js/bootstrap-select-1.12.2/js/jquery-3.1.1.min.js"></script>

<title>无标题文档</title>  
  
<script type="text/javascript">  
  
var imgurl = "";  
  
function getImgURL(node) {    
    var imgURL = "";      
    try{     
        var file = null;  
        if(node.files && node.files[0] ){  
            file = node.files[0];   
        }else if(node.files && node.files.item(0)) {                                  
            file = node.files.item(0);     
        }     
        //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径  
        try{  
            //Firefox7.0   
            imgURL =  file.getAsDataURL();    
            //alert("//Firefox7.0"+imgRUL);                           
        }catch(e){  
            //Firefox8.0以上                                
            imgRUL = window.URL.createObjectURL(file);  
            //alert("//Firefox8.0以上"+imgRUL);  
        }  
     }catch(e){      //这里不知道怎么处理了，如果是遨游的话会报这个异常                   
        //支持html5的浏览器,比如高版本的firefox、chrome、ie10  
        if (node.files && node.files[0]) {                            
            var reader = new FileReader();   
            reader.onload = function (e) {                                        
                imgURL = e.target.result;    
            };  
            reader.readAsDataURL(node.files[0]);   
        }    
     }  
    //imgurl = imgURL;  
    creatImg(imgRUL);  
    return imgURL;  
}  
         
function creatImg(imgRUL){   //根据指定URL创建一个Img对象  
    var textHtml = "<img src='"+imgRUL+"'/>";  
    $(".mark").after(textHtml);  
}  
         
</script>  
  
</head>  
  
<body>  
    <div style="width:90px; height:110px; overflow:hidden; border:1px solid red;" id="show">  
        <div class="mark"></div>  
    </div>  
<br>  
<input type="file" value="上传文件" onchange="getImgURL(this)">  
</body>  
</html>  