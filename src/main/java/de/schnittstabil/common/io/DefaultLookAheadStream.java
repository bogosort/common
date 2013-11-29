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

package de.schnittstabil.common.io;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A canonical implementation of {@code LookAheadStream<T>} wrapping a
 * {@link SimpleInputStream}.
 * 
 * @author Michael Mayer
 */
public class DefaultLookAheadStream<T> implements LookAheadStream<T> {
	private final SimpleInputStream<T> input;
	private final LinkedList<T> buffer;

	public DefaultLookAheadStream(SimpleInputStream<T> input) {
		this.input = input;
		this.buffer = new LinkedList<T>();
	}

	/*
	 * Fill the buffer until {@code position} or the end of the stream is
	 * reached.
	 */
	private void fillBuffer(int position) throws StreamIOException {
		int n = position - buffer.size() + 1;
		for (int i = 0; i < n && input.hasNext(); i++)
			buffer.offerLast(input.next());
	}

	@Override
	public T peek() throws StreamIOException {
		return peek(0);
	}

	@Override
	public T peek(int position) throws StreamIOException {
		fillBuffer(position);

		if (buffer.size() < position + 1)
			return null;

		return buffer.get(position);
	}

	@Override
	public List<T> next(int n) throws StreamIOException {
		List<T> result = new ArrayList<T>(n);
		// remove from buffer
		while (n >= 0 && buffer.size() > 0) {
			result.add(buffer.pollLast());
			n--;
		}
		// remove from input
		while (n >= 0 && input.hasNext()) {
			result.add(input.next());
			n--;
		}
		if (n >= 0)
			return null;
		return result;
	}

	@Override
	public boolean hasNext(int n) throws StreamIOException {
		fillBuffer(n);
		return buffer.size() > n;
	}

	@Override
	public T next() throws StreamIOException {
		if (buffer.size() > 0)
			return buffer.pollLast();
		return input.next();
	}

	@Override
	public boolean hasNext() throws StreamIOException {
		return hasNext(0);
	}

	@Override
	public String toString() {
		return "{" + buffer + ", " + input + "}";
	}
}