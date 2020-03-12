package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public Date changeToMidnight(Date date, boolean up) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (up) {
			setNextDay(calendar);
		} else {
			setPreviousDay(calendar);
		}
		setMidnight(calendar);
		return calendar.getTime();
	}

	private void setNextDay(Calendar c){
		c.add(Calendar.DATE,  1 );
	}

	private void setPreviousDay(Calendar c){
		c.add(Calendar.DATE,  -1 );
	}

	private void setMidnight(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
}
