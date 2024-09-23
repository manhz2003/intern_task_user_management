/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.fds.nsw.nghiepvu.modelImpl;
public class TempHealthQuestionModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp health question model instance should use the {@link com.fds.nsw.nghiepvu.model.TempHealthQuestion} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_HEALTH_QUESTION (ID LONG not null primary key,RequestCode VARCHAR(75) null,HealthQuestionCode VARCHAR(75) null,PersonDied INTEGER,PersonDiedNo INTEGER,PersonDiedReport VARCHAR(75) null,IsInfectious INTEGER,InfectiousReport VARCHAR(75) null,IllPassengersGreaterNomal INTEGER,IllPassengersNo INTEGER,IllPersonsOnBoard INTEGER,IllPersonsReport INTEGER,MedicalPractitionerConsulted INTEGER,MedicalTreatmentOrAdvice VARCHAR(75) null,InfectionOrSpreadOfDisease INTEGER,InfectionsReport VARCHAR(75) null,IsSanitary INTEGER,SanitaryDes VARCHAR(75) null,IsStowaways INTEGER,JoinShip VARCHAR(75) null,IsAnimal INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_HEALTH_QUESTION";
	public static final String ORDER_BY_JPQL = " ORDER BY tempHealthQuestion.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_HEALTH_QUESTION.ID ASC";
	
}