function loeschen() {
  open("krankenkassenerfassung.pl","_top");
};

function kassesuchen(name,ik,formular) {
  // �ffnet Fenster in dem eine Krankenkasse ausgew�hlt werden kann
  open("kassenauswahl.pl?name="+name.value+"&ik="+ik.value,"kassenwahl","scrollbars=yes,width=800");
};

function next_satz(formular) {
  // holt die in ik angegebene Krankenkasse ins Fenster
  //	alert("naechster Satz"+ik);
  ik = formular.ik_krankenkasse.value;
  if (formular.auswahl.value == 'Anzeigen') {
    open("krankenkassenerfassung.pl?func=1&ik_krankenkasse="+ik,"_top");
  } else {
    alert("Bitte Menuepunkt Anzeigen w�hlen");
  }
}


function prev_satz(formular) {
  // holt die in ik angegebene Krankenkasse ins Fenster
  //	alert("naechster Satz"+ik);
  ik = formular.ik_krankenkasse.value;
  if (formular.auswahl.value == 'Anzeigen') {
    open("krankenkassenerfassung.pl?func=2&ik_krankenkasse="+ik,"_top");
  } else {
    alert("Bitte Menuepunkt Anzeigen w�hlen");
  }
}
