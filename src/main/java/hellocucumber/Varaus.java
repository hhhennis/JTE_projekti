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
