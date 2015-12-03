/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.param;

import java.io.Serializable;
import java.util.Set;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.param <br>
 * <b>类名称</b>： Request <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Request implements Serializable {
    private Long   scene;
    private String   systemId;
    private Set<Fact> factSet;
    private RequestType requestType ;

    public Request(Long scene, String systemId) {
        this.scene = scene;
        this.systemId = systemId;
    }

    public Long getScene() {
        return scene;
    }

    public void setScene(Long scene) {
        this.scene = scene;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public Set<Fact> getFactSet() {
        return factSet;
    }

    public void setFactSet(Set<Fact> factSet) {
        this.factSet = factSet;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
