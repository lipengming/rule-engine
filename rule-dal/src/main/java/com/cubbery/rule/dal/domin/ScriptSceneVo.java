/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.dal.domin;

import com.cubbery.rule.dal.code.SceneType;

import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.dal.domin <br>
 * <b>类名称</b>： ScriptSceneVo <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ScriptSceneVo extends SceneVo {
    private String script;
    private List<FactVo> args;

    @Override
    public SceneType getSceneType() {
        return SceneType.SCRIPT;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public List<FactVo> getArgs() {
        return args;
    }

    public void setArgs(List<FactVo> args) {
        this.args = args;
    }
}
