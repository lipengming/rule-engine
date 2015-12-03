/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.server.builder;

import com.cubbery.rule.dal.domin.FactVo;
import com.cubbery.rule.dal.domin.FieldVo;
import com.cubbery.rule.dal.domin.ScriptSceneVo;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server.builder <br>
 * <b>类名称</b>： ScriptRuleBuilder <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 "  package org.drools.compiler.test
 "  declare FactA
 "    fieldB: FactB
 "  end

 * @version 1.0.0 <br>
 */
public final class ScriptRuleBuilder implements SceneBuilder<ScriptSceneVo> {
    @Override
    public List<String> buildByScene(ScriptSceneVo scene) {
        List<String> scripts = Lists.newArrayList();
        List<FactVo> fields = scene.getArgs();
        for(FactVo cell : fields) {
            StringBuilder sb = new StringBuilder("package ").append(cell.getPackageName()).append("\n");
            sb.append("     declare ").append(cell.getName()).append("\n");
            for(FieldVo field : cell.getFields()) {
                sb.append("                 ").append(field.getName()).append(" : ").append(field.getType()).append("\n");
            }
            sb.append("end \n");
            scripts.add(sb.toString());
        }
        scripts.add(new StringBuilder("global com.cubbery.rule.param.Action $resultAction;\n").append(scene.getScript()).toString());
        return scripts;
    }
}
