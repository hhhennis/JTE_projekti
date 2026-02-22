package hellocucumber.steps;

import hellocucumber.LoginKirjautuminen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final Kaikille kaikille;
    private LoginKirjautuminen login;

    private String annettuKayttajanimi;
    private String annettuSalasana;

    public LoginSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Given("kayttaja on kirjautumissivulla")
    public void kayttaja_on_kirjautumissivulla() {
        //Pääohjelmaluokan (LoginKirjautuminen) luominen
        login = new LoginKirjautuminen();
    }

    @When("kayttaja kirjoittaa {string} ja {string}")
    public void kayttaja_kirjoittaa_ja(String kayttajanimi, String salasana) {
        this.annettuKayttajanimi = kayttajanimi;
        this.annettuSalasana = salasana;
    }

    @When("kayttaja klikkaa kirjautumispainiketta")
    public void kayttaja_klikkaa_kirjautumispainiketta() {
        //Kutsutaan pääohjelmaluokkaa kirjautumisen tarkistamiseen
        kaikille.viesti = login.kirjaudu(annettuKayttajanimi, annettuSalasana);
    }
}