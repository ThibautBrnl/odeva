import static org.junit.Assert.*;

public class Test {

	@Test
	public void testSomeFunction() {
		Main m = new Main();

		Assert.assertEquals(m.someFunction(), 42);
	}
}