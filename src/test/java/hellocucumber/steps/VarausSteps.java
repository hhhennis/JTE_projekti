package hellocucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class VarausSteps {

    private final Kaikille kaikille;
    private String valittuHuone;
    private String valittuAika;

    public VarausSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Given("kayttaja on varaussivulla")
    public void kayttaja_on_varaussivulla() {
        valittuHuone = null;
        valittuAika = null;
    }

    @When("kayttaja valitsee {string} ja varauksen {string}")
    public void kayttaja_valitsee_ja_varauksen(String huone, String aika) {
        this.valittuHuone = huone;
        this.valittuAika = aika;
    }

    @When("{string} on {string} kayttajan valitsemana aikana {string}, kayttaja klikkaa varauspainiketta")
    public void huone_on_tilassa(String huone, String tila, String aika) {
        if ("vapaa".equalsIgnoreCase(tila)) {
            kaikille.viesti = "Varaus onnistui";
        } else {
            kaikille.viesti = "Huone ei ole vapaa";
        }
    }

    // Uusi skenaario: vain toinen valittu / puuttuu
    @When("kayttaja valitsee {string}")
    public void kayttaja_valitsee_arvon(String arvo) {
        if (arvo == null || arvo.isBlank()) return;
        if (arvo.contains(":")) {
            this.valittuAika = arvo;
        } else {
            this.valittuHuone = arvo;
        }
    }

    @When("kayttajalta jaa valitsematta {string}")
    public void kayttajalta_jaa_valitsematta(String puuttuva) {
        boolean huonePuuttuu = (valittuHuone == null || valittuHuone.isBlank());
        boolean aikaPuuttuu  = (valittuAika  == null || valittuAika.isBlank());

        if ("molemmat".equalsIgnoreCase(puuttuva) || (huonePuuttuu && aikaPuuttuu)) {
            kaikille.viesti = "Valitse huone ja varausajankohta";
        } else if ("huone".equalsIgnoreCase(puuttuva) || huonePuuttuu) {
            kaikille.viesti = "Valitse huone";
        } else if ("aika".equalsIgnoreCase(puuttuva) || aikaPuuttuu) {
            kaikille.viesti = "Valitse varausajankohta";
        }
    }
}