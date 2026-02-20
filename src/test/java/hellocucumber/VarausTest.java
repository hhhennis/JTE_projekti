package hellocucumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VarausTest {

    @Test
    void varaus_Onnistuu() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("A101", "2026-02-28","vapaa");

        assertEquals("Varaus onnistui", varauksenTulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_TilaVarattu() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("A101", "26-03-01","varattu");

        assertEquals("Huone ei ole vapaa", varauksenTulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuonePuuttuu() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("", "26-03-01",null);

        assertEquals("Valitse huone", varauksenTulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_AikaPuuttuu() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("A101", "",null);

        assertEquals("Valitse varausajankohta", varauksenTulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuoneJaAikaPuuttuu() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("", "",null);

        assertEquals("Valitse huone ja varausajankohta", varauksenTulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuoneNull() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa(null, "26-03-01",null);

        assertEquals("Valitse huone", varauksenTulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_AikaNull() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("A101", null,null);

        assertEquals("Valitse varausajankohta", varauksenTulos);
    }

    @Test
    void varaus_Epaonnistuu_Kun_HuoneJaAikaNull() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa(null, null, null);

        assertEquals("Valitse huone ja varausajankohta", varauksenTulos);


    }


    @Test
    void varauksen_Peruminen_Onnistuu() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru("A101", "26-03-01","tulossa");

        assertEquals("Varaus peruttu onnistuneesti", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_TilaKaynnissa() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru("A101", "26-03-01","kaynnissa");

        assertEquals("Kaynnissa olevaa varausta ei voi perua", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_TilaMennyt() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru("A101", "26-01-01","mennyt");

        assertEquals("Mennytta varausta ei voi perua", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuonePuuttuu() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru("", "26-01-01",null);

        assertEquals("Valitse huone", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_AikaPuuttuu() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru("A101", "",null);

        assertEquals("Valitse varausajankohta", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuoneJaAikaPuuttuu() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru("", "",null);

        assertEquals("Valitse huone ja varausajankohta", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuoneNull() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru(null, "26-01-01",null);

        assertEquals("Valitse huone", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_AikaNull() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru("A101", null,null);

        assertEquals("Valitse varausajankohta", perumisenTulos);
    }

    @Test
    void varauksen_Peruminen_Epaonnistuu_Kun_HuoneJaAikaNull() {
        Varaus varaus = new Varaus();

        String perumisenTulos = varaus.peru(null, null,null);

        assertEquals("Valitse huone ja varausajankohta", perumisenTulos);
    }



}

