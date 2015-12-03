package com.cubbery;

import com.cubbery.rule.core.DroolsResource;
import com.cubbery.rule.core.PathType;
import com.cubbery.rule.core.RuleEngine;
import com.cubbery.rule.core.RuleEngineSession;
import org.drools.builder.ResourceType;
import org.junit.Assert;
import org.junit.Test;

public class SampleFactTest {
    @Test
    public void test() {
        RuleEngineSession ruleEngine = new RuleEngine(new DroolsResource[]{new DroolsResource("sample.drl", PathType.CLASSPATH, ResourceType.DRL)}).initialiseSession();
        SampleFact sampleFact = new SampleFact(1,"bad man!");
        ruleEngine.insert(sampleFact);
        ruleEngine.fireAllRules();
        Assert.assertEquals("Goodbye world!",sampleFact.getMessage());
        Assert.assertEquals(0,sampleFact.getStatus());
        ruleEngine.retractAll();

        sampleFact = new SampleFact(2,"bad man!");
        ruleEngine.insert(sampleFact);
        ruleEngine.fireAllRules();
        Assert.assertEquals("bad man!",sampleFact.getMessage());
        Assert.assertEquals(2,sampleFact.getStatus());
        ruleEngine.retractAll();

    }
}
