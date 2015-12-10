/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.repository;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.repository <br>
 * <b>类名称</b>： BaseVo <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/4 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public abstract class BaseVo implements Serializable {
    private Long id;
    private Date createdDate;
    private Date modifiedDate;
    private Date created;
    private Date modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
