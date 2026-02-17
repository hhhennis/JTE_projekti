package hellocucumber;

import io.cucumber.java.en.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    @Given("an example scenario")
    public void anExampleScenario() {
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
    }

    @Given("kayttaja on varaussivulla")
    public void kayttaja_on_varaussivulla() {
        // esim. alustetaan testiymparisto
    }

    @When("kayttaja valitsee {string} ja varauksen {string}")
    public void kayttaja_valitsee_ja_varauksen(String huone, String aika) {
        // tallenna arvot muuttujiin
    }

    @When("{string} on {string} kayttaajan valitsemana aikana {string}, kayttaja klikkaa varauspainiketta")
    public void huone_on_tilassa(String huone, String tila, String aika) {
        // simuloi varauslogiikkaa
    }

    @Then("kayttajalle naytetaan viesti {string}")
    public void kayttajalle_naytetaan_viesti(String tulos) {
        // tarkista että tulos vastaa odotusta
    }

}
