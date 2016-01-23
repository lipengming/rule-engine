/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.limit;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.limit <br>
 * <b>类名称</b>： Limit <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Limit {

    private int limitMoth;//月限额
    private int limitDay;//日限额

    public int getLimitMoth() {
        return limitMoth;
    }

    public void setLimitMoth(int limitMoth) {
        this.limitMoth = limitMoth;
    }

    public int getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(int limitDay) {
        this.limitDay = limitDay;
    }

    @Override
    public String toString() {
        return "Limit{" +
                "limitMoth=" + limitMoth +
                ", limitDay=" + limitDay +
                '}';
    }
}
