# Code-Kata: Anagram 1

Angelehnt an: http://codekata.com/kata/kata06-anagrams/

Anagramme sind Wörter die aus den gleichen Buchstaben bestehen wie ein vorgegebenes Wort, also z.B. Torte → Otter, Rotte, etc. die Anzahl der Buchstaben bleibt hier gleich.

Die Aufgabe wäre es also zu einem übergebenen Wort alle Anagramme zu finden. 

Zeile der Aufgabe:

    Das Wissen aus der Double linked list anwenden (z.B. Test driven development, fail fast etc.) 
    Eine übersichtliche Basis Funktionalität erstellen auf den die weiteren Ausbaustufen aufbauen


Die Aufgabe dreht sich um Verständnis also sollten keine Libraries genutzt werden, die die Hauptaufgabe übernehmen. Allerdings ist der Einsatz von Hilfsmethoden wie StringUtils.isBlank() anstelle von if( var == null || var.isEmpty())  völlig legitim. 


Nicht funktionale Anforderung: Geschwindigkeit

 Die Ausführungszeit soll gemessen und dargestellt werden. Der erste Ansatz ist sicher nicht gleich der schnellste, das ist auch Sinn der Aufgabe. Die langsame Implementierung soll nicht gelöscht werden. Eine neue schnellere Lösung soll parallel aufgebaut werden, damit man direkt die Ausführungszeit vergleichen kann. Wenn mehrere Algorithmen entstanden sind soll eine Auswahl Möglichkeit für den Anwender zur Verfügung stehen.


Pflicht tasks

    0.0 Report im Daily (mind. 2 mal die Woche, Mittwoch und Freitag)
    1.1 Definieren: Was heißt eigentlich "alle Anagramme zu finden."
    1.2 Abstrakte Auflistung der notwendigen Schritte (Prosa, Diagramm, what ever)
    1.3 Projekt in Git gespeichert (Bitbucket, Github, Gitlab, etc.)
    1.4 Test grün (Sollte spätestens nach 14 Tagen fertig sein)
    1.5 Release as executable jar
    1.6 Akzeptanz: Testen des jars in unabhängiger Umgebung z.B. andere Pc, VM, Container


Weitere Ausbaustufen

    Containerisierung
    Mehrsprachigkeit
    Durchschnitts execution time persistent speichern, aktualisieren und ausgeben 
    Restservice mit Basic Auth TODO: Which RestFramework on which application server? Spring on Tomcat, resteasy on tomcat? → Need introduction into: "Why do I need a application server" 
    Restservice mit OAuth
    Einfügen persistenter neuer Sprache per API Stichwort: Rollen
    Oberfläche
    OpenIdConnect Absicherung


