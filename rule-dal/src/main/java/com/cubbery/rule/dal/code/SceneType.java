/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.dal.code;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.dal.domin <br>
 * <b>类名称</b>： SceneType <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public enum SceneType {
    SCRIPT("SCRIPT","DRL脚本文件"),
    ADVANCE("ADVANCE","配置");

    private String code;
    private String desc;

    SceneType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return null;
    }

    public String getDesc() {
        return null;
    }
}
