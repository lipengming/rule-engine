package com.cubbery.rule.server;

import com.cubbery.rule.api.RemoteRuleBus;
import com.cubbery.rule.dal.domin.AdvanceSceneVo;
import com.cubbery.rule.dal.domin.FactVo;
import com.cubbery.rule.dal.domin.FieldVo;
import com.cubbery.rule.dal.domin.ScriptSceneVo;
import com.cubbery.rule.param.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RemoteRuleBusImplTest {

    static RemoteRuleBus remoteRuleBus = new RemoteRuleBusImpl();
    static ScriptSceneVo scene = new ScriptSceneVo();
    static AdvanceSceneVo advanceSceneVo = new AdvanceSceneVo();

    @Before
    public void bf() {
        scene.setId(1L);
        FactVo f1 = new FactVo();
        f1.setPackageName("com.cubbery.rule");
        f1.setName("SampleFact");
        FieldVo f11 = new FieldVo("status","java.lang.Integer");
        FieldVo f12 = new FieldVo("message","java.lang.String");
        f1.setFields(Lists.newArrayList(f11, f12));

        scene.setArgs(Lists.newArrayList(f1));
        scene.setScript(ruleScript);

        RuleEngineManager.getRuleEngine(scene);//先塞进去，mock调dal层




    }

    @Test
    public void testExecuteWithScript() throws Exception {
        Request request = new Request(scene.getId(),"cuc");
        MaskFact fact = new MaskFact("com.cubbery.rule","SampleFact");
        fact.setFields(Sets.newHashSet(new FactField("status", 1), new FactField("message", "hello")));

        Set<Fact> facts = new HashSet<Fact>();
        facts.add(fact);
        request.setFactSet(facts);

        Response response = remoteRuleBus.execute(request);
        assertEquals(true,response.getSuccess());
    }

    @Test
    public void testExecuteWithAdvance() throws Exception {

    }

    static String ruleScript = "import com.cubbery.rule.SampleFact;\n" +
            "\n" +
            "rule \"Hello World\"\n" +
            "    when\n" +
            "        m : SampleFact( status == 1, myMessage : message )\n" +
            "    then\n" +
            "        System.out.println( \"before \" + myMessage );\n" +
            "        m.setMessage( \"Goodbye world!\" );\n" +
            "        m.setStatus( 0 );\n" +
            "        update( m );\n" +
            "        System.out.println( \"after \" + m.getMessage() );\n" +
            "end\n" +
            "\n" +
            "rule \"GoodBye\"\n" +
            "    when\n" +
            "        SampleFact( status == 0, myMessage : message )\n" +
            "    then\n" +
            "        System.out.println( myMessage );\n" +
            "end\n" +
            "\n" +
            "rule \"Nomal\"\n" +
            "    when\n" +
            "        SampleFact( status != 0, myMessage : message )\n" +
            "    then\n" +
            "        System.out.println( myMessage );\n" +
            "end";
}