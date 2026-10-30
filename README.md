# Geräteverwaltung

Einfaches Java-Schulprojekt zur Verwaltung von Geräten (Laptops, Tablets usw.): aufnehmen, ausleihen, zurückgeben und Übersicht anzeigen.

## Dateien / Klassen

| Datei | Klasse | Beschreibung |
|-------|--------|--------------|
| `Geraet.java` | `Geraet` | Ein Gerät mit Id, Inventarnummer, Wert, Ausleihstatus usw. |
| `GeraeteVerwaltung.java` | `GeraeteVerwaltung` | Liste der Geräte: aufnehmen, finden, ausleihen, zurückgeben |
| `Test.java` | `Test` | Testprogramm mit Beispieldaten und Checks |

## Kompilieren und starten

Voraussetzung: JDK (javac/java) installiert.

```bash
javac Geraet.java GeraeteVerwaltung.java Test.java
java Test
```

Die Klasse `Test` gibt OK/FEHLER für die Prüfungen aus und beendet sich mit Exit-Code 1, wenn Fehler auftreten.
