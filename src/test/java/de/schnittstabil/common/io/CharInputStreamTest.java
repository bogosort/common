package de.schnittstabil.common.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Some simple tests.
 * 
 * @author Michael Mayer
 */
public class CharInputStreamTest {
	
	@DataProvider(name = "nonNull")
	public Object[][] createData1() {
		return new Object[][] { { "abc" }, { "a" }, { "" } };
	}

	@Test(dataProvider = "nonNull")
	public void nonNull(String s) throws StreamIOException {
		CharInputStream cis = new CharInputStream(s);
		StringBuffer sb = new StringBuffer();
		while (cis.hasNext())
			sb.append(cis.next());
		if (s == null)
			Assert.assertEquals(sb.length(), 0);
		else
			Assert.assertEquals(sb.toString(), s);
	}

	/**
	 * Test CharInputStream constructors with <code>null</code> parameters.
	 * @throws StreamIOException
	 */
	@Test()
	public void testNull() throws StreamIOException {
		Assert.assertEquals(new CharInputStream((Reader) null).hasNext(), false);
		Assert.assertEquals(new CharInputStream((String) null).hasNext(), false);
		Assert.assertEquals(new CharInputStream((InputStream) null).hasNext(),
				false);

		CharInputStream file;
		try {
			file = new CharInputStream((File) null);
			Assert.assertEquals(file.hasNext(), false);
		} catch (FileNotFoundException e) {
			Assert.fail();
		}
	}

}
