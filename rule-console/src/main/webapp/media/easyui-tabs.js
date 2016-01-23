$.extend($.fn.tabs.methods,{
    allTabs:function(jq){
        var tabs = $(jq).tabs('tabs');
        var all = [];
        all = $.map(tabs,function(n,i){
            return $(n).panel('options')
        });
        return all;
    },
    closeCurrent: function(jq){ // 关闭当前
        var currentTab = $(jq).tabs('getSelected'),
            currentTabIndex = $(jq).tabs('getTabIndex',currentTab);
        $(jq).tabs('close',currentTabIndex);
    },
    closeAll:function(jq){ //关闭全部
        var tabs = $(jq).tabs('allTabs');
        $.each(tabs,function(i,n){
            $(jq).tabs('close', n.title);
        })
    },
    closeOther:function(jq){ //关闭除当前标签页外的tab页
        var tabs =$(jq).tabs('allTabs');
        var currentTab = $(jq).tabs('getSelected'),
            currentTabIndex = $(jq).tabs('getTabIndex',currentTab);

        $.each(tabs,function(i,n){
            if(currentTabIndex != i) {
                $(jq).tabs('close', n.title);
            }
        })
    },
    closeLeft:function(jq){ // 关闭当前页左侧tab页
        var tabs = $(jq).tabs('allTabs');
        var currentTab = $(jq).tabs('getSelected'),
            currentTabIndex = $(jq).tabs('getTabIndex',currentTab);
        var i = currentTabIndex-1;

        while(i > -1){
            $(jq).tabs('close', tabs[i].title);
            i--;
        }
    },
    closeRight: function(jq){ //// 关闭当前页右侧tab页
        var tabs = $(jq).tabs('allTabs');
        var currentTab = $(jq).tabs('getSelected'),
            currentTabIndex = $(jq).tabs('getTabIndex',currentTab);
        var i = currentTabIndex+ 1,len = tabs.length;
        while(i < len){
            $(jq).tabs('close', tabs[i].title);
            i++;
        }
    }
})