/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.salary;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.salary <br>
 * <b>类名称</b>： Fact <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/8 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Salary {
    private Position position;//职位
    private double achievement ;//业绩


    private double base = 0.0;//基本工资
    private double bonus  = 0.0;//奖金
    private double preTax  = 0.0;
    private double incomeTax  = 0.0;//所得税
    private double afterTax  = 0.0;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getPreTax() {
        return preTax;
    }

    public void setPreTax(double preTax) {
        this.preTax = preTax;
    }

    public double getAchievement() {
        return achievement;
    }

    public void setAchievement(double achievement) {
        this.achievement = achievement;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(double afterTax) {
        this.afterTax = afterTax;
    }
}
