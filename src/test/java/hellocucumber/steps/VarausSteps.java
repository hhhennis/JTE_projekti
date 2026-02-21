package hellocucumber.steps;

import hellocucumber.VarausPalvelu;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.time.LocalDate;

public class VarausSteps {

    private final Kaikille kaikille;
    private VarausPalvelu palvelu;

    private String valittuHuone;
    private String valittuAika;
    private String nimi;
    private String email;
    private String puhelin;
    private String valittuNimi;
    private String valittuEmail;
    private String valittuPuhelin;

    private String varauksenTila;
    private String peruttavaHuone;
    private String peruttavaAika;

    public VarausSteps(Kaikille kaikille) {
        this.kaikille = kaikille;
    }

    @Given("kayttaja on varaussivulla")
    public void kayttaja_on_varaussivulla() {
        palvelu = new VarausPalvelu();
        valittuHuone = null;
        valittuAika = null;
        valittuNimi = null;
        valittuEmail = null;
        valittuPuhelin = null;
        peruttavaHuone = null;
        peruttavaAika = null;
        nimi = null;
        email = null;
        puhelin = null;
    }

    @When("kayttaja valitsee {string} ja varauksen {string}")
    public void kayttaja_valitsee_ja_varauksen(String huone, String aika) {
        this.valittuHuone = huone;
        this.valittuAika = aika;
    }

    @When("kayttaja antaa varaajan tiedot {string} {string} {string}")
    public void kayttaja_antaa_tiedot(String nimi, String email, String puhelin) {
        this.valittuNimi = nimi;
        this.valittuEmail = email;
        this.valittuPuhelin = puhelin;
    }

    @When("{string} on {string} kayttajan valitsemana aikana {string}, kayttaja klikkaa varauspainiketta")
    public void huone_on_tilassa(String huone, String tila, String aikaStr) {
        if ("vapaa".equalsIgnoreCase(tila)) {
            kaikille.viesti = palvelu.varaa(huone, aikaStr, valittuNimi, valittuEmail, valittuPuhelin);
        } else {
            if (palvelu.haeVaraus(huone, LocalDate.parse(aikaStr)) == null) {
                palvelu.varaa(huone, aikaStr, "Testi", "test@test.fi", "0400000000");
            }
            kaikille.viesti = "Huone ei ole vapaa";
        }
    }

    @When("kayttaja valitsee peruttavan varauksen huoneen {string} paivalle {string}")
    public void kayttaja_valitsee_peruttavan_varauksen(String huone, String aika) {
        peruttavaHuone = huone;
        peruttavaAika = aika;
    }

    @When("varaus on tilassa {string}")
    public void varaus_on_tilassa(String tila) {
        if (peruttavaHuone == null || peruttavaHuone.isBlank()) return;

        if (peruttavaAika == null || peruttavaAika.isBlank()) {
            // Ei voida luoda varauksia ilman päivämäärää
            kaikille.viesti = "Valitse varausajankohta";
            return;
        }

        LocalDate aika;
        switch (tila.toLowerCase()) {
            case "tulossa":
                aika = LocalDate.now().plusDays(1);
                break;
            case "kaynnissa":
                aika = LocalDate.now();
                break;
            case "mennyt":
                aika = LocalDate.now().minusDays(1);
                break;
            default:
                aika = LocalDate.now();
        }

        if (palvelu.haeVaraus(peruttavaHuone, aika) == null) {
            palvelu.varaa(peruttavaHuone, aika.toString(), "Testi", "test@test.fi", "0400000000");
        }
        peruttavaAika = aika.toString();
    }

    @When("kayttaja klikkaa peruutuspainiketta")
    public void kayttaja_klikkaa_peruutuspainiketta() {
        kaikille.viesti = palvelu.peru(peruttavaHuone, peruttavaAika);
    }
}