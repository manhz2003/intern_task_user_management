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
public class VmaAccidentListModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma accident list model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaAccidentList} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_accident_list (id LONG not null primary key,PortofAuthority VARCHAR(75) null,AccidentCode VARCHAR(75) null,AccidentTime DATE null,AccidentRegion VARCHAR(75) null,AccidentBrief VARCHAR(75) null,AccidentConclusion VARCHAR(75) null,AccidentType VARCHAR(75) null,AccidentCriticalType VARCHAR(75) null,DomesticShip VARCHAR(75) null,InternationalShip VARCHAR(75) null,DamageHumanLife VARCHAR(75) null,NumberOfDead VARCHAR(75) null,NumberOfMissed VARCHAR(75) null,NumberOfInjured VARCHAR(75) null,DamageToCargo VARCHAR(75) null,RemarksOfCargo VARCHAR(75) null,DamageToShip VARCHAR(75) null,RemarksOfShip VARCHAR(75) null,DamageToEnvironment VARCHAR(75) null,RemarksOfEnvironment VARCHAR(75) null,DamageToMarineActivity VARCHAR(75) null,RemarksOfMarineActivity VARCHAR(75) null,AccidentOfficialNo VARCHAR(75) null,AccidentOfficialDate DATE null,F1AttachedReport VARCHAR(75) null,F2AttachedReport VARCHAR(75) null,F3AttachedReport VARCHAR(75) null,F4AttachedReport VARCHAR(75) null,NameOfShip VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,RegistryNumber VARCHAR(75) null,PilotOnBoad VARCHAR(75) null,MakeInvestigation VARCHAR(75) null,InvestigationDate DATE null,InvestigationOffice VARCHAR(75) null,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_accident_list";
	
}