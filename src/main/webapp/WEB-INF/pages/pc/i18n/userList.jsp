<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/css/ext4.2.1/ext-theme-classic/ext-theme-classic-all.css" rel="stylesheet" type="text/css" />
<!-- <link href="/resources/js/ext4.2.1/modern/theme-cupertino/resources/theme-cupertino-all.css" rel="stylesheet" type="text/css" /> -->
       <script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.js"></script>
       <script src="/resources/js/ext4.2.1/ext-all.js" type="text/javascript" ></script>
       <script src="/resources/js/ext4.2.1/ext-lang-zh_CN.js" type="text/javascript" ></script>
<!--        <script src="/resources/js/ext4.2.1/ext-modern-all.js" type="text/javascript" ></script> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国际化列表</title>
</head>
<body>
       <div id="data-grid"></div>
       <script type="text/javascript">
       var store, grid, win, form;
       Ext.onReady(function() {
           Ext.define('roleModel', {
               extend : 'Ext.data.Model',
               fields : [ "id", "username", "password", "realname", "tel", "address", "company", "sex", "age", "qq", "email" ],
               idProperty : 'id'
           });
           store = Ext.create('Ext.data.Store', {
           pageSize : 20,
           model : 'roleModel',
           remoteSort : false,
           autoLoad : true,
           proxy : {
               type : 'ajax',
               url : '/i18n/getUserList',
               actionMethods:{
                   create: "POST", read: "POST", update: "POST", destroy: "POST"
               },
               reader : {
                   type : 'json',
                   root : 'rows',
                   totalProperty : 'total'
               }
           }
           });

           grid = Ext.create('Ext.grid.Panel', {
               store : store,
               multiSelect : false,
               height : 510,
               layout : 'fit',
               columns : [ {
                   text : 'id',
                   dataIndex : 'id',
                   minWidth:90,
                   align : 'center'
               }, {
                   text : "username",
                   dataIndex : 'username',
                   minWidth:150,
                   align : 'left'
               }, {
                   text : "password",
                   dataIndex : 'password',
                   minWidth: 150,
                   align : 'left'
               }, {
                   text : "realname",
                   dataIndex : 'realname',
                   minWidth: 90,
                   align : 'center'
               }, {
                   text : "tel",
                   dataIndex : 'tel',
                   minWidth: 90,
                   align : 'center',
                   renderer : function(value){
                       return value;
                   }
               }, {
                   text : "address",
                   dataIndex : 'address',
                   minWidth: 90,
                   align : 'center',
                   renderer : function(value){
                       return value;
                   }
               }, {
                   text : "company",
                   dataIndex : 'company',
                   minWidth: 90,
                   align : 'center',
                   renderer : function(value){
                       return value;
                   }
               }, {
                   text : "sex",
                   dataIndex : 'sex',
                   minWidth: 90,
                   align : 'center',
                   renderer : function(value){
                       return value;
                   }
               }, {
                   text : "age",
                   dataIndex : 'age',
                   minWidth: 90,
                   align : 'center',
                   renderer : function(value){
                       return value;
                   }
               }, {
                   text : "qq",
                   dataIndex : 'qq',
                   minWidth: 90,
                   align : 'center',
                   renderer : function(value){
                       return value;
                   }
               }, {
                   text : "email",
                   dataIndex : 'email',
                   minWidth: 90,
                   align : 'center',
                   renderer : function(value){
                       return value;
                   }
               }],
               listeners : {
                   'itemclick' : function(view, record, item, index, e) {
                   }
               },
               tbar :[
                   {text: '刷新',iconCls:'a_refresh', handler: sp_refresh},
                   {text: '新增',iconCls:'a_add', handler: sp_add},
                   {text: '修改',iconCls:'a_edit', handler: sp_update},
                   {text: '删除',iconCls:'a_delete', handler: sp_delete}
               ],
               bbar : [ {
                   xtype : 'pagingtoolbar',
                   store : store,
                   displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
                   emptyMsg : '没有数据',
                   beforePageText : '当前页',
                   afterPageText : '共{0}页',
                   displayInfo : true
               } ],
               renderTo : 'data-grid'
           });
           store.on('beforeload', function(store, options) {
               var searchParams = {
				   /*
                   keyWord : $("#keyWord").val(),
                   language : $("#language").val()
				   */
               };
               Ext.apply(store.proxy.extraParams, searchParams);
           });
           form = Ext.create('Ext.form.Panel', {
               frame:true,
               bodyPadding: 0,
               layout:'form',
               style:'border-width:0 0 0 0',
               items : [{
                   layout:'column',
                   frame:true,
                   style:'border-width:0 0 0 0',
                   padding: 0,
                   defaults :{
                       padding: 0
                   },
                   items:[{
                           defaults :{
                               labelWidth:60,
                               labelAlign:'right',
                           },
                           columnWidth: .5,
                           style:'border-width:0 0 0 0',
                           frame:true,
                           layout:'form',
                           defaultType:'textfield',
                           items:[{
                                   fieldLabel : "id",
                                   name : 'id',
                                   readOnly:true,
                                   hidden:true
                               }, {
                                   fieldLabel :"username",
                                   name : "username",
                                   allowBlank : false
                               }, {
                                   fieldLabel : "password",
                                   name : 'password',
                               }, {
                                   fieldLabel : 'realname',
                                   name : 'realname',
                               }, {
                                   fieldLabel : 'tel',
                                   name : 'tel',
                               }, {
                                   fieldLabel : 'qq',
                                   name : 'qq',
                               }
                           ]
                       },{
                           defaults :{
                               labelWidth:60,
                               labelAlign:'right',
                           },
                           layout:'form',
                           columnWidth: .5,
                           frame:true,
                           style:'border-width:0 0 0 0',
                           defaultType:'textfield',
                           items:[{
                               fieldLabel : 'address',
                               name : 'address',
                           }, {
                               fieldLabel : 'company',
                               name : 'company',
                           }, {
                               fieldLabel : 'sex',
                               name : 'sex',
                           }, {
                               fieldLabel : 'age',
                               name : 'age',
                           }, {
                               fieldLabel : 'email',
                               name : 'email',
                           }]
                       }]
               } /*, {
                   labelWidth: 60,
                   labelAlign:'right',
                   fieldLabel : 'remark',
                   xtype:'textarea',
                   name : 'remark'
               }*/],
               buttons : [ {
                   text : '取消',
                   handler : function() {
                       win.close();
                   }
               }, {
                   text : '保存',
                   formBind : true, // only enabled once the form is valid
                   disabled : true,
                   handler : function () {
                       if (form.isValid()) {
//                            if(selectedStoreIndex != null) {
//                                var o = form.getValues();
//                                for(var i in o) {
//                                    store.getAt(selectedStoreIndex).set(i, o[i]);
//                                }
//                            } else {
//                                store.insert(store.getCount(), form.getValues());
//                            }
                            var o = form.getValues();
                            $.ajax({
                                    url : '/i18n/addOrUpdateUser',
                                    type : "post",
                                    contentType: "application/json",
                                    data : JSON.stringify(o),
                                    success : function(result) {
                                        if (result.code == 0){
                                            Ext.Msg.alert("提示", "保存成功", function(result){
                                                if(result){
                                                    sp_refresh();
                                                }
                                            });
                                        } else {
                                            Ext.Msg.alert("提示", "保存失败" + result.message);
                                        }
                                    }
                                });
                            win.close();
                       }
                   }
               } ],
           });
           win = new Ext.Window({  
               width: 1000,
               resizable: false,
               closeAction: 'close',
               closable: true,  
               modal: 'true',
               buttonAlign: "center",
               items: [form]
           });
       });
       function sp_refresh() {
           store.loadPage(1);
       }
       function sp_add() {
           win.setTitle("Add User");
           win.show();
       }
       function sp_update() {
           var no = null;
           var selection = grid.getView().getSelectionModel().getSelection()[0];
           if(selection){
               no = selection.data.no;
               window.location.href = "/crm/editBusinessPartner?no=" + no;
           }else{
               Ext.Msg.alert(siping.tools.i18n.getMessage("message.hint"), siping.tools.i18n.getMessage("message.pleaseSelectRow"));
           }
       }
       function sp_delete(){
           var no = null;
           var selection = grid.getView().getSelectionModel().getSelection()[0];
           if (selection != undefined) {
               no = selection.data.no;
               Ext.Msg.confirm(siping.tools.i18n.getMessage("message.hint"), siping.tools.i18n.getMessage("message.confirmDelete"), function(res) {
                   if(res=="yes") {
                       $.post(siping.systemParams.get("host") + '/crm/deleteBusiPartner', [ {
                           name : 'no',
                           value : no
                       } ], function(result) {
                           if (result.code == 0) {
                               Ext.Msg.alert(siping.tools.i18n.getMessage("message.hint"), siping.tools.i18n.getMessage("message.deleteSuccess"), function() {
                                   location.reload();
                               });
                           } else {
                               Ext.Msg.alert(siping.tools.i18n.getMessage("message.hint"), result.message);
                           }
                       });
                   } else {
                   }
               });
           }else{
               Ext.MessageBox.alert(siping.tools.i18n.getMessage("message.hint"), siping.tools.i18n.getMessage("message.pleaseSelectRow"));
           }
       }
       </script>
</body>
</html>