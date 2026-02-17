package hellocucumber;

public class Varaus {

    public String varaa(String huone, String tila) {
        if(tila=="vapaa"){
            return "Varaus onnistui";
        } else {
            return "Huone ei ole vapaa";
        }

    }

}
