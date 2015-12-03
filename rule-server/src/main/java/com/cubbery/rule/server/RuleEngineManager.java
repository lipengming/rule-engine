/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.server;

import com.cubbery.rule.core.CompositeDroolsResource;
import com.cubbery.rule.core.DroolsResource;
import com.cubbery.rule.core.PathType;
import com.cubbery.rule.core.RuleEngine;
import com.cubbery.rule.dal.domin.SceneVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.LRUMap;
import org.drools.builder.ResourceType;

import java.util.List;
import java.util.Map;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server <br>
 * <b>类名称</b>： RuleEngineManager <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RuleEngineManager {
    private final static Map<Long,RuleEngine> CACHE = new LRUMap(1000);

    public static RuleEngine getRuleEngine(SceneVo scene) {
        RuleEngine ruleEngine = fromCache(scene.getId());
        if(ruleEngine != null) {
            return ruleEngine;
        }
        return newEngine(scene);
    }

    public static RuleEngine newEngine(SceneVo scene) {
        List<String> scripts = RuleEngineBuilder.newBuilder().setScene(scene).build();
        if(CollectionUtils.isEmpty(scripts)) {
            return null;
        }
        DroolsResource droolsResource = null;
        if(scripts.size() > 1) {
            droolsResource = new CompositeDroolsResource(PathType.BYTE, ResourceType.DRL, scripts);
        } else {
            droolsResource = new DroolsResource(scripts.get(0), PathType.READER, ResourceType.DRL);
        }
        return CACHE.put(scene.getId(),new RuleEngine(new DroolsResource[]{droolsResource}));
    }

    public static RuleEngine fromCache(Long identify) {
        return CACHE.get(identify);
    }
}
