/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.server.builder;

import com.cubbery.rule.dal.domin.AdvanceSceneVo;
import com.cubbery.rule.dal.domin.FactVo;
import com.cubbery.rule.dal.domin.RuleVo;
import com.google.common.collect.Lists;

import java.util.List;

import static com.cubbery.rule.server.builder.RuleBuilder.buildRule;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server.builder <br>
 * <b>类名称</b>： AdvanceSceneBuilder <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public final class AdvanceSceneBuilder implements SceneBuilder<AdvanceSceneVo> {

    @Override
    public List<String> buildByScene(AdvanceSceneVo scene) {
        //设置包路径
        StringBuffer sb = new StringBuffer("package com.cubbery.rule.scene").append(scene.getId()).append(";").append("\n");
        //统一的包
        sb.append(buildImport(scene)).append("\n");
        //全局变量
        sb.append(buildGlobals(scene)).append("\n");
        //函数
        sb.append(buildFunctions(scene)).append("\n");
        //设置规则
        for(RuleVo rule : scene.getRules()) {
            sb.append(buildRule(rule));
        }
        return Lists.newArrayList(sb.toString());
    }

    private String buildFunctions(AdvanceSceneVo scene) {
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }

    private String buildGlobals(AdvanceSceneVo scene) {
        StringBuffer sb = new StringBuffer();
        //全局 变量申明
        if(scene.getGlobals() != null) {
            for(FactVo fact : scene.getGlobals()) {
                sb.append("global ").append(fact.getId()).append("$").append(fact.getName().toLowerCase()).append("\n");//!!!这里需要注意
            }
        }
        //结果放置到这里
        sb.append("global com.cubbery.rule.param.Action $resultAction;").append("\n");
        return sb.toString();
    }

    private StringBuffer buildImport(AdvanceSceneVo scene) {
        StringBuffer sb = new StringBuffer().append("\n");
        //导包
        if(scene.getGlobals() == null) return sb;
        //导入entity需要使用的包
        for(FactVo fact : scene.getGlobals()) {
        sb.append("import").append("  ").append(fact.getId()).append(";").append("\n");
        }
        //导入基本类
        sb.append("import").append("  ").append("java.lang.String").append(";").append("\n");
        sb.append("import").append("  ").append("java.util.Map").append(";").append("\n");
        sb.append("import").append("  ").append("java.util.List").append(";").append("\n");
        //导入动作类
        if(scene.getFacts() == null) return sb;
        for(FactVo fact : scene.getFacts()) {
            sb.append("import").append("  ").append(fact.getId()).append(";").append("\n");
        }
        return sb;
    }
}
