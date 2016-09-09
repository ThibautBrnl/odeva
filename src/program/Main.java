package program;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Executable
 * 
 * @author Thibaut Brunel and Julien Roche - 2016 Master 1 Informatique Lille 1 CAR
 */
public class Main {
	
	/**
	 * Main function - Call multiples functions wich corresponds to questions
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("--- TP03 Akka - Thibaut Brunel et Julien Roche ---\n\n"+getDiagram1());
		System.out.println("Question 1 - Tests : Couverture de 87,9%");
		question2();
		question3();
		question4();
		System.out.println("\n"+getDiagram2());
		question5();
	}
	
	/**
	 * Print diagram for questions 2 to 4
	 * @return String : visual for tree diagram
	 */
	public static String getDiagram1() {
		String diagram = "";
		
		diagram += "    A\n";
		diagram += "   / \\\n";
		diagram += "  B   E\n";
		diagram += " / \\   \\\n";
		diagram += "C   D   F\n";
		
		return diagram;
	}
	
	/**
	 * Print diagram for question 5
	 * @return String : visual for tree diagram
	 */
	public static String getDiagram2() {
		String diagram = "";
		
		diagram += "    A\n";
		diagram += "   / \\\n";
		diagram += "  B   E\n";
		diagram += " / \\   \\\n";
		diagram += "C   D - F\n";
		
		return diagram;
	}
	
	/**
	 * Question 2 - Trace program execution of tree from the first node "A"
	 * @throws InterruptedException 
	 */
	public static void question2() throws InterruptedException {
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
		
		System.out.println("\nQuestion 2 - Arbre à partir de \"A\" :");
		
		a.tell(new Message("BrunelRoche"), ActorRef.noSender());
		
		Thread.sleep(1000);
		
		system.shutdown();
	}
	
	/**
	 * Question 3 - Trace program execution of tree from the node "B"
	 * @throws InterruptedException 
	 */
	public static void question3() throws InterruptedException {
		ActorSystem system = ActorSystem.create();
		
		@SuppressWarnings("unused")
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
		
		System.out.println("\nQuestion 3 - Arbre à partir de \"B\" :");
		
		b.tell(new Message("BrunelRoche"), ActorRef.noSender());
		
		Thread.sleep(1000);
		
		system.shutdown();
	}
	
	/**
	 * Question 4 - Trace program execution of tree with nodes in two differents systems
	 * @throws InterruptedException 
	 */
	public static void question4() throws InterruptedException {
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
		
		System.out.println("\nQuestion 4 - Arbre à partir de \"A\" - {A, B, D} dans un systeme et {C, E, F} dans un autre :");
		
		a.tell(new Message("BrunelRoche"), ActorRef.noSender());
		
		Thread.sleep(1000);
		
		system1.shutdown();
		system2.shutdown();
	}
	
	/**
	 * Question 5 - Trace program execution of graph (loop on "F", look diagram2())
	 * @throws InterruptedException 
	 */
	public static void question5() throws InterruptedException {
		ActorSystem system1 = ActorSystem.create();
		ActorSystem system2 = ActorSystem.create();
		
		ActorRef a, b, c, d, e, f;
		
		f = system1.actorOf(Props.create(Node.class, "F"));
		e = system1.actorOf(Props.create(Node.class, "E"));
		d = system2.actorOf(Props.create(Node.class, "D"));
		c = system1.actorOf(Props.create(Node.class, "C"));
		b = system2.actorOf(Props.create(Node.class, "B"));
		a = system2.actorOf(Props.create(Node.class, "A"));
		
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
		
		System.out.println("\nQuestion 5 - Graphe à partir de \"A\" - {A, B, D} dans un systeme et {C, E, F} dans un autre - Boucle sur F :");
		
		a.tell(new Message("BrunelRoche"), ActorRef.noSender());
		
		Thread.sleep(1000);
		
		system1.shutdown();
		system2.shutdown();
	}
}
