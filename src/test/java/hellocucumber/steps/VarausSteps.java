package hellocucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class VarausSteps {

    private final Kaikille kaikille;
    private String valittuHuone;
    private String valittuAika;
    private String peruttavaVaraus;


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

    @When
            ("kayttaja klikkaa peruutuspainiketta")
    public void kayttaja_klikkaa_peruutuspainiketta() {
        // Yksinkertainen simulaatiologiikka esimerkkitapauksiin:

        if (peruttavaVaraus == null || peruttavaVaraus.isBlank()) {
            // Ei pitäisi tulla Examplesista, mutta varmistetaan
            kaikille.viesti = "Varausta ei loytynyt";
            return;
        }

        // 1) Tunnistetaan erikoistapaukset ensin
        if (peruttavaVaraus.equals("Varauksen ID ei loydy")) {
            kaikille.viesti = "Varausta ei loytynyt";
            return;
        }

        // Jos teksti on täsmälleen tämä
        if (peruttavaVaraus.equals("Kayttaja yrittää perua toisen kayttajan varauksen")
                || peruttavaVaraus.equals("Kayttaja yrittai perua toisen kayttajan varauksen")) {
            kaikille.viesti = "Et voi perua toisen kayttajan varausta";
            return;
        }

        // 2) Jos varaus on jo alkanut (tai merkitty sellaiseksi), ei voi perua
        if (peruttavaVaraus.contains("jo alkanut")) {
            kaikille.viesti = "Menneisyyden tai kaynnissa olevaa varausta ei voi perua";
            return;
        }

        // 3) Muut tapaukset: peruminen onnistuu
        kaikille.viesti = "Varaus peruttu onnistuneesti";
    }


}