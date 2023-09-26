package com.ks.todo.core.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.owasp.esapi.ESAPI;

/**
 * Logging Utilities
 * 
 * @author Ken Shen
 * @version 1.0
 */
public final class LogUtil {
	private LogUtil() {
		super();
	}

	/**
	 * Encode String with HTML Encoder Format
	 * 
	 * @param x
	 * @return {@link String}
	 */
	public static String encode(String x) {
		if (null == x) {
			return "x";
		}

		String clean = x.replace('\n', '_').replace('\r', '_').replace('\t', '_');
		return ESAPI.encoder().encodeForHTML(clean);
	}

	/**
	 * Encode List of String with HTML Encoder Format
	 * 
	 * @param x
	 * @return {@link String}
	 */
	public static String encode(List<String> x) {
		return encode(StringUtils.join(x, ", "));
	}

	/**
	 * Encode List of String with HTML Encoder Format
	 * 
	 * @param x
	 * @return {@link String}
	 */
	public static Object[] encode(Object... objs) {
		Object[] cleanLog = null;

		if (null != objs && objs.length > 0) {
			cleanLog = new Object[objs.length];
			for (int i = 0; i < objs.length; i++) {
				cleanLog[i] = ESAPI.encoder().encodeForHTML(String.valueOf(objs[i]));
			}
		}

		return cleanLog;
	}
}
