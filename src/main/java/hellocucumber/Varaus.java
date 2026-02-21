package hellocucumber;

import java.time.LocalDate;

public class Varaus {

    private String huone;
    private LocalDate aika;

    public Varaus(String huone, LocalDate aika) {
        this.huone = huone;
        this.aika = aika;
    }

    public String getHuone() {
        return huone;
    }

    public LocalDate getAika() {
        return aika;
    }
}