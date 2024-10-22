/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.nsw.liferay.file;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public interface CalendarFactory {

	public Calendar getCalendar();

	public Calendar getCalendar(int year, int month, int date);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second);

	public Calendar getCalendar(Locale locale);

	public Calendar getCalendar(TimeZone timeZone);

	public Calendar getCalendar(TimeZone timeZone, Locale locale);

}