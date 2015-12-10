/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.salary;

import com.cubbery.rule.core.*;
import com.google.common.collect.Lists;
import org.drools.builder.ResourceType;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.salary <br>
 * <b>类名称</b>： SalaryTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/8 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class SalaryTest {
    @Test
    public void testExecute() throws Exception {
        /*
        RuleEngineSession ruleEngine = new RuleEngine(
                new DroolsResource[]{
                        new DroolsResource("salary/salary-pre.drl", PathType.CLASSPATH, ResourceType.DRL),
                        new DroolsResource("salary/salary-incomeTax.drl", PathType.CLASSPATH, ResourceType.DRL),
                        new DroolsResource("salary/salary-after.drl", PathType.CLASSPATH, ResourceType.DRL)}
        ).initialiseSession();
*/
        /**/
      /*  RuleEngineSession ruleEngine = new RuleEngine(
                new DroolsResource[]{
                        new CompositeDroolsResource(PathType.CLASSPATH, ResourceType.DRL, Lists.newArrayList(
                                "salary/salary-pre.drl","salary/salary-incomeTax.drl","salary/salary-after.drl"
                        ))}
        ).initialiseSession();*/

        /**/
        RuleEngineSession ruleEngine = new RuleEngine(
                new DroolsResource[]{new DroolsResource("salary.drl", PathType.CLASSPATH, ResourceType.DRL)}
        ).initialiseSession();

        Salary salary = new Salary();
        salary.setPosition(Position.common);
        salary.setAchievement(50000);

        ruleEngine.insert(salary);
        ruleEngine.fireAllRules();

        assertEquals(1000.0, salary.getBase());
        assertEquals(2000.0,salary.getBonus());
        assertEquals(3000.0,salary.getPreTax());

        assertEquals(155.0,salary.getIncomeTax());//((3000 - 1800) * 0.1 + 35)
        assertEquals((3000.0-155.0),salary.getAfterTax());
    }


}
