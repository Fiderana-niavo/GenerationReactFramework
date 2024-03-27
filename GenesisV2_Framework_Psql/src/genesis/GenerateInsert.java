package genesis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.FileWriter;


import handyman.HandyManUtils;

public class GenerateInsert {
    HashMap<String, String> typeInput;

    public HashMap<String, String> getTypeInput() {
        return this.typeInput;
    }

    public void setTypeInput(HashMap<String, String> type) {
        typeInput = type;
    }

    public GenerateInsert() {
        HashMap<String, String> typeInput = new HashMap<String, String>();
        typeInput.put("float", "number");
        typeInput.put("Float", "number");
        typeInput.put("double", "number");
        typeInput.put("Double", "number");
        typeInput.put("long", "number");
        typeInput.put("int", "number");
        typeInput.put("Integer", "number");
        typeInput.put("String", "text");
        typeInput.put("java.time.LocalDate", "date");
        typeInput.put("java.Time.LocalDateTime", "datetime");
        this.setTypeInput(typeInput);
    }

    public static String lireFichier(String path) throws Exception {

        // public HashMap<String,String> getTypeInput(){
        // return this.typeInput;
        // }

        // public void setTypeInput(HashMap<String,String> type){
        // typeInput=type;
        // }
        // File file = new File(path);

        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();

        return String.valueOf(content);
    }

    public String generateSelect(EntityField ef, String select) {
        return select.replace("[nomTable]", ef.getReferencedField().split("id")[1]);
    }

    public String generateInput(EntityField ef, String input) {
        String fieldInput = input.replace("[nomColonne]", ef.getName());
        fieldInput = fieldInput.replace("[type]", this.getTypeInput().get(ef.getType()));
     //   System.out.println(fieldInput+"hash");
        return fieldInput;
    }

    public String generateForeignList(EntityField ef, String tempforeignList) {
        String foreign = tempforeignList.replace("[nomTable]", ef.getReferencedField().split("id")[1]);
        foreign = foreign.replace("[nomTableMaj]", HandyManUtils.majStart(ef.getReferencedField().split("id")[1]));
        return foreign;
    }

    public String generateAttribute(EntityField ef, String tempAttribute) {
        String attribute = tempAttribute.replace("[nomColonne]", ef.getName());
        return attribute;
    }

    public String generateImportForeign(EntityField ef, String tempAttributeFormData) {
        return tempAttributeFormData.replace("[nomTableMaj]", HandyManUtils.majStart(ef.getReferencedField().split("id")[1]));
    }

<<<<<<< Updated upstream:GenesisV2_Framework_Psql/src/genesis/GenerateInsert.java
    public String generateImportSelectEntite(EntityField ef, String importInsertEntite) {
        return importInsertEntite.replace("[nomTableMaj]", HandyManUtils.majStart(ef.getReferencedField().split("id")[1]));
=======
    public String generateImportSelectEntite(String tableName, String importInsertEntite) {
        return importInsertEntite.replace("[nomTableMaj]", HandyManUtils.majStart(tableName));
>>>>>>> Stashed changes:GenesisV2_Framework_Psql - Copie (2)/src/genesis/GenerateInsert.java
    }

    public  String generateInsertView(Entity e,String select,String input,String tempformDataAttribute,String tempimportForeign,String tempforeignList,String templateInsert,String importInsertEntite){
        String champ="";
        String formDataAttribute="";
        String importForeign="";
        String foreignList="";
        String temp="";
<<<<<<< Updated upstream:GenesisV2_Framework_Psql/src/genesis/GenerateInsert.java
        String insert="";
=======
        String insert=generateImportSelectEntite(e.getTableName(),importInsertEntite);
>>>>>>> Stashed changes:GenesisV2_Framework_Psql - Copie (2)/src/genesis/GenerateInsert.java
        for (EntityField ef : e.getFields()) {
            formDataAttribute+=generateAttribute(ef, tempformDataAttribute);
            insert+=generateImportSelectEntite(ef,importInsertEntite);
            if(ef.isForeign()){
                champ+="\n"+generateSelect(ef, select);
                importForeign+=generateImportForeign(ef, tempimportForeign);
                foreignList+="\n"+generateForeignList(ef, tempforeignList);
            }
            else{
                champ+="\n"+generateInput(ef, input);
            }
        }
        temp=templateInsert.replace("[nomtableMaj]", HandyManUtils.majStart(e.getTableName()));
        System.out.println(temp+"temp");
         temp=temp.replace("[importForeignEntityService]", importForeign);
<<<<<<< Updated upstream:GenesisV2_Framework_Psql/src/genesis/GenerateInsert.java
         temp+=temp.replace("[importInsertEntite]",insert);
=======
         temp=temp.replace("[importInsertEntite]",insert);
>>>>>>> Stashed changes:GenesisV2_Framework_Psql - Copie (2)/src/genesis/GenerateInsert.java
        temp=temp.replace("[foreignList]", foreignList);
        temp=temp.replace("[nomtable]", HandyManUtils.minStart(e.getTableName()));
        temp=temp.replace("[attribut]", formDataAttribute);
        temp=temp.replace("[champ]", champ);
        temp=temp.replace("[nomTableMaj]",HandyManUtils.majStart(e.getTableName()));
        temp=temp.replace("[nomTable]",HandyManUtils.minStart(e.getTableName()));
        return temp;
    }

    public void creationInsertion( String template,Entity e) throws Exception {
        // path = "template/view/insertion.txt";
        // template=template.replace("[Champ]", this.creationChampToutColonne(t));
        // template=template.replace("[nomTable]", t.getNom());

        // // CREATION DU FICHIER
        // /// String nomFichier = path + "/" + tableName + "." +
        // dotnetOuJava.get("type");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Insert/Insertion"+e.getTableName()+".jsx"))) {
        writer.write(template);
        } catch (Exception exe) {
            exe.printStackTrace();
        }
    }

}
