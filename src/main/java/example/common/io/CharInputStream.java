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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * A canonical implementation of {@code SimpleInputStream<Character>} using a
 * {@link Reader}.
 * 
 * @author mm
 */
public class CharInputStream implements SimpleInputStream<Character> {
	private final Reader reader;
	private boolean eof;
	private char buffer;

	/**
	 * The default constructor.
	 * 
	 * @param reader
	 * @throws StreamIOException
	 *             if an I/O error occurs
	 */
	public CharInputStream(Reader reader) throws StreamIOException {
		this.reader = reader;
		this.eof = reader == null;
		if (reader != null)
			// initialize buffer
			read();
	}

	/**
	 * This constructor uses {@link CharInputStream#CharInputStream(Reader)}.
	 */
	public CharInputStream(String content) throws StreamIOException {
		this(content == null ? null : new StringReader(content));
	}

	/**
	 * This constructor uses {@link CharInputStream#CharInputStream(Reader)}.
	 */
	public CharInputStream(File content) throws FileNotFoundException,
			StreamIOException {
		this(content == null ? null : new BufferedReader(
				new FileReader(content)));
	}

	/**
	 * This constructor uses {@link CharInputStream#CharInputStream(Reader)}.
	 */
	public CharInputStream(InputStream content) throws StreamIOException {
		this(content == null ? null : new BufferedReader(new InputStreamReader(
				content)));
	}

	/*
	 * Returns the buffer element and replace the buffer by an element from the
	 * stream/reader
	 */
	private char read() throws StreamIOException {
		char result = buffer;

		int temp;
		try {
			temp = reader.read();
		} catch (IOException e) {
			throw new StreamIOException(e);
		}

		if (temp == -1) {
			eof = true;
			buffer = 0;
		} else {
			eof = false;
			buffer = (char) temp;
		}
		return result;
	}

	@Override
	public Character next() throws StreamIOException {
		if (eof)
			return null;
		return read();
	}

	@Override
	public boolean hasNext() throws StreamIOException {
		return !eof;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (!eof) {
			sb.append(buffer);
			sb.append(",...");
		}
		sb.append(']');
		return sb.toString();
	}
}
