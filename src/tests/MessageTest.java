package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import program.Message;

public class MessageTest {
	private Message message;
	private final String s = "test";

	@Before
	public void before() {
		message = new Message(s);
	}
	
	@Test
	public void messageTest() {
		assertEquals(message.toString(), "\""+s+"\"");
	}

}
