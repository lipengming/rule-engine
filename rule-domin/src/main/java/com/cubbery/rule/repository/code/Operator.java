/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.repository.code;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.repository.op <br>
 * <b>类名称</b>： Numeric <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/8 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public enum Operator {
    //Numeric域
    EQ("==","等于"),
    NOT_EQ("!=","不等于"),
    LGT(">","大于"),
    SMT("<","小于"),
    LGET(">=","大于等于"),
    SMET("<=","小于等于"),

    //Collection域
    CONTAINS("contains","包含"),
    EXCLUDES("excludes","不包含"),

    //String 域
    MATCHES("matches","正则匹配")
    ;
    private String code;
    private String desc;

    Operator(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
