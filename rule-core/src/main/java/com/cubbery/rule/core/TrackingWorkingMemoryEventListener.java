/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.core;

import org.apache.commons.beanutils.BeanUtils;
import org.drools.event.rule.*;
import org.drools.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.core <br>
 * <b>类名称</b>： TrackingWorkingMemoryEventListener <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class TrackingWorkingMemoryEventListener extends DefaultWorkingMemoryEventListener {
    private static Logger log = LoggerFactory.getLogger(TrackingWorkingMemoryEventListener.class);

    private List<WorkingMemoryEvent> allEvents = new ArrayList<WorkingMemoryEvent>();
    private List<ObjectInsertedEvent> insertions = new ArrayList<ObjectInsertedEvent>();
    private List<ObjectRetractedEvent> retractions = new ArrayList<ObjectRetractedEvent>();
    private List<ObjectUpdatedEvent> updates = new ArrayList<ObjectUpdatedEvent>();

    private List<Map<String, String>> factChanges = new ArrayList<Map<String, String>>();
    private FactHandle handleFilter;
    private Class<?> classFilter;

    public TrackingWorkingMemoryEventListener() {
        this.handleFilter = null;
    }

    public TrackingWorkingMemoryEventListener(FactHandle handle) {
        this.handleFilter = handle;
    }

    public TrackingWorkingMemoryEventListener(Class<?> classFilter) {
        this.handleFilter = null;
        this.classFilter = classFilter;
    }

    /**
     * 事实插入
     *
     * @param event
     */
    @Override
    public void objectInserted(final ObjectInsertedEvent event) {
        if ((handleFilter == null && classFilter == null)
                || event.getFactHandle() == handleFilter
                || event.getObject().getClass().equals(classFilter)) {
            insertions.add(event);
            allEvents.add(event);
            log.trace("Insertion: " + DroolsUtil.objectDetails(event.getObject()));
        }
    }

    /**
     * 事实回收
     *
     * @param event
     */
    @Override
    public void objectRetracted(final ObjectRetractedEvent event) {
        if ((handleFilter == null && classFilter == null)
                || event.getFactHandle() == handleFilter
                || event.getOldObject().getClass().equals(classFilter)) {
            retractions.add(event);
            allEvents.add(event);
            log.trace("Retraction: " + DroolsUtil.objectDetails(event.getOldObject()));
        }
    }

    /**
     * 事实更新
     *
     * @param event
     */
    @SuppressWarnings("unchecked")
    @Override
    public void objectUpdated(final ObjectUpdatedEvent event) {
        if ((handleFilter == null && classFilter == null)
                || event.getFactHandle() == handleFilter
                || event.getObject().getClass().equals(classFilter)) {
            updates.add(event);
            allEvents.add(event);

            Object fact = event.getObject();
            try {
                factChanges.add(BeanUtils.describe(fact));
            } catch (Exception e) {
                log.error("Unable to get object details for tracking: " + DroolsUtil.objectDetails(fact), e);
            }
            log.trace("Update: " + DroolsUtil.objectDetails(event.getObject()));
        }
    }

    public List<WorkingMemoryEvent> getAllEvents() {
        return allEvents;
    }

    public List<ObjectInsertedEvent> getInsertions() {
        return insertions;
    }

    public List<ObjectRetractedEvent> getRetractions() {
        return retractions;
    }

    public List<ObjectUpdatedEvent> getUpdates() {
        return updates;
    }

    public List<Map<String, String>> getFactChanges() {
        return factChanges;
    }

}
