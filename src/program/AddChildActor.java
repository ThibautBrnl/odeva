package program;
import akka.actor.ActorRef;

/**
 * Add child for an actor
 * 
 * @author Thibaut Brunel and Julien Roche - 2016 Master 1 Informatique Lille 1 CAR
 */
public class AddChildActor {
	/**
	 * Child to add
	 */
	private ChildActor childActor;
	
	/**
	 * Constructor with child actor
	 * @param childActor
	 */
	public AddChildActor(ChildActor childActor) {
		this.childActor = childActor;
	}
	
	/**
	 * Constructor with actor reference
	 * @param actor
	 */
	public AddChildActor(ActorRef actor) {
		this.childActor = new ChildActor(actor);
	}
	
	/**
	 * Get child actor
	 * @return ChildActor
	 */
	public ChildActor getChildActor() {
		return this.childActor;
	}
	
	/**
	 * Get actor reference
	 * @return ActorRef
	 */
	public ActorRef getActor() {
		return this.childActor.getActor();
	}

}
