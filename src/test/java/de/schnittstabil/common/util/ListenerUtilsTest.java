/*                          __    _                     
 *                         |  |  |_|___ ___ ___ ___ ___ 
 *                         |  |__| |  _| -_|   |_ -| -_|
 *                         |_____|_|___|___|_|_|___|___|
 * 
 * -----------------------------------------------------------------------------
 * Copyright (c) 2012-2013, Michael Mayer <michael@schnittstabil.de>
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: 
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * -----------------------------------------------------------------------------
 *                                                       (BSD 2-Clause License)
 */

package de.schnittstabil.common.util;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Some {@link ListenerUtils#buildAdaptor(Listener, Class)} and
 * {@link ListenerUtils#cast(Listener, Class)} tests.
 * 
 * @author Michael Mayer
 */
public class ListenerUtilsTest {
	private Listener<CharSequence> csListener;
	private List<CharSequence> csListenerResults;

	@BeforeMethod
	public void beforeTest() {
		csListenerResults = new ArrayList<>();
		csListener = new Listener<CharSequence>() {
			@Override
			public void newEvent(CharSequence e) {
				csListenerResults.add(e);
			}
		};
	}

	@AfterMethod
	public void afterTest() {
		csListener = null;
		csListenerResults = null;
	}

	@Test
	public void buildAdaptor() {
		Listener<String> strl = ListenerUtils.buildAdaptor(csListener,
				String.class);
		strl.newEvent("Test1");
		strl.newEvent("Test2");
		Assert.assertEquals(csListenerResults.size(), 2);
		Assert.assertEquals(csListenerResults.get(0), "Test1");
		Assert.assertEquals(csListenerResults.get(1), "Test2");
	}

	@Test
	public void cast() {
		Listener<String> strl = ListenerUtils.cast(csListener, String.class);
		strl.newEvent("Test1");
		strl.newEvent("Test2");
		Assert.assertEquals(csListenerResults.size(), 2);
		Assert.assertEquals(csListenerResults.get(0), "Test1");
		Assert.assertEquals(csListenerResults.get(1), "Test2");
	}
}
