package org.tinyheb.core;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Patrons")
@MySqlTable(ormtable = @DatabaseTable(tableName = "Stammdaten"))
public class Patron {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@DatabaseField(columnName = "_id",generatedId = false, useGetSet = true, id = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "id",generatedId = false, useGetSet = true, id = true))
	protected int id;
	
	@DatabaseField(columnName = "firstname",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "vorname",useGetSet = true))
	protected String firstname;
	
	@DatabaseField(columnName = "lastname",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "nachname",useGetSet = true))
	protected String lastname;

	public Patron() {}

	public Patron(int id, String firstname, String lastname) {

		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String name) {
		this.firstname = name;
	}
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String name) {
		this.lastname = name;
	}
}
