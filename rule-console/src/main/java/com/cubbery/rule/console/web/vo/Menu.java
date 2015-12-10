/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.console.web.vo;

import com.cubbery.rule.repository.SceneVo;
import com.cubbery.rule.repository.data.DataVo;
import com.cubbery.rule.repository.rule.RuleVo;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.console.web.vo <br>
 * <b>类名称</b>： Menu <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/10 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Menu implements Serializable {
    private long id;
    private String text;
    private boolean checkbox;
    private boolean checked;
    private String iconCls;
    private String state;//'closed' : 'open'
    private List<Menu> children;

    public static Menu fromScene(SceneVo sceneVo) {
        Menu menu = new Menu();
        menu.setText(sceneVo.getDisplayName());
        menu.setIconCls("icon-scene");
        menu.setId(sceneVo.getId());

        List<Menu> child = Lists.newArrayList();
        //规则库
        if(sceneVo.getRuleRepositories() != null) {
            for(RuleVo ruleVo : sceneVo.getRuleRepositories()) {
                child.add(fromRule(ruleVo));
            }
        }

        //对象库
        if(sceneVo.getDataRepositories() != null) {
            child.add(fromData(sceneVo.getId()));
        }
        menu.setChildren(child);
        return menu;
    }

    public static Menu fromRule(RuleVo ruleVo) {
        Menu menu = new Menu();
        menu.setText(ruleVo.getDisplayName());
        switch (ruleVo.getType()) {
            case SINGLE:
                menu.setIconCls("icon-rule");break;
            case SETS:
                menu.setIconCls("icon-package");
                if(ruleVo.getChildrens() != null) {
                    List<Menu> child = Lists.newArrayList();
                    for(RuleVo cell : ruleVo.getChildrens()) {
                        child.add(fromRule(cell));
                    }
                    menu.setChildren(child);
                }
                break;
        }
        menu.setId(ruleVo.getId());
        return menu;
    }

    public static Menu fromData(Long sceneId) {
        Menu menu = new Menu();
        menu.setText("对象库");
        menu.setIconCls("icon-data");
        menu.setId(sceneId * 100);
        return menu;
    }

    public static Menu fromData(List<DataVo> dataVos) {
        Menu menu = new Menu();
        menu.setText("对象库");
        menu.setIconCls("icon-data");
        menu.setId(100);
        return menu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
