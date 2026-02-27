package hellocucumber;

import java.time.LocalDate;

public class Varaus {

    private String huone;
    private LocalDate aika;
    private String nimi;
    private String email;
    private String puhelin;

    public Varaus(String huone, LocalDate aika, String nimi, String email, String puhelin) {
        this.huone = huone;
        this.aika = aika;
        this.nimi = nimi;
        this.email = email;
        this.puhelin = puhelin;
    }

    public String getHuone() { return huone; }
    public LocalDate getAika() { return aika; }
    }