/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.dal.domin;

import java.util.Date;
import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.dal.domin <br>
 * <b>类名称</b>： RuleVo <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RuleVo extends BaseVo {
    private String name;
    private String description;
    private ConditionVo condition;//条件
    private InstructionVo instruction;//结果
    private List<FactVo> facts;
    private boolean enabled;
    private Date begin;//规则开始时间
    private Date end;//规则结束时间

    public List<FactVo> getFacts() {
        return facts;
    }

    public void setFacts(List<FactVo> facts) {
        this.facts = facts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConditionVo getCondition() {
        return condition;
    }

    public void setCondition(ConditionVo condition) {
        this.condition = condition;
    }

    public InstructionVo getInstruction() {
        return instruction;
    }

    public void setInstruction(InstructionVo instruction) {
        this.instruction = instruction;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
