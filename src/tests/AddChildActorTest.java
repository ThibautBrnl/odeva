package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import program.AddChildActor;
import program.ChildActor;
import program.Node;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AddChildActorTest {
	@Test
	public void addChildActorTest() {
		ActorSystem system;
		AddChildActor child;
		ActorRef actor;
		ChildActor childActor;
		
		system = ActorSystem.create();
		actor = system.actorOf(Props.create(Node.class, "A"));
		childActor = new ChildActor(actor);
		child = new AddChildActor(childActor);
		
		assertEquals(childActor, child.getChildActor());
		assertEquals(actor, child.getActor());
		
		system.shutdown();
	}
	
	@Test
	public void addActorTest() {
		ActorSystem system;
		AddChildActor child;
		ActorRef actor;
		
		system = ActorSystem.create();
		actor = system.actorOf(Props.create(Node.class, "A"));
		child = new AddChildActor(actor);
		
		assertEquals(actor, child.getActor());
		
		system.shutdown();
	}

}
