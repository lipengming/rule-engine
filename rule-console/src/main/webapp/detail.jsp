<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>规则配置引擎</title>
    <link rel="stylesheet" type="text/css" href="media/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="media/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="media/rule.css">

    <script type="text/javascript" src="media/jquery.min.js"></script>
    <script type="text/javascript" src="media/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="media/easyui-tabs.js"></script>
    <script type="text/javascript" src="media/rule.js"></script>

</head>
<body class="easyui-layout" >

<!--上边栏-->
<div region="north" title="" split="true" style="height:36px;padding:0px;background:#99BBE8;">
    <div style="width:100%;" id="toolbar">
        <TABLE width="100%" align="center" style="word-break: break-all;" cellpadding="0" cellspacing="0">
            <tr>
                <td style="width:80%;" class="toolbar_left">
                    <a href="#" onClick="javascript:toSave()" id="savebutton" class="easyui-linkbutton" plain="true" iconCls="icon-SaveItem">首页</a>
                </td>
                <td style="width:20%;" class="toolbar_right">
                    规则包
                </td>
            </tr>
        </table>
    </div>
</div>

<%--<!--下边栏-->--%>
<%--<div data-options="region:'south',split:true" style="height:50px;">--%>
<%--<div class="copy"></div>--%>
<%--</div>--%>

<!--右边栏-->
<div data-options="region:'east',split:true" title="属性窗口" style="width:268px;">
    <div id="propTabs" class="easyui-tabs"  border="false" style="height:450px">
        <div iconCls="icon-reload" title="属性" split="true" style="width:268px;">
            <table id="prop_table"></table>
        </div>
        <div id="bom_region" iconCls="icon-reload" title="数据对象" split="true" style="width:268px;">
            <ul id="bom_tree"></ul>
        </div>
    </div>
</div>

<!--左边栏-->
<div data-options="region:'west',split:true" title="规则场景" style="width:180px;padding1:1px;">
    <ul id="tree_menu"></ul>
</div>

<!--中间栏-->
<div data-options="region:'center',title:'编辑窗口',iconCls:'icon-ok'">
    <div id="main-tabs" class="easyui-tabs" data-options="fit:true,border:false" style="overflow:hidden;">

    </div>
</div>
</body>
<script type="text/javascript">

    $('#tree_menu').tree({
        url:'/menu.htm',
        onClick: function(node){
            var type=node.iconCls.replace('icon-', '');
            clickNode(type,node.id);
        },
        onContextMenu: function(e, node){
            e.preventDefault();
            var mmid = '#mm_menu';
            $(mmid).menu('show', {
                left: e.pageX,
                top: e.pageY
            })
        }
    });

    function clickNode(type,theUuid) {
        clearTabs();

        var href; var title;
        if(type=='scene'){
            href = "/scene.htm?id="+theUuid;
            title = "场景";
        }
        else if(type=='package'){
            href = "/package.htm?id="+theUuid;
            title = "规则集";
        }
        else if(type=='rule'){
            href = "/rule.htm?id="+theUuid;
            title = "规则";
        }
        else if(type=='data'){
            addTabs([{
                href : "/data.htm?type=0&id="+theUuid,
                title : "传入数据"
            },{
                href : "/data.htm?type=1&id="+theUuid,
                title : "临时数据"
            },{
                href : "/data.htm?type=2&id="+theUuid,
                title : "外部调用"
            },{
                href : "/data.htm?type=3&id="+theUuid,
                title : "常量数据"
            }]);
            return;
        }
        addTab(title,href);
    }

</script>
</html>

<div id="mm_menu" class="easyui-menu"  style="width:150px;">
    <div data-options="iconCls:'icon-undo'">撤销</div>
    <div data-options="iconCls:'icon-redo'">Redo</div>
    <div class="menu-sep"></div>
    <div>剪切</div>
    <div>复制</div>
    <div>粘贴</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove'">删除</div>
    <div>全选</div>
</div>