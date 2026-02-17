package hellocucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class RekisterointiSteps {

    private final Kaikille kaikille;

    public RekisterointiSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Given("kayttaja on rekisteroitymissivulla")
    public void kayttaja_on_rekisteroitymissivulla() {
    }

    @When("kayttaja syottaa {string}, {string}, {string} ja {string}")
    public void kayttaja_syottaa_ja(String nimi, String osoite, String email, String puhelinnumero) {

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
    }
}