# codekata: Anagram

Inspiriert durch: http://codekata.com/kata/kata06-anagrams/

## Agenda
### Wortliste initialisieren
```java
private final List<String> unfilteredWordlist;
```
* Wordlist im Konstruktor initialisieren: kann final sein, anfällig beim Deserialisieren, weil beim Deserialisieren der Konstruktor aufgerufen wird
    * Als Methode: Logik aus Konstruktor raushalten
___
```java
    private List<String> filterWordlist( List<String> unfilteredWordlist ) {
```
* testen
___

