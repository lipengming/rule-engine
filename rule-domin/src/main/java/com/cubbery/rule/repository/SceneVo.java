/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.repository;

import com.cubbery.rule.repository.BaseVo;
import com.cubbery.rule.repository.data.DataVo;
import com.cubbery.rule.repository.rule.RuleVo;

import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.repository.rule <br>
 * <b>类名称</b>： SceneVo <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/9 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class SceneVo extends BaseVo {
    private String sceneName;//外部调用的名称
    private String displayName;//显示的名称
    private List<DataVo> dataRepositories;//对象库
    private List<RuleVo> ruleRepositories;//规则库

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<DataVo> getDataRepositories() {
        return dataRepositories;
    }

    public void setDataRepositories(List<DataVo> dataRepositories) {
        this.dataRepositories = dataRepositories;
    }

    public List<RuleVo> getRuleRepositories() {
        return ruleRepositories;
    }

    public void setRuleRepositories(List<RuleVo> ruleRepositories) {
        this.ruleRepositories = ruleRepositories;
    }
}
