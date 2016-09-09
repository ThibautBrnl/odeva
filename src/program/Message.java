package program;
/**
 * String message which transits between nodes
 * 
 * @author Thibaut Brunel and Julien Roche - 2016 Master 1 Informatique Lille 1 CAR
 */
public class Message {
	/**
	 * String message
	 */
	private String message;
	
	/**
	 * Constructor with string message
	 * @param message
	 */
	public Message(String message) {
		this.message = message;
	}
	
	/**
	 * to string
	 */
	public String toString() {
		return "\""+this.message+"\"";
	}

}
