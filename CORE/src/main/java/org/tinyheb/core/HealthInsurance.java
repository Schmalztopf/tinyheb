package org.tinyheb.core;

import org.robobinding.annotation.PresentationModel;
import org.tinyheb.core.annotations.MySqlField;
import org.tinyheb.core.annotations.MySqlTable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Insurances")
@MySqlTable(ormtable = @DatabaseTable(tableName = "Krankenkassen"))
@PresentationModel
public class HealthInsurance {
	
	public HealthInsurance() {
		// ORMLite needs empty constructor
	}
	
	@DatabaseField(columnName = "_id",generatedId = false, useGetSet = true, id=true)
	@MySqlField(ormfield = @DatabaseField(columnName = "ik",generatedId = false, useGetSet = true, id=true))
	private String iknumber;
	@DatabaseField(columnName = "name",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "name",useGetSet = true))
	private String name;
	@DatabaseField(columnName = "shortname",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "kname",useGetSet = true))
	private String shortname;
	
	public String getIknumber() {
		return iknumber;
	}
	
	public void setIknumber(String id) {
		this.iknumber = id;
	}
	public String getName() {
		return name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}
	public String getShortname() {
		return shortname;
	}	
	
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	@Override
	public String toString() {
		return getIknumber();
	}
}
