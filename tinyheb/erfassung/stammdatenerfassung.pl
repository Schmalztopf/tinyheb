#!/usr/bin/perl -wT
#-wT
#-d:ptkdb
#-d:DProf  

# Stammdaten erfassen
# Copyright (C) 2004,2005,2006, 2007 Thomas Baum <thomas.baum@arcor.de>
# Thomas Baum, 42719 Solingen, Germany

# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.

use strict;
use CGI;
use CGI::Carp qw(fatalsToBrowser);
use Date::Calc qw(Today);

use lib "../";
use Heb_stammdaten;
use Heb_krankenkassen;
use Heb_leistung;
use Heb_datum;

my $q = new CGI;
my $s = new Heb_stammdaten;
my $k = new Heb_krankenkassen;
my $d = new Heb_datum;
my $l = new Heb_leistung;

my $debug=1;

my $TODAY = sprintf "%4.4u-%2.2u-%2.2u",Today();
my @aus = ('Anzeigen','�ndern','Neu','L�schen');
my @kinder = ('Einlinge','Zwillinge','Drillinge','Vierlinge');
my @verstatus = ('1 1','3 1','privat','1 9','3 9','1 7','3 7','1 8','3 8','5 1');

my $hint = '';

my $frau_id = $q->param('frau_id') || '0';
my $frau_id2 = $q->param('frau_id2') || $frau_id;
my $vorname = $q->param('vorname') || '';
my $nachname = $q->param('nachname') || '';
my $geb_frau = $q->param('geburtsdatum_frau') || '';
my $tel = $q->param('tel') || '';
my $strasse = $q->param('strasse') || '';
my $plz = $q->param('plz') || '';
my $ort = $q->param('ort') || '';
my $entfernung = $q->param('entfernung') || '';
my $kv_nummer = $q->param('krankenversicherungsnummer') || '';
my $kv_gueltig = $q->param('krankenversicherungsnummer_gueltig') || '';
my $versichertenstatus = $q->param('versichertenstatus') || '';
my $ik_krankenkasse = $q->param('ik_krankenkasse') || '';
my $anz_kinder = $q->param('anz_kinder') || 1;
my $name_krankenkasse = '';
my $plz_krankenkasse = '';
my $ort_krankenkasse = '';
my $strasse_krankenkasse = '';
my $geb_kind = $q->param('geburtsdatum_kind') || '';
my $naechste_hebamme = $q->param('naechste_hebamme');
my $begruendung_nicht_nae_heb = $q->param('nicht_naechste_heb') || '';
my $datum = $q->param('datum') || '';
my $speichern = $q->param('Speichern');
my $auswahl = $q->param('auswahl') || 'Anzeigen';
my $abschicken = $q->param('abschicken');
my $func = $q->param('func') || 0;
#my $frau_suchen = $q->param('frau_suchen');
my $status_edi='kein elektronischer Datenaustausch';

hole_frau_daten() if ($func == 1 || $func == 2 || $func==3);

# Infos zur Krankenkasse holen
if ($ik_krankenkasse ne '' && $ik_krankenkasse > 0) {
  ($name_krankenkasse,
   $plz_krankenkasse,
   $ort_krankenkasse,
   $strasse_krankenkasse) = $k->krankenkasse_sel('NAME,PLZ_HAUS,ORT,STRASSE',$ik_krankenkasse);
  if (!defined($name_krankenkasse)) {
    $name_krankenkasse = 'nicht bekannte IK angegeben';
  } else {
    # ermitteln Status Datenaustausch
    my $test_ind= $k->krankenkasse_test_ind($ik_krankenkasse);
    if (defined($test_ind)) {
      $status_edi='Testphase' if ($test_ind == 0);
      $status_edi='Erprobungsphase' if ($test_ind == 1);
      $status_edi='Echtbetrieb' if ($test_ind == 2);
      $status_edi='unbekannt, Bitte Parameter pr�fen' if ($test_ind != 0 && $test_ind != 1 && $test_ind != 2);
    }
  }
} elsif($versichertenstatus ne 'privat') {
  $name_krankenkasse = 'noch keine g�ltige Krankenkasse gew�hlt';
} else {
  $name_krankenkasse = 'Privat versichert';
}

$ort_krankenkasse = '' unless defined ($ort_krankenkasse);
$plz_krankenkasse = '' unless defined ($plz_krankenkasse);
$strasse_krankenkasse = '' unless defined ($strasse_krankenkasse);

print $q->header ( -type => "text/html", -expires => "-1d");

if (($auswahl eq 'Neu') && defined($abschicken)) {
  $frau_id = speichern();
  $auswahl = 'Anzeigen';
}
if (($auswahl eq '�ndern') && defined($abschicken)) {
  aendern();
  $auswahl = 'Anzeigen';
}
print '<head>';
print '<title>Stammdaten</title>';
print '<script language="javascript" src="stammdaten.js"></script>';
print '<script language="javascript" src="leistungen.js"></script>';
print '<script language="javascript" src="../Heb.js"></script>';
print '<link href="../Heb.css" rel="stylesheet" type="text/css">';
print '</head>';

if (($auswahl eq 'L�schen') && defined($abschicken)) {
  loeschen();
  if ($hint eq '') {
    print '<script>loeschen();</script>';
  } else {
    print "<script>alert('$hint');</script>";
    $auswahl='Anzeigen';
  }
}


# Alle Felder zur Eingabe ausgeben
print '<body id="stammdaten_window" bgcolor=white>';
print '<div align="center">';
print '<h1>Stammdaten</h1>';
print '<hr width="90%">';
print '</div><br>';
# Formular ausgeben
print '<form name="stammdaten" action="stammdatenerfassung.pl" method="get" target=_top onsubmit="return frau_speicher(this);" bgcolor=white>';
print '<table border="0" width="700" align="left">';

# Zeile Vorname, Nachname, Geburtsdatum
# z1 s1
print '<tr><td><table border="0" align="left">';
print '<tr>';
print '<td><b>ID:</b></td>';
print '<td><b>Vorname:</b></td>';
print '<td><b>Nachname:</b></td>';
print '<td><b>Geb.:</b></td>';
print '</tr>';
print "\n";

# z2 s1
print '<tr>';
print "<input type='hidden' name='frau_id' value='$frau_id' size='5'>";
print "<td><input type='text' disabled class='disabled' name='frau_id2' value='$frau_id2' size='5'></td>";
print "<td><input type='text' name='vorname' value='$vorname' size='30' maxlength='30'></td>";
# z2 s2
print "<td><input type='text' name='nachname' value='$nachname' size='47' maxlength='47'></td>";
# z2 s3
print "<td><input type='text' name='geburtsdatum_frau' value='$geb_frau' size='10' maxlength='10' onChange='datum_check(this)'></td>";
print "<td><input type='button' name='frau_suchen' value='Suchen' onClick='return frausuchen(stammdaten.vorname,stammdaten.nachname,stammdaten.geburtsdatum_frau,form);'></tr>";
print '</table>';
print "\n";

# Zeile Telefonnumer
print '<tr><td><b>Tel.:</b></td></tr>';
print "<tr><td><input type='text' name='tel' value='$tel' size='40'></td>";

print '</tr>';
print "\n";

# leere Zeile
print '<tr><td>&nbsp;</td></tr>';

# Zeile PLZ, Ort, Strasse, Entfernung
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td><b>PLZ:</b></td>';
print '<td><b>Ort:</b></td>';
print '<td><b>Strasse:</b></td>';
print '</tr>';

# Eingabe Felder
print "<tr>";
print "<td><input type='text' name='plz' value='$plz' size='5' maxlength='5' onChange='plz_check(this)'></td>";
print "<td><input type='text' name='ort' value='$ort' size='25' maxlength='25'></td>";
print "<td><input type='text' name='strasse' value='$strasse' size='30' maxlength='30'></td>";
print '</tr>';
print '</table>';
print "\n";

print '<tr>';
print '<td><b>Entfernung:</b></td>';
print '</tr>';

print '<tr>';
$entfernung =~ s/\./,/g;
print "<td><input type='text' name='entfernung' value='$entfernung' size='10'></td>";
print '</tr>';
print "\n";

# leere Zeile 
print '<tr><td>&nbsp;</td></tr>';
# Zeile Krankenversicherungsnummer, Versichertenstatus, IK Krankenkasse
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr><td><b>KV-Nummer:</b></td>';
print '<td><b>G�ltig bis:</b></td>';
print '<td><b>Versichertenstatus:</b></td>';
print '<td><b>IK Krankenkasse:</b></td>';
print '</tr>';
print "\n";

print '<tr>';
print "<td><input type='text' name='krankenversicherungsnummer' value='$kv_nummer' size='10' maxlength='10' onChange='kvnr_check(this);'></td>";
print "<td><input type='text' name='krankenversicherungsnummer_gueltig' value='$kv_gueltig' size='4' maxlength='4' onChange='kvnr_gueltig_check(this)'></td>";
# z4.2 s3
print '<td>';
print "<select name='versichertenstatus' size=1>";
my $j=0;
while ($j <= $#verstatus) {
  print "<option value='$verstatus[$j]'";
  print ' selected' if ($verstatus[$j] eq $versichertenstatus);
  print '>';
  print $verstatus[$j];
  print '</option>';
  $j++;
}
print "</td>\n";
print "<td><input type='text' name='ik_krankenkasse' value='$ik_krankenkasse' size='10' maxlength='9' onChange='ik_gueltig_check(this)'></td>";
print "<td><input type='button' name='kasse_waehlen' value='Kasse ausw�hlen' onClick='return kassen_auswahl();'></td>";
print '</tr>';
print '</table>';
print "\n";

# zeile mit Krankenkasseninfos ausgeben
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td><b>Name Krankenkasse</b></td>';
print '<td><b>Ort</b></td>';
print '<td><b>Stra�e</b></td>';
print '<td><b>Status Datenaustausch</b></td>';
print '</tr>';
print '<tr>';
print "<td><input type='text' class=disabled disabled name='name_krankenkasse' value='$name_krankenkasse' size='28'></td>";
print "<td><input type='text' class=disabled disabled name='ort_krankenkasse' value='$plz_krankenkasse&nbsp;$ort_krankenkasse' size='30'></td>";
print "<td><input type='text' class=disabled disabled name='strasse_krankenkasse' value='$strasse_krankenkasse' size='20'></td>";
print "<td><input type='text' class=disabled disabled name='status_edi_krankenkasse' value='$status_edi' size='30'></td>";
print '</tr>';
print '</table>';
print "\n";

# Zeile mit Geburtsdatum Kind
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td><b>Geburtsdatum Kind</b></td>';
print '<td><b>Anzahl Kinder</b></td>';
print '</tr>';
print "<td><input type='text' name='geburtsdatum_kind' value='$geb_kind' size='10' maxlength='10' onChange='return datum_check(this)'></td>";
print '<td>';
print "<select name='anz_kinder' size=1>";
$j=0;
$anz_kinder=1 if($anz_kinder eq '');
while ($j <= $#kinder) {
  my $jh=$j+1;
  print "<option value='$jh'";
  print ' selected' if ($jh == $anz_kinder);
  print '>';
  print $kinder[$j];
  print '</option>';
  $j++;
}
print '</td>';

print '</tr>';
print '</table>';
print "\n";

# Zeile mit n�chste Hebamme
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td valign=top>';
print "<b>n�chste Hebamme&nbsp;</b>";
if (defined ($naechste_hebamme) ) {
  print '<input type="checkbox" name="naechste_hebamme" value="j" checked onClick="check_begr(this,document.stammdaten)">';
} else {
  print '<input type="checkbox" name="naechste_hebamme" value="j" onClick="check_begr(this,document.stammdaten)">';
}
print '</td>';
print '<td valign=top>';
print '<b>Begr�ndung falls nicht<br>n�chste Hebamme</b>';
print '</td>';
print '<td valgin=bottom>';
print "<textarea ";
if (!defined ($naechste_hebamme) ) {
  print ' enabled ';
} else {
  print ' disabled ';
}
print "name='nicht_naechste_heb' rows='2' cols='50'>$begruendung_nicht_nae_heb</textarea>";
print '</td>';


print '</table>';
print "\n";

# leere Zeile
print '<tr><td>&nbsp;</td></tr>';
print "\n";

# Zeile mit Kn�pfen f�r unterschiedliche Funktionen
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td>';
print "<select name='auswahl' size=1 onChange='auswahl_wechsel(document.stammdaten)'>";
my $i=0;
while ($i <= $#aus) {
  print "<option value='$aus[$i]'";
  print ' selected' if ($aus[$i] eq $auswahl);
  print '>';
  print $aus[$i];
  print '</option>';
  $i++;
}
print '</select>';
print '</td>';
print '<td>';
print '<input type="button" name="reset" value="Inhalt l�schen"';
print ' onClick="loeschen()">';
print '</td>';
print '<td><input type="submit" name="abschicken" value="Speichern"</td>';
print '<td><input type="button" name="vorheriger" value="vorheriger Datensatz" onclick="prev_satz(document.stammdaten)"></td>';
print '<td><input type="button" name="naechster" value="n�chster Datensatz" onclick="next_satz(document.stammdaten)"></td>';
print '<td><input type="button" name="hauptmenue" value="Hauptmenue" onClick="haupt();"></td>';
print '<td><input type="button" name="rechnung" value="Rechnungsposten erfassen" onclick="rechnung_erfassen(document.stammdaten)"></td>';
# n�chste Zeile
print '<tr><td>&nbsp;</td>';
print '<td><input type="button" name="drucken" value="Rechnung gen" onclick="druck(document.stammdaten)"></td>';
print '</tr>';

print '</tr>';
print '</table>';
print '</form>';
print '</tr>';
print '</table>';
print <<SCRIPTE;
<script>
  set_focus(document.stammdaten);
  auswahl_wechsel(document.stammdaten);
</script>
SCRIPTE
print "</body>";
print "</html>";

sub print_color {
  my ($bezeichnung,$variable) = @_;
  
  print '<font color=red>' if ($variable eq '');
  print "<b>$bezeichnung</b>";
  print '<font color=black>';
}


sub speichern {
  # Speichert die Daten in der Stammdaten Datenbank
  # print "Speichern in DB\n";
  # Dat�mer konvertierten
  my $geb_f = $d->convert($geb_frau);
  my $geb_k = $d->convert($geb_kind);
  my $ent_sp = $entfernung;
  $ent_sp =~ s/,/\./g;
  my $plz_sp = $plz; $plz_sp = 0 if (!defined($plz_sp) or $plz_sp eq '');
  my $ik_sp = $ik_krankenkasse; $ik_sp = 0 if (!defined($ik_sp) or $ik_sp eq '');
  $geb_k = '0000-00-00' if(!defined($geb_k) or $geb_k eq 'error');
  $geb_f = '0000-00-00' if(!defined($geb_f) or $geb_f eq 'error');
  $ent_sp = 0 if(!defined($ent_sp) or $ent_sp eq '');
  
  # jetzt speichern
  my $erg = $s->stammdaten_ins($vorname,$nachname,$geb_f,$strasse,$plz_sp,$ort,$tel,
			       $ent_sp,$kv_nummer,$kv_gueltig,$versichertenstatus,
			       $ik_sp,$anz_kinder,$geb_k,$naechste_hebamme,
			       $begruendung_nicht_nae_heb,$TODAY);
  return $erg;
}

sub loeschen {
  # l�scht Datensatz aus der Stammdaten Datenbank
  if ($l->leistungsdaten_such($frau_id)) {
    $hint = "L�schen nicht m�glich, es sind schon Leistungen erfasst";
    return;
  }
  my $erg = $s->stammdaten_delete($frau_id);
  return $erg;
}

sub aendern {
  # �ndert die Daten zur angegebenen Frau in der Datenbank
  # print "Aendern $frau_id";
  # Dat�mer konvertierten
  my $geb_f = $d->convert($geb_frau);
  my $geb_k = $d->convert($geb_kind);
  my $ent_sp = $entfernung;
  $ent_sp =~ s/,/\./g;
  my $plz_sp = $plz; $plz_sp = 0 if (!defined($plz_sp) or $plz_sp eq '');
  my $ik_sp = $ik_krankenkasse; $ik_sp = 0 if (!defined($ik_sp) or $ik_sp eq '');
  $geb_k = '0000-00-00' if(!defined($geb_k) or $geb_k eq 'error');
  $geb_f = '0000-00-00' if(!defined($geb_f) or $geb_f eq 'error');
  $ent_sp = 0 if(!defined($ent_sp) or $ent_sp eq '');

  # jetzt speichern
  my $erg = $s->stammdaten_update($vorname,$nachname,$geb_f,$strasse,$plz_sp,$ort,$tel,
			      $ent_sp,$kv_nummer,$kv_gueltig,$versichertenstatus,
			      $ik_sp,$anz_kinder,$geb_k,$naechste_hebamme,
			      $begruendung_nicht_nae_heb,$TODAY,$frau_id);
  return $erg;
}

sub hole_frau_daten {
  my $frau_id_alt = $frau_id;
  $frau_id = $s->stammdaten_next_id($frau_id) if ($func==1);
  $frau_id = $s->stammdaten_prev_id($frau_id) if ($func==2);
  $frau_id = $frau_id_alt if (!defined($frau_id));
  ($vorname,$nachname,$geb_frau,$geb_kind,$plz,$ort,$tel,$strasse,
   $anz_kinder,$entfernung,$kv_nummer,$kv_gueltig,$versichertenstatus,
   $ik_krankenkasse,$naechste_hebamme,
   $begruendung_nicht_nae_heb) = $s->stammdaten_frau_id($frau_id);
  $frau_id2=$frau_id;
  $entfernung = '0.0' unless defined($entfernung);
  $entfernung =~ s/\./,/g;
  $geb_frau = '' if ($geb_frau eq '00.00.0000');
  $geb_kind = '' if ($geb_kind eq '00.00.0000');
  $plz = sprintf "%5.5u",$plz if ($plz ne '' && $plz > 0);
  $plz = '' if ($plz eq '' || $plz == 0);
  $ik_krankenkasse='' if (!defined($ik_krankenkasse) || $ik_krankenkasse eq '' || $ik_krankenkasse == 0);
  
  return;
}
