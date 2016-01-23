/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.limit;

import com.cubbery.rule.core.DroolsResource;
import com.cubbery.rule.core.PathType;
import com.cubbery.rule.core.RuleEngine;
import com.cubbery.rule.core.RuleEngineSession;
import junit.framework.Assert;
import org.drools.builder.ResourceType;
import org.junit.Test;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.limit <br>
 * <b>类名称</b>： LimitTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class LimitTest {
    @Test
    public void testExecute() throws Exception {
        RuleEngineSession ruleEngine = new RuleEngine(
                new DroolsResource[]{
                        new DroolsResource("limit/limit-cus.drl", PathType.CLASSPATH, ResourceType.DRL),
                        new DroolsResource("limit/limit-card.drl", PathType.CLASSPATH, ResourceType.DRL),
                        new DroolsResource("limit/limit-channel.drl", PathType.CLASSPATH, ResourceType.DRL),
                        new DroolsResource("limit/avalible.drl", PathType.CLASSPATH, ResourceType.DRL),
                }
        ).initialiseSession();

        WithDrawLimit limit = new WithDrawLimit();
        limit.setBankCardNumber("num");
        limit.setBankCode(Channel.ABC);
        limit.setCustomerId("cu1");
        limit.setCustomerRecords(new CustomerRecordsImpl(100,4300));//今天取了100，这个月取了3000【数据库记录】

        ruleEngine.insert(limit);
        ruleEngine.setGlobal("$limit",new Limit());//用于计算的中间变量
        ruleEngine.fireAllRules();
        Assert.assertEquals(200,limit.getAvailable());
    }
}
