function loeschen() {
  open("stammdatenerfassung.pl","_top");
};

function frausuchen(vorname,nachname,geb,formular) {
  // �ffnet Fenster in dem eine Frau ausgew�hlt werden kann
  open("frauenauswahl.pl?vorname="+vorname.value+"&nachname="+nachname.value+"&geb_f="+geb.value+"&suchen=Suchen","frauenwahl","scrollbars=yes,width=700,height=400");
  };

function kvnr_check(kvnummer) {
  re=/^\d{10}$/;
  if (kvnummer.value != '' && !re.test(kvnummer.value)) {
    alert("Bitte KV-Nummer 10 stellig numerisch erfassen");
    kvnummer.focus();
    kvnummer.select();
  }
}

function kvnr_gueltig_check(kvnr_gueltig) {
//  alert("gueltig"+kvnr_gueltig.value);
  re=/^[0-1][0-9]\d{2}$/;
  if (kvnr_gueltig.value != '' && !re.test(kvnr_gueltig.value)) {
    alert("Bitte G�ltigkeit im Format mmjj erfassen");
    kvnr_gueltig.focus();
    kvnr_gueltig.select();
  }
}

  function kassen_auswahl() {
  // �ffnet Fenster in dem eine Krankenkasse ausgew�hlt werden kann
  open("kassenauswahl.pl","kassenwahl","scrollbars=yes,width=600,height=400");
  }


  function frau_eintrag(frau_id,vorname,nachname,geb_f,geb_k,plz,ort,tel,strasse,bundesland,entfernung,kranknr,kranknrguelt,verstatus,nae_heb,begr_nicht_nae_heb) {
  // �bertragt Daten in die Stammdatenmaske

    // in Parent Dokument �bernehmen
    // alert("parent"+opener.document.forms[0]);
    opener.document.stammdaten.frau_id.value=frau_id;
    opener.document.stammdaten.vorname.value=vorname;
    opener.document.stammdaten.nachname.value=nachname;
    opener.document.stammdaten.geburtsdatum_frau.value=geb_f;
    opener.document.stammdaten.plz.value=plz;
    opener.document.stammdaten.ort.value=ort;
    opener.document.stammdaten.strasse.value=strasse;
    opener.document.stammdaten.geburtsdatum_kind.value=geb_k;
    var bund=['NRW','Bayern','Rheinlandpfalz','Hessen'];
    for (var i=0; i<4;i++) {
       if (bund[i] == bundesland) {
         opener.document.stammdaten.bundesland.selectedIndex=i;
       }
    }
    opener.document.stammdaten.entfernung.value=entfernung;
    opener.document.stammdaten.krankenversicherungsnummer.value=kranknr;
    opener.document.stammdaten.krankenversicherungsnummer_gueltig.value=kranknrguelt;
    opener.document.stammdaten.tel.value=tel;

    for (i=0;i<opener.document.stammdaten.versichertenstatus.options.length;i++) {
       if ( opener.document.stammdaten.versichertenstatus.options[i].text == verstatus) {
         opener.document.stammdaten.versichertenstatus.selectedIndex=i;
       }
    }
    opener.document.stammdaten.naechste_hebamme.checked = false;      
    opener.document.stammdaten.nicht_naechste_heb.disabled=false;
    if (nae_heb=='j') {
      opener.document.stammdaten.naechste_hebamme.checked = true;
      opener.document.stammdaten.nicht_naechste_heb.disabled=true;
    }
    opener.document.stammdaten.nicht_naechste_heb.value=begr_nicht_nae_heb;
}
    

function check_begr(wert,formular) {
// �ndert im �bergebenen Formular ob Begr�ndung erfasst werden kann oder nicht
	// alert("check_begr"+wert.checked);
	formular.nicht_naechste_heb.disabled=wert.checked;

}

function next_satz(formular) {
	var id = 0;
	id = new Number(formular.frau_id.value);	
	id++;
//	alert("naechster Satz"+formular+id);
	if (formular.auswahl.value == 'Anzeigen') {
		open("stammdatenerfassung.pl?func=1&frau_id="+id,"_top");
	} else {
		alert("Bitte Menuepunkt Anzeigen w�hlen");
	}
}

function prev_satz(formular) {
	var id = 0;
	id = new Number(formular.frau_id.value);	
	id--;
//	alert("naechster Satz"+formular+id);
	if (formular.auswahl.value == 'Anzeigen') {
		open("stammdatenerfassung.pl?func=2&frau_id="+id,"_top");
	} else {
		alert("Bitte Menuepunkt Anzeigen w�hlen");
	}
}