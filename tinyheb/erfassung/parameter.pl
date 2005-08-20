#!/usr/bin/perl -wT
#-wT
#-d:ptkdb
#-d:DProf  

# author: Thomas Baum
# 02.07.2005
# Parameter erfassen, �ndern, l�schen

use strict;
use CGI;
use Date::Calc qw(Today);

use lib "../";
use Heb_datum;
use Heb;

my $q = new CGI;
my $d = new Heb_datum;
my $h = new Heb;

my $debug=1;

my $TODAY = sprintf "%4.4u-%2.2u-%2.2u",Today();
my @aus = ('Anzeigen','�ndern','Neu','L�schen');

my $hint='';

my $id =$q->param('id') || 0;
my $id2 =$q->param('id2') || 0;
my $pname = $q->param('pname') || '';
my $pvalue = $q->param('pvalue') || '';
my $pbeschreibung = $q->param('pbeschreibung') || '';

my $speichern = $q->param('Speichern');

my $auswahl = $q->param('auswahl') || 'Anzeigen';
my $abschicken = $q->param('abschicken');
my $func = $q->param('func') || 0;

hole_parm_daten() if ($func == 1 || $func == 2);
if (($auswahl eq '�ndern') && defined($abschicken)) {
  aendern();
  $auswahl = 'Anzeigen';
  $id=$id2;
}

if (($auswahl eq 'Neu') && defined($abschicken)) {
  $id=speichern();
  $auswahl = 'Anzeigen';
}

print $q->header ( -type => "text/html", -expires => "-1d");


print '<head>';
print '<title>Parameter</title>';
print '<script language="javascript" src="../Heb.js"></script>';
print '<script language="javascript" src="parameter.js"></script>';
print '</head>';

# style-sheet ausgeben
print <<STYLE;
  <style type="text/css">
  .disabled { color:black; background-color:gainsboro}
  .invisible { color:white; background-color:white;border-style:none}
  </style>
STYLE

if (($auswahl eq 'L�schen') && defined($abschicken)) {
  loeschen();
  if ($hint eq '') {
    print '<script>open("parameter.pl","_top");</script>';
  } else {
    print "<script>alert('$hint');</script>";
    $auswahl='Anzeigen';
  }
}

# Alle Felder zur Eingabe ausgeben
print '<body bgcolor=white>';
print '<div align="center">';
print '<h1>Parameter $Revision: 1.2 $ <br>$Date: 2005-08-20 16:04:40 $</h1>';
print '<hr width="100%">';
print '</div><br>';
# Formular ausgeben
print '<form name="parameter" action="parameter.pl" method="get" target=_top bgcolor=white>';
print '<table border="0" width="700" align="left">';

# Zeile ID, Name
print '<tr><td><table border="0" align="left">';
print '<tr>';
print '<td><b>ID</b></td>';
print '<td><b>Name</b></td>';
print '</tr>';
print "\n";

# ID noch mal Hidden ausgeben
print "<td><input type='hidden' name='id2' value='$id' size='4'></td>";

print '<tr>';
print "<td><input type='text' disabled class='disabled' name='id' value='$id' size='4'></td>";
print "<td><input type='text' name='pname' value='$pname' size='20'></td>";
print "<td><input type='button' name='parm_suchen' value='Suchen' onClick='parmsuchen(pname,pvalue,pbeschreibung);'></tr>";
print '</table>';
print "\n";

# Wert des Parameters erfassen
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td><b>Wert</b></td>';
print '</tr>';
print '<tr>';
print "<td><input type='text' name='pvalue' value='$pvalue' size='80'></td>";
print '</tr>';
print '</table>';
print "\n";

# Beschreibung des Parameters erfassen
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td><b>Beschreibung</b></td>';
print '</tr>';
print '<tr>';
print "<td><input type='text' name='pbeschreibung' value='$pbeschreibung' size='80'></td>";
print '</tr>';
print '</table>';
print "\n";


# Zeile mit Kn�pfen f�r unterschiedliche Funktionen
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td>';
print "<select name='auswahl' size=1 onChange='auswahl_wechsel(document.parameter)'>";
my $i=0;
while ($i <= $#aus) {
  print '<option';
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
print '<td>';
print '<input type="submit" name="abschicken" value="Speichern"';
print '</td>';
print '<td>';
print '<input type="button" name="vorheriger" value="vorheriger Datensatz" onclick="prev_satz_parms(document.parameter)"';
print '</td>';
print '<td>';
print '<input type="button" name="naechster" value="n�chster Datensatz" onclick="next_satz_parms(document.parameter)"';
print '</td>';
print '<td><input type="button" name="hauptmenue" value="Hauptmenue" onClick="haupt();"></td>';
print '</tr>';
print '</table>';
print '</form>';
print '</tr>';
print '</table>';
print <<SCRIPTE;
<script>
  auswahl_wechsel(document.parameter);
</script>
SCRIPTE
print "</body>";
print "</html>";

sub speichern {
  # Speichert neuen Parameter in der Parms Tabelle
  my $erg = $h->parm_ins($pname,$pvalue,$pbeschreibung);
  return $erg;
}

sub loeschen {
  # l�scht Datensatz aus der Parms Tabelle
  if ($id2 == 0) {
    $hint = 'Dieser Datensatz kann nicht gel�scht werden';
    return;
  }
  my $erg = $h->parm_delete($id2);
  return $erg;
}

sub aendern {
  # �ndert Datensatz in der Parms Tabelle 
  my $erg = $h->parm_update($id2,$pname,$pvalue,$pbeschreibung);
  return $erg;
}



sub hole_parm_daten {
  my $id_alt=$id;
  $id = $h->parm_next_id($id) if ($func==1);
  $id = $h->parm_prev_id($id) if ($func==2);
  $id=$id_alt if (!defined($id));
  ($id,$pname,$pvalue,$pbeschreibung)= $h->parm_get_id($id);
  return;

}
