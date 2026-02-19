package hellocucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class VarausSteps {

    private final Kaikille kaikille;
    private String valittuHuone;
    private String valittuAika;
    private String peruttavaVaraus;
    private String varauksenTila; // ✅ uusi

    public VarausSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Given("kayttaja on varaussivulla")
    public void kayttaja_on_varaussivulla() {
        valittuHuone = null;
        valittuAika = null;
        peruttavaVaraus = null;
        varauksenTila = null;
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


    @When("kayttaja valitsee {string}")
    public void kayttaja_valitsee_arvon(String arvo) {
        if (arvo == null || arvo.isBlank()) return;

        if (arvo.matches("\\d{4}-\\d{2}-\\d{2}")) {
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



    @When("kayttaja valitsee peruttavan varauksen {string}")
    public void kayttaja_valitsee_peruttavan_varauksen(String varaus) {
        this.peruttavaVaraus = varaus;
    }

    @When("varaus on tilassa {string}")
    public void varaus_on_tilassa(String tila) {
        this.varauksenTila = tila;
    }

    @When("kayttaja klikkaa peruutuspainiketta")
    public void kayttaja_klikkaa_peruutuspainiketta() {

        if (peruttavaVaraus == null || peruttavaVaraus.isBlank()) {
            kaikille.viesti = "Varausta ei loytynyt";
            return;
        }

        if ("tulossa".equalsIgnoreCase(varauksenTila)) {
            kaikille.viesti = "Varaus peruttu onnistuneesti";
        } else if ("kaynnissa".equalsIgnoreCase(varauksenTila)) {
            kaikille.viesti = "Kaynnissa olevaa varausta ei voi perua";
        } else if ("mennyt".equalsIgnoreCase(varauksenTila)) {
            kaikille.viesti = "Mennytta varausta ei voi perua";
        }
    }
}
