function loeschen() {
  open("krankenkassenerfassung.pl","_top");
};

function kassesuchen(name,ik,formular) {
  // �ffnet Fenster in dem eine Krankenkasse ausgew�hlt werden kann
  open("kassenauswahl.pl?name="+name.value+"&ik="+ik.value,"kassenwahl","scrollbars=yes,width=600,height=400");
};

function next_satz(formular) {
	var id = 0;
	id = new Number(formular.krank_id.value);	
	id++;
//	alert("naechster Satz"+formular+id);
	if (formular.auswahl.value == 'Anzeigen') {
		open("krankenkassenerfassung.pl?func=1&krank_id="+id,"_top");
	} else {
		alert("Bitte Menuepunkt Anzeigen w�hlen");
	}
}

function prev_satz(formular) {
	var id = 0;
	id = new Number(formular.krank_id.value);	
	id--;
//	alert("naechster Satz"+formular+id);
	if (formular.auswahl.value == 'Anzeigen') {
		open("krankenkassenerfassung.pl?func=2&krank_id="+id,"_top");
	} else {
		alert("Bitte Menuepunkt Anzeigen w�hlen");
	}
}
