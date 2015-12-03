package com.cubbery;

import com.cubbery.rule.core.*;
import org.drools.builder.ResourceType;
import org.drools.runtime.rule.FactHandle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrackingWorkingMemoryEventListenerTest {
    private RuleEngineSession kenv = new RuleEngine(new DroolsResource[] {
            new DroolsResource("listener.drl",
                    PathType.CLASSPATH,
                    ResourceType.DRL)
    }).initialiseSession();
    @Test
    public void testSample() {

        TrackingAgendaEventListener agendaListener = new TrackingAgendaEventListener();
        TrackingWorkingMemoryEventListener workingMemoryListener = new TrackingWorkingMemoryEventListener();

        kenv.addEventListener(agendaListener);
        kenv.addEventListener(workingMemoryListener);

        FactHandle productHandle = kenv.insert(new Product("Book", 20));
        FactHandle customerHandle = kenv.insert(new Customer("Jimbo"));

        TrackingWorkingMemoryEventListener productListener = new TrackingWorkingMemoryEventListener(productHandle);
        kenv.addEventListener(productListener);
        TrackingWorkingMemoryEventListener customerListener = new TrackingWorkingMemoryEventListener(customerHandle);
        kenv.addEventListener(customerListener);

        kenv.fireAllRules();

        assertEquals( 10, productListener.getUpdates().size());
        assertEquals( 1, customerListener.getUpdates().size());
    }
}