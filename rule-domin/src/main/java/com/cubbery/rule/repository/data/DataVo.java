/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.repository.data;

import com.cubbery.rule.repository.BaseVo;
import com.cubbery.rule.repository.code.DataDire;
import com.cubbery.rule.repository.code.DataType;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.repository.data <br>
 * <b>类名称</b>： DataVo <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/4 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class DataVo extends BaseVo {
    private String name;//英文名
    private String type;//类型：如：int,double
    private String displayName;//显示名称（中文）
    private String group;//分组
    private String nickName;//调用时的昵称

    private DataType dataType;
    private DataDire dire;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public DataDire getDire() {
        return dire;
    }

    public void setDire(DataDire dire) {
        this.dire = dire;
    }
}
