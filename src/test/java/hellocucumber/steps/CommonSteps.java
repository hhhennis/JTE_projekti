package hellocucumber.steps;

import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonSteps {
    private final Kaikille kaikille;

    public CommonSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Then("kayttajalle naytetaan viesti {string}")
    public void kayttajalle_naytetaan_viesti(String odotettu) {
        assertEquals(odotettu, kaikille.viesti);
    }
}