//alert("rechnung.js wird geladen");

function druck_fertig(frau_id) {
  // schreibt Rechnung in Fenster und macht update auf Datenbank
  //alert("Drucken Frau"+frau_id);
  if (frau_id > 0) {
    open("ps2html.pl?frau_id="+frau_id+"&speichern=save","rechnung");
    alert("Rechnung wurde gespeichert\nBitte Rechnung �ber 'Print All' drucken");
  } else {
    alert("Bitte zun�chst Frau ausw�hlen");
  }
};

function bearb_rech(rechnr,status) {
  // Bearbeitungsmaske Rechnung mit Daten f�llen
  if (status < 30) {
    open("rechposbear.pl?rechnungsnr="+rechnr,"rechposbear");
  } else {
    alert("Rechnung ist schon gezahlt, keine weitere Bearbeitung");
  }
}

function recherf(frau_id) {
  // springt in Maske zur Rechnungserfassung
  open("../erfassung/rechnungserfassung.pl?frau_id="+frau_id,"_top");
}

function anseh_rech(rech_id) {
  // neues Fenster mit Rechnung �ffnen
  if (rech_id > 0) {
    open("druck_alt_rech.pl?rech_id="+rech_id,"rech_alt","scrollbars=yes,width=950,heigth=1100");
  } else {
    alert("Bitte Rechnung anw�hlen");
  }
}

//alert("rechnung.js ist geladen");
