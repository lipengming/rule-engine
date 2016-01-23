/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.limit;

import java.math.BigDecimal;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.limit <br>
 * <b>类名称</b>： Customer <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public enum Channel {
    ICBC(500,5000),
    ABC(400,4500);

    private int maxDay;
    private int maxMoth;

    Channel(int maxDay, int maxMoth) {
        this.maxDay = maxDay;
        this.maxMoth = maxMoth;
    }

    public int getMaxDay() {
        return maxDay;
    }

    public int getMaxMoth() {
        return maxMoth;
    }
}
