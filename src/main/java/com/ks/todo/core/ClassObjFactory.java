package com.ks.todo.core;

import java.lang.reflect.Constructor;

import org.apache.commons.collections.FastHashMap;

import com.ks.todo.core.exception.SvcException;


/**
 * Class Object Factory to create a new instance of class
 * 
 * @author Ken Shen
 * @version 1.0
 */
public class ClassObjFactory {
	private static FastHashMap clsCache;
	
	static {
		clsCache = new FastHashMap();
		clsCache.setFast(true);
	}
	
	public static <T> T newInstance(Class<T> clsObj) {
		return (T) newInstance(clsObj.getName());
	}
	
	public static Object newInstance(String clsObjNm) {
		Class cls = getClass(clsObjNm);
		
		try {
			Constructor constructor = cls.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		} catch (Exception ex) {
			throw new SvcException("Error when create a new instance of class object [{0}]", new Object[] { clsObjNm }, ex);
		}
	}
	
	public static <T> T newInstance(Class<T> clsObjNm, Class[] argsCls, Object... argsVals) {
		Class cls = getClass(clsObjNm);
		
		try {
			Constructor constructor = cls.getDeclaredConstructor(argsCls);
			constructor.setAccessible(true);
			return (T) constructor.newInstance(argsVals);
		} catch (Exception ex) {
			throw new SvcException("Error when create a new instance of class object [{0}]", new Object[] { clsObjNm }, ex);
		}
	}
	
	public static Class<?> getClass(Class<?> clsObj) {
		return getClass(clsObj.getName());
	}
	
	public static Class<?> getClass(String clsObjNm) {
		Class<?> cls = (Class<?>) clsCache.get(clsObjNm);
		
		if (null != cls) {
			return cls;
		}
		
		// Project Layer
		if (clsObjNm.endsWith("Qq")) {
			try {
				cls = Class.forName(clsObjNm);
				clsCache.put(clsObjNm, cls);
			} catch (ClassNotFoundException ex) {
				throw new SvcException("Error when create a new instance of class object [{0}]", new Object[] { clsObjNm }, ex);
			}
		} else if (clsObjNm.endsWith("Q")) {
			try {
				cls = Class.forName(clsObjNm + "q");
				clsCache.put(clsObjNm, cls);
			} catch (ClassNotFoundException e) {
				try {
					cls = Class.forName(clsObjNm);
					clsCache.put(clsObjNm, cls);
				} catch (ClassNotFoundException ex) {
					throw new SvcException("Error when create a new instance of class object [{0}]", new Object[] { clsObjNm }, ex);
				}
			}
		} else {
			try {
				cls = Class.forName(clsObjNm + "Qq");
				clsCache.put(clsObjNm, cls);
			} catch (ClassNotFoundException ex) {
				try {
					cls = Class.forName(clsObjNm + "Q");
					clsCache.put(clsObjNm, cls);
				} catch (ClassNotFoundException nstEx) {
					try {
						cls = Class.forName(clsObjNm);
						clsCache.put(clsObjNm, cls);
					} catch (ClassNotFoundException nstEx2) {
						throw new SvcException("Error when create a new instance of class object [{0}]",
								new Object[] { clsObjNm }, nstEx2);
					}
				}
			}
		}
		
		return cls;
	}
}