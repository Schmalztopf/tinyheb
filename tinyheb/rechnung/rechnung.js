//alert("rechnung.js wird geladen");

function druck_fertig(frau_id) {
  // schreibt Rechnung in Fenster und macht update auf Datenbank
  if (frau_id > 0) {
    open("ps2html.pl?name="+frau_id+"&speichern=save","rechnung");
    alert("Rechnung wurde gespeichert\nBitte Rechnung �ber 'Print All' drucken");
  } else {
    alert("Bitte zun�chst Frau ausw�hlen");
  }
};

function recherf(frau_id) {
  // springt in Maske zur Rechnungserfassung
  open("../erfassung/rechnungserfassung.pl?frau_id="+frau_id,"_top");
}

//alert("rechnung.js ist geladen");
