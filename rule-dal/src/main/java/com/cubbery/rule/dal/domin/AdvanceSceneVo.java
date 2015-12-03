/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.dal.domin;

import com.cubbery.rule.dal.code.SceneType;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.dal.domin <br>
 * <b>类名称</b>： AdvanceSceneVo <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class AdvanceSceneVo extends SceneVo {
    private List<FactVo> globals;//全局指令对象
    private List<RuleVo> rules;

    @Override
    public SceneType getSceneType() {
        return SceneType.ADVANCE;
    }

    public List<FactVo> getGlobals() {
        return globals;
    }

    public void setGlobals(List<FactVo> globals) {
        this.globals = globals;
    }

    public List<RuleVo> getRules() {
        return rules;
    }

    public void setRules(List<RuleVo> rules) {
        this.rules = rules;
    }

    public List<FactVo> getFacts() {
        List<FactVo> factVos = Lists.newArrayList();
        factVos.addAll(getGlobals());
        if(rules != null && !rules.isEmpty()) {
            for(RuleVo ruleVo : rules) {
                factVos.addAll(ruleVo.getFacts());
            }
        }
        return factVos;
    }
}
