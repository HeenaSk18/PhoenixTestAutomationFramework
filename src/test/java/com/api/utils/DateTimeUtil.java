package com.api.utils;

import java.time.temporal.ChronoUnit;

import org.joda.time.Instant;

public class DateTimeUtil {

	private DateTimeUtil() {
		
	}
	
	public static String getTimeWithDaysAgo(int days) {
	    return Instant.now().minus(days).toString();
	}
}
