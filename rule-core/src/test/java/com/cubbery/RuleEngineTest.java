package com.cubbery;

import com.cubbery.rule.core.*;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.runtime.ObjectFilter;
import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;
import org.drools.runtime.rule.FactHandle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RuleEngineTest {

    public static final ObjectFilter MESSAGE_FILTER = new ObjectFilter() {
        public boolean accept(Object object) {
            return object.getClass().getSimpleName().equals("Message");
        }
    };

    public static final ObjectFilter SAMPLE_FILTER = new ObjectFilter() {
        public boolean accept(Object object) {
            return object.getClass().getSimpleName().equals("SampleFact");
        }
    };

    public static AgendaFilter getFilter(final Object obj) {
        return new AgendaFilter() {
            @Override
            public boolean accept(Activation activation) {
                return activation.getFactHandles().contains(obj);
            }
        };
    }

    @Test
    public void shouldBe() {
        RuleEngineSession ruleEngine = new RuleEngine(new DroolsResource[]{new DroolsResource("msg.drl", PathType.CLASSPATH, ResourceType.DRL)}).initialiseSession();
        assertEquals(0, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
        ruleEngine.insert(new Message("Hello Droolers!"));
        assertEquals(1, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
        ruleEngine.clearSession();//ksession 存在，这次会清除掉
        assertEquals(0, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
        ruleEngine.insert(new Message("Test Droolers!"));
        Collection<FactHandle> factHandles = ruleEngine.getFactHandles(MESSAGE_FILTER);
        assertEquals(1, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
    }

    @Test
    public void returnAction() {
        RuleEngineSession ruleEngine = new RuleEngine(new DroolsResource[]{new DroolsResource("msg.drl", PathType.CLASSPATH, ResourceType.DRL)}).initialiseSession();
        assertEquals(0, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
        FactHandle factHandle = ruleEngine.insert(new Message("Hello Droolers!"));
        assertEquals(1, ruleEngine.getFactHandles(MESSAGE_FILTER).size());

        ruleEngine.fireAllRules();

        Object message = ruleEngine.getKsession().getObject(factHandle);
        assertEquals(Message.class, message.getClass());
        assertEquals("Hello, World!", ((Message)message).getText());
    }

    @Test
    public void testStr() {
        String str = "import com.cubbery.Message\n" +
                "\n" +
                "dialect \"java\"\n" +
                "lock-on-active true\n" +
                "no-loop\n" +
                "rule \"msg\"\n" +
                "\n" +
                "when\n" +
                "\t$msg : Message()\n" +
                "then\n" +
                "\tmodify( $msg ) {\n" +
                "\t\tsetText(\"Hello, World!\")\n" +
                "\t}\n" +
                "\n" +
                "end";

        RuleEngineSession ruleEngine = new RuleEngine(new DroolsResource[]{new DroolsResource(str, PathType.READER, ResourceType.DRL)}).initialiseSession();
        assertEquals(0, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
        ruleEngine.insert(new Message("Hello Droolers!"));
        assertEquals(1, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
        ruleEngine.clearSession();//ksession 存在，这次会清除掉
        assertEquals(0, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
        ruleEngine.insert(new Message("Test Droolers!"));
        Collection<FactHandle> factHandles = ruleEngine.getFactHandles(MESSAGE_FILTER);
        assertEquals(1, ruleEngine.getFactHandles(MESSAGE_FILTER).size());
    }

    @Test
    public void testCompositeKnowledgeBuilder() throws Exception {
        String rule = "package org.drools.compiler.test\n" +
                "rule R1 when\n" +
                "   $fieldA : FactA( $fieldB : fieldB )\n" +
                "   FactB( this == $fieldB, fieldA == $fieldA )\n" +
                "then\n" +
                "end";

        String declarationA = "package org.drools.compiler.test\n" +
                "declare FactA\n" +
                "    fieldB: FactB\n" +
                "end\n";

        String declarationB = "package org.drools.compiler.test\n" +
                "declare FactB\n" +
                "    fieldA: FactA\n" +
                "end\n";
        RuleEngine ruleEngine = new RuleEngine(new DroolsResource[]{
                new CompositeDroolsResource(PathType.BYTE,ResourceType.DRL, Arrays.asList(declarationA,declarationB,rule))
        });

        FactType aType = ruleEngine.getKbase().getFactType("org.drools.compiler.test", "FactA");
        Object a = aType.newInstance();
        FactType bType = ruleEngine.getKbase().getFactType("org.drools.compiler.test", "FactB");
        Object b = bType.newInstance();
        aType.set( a, "fieldB", b );
        bType.set( b, "fieldA", a );

        RuleEngineSession session = ruleEngine.initialiseSession();
        session.insert(a,b);

        int rules = session.fireAllRules();
        assertEquals(rules,rules);
    }

    @Test
    public void testUndoRule() throws Exception {
        String rule = "package org.drools.compiler.test\n" +
                "global java.util.List list\n" +
                "import org.drools.compiler.test.FactA\n" +
                "import org.drools.compiler.test.FactB\n" +
                "rule R1 when\n" +
                "   FactA( j == 1 )\n" +
                "   FactB( i == 1 )\n" +
                "then\n" +
                "   list.add(\"OK\");" +
                "end\n";

        String declarationA = "package org.drools.compiler.test\n" +
                "declare FactA\n" +
                "    i : int\n" +
                "end\n";

        String declarationB = "package org.drools.compiler.test\n" +
                "declare FactB\n" +
                "    i : int\n" +
                "end\n";

        try {
            new RuleEngine(new DroolsResource[]{
                    new CompositeDroolsResource(PathType.BYTE,ResourceType.DRL, Arrays.asList(declarationA,declarationB,rule))
            });
            fail();
        } catch (Exception e) {
            //rule中存在无效的字段
        }

        rule = "package org.drools.compiler.test\n" +
                "global java.util.List list\n" +
                "import org.drools.compiler.test.FactA\n" +
                "import org.drools.compiler.test.FactB\n" +
                "rule R1 \n" +
                "when\n" +
                "   FactA( i == 1 )\n" +
                "   FactB( i == 1 )\n" +
                "then\n" +
                "   list.add(\"OK\");" +
                "end\n";
        RuleEngine ruleEngine = null;
        try {
            ruleEngine = new RuleEngine(new DroolsResource[]{
                    new CompositeDroolsResource(PathType.BYTE,ResourceType.DRL, Arrays.asList(declarationA,declarationB,rule))
            });

        } catch (Exception e) {
            fail();
        }

        RuleEngineSession session = ruleEngine.initialiseSession();
        List list = new ArrayList();
        session.setGlobal( "list", list );

        FactType aType = ruleEngine.getKbase().getFactType( "org.drools.compiler.test", "FactA" );
        Object a = aType.newInstance();
        aType.set( a, "i", 1 );

        FactType bType = ruleEngine.getKbase().getFactType( "org.drools.compiler.test", "FactB" );
        Object b = bType.newInstance();
        bType.set( b, "i", 1 );

        session.insert( a );
        session.insert( b );

        int rules = session.fireAllRules();
        assertEquals( 1, rules );
        assertEquals( "OK", list.get( 0 ) );
    }

}