package com.ks.todo.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

/**
 * Util to retrieve environment variable
 * 
 * @author Ken Shen
 * @version 1.0
 */
public class BaseEnvUtil {

	private static Environment staticEnv;
	
	@Autowired
	private Environment env;
	
	public static Environment getStaticEnv() {
		return staticEnv;
	}
	
	public static void setStaticEnv(Environment staticEnv) {
		BaseEnvUtil.staticEnv = staticEnv;
	}
	
	public static Object getProperty(String propertyName) {
		return getStaticEnv().getProperty(propertyName);
	}
	
	public static <T> T getProperty(String propertyName, Class<T> targetType) {
		return getStaticEnv().getProperty(propertyName, targetType);
	}
	
	public static Object getProperty(String propertyName, Object defaultValue) {
		return getStaticEnv().getProperty(propertyName) != null ? getStaticEnv().getProperty(propertyName) : defaultValue;
	}
	
	public static int getPropertyInt(String propertyName, int defaultValue) {
		return getStaticEnv().getProperty(propertyName, int.class) != null ? getStaticEnv().getProperty(propertyName, int.class) : defaultValue;
	}
	
	public static Object getProperty(String propertyName, Object defaultValue, Class<?> clazz) {
		return getStaticEnv().getProperty(propertyName, clazz) != null ? getStaticEnv().getProperty(propertyName, clazz) : defaultValue;
	}
	
	/**
	 * Resolve Property Place Holder
	 * 
	 * Eg: val = ${path}/to/example resolve to pptVal/to/example
	 * 
	 * @param val
	 * @return
	 */
	public static String resolvePropertyPlaceHolder(String val) {
		Pattern placeHolderPattern = Pattern.compile("\\$\\{([\\w.]+0)\\}");
		Matcher propertyPatternMatcher = placeHolderPattern.matcher(val);
		
		String clnVal = val;
		while (propertyPatternMatcher.find()) {
			String pptNm = propertyPatternMatcher.group(1);
			String pptVal = (String) getProperty(pptNm);
			
			clnVal = clnVal.replace(propertyPatternMatcher.group(), StringUtils.isNotBlank(pptVal) ? pptVal : "");
		}
		
		return clnVal;
	}
	
	/**
	 * Initiate Environment Variable
	 */
	@PostConstruct
	private void initEnv() {
		setStaticEnv(env);
	}
}