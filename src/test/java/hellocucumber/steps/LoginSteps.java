package hellocucumber.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {

        private String annettuKayttajanimi;
        private String annettuSalasana;
        private String naytettavaTulos;

        @Given("kayttaja on kirjautumissivulla")
        public void kayttaja_on_kirjautumissivulla() {
            // Voit alustaa mockattuun sovellukseen liittyvät asiat tähän
            System.out.println("Käyttäjä siirtyy kirjautumissivulle");
        }

        @When("kayttaja kirjoittaa {string} ja {string}")
        public void kayttaja_kirjoittaa_ja(String kayttajanimi, String salasana) {
            this.annettuKayttajanimi = kayttajanimi;
            this.annettuSalasana = salasana;

            // Simuloidaan kirjautumislogiikkaa
            if ("kayttaja".equals(kayttajanimi) && "salasana".equals(salasana)) {
                naytettavaTulos = "Tervetuloa, kayttaja!";
            } else {
                naytettavaTulos = "Virheellinen käyttäjänimi tai salasana";
            }
        }

        @When("kayttaja klikkaa kirjautumispainiketta")
        public void kayttaja_klikkaa_kirjautumispainiketta() {
            // Tässä olisi oikeassa sovelluksessa painikkeen toiminnallisuus
            System.out.println("Käyttäjä klikkaa kirjautumispainiketta");
        }

        @Then("kayttajalle naytetaan viesti {string}")
        public void kayttajalle_naytetaan_viesti(String odotettuTulos) {
            assertEquals(odotettuTulos, naytettavaTulos);
        }
}

