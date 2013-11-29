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

/**
 * Utility class providing methods for casting listeners with <a href=
 * "http://en.wikipedia.org/wiki/Covariance_and_contravariance_%28computer_science%29"
 * >contravariant parameters</a>.
 * 
 * @author Michael Mayer
 */
public class ListenerUtils {

	/**
	 * Cast a {@link Listener} of type <tt>Listener&lt;S&gt;</tt> to a listener
	 * of type <tt>Listener&lt;T&gt;</tt>. This is possible because Listener
	 * only uses parameter of type <tt>T</tt> but never returns something of
	 * type <tt>T</tt> and <tt>T extends S</tt>. So the input parameter can
	 * always be casted from <tt>S</tt> to <tt>T</tt>.
	 * 
	 * @see <a
	 *      href="http://docs.oracle.com/javase/tutorial/java/generics/erasure.html">Type
	 *      Erasure</a>
	 * 
	 * @param listener
	 *            the listener to be casted
	 * @param clazz
	 *            type parameter for return type
	 * @return casted listenercast
	 */
	@SuppressWarnings("unchecked")
	public static <S, T extends S> Listener<T> cast(final Listener<S> listener,
			Class<T> clazz) {
		// possible due to type erasure of the JVM
		return (Listener<T>) listener;
	}

	/**
	 * This is a fall back implementation of the {@link #cast(Listener, Class)}
	 * method, because {@link #cast(Listener, Class)} feels like a bloody hack.
	 * This implementation simply builds an adaptor and guarantees a well typed
	 * {@link #cast(Listener, Class)} method (NB return types).
	 * 
	 * @param listener
	 *            the listener to be casted
	 * @param clazz
	 *            type parameter for return type
	 * @return casted listener
	 */
	public static <S, T extends S> Listener<T> buildAdaptor(
			final Listener<S> listener, Class<T> clazz) {
		// build adaptor
		return new Listener<T>() {
			@Override
			public void newEvent(T event) {
				listener.newEvent(event);
			}
		};
	}
}
