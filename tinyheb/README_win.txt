<< 0. Was ist tinyHeb >>

tinyHeb ist eine Webapplikation mit der Hebammen die notwendigen Abrechnungen
gegen�ber den gesetzlichen Krankenkassen durchf�hren k�nnen.
tinyHeb kann sowohl Papier, wie auch elektronische Rechnungen produzieren.
Die gesetzlichen Anforderungen nach �301a, resp. �302 SGB V werden eingehalten.

<< 1. Installation >>
In diesem Abschnitt ist die Installation f�r ein Win* System beschrieben,
Linux Nutzer lesen bitte die Installationshinweise in der Datei README

<< Schritt 1 >>
Um tinyHeb unter Windows benutzen zu k�nnen m�ssen zun�chst verschiedene 
Programme aus dem Internet heruntergeladen werden. Der Download aus dem
Internet sollte nur erfolgen, wenn man �ber einen DSL Anschluss verf�gt,
oder sehr viel Zeit hat, da ca. 30MB heruntergeladen werden m�ssen.
Man sollte sich die Stelle merken an der man die Programme gespeichert hat,
f�r die sp�tere Installation.

Im einzelnen sind dies folgende Programme:

a. ActiveState perl Version >= 5.8.8.817 und zwar MSI
	http://www.activestate.com/Products/Download/Download.plex?id=ActivePerl

b. Apache Webserver Version 2.0.x
	http://www.apache.de/dist/httpd/binaries/win32/apache_2.0.59-win32-x86-no_ssl.msi

c. MySQL Datenbank Version 5.0.x
	z.B. von
	http://www.mysql.org/get/Downloads/MySQL-5.0/mysql-essential-5.0.22-win32.msi/from/http://ftp.gwdg.de/pub/misc/mysql/

d. OpenSSL
	http://www.slproweb.com/download/Win32OpenSSL-v0.9.8b.exe oder
	http://www.slproweb.com/products/Win32OpenSSL.html

e. Ghostscript Version 8.15
	http://prdownloads.sourceforge.net/ghostscript/gs815w32.exe

f. Webbrowser Firefox
	dieser ist nicht unbedingt notwendig, aber doch um einiges besser als der IE
	http://www.mozilla.com/firefox/

g. Acrobat Reader
	der Acrobat Reader sollte eigentlich vorhanden sein, wenn nicht hier herunterladen:
	http://www.adobe.com/products/acrobat/readstep2.html

<< Schritt 2 >>

Nachdem die einzelnen Programme heruntergeladen wurden, m�ssen diese noch installiert werden

a. ActiveState Perl Installieren
	durch Doppelklick auf die Datei ActivePerl-5.8.8.817-MSWin32-x86-257965.msi
	(ggf. hat die Datei einen etwas anderen Namen)
	startet die Installation
	- Lizenz akzeptieren und alle Standardwerte �bernehmen

b. Apache Webserver Installieren
	durch Doppelklick auf die Datei apache_2.0.58-win32-x86-no_ssl.msi
        (ggf. hat die Datei einen etwas anderen Namen)
        startet die Installation
	- Lizenz akzeptieren und alle Standardwerte �bernehmen
	- Bei Network domain nur die eigene E-Mail Adresse angeben, den Rest leer lassen und Next klicken
	- Typical Installation w�hlen
	- Ort der Installation ohne �nderung �bernehmen
	- Neustart des Systems ist nicht notwendig, da noch weitere Programme installiert werden m�ssen

c. MySQL Datenbankserver Installieren
	durch Doppelklick auf die Datei mysql-essential-5.0.18-win32.msi
        (ggf. hat die Datei einen etwas anderen Namen)
        startet die Installation
	- Typical Installation w�hlen
	- alle anderen Werte �bernehmen und Install klicken
	- Finish klicken und den Haken bei Configure Now gesetzt lassen,
	  dadurch startet sofort die Konfiguration von mysql
	- Standard Configuration anw�hlen und Next klicken.
	- Include Bin Directory in Windows PATH anw�hlen und Next klicken.
	- Finish klicken

d. OpenSSL Verschl�sselungssoftware Installieren
	durch Doppelklick auf die Datei Win32OpenSSL-v0.9.8a.exe
        (ggf. hat die Datei einen etwas anderen Namen)
        startet die Installation
	- Lizenz akzeptieren und alle anderen Werte �bernehmen

e. Ghostscript Installieren
	durch Doppelklick auf die Datei GS815W32.EXE
	startet die Installation
	- alle Vorgaben akzeptieren und Install dr�cken

f. Firefox Installieren (nur bei Bedarf)
	durch Doppelklick auf die Datei Firefox Setup 1_5_0_2.exe
        (ggf. hat die Datei einen etwas anderen Namen)
        startet die Installation
	- Lizenz akzeptieren und alle anderen Werte �bernehmen

g. Acrobat Reader Installieren (nur bei Bedarf)
	durch Doppelklick auf die Datei RP505DEU.EXE
	(diese Datei sollte einen anderen Namen haben)
	startet die Installation
	- Lizenz akzeptieren und alle anderen Werte �bernehmen

<< Schritt 3 >>
	Computer Neu starten

Wenn es eine Fortschrittsanzeige geben w�rden, w�re diese jetzt bei 70%	

<< Schritt 4 >>
	Die Archiv Datei von tinyHeb entpacken
	- Doppelklick auf die Datei tinyheb_win.0.7.1.tar.gz
	(ggf. hat die Datei einen anderen Namen)
	startet in der Regel Winzip
	- Auf die Frage: Das Archiv enth�lt eine Datei tinyheb_win.0.7.1.tar
			Soll WinZip die Datei in einem tempor�ren Ordner extrahieren und �ffen
	mit Ja Antworten
	- danach auf Extrahieren klicken.
	- Extrahieren nach: c:\Programme\Apache Group\Apache2\cgi-bin
	  Pfadangaben verwenden,
	  Alle Dateien/ Order im Archiv

Wenn es eine Fortschrittsanzeige geben w�rden, w�re diese jetzt bei 90%

<< Schritt 5 >>
	ACHTUNG:
	wenn Sie den Apache Webserver oder den MySQL Server schon vorher installiert
	hatten, sind Sie quasi fertig mit der Installation und sollten die 
	n�chsten in Schritt 5 aufgef�hrten Schritte nicht durchf�hren, da
	die Konfiguration des Webservers und des MySQL Servers �berschrieben w�rden.

	Das Verzeichnis win32 und die Datei setup.bat existieren noch nicht,
	da noch zu viele Fehler enthalten sind.
	Bitte schickt mir eine Mail, ich schicke dann noch Hinweise und
	einige fehlende Dateien. Selber probieren geht u.u. auch, dauert
	aber wahrscheinlich 10x solange, wie eine Mail an mich.
	
	�berpr�fen Sie daher den Inhalt der Datei setup.bat und f�hren die 
	einzelnen Schritte ggf. manuell durch.
	
	- jetzt m�ssen noch einige Dateien aus dem Internet geladen werden,
	  dazu bitte eine Verbindung mit dem Internet gerstellen
	- In das Verzeichnis c:\Programme\Apache Group\Apache2\cgi-bin\tinyheb\win32
	  wechseln
	- Doppelklick auf setup.bat

Wenn es eine Fortschrittsanzeige geben w�rden, w�re diese jetzt bei 100%

fast fertig:
im Browser kann jetzt �ber http://localhost/tinyheb/hebamme.html
tinyHeb gestart werden.
um elektronische Rechnungen zu verschicken existiert das Programm xauftrag.pl im Verzeichnis edifact.

<< Schritt 6 >>
        Jetzt ist es noch notwendig die Parameter wie in Kapitel 2.4 des Handbuches beschrieben
        anzupassen, damit z.B. der wirkliche Name der Hebamme auf der Rechnung erscheint.
	

<< 2. �nderungshistorie >>
Die �nderungshistorie befindet sich in der Datei RelNotes.txt

<< 3. ben�tigte perl Module zur Laufzeit >>
DBI,
DBD,
CGI,
Date::Calc
File::stat,
MIME::QuotedPrint,
Tk,
Mail::Sender,
PostScript::Simple

<< 4. zus�tzliche Programme >>
OpenSSL muss installiert sein, wenn man verschl�sselte Rechnungen erzeugen m�chte

<< 5. Fragen >>
wenn es Probleme bei der Installation geben sollte, schreibt mir einfach eine Mail:
thomas.baum@arcor.de

