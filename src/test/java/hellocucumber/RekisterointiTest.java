package hellocucumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RekisterointiTest {

    private RekisterointiPalvelu palvelu;

    @BeforeEach
    void setUp() {
        palvelu = new RekisterointiPalvelu();
    }

    @Test
    void onnistunutRekisterointi() {
        String tulos = palvelu.rekisteroi(
                "Matti Meikalainen",
                "Testikatu 1",
                "matti@testi.fi",
                "0401234567"
        );

        assertEquals("Rekisteroityminen onnistui", tulos);
    }

    @Test
    void rekisterointi_epaonnistuu_kun_puuttuvaPuhelinnumero() {
        String tulos = palvelu.rekisteroi(
                "Liisa Esimerkki",
                "Esimerkkitie 5",
                "liisa@testi.fi",
                ""
        );

        assertEquals("Tayta puhelinnumero", tulos);
    }

    @Test
    void rekisterointi_epaonnistuu_kun_virheellinenSahkoposti() {
        String tulos = palvelu.rekisteroi(
                "Minna Malli",
                "Mallitie 10",
                "vaara-sahkoposti",
                "0415552222"
        );

        assertEquals("Sahkopostiosoite ei kelpaa", tulos);
    }

    @Test
    void rekisterointi_epaonnistuu_kun_liianPitkaTaiVirheellinenPuhelin() {
        String tulos = palvelu.rekisteroi(
                "Testi Testaaja",
                "Testikatu 8",
                "testi@testi.fi",
                "LiianPitkaNumero9999"
        );

        assertEquals("Puhelinnumero ei kelpaa", tulos);
    }

    @Test
    void rekisterointi_epaonnistuu_kun_sahkopostiJoKaytossa() {
        palvelu.rekisteroi(
                "Eka",
                "Katu 1",
                "dupe@testi.fi",
                "0401111111"
        );

        String tulos = palvelu.rekisteroi(
                "Toka",
                "Katu 2",
                "dupe@testi.fi",
                "0402222222"
        );

        assertEquals("Sahkopostiosoite on jo kaytossa", tulos);
    }

    @Test
    void rekisterointi_epaonnistuu_kun_puhelinJoKaytossa() {
        palvelu.rekisteroi(
                "Eka",
                "Katu 1",
                "eka@testi.fi",
                "0403333333"
        );

        String tulos = palvelu.rekisteroi(
                "Toka",
                "Katu 2",
                "toka@testi.fi",
                "0403333333"
        );

        assertEquals("Puhelinnumero on jo kaytossa", tulos);
    }

}