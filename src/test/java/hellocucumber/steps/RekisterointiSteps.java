package hellocucumber.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RekisterointiSteps {

    private final Kaikille kaikille;

    //käyttäjät, jotka jo olemassa
    private Map<String, Boolean> olemassaKayttajat = new HashMap<>();

    private static class RekisteriTiedot {
        String nimi;
        String osoite;
        String email;
        String puhelinnumero;

        RekisteriTiedot(String nimi, String osoite, String email, String puhelinnumero) {
            this.nimi = nimi;
            this.osoite = osoite;
            this.email = email;
            this.puhelinnumero = puhelinnumero;
        }
    }

    private RekisteriTiedot rekisteriTiedot;

    public RekisterointiSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Before
    public void setup() {
        olemassaKayttajat.clear();
    }

    @Given("{string} arvolla {string} on jo olemassa")
    public void olemassa(String muuttuja, String arvo) {
        olemassaKayttajat.put(muuttuja + ":" + arvo, true);
    }

    @Given("kayttaja on rekisteroitymissivulla")
    public void kayttaja_on_rekisteroitymissivulla() {
    }

    @When("kayttaja syottaa {string}, {string}, {string} ja {string}")
    public void kayttaja_syottaa_ja(String nimi, String osoite, String email, String puhelinnumero) {
        rekisteriTiedot = new RekisteriTiedot(nimi, osoite, email, puhelinnumero);
        if (puhelinnumero == null || puhelinnumero.isBlank()) {
            kaikille.viesti = "Tayta puhelinnumero";
            return;
        }
        if (!email.contains("@") || !email.contains(".")) {
            kaikille.viesti = "Sahkopostiosoite ei kelpaa";
            return;
        }
        if (puhelinnumero.length() > 15 || !puhelinnumero.matches("\\d+")) {
            kaikille.viesti = "Puhelinnumero ei kelpaa";
            return;
        }
        kaikille.viesti = "Rekisteroityminen onnistui";
    }

    @When("kayttaja painaa rekisterointipainiketta")
    public void kayttaja_painaa_rekisterointipainiketta() {
        String emailKey = "email:" + rekisteriTiedot.email;
        String puhKey = "puhelinnumero:" + rekisteriTiedot.puhelinnumero;

        if (olemassaKayttajat.containsKey(emailKey)) {
            kaikille.viesti = "Sahkopostiosoite on jo kaytossa";
        } else if (olemassaKayttajat.containsKey(puhKey)) {
            kaikille.viesti = "Puhelinnumero on jo kaytossa";
        }
    }

    @Then("Rekisteroityminen onnistui")
    public void rekisteroityminen_onnistui() {
        assertEquals("Rekisteroityminen onnistui", kaikille.viesti);
    }

    @Then("kayttajalle naytetaan virheviesti {string}")
    public void kayttajalle_naytetaan_virheviesti(String string) {
        assertEquals(string, kaikille.viesti);
    }
}