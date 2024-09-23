package vn.gt.utils.config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @date: 7 Aug 2009
 */
public class ConcurrentProperties<K, V> extends ConcurrentHashMap<K, V>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConcurrentProperties(int capacity) {
		super(capacity);
	}
}
