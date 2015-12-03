/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.dal.code;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.dal.code <br>
 * <b>类名称</b>： RelationEnum <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public enum RelationEnum {
    AND("$&&$"," && ","与"),
    OR ("$||$"," || ","或"),
    NOT("$!$", " ! " ,"非"),

            ;

    private String identify;//存储
    private String expression;//drl表达
    private String desc;//文字描述

    RelationEnum(String identify, String expression, String desc) {
        this.identify = identify;
        this.expression = expression;
        this.desc = desc;
    }

    public String getExpression() {
        return expression;
    }

    public String getDesc() {
        return desc;
    }

    public String getIdentify() {
        return identify;
    }
}

