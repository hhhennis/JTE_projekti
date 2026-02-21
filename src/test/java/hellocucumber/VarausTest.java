package hellocucumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VarausTest {

    private static final String NIMI = "Matti";
    private static final String EMAIL = "matti@test.fi";
    private static final String PUHELIN = "040123";

    @Test
    void varaus_Onnistuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", "2026-02-28", NIMI, EMAIL, PUHELIN);

        assertEquals("Varaus onnistui", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_TilaVarattu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        palvelu.varaa("A100", "2026-03-01", NIMI, EMAIL, PUHELIN);
        String tulos = palvelu.varaa("A100", "2026-03-01", NIMI, EMAIL, PUHELIN);

        assertEquals("Huone ei ole vapaa", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuonePuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("", "2026-03-01", NIMI, EMAIL, PUHELIN);

        assertEquals("Valitse huone", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_AikaPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", "", NIMI, EMAIL, PUHELIN);

        assertEquals("Valitse varausajankohta", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuoneJaAikaPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("", "", NIMI, EMAIL, PUHELIN);

        assertEquals("Valitse huone ja varausajankohta", tulos);
    }

    @Test
    void varauksen_Peruminen_Onnistuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        palvelu.varaa("A120", "2026-03-10", NIMI, EMAIL, PUHELIN);
        String tulos = palvelu.peru("A120", "2026-03-10");

        assertEquals("Varaus peruttu onnistuneesti", tulos);
    }

    @Test
    void varaus_Epaonnistuu_NimiPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", "2026-02-28",
                "", EMAIL, PUHELIN);

        assertEquals("Lisaa varaajan nimi", tulos);
    }

    @Test
    void varaus_Epaonnistuu_EmailPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", "2026-02-28",
                NIMI, "", PUHELIN);

        assertEquals("Lisaa sahkoposti", tulos);
    }

    @Test
    void varaus_Epaonnistuu_PuhelinPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", "2026-02-28",
                NIMI, EMAIL, "");

        assertEquals("Lisaa puhelinnumero", tulos);
    }
}