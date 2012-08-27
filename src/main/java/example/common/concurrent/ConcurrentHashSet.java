package example.common.concurrent;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A lock free implementation inspired by {@link HashSet} implementation
 * 
 * TODO benchmark against {@link ConcurrentIdentityMap}
 */
public class ConcurrentHashSet<E> {
	private final ConcurrentHashMap<E, Object> map;
	private static final Object PRESENT = new Object();

	public ConcurrentHashSet() {
		map = new ConcurrentHashMap<>();
	}

	public boolean add(E e) {
		return map.put(e, PRESENT) == null;
	}

	public Collection<E> getElements() {
		return map.keySet();
	}
}
