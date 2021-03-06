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

package de.schnittstabil.common.lang;

import java.util.Iterator;

/**
 * Some join implementations, found in many programming languages (e.g. PHP).
 * 
 * @author Michael Mayer
 */
public class StringUtils {

	/**
	 * Returns a string containing the string representations of the elements of
	 * {@code it} - obtained by {@link String#valueOf(Object)} - without any
	 * separator between the elements or {@code null} if {@code it==null}.
	 * 
	 * @param it
	 *            the elements to join
	 * @return a string containing the strings of the elements of {@code it}
	 */
	public static String join(Iterable<?> it) {
		return join(it, "");
	}

	/**
	 * Returns a string containing the string representations of the elements of
	 * {@code it} - obtained by {@link String#valueOf(Object)} with
	 * {@code separator} between the elements or {@code null} if
	 * {@code it==null}.
	 * 
	 * @param it
	 *            the elements to join
	 * @param separator
	 *            the separator
	 * @return a string containing the strings of the elements of {@code it}
	 */
	public static String join(Iterable<?> it, CharSequence separator) {
		if (it == null)
			return null;
		return join(it.iterator(), separator);
	}

	/**
	 * Returns a string containing the string representations of the elements of
	 * {@code it} - obtained by {@link String#valueOf(Object)} with
	 * {@code separator} between the elements or {@code null} if
	 * {@code it==null}.
	 * 
	 * @param it
	 *            the elements to join
	 * @param separator
	 *            the separator
	 * @return a string containing the strings of the elements of {@code it}
	 */
	public static String join(Iterator<?> it, CharSequence separator) {
		if (it == null)
			return null;
		if (!it.hasNext())
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(it.next());
		while (it.hasNext())
			sb.append(separator).append(String.valueOf(it.next()));
		return sb.toString();
	}
}
