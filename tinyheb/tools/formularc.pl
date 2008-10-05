#!/usr/bin/perl -w
# -d:ptkdb
# -wT

# Erzeugen Formular Versichertenbest�tigung C und Druckoutput (Postscript)

# $Id: formularc.pl,v 1.1 2008-10-05 13:50:55 thomas_baum Exp $
# Tag $Name: not supported by cvs2svn $

# Copyright (C) 2008 Thomas Baum <thomas.baum@arcor.de>
# Thomas Baum, 42719 Solingen, Germany

# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.

use lib "../";
#use Devel::Cover -silent => 'On';

use PostScript::Simple;
use Date::Calc qw(Today);
use strict;
use CGI;
use CGI::Carp qw(fatalsToBrowser);

use Heb;
use Heb_stammdaten;
use Heb_datum;

my $s = new Heb_stammdaten;
my $d = new Heb_datum;
my $h = new Heb;

my $q = new CGI;

my $frau_id = $q->param('frau_id') || -1;
my $seite=1;
my $datum = $d->convert_tmj(sprintf "%4.4u-%2.2u-%2.2u",Today());

# zun�chst daten der Frau holen
my ($vorname,$nachname,$geb_frau,$geb_kind,$plz,$ort,$tel,$strasse,
    $anz_kinder,$entfernung_frau,$kv_nummer,$kv_gueltig,$versichertenstatus,
    $ik_krankenkasse,$naechste_hebamme,
    $begruendung_nicht_nae_heb,
    $kzetgt,$uhr_kind,$privat_faktor) = $s->stammdaten_frau_id($frau_id);

$entfernung_frau =~ s/\./,/g;
$plz = $plz ? sprintf "%5.5u",$plz : '';


my $font ="Helvetica-iso";
my $font_b = "Helvetica-Bold-iso";
my $y_font = 0.410;

my $p = new PostScript::Simple(papersize => "A4",
#			       color => 1,
			       eps => 0,
			       landscape => 1,
			       units => "cm",
			       reencode => "ISOLatin1Encoding");

my $x1=1;
my $x2=29;
my $y1=0;

$p->newpage;

# als erstes gro�e Rahmenkiste
$p->box($x1,19.0,$x2,1.5);

# �berschrift
$p->setfont($font_b,12);
$p->text($x1,19.1,'Versichertenbest�tigung C: Hebammenhilfe, Abrechnung �ber ein IK');

# Fussnote
$p->setfont($font,7);
$p->text($x1,1.2,'Zutreffende Leistungen bitte ankreuzen A/E = Zeitangabe von/bis  Z = Zeitpunkt der Geburt  E = Zeitpunkt der Beendigung der Leistung  A = Zeitpunkt des Beginns der Leistung  sonst: keine Zeitangabe erforderlich');

# Horizontale Querlinien
my $y2=14.1;
$p->setlinewidth(0.05);
$p->line($x1,15,$x2,15);
$p->setfont($font_b,8);
$p->text($x1+0.1,14.4,'Geplanter Geburtsort zum Zeitpunkt des 2. Vorgespr�ches:');
$p->line($x1,$y2,$x2,$y2);

# dicke Linie unter Datum Zeit
$p->line($x1,10.8,$x2,10.8);

my $x3=20.25;
$p->setlinewidth(0.03);

# noch 2 Kisten f�r Hausgeburt und Geburtshaus
$p->box($x1+8.5,14.7,$x1+8.9,14.3);
$p->setfont($font,8);
$p->text($x1+9.1,14.4,'Hausgeburt');

$p->box($x1+8.5+3,14.7,$x1+8.9+3,14.3);
$p->setfont($font,8);
$p->text($x1+9.1+3,14.4,'Geburtshaus, Hebammenpraxis, Entbindungsheim');


$p->line($x1,12.3,$x2,12.3);
$p->setfont($font,10);
$p->text({align => 'right'},6.3,12.4,'Leistungen');

$p->line($x1,11.4,$x3,11.4);
$p->setfont($font,10);
$p->text({align => 'right'},6.3,11.5,'Leistungs-Nr.');

# jetzt d�nne Linien f�r die einzelnen Best�tigungen
$p->setlinewidth(0.02);
$y1=10.3;
while ($y1 > 1.8) {
  $p->line($x1,$y1,$x2,$y1);
  $y1-=0.55;
}

# senkrechte Querlinien
$p->line(12.2,19.0,12.2,15.0);
$p->setfont($font_b,8);
my $y4=18.5;
$p->text(12.2+0.1,$y4,'Hebamme: ');
$y4-=$y_font;
$p->setfont($font,8);
$p->text(12.2+0.1,$y4,$h->parm_unique('HEB_VORNAME').' '.$h->parm_unique('HEB_NACHNAME'));
$y4-=$y_font;
$p->text(12.2+0.1,$y4,$h->parm_unique('HEB_STRASSE'));
$y4-=$y_font;
$p->text(12.2+0.1,$y4,$h->parm_unique('HEB_PLZ').' '.$h->parm_unique('HEB_ORT'));
$y4-=$y_font;
$p->text(12.2+0.1,$y4,$h->parm_unique('HEB_TEL'));

$y4-=(2*$y_font);
$p->text(12.2+0.1,$y4,'IK-Nummer der Hebamme: '.$h->parm_unique('HEB_IK'));
$y4-=$y_font;
$p->text(12.2+0.1,$y4,'IK-Nummer der Klinik:'.$h->parm_unique('HEB_IK_BELEG_KKH')) if ($h->parm_unique('HEB_IK_BELEG_KKH'));

# Querlinie vor Angaben der Versicherten
$p->line(20,19.0,20,15.0);
$y4=18.5;
$p->setfont($font_b,8);
$p->text(20.2+0.1,$y4,'Angaben zur Versicherten (Mitglied)');
$y4-=$y_font;
$p->setfont($font,8);
$p->text(20.2+0.1,$y4,'Name: '.$nachname.", ".$vorname) if($vorname && $nachname);
$p->text(20.2+0.1,$y4,'Name: ') unless($vorname && $nachname);
$y4-=$y_font;
$p->text(20.2+0.1,$y4,"Mitgl-Nr.: $kv_nummer");
$y4-=$y_font;
$p->text(20.2+0.1,$y4,"V-Status: $versichertenstatus");
$y4-=$y_font;
my ($m,$j)=unpack("A2A2",$kv_gueltig);
$p->text(20.2+0.1,$y4,"g�ltig bis: $m/$j") if ($kv_gueltig);
$p->text(20.2+0.1,$y4,"g�ltig bis:") unless ($kv_gueltig);

$p->setlinewidth(0.05);
$p->line(6.4,$y2,6.4,1.5); # erste Linie dick

$p->setlinewidth(0.02);
$x1=7.05;
$p->setfont($font,6);
$p->text({align => 'center'},$x1-0.25,10.9,'A/E');
$p->text({rotate => 90},$x1-0.25,11.45,'020');
$p->text({rotate => 90},$x1-0.25,12.4,'Vorgespr�ch');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({rotate => 90},$x1-0.25,11.45,'030');
$p->text({rotate => 90},$x1-0.25,12.4,'Vorsorge');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({rotate => 90},$x1-0.15,11.45,'040');
$p->text({rotate => 90},$x1-0.4,12.4,'Entnahme von');
$p->text({rotate => 90},$x1-0.1,12.4,'K�rpermaterial');


$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'A/E');
$p->text({rotate => 90},$x1-0.25,11.45,'050 /051');
$p->text({rotate => 90},$x1-0.25,12.4,'Hilfeleistung');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'A/E');
$p->text({rotate => 90},$x1-0.25,11.45,'060');
$p->text({rotate => 90},$x1-0.25,12.4,'CTG');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'A/E');
$p->text({rotate => 90},$x1-0.25,11.45,'080');
$p->text({rotate => 90},$x1-0.4,12.4,'Einzelgeburts-');
$p->text({rotate => 90},$x1-0.1,12.4,'vorbereitung');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'Z');
$p->text({rotate => 90},$x1-0.25,11.45,'090 /091');
$p->text({rotate => 90},$x1-0.4,12.4,'Hilfe bei Geburt');
$p->text({rotate => 90},$x1-0.1,12.4,'in der Klinik');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'Z');
$p->text({rotate => 90},$x1-0.4,11.45,'100 /101');
$p->text({rotate => 90},$x1-0.1,11.45,'110 /111');
$p->text({rotate => 90},$x1-0.4,12.4,'Hilfe bei Geburt');
$p->text({rotate => 90},$x1-0.1,12.4,'au�erkl. Einricht.');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'Z');
$p->text({rotate => 90},$x1-0.25,11.45,'120 /121');
$p->text({rotate => 90},$x1-0.25,12.4,'Hausgeburt');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'Z');
$p->text({rotate => 90},$x1-0.25,11.45,'130 /131');
$p->text({rotate => 90},$x1-0.4,12.4,'Hilfe bei einer');
$p->text({rotate => 90},$x1-0.1,12.4,'Fehlgeburt');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.8;
$p->text({align => 'center'},$x1-0.35,10.9,'E');
$p->text({rotate => 90},$x1-0.3,11.45,'160 /161');
$p->text({rotate => 90},$x1-0.55,12.4,'Hilfe bei einer');
$p->text({rotate => 90},$x1-0.35,12.4,'nicht vollendeten');
$p->text({rotate => 90},$x1-0.15,12.4,'Geburt in Klinik');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.8;
$p->text({align => 'center'},$x1-0.35,10.9,'E');
$p->text({rotate => 90},$x1-0.3,11.45,'162 /163');
$p->text({rotate => 90},$x1-0.55,12.4,'Hilfe bei einer');
$p->text({rotate => 90},$x1-0.35,12.4,'nicht vollendeten');
$p->text({rotate => 90},$x1-0.15,12.4,'Hausgeburt');


$p->line($x1,$y2,$x1,1.5);
$x1+=0.8;
$p->text({align => 'center'},$x1-0.35,10.9,'E');
$p->text({rotate => 90},$x1-0.4,11.45,'164 /165');
$p->text({rotate => 90},$x1-0.1,11.45,'166 /167');
$p->text({rotate => 90},$x1-0.55,12.4,'Hilfe bei nicht');
$p->text({rotate => 90},$x1-0.35,12.4,'vollendeter Geb.');
$p->text({rotate => 90},$x1-0.15,12.4,'au�erkl. Einr.');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'A/E');
$p->text({rotate => 90},$x1-0.25,11.45,'170 /171');
$p->text({rotate => 90},$x1-0.4,12.4,'Hilfe bei Geburt');
$p->text({rotate => 90},$x1-0.1,12.4,'2. Hebamme');


$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'A');
$p->text({rotate => 90},$x1-0.25,11.45,'180 /181');
$p->text({rotate => 90},$x1-0.4,12.4,'Wochenbett-');
$p->text({rotate => 90},$x1-0.1,12.4,'besuch');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.8;
$p->text({align => 'center'},$x1-0.35,10.9,'A');
$p->text({rotate => 90},$x1-0.35,11.45,'200 /201');
$p->text({rotate => 90},$x1-0.55,12.4,'Wobettbesuch in');
$p->text({rotate => 90},$x1-0.35,12.4,'Klinik o. �rztl.');
$p->text({rotate => 90},$x1-0.15,12.4,'gel. akl. Einr.');


$p->line($x1,$y2,$x1,1.5);
$x1+=0.8;
$p->text({align => 'center'},$x1-0.35,10.9,'A');
$p->text({rotate => 90},$x1-0.35,11.45,'210 /211');
$p->text({rotate => 90},$x1-0.55,12.4,'Wobettbesuch in');
$p->text({rotate => 90},$x1-0.35,12.4,'heb. geleiteter');
$p->text({rotate => 90},$x1-0.15,12.4,'au�erkl. Einr.');


$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'');
$p->text({rotate => 90},$x1-0.25,11.45,'240');
$p->text({rotate => 90},$x1-0.4,12.4,'Erstuntersuchung');
$p->text({rotate => 90},$x1-0.1,12.4,'U1');

$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'');
$p->text({rotate => 90},$x1-0.25,11.45,'250');
$p->text({rotate => 90},$x1-0.4,12.4,'Entnahme von');
$p->text({rotate => 90},$x1-0.1,12.4,'K�rpermaterial');


$p->line($x1,$y2,$x1,1.5);
$x1+=0.6;
$p->text({align => 'center'},$x1-0.25,10.9,'A/E');
$p->text({rotate => 90},$x1-0.25,11.45,'260');
$p->text({rotate => 90},$x1-0.4,12.4,'�berwachnung');
$p->text({rotate => 90},$x1-0.1,12.4,'�rztl. Anordnung');


$p->line($x1,$y2,$x1,1.5);
$x1+=0.8;
$p->text({align => 'center'},$x1-0.35,10.9,'E');
$p->text({rotate => 90},$x1-0.35,11.45,'280 /281');
$p->text({rotate => 90},$x1-0.55,12.4,'Beratung bei');
$p->text({rotate => 90},$x1-0.35,12.4,'Stillschwierig-');
$p->text({rotate => 90},$x1-0.15,12.4,'keiten');

$p->setlinewidth(0.05);
$p->line($x1,15,$x1,1.5); # letzte Linie auch dick
$p->setlinewidth(0.02);

# Texte Entbindungstermin etc. da jetzt $x1 auf richtigem Wert steht
$x1+=0.1;
$p->setfont($font_b,8);
$p->text($x1,14.4,"Entbindungstermin:");
# pr�fen ob ET oder Geburtsdatum
my $geb_kind_et=$d->convert($geb_kind);
$geb_kind_et =~ s/-//g if($geb_kind_et);
my $datum_jmt=$d->convert($datum);
$datum_jmt =~ s/-//g if($datum_jmt);
# zeilen nur ausgeben, wenn geb Kind g�ltig ist
if ($geb_kind_et && $geb_kind_et ne 'error') {
  if($datum_jmt >= $geb_kind_et && !$kzetgt || $kzetgt == 1) {
    # geburtsdatum
    $p->text($x1+6.7,14.4,"$geb_kind");
  } else {
    # errechneter Termin
    $p->text($x1+2.8,14.4,"$geb_kind");
  }
}


$p->setfont($font_b,10);
$p->text($x1,13.5,'Rechnungsnummer:');
$p->setfont($font,10);
$p->text($x1,12.8,'Bogennummer:');


$p->setfont($font_b,8);
$p->text($x1+4.5,14.4,"Tag der Geburt:");

$p->text($x1+2.5,11.5,"Unterschrift der Versicherten");

# Datum Zeit und entsprechende vertikale Linie
$x1=2.8;
$p->line($x1,11.4,$x1,1.5);

$p->text($x1-1.3,10.9,'Datum');
$p->text($x1+1.5,10.9,'Zeit');


# in Browser schreiben, falls Windows wird PDF erzeugt, sonst Postscript
my $all_rech=$p->get();
$all_rech =~ s/Portrait/Landscape/;
if ($q->user_agent !~ /Windows/) {
  print $q->header ( -type => "application/postscript", -expires => "-1d");
  $all_rech =~ s/PostScript::Simple generated page/${nachname}_${vorname}/g;
  print $all_rech;
}

if ($q->user_agent =~ /Windows/) {
  print $q->header ( -type => "application/pdf", -expires => "-1d");
  if (!(-d "/tmp/wwwrun")) {
    mkdir "/tmp" if (!(-d "/tmp"));
    mkdir "/tmp/wwwrun";
  }
  unlink('/tmp/wwwrun/file.ps');
  $p->output('/tmp/wwwrun/file.ps');

  if ($^O =~ /linux/) {
    system('ps2pdf /tmp/wwwrun/file.ps /tmp/wwwrun/file.pdf');
  } elsif ($^O =~ /MSWin32/) {
    unlink('/tmp/wwwrun/file.pdf');
    my $gswin=$h->suche_gswin32();
    system("$gswin -q -dCompatibilityLevel=1.2 -dSAFER -dNOPAUSE -dBATCH -sDEVICE=pdfwrite -sOutputFile=/tmp/wwwrun/file.pdf -c .setpdfwrite -f /tmp/wwwrun/file.ps");
  } else {
    die "kein Konvertierungsprogramm ps2pdf gefunden\n";
  }

  open AUSGABE,"/tmp/wwwrun/file.pdf" or
    die "konnte Datei nicht konvertieren in pdf\n";
  binmode AUSGABE;
  binmode STDOUT;
  while (my $zeile=<AUSGABE>) {
    print $zeile;
  }
  close AUSGABE;
}

