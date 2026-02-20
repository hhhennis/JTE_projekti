package hellocucumber;

import java.util.ArrayList;
import java.util.Arrays;

public class Varaus {

    ArrayList<String> huoneLista = new ArrayList<>(Arrays.asList(
            "A100", "A110", "A120", "A130", "A140", "A150", "A160",
            "A170", "A180", "A190", "A200", "A210", "A220", "A230"
    ));

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

        String peruutus = "";

        if(((huone==null)||(huone==""))){

            peruutus = "Valitse huone";
        }
        if((aika==null)||(aika=="")){
            peruutus = "Valitse varausajankohta";
        }
        if(((huone==null)||(huone==""))&&((aika==null)||(aika==""))){
            peruutus = "Valitse huone ja varausajankohta";
        }
        if (tila=="tulossa"){
            peruutus = "Varaus peruttu onnistuneesti";
        } else if (tila=="kaynnissa"){
            peruutus = "Kaynnissa olevaa varausta ei voi perua";
        } else if (tila=="mennyt"){
            peruutus = "Mennytta varausta ei voi perua";
        }

        return peruutus;
    }

}
