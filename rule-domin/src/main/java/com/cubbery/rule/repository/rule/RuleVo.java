/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.repository.rule;

import com.cubbery.rule.repository.BaseVo;
import com.cubbery.rule.repository.code.RuleType;

import java.util.Date;
import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.repository.rule <br>
 * <b>类名称</b>： RuleVo <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/4 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RuleVo extends BaseVo {
    private String   enable;//1、0，
    private String   name;//英文名
    private String   displayName;//显示名称（中文）

    //drools自带的规则属性
    private Date    effectiveDate;//设置规则的过期时间，默认的时间格式：“日-月-年”
    private Date    expiresDate;//设置规则的生效时间，时间格式同上。
    private Integer salience;//优先级，数值越大越先执行，这个可以控制规则的执行顺序。
    private Long    duration;//规则定时，duration 3000   3秒后执行规则
    private Boolean loop;//是否不允许多次循环执行，默认是false。当rule的rhs中修改了一个fact，这可能引擎这个rule自此被activate。引起递归。
    private Boolean lockOnActive;//lock-on-active true：通过这个标签，可以控制当前的规则只会被执行一次，因为一个规则的重复执行不一定是本身触发的，也可能是其他规则触发的，所以这个是no-loop的加强版。
    private String  activationGroup;//同一个activication fire后，zhege activie-group中的其余剩下的activcation都不会被fire。
    private String  agendaGroup;//允许用户对agenda进行分组，以提供更多的执行控制。只有具有交掉的组中的activication才会诶激发。
    private Boolean autoFocus;//默认false,当一个规则被activate，乳沟autofocus为true，并且这个rule的agend-group没有焦点，此时activation会被基于叫丢安，运行这个activation有fire的肯能，

    //drools 规则部分
    private String  lhs;//条件部分。
    private String  rhs;//结果部分。（动作部分）[可以执行java代码外，还可以modified等,概述引擎fact已经变化，需要重新匹配在lhs出现的fact]

    private RuleType type;//规则类型，默认为单个规则
    private List<RuleVo> childrens;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    public Integer getSalience() {
        return salience;
    }

    public void setSalience(Integer salience) {
        this.salience = salience;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getLoop() {
        return loop;
    }

    public void setLoop(Boolean loop) {
        this.loop = loop;
    }

    public Boolean getLockOnActive() {
        return lockOnActive;
    }

    public void setLockOnActive(Boolean lockOnActive) {
        this.lockOnActive = lockOnActive;
    }

    public String getActivationGroup() {
        return activationGroup;
    }

    public void setActivationGroup(String activationGroup) {
        this.activationGroup = activationGroup;
    }

    public String getAgendaGroup() {
        return agendaGroup;
    }

    public void setAgendaGroup(String agendaGroup) {
        this.agendaGroup = agendaGroup;
    }

    public Boolean getAutoFocus() {
        return autoFocus;
    }

    public void setAutoFocus(Boolean autoFocus) {
        this.autoFocus = autoFocus;
    }

    public String getLhs() {
        return lhs;
    }

    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    public String getRhs() {
        return rhs;
    }

    public void setRhs(String rhs) {
        this.rhs = rhs;
    }

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public List<RuleVo> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<RuleVo> childrens) {
        this.childrens = childrens;
    }
}
