/*                          __    _                     
 *                         |  |  |_|___ ___ ___ ___ ___ 
 *                         |  |__| |  _| -_|   |_ -| -_|
 *                         |_____|_|___|___|_|_|___|___|
 * 
 * -----------------------------------------------------------------------------
 * Copyright (c) 2012, Michael Mayer <mm.pub@gmx.de>
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

package example.common.io;

import java.util.List;

/**
 * A {@code SimpleInputStream<T>} capable to deal with <a
 * href="http://en.wikipedia.org/wiki/Parsing#Lookahead">Lookahead</a>.
 * 
 * @author mm
 */
public interface LookAheadStream<T> extends SimpleInputStream<T> {

	/**
	 * Returns {@code true} if the stream has {@code n} more elements.
	 * 
	 * @param n
	 *            the number of elements
	 * @return {@code true} if the stream has {@code n} more elements
	 * @throws StreamIOException
	 *             if an I/O error occurs
	 * 
	 * @see SimpleInputStream#hasNext()
	 */
	boolean hasNext(int n) throws StreamIOException;

	/**
	 * Retrieves and removes the next {@code n} elements of the stream.
	 * 
	 * @param n
	 *            the number of elements
	 * @return the next {@code n} element in the stream
	 * @throws StreamIOException
	 *             if an I/O error occurs
	 */
	List<T> next(int n) throws StreamIOException;

	/**
	 * Retrieves the element at {@code position} of the stream.
	 * 
	 * @param position
	 *            the position of the element
	 * @return the element at {@code position}
	 * @throws StreamIOException
	 *             if an I/O error occurs
	 */
	T peek(int position) throws StreamIOException;

	/**
	 * Retrieves the next element of the stream.
	 * 
	 * @return the next element in the stream
	 * @throws StreamIOException
	 *             if an I/O error occurs
	 */
	T peek() throws StreamIOException;

}