package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import program.AddChildActor;
import program.ChildActor;
import program.Main;
import program.Message;
import program.Node;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ExecutionTest {
	
	@Test
	public void classTest() {
		Props p = Props.create(Node.class, "A");
		assertEquals(p.actorClass(), Node.class);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void sendString() throws InterruptedException {
		ActorSystem system = ActorSystem.create();
		
		ActorRef a, b, c, d, e, f;
		Props pa, pb, pc, pd, pe, pf;
		List<ChildActor> childrenA = new ArrayList<ChildActor>();
		List<ChildActor> childrenB = new ArrayList<ChildActor>();
		List<ChildActor> childrenE = new ArrayList<ChildActor>();
		
		pf = Props.create(Node.class, "F");		
		f = system.actorOf(pf);
		childrenE.add(new ChildActor(f));
		pe = Props.create(Node.class, "E", childrenE);
		e = system.actorOf(pe);
		pd = Props.create(Node.class, "D");
		d = system.actorOf(pd);
		pc = Props.create(Node.class, "C");
		c = system.actorOf(pc);
		childrenB.add(new ChildActor(c));
		childrenB.add(new ChildActor(d));
		pb = Props.create(Node.class, "B", childrenB);
		b = system.actorOf(pb);
		childrenA.add(new ChildActor(b));
		childrenA.add(new ChildActor(e));
		pa = Props.create(Node.class, "A", childrenA);
		a = system.actorOf(pa);
		
		System.out.println("\nTest String - Arbre à partir de \"A\" :");
		a.tell(new String("BrunelRoche"), ActorRef.noSender());

		Thread.sleep(1000);
		
		system.shutdown();
		
		assert(a.isTerminated());
		assert(b.isTerminated());
		assert(c.isTerminated());
		assert(d.isTerminated());
		assert(e.isTerminated());
		assert(f.isTerminated());
		assert(system.isTerminated());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void treeBasicTest() throws InterruptedException {
		ActorSystem system = ActorSystem.create();
		
		ActorRef a, b, c, d, e, f;
		List<ChildActor> childrenA = new ArrayList<ChildActor>();
		List<ChildActor> childrenB = new ArrayList<ChildActor>();
		List<ChildActor> childrenE = new ArrayList<ChildActor>();
		
		f = system.actorOf(Props.create(Node.class, "F"));
		childrenE.add(new ChildActor(f));
		e = system.actorOf(Props.create(Node.class, "E", childrenE));
		d = system.actorOf(Props.create(Node.class, "D"));
		c = system.actorOf(Props.create(Node.class, "C"));
		childrenB.add(new ChildActor(c));
		childrenB.add(new ChildActor(d));
		b = system.actorOf(Props.create(Node.class, "B", childrenB));
		childrenA.add(new ChildActor(b));
		childrenA.add(new ChildActor(e));
		a = system.actorOf(Props.create(Node.class, "A", childrenA));
		
		System.out.println("\nTest question 2 - Arbre à partir de \"A\" :");
		
		a.tell(new Message("BrunelRoche"), ActorRef.noSender());
		
		Thread.sleep(1000);
		
		system.shutdown();
		
		assert(a.isTerminated());
		assert(b.isTerminated());
		assert(c.isTerminated());
		assert(d.isTerminated());
		assert(e.isTerminated());
		assert(f.isTerminated());
		assert(system.isTerminated());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void multipleSystemsTest() throws InterruptedException {
		ActorSystem system1 = ActorSystem.create();
		ActorSystem system2 = ActorSystem.create();
		
		ActorRef a, b, c, d, e, f;
		
		f = system1.actorOf(Props.create(Node.class, "F"));
		e = system1.actorOf(Props.create(Node.class, "E"));
		d = system2.actorOf(Props.create(Node.class, "D"));
		c = system1.actorOf(Props.create(Node.class, "C"));
		b = system2.actorOf(Props.create(Node.class, "B"));
		a = system2.actorOf(Props.create(Node.class, "A"));
		
		a.tell(new AddChildActor(b), ActorRef.noSender());
		a.tell(new AddChildActor(e), ActorRef.noSender());
		b.tell(new AddChildActor(c), ActorRef.noSender());
		b.tell(new AddChildActor(d), ActorRef.noSender());
		e.tell(new AddChildActor(f), ActorRef.noSender());
		
		System.out.println("\nTest question 4 - Arbre à partir de \"A\" - {A, B, D} dans un systeme et {C, E, F} dans un autre :");
		
		a.tell(new Message("BrunelRoche"), ActorRef.noSender());
		
		Thread.sleep(1000);
		
		system1.shutdown();
		system2.shutdown();
		
		assert(a.isTerminated());
		assert(b.isTerminated());
		assert(c.isTerminated());
		assert(d.isTerminated());
		assert(e.isTerminated());
		assert(f.isTerminated());
		assert(system1.isTerminated());
		assert(system2.isTerminated());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void graphTest() throws InterruptedException {
		ActorSystem system = ActorSystem.create();
		
		ActorRef a, b, c, d, e, f;
		
		f = system.actorOf(Props.create(Node.class, "F"));
		e = system.actorOf(Props.create(Node.class, "E"));
		d = system.actorOf(Props.create(Node.class, "D"));
		c = system.actorOf(Props.create(Node.class, "C"));
		b = system.actorOf(Props.create(Node.class, "B"));
		a = system.actorOf(Props.create(Node.class, "A"));
		
		AddChildActor childB = new AddChildActor(b);
		AddChildActor childC = new AddChildActor(c);
		AddChildActor childD = new AddChildActor(d);
		AddChildActor childE = new AddChildActor(e);
		AddChildActor childF = new AddChildActor(f);
		
		a.tell(childB, ActorRef.noSender());
		a.tell(childE, ActorRef.noSender());
		b.tell(childC, ActorRef.noSender());
		b.tell(childD, ActorRef.noSender());
		e.tell(childF, ActorRef.noSender());
		d.tell(childF, ActorRef.noSender());
		f.tell(childD, ActorRef.noSender());
		
		System.out.println("\nTest question 5 - Graphe à partir de \"A\" - {A, B, D} dans un systeme et {C, E, F} dans un autre - Boucle sur F :");
		
		a.tell(new Message("BrunelRoche"), ActorRef.noSender());
		
		Thread.sleep(1000);
		
		system.shutdown();
		
		assert(a.isTerminated());
		assert(b.isTerminated());
		assert(c.isTerminated());
		assert(d.isTerminated());
		assert(e.isTerminated());
		assert(f.isTerminated());
		assert(system.isTerminated());
	}
	
	@Test
	public void mainTest() throws InterruptedException {
		Main.main(null);
		assert(true);
	}

}
