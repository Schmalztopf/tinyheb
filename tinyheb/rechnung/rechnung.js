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

//alert("rechnung.js ist geladen");
