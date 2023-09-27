package com.ks.todo.core.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Property Util to retrieve configured property from application.properties
 * 
 * @author Ken Shen
 * @version 1.0
 */
@Component
@Lazy(false)
public class PropertyUtil extends BaseEnvUtil {
	private PropertyUtil() {
	}

	public static String getEnvironment() {
		return (String) getProperty("todo.app.env", "DEV");
	}
	
	public static String getDefaultLocale() {
		return (String) getProperty("default.locale", Locale.ENGLISH.toString());
	}
	
	public static String getDefaultDateFormat() {
		return (String) getProperty("default.date.format", "dd/MM/yyyy");
	}

	public static String getDefaultDateTimeFormat() {
		return (String) getProperty("default.date.time.format", "dd/MM/yyyy HH:mm:ss, EEE");
	}

	public static String getDefaultDateTimeFormatWithTimezone() {
		SimpleDateFormat sdf = new SimpleDateFormat("XXX");
		String utcTmz = sdf.format(DateUtil.getCurrentDateTime());
		return (String) getProperty("default.date.time.format", "dd/MM/yyyy HH:mm:ss, EEE") + "\\' UTC " + utcTmz
				+ "\\'";
	}

	public static String getDefaultDateTimeFormatShort() {
		return (String) getProperty("default.date.time.format.short", DateUtil.DEFAULT_TIMESTAMP_FORMAT);
	}

	public static String getDefaultTimeFormat() {
		return (String) getProperty("default.time.format", "HH:mm");
	}
	
	public static String getDefaultParseDateFormat() {
		return (String) getProperty("default.parse.date.format", "YYYY-MM-DD");
	}
	
	public static String getResourceServerUri() {
		return (String) getProperty("spring.security.oauth2.resourceserver.jwt.issuer-url", "");
	}

}
