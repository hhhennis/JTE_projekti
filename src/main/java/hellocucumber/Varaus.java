package hellocucumber;

public class Varaus {

    public String varaa(String huone, String aika, String tila) {
        if((huone==null)||(huone=="")){
            if((aika==null)||(aika=="")){
                return "Valitse huone ja varausajankohta";
            }
            return "Valitse huone";
        }
        if((aika==null)||(aika=="")){
            return "Valitse varausajankohta";
        }
        if(tila=="vapaa"){
            return "Varaus onnistui";
        } else {
            return "Huone ei ole vapaa";
        }

    }

    public String peru(String huone, String aika, String tila){
        return "";
    }

}
