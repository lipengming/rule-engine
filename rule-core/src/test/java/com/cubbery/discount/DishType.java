/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.discount;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server.discount <br>
 * <b>类名称</b>： DishType <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/4 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public enum DishType {
    COM(0),DIS(0.1);
    private double dis;//折扣

    DishType(double dis) {
        this.dis = dis;
    }

    public double getDis() {
        return dis;
    }
}
