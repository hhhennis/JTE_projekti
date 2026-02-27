package hellocucumber;

import java.util.HashMap;
import java.util.Map;

public class RekisterointiPalvelu {

    private Map<String, Kayttaja> kayttajat = new HashMap<>();

    private Map<String, Kayttaja> emailIndex = new HashMap<>();
    private Map<String, Kayttaja> puhelinIndex = new HashMap<>();

    public String rekisteroi(String nimi, String osoite, String email, String puhelinnumero) {

        if (puhelinnumero == null || puhelinnumero.isBlank()) {
            return "Tayta puhelinnumero";
        }

        if (email == null || !email.contains("@") || !email.contains(".")) {
            return "Sahkopostiosoite ei kelpaa";
        }

        if (puhelinnumero.length() > 15 || !puhelinnumero.matches("\\d+")) {
            return "Puhelinnumero ei kelpaa";
        }

        if (emailIndex.containsKey(email)) {
            return "Sahkopostiosoite on jo kaytossa";
        }

        if (puhelinIndex.containsKey(puhelinnumero)) {
            return "Puhelinnumero on jo kaytossa";
        }

        Kayttaja k = new Kayttaja(nimi, osoite, email, puhelinnumero);
        emailIndex.put(email, k);
        puhelinIndex.put(puhelinnumero, k);

        return "Rekisteroityminen onnistui";
    }
}