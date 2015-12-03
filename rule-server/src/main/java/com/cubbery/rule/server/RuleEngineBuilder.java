/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.server;

import com.cubbery.rule.dal.domin.AdvanceSceneVo;
import com.cubbery.rule.dal.domin.SceneVo;
import com.cubbery.rule.dal.domin.ScriptSceneVo;
import com.cubbery.rule.server.builder.AdvanceSceneBuilder;
import com.cubbery.rule.server.builder.SceneBuilder;
import com.cubbery.rule.server.builder.ScriptRuleBuilder;
import com.google.common.base.Preconditions;

import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server <br>
 * <b>类名称</b>： RuleEngineBuilder <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RuleEngineBuilder<T extends SceneVo> {
    private T scene;
    private SceneBuilder<T> sceneBuilder;

    private RuleEngineBuilder(){}

    public static RuleEngineBuilder newBuilder() {
        return new RuleEngineBuilder();
    }

    public RuleEngineBuilder setScene(T scene) {
        this.scene = scene;
        return this;
    }

    public RuleEngineBuilder setSceneBuilder(SceneBuilder<T> sceneBuilder) {
        this.sceneBuilder = sceneBuilder;
        return this;
    }

    public List<String> build() {
        Preconditions.checkArgument(scene != null, "场景参数必须指定！");
        if(this.sceneBuilder == null) {
            this.sceneBuilder = getSceneBuilder(this.scene);
        }
        return this.sceneBuilder.buildByScene(this.scene);
    }

    private SceneBuilder getSceneBuilder(SceneVo scene) {
        if(scene instanceof AdvanceSceneVo) {
            return new AdvanceSceneBuilder();
        }
        if(scene instanceof ScriptSceneVo) {
            return new ScriptRuleBuilder();
        }
        throw new IllegalArgumentException("场景参数必须指定！");
    }
}
