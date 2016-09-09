package program;
import java.util.ArrayList;
import java.util.List;

import akka.actor.UntypedActor;

/**
 * Node
 * 
 * @author Thibaut Brunel and Julien Roche - 2016 Master 1 Informatique Lille 1 CAR
 */
public class Node extends UntypedActor {
	/**
	 * Name of node
	 */
	private String name;
	
	/**
	 * Possibly, children ActorRef
	 */
	private List<ChildActor> children;
	
	/**
	 * Basic constructor with String name
	 * @param name
	 */
	public Node(String name) {
		this.name = name;
		this.children = new ArrayList<ChildActor>();
	}
	
	/**
	 * Constuctor with String name and list for children
	 * @param name
	 * @param children
	 */
	public Node(String name, List<ChildActor> children) {
		this.name = name;
		this.children = children;
	}

	@Override
	public void onReceive(Object object) throws Exception {
		if(object instanceof Message) {
			System.out.println(this.name+" received a message : "+object);
			if(this.children !=null && !this.children.isEmpty()) {
				for(ChildActor a : this.children) {
					synchronized(a) {
						if(a.getIsVisited())
							return;
						else {
							a.childVisited();
							a.getActor().tell(object, getSelf());
						}
					}
				}
			}
		} else if(object instanceof AddChildActor) {
			this.children.add(((AddChildActor) object).getChildActor());
		} else if(object instanceof String) {
			System.out.println(this.name+" received a message : "+object);
		} else {
			this.unhandled(object);
		}
		
	}

}
