package hellocucumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VarausPalvelu {

    private List<Varaus> varausLista = new ArrayList<>();

    public VarausPalvelu() {
        varausLista.add(new Varaus("A100", LocalDate.now().minusDays(10), "Testi", "t@test.fi", "040"));
        varausLista.add(new Varaus("A120", LocalDate.now().plusDays(2), "Testi", "t@test.fi", "040"));
        varausLista.add(new Varaus("A140", LocalDate.now(), "Testi", "t@test.fi", "040"));
    }

    private boolean tyhja(String s) {
        return s == null || s.isBlank();
    }

    private String laskeTila(LocalDate aika) {
        if (aika.isBefore(LocalDate.now())) return "mennyt";
        if (aika.isEqual(LocalDate.now())) return "kaynnissa";
        return "tulossa";
    }

    public Varaus haeVaraus(String huone, LocalDate aika) {
        return varausLista.stream()
                .filter(v -> v.getHuone().equals(huone) && v.getAika().equals(aika))
                .findFirst()
                .orElse(null);
    }

    public String varaa(String huoneStr, String aikaStr,
                        String nimi, String email, String puhelin) {

        if (tyhja(huoneStr) && tyhja(aikaStr)) return "Valitse huone ja varausajankohta";
        if (tyhja(huoneStr)) return "Valitse huone";
        if (tyhja(aikaStr)) return "Valitse varausajankohta";
        if (tyhja(nimi)) return "Lisaa varaajan nimi";
        if (tyhja(email)) return "Lisaa sahkoposti";
        if (tyhja(puhelin)) return "Lisaa puhelinnumero";

        LocalDate aika = LocalDate.parse(aikaStr);

        // tarkista onko huone + aika jo varattu
        if (haeVaraus(huoneStr, aika) != null) return "Huone ei ole vapaa";

        varausLista.add(new Varaus(huoneStr, aika, nimi, email, puhelin));
        return "Varaus onnistui";
    }

    public String peru(String huoneStr, String aikaStr) {

        if (tyhja(huoneStr) && tyhja(aikaStr)) return "Valitse huone ja varausajankohta";
        if (tyhja(huoneStr)) return "Valitse huone";
        if (tyhja(aikaStr)) return "Valitse varausajankohta";

        LocalDate aika = LocalDate.parse(aikaStr);
        Varaus v = haeVaraus(huoneStr, aika);

        if (v == null) return "Varausta ei loydy";

        String tila = laskeTila(v.getAika());

        if ("tulossa".equals(tila)) {
            varausLista.remove(v);
            return "Varaus peruttu onnistuneesti";
        }
        if ("kaynnissa".equals(tila)) return "Kaynnissa olevaa varausta ei voi perua";
        if ("mennyt".equals(tila)) return "Mennytta varausta ei voi perua";

        return "";
    }
}