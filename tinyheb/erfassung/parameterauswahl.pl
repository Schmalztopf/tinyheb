#!/usr/bin/perl -wT
#-w
#-d:ptkdb
#-d:DProf  

# author: Thomas Baum
# 10.07.2007
# Auswahl von Parametern

use strict;
use CGI;
use Date::Calc qw(Today);

use lib "../";
use Heb;


my $q = new CGI;
my $h = new Heb;

my $pname = $q->param('pname') || '';
my $pvalue = $q->param('pvalue') || '';
my $pbeschreibung = $q->param('pbeschreibung') || '';

my $suchen = $q->param('suchen');
my $abschicken = $q->param('abschicken');

print $q->header ( -type => "text/html", -expires => "-1d");

# Alle Felder zur Eingabe ausgeben
print '<head>';
print '<title>Parameter suchen</title>';
print '</head>';
print '<body bgcolor=white>';
print '<div align="center">';
print '<h1>Parameter suchen</h1>';
print '<hr width="100%">';
print '</div><br>';
# Formular ausgeben
print '<form name="parameter_suchen" action="parameterauswahl.pl" method="get" target=_self>';
print '<h3>Suchkriterien:</h3>';
print '<table border="0" width="500" align="left">';

# Name, Wert, Beschreibung als Suchkriterien vorgeben
# z1 s1
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print "<td><b>Name</b></td>\n";
print "<td><b>Beschreibung</b></td>\n";
print "<td><b>Wert</b></td>\n";

print '</tr>';
print "\n";

print '<tr>';
print "<td><input type='text' name='pname' value='$pname' size='20'></td>";
print "<td><input type='text' name='pbeschreibung' value='$pbeschreibung' size='30'></td>";
print "<td><input type='text' name='pvalue' value='$pvalue' size='20'></td>";

print '</table>';
print "\n";

# Zeile mit Kn�pfen f�r unterschiedliche Funktionen
print '<tr>';
print '<td>';
print '<table border="0" align="left">';
print '<tr>';
print '<td><input type="submit" name="suchen" value="Suchen"></td>';
print '<td><input type="button" name="zurueck" value="Zur�ck" onClick="self.close()"></td>';
print '</tr>';
print '</table>';

# Pr�fen, ob gesucht werden soll
if (defined($suchen)) {
  # alle Feiertage ausgeben, die den Kriterien entsprechen
  print '<tr>';
  print '<td>';
  print '<table border="1" align="left">';
  print '<tr>';
  print "<td><b>Name</b></td>\n";
  print "<td><b>Beschreibung</b></td>\n";
  print "<td><b>Wert</b></td>\n";

  print '</tr>';
  $pname = '%'.$pname.'%';
  $pbeschreibung = $pbeschreibung.'%';
  $pvalue = '%'.$pvalue.'%';

  $h->parm_such_werte($pname,$pvalue,$pbeschreibung);
  while (my ($id,$p_name,$p_wert,$p_beschreibung) = $h->parm_such_werte_next) {
    print '<tr>';
    print "<td>$p_name</td>";
    print "<td>$p_beschreibung</td>";
    print "<td>$p_wert</td>";
    print '<td><input type="button" name="waehlen" value="Ausw�hlen"';
    print "onclick=\"p_eintrag('$id','$p_name','$p_wert','$p_beschreibung');self.close()\"></td>";
    print "</tr>\n";
  }
}
print '</form>';
print '</tr>';
print '</table>';

print <<SCRIPTE;
<script>
  function p_eintrag(parm_id,name,wert,beschreibung) {
    // in Parent Dokument �bernehmen
    var formular=opener.window.document.forms[0];
    formular.id.value=parm_id;
    formular.id2.value=parm_id;
    formular.pname.value=name;
    formular.pvalue.value=wert;
    formular.pbeschreibung.value=beschreibung;
  }
</script>
SCRIPTE
print "</body>";
print "</html>";
