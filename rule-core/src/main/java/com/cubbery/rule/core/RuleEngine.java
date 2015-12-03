/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.core;

import org.drools.KnowledgeBase;
import org.drools.conf.EventProcessingOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.core <br>
 * <b>类名称</b>： RuleEngine <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RuleEngine {
    private final static Logger log = LoggerFactory.getLogger(RuleEngine.class);
    private DroolsResource[] resources;
    private KnowledgeBase kbase;

    /**
     * 构造函数
     * @param resources      资源
     * @exception Exception  构建异常（rule文件解析异常等）
     */
    public RuleEngine(DroolsResource[] resources) {
        initialise(resources);
    }


    private void initialise(DroolsResource[] resources) {
        this.resources = resources;
        initialise();
    }

    public void initialise() {
        log.info("Initialising KnowledgeEnvironment with resources: " + this.resources);
        this.kbase = DroolsUtil.createKnowledgeBase( this.resources, EventProcessingOption.STREAM);
        //Log a description of the new knowledge base.
        log.info(toString());
    }

    public RuleEngineSession initialiseSession() {
        log.info("Initialising session...");
        RuleEngineSession session = new RuleEngineSession(kbase.newStatefulKnowledgeSession());
        return session;
    }

    public RuleEngineSession initialiseSession(TrackingAgendaEventListener agendaEventListener) {
        log.info("Initialising session...");
        RuleEngineSession session = new RuleEngineSession(kbase.newStatefulKnowledgeSession());
        session.addEventListener(agendaEventListener);
        return session;
    }

    public RuleEngineSession initialiseSession(TrackingWorkingMemoryEventListener agendaEventListener) {
        log.info("Initialising session...");
        RuleEngineSession session = new RuleEngineSession(kbase.newStatefulKnowledgeSession());
        session.addEventListener(agendaEventListener);
        return session;
    }

    /**
     * re init , clear the session
     *
     * @param session
     */
    public void clearSession(RuleEngineSession session) {
        session.retractAll();
        session.clearListeners();
    }

    /**
     * 获取当前的Knowledge【注：Knowledge持有了当前的所有规则，函数，处理，modal等信息】
     * @return
     */
    public KnowledgeBase getKbase() {
        return this.kbase;
    }
}
