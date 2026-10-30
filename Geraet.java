public class Geraet {

    private static int naechsteId = 1000;

    private int id;
    private String bezeichnung;
    private String inventarnummer;
    private double wert;
    private boolean ausgeliehen;
    private String mitarbeiter;
    private String anschaffungsdatum;
    private String standort;
    private String zustand;
    private String garantieende;

    public Geraet(String bezeichnung, String inventarnummer, double wert, boolean ausgeliehen,
            String mitarbeiter, String anschaffungsdatum, String standort, String zustand, String garantieende) {
        this.id = naechsteId;
        naechsteId++;
        this.bezeichnung = text(bezeichnung);
        this.inventarnummer = text(inventarnummer);
        this.wert = wert;
        this.anschaffungsdatum = text(anschaffungsdatum);
        this.standort = text(standort);
        this.zustand = text(zustand);
        this.garantieende = text(garantieende);
        mitarbeiter = text(mitarbeiter);

        if (ausgeliehen && !mitarbeiter.trim().equals("")) {
            this.ausgeliehen = true;
            this.mitarbeiter = mitarbeiter.trim();
        } else {
            this.ausgeliehen = false;
            this.mitarbeiter = "";
        }
    }

    private static String text(String wert) {
        if (wert == null) {
            return "";
        }
        return wert;
    }

    public String ausleihenAn(String mitarbeiterName) {
        if (mitarbeiterName == null || mitarbeiterName.trim().equals("")) {
            return "Mitarbeitername fehlt";
        }
        if (ausgeliehen) {
            return inventarnummer + " ist schon ausgeliehen (" + mitarbeiter + ")";
        }
        ausgeliehen = true;
        mitarbeiter = mitarbeiterName.trim();
        return inventarnummer + " ausgeliehen an " + mitarbeiter;
    }

    public String zuruecknehmen() {
        if (!ausgeliehen) {
            return inventarnummer + " ist schon verfuegbar";
        }
        ausgeliehen = false;
        mitarbeiter = "";
        return inventarnummer + " zurueck, Mitarbeiter leer";
    }

    public void anzeigen() {
        System.out.println("Id: " + id);
        System.out.println(inventarnummer);
        System.out.println(bezeichnung);
        System.out.println(wert);
    }

    public int getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getInventarnummer() {
        return inventarnummer;
    }

    public double getWert() {
        return wert;
    }

    public boolean isAusgeliehen() {
        return ausgeliehen;
    }

    public String getMitarbeiter() {
        return mitarbeiter;
    }

    public String getAnschaffungsdatum() {
        return anschaffungsdatum;
    }

    public String getStandort() {
        return standort;
    }

    public String getZustand() {
        return zustand;
    }

    public String getGarantieende() {
        return garantieende;
    }

    @Override
    public String toString() {
        String status = "verfuegbar";
        if (ausgeliehen) {
            status = "ausgeliehen, Mitarbeiter: " + mitarbeiter;
        }
        return "Id: " + id + "\n"
                + "Bezeichnung: " + bezeichnung + "\n"
                + "Inventarnummer: " + inventarnummer + "\n"
                + "Wert: " + wert + "\n"
                + "Status: " + status + "\n"
                + "Anschaffungsdatum: " + anschaffungsdatum + "\n"
                + "Standort: " + standort + "\n"
                + "Zustand: " + zustand + "\n"
                + "Garantieende: " + garantieende;
    }
}
