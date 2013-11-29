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

package de.schnittstabil.common.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.jcip.annotations.GuardedBy;

/**
 * Similar idea as the <a
 * href="http://www.martinfowler.com/eaaCatalog/identityMap.html">IdentityMap
 * pattern</a>
 * 
 * TODO benchmark against {@link ConcurrentHashSet}
 * 
 * @author Michael Mayer
 */
public class ConcurrentIdentityMap<E> {
	protected final ReentrantReadWriteLock elementsLock = new ReentrantReadWriteLock();

	@GuardedBy("elementsLock")
	protected final Set<E> elements = new HashSet<>();

	@GuardedBy("elementsLock")
	protected final ArrayList<E> list = new ArrayList<>();

	/**
	 * {@link java.util.Set#add(Object)}
	 * 
	 * @param e
	 *            the element to add
	 * @return {@code false} if this set did already contain the element
	 *         {@code e}
	 */
	public boolean add(E e) {
		elementsLock.readLock().lock();
		try {
			if (elements.contains(e))
				return false;
		} finally {
			elementsLock.readLock().unlock();
		}

		// ReentrantReadWriteLock do not allows upgrading from read to write
		elementsLock.writeLock().lock();
		try {
			// recheck
			if (elements.contains(e))
				return false;
			list.add(e);
			return elements.add(e);
		} finally {
			elementsLock.writeLock().unlock();
		}
	}

	/**
	 * Retruns the elements of the set.
	 * 
	 * @return the elements of the set
	 */
	public Collection<E> getElements() {
		elementsLock.readLock().lock();
		try {
			return new ArrayList<>(list);
		} finally {
			elementsLock.readLock().unlock();
		}
	}
}
