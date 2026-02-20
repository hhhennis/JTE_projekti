package hellocucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class VarausSteps {

    private final Kaikille kaikille;
    private String valittuHuone;
    private String valittuAika;
    private String varauksenTila;
    private String peruttavaHuone;
    private String peruttavaAika;

    public VarausSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Given("kayttaja on varaussivulla")
    public void kayttaja_on_varaussivulla() {
        valittuHuone = null;
        valittuAika = null;
        peruttavaAika = null;
        peruttavaHuone = null;
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



    @When("kayttaja valitsee peruttavan varauksen huoneen {string} paivalle {string}")
    public void kayttaja_valitsee_peruttavan_varauksen(String huone, String aika) {
        this.peruttavaHuone = huone;
        this.peruttavaAika = aika;
    }

    @When("varaus on tilassa {string}")
    public void varaus_on_tilassa(String tila) {
        this.varauksenTila = tila;
    }

    @When("kayttaja klikkaa peruutuspainiketta")
    public void kayttaja_klikkaa_peruutuspainiketta() {

        if ((peruttavaHuone == null || peruttavaHuone.isBlank()) &&
                (peruttavaAika == null || peruttavaAika.isBlank())) {
            kaikille.viesti = "Valitse huone ja varausajankohta";
            return;
        }

        if (peruttavaHuone == null || peruttavaHuone.isBlank()) {
            kaikille.viesti = "Valitse huone";
            return;
        }

        if (peruttavaAika == null || peruttavaAika.isBlank()) {
            kaikille.viesti = "Valitse varausajankohta";
            return;
        }

        switch (varauksenTila.toLowerCase()) {
            case "tulossa":
                kaikille.viesti = "Varaus peruttu onnistuneesti";
                break;

            case "kaynnissa":
                kaikille.viesti = "Kaynnissa olevaa varausta ei voi perua";
                break;

            case "mennyt":
                kaikille.viesti = "Mennytta varausta ei voi perua";
                break;
        }
    }
}
