/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-02-22 02:33:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.pc.i18n;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link href=\"/resources/css/ext4.2.1/ext-theme-classic/ext-theme-classic-all.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<!-- <link href=\"/resources/js/ext4.2.1/modern/theme-cupertino/resources/theme-cupertino-all.css\" rel=\"stylesheet\" type=\"text/css\" /> -->\r\n");
      out.write("       <script type=\"text/javascript\" src=\"/resources/js/jquery/jquery-1.10.2.js\"></script>\r\n");
      out.write("       <script src=\"/resources/js/ext4.2.1/ext-all.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("       <script src=\"/resources/js/ext4.2.1/ext-lang-zh_CN.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("<!--        <script src=\"/resources/js/ext4.2.1/ext-modern-all.js\" type=\"text/javascript\" ></script> -->\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>国际化列表</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("       <div id=\"data-grid\"></div>\r\n");
      out.write("       <script type=\"text/javascript\">\r\n");
      out.write("       var store, grid, win, form, selectedStoreIndex;\r\n");
      out.write("       Ext.onReady(function() {\r\n");
      out.write("           /** \r\n");
      out.write("            * 处理Grid重新加载过后selectionModel中的记录不更新的问题 \r\n");
      out.write("            * me.selected中存放的是选中的记录的集合 \r\n");
      out.write("            */  \r\n");
      out.write("           Ext.override(Ext.selection.Model,{  \r\n");
      out.write("               onStoreLoad:function(store, records, successful, eOpts){  \r\n");
      out.write("                   var me = this,  \r\n");
      out.write("                       length = me.selected.getCount( );  \r\n");
      out.write("                     \r\n");
      out.write("                   //如果没有选中的记录，则不需要进行任何的操作  \r\n");
      out.write("                   if(length===0)return;  \r\n");
      out.write("                     \r\n");
      out.write("                   //遍历selected并更新其中的记录  \r\n");
      out.write("                   me.selected.eachKey(function(key,item){  \r\n");
      out.write("                       var model = store.getById(key);  \r\n");
      out.write("                         \r\n");
      out.write("                       //如果获取到了model就更新，否则从selected中移除  \r\n");
      out.write("                       if(model){  \r\n");
      out.write("                           me.selected.add(model);//add时会覆盖掉原来的值  \r\n");
      out.write("                       }else{  \r\n");
      out.write("                           me.selected.removeAtKey(key);  \r\n");
      out.write("                       }  \r\n");
      out.write("                   })  \r\n");
      out.write("                     \r\n");
      out.write("               }  \r\n");
      out.write("           });  \r\n");
      out.write("           Ext.define('roleModel', {\r\n");
      out.write("               extend : 'Ext.data.Model',\r\n");
      out.write("               fields : [ \"id\", \"username\", \"password\", \"realname\", \"tel\", \"address\", \"company\", \"sex\", \"age\", \"qq\", \"email\" ],\r\n");
      out.write("               idProperty : 'id'\r\n");
      out.write("           });\r\n");
      out.write("           store = Ext.create('Ext.data.Store', {\r\n");
      out.write("           pageSize : 20,\r\n");
      out.write("           model : 'roleModel',\r\n");
      out.write("           remoteSort : false,\r\n");
      out.write("           autoLoad : true,\r\n");
      out.write("           proxy : {\r\n");
      out.write("               type : 'ajax',\r\n");
      out.write("               url : '/i18n/getUserList',\r\n");
      out.write("               actionMethods:{\r\n");
      out.write("                   create: \"POST\", read: \"POST\", update: \"POST\", destroy: \"POST\"\r\n");
      out.write("               },\r\n");
      out.write("               reader : {\r\n");
      out.write("                   type : 'json',\r\n");
      out.write("                   root : 'rows',\r\n");
      out.write("                   totalProperty : 'total'\r\n");
      out.write("               }\r\n");
      out.write("           }\r\n");
      out.write("           });\r\n");
      out.write("\r\n");
      out.write("           grid = Ext.create('Ext.grid.Panel', {\r\n");
      out.write("               store : store,\r\n");
      out.write("               multiSelect : false,\r\n");
      out.write("               height : 510,\r\n");
      out.write("               layout : 'fit',\r\n");
      out.write("               columns : [ {\r\n");
      out.write("                   text : 'id',\r\n");
      out.write("                   dataIndex : 'id',\r\n");
      out.write("                   minWidth:90,\r\n");
      out.write("                   align : 'center'\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"username\",\r\n");
      out.write("                   dataIndex : 'username',\r\n");
      out.write("                   minWidth:150,\r\n");
      out.write("                   align : 'left'\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"password\",\r\n");
      out.write("                   dataIndex : 'password',\r\n");
      out.write("                   minWidth: 150,\r\n");
      out.write("                   align : 'left'\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"realname\",\r\n");
      out.write("                   dataIndex : 'realname',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center'\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"tel\",\r\n");
      out.write("                   dataIndex : 'tel',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center',\r\n");
      out.write("                   renderer : function(value){\r\n");
      out.write("                       return value;\r\n");
      out.write("                   }\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"address\",\r\n");
      out.write("                   dataIndex : 'address',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center',\r\n");
      out.write("                   renderer : function(value){\r\n");
      out.write("                       return value;\r\n");
      out.write("                   }\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"company\",\r\n");
      out.write("                   dataIndex : 'company',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center',\r\n");
      out.write("                   renderer : function(value){\r\n");
      out.write("                       return value;\r\n");
      out.write("                   }\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"sex\",\r\n");
      out.write("                   dataIndex : 'sex',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center',\r\n");
      out.write("                   renderer : function(value){\r\n");
      out.write("                       return value;\r\n");
      out.write("                   }\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"age\",\r\n");
      out.write("                   dataIndex : 'age',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center',\r\n");
      out.write("                   renderer : function(value){\r\n");
      out.write("                       return value;\r\n");
      out.write("                   }\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"qq\",\r\n");
      out.write("                   dataIndex : 'qq',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center',\r\n");
      out.write("                   renderer : function(value){\r\n");
      out.write("                       return value;\r\n");
      out.write("                   }\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : \"email\",\r\n");
      out.write("                   dataIndex : 'email',\r\n");
      out.write("                   minWidth: 90,\r\n");
      out.write("                   align : 'center',\r\n");
      out.write("                   renderer : function(value){\r\n");
      out.write("                       return value;\r\n");
      out.write("                   }\r\n");
      out.write("               }],\r\n");
      out.write("               listeners : {\r\n");
      out.write("                   'itemclick' : function(view, record, item, index, e) {\r\n");
      out.write("                   }\r\n");
      out.write("               },\r\n");
      out.write("               tbar :[\r\n");
      out.write("                   {text: '刷新',iconCls:'a_refresh', handler: sp_refresh},\r\n");
      out.write("                   {text: '新增',iconCls:'a_add', handler: sp_add},\r\n");
      out.write("                   {text: '修改',iconCls:'a_edit', handler: sp_update},\r\n");
      out.write("                   {text: '删除',iconCls:'a_delete', handler: sp_delete}\r\n");
      out.write("               ],\r\n");
      out.write("               bbar : [ {\r\n");
      out.write("                   xtype : 'pagingtoolbar',\r\n");
      out.write("                   store : store,\r\n");
      out.write("                   displayMsg : '显示 {0} - {1} 条，共计 {2} 条',\r\n");
      out.write("                   emptyMsg : '没有数据',\r\n");
      out.write("                   beforePageText : '当前页',\r\n");
      out.write("                   afterPageText : '共{0}页',\r\n");
      out.write("                   displayInfo : true\r\n");
      out.write("               } ],\r\n");
      out.write("               renderTo : 'data-grid'\r\n");
      out.write("           });\r\n");
      out.write("           store.on('beforeload', function(store, options) {\r\n");
      out.write("               var searchParams = {\r\n");
      out.write("\t\t\t\t   /*\r\n");
      out.write("                   keyWord : $(\"#keyWord\").val(),\r\n");
      out.write("                   language : $(\"#language\").val()\r\n");
      out.write("\t\t\t\t   */\r\n");
      out.write("               };\r\n");
      out.write("               Ext.apply(store.proxy.extraParams, searchParams);\r\n");
      out.write("           });\r\n");
      out.write("           form = Ext.create('Ext.form.Panel', {\r\n");
      out.write("               frame:true,\r\n");
      out.write("               bodyPadding: 0,\r\n");
      out.write("               layout:'form',\r\n");
      out.write("               style:'border-width:0 0 0 0',\r\n");
      out.write("               items : [{\r\n");
      out.write("                   layout:'column',\r\n");
      out.write("                   frame:true,\r\n");
      out.write("                   style:'border-width:0 0 0 0',\r\n");
      out.write("                   padding: 0,\r\n");
      out.write("                   defaults :{\r\n");
      out.write("                       padding: 0\r\n");
      out.write("                   },\r\n");
      out.write("                   items:[{\r\n");
      out.write("                           defaults :{\r\n");
      out.write("                               labelWidth:60,\r\n");
      out.write("                               labelAlign:'right',\r\n");
      out.write("                           },\r\n");
      out.write("                           columnWidth: .5,\r\n");
      out.write("                           style:'border-width:0 0 0 0',\r\n");
      out.write("                           frame:true,\r\n");
      out.write("                           layout:'form',\r\n");
      out.write("                           defaultType:'textfield',\r\n");
      out.write("                           items:[{\r\n");
      out.write("                                   fieldLabel : \"id\",\r\n");
      out.write("                                   name : 'id',\r\n");
      out.write("                                   readOnly:true,\r\n");
      out.write("                                   hidden:true\r\n");
      out.write("                               }, {\r\n");
      out.write("                                   fieldLabel :\"username\",\r\n");
      out.write("                                   name : \"username\",\r\n");
      out.write("                                   allowBlank : false\r\n");
      out.write("                               }, {\r\n");
      out.write("                                   fieldLabel : \"password\",\r\n");
      out.write("                                   name : 'password',\r\n");
      out.write("                               }, {\r\n");
      out.write("                                   fieldLabel : 'realname',\r\n");
      out.write("                                   name : 'realname',\r\n");
      out.write("                               }, {\r\n");
      out.write("                                   fieldLabel : 'tel',\r\n");
      out.write("                                   name : 'tel',\r\n");
      out.write("                               }, {\r\n");
      out.write("                                   fieldLabel : 'qq',\r\n");
      out.write("                                   name : 'qq',\r\n");
      out.write("                               }\r\n");
      out.write("                           ]\r\n");
      out.write("                       },{\r\n");
      out.write("                           defaults :{\r\n");
      out.write("                               labelWidth:60,\r\n");
      out.write("                               labelAlign:'right',\r\n");
      out.write("                           },\r\n");
      out.write("                           layout:'form',\r\n");
      out.write("                           columnWidth: .5,\r\n");
      out.write("                           frame:true,\r\n");
      out.write("                           style:'border-width:0 0 0 0',\r\n");
      out.write("                           defaultType:'textfield',\r\n");
      out.write("                           items:[{\r\n");
      out.write("                               fieldLabel : 'address',\r\n");
      out.write("                               name : 'address',\r\n");
      out.write("                           }, {\r\n");
      out.write("                               fieldLabel : 'company',\r\n");
      out.write("                               name : 'company',\r\n");
      out.write("                           }, {\r\n");
      out.write("                               fieldLabel : 'sex',\r\n");
      out.write("                               name : 'sex',\r\n");
      out.write("                           }, {\r\n");
      out.write("                               fieldLabel : 'age',\r\n");
      out.write("                               name : 'age',\r\n");
      out.write("                           }, {\r\n");
      out.write("                               fieldLabel : 'email',\r\n");
      out.write("                               name : 'email',\r\n");
      out.write("                           }]\r\n");
      out.write("                       }]\r\n");
      out.write("               } /*, {\r\n");
      out.write("                   labelWidth: 60,\r\n");
      out.write("                   labelAlign:'right',\r\n");
      out.write("                   fieldLabel : 'remark',\r\n");
      out.write("                   xtype:'textarea',\r\n");
      out.write("                   name : 'remark'\r\n");
      out.write("               }*/],\r\n");
      out.write("               buttons : [ {\r\n");
      out.write("                   text : '取消',\r\n");
      out.write("                   handler : function() {\r\n");
      out.write("                       win.close();\r\n");
      out.write("                   }\r\n");
      out.write("               }, {\r\n");
      out.write("                   text : '保存',\r\n");
      out.write("                   formBind : true, // only enabled once the form is valid\r\n");
      out.write("                   disabled : true,\r\n");
      out.write("                   handler : function () {\r\n");
      out.write("                       if (form.isValid()) {\r\n");
      out.write("//                            if(selectedStoreIndex != null) {\r\n");
      out.write("//                                var o = form.getValues();\r\n");
      out.write("//                                for(var i in o) {\r\n");
      out.write("//                                    store.getAt(selectedStoreIndex).set(i, o[i]);\r\n");
      out.write("//                                }\r\n");
      out.write("//                            } else {\r\n");
      out.write("//                                store.insert(store.getCount(), form.getValues());\r\n");
      out.write("//                            }\r\n");
      out.write("                            var o = form.getValues();\r\n");
      out.write("                            $.ajax({\r\n");
      out.write("                                    url : '/i18n/addOrUpdateUser',\r\n");
      out.write("                                    type : \"post\",\r\n");
      out.write("                                    contentType: \"application/json\",\r\n");
      out.write("                                    data : JSON.stringify(o),\r\n");
      out.write("                                    success : function(result) {\r\n");
      out.write("                                        if (result.code == 0){\r\n");
      out.write("                                            Ext.Msg.alert(\"提示\", \"保存成功\", function(result){\r\n");
      out.write("                                                if(result){\r\n");
      out.write("                                                    sp_refresh();\r\n");
      out.write("                                                }\r\n");
      out.write("                                            });\r\n");
      out.write("                                        } else {\r\n");
      out.write("                                            Ext.Msg.alert(\"提示\", \"保存失败\" + result.message);\r\n");
      out.write("                                        }\r\n");
      out.write("                                    }\r\n");
      out.write("                                });\r\n");
      out.write("                            win.close();\r\n");
      out.write("                       }\r\n");
      out.write("                   }\r\n");
      out.write("               } ],\r\n");
      out.write("           });\r\n");
      out.write("           win = new Ext.Window({  \r\n");
      out.write("               width: 1000,\r\n");
      out.write("               resizable: false,\r\n");
      out.write("               closeAction: 'close',\r\n");
      out.write("               closable: true,  \r\n");
      out.write("               modal: 'true',\r\n");
      out.write("               buttonAlign: \"center\",\r\n");
      out.write("               items: [form]\r\n");
      out.write("           });\r\n");
      out.write("       });\r\n");
      out.write("       function sp_refresh() {\r\n");
      out.write("           //不宜用loadPage(1);\r\n");
      out.write("           store.reload();\r\n");
      out.write("       }\r\n");
      out.write("       function sp_add() {\r\n");
      out.write("           form.getForm().reset();\r\n");
      out.write("           win.setTitle(\"Add User\");\r\n");
      out.write("           win.show();\r\n");
      out.write("       }\r\n");
      out.write("       function sp_update() {\r\n");
      out.write("           var no = null;\r\n");
      out.write("           var selection = grid.getView().getSelectionModel().getSelection()[0];\r\n");
      out.write("           if(selection){\r\n");
      out.write("               selectedStoreIndex = store.indexOf(selection);\r\n");
      out.write("               form.loadRecord(selection);\r\n");
      out.write("               win.setTitle(\"Update User\");\r\n");
      out.write("               win.show();\r\n");
      out.write("           }else{\r\n");
      out.write("               Ext.Msg.alert(\"提示\", \"请选择用户\");\r\n");
      out.write("           }\r\n");
      out.write("       }\r\n");
      out.write("       function sp_delete(){\r\n");
      out.write("           var id = null;\r\n");
      out.write("           var selection = grid.getView().getSelectionModel().getSelection()[0];\r\n");
      out.write("           if (selection != undefined) {\r\n");
      out.write("               id = selection.data.id;\r\n");
      out.write("               Ext.Msg.confirm(\"提示\", \"确认删除吗？\", function(res) {\r\n");
      out.write("                   if(res==\"yes\") {\r\n");
      out.write("                       $.post('/i18n/deleteUser', [ {\r\n");
      out.write("                           name : 'id',\r\n");
      out.write("                           value : id\r\n");
      out.write("                       } ], function(result) {\r\n");
      out.write("                           if (result.code == 0) {\r\n");
      out.write("                               Ext.Msg.alert(\"提示\", \"删除成功\", function() {\r\n");
      out.write("                                   sp_refresh();\r\n");
      out.write("                               });\r\n");
      out.write("                           } else {\r\n");
      out.write("                               Ext.Msg.alert(\"提示\", result.message);\r\n");
      out.write("                           }\r\n");
      out.write("                       });\r\n");
      out.write("                   } else {\r\n");
      out.write("                   }\r\n");
      out.write("               });\r\n");
      out.write("           }else{\r\n");
      out.write("               Ext.MessageBox.alert(\"提示\", \"请选择行\");\r\n");
      out.write("           }\r\n");
      out.write("       }\r\n");
      out.write("       </script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
