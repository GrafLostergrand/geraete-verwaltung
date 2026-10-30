import java.util.ArrayList;

public class GeraeteVerwaltung {

    private ArrayList<Geraet> geraete = new ArrayList<Geraet>();

    public String aufnehmen(String bezeichnung, String inventarnummer, double wert, boolean ausgeliehen,
            String mitarbeiter, String anschaffungsdatum, String standort, String zustand, String garantieende) {
        if (inventarnummer == null || inventarnummer.trim().equals("")) {
            return "Inventarnummer fehlt";
        }
        inventarnummer = inventarnummer.trim();
        if (finden(inventarnummer) != null) {
            return "Inventarnummer gibt es schon: " + inventarnummer;
        }
        if (wert < 0) {
            return "Wert darf nicht negativ sein";
        }
        if (ausgeliehen && (mitarbeiter == null || mitarbeiter.trim().equals(""))) {
            return "ausgeliehen, aber kein Mitarbeiter";
        }

        Geraet neu = new Geraet(bezeichnung, inventarnummer, wert, ausgeliehen, mitarbeiter,
                anschaffungsdatum, standort, zustand, garantieende);
        geraete.add(neu);
        return inventarnummer + " aufgenommen";
    }

    public Geraet finden(String inventarnummer) {
        if (inventarnummer == null) {
            return null;
        }
        inventarnummer = inventarnummer.trim();
        for (Geraet geraet : geraete) {
            if (inventarnummer.equals(geraet.getInventarnummer())) {
                return geraet;
            }
        }
        return null;
    }

    public Geraet finden(int id) {
        for (Geraet geraet : geraete) {
            if (geraet.getId() == id) {
                return geraet;
            }
        }
        return null;
    }

    public String ausleihen(int id, String mitarbeiterName) {
        Geraet geraet = finden(id);
        if (geraet == null) {
            return "nicht gefunden: Id " + id;
        }
        return geraet.ausleihenAn(mitarbeiterName);
    }

    public String zurueckgeben(int id) {
        Geraet geraet = finden(id);
        if (geraet == null) {
            return "nicht gefunden: Id " + id;
        }
        return geraet.zuruecknehmen();
    }

    public String wertAnzeigen(int id) {
        Geraet geraet = finden(id);
        if (geraet == null) {
            return "kein Geraet: Id " + id;
        }
        return "Wert von " + geraet.getInventarnummer() + " (" + geraet.getBezeichnung() + "): " + geraet.getWert();
    }

    public String ausleihen(String nummer, String mitarbeiterName) {
        Geraet geraet = finden(nummer);
        if (geraet == null) {
            return "nicht gefunden: " + nummer;
        }
        return geraet.ausleihenAn(mitarbeiterName);
    }

    public String zurueckgeben(String nummer) {
        Geraet geraet = finden(nummer);
        if (geraet == null) {
            return "nicht gefunden: " + nummer;
        }
        return geraet.zuruecknehmen();
    }

    public String wertAnzeigen(String nummer) {
        Geraet geraet = finden(nummer);
        if (geraet == null) {
            return "kein Geraet: " + nummer;
        }
        return "Wert von " + geraet.getInventarnummer() + " (" + geraet.getBezeichnung() + "): " + geraet.getWert();
    }

    public String uebersicht() {
        String text = "=== Uebersicht (" + geraete.size() + ") ===\n";
        if (geraete.size() == 0) {
            return text + "keine Geraete\n";
        }
        for (Geraet geraet : geraete) {
            text = text + geraet.toString() + "\n----------\n";
        }
        return text;
    }

    public int anzahl() {
        return geraete.size();
    }
}
