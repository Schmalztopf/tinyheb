package org.tinyheb.core;

import java.util.Date;

import org.tinyheb.core.annotations.MySqlField;
import org.tinyheb.core.annotations.MySqlTable;

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
	
	@DatabaseField(columnName = "birthdate",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "geburtsdatum_frau",useGetSet = true))
	protected Date birthdate;
	
	@DatabaseField(columnName = "telefonnumber",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "tel",useGetSet = true))
	protected String telefonnumber;
	
	@DatabaseField(columnName = "street",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "strasse",useGetSet = true))
	protected String street;
	
	@DatabaseField(columnName = "postalcode",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "plz",useGetSet = true))
	protected String postalcode;
	
	@DatabaseField(columnName = "city",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "ort",useGetSet = true))
	protected String city;
	
	@DatabaseField(columnName = "distancetooffice",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "entfernung",useGetSet = true))
	protected String distancetooffice;
	
	@DatabaseField(columnName = "insurancenumber",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "krankenversicherungsnummer",useGetSet = true))
	protected String insurancenumber;
	
	@DatabaseField(columnName = "insurancevaliduntil",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "krankenversicherungsnummer_gueltig",useGetSet = true))
	protected String insurancevaliduntil;
	
	@DatabaseField(columnName = "insuracncestatus",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "versichertenstatus",useGetSet = true))
	protected String insuracncestatus;
	
	@DatabaseField(foreign=true, useGetSet = true, columnName="ik")
	@MySqlField(ormfield = @DatabaseField(foreign=true, useGetSet = true, columnName="ik"))
	protected HealthInsurance insurance;
	
	@DatabaseField(columnName = "numberofchildren",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "anz_kinder",useGetSet = true))
	protected int numberofchildren;
	
	@DatabaseField(columnName = "child_dateofbirth",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "geburtsdatum_kind",useGetSet = true))
	protected Date child_dateofbirth;
	
	@DatabaseField(columnName = "child_dateofbirth_status",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "kzetgt",useGetSet = true))
	protected int child_dateofbirth_status;
	
//	@DatabaseField(columnName = "child_timeofbirth",useGetSet = true)
//	@MySqlField(ormfield = @DatabaseField(columnName = "geburtszeit_kind",useGetSet = true))
//	protected Time child_timeofbirth;
	
	@DatabaseField(columnName = "private_insurance_factor",useGetSet = true)
	@MySqlField(ormfield = @DatabaseField(columnName = "privat_faktor",useGetSet = true))
	protected double private_insurance_factor;
	

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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getTelefonnumber() {
		return telefonnumber;
	}

	public void setTelefonnumber(String telefonnumber) {
		this.telefonnumber = telefonnumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistancetooffice() {
		return distancetooffice;
	}

	public void setDistancetooffice(String distancetooffice) {
		this.distancetooffice = distancetooffice;
	}

	public String getInsurancenumber() {
		return insurancenumber;
	}

	public void setInsurancenumber(String insurancenumber) {
		this.insurancenumber = insurancenumber;
	}

	public String getInsurancevaliduntil() {
		return insurancevaliduntil;
	}

	public void setInsurancevaliduntil(String insurancevaliduntil) {
		this.insurancevaliduntil = insurancevaliduntil;
	}

	public String getInsuracncestatus() {
		return insuracncestatus;
	}

	public void setInsuracncestatus(String insuracncestatus) {
		this.insuracncestatus = insuracncestatus;
	}

	public HealthInsurance getInsurance() {
		return insurance;
	}

	public void setInsurance(HealthInsurance insurance) {
		this.insurance = insurance;
	}

	public int getNumberofchildren() {
		return numberofchildren;
	}

	public void setNumberofchildren(int numberofchildren) {
		this.numberofchildren = numberofchildren;
	}

	public Date getChild_dateofbirth() {
		return child_dateofbirth;
	}

	public void setChild_dateofbirth(Date child_dateofbirth) {
		this.child_dateofbirth = child_dateofbirth;
	}


	public int getChild_dateofbirth_status() {
		return child_dateofbirth_status;
	}

	public void setChild_dateofbirth_status(int child_dateofbirth_status) {
		this.child_dateofbirth_status = child_dateofbirth_status;
	}

	public double getPrivate_insurance_factor() {
		return private_insurance_factor;
	}

	public void setPrivate_insurance_factor(double private_insurance_factor) {
		this.private_insurance_factor = private_insurance_factor;
	}

//	public Time getChild_timeofbirth() {
//		return child_timeofbirth;
//	}
//
//	public void setChild_timeofbirth(Time child_timeofbirth) {
//		this.child_timeofbirth = child_timeofbirth;
//	}
}
