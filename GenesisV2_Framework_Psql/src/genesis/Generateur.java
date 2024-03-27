package genesis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Generateur {
    HashMap<String, String> remplacement;

    public void setRemplacement(HashMap<String, String> remplacement) {
        this.remplacement = remplacement;
    }

    public Generateur() {

        HashMap<String, String> remplacement = new HashMap<String, String>();
        remplacement.put("int", "number");
        remplacement.put("double", "number");
        remplacement.put("Double", "number");
        remplacement.put("integer", "number");
        remplacement.put("String", "text");
        remplacement.put("java.time.LocalDate", "date");
        this.setRemplacement(remplacement);
    }

    public Generateur(HashMap<String, String> remplacement) {
        this.remplacement = remplacement;
    }

    public HashMap<String, String> getRemplacement() {
        return remplacement;
    }



}
