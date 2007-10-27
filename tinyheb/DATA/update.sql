# Updates f�r tinyHeb
#
# $Id: update.sql,v 1.12 2007-10-27 16:41:39 thomas_baum Exp $
# Tag $Name: not supported by cvs2svn $
#
# zun�chst alte GO ung�ltig machen
WWWRUN	UPDATE	Leistungsart		update Leistungsart set GUELT_BIS='2007-07-31' where GUELT_VON = '2004-07-22' and GUELT_BIS = '9999-12-31';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set GUELT_BIS='2007-07-31' where GUELT_VON = '2004-07-22' and GUELT_BIS = '9999-01-01';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set GUELT_BIS='2007-07-31' where GUELT_VON = '2005-04-19' and GUELT_BIS = '9999-12-31';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set GUELT_BIS='2007-07-31' where GUELT_VON = '2004-01-01' and GUELT_BIS = '9999-12-31';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set GUELT_BIS='2007-07-31' where GUELT_VON = '2006-01-01' and GUELT_BIS = '9999-12-31';
#
# Neue Geb�hrenordnung
#
# Leistungen der Mutterschaftsvorsorge und Schwangerenbetreuung
# Leistungsgruppe A
# 
# PosNr 010
#
WWWRUN	INSERT	Leistungsart	POSNR='010' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS) values (9999,'010','A','Beratung',5.7,'Beratung der Schwangeren, auch mittels Kommunikationsmedium','2007-08-01','9999-12-31');
#
# PosNr 020
#
WWWRUN	INSERT	Leistungsart	POSNR='020' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'020','A','Vorgespr�ch',7.2,'Vorgespr�ch �ber Fragen der Schwangerschaft und Geburt,mindestens 30 Minuten, je angefangene 15 Minuten','2007-08-01','9999-12-31',15);
#
# PosNr 030
#
WWWRUN	INSERT	Leistungsart	POSNR='030' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'030','A','Vorsorgeuntersuchung',22,'Vorsorgeuntersuchung der Schwangeren nach Ma�gabe der Richtlinien des Gemeinsamen Bundesausschusses �ber die �rztliche Betreuung w�hrend der Schwangerschaft und nach der Entbindung in der jeweils geltenden Fassung','2007-08-01','9999-12-31',0,'+340');
#
# PosNr 040
#
WWWRUN	INSERT	Leistungsart	POSNR='040' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'040','A','Entnahme von K�rpermaterial',5.6,'Entnahme von K�rpermaterial zur Durchf�hrung notwendiger Laboruntersuchungen im Rahmen der Richtlinien des Gemeinsamen Bundesausschusses �ber die �rztliche Betreuung w�hrend der Schwangerschaft und nach der Entbindung (Mutterschafts-Richtlinien) in der jeweils geltenden Fassung, je Entnahme, einschlie�lich Veranlassung der Laboruntersuchung(en), Versand- und Portokosten, Dokumentation im Mutterpass nach den Mutterschafts-Richtlinien und Befund�bermittlung','2007-08-01','9999-12-31',0,'');
#
# PosNr 050
#
WWWRUN	INSERT	Leistungsart	POSNR='050' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'050','A','Hilfe bei Beschw.',14.4,'Hilfe bei Schwangerschaftsbeschwerden oder bei Wehen, f�r jede angefangenen 30 Minuten','2007-08-01','9999-12-31',30,'+350','051','051','051',180);
#
# PosNr 051
#
WWWRUN	INSERT	Leistungsart	POSNR='051' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'051','A','Hilfe bei Beschw. Sa,So,Nacht',17.28,'Hilfe bei Schwangerschaftsbeschwerden oder bei Wehen, f�r jede angefangenen 30 Minuten, mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',30,'+350','','','',180);
#
# PosNr 060
#
WWWRUN	INSERT	Leistungsart	POSNR='060' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'060','A','CTG �berwachung',6.3,'Cardiotokografische �berwachnung bei Indikationen nach Ma�gabe der Anlage 2 zu den Richtlinien des gemeinsamen Bundesausschusses �ber die �rztliche Betreuung w�hrend der Schwangerschaft und nach der Entbindung (Mutterschafts-Richtlinien) in der jeweils geltenden Fassung einschl. Dokumentation im Mutterpass nach den Mutterschafts-Richtlinien in der jeweils geltenden Fassung.','2007-08-01','9999-12-31',0,'','','','',0);
#
# Posnr 070
#
WWWRUN	INSERT	Leistungsart	POSNR='070' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'070','A','Geburtsvorbereitung in der Gruppe',5.6,'Geburtsvorbereitung bei Unterweisung in der Gruppe, bis zu zehn Schwangere je Gruppe und h�chsten 14 Stunden, f�r jede Schwangere je Unterrichtsstunden (60 Minuten)','2007-08-01','9999-12-31','E60','','','','',0);
#
# Posnr 080
WWWRUN	INSERT	Leistungsart	POSNR='080' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,BEGRUENDUNGSPFLICHT) values (9999,'080','A','Einzelgeburtsvorbereitung',14.4,'Geburtsvorbereitung bei Einzelunterweisung auf �rztliche Anordnung h�chstens 14 Unterrichtseinheiten a 30 Minuten, f�r jede angefangenen 30 Minuten','2007-08-01','9999-12-31','30','','','','',0,'J');
#
# -------------Geburtshilfe ---------------------
#
# Leistungen Geburtshilfe
# Leistungsgruppe B
#
# PosNr 090
#
WWWRUN	INSERT	Leistungsart	POSNR='090' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'090','B','Geburt im Krankenhaus',220,'Hilfe bei der Geburt eines Kindes in einem Krankenhaus','2007-08-01','9999-12-31',0,'+360','091','091','091',0);
#
# PosNr 091
WWWRUN	INSERT	Leistungsart	POSNR='091' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'091','B','Geburt im Krankenhaus, Nacht,Sa,So',264,'Hilfe bei der Geburt eines Kindes in einem Krankenhaus mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'+360','','','',0);
#
# PosNr 100
#
WWWRUN	INSERT	Leistungsart	POSNR='100' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'100','B','Geburt au�erkl. �rztl. Leitung',220,'Hilfe bei einer au�erklinischen Geburt in einer Einrichtung unter �rztlicher Leitung','2007-08-01','9999-12-31',0,'+360','101','101','101',0);
#
# PosNr 101
#
WWWRUN	INSERT	Leistungsart	POSNR='101' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'101','B','Geburt au�erkl. �rztl. Leitung, Nacht,Sa,So',264,'Hilfe bei einer au�erklinischen Geburt in einer Einrichtung unter �rztlicher Leitung mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'+360','','','',0);
#
# PosNr 110
#
WWWRUN	INSERT	Leistungsart	POSNR='110' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'110','B','Geburt au�erkl. Leitung Hebammen',360,'Hilfe bei einer au�erklinischen Geburt in einer von Hebammen geleiteten Einrichtung','2007-08-01','9999-12-31',0,'+360','111','111','111',0);
#
# PosNr 111
#
WWWRUN	INSERT	Leistungsart	POSNR='111' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'111','B','Geburt au�erkl. Leitung Hebammen Nacht,Sa,So',432,'Hilfe bei einer au�erklinischen Geburt in einer von Hebammen geleiteten Einrichtung mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'+360','','','',0);
#
# PosNr 120
#
WWWRUN	INSERT	Leistungsart	POSNR='120' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'120','B','Hilfe bei Hausgeburt',440,'Hilfe bei Hausgeburt','2007-08-01','9999-12-31',0,'+360','121','121','121',0);
#
# PosNr 121
#
WWWRUN	INSERT	Leistungsart	POSNR='121' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'121','B','Hilfe bei Hausgeburt Nacht,Sa,So',528,'Hilfe bei Hausgeburt mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'+360','','','',0);
#
# PosNr 130
#
WWWRUN	INSERT	Leistungsart	POSNR='130' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'130','B','Hilfe bei Fehlgeburt',140,'Hilfe bei einer Fehlgeburt','2007-08-01','9999-12-31',0,'+360','131','131','131',0);
#
# PosNr 131
#
WWWRUN	INSERT	Leistungsart	POSNR='131' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'131','B','Hilfe bei Fehlgeburt Nacht,Sa,So',168,'Hilfe bei einer Fehlgeburt mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'+360','','','',0);
#
# PosNr 140
#
WWWRUN	INSERT	Leistungsart	POSNR='140' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'140','B','Vers. Schnitt-/ Rissverletzung ohne DR III/IV',25,'Versorgung einer geburtshilflichen Schnitt- oder Rissverletzung mit Ausnahme DR III oder IV','2007-08-01','9999-12-31',0,'+370','','','',0);
#
# PosNr 150
#
WWWRUN	INSERT	Leistungsart	POSNR='150' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'150','B','Zuschlag Zwillinge',57.5,'Zuschlag f�r die Hilfe bei der Geburt von Zwillingen und mehr Kindern, f�r das zweite und jedes weitere Kind, je Kind','2007-08-01','9999-12-31','','','','','',0);
#
# PosNr 160
#
WWWRUN	INSERT	Leistungsart	POSNR='160' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'160','B','Hilfe bei nicht vollendeter Geburt',140,'Hilfe bei einer nicht vollendeten Geburt a) im Krankenhaus, b) zu Hause, c) in einer au�erklinischen Einrichtung unter Leitung einer Hebammen d) in einer au�erklinischen Einrichtung unter �rztlicher Leitung','2007-08-01','9999-12-31',0,'+360','161','161','161',0);
#
# PosNr 161
#
WWWRUN	INSERT	Leistungsart	POSNR='161' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'161','B','Hilfe bei nicht vollendeter Geburt Nacht,Sa,So',168,'Hilfe bei einer nicht vollendeten Geburt a) im Krankenhaus, b) zu Hause, c) in einer au�erklinischen Einrichtung unter Leitung einer Hebammen d) in einer au�erklinischen Einrichtung unter �rztlicher Leitung mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'+360','','','',0);
#
# PosNr. 170
WWWRUN	INSERT	Leistungsart	POSNR='170' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'170','B','2. Hebamme',14.4,'Hilfe bei einer au�erklinischen Geburt oder Fehlgeburt durch eine zweite Hebamme, f�r jede angefangene halbe Stunde','2007-08-01','9999-12-31','30','+360','171','171','171',0);
#
# PosNr. 171
#
WWWRUN	INSERT	Leistungsart	POSNR='171' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER) values (9999,'171','B','2. Hebamme Nacht,Sa,So',17.28,'Hilfe bei einer au�erklinischen Geburt oder Fehlgeburt durch eine zweite Hebamme, f�r jede angefangene halbe Stunde mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31','30','+360','','','',0);
#
# ------------- Wochenbett ----------------------
#
# Leistungen w�hrend des Wochenbetts
# Leistungsgruppe C
#
# PosNr. 180
#
WWWRUN	INSERT	Leistungsart	POSNR='180' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'180','C','Hausbesuch',26,'Hausbesuch nach der Geburt','2007-08-01','9999-12-31',0,'','181','181','181',0,'+190');
#
# PosNr. 181
#
WWWRUN	INSERT	Leistungsart	POSNR='181' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'181','C','Hausbesuch Nacht,Sa,So',31.2,'Hausbesuch nach der Geburt mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'','','','',0,'+190');
#
# PosNr 190
#
WWWRUN	INSERT	Leistungsart	POSNR='190' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,ZUSATZGEBUEHREN2,ZUSATZGEBUEHREN3,ZUSATZGEBUEHREN4,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'190','C','Zuschlag 1. Besuch nach der Geburt',5.6,'Zuschlag zu der Geb�hr nach Nr. 180 f�r den ersten Hausbesuch nach der Geburt','2007-08-01','9999-12-31','','+380','<6GK','+390','>5GK','','','',0,'');
#
# PosNr 200
#
WWWRUN	INSERT	Leistungsart	POSNR='200' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'200','C','Besuch in Krankenhaus oder au�erkl. Einrichtung',12.9,'Besuch in einem Krankenhaus oder in einer au�erklinischen Einrichtung unter �rztlicher Leitung','2007-08-01','9999-12-31',0,'','201','201','201',0,'');
#
# PosNr 201
#
WWWRUN	INSERT	Leistungsart	POSNR='201' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'201','C','Besuch in K-Haus oder au�erkl. Einr. Nacht,Sa,So',15.48,'Besuch in einem Krankenhaus oder in einer au�erklinischen Einrichtung unter �rztlicher Leitung mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'','','','',0,'');
#
# PosNr. 210
#
WWWRUN	INSERT	Leistungsart	POSNR='210' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'210','C','Besuch in Einr. Leitung Heb.',21,'Besuch in einer von Hebammen geleiteten Einrichtung nach der Geburt','2007-08-01','9999-12-31',0,'','211','211','211',0,'');
#
# PosNr. 211
#
WWWRUN	INSERT	Leistungsart	POSNR='211' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'211','C','Besuch in Einr. Leitung Heb. Nacht,Sa,So',25.2,'Besuch in einer von Hebammen geleiteten Einrichtung nach der Geburt mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31',0,'','','','',0,'');
#
# PosNr 220
#
WWWRUN	INSERT	Leistungsart	POSNR='220' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'220','C','Zuschlag Zwillinge',8.7,'Zuschlag f�r einen Besuch nach der Geburt von Zwillingen und mehr Kindern zu den Geb�hren nach den Nummern 180 bis 210, f�r das zweite und jedes weitere Kind, je Kind','2007-08-01','9999-12-31','','','','','',0,'');
#
# PosNr 230
#
WWWRUN	INSERT	Leistungsart	POSNR='230' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'230','C','Beratung mittels Kommunikationsmedium',5,'Beratung der W�chnerin mittels Kommunikationsmedium','2007-08-01','9999-12-31','','','','','',0,'');
#
# PosNr. 240
#
WWWRUN	INSERT	Leistungsart	POSNR='240' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'240','C','Erstuntersuchung (U1)',7.5,'Erstuntersuchung des Kindes einschlie�lich Eintragung der Befunde in das Untersuchungsheft f�r Kinder (U 1) nach den Richtlinien des Bundesausschusses der �rzte und Krankenkassen �ber die Fr�herkennung von Krankheiten bei Kindern bis zur Vollendung des 6. Lebensjahres (Kinder-Richtlinien) in der jeweils geltenden Fassung.','2007-08-01','9999-12-31','','','','','',0,'');
#
# PosNr. 250
#
WWWRUN	INSERT	Leistungsart	POSNR='250' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'250','C','Entnahme von K�rpermaterial',5.6,'Entnahme von K�rpermaterial zur Durchf�hrung notwendiger Laboruntersuchungen im Rahmen der Richtlinien des Gemeinsamen Bundesausschusses �ber die �rztliche Betreuung w�hrend der Schwanderschaft und nach der Entbindung (Mutterschafts-Richtlinien) oder im Rahmen der Richtlinien des Bundesausschusses der �rzte und Krankenkassen �ber die Fr�herkennung von Krankheiten bei Kindern bis zur Vollendung des 6. Lebensjahres (Kinder-Richtlinien) in der jeweils geltenden Fassung, je Entnahme, einschlie�lich Veranlassung der Laboruntersuchung(en), Versand und Portojosten, Dokumentation nach den vorgenannten Richtlinien und Befund�bermittlung','2007-08-01','9999-12-31','','','','','',0,'');
#
# ------------- sonstige Leisrungen -------------
#
# sonstige Leistungen
# Leistungsgruppe D
# 
# PosNr 260
#
WWWRUN	INSERT	Leistungsart	POSNR='260' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'260','D','�berwachung',14.4,'�berwachung, je angefangene halbe Stunde','2007-08-01','9999-12-31','30','','261','261','261',0,'');
#
# PosNr 261
#
WWWRUN	INSERT	Leistungsart	POSNR='261' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'261','D','�berwachung Nacht,Sa,So',17.28,'�berwachung, je angefangene halbe Stunde mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31','30','','','','',0,'');
#
# PosNr 270
#
WWWRUN	INSERT	Leistungsart	POSNR='270' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'270','D','R�ckbildungsgymnastik Gruppe',5.6,'R�ckbildungsgymnastik bei Unterweisung in der Gruppe, bis zu zehn Teilnehmerinnen je Gruppe und h�chstens zehn Stunden, f�r jede Teilnehmerin je Unterrichtsstunde (60 Minute)','2007-08-01','9999-12-31','E60','','','','',0,'');
#
# PosNr 280
#
WWWRUN	INSERT	Leistungsart	POSNR='280' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'280','D','Beratung bei Stillschwierigkeiten',26,'Beratung der Mutter bei Stillschwierigkeiten oder Ern�hrungsproblemen des S�uglings','2007-08-01','9999-12-31',0,'','281','281','281',0,'');
#
# PosNr 281
#
WWWRUN	INSERT	Leistungsart	POSNR='281' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'281','D','Beratung bei Stillschw. Nacht,Sa,So',31.2,'Beratung der Mutter bei Stillschwierigkeiten oder Ern�hrungsproblemen des S�uglings mit Zuschlag gem�� �5 Abs. 1','2007-08-01','9999-12-31','','','','','',0,'');
#
# PosNr 290
#
WWWRUN	INSERT	Leistungsart	POSNR='290' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1,SAMSTAG,SONNTAG,NACHT,DAUER,EINMALIG) values (9999,'290','D','Beratung bei Stillschw. per Kommunikationsmedium',5,'Beratung der Mutter bei Stillschwierigkeiten oder Ern�hrungsproblemen des S�uglings mittels Kommunikationsmedium','2007-08-01','9999-12-31','','','','','',0,'');
#
##
# ------------ Wegegeld -------------------------
#
# In GO Leistungsgruppe E in tinyHeb Leistungsgruppe W
#
# PosNr 300 Wegegeld nicht mehr als 2 KM Tag
#
WWWRUN	INSERT	Leistungsart	POSNR='300' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'300','W','Wegegeld nicht mehr als 2 KM bei Tag',1.65,'Wegegeld bei einer Entfernung von nicht mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung','2007-08-01','9999-12-31',0);
#
# PosNr 301 anteiliges Wegegeld nicht mehr als 2 KM Tag
#
WWWRUN	INSERT	Leistungsart	POSNR='301' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'301','W','ant. Wegegeld nicht mehr als 2 KM bei Tag',1.65,'anteiliges Wegegeld bei einer Entfernung von nicht mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung','2007-08-01','9999-12-31',0);
#
# PosNr 310 Wegegeld nicht mehr als 2 KM Nacht
#
WWWRUN	INSERT	Leistungsart	POSNR='310' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'310','W','Wegegeld nicht mehr als 2 KM bei Nacht',2.33,'Wegegeld bei einer Entfernung von nicht mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung in der Zeit von 20:00 bis 8:00','2007-08-01','9999-12-31',0);
#
# PosNr 311 anteiliges Wegegeld nicht mehr als 2 KM Nacht
#
WWWRUN	INSERT	Leistungsart	POSNR='311' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'311','W','ant. Wegegeld nicht mehr als 2 KM bei Nacht',2.33,'anteiliges Wegegeld bei einer Entfernung von nicht mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung in der Zeit von 20:00 bis 8:00','2007-08-01','9999-12-31',0);
#
# PosNr 320 Wegegeld mehr als 2 KM Tag
#
WWWRUN	INSERT	Leistungsart	POSNR='320' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'320','W','Wegegeld von mehr als 2 KM bei Tag',0.58,'Wegegeld bei einer Entfernung von mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung, f�r jeden zur�ckgelegten Kilometer','2007-08-01','9999-12-31',0);
#
# PosNr 321 anteiliges Wegegeld mehr als 2 KM Tag
#
WWWRUN	INSERT	Leistungsart	POSNR='321' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'321','W','ant. Wegegeld von mehr als 2 KM bei Tag',0.58,'anteiliges Wegegeld bei einer Entfernung von mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung, f�r jeden zur�ckgelegten Kilometer','2007-08-01','9999-12-31',0);
#
# PosNr 330 Wegegeld mehr als 2 KM Nacht
#
WWWRUN	INSERT	Leistungsart	POSNR='330' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'330','W','Wegegeld von mehr als 2 KM bei Nacht',0.79,'Wegegeld bei einer Entfernung von mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung in der Zeit von 20:00 bis 8:00, f�r jeden zur�ckgelegten Kilometer','2007-08-01','9999-12-31',0);
#
#
# PosNr 331 anteiliges Wegegeld mehr als 2 KM Nacht
#
WWWRUN	INSERT	Leistungsart	POSNR='331' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'331','W','ant. Wegegeld von mehr als 2 KM bei Nacht',0.79,'anteiliges Wegegeld bei einer Entfernung von mehr als zwei Kilometern zwischen der Wohnung oder Praxis der Hebamme und der Stelle der Leistung in der Zeit von 20:00 bis 8:00, f�r jeden zur�ckgelegten Kilometer','2007-08-01','9999-12-31',0);
#
# ------------- Auslagenersatz -------------
#
# sonstige Leistungen
# Leistungsgruppe E in tinyHeb Leistungsgruppe M
#
# PosNr 340
#
WWWRUN	INSERT	Leistungsart	POSNR='340' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'340','M','Pauschale Vorsorgeuntersuchung',2.5,'Materialpauschale Vorsorgeuntersuchung','2007-08-01','9999-12-31',0);
#
# PosNr 350
#
WWWRUN	INSERT	Leistungsart	POSNR='350' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'350','M','Pauschale Schwangerschaftsbeschw.',2.5,'Materialpauschale bei Schwangerschaftsbeschwerden oder bei Wehen','2007-08-01','9999-12-31',0);
#
# PosNr. 360
#
WWWRUN	INSERT	Leistungsart	POSNR='360' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'360','M','Pauschale Geburtshilfe',34,'Materialpauschale Geburtshilfe','2007-08-01','9999-12-31',0);
#
# PosNr. 370
#
WWWRUN	INSERT	Leistungsart	POSNR='370' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'370','M','Pauschale Naht bei Geburtsverletzung',27.5,'Materialpauschale, zus�tzlich zu Nr. 360, bei Versorgung einer Nacht bei Geburtsverletzungen','2007-08-01','9999-12-31',0);
#
# PosNr. 380
#
WWWRUN	INSERT	Leistungsart	POSNR='380' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'380','M','Pauschale Wochenbettbetreuung',24.5,'Materialpauschale Wochenbettbetreuung','2007-08-01','9999-12-31',0);
#
# PosNr. 390
#
WWWRUN	INSERT	Leistungsart	POSNR='390' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'390','M','Pauschale Wochenbettbetreuung nach 4 Tag p.p.',13.3,'Materialpauschale bei Beginn der Betreuung sp�ter als vier Tage nach der Geburt','2007-08-01','9999-12-31',0);
#
# PosNr. 400
#
WWWRUN	INSERT	Leistungsart	POSNR='400' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'400','M','Perinatalerhebung',7.5,'Perinatalerhebung bei einer au�erklinischen Geburt nach vorgeschriebenem Formblatt einschlie�lich Versand- und Portokosten','2007-08-01','9999-12-31',0);
#
#
#
# ------------- sonstige Auslagen -------------
#
# sonstige Leistungen
# Leistungsgruppe F in tinyHeb Leistungsgruppe M
#
# PosNr 500
#
WWWRUN	INSERT	Leistungsart	POSNR='500' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'500','M','Arzneimittel Wirkstoffgruppe Antidiarrhoika',0,'Arzneimittel aus der Wirkstoffgruppe Antidiarrhoika','2007-08-01','9999-12-31',0);
#
# PosNr 510
#
WWWRUN	INSERT	Leistungsart	POSNR='510' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'510','M','Arzneimittel Wirkstoffgruppe Antiemetika',0,'Arzneimittel aus der Wirkstoffgruppe Antiemetika','2007-08-01','9999-12-31',0);
#
#
# PosNr 520
#
WWWRUN	INSERT	Leistungsart	POSNR='520' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'520','M','Arzneimittel Wirkstoffgruppe Antihypotonika',0,'Arzneimittel aus der Wirkstoffgruppe Antihypotonika','2007-08-01','9999-12-31',0);
#
#
# PosNr 530
#
WWWRUN	INSERT	Leistungsart	POSNR='530' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'530','M','Arzneimittel Wirkstoffgruppe Dermatika',0,'Arzneimittel aus der Wirkstoffgruppe Dermatika','2007-08-01','9999-12-31',0);
#
#
# PosNr 540
#
WWWRUN	INSERT	Leistungsart	POSNR='540' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'540','M','Arzneimittel Wirkstoffgruppe Ophtalmika',0,'Arzneimittel aus der Wirkstoffgruppe Ophtalmika','2007-08-01','9999-12-31',0);
#
#
# PosNr 550
#
WWWRUN	INSERT	Leistungsart	POSNR='550' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'550','M','Arzneimittel Wirkstoffgruppe Vitamin D',0,'Arzneimittel aus der Wirkstoffgruppe Vitamin D - auch in Kombination mit Fluorsalzen','2007-08-01','9999-12-31',0);
#
#
# PosNr 560
#
WWWRUN	INSERT	Leistungsart	POSNR='560' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'560','M','Arzneimittel Wirkstoffgruppe Vitamin K',0,'Arzneimittel aus der Wirkstoffgruppe Vitamin K','2007-08-01','9999-12-31',0);
#
#
# PosNr 570
#
WWWRUN	INSERT	Leistungsart	POSNR='570' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'570','M','Arzneimittel Wirkstoffgruppe Antimykotika',0,'Arzneimittel aus der Wirkstoffgruppe Antimykotika','2007-08-01','9999-12-31',0);
#
#
# PosNr 580
#
WWWRUN	INSERT	Leistungsart	POSNR='580' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'580','M','Arzneimittel Wirkstoffgruppe Carminativa',0,'Arzneimittel aus der Wirkstoffgruppe Carminativa','2007-08-01','9999-12-31',0);
#
# PosNr 590
#
WWWRUN	INSERT	Leistungsart	POSNR='590' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'590','M','Arz. Wirkstoffgr. Galle- und Lebertherapeutika ',0,'Arzneimittel aus der Wirkstoffgruppe Galle- u. Lebertherapeutika','2007-08-01','9999-12-31',0);
#
# PosNr 600
#
WWWRUN	INSERT	Leistungsart	POSNR='600' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'600','M','Arzneimittel Wirkstoffgruppe Phytotherapie',0,'Arzneimittel aus der Wirkstoffgruppe Phytotherapie','2007-08-01','9999-12-31',0);
#
# PosNr 610
#
WWWRUN	INSERT	Leistungsart	POSNR='610' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'610','M','Arzneimittel Wirkstoffgruppe Hom�opathie',0,'Arzneimittel aus der Wirkstoffgruppe Hom�opathie','2007-08-01','9999-12-31',0);
#
# PosNr 620
#
WWWRUN	INSERT	Leistungsart	POSNR='620' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'620','M','Arz. Wirkstoffgr. anthroposophische Medizin ',0,'Arzneimittel aus der Wirkstoffgruppe anthroposophische Medizin','2007-08-01','9999-12-31',0);
#
#
# PosNr 800
#
WWWRUN	INSERT	Leistungsart	POSNR='800' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT) values (9999,'800','M','sonstige Auslagen',0,'sonstige Auslagen','2007-08-01','9999-12-31',0);
#
# -------- eigene Positionsnummern w/ Materialien
#
# PosNr M006
#
#WWWRUN	INSERT	Leistungsart	POSNR='M006' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M006','M','Betaisodona Salbe',5.2,'Betaisodona Salbe','2007-08-01','9999-12-31',0,'');
#
# PosNr M007
#
#WWWRUN	INSERT	Leistungsart	POSNR='M007' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M007','M','Wecesinpuder zur Nabelpflege (50 g)',6.62,'Wecesinpuder zur Nabelpflege 50 Gramm','2007-08-01','9999-12-31',0,'');
#
#
# PosNr M008
#
#WWWRUN	INSERT	Leistungsart	POSNR='M008' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M008','M','Moronal Suspension (30 ml)',7.89,'Moronal Suspension (30 ml)','2007-08-01','9999-12-31',0,'570');
#
#
# PosNr M009
#
#WWWRUN	INSERT	Leistungsart	POSNR='M009' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M009','M','Paracetamol (30 St�ck)',2.0,'Paracetamol (30 St�ck)','2007-08-01','9999-12-31',0,'580');
#
#
# PosNr M010
#
#WWWRUN	INSERT	Leistungsart	POSNR='M010' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M010','M','Retterspitz �u�erlich (350 ml)',7.77,'Retterspitz �u�erlich (350 ml)','2007-08-01','9999-12-31',0,'600');
#
#
# PosNr M011
#
#WWWRUN	INSERT	Leistungsart	POSNR='M011' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M011','M','Calendula Urtinktur (20 ml)',10.36,'Calendula Urtinktur (20 ml)','2007-08-01','9999-12-31',0,'610');
#
#
# PosNr M012
#
#WWWRUN	INSERT	Leistungsart	POSNR='M012' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M012','M','Phytolacca D4 Globuli 10g',5.44,'Phytolacca D4 Globuli 10g','2007-08-01','9999-12-31',0,'610');
#
#
# PosNr M013
#
#WWWRUN	INSERT	Leistungsart	POSNR='M013' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M013','M','Phytolacca C30 Globuli 10g',6.97,'Phytolacca C30 Globuli 10g','2007-08-01','9999-12-31',0,'610');
#
#
# PosNr M014
#
#WWWRUN	INSERT	Leistungsart	POSNR='M014' and GUELT_VON='2007-08-01' and GUELT_BIS='9999-12-31'	insert into Leistungsart (ID,POSNR,LEISTUNGSTYP,KBEZ,EINZELPREIS,BEZEICHNUNG,GUELT_VON,GUELT_BIS,FUERZEIT,ZUSATZGEBUEHREN1) values (9999,'M014','M','Lanolin 10g',0.95,'Lanolin 10g','2007-08-01','9999-12-31',0,'530');
#
# -------- sonstige updates Indizes w/ Perfomance
# 
ROOT	ALTER	Leistungsart		alter table Leistungsart add index POSNR_INDEX(POSNR,GUELT_VON,GUELT_BIS);
ROOT	ALTER	Leistungsdaten		alter table Leistungsdaten add index FKST_INDEX(FK_STAMMDATEN);

# -------- neue Felder aufnehmen
ROOT	ALTER	Stammdaten		alter table Stammdaten add KZETGT TINYINT UNSIGNED DEFAULT NULL AFTER DATUM;
ROOT	ALTER	Stammdaten		alter table Stammdaten add GEBURTSZEIT_KIND TIME DEFAULT NULL AFTER KZETGT;
ROOT	ALTER	Leistungsart		alter table Leistungsart add KILOMETER VARCHAR(5) DEFAULT 'J'  AFTER KBEZ;
ROOT	ALTER	Leistungsart		alter table Leistungsart add PZN DECIMAL(7,0) DEFAULT NULL AFTER KILOMETER;
ROOT	ALTER	Leistungsart		alter table Leistungsart add NICHT VARCHAR(100) DEFAULT NULL AFTER PZN;
#
# -------- update w/ neuer Felder
#
# Kilometer j/n
#
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where LEISTUNGSTYP = 'M';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '040';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '060';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '070';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '140';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '150';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '190';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '220';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '230';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '240';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '250';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '270';
WWWRUN	UPDATE	Leistungsart		update Leistungsart set KILOMETER='N' where POSNR = '290';
#
# NICHT
#
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='020,030,040,050,051,060,080' where POSNR = '010' and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='010,030,040,050,051,060,080' where POSNR = '020' and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='010,020' where POSNR = '030' and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='010,020' where POSNR = '040' and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='010,020' where POSNR = '050' and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='010,020' where POSNR = '051' and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='010,020' where POSNR = '060' and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='010,020' where POSNR = '080' and NICHT IS NULL;
#
# alte Positionsnummern NICHT
#
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='1' where POSNR in ('2','4','5','8') and NICHT IS NULL;
WWWRUN	UPDATE	Leistungsart		update Leistungsart set NICHT='2,4,5,8' where POSNR = '1' and NICHT IS NULL;
#
#
# Begr�ndungspflicht POSNR 260,261
#
WWWRUN	UPDATE	Leistungsart		update Leistungsart set BEGRUENDUNGSPFLICHT='j' where POSNR in ('260','261');
