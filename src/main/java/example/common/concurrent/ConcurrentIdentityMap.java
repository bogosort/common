package example.common.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Similar idea as the <a href="http://www.martinfowler.com/eaaCatalog/identityMap.html">IdentityMap pattern</a>
 * TODO benchmark against {@link ConcurrentHashSet}
 */
public class ConcurrentIdentityMap<E> {
	protected final ReentrantReadWriteLock elementsLock = new ReentrantReadWriteLock();
	
	@GuardedBy("elementsLock")
	protected final Set<E> elements = new HashSet<>();
	
	@GuardedBy("elementsLock")
	protected final ArrayList<E> list = new ArrayList<>();

	/**
	 * {@link java.util.Set#add(Object)}
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

	public Collection<E> getElements() {
		elementsLock.readLock().lock();
		try {
			return new ArrayList<>(list);
		} finally {
			elementsLock.readLock().unlock();
		}
	}
}
