package hellocucumber;

public class LoginKirjautuminen {

    public String kirjaudu(String kayttajanimi, String salasana) {
        // Tarkistetaan onko käyttäjänimi ja salasana oikein
        if ("kayttaja".equals(kayttajanimi) && "salasana".equals(salasana)) {
            return "Tervetuloa, kayttaja!";
        } else {
            return "Virheellinen kayttajanimi tai salasana";
        }
    }
}
