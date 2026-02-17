package hellocucumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VarausTest {

    @Test
    void varausOnnistuu() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("A101", "vapaa");

        assertEquals("Varaus onnistui", varauksenTulos);
    }

    @Test
    void varausEpaonnistuu() {
        Varaus varaus = new Varaus();

        String varauksenTulos = varaus.varaa("A101", "varattu");

        assertEquals("Huone ei ole vapaa", varauksenTulos);
    }


}
