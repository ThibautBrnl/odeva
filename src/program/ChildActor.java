package program;
import akka.actor.ActorRef;

/**
 * Overriden class of ActorRef for operate isVisited boolean
 * 
 * @author Thibaut Brunel and Julien Roche - 2016 Master 1 Informatique Lille 1 CAR
 */
public class ChildActor {
	/**
	 * Check if actor is already visited or not
	 */
	private boolean isVisited;
	
	/**
	 * Actor reference
	 */
	private ActorRef actor;
	
	/**
	 * Constructor with an actor
	 * @param actor
	 */
	public ChildActor(ActorRef actor) {
		this.isVisited = false;
		this.actor = actor;
	}
	
	/**
	 * Boolean isVisited
	 * @return true if actor is already visited, not otherwise
	 */
	public boolean getIsVisited() {
		return this.isVisited;
	}
	
	/**
	 * Change isVisited status to true
	 */
	public void childVisited() {
		this.isVisited = true;
	}
	
	/**
	 * Getter actor reference
	 * @return the actor reference
	 */
	public ActorRef getActor() {
		return this.actor;
	}

}
