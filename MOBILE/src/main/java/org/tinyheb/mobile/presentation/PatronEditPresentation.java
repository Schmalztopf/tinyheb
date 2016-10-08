package org.tinyheb.mobile.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.adapterview.ItemClickEvent;
import org.tinyheb.core.DateFormatter;
import org.tinyheb.core.Patron;

import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Patrons")
@PresentationModel
public class PatronEditPresentation extends Patron {

	private Locale currentLocale = new Locale("de","DE");

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	public String getInsuranceName() {
		if (insurance == null) return "";
		return insurance.getIknumber() + " - " + insurance.getName();
	}

	public String getInsuranceIKNumber () {
		return insurance.getIknumber();
	}	

	public String getPatronBirthdate() {
		if (birthdate == null) return "";
		return DateFormatter.toDateString(birthdate, currentLocale);
	}

	public void setPatronBirthdate(String date) {
		this.birthdate = DateFormatter.getDateFromString(date, currentLocale);
	}

	public String getChildBirthdate() {
		if (child_dateofbirth == null) return "";
		return DateFormatter.toDateString(child_dateofbirth, currentLocale);
	}

	public void setChildBirthdate(String date) {
		this.child_dateofbirth = DateFormatter.getDateFromString(date, currentLocale);
	}

	public String getChildTimeOfBirth() {
		if (child_dateofbirth == null) return "";
		return DateFormatter.toTimeString(child_dateofbirth, currentLocale);
	}

	public void calculatedBirthdateSelected() {
		child_dateofbirth_status = 1;
	}

	public void trueBirthdateSelected() {
		child_dateofbirth_status = 2;
	}

	public boolean isCalcualtedBirthdate() {
		return child_dateofbirth_status == 1;
	}

	public boolean isTrueBirthdate() {
		return child_dateofbirth_status == 2;
	}
	

	public void insuranceStatusSelected(ItemClickEvent event) {
		setInsuracncestatus(getInsuranceStatuses().get(event.getPosition()));
	}

	public void numberOfChildrenSelected(ItemClickEvent event) {
		setNumberofchildren(event.getPosition() + 1);
	}
	
	@ItemPresentationModel(value=StringItemPresentation.class)
	public List<String> getInsuranceStatuses() {
		List<String> statusList = new ArrayList<String>();
		statusList.add("1 1");
		statusList.add("3 1");
		statusList.add("privat");
		statusList.add("1 9");
		statusList.add("3 9");
		statusList.add("1 7");
		statusList.add("1 8");
		statusList.add("3 8");
		statusList.add("5 1");
		statusList.add("SOZ");
		statusList.add("5 9");
		return statusList;
	}
	
	@ItemPresentationModel(value=StringItemPresentation.class)
	public List<String> getNumberOfChildren() {
		List<String> statusList = new ArrayList<String>();
		statusList.add("Einling");
		statusList.add("Zwillinge");
		statusList.add("Drillinge");
		statusList.add("Vierlinge");
		return statusList;
	}
}
