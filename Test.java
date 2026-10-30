public class Test {

    static int ok = 0;
    static int falsch = 0;

    public static void check(String name, boolean bedingung) {
        if (bedingung) {
            System.out.println("OK   " + name);
            ok++;
        } else {
            System.out.println("FEHLER   " + name);
            falsch++;
        }
    }

    public static void main(String[] args) {
        GeraeteVerwaltung v = new GeraeteVerwaltung();

        System.out.println(v.aufnehmen("Lenovo ThinkPad E14", "NB-001", 1199, true, "Jonas Müller", "12.03.2024", "Buero 1", "gebraucht", "12.03.2027"));
        System.out.println(v.aufnehmen("Dell Latitude 5450", "NB-002", 899, false, "", "05.11.2023", "Lager", "gebraucht", "05.11.2026"));
        System.out.println(v.aufnehmen("HP ProBook 450", "NB-003", 949, true, "Sarah Meier", "20.01.2025", "Vertrieb", "neu", "20.01.2028"));
        System.out.println(v.aufnehmen("Apple iPad Air", "TAB-001", 699, false, "", "15.06.2024", "Besprechung", "gebraucht", "15.06.2026"));
        System.out.println(v.aufnehmen("Samsung Galaxy Tab S9", "TAB-002", 579, true, "Lena Schmidt", "02.09.2025", "Aussendienst", "neu", "02.09.2027"));
        System.out.println(v.aufnehmen("Lenovo ThinkPad L14", "NB-012", 1049, false, "", "18.04.2025", "Lager", "neu", "18.04.2028"));
        System.out.println(v.aufnehmen("Lenovo ThinkPad E14", "NB-017", 1199, true, "Becker", "10.02.2024", "Buero 2", "gebraucht", "10.02.2027"));

        check("7 geraete in der liste", v.anzahl() == 7);

        Geraet nb1 = v.finden("NB-001");
        check("NB-001 gefunden", nb1 != null);
        if (nb1 != null) {
            check("NB-001 nummer stimmt", nb1.getInventarnummer().equals("NB-001"));
            check("NB-001 bezeichnung", nb1.getBezeichnung().equals("Lenovo ThinkPad E14"));
            check("NB-001 ist ausgeliehen", nb1.isAusgeliehen());
            check("NB-001 mitarbeiter Mueller", nb1.getMitarbeiter().equals("Jonas Müller"));
            check("NB-001 id 1000", nb1.getId() == 1000);
            check("NB-001 datum", nb1.getAnschaffungsdatum().equals("12.03.2024"));
            check("NB-001 standort", nb1.getStandort().equals("Buero 1"));
            check("NB-001 zustand", nb1.getZustand().equals("gebraucht"));
            check("NB-001 garantie", nb1.getGarantieende().equals("12.03.2027"));
            nb1.anzeigen();
        }

        check("falsche nummer ist null", v.finden("XX-999") == null);
        check("finden null kracht nicht", v.finden(null) == null);
        check("ausleihen falsche nummer", v.ausleihen("XX-999", "Mueller").equals("nicht gefunden: XX-999"));
        check("rueckgabe falsche nummer", v.zurueckgeben("XX-999").equals("nicht gefunden: XX-999"));
        check("wert falsche nummer", v.wertAnzeigen("XX-999").equals("kein Geraet: XX-999"));

        Geraet tab1 = v.finden("TAB-001");
        check("TAB-001 gefunden", tab1 != null);
        if (tab1 != null) {
            check("TAB-001 iPad frei", tab1.isAusgeliehen() == false && tab1.getWert() == 699);
            check("TAB-001 bezeichnung", tab1.getBezeichnung().equals("Apple iPad Air"));
            check("TAB-001 datum", tab1.getAnschaffungsdatum().equals("15.06.2024"));
            check("TAB-001 standort", tab1.getStandort().equals("Besprechung"));
            check("TAB-001 zustand", tab1.getZustand().equals("gebraucht"));
            check("TAB-001 garantie", tab1.getGarantieende().equals("15.06.2026"));
        }
        Geraet tab2 = v.finden("TAB-002");
        check("TAB-002 gefunden", tab2 != null);
        if (tab2 != null) {
            check("TAB-002 bei Lena Schmidt", tab2.isAusgeliehen() && tab2.getMitarbeiter().equals("Lena Schmidt"));
            check("TAB-002 wert 579", tab2.getWert() == 579);
            check("TAB-002 datum", tab2.getAnschaffungsdatum().equals("02.09.2025"));
            check("TAB-002 standort", tab2.getStandort().equals("Aussendienst"));
            check("TAB-002 zustand", tab2.getZustand().equals("neu"));
            check("TAB-002 garantie", tab2.getGarantieende().equals("02.09.2027"));
        }
        Geraet nb3 = v.finden("NB-003");
        check("NB-003 gefunden", nb3 != null);
        if (nb3 != null) {
            check("NB-003 bei Sarah Meier", nb3.getMitarbeiter().equals("Sarah Meier") && nb3.getWert() == 949);
            check("NB-003 datum", nb3.getAnschaffungsdatum().equals("20.01.2025"));
            check("NB-003 standort", nb3.getStandort().equals("Vertrieb"));
            check("NB-003 zustand", nb3.getZustand().equals("neu"));
            check("NB-003 garantie", nb3.getGarantieende().equals("20.01.2028"));
        }

        Geraet nb12 = v.finden("NB-012");
        check("NB-012 gefunden", nb12 != null);
        if (nb12 != null) {
            check("NB-012 wert 1049", nb12.getWert() == 1049);
            check("NB-012 bezeichnung", nb12.getBezeichnung().equals("Lenovo ThinkPad L14"));
            check("NB-012 datum", nb12.getAnschaffungsdatum().equals("18.04.2025"));
            check("NB-012 standort", nb12.getStandort().equals("Lager"));
            check("NB-012 zustand", nb12.getZustand().equals("neu"));
            check("NB-012 garantie", nb12.getGarantieende().equals("18.04.2028"));
            String wertText = v.wertAnzeigen(nb12.getId());
            System.out.println(wertText);
            check("wertAnzeigen per id", wertText.equals("Wert von NB-012 (Lenovo ThinkPad L14): 1049.0"));
        }
        Geraet nb2 = v.finden("NB-002");
        check("NB-002 gefunden", nb2 != null);
        if (nb2 != null) {
            check("NB-002 wert 899", nb2.getWert() == 899);
            check("NB-002 ist frei", nb2.isAusgeliehen() == false);
            check("NB-002 datum", nb2.getAnschaffungsdatum().equals("05.11.2023"));
            check("NB-002 standort", nb2.getStandort().equals("Lager"));
            check("NB-002 zustand", nb2.getZustand().equals("gebraucht"));
            check("NB-002 garantie", nb2.getGarantieende().equals("05.11.2026"));
            check("NB-002 andere id", nb1 != null && nb2.getId() != nb1.getId());
            check("finden per id ist NB-002", v.finden(nb2.getId()) == nb2);
        }
        check("falsche id ist null", v.finden(9999) == null);
        check("ausleihen falsche id", v.ausleihen(9999, "Schmidt").equals("nicht gefunden: Id 9999"));

        String nochmal = v.ausleihen("NB-001", "Schmidt");
        System.out.println(nochmal);
        check("zweite ausleihe geht nicht", nochmal.equals("NB-001 ist schon ausgeliehen (Jonas Müller)"));
        if (nb1 != null) {
            check("Mueller bleibt", nb1.getMitarbeiter().equals("Jonas Müller"));
        }

        String leihe = v.ausleihen(nb2.getId(), "Lena Schmidt");
        System.out.println(leihe);
        check("ausleihen text", leihe.equals("NB-002 ausgeliehen an Lena Schmidt"));
        if (nb2 != null) {
            check("NB-002 jetzt ausgeliehen", nb2.isAusgeliehen());
            check("NB-002 mitarbeiter Lena Schmidt", nb2.getMitarbeiter().equals("Lena Schmidt"));
        }

        String ohneName = v.ausleihen("NB-012", "  ");
        System.out.println(ohneName);
        check("leerer name geht nicht", ohneName.equals("Mitarbeitername fehlt"));
        if (nb12 != null) {
            check("NB-012 bleibt frei", nb12.isAusgeliehen() == false);
        }

        Geraet nb17 = v.finden("NB-017");
        check("NB-017 gefunden", nb17 != null);
        if (nb17 != null) {
            check("NB-017 vorher Becker", nb17.getMitarbeiter().equals("Becker"));
            check("NB-017 vorher ausgeliehen", nb17.isAusgeliehen());
            check("NB-017 datum", nb17.getAnschaffungsdatum().equals("10.02.2024"));
            check("NB-017 standort", nb17.getStandort().equals("Buero 2"));
            check("NB-017 zustand", nb17.getZustand().equals("gebraucht"));
            check("NB-017 garantie", nb17.getGarantieende().equals("10.02.2027"));
            String rueck = v.zurueckgeben(nb17.getId());
            System.out.println(rueck);
            check("rueckgabe text", rueck.equals("NB-017 zurueck, Mitarbeiter leer"));
            check("NB-017 nachher frei", nb17.isAusgeliehen() == false);
            check("NB-017 mitarbeiter leer", nb17.getMitarbeiter().equals(""));
            String rueck2 = v.zurueckgeben("NB-017");
            System.out.println(rueck2);
            check("zweite rueckgabe", rueck2.equals("NB-017 ist schon verfuegbar"));
            check("danach immer noch leer", nb17.getMitarbeiter().equals(""));
            check("NB-017 standort bleibt", nb17.getStandort().equals("Buero 2"));
            check("NB-017 datum bleibt", nb17.getAnschaffungsdatum().equals("10.02.2024"));
        }

        String fehltNummer = v.aufnehmen("Kaputt", "", 10, false, "", "01.01.2026", "X", "alt", "01.01.2027");
        check("leere nummer geht nicht", fehltNummer.equals("Inventarnummer fehlt"));
        String negativ = v.aufnehmen("Kaputt", "NB-050", -5, false, "", "01.01.2026", "X", "alt", "01.01.2027");
        check("negativer wert geht nicht", negativ.equals("Wert darf nicht negativ sein"));
        check("schlechte geraete nicht in der liste", v.finden("NB-050") == null && v.anzahl() == 7);

        int vorher = v.anzahl();
        String neuText = v.aufnehmen("Samsung Galaxy Tab", "TAB-010", 649, false, "", "01.08.2026", "Empfang", "neu", "01.08.2028");
        System.out.println(neuText);
        check("neues geraet dazu", v.anzahl() == vorher + 1);
        Geraet neu = v.finden("TAB-010");
        check("TAB-010 finden", neu != null);
        if (neu != null) {
            check("TAB-010 wert 649", neu.getWert() == 649);
            check("TAB-010 bezeichnung", neu.getBezeichnung().equals("Samsung Galaxy Tab"));
            check("TAB-010 frei", neu.isAusgeliehen() == false);
            check("TAB-010 datum", neu.getAnschaffungsdatum().equals("01.08.2026"));
            check("TAB-010 standort", neu.getStandort().equals("Empfang"));
            check("TAB-010 zustand", neu.getZustand().equals("neu"));
            check("TAB-010 garantie", neu.getGarantieende().equals("01.08.2028"));
        }

        String doppelt = v.aufnehmen("Nochmal", "NB-001", 1, false, "", "01.01.2020", "X", "alt", "01.01.2021");
        System.out.println(doppelt);
        check("doppelte nummer nicht nochmal", doppelt.equals("Inventarnummer gibt es schon: NB-001"));
        check("anzahl bleibt", v.anzahl() == vorher + 1);
        if (nb1 != null) {
            check("alter wert bleibt 1199", nb1.getWert() == 1199);
            check("alte bezeichnung bleibt", nb1.getBezeichnung().equals("Lenovo ThinkPad E14"));
        }

        String liste = v.uebersicht();
        System.out.println(liste);
        check("uebersicht hat TAB-010", liste.contains("TAB-010"));
        check("uebersicht hat NB-003", liste.contains("NB-003"));
        check("Becker ist weg", liste.contains("Becker") == false);
        int von = liste.indexOf("Inventarnummer: NB-017");
        int bis = liste.indexOf("----------", von);
        String block = "";
        if (von >= 0 && bis > von) {
            block = liste.substring(von, bis);
        }
        check("NB-017 in der uebersicht verfuegbar", block.contains("Status: verfuegbar"));
        check("NB-017 ohne mitarbeiterzeile", block.contains("Mitarbeiter") == false);

        System.out.println("-----");
        System.out.println(ok + " ok, " + falsch + " fehler");
        if (falsch > 0) {
            System.exit(1);
        }
    }
}
