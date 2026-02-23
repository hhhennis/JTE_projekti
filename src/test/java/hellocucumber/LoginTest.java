package hellocucumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    //Perustapaukset
    //Onnistunut kirjautuminen: oikea käyttäjänimi JA salasana.
    @Test
    public void kirjautuminen_onnistuu() {
        LoginKirjautuminen login = new LoginKirjautuminen();
        String viesti = login.kirjaudu("kayttaja", "salasana");
        assertEquals("Tervetuloa, kayttaja!", viesti);
    }

    //Virheellinen käyttäjänimi: väärä nimi, oikea salasana.
    @Test
    public void kirjautuminen_VirheellinenNimi() {
        LoginKirjautuminen login = new LoginKirjautuminen();
        String viesti = login.kirjaudu("vääränimi", "salasana");
        assertEquals("Virheellinen kayttajanimi tai salasana", viesti);
    }


    //Virheellinen salasana: oikea nimi, väärä salasana.
    @Test
    public void kirjautumine_VirheellinenSalasana() {
        LoginKirjautuminen login = new LoginKirjautuminen();
        String viesti = login.kirjaudu("kayttaja", "vääräsalasana");
        assertEquals("Virheellinen kayttajanimi tai salasana", viesti);
    }

    //Molemmat väärin: väärä nimi JA väärä salasana.
    @Test
    public void kirjautuminen_MolemmatVaarin() {
        LoginKirjautuminen login = new LoginKirjautuminen();
        String viesti = login.kirjaudu("vääränimi", "vääräsalasana");
        assertEquals("Virheellinen kayttajanimi tai salasana", viesti);
    }

    //Tyhjät kentät: nimi TAI salasana puuttuu.
    @Test
    public void kirjautuminen_TyhjatKentat() {
        LoginKirjautuminen login = new LoginKirjautuminen();

        // Tyhjä käyttäjänimi
        String viesti1 = login.kirjaudu("", "salasana");
        assertEquals("Virheellinen kayttajanimi tai salasana", viesti1);

        // Tyhjä salasana
        String viesti2 = login.kirjaudu("kayttaja", "");
        assertEquals("Virheellinen kayttajanimi tai salasana", viesti2);

        // Molemmat tyhjät
        String viesti3 = login.kirjaudu("", "");
        assertEquals("Virheellinen kayttajanimi tai salasana", viesti3);
    }

}
