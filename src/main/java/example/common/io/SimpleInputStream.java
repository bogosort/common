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

/**
 * {@code SimpleInputStream} represents the core ideas of InputStreams of
 * arbitrary types. It can be seen as a simplified generic interface version of
 * the {@link java.io.InputStream} class. As we (maybe) can not rewind the
 * stream it is similar to {@link java.util.Iterator} but without its
 * {@link java.util.Iterator#remove()} method, which is also the reason why
 * concrete {@code SimpleInputStream}s may not implement the
 * {@link java.lang.Iterable} interface.
 * 
 * @author mm
 * 
 * @param <T>
 *            the stream element type
 */
public interface SimpleInputStream<T> {
	/**
	 * Retrieves and removes the next element of the stream or {@code null} if
	 * none are present.
	 * 
	 * @return the next element in the stream
	 * @throws StreamIOException
	 *             if an I/O error occurs
	 */
	T next() throws StreamIOException;

	/**
	 * Returns {@code true} if the stream has more elements.
	 * 
	 * @return {@code true} if the stream has more elements
	 * @throws StreamIOException
	 *             if an I/O error occurs
	 */
	boolean hasNext() throws StreamIOException;
}