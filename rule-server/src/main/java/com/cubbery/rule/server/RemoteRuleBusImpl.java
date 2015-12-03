/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.server;

import com.cubbery.rule.api.RemoteRuleBus;
import com.cubbery.rule.core.RuleEngine;
import com.cubbery.rule.core.RuleEngineSession;
import com.cubbery.rule.dal.SceneService;
import com.cubbery.rule.dal.domin.SceneVo;
import com.cubbery.rule.param.*;
import com.google.common.base.Preconditions;
import org.drools.definition.type.FactType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server <br>
 * <b>类名称</b>： RemoteRuleBusImpl <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RemoteRuleBusImpl implements RemoteRuleBus {
    private static Logger log = LoggerFactory.getLogger(RemoteRuleBusImpl.class);

    @Resource
    SceneService sceneService;

    @Override
    public Response execute(Request request) {
        Preconditions.checkNotNull(Preconditions.checkNotNull(request).getScene());
        RuleEngine ruleEngine = RuleEngineManager.fromCache(request.getScene());
        if(ruleEngine == null) {
            SceneVo scene = sceneService.findById(request.getScene());
            log.info("load scene from db success!",scene.getId());
            ruleEngine = RuleEngineManager.newEngine(scene);
        }
        //create a session
        RuleEngineSession engineSession = ruleEngine.initialiseSession();
        Set<Fact> facts = request.getFactSet();
        if(facts != null && !facts.isEmpty()) {
            insertFacts(ruleEngine,engineSession,facts);
        }
        //result action
        Action resultAction = new Action();
        engineSession.setGlobal("$resultAction",resultAction);
        engineSession.fireAllRules();
        engineSession.dispose();//release resource
        return new Response().setResultAction(resultAction);
    }

    private void insertFacts(RuleEngine ruleEngine, RuleEngineSession engineSession, Set<Fact> facts) {
        for(Fact fact : facts) {
            if(fact instanceof MaskFact) {
                try {
                    MaskFact mf = (MaskFact) fact;
                    FactType aType = ruleEngine.getKbase().getFactType(mf.getPackageName(), mf.getTypeName());
                    Object obj = aType.newInstance();
                    Set<FactField> fs = mf.getFields();
                    for (FactField cell : fs) {
                        aType.set(obj, cell.getName(), cell.getValue());
                    }
                    engineSession.insert(obj);
                } catch (Exception e) {
                    log.error("Error Create Fact From Inn With " + ((MaskFact) fact).getPackageName(), e);
                }
            } else {
                engineSession.insert(fact);
            }
        }
    }
}
