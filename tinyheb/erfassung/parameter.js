function next_satz_parms(formular) {
  //  alert("next"+formular.leist_id);
  id = formular.id.value;
  if(formular.auswahl.value == 'Anzeigen') {
    open("parameter.pl?func=1&id="+id,"_top");
  } else {
    alert("Bitte Menuepunkt Anzeigen w�hlen");
  }
}

function prev_satz_parms(formular) {
  id = formular.id.value;
  if(formular.auswahl.value == 'Anzeigen') {
    open("parameter.pl?func=2&id="+id,"_top");
  } else {
    alert("Bitte Menuepunkt Anzeigen w�hlen");
  }
}


function parmsuchen(pname,pvalue,pbeschreibung) {
  // �ffnet Fenster in Parameter gesucht und ausgew�hlt werden kann
  open("parameterauswahl.pl?pname="+pname.value+"&pvalue="+pvalue.value+"&pbeschreibung="+pbeschreibung.value+"&suchen=Suchen","parmauswahl","srollbar=yes,width=700,height=400");
}
