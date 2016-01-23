<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="media/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="media/themes/icon.css">
    <script type="text/javascript" src="media/jquery.min.js"></script>
    <script type="text/javascript" src="media/jquery.easyui.min.js"></script>

</head>
<body>
<table id="dg" class="easyui-datagrid"
       data-options="url:'/dataA.htm',method:'get',singleSelect:true,fit:true,fitColumns:true,toolbar:'#toolbar'">
    <thead>
        <tr>
            <th data-options="field:'name'" width="80">名称（英文）</th>
            <th data-options="field:'type'" width="100">类型</th>
            <th data-options="field:'displayName',align:'right'" width="80">显示名称</th>
            <th data-options="field:'group',align:'right'" width="80">分组</th>
            <th data-options="field:'nickName',align:'right'" width="80">调用名</th>
            <th data-options="field:'direDesc',align:'right'" width="80">传递方向</th>

        </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">撤销</a>
</div>
</body>
</html>
