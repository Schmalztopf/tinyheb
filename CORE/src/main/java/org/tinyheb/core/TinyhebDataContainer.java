package org.tinyheb.core;

import java.util.List;

import org.robobinding.annotation.PresentationModel;

@PresentationModel
public class TinyhebDataContainer {
    public List<Patron> patrons;
    public List<HealthInsurance> insurances;
    
	public List<Patron> getPatrons() {
		return patrons;
	}
	public void setPatrons(List<Patron> patrons) {
		this.patrons = patrons;
	}
	public List<HealthInsurance> getInsurances() {
		return insurances;
	}
	public void setInsurances(List<HealthInsurance> insurances) {
		this.insurances = insurances;
	}
}