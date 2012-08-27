package example.common.concurrent;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class ConcurrentHashSetTest {
	private ConcurrentHashSet<String> chs;
	
	@BeforeMethod
	public void beforeMethod() {
		chs = new ConcurrentHashSet<>();
	}

	@AfterMethod
	public void afterMethod() {
		chs = null;
	}

	@Test
	public void add() {
		chs.add("abc");
		Assert.assertTrue(chs.getElements().contains("abc"));
		Assert.assertFalse(chs.getElements().contains("abcd"));
	}
}
