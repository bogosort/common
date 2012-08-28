package example.common.lang;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StringUtilsTest {

	@Test
	public void testJoinIterable() {
		Assert.assertEquals(StringUtils.toString(Arrays.asList("a", "b", "c")),
				"abc");
	}

	@Test
	public void joinIteratorString() {
		Assert.assertEquals(StringUtils.join(Arrays.asList("a", "b", "c"),"|"),
				"a|b|c");
	}

	@Test
	public void testToString() {
		Assert.assertEquals(StringUtils.toString(Arrays.asList('a', 'b', 'c')),
				"abc");
	}
}
