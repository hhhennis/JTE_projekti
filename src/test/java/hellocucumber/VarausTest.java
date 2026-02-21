package hellocucumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VarausTest {

    @Test
    void varaus_Onnistuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", "2026-02-28");

        assertEquals("Varaus onnistui", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_TilaVarattu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        palvelu.varaa("A100", "2026-03-01");
        String tulos = palvelu.varaa("A100", "2026-03-01");

        assertEquals("Huone ei ole vapaa", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuonePuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("", "2026-03-01");

        assertEquals("Valitse huone", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_AikaPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", "");

        assertEquals("Valitse varausajankohta", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuoneJaAikaPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("", "");

        assertEquals("Valitse huone ja varausajankohta", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuoneNull() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa(null, "2026-03-01");

        assertEquals("Valitse huone", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_AikaNull() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa("A100", null);

        assertEquals("Valitse varausajankohta", tulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuoneJaAikaNull() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.varaa(null, null);

        assertEquals("Valitse huone ja varausajankohta", tulos);
    }


    @Test
    void varauksen_Peruminen_Onnistuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        palvelu.varaa("A120", "2026-03-10");
        String tulos = palvelu.peru("A120", "2026-03-10");

        assertEquals("Varaus peruttu onnistuneesti", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_TilaKaynnissa() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru("A140", java.time.LocalDate.now().toString());

        assertEquals("Kaynnissa olevaa varausta ei voi perua", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_TilaMennyt() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru("A100", java.time.LocalDate.now().minusDays(10).toString());

        assertEquals("Mennytta varausta ei voi perua", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuonePuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru("", "2026-01-01");

        assertEquals("Valitse huone", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_AikaPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru("A100", "");

        assertEquals("Valitse varausajankohta", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuoneJaAikaPuuttuu() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru("", "");

        assertEquals("Valitse huone ja varausajankohta", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuoneNull() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru(null, "2026-01-01");

        assertEquals("Valitse huone", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_AikaNull() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru("A100", null);

        assertEquals("Valitse varausajankohta", tulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuoneJaAikaNull() {
        VarausPalvelu palvelu = new VarausPalvelu();

        String tulos = palvelu.peru(null, null);

        assertEquals("Valitse huone ja varausajankohta", tulos);
    }
}