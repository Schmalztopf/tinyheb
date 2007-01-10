#!/usr/bin/perl -w


# Mini Setup f�r tinyHeb
# Copyright (C) 2007 Thomas Baum <thomas.baum@arcor.de>
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


use File::Copy;

print "Setup f�r tinyHeb Copyright (C) 2007 Thomas Baum\n";
print "Version of this setup programm 0.0.1 (alpha)\n";
print "The tinyHeb setup programm comes with ABSOLUTELY NO WARRANTY;\nfor details see the file gpl.txt\n";
print "This is free software, and you are welcome to redistribute it\n";
print "under certain conditions; for details see the file gpl.txt\n\n";


print "Es wird zun�chst gepr�ft, ob alle Komponenten vorhanden sind\n";

print "Pr�fe auf Apache\n";
my $pfad="/Programme/Apache Group/Apache2/bin/Apache.exe";
print "$pfad \t";
if (-e $pfad) {
  print "ist vorhanden\n";
} else {
  print "nicht vorhanden,\nBitte zunaechst den Apache Webserver Installieren,\nbevor dieses Setup Programm erneut gestartet werden kann\n";
  exit(1);
}

print "Pr�fe auf MySQL\n";
$pfad="/Programme/MySQL/MySQL Server 5.0/bin/mysql.exe";
print "$pfad \t";
if (-e $pfad) {
  print "ist vorhanden\n";
} else {
  print "nicht vorhanden,\nBitte zunaechst den MySQL Server Installieren,\nbevor dieses Setup Programm erneut gestartet werden kann\n";
  exit(1);
}

print "Pr�fe auf OpenSSL\n";
$pfad="/OpenSSL/bin/openssl.exe";
print "$pfad \t";
if (-e $pfad) {
  print "ist vorhanden\n";
} else {
  print "nicht vorhanden,\nBitte zunaechst OpenSSL Installieren,\nbevor dieses Setup Programm erneut gestartet werden kann\n";
  exit(1);
}


print "Pr�fe auf Ghostscript (Version 8.15)\n";
$pfad="/gs/gs8.15/bin/gswin32c.exe";
print "$pfad \t";
if (-e $pfad) {
  print "ist vorhanden\n";
} else {
  print "nicht vorhanden,\nBitte zunaechst Ghostscript Version 8.15 Installieren,\nbevor dieses Setup Programm erneut gestartet werden kann\n";
  exit(1);
}


print "Bis jetzt sieht alles gut aus\n";

print "Es wird jetzt versucht die fehlenden Perl Pakete aus dem Internet zu laden\n";
my $eingabe=0;
while ($eingabe < 1 or $eingabe > 3 or $eingabe !~ /\d{1}/) {
  print "Welches Betriebssystem wird genutzt?\n";
  print "(1) Win98\n";
  print "(2) WinXP\n";
  print "(3) anderes Windows System\n";
  print "Eingabe :";
  $eingabe=<STDIN>;
}
if ($eingabe == 1) {
  print "Du benutzt Win98, der perl Paketmanager ist vermutlich kaputt\n";
  print "Soll ich den Paketmanager neu generieren (ja/nein) [ja]? ";
  $eingabe =<STDIN>;
  chomp $eingabe;
  if (uc $eingabe eq 'NEIN') {
    print "ich verstehe, das ist nicht gew�nscht\n";
  } else {
    print "ich generiere den Paketmanager neu:\n";
    unlink ("/Perl/bin/ppm.bat");
    my $erg=system ("/Perl/bin/pl2bat /Perl/bin/ppm");
    if ($erg > 0) {
      print "Es ist ein unbekannter Fehler aufgetreten, ggf. T. Baum benachrichtigen\nUnd Hardcopy der Bildschirmausgabe mitschicken\n";
      exit(1);
    }
    print "Der Paketmanager wurde neu generiert\n";
  }
}

print "Bitte Verbindung zum Internet aufbauen und die ENTER Taste dr�cken\n";
$eingabe=<STDIN>;

system('ppm install Date-Calc');
system('ppm install dbi');
system('ppm install DBD-mysql');
system('ppm install PostScript-Simple');
system('ppm install Mail-Sender');

print "\nDie fehlenden Pakete sind jetzt initialisiert\n";
print "Die Verbindung zum Internet wird nicht mehr benoetigt\n\n";


print "Soll ich die httpd.conf f�r den Webserver kopieren (ja/nein) [ja]";
$eingabe = <STDIN>;
chomp $eingabe;
if ($eingabe =~ /ja/i || $eingabe eq '') {
  copy("httpd.conf","/Programme/Apache Group/Apache2/conf/httpd.conf") or die "konnte httpd.conf nicht kopieren $!\n";
  print "Habe die httpd.conf kopiert\n";
}

print "\nSoll ich die my.ini f�r den MySQL Server kopieren (ja/nein) [ja]";
$eingabe = <STDIN>;
chomp $eingabe;
if ($eingabe =~ /ja/i || $eingabe eq '') {
  copy("my.ini","/Programme/MySQL/my.ini") or die "konnte my.ini nicht kopieren $!\n";
  print "Habe die my.ini kopiert\n";
}

print "\n\nJetzt muss ein Neustart des Rechners ausgefuehrt werden, damit\n";
print "die Aenderungen an der Konfiguration des Webservers und des\n";
print "MySQL Servers (Datenbank) wirksam werden\n\n";

print "danach muss Du noch in das Verzeichnis DATA wechseln und\n";
print "folgenden Befehl in der Kommandozeile ausf�hren:\n";
print "mysql -u root < init.sql\n";
print "ODER falls Du bei der MySQL Installation ein Passwort f�r\n den Datenbankadmin angegeben hast:\n";
print "mysql -u root -p < init.sql\n\n";

print "Danach kann tinyHeb in Deinem Browser unter dem Link\nhttp://localhost/tinyheb/hebamme.html aufgerufen werden\n";
