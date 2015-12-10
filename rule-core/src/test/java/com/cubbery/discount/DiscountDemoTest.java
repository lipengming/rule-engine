/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.discount;

import com.cubbery.rule.core.DroolsResource;
import com.cubbery.rule.core.PathType;
import com.cubbery.rule.core.RuleEngine;
import com.cubbery.rule.core.RuleEngineSession;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.drools.builder.ResourceType;
import org.junit.Test;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server <br>
 * <b>类名称</b>： RemoteRuleBusAdvanceTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/4 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * http://www.360doc.com/content/07/0705/18/7147_596025.shtml
 *
 * @version 1.0.0 <br>
 */
public class DiscountDemoTest {

    @Test
    public void testExecute() throws Exception {
        RuleEngineSession ruleEngine = new RuleEngine(
                new DroolsResource[]{new DroolsResource("loop.drl", PathType.CLASSPATH, ResourceType.DRL)}
        ).initialiseSession();

        Order order = new Order();
        Dish dish = new Dish();
        dish.setPrice(10.0);
        dish.setType(DishType.DIS);
        dish.setCount(1);
        order.setDishes(Lists.newArrayList(dish));


        ruleEngine.insert(order);
        ruleEngine.fireAllRules();
        Assert.assertEquals(9.0,order.getTotal());
    }


}
