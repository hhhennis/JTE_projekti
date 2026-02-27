package hellocucumber;

public class Kayttaja {
    private String kayttajanimi;
    private String salasana;
    private String nimi;
    private String osoite;
    private String email;
    private String puhelinnumero;

    //mitenhän sais järkevästi luotua rekisteröinnin yhteydessä käyttäjänimen ja salasanan
    public Kayttaja(String nimi, String osoite, String email, String puhelinnumero) {
        this.nimi = nimi;
        this.osoite = osoite;
        this.email = email;
        this.puhelinnumero = puhelinnumero;
    }

}
