<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="media/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="media/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="media/rule.css">

    <script type="text/javascript" src="media/jquery.min.js"></script>
    <script type="text/javascript" src="media/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="media/rule.js"></script>
</head>
<body>
<div title="传入数据" style="padding:2px">
    <table class="easyui-datagrid"
           data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true,toolbar:toolbar">
        <thead>
        <tr>
            <th data-options="field:'itemid'" width="80">名称（英文）</th>
            <th data-options="field:'productid'" width="100">类型</th>
            <th data-options="field:'listprice',align:'right'" width="80">显示名称</th>
            <th data-options="field:'unitcost',align:'right'" width="80">分组</th>
            <th data-options="field:'unitcost',align:'right'" width="80">调用名</th>
            <th data-options="field:'unitcost',align:'right'" width="80">传递方向</th>
        </tr>
        </thead>
    </table>
</div>
<div title="临时数据" style="padding:2px">
    <table class="easyui-datagrid"
           data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true,toolbar:toolbar">
        <thead>
        <tr>
            <th data-options="field:'itemid'" width="80">名称（英文）</th>
            <th data-options="field:'productid'" width="100">类型</th>
            <th data-options="field:'listprice',align:'right'" width="80">显示名称</th>
            <th data-options="field:'unitcost',align:'right'" width="80">分组</th>
            <th data-options="field:'unitcost',align:'right'" width="80">调用名</th>
        </tr>
        </tr>
        </thead>
    </table>
</div>
<div title="外部调用" style="padding:2px">
    <table class="easyui-datagrid"
           data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true,toolbar:toolbar">
        <thead>
        <tr>
            <th data-options="field:'itemid'" width="80">名称（英文）</th>
            <th data-options="field:'productid'" width="100">类型</th>
            <th data-options="field:'listprice',align:'right'" width="80">显示名称</th>
            <th data-options="field:'unitcost',align:'right'" width="80">分组</th>
            <th data-options="field:'unitcost',align:'right'" width="80">调用名</th>
        </tr>
        </thead>
    </table>
</div>
<div title="常量数据" style="padding:2px">
    <table class="easyui-datagrid"
           data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true,toolbar:toolbar">
        <thead>
        <tr>
            <th data-options="field:'itemid'" width="80">名称（英文）</th>
            <th data-options="field:'productid'" width="100">类型</th>
            <th data-options="field:'listprice',align:'right'" width="80">显示名称</th>
            <th data-options="field:'unitcost',align:'right'" width="80">分组</th>
            <th data-options="field:'unitcost',align:'right'" width="80">调用名</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
