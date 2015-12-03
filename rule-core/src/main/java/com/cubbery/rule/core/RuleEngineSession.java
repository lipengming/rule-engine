/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.core;

import org.drools.event.rule.AgendaEventListener;
import org.drools.event.rule.WorkingMemoryEventListener;
import org.drools.runtime.ObjectFilter;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.AgendaFilter;
import org.drools.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.core <br>
 * <b>类名称</b>： RuleEngineSession <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RuleEngineSession {
    private final static Logger log = LoggerFactory.getLogger(RuleEngineSession.class);
    private final StatefulKnowledgeSession ksession;
    private TrackingAgendaEventListener agendaEventListener;
    private TrackingWorkingMemoryEventListener workingMemoryEventListener;

    public RuleEngineSession(StatefulKnowledgeSession ksession) {
        this.ksession = ksession;
    }

    /**
     * fire all rules in the session
     */
    public int fireAllRules() {
        return ksession.fireAllRules();
    }

    /**
     * fire with filter
     * @param filter
     */
    public int fireAllRules(AgendaFilter filter) {
        return ksession.fireAllRules(filter);
    }

    /**
     * Releases all the current session resources, setting up the session for garbage collection.
     * This method <b>must</b> always be called after finishing using the session, or the engine
     * will not free the memory used by the session.
     */
    public void dispose() {
        ksession.dispose();
    }

    /**
     * re init , clear the session
     */
    public void clearSession() {
        retractAll();
        clearListeners();
    }

    /**
     * clear listeners and then init the listener
     */
    public void clearListeners() {
        this.ksession.removeEventListener(this.agendaEventListener);
        this.ksession.removeEventListener(this.workingMemoryEventListener);
        this.agendaEventListener = new TrackingAgendaEventListener();
        this.workingMemoryEventListener = new TrackingWorkingMemoryEventListener();
        this.ksession.addEventListener(this.agendaEventListener);
        this.ksession.addEventListener(this.workingMemoryEventListener);
    }

    /**
     * insert facts
     *
     * @param objects
     * @return
     */
    public List<FactHandle> insert(Object... objects) {
        List<FactHandle> handles = new ArrayList<FactHandle>();
        for (Object o : objects) {
            handles.add(ksession.insert(o));
        }
        return handles;
    }

    public void setGlobal(String identifer,Object value) {
        this.ksession.setGlobal(identifer, value);
    }

    /**
     * insert one fact
     *
     * @param o
     * @return
     */
    public FactHandle insert(Object o) {
        return this.ksession.insert(o);
    }

    /**
     * update a fact by factHandler
     *
     * @param handle
     * @param obj
     */
    public void update(FactHandle handle, Object obj) {
        this.ksession.update(handle, obj);
    }

    /**
     * add a new listener
     *
     * @param listener
     */
    public void addEventListener(AgendaEventListener listener) {
        ksession.addEventListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void addEventListener(WorkingMemoryEventListener listener) {
        ksession.addEventListener(listener);
    }

    /**
     * remove event listener
     *
     * @param listener
     */
    public void removeEventListener(AgendaEventListener listener) {
        ksession.removeEventListener(listener);
    }

    /**
     * 移除监听
     * @param listener
     */
    public void removeEventListener(WorkingMemoryEventListener listener) {
        ksession.removeEventListener(listener);
    }

    /**
     * 回收内存中的事实（fact）
     */
    public void retractAll() {
        log.info("Retracting all facts matching filter...");
        for (FactHandle handle : getFactHandles()) {
            retract(handle);
        }
    }

    /**
     * 根据fact句柄回收一个fact
     * @param handle
     */
    public void retract(FactHandle handle) {
        ksession.retract(handle);
    }

    /**
     * 获取所有的fact句柄
     * @return
     */
    public Collection<FactHandle> getFactHandles() {
        return ksession.getFactHandles();
    }

    /**
     * 获取句柄
     * @param filter
     * @return
     */
    public Collection<FactHandle> getFactHandles(ObjectFilter filter) {
        return ksession.getFactHandles(filter);
    }

    /**
     * 获取会话
     * @return
     */
    public StatefulKnowledgeSession getKsession() {
        return this.ksession;
    }

}

