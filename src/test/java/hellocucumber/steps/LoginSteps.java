package hellocucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final Kaikille kaikille;
    private String annettuKayttajanimi;
    private String annettuSalasana;

    public LoginSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Given("kayttaja on kirjautumissivulla")
    public void kayttaja_on_kirjautumissivulla() {
    }

    @When("kayttaja kirjoittaa {string} ja {string}")
    public void kayttaja_kirjoittaa_ja(String kayttajanimi, String salasana) {
        this.annettuKayttajanimi = kayttajanimi;
        this.annettuSalasana = salasana;

        if ("kayttaja".equals(kayttajanimi) && "salasana".equals(salasana)) {
            kaikille.viesti = "Tervetuloa, kayttaja!";
        } else {
            kaikille.viesti = "Virheellinen kayttajanimi tai salasana";
        }
    }

    @When("kayttaja klikkaa kirjautumispainiketta")
    public void kayttaja_klikkaa_kirjautumispainiketta() {
    }
}