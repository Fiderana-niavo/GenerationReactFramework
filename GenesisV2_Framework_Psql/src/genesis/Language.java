package genesis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import handyman.HandyManUtils;
import java.io.*;

public class Language {
    private int id;
    private String nom;
    private HashMap<String, String> syntax;
    private HashMap<String, String> types, typeParsers;
    private String skeleton;
    private String[] projectNameTags;
    private CustomFile[] additionnalFiles;
    private Model model;
    private Controller controller;
    private View view;
    private CustomChanges[] customChanges;
    private NavbarLink navbarLinks;

    public NavbarLink getNavbarLinks() {
        return navbarLinks;
    }

    public void setNavbarLinks(NavbarLink navbarLinks) {
        this.navbarLinks = navbarLinks;
    }

    public CustomChanges[] getCustomChanges() {
        return customChanges;
    }

    public void setCustomChanges(CustomChanges[] customChanges) {
        this.customChanges = customChanges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public HashMap<String, String> getSyntax() {
        return syntax;
    }

    public void setSyntax(HashMap<String, String> syntax) {
        this.syntax = syntax;
    }

    public HashMap<String, String> getTypes() {
        return types;
    }

    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }

    public HashMap<String, String> getTypeParsers() {
        return typeParsers;
    }

    public void setTypeParsers(HashMap<String, String> typeParsers) {
        this.typeParsers = typeParsers;
    }

    public String getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(String skeleton) {
        this.skeleton = skeleton;
    }

    public String[] getProjectNameTags() {
        return projectNameTags;
    }

    public void setProjectNameTags(String[] projectNameTags) {
        this.projectNameTags = projectNameTags;
    }

    public CustomFile[] getAdditionnalFiles() {
        return additionnalFiles;
    }

    public void setAdditionnalFiles(CustomFile[] additionnalFiles) {
        this.additionnalFiles = additionnalFiles;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String generateModel(Entity entity, String projectName) throws IOException, Exception {
        String content = HandyManUtils.getFileContent(
                Constantes.DATA_PATH + "/" + getModel().getModelTemplate() + "." + Constantes.MODEL_TEMPLATE_EXT);
        content = content.replace("[namespace]", getSyntax().get("namespace"));
        content = content.replace("[namespaceStart]", getSyntax().get("namespaceStart"));
        content = content.replace("[namespaceEnd]", getSyntax().get("namespaceEnd"));
        content = content.replace("[package]", getModel().getModelPackage());
        String imports = "";
        for (String i : getModel().getModelImports()) {
            imports += i + "\n";
        }
        content = content.replace("[imports]", imports);
        String annotes = "";
        for (String a : getModel().getModelAnnotations()) {
            annotes += a + "\n";
        }
        content = content.replace("[classAnnotations]", annotes);
        content = content.replace("[extends]", getModel().getModelExtends());
        String constructors = "";
        for (String c : getModel().getModelConstructors()) {
            constructors += c + "\n";
        }
        content = content.replace("[constructors]", constructors);
        String fields = "", fieldAnnotes;
        for (int i = 0; i < entity.getFields().length; i++) {
            fieldAnnotes = "";
            if (entity.getFields()[i].isPrimary()) {
                for (String primAnnote : getModel().getModelPrimaryFieldAnnotations()) {
                    fieldAnnotes += primAnnote + "\n";
                }
            } else if (entity.getFields()[i].isForeign()) {
                for (String forAnnote : getModel().getModelForeignFieldAnnotations()) {
                    fieldAnnotes += forAnnote + "\n";
                    fieldAnnotes = fieldAnnotes.replace("[referencedFieldNameMin]",
                            HandyManUtils.minStart(entity.getFields()[i].getReferencedField()));
                    fieldAnnotes = fieldAnnotes.replace("[referencedFieldNameMaj]",
                            HandyManUtils.majStart(entity.getFields()[i].getReferencedField()));
                }
            }
            for (String fa : getModel().getModelFieldAnnotations()) {
                fieldAnnotes += fa + "\n";
            }
            fields += fieldAnnotes;
            fields += getModel().getModelFieldContent() + "\n";
            fields += getModel().getModelGetter() + "\n";
            fields += getModel().getModelSetter() + "\n";
            fields = fields.replace("[columnName]", entity.getColumns()[i].getName());
            fields = fields.replace("[fieldType]", entity.getFields()[i].getType());
            fields = fields.replace("[modelFieldCase]", getModel().getModelFieldCase());
            fields = fields.replace("[fieldNameMin]", HandyManUtils.minStart(entity.getFields()[i].getName()));
            fields = fields.replace("[fieldNameMaj]", HandyManUtils.majStart(entity.getFields()[i].getName()));
        }
        content = content.replace("[fields]", fields);
        content = content.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
        content = content.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
        content = content.replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
        content = content.replace("[modelFieldCase]", getModel().getModelFieldCase());
        content = content.replace("[primaryFieldType]", entity.getPrimaryField().getType());
        content = content.replace("[primaryFieldNameMin]", HandyManUtils.minStart(entity.getPrimaryField().getName()));
        content = content.replace("[primaryFieldNameMaj]", HandyManUtils.majStart(entity.getPrimaryField().getName()));
        content = content.replace("[tableName]", entity.getTableName());
        return content;
    }

    public String generateController(Entity entity, Database database, Credentials credentials, String projectName)
            throws IOException, Exception {
        String content = HandyManUtils.getFileContent(Constantes.DATA_PATH + "/"
                + getController().getControllerTemplate() + "." + Constantes.CONTROLLER_TEMPLATE_EXT);
        content = content.replace("[namespace]", getSyntax().get("namespace"));
        content = content.replace("[namespaceStart]", getSyntax().get("namespaceStart"));
        content = content.replace("[namespaceEnd]", getSyntax().get("namespaceEnd"));
        content = content.replace("[package]", getController().getControllerPackage());
        String imports = "";
        for (String i : getController().getControllerImports()) {
            imports += i + "\n";
        }
        content = content.replace("[imports]", imports);
        String annotes = "";
        for (String a : getController().getControllerAnnotations()) {
            annotes += a + "\n";
        }
        content = content.replace("[controllerAnnotations]", annotes);
        content = content.replace("[extends]", getController().getControllerExtends());
        String fields = "", fieldAnnotes;
        for (ControllerField cf : getController().getControllerFields()) {
            fieldAnnotes = "";
            for (String a : cf.getControllerFieldAnnotations()) {
                fieldAnnotes += a + "\n";
            }
            fields += fieldAnnotes;
            fields += cf.getControllerFieldContent() + "\n";
        }
        for (EntityField ef : entity.getFields()) {
            if (ef.isForeign() && getController().getControllerFieldsForeign() != null) {
                fieldAnnotes = "";
                for (String a : getController().getControllerFieldsForeign().getControllerFieldAnnotations()) {
                    fieldAnnotes += a + "\n";
                }
                fields += fieldAnnotes;
                fields += getController().getControllerFieldsForeign().getControllerFieldContent() + "\n";
                fields = fields.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getType()));
                fields = fields.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getType()));
            }
        }
        content = content.replace("[fields]", fields);
        String constructors = "";
        String constructorParameter, foreignInstanciation;
        for (String c : getController().getControllerConstructors()) {
            constructorParameter = "";
            foreignInstanciation = "";
            for (EntityField ef : entity.getFields()) {
                if (ef.isForeign()) {
                    constructorParameter = constructorParameter + ","
                            + getController().getControllerForeignContextParam();
                    constructorParameter = constructorParameter.replace("[foreignNameMaj]",
                            HandyManUtils.majStart(ef.getName()));
                    foreignInstanciation += getController().getControllerForeignContextInstanciation();
                    foreignInstanciation = foreignInstanciation.replace("[foreignNameMaj]",
                            HandyManUtils.majStart(ef.getName())) + "\n";
                }
            }
            constructors += c + "\n";
            constructors = constructors.replace("[controllerForeignContextParam]", constructorParameter);
            constructors = constructors.replace("[controllerForeignContextInstanciation]", foreignInstanciation);
        }
        content = content.replace("[constructors]", constructors);
        String methods = "", methodAnnotes, methodParameters;
        String changeInstanciation, whereInstanciation, foreignList, foreignInclude;
        for (ControllerMethod m : getController().getControllerMethods()) {
            methodAnnotes = "";
            for (String ma : m.getControllerMethodAnnotations()) {
                methodAnnotes += ma + "\n";
            }
            methods += methodAnnotes;
            methodParameters = "";
            for (EntityField ef : entity.getFields()) {
                methodParameters += m.getControllerMethodParameter();
                if (methodParameters.isEmpty() == false) {
                    methodParameters += ",";
                }
                methodParameters = methodParameters.replace("[fieldType]", ef.getType());
                methodParameters = methodParameters.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
            }
            if (methodParameters.isEmpty() == false) {
                methodParameters = methodParameters.substring(0, methodParameters.length() - 1);
            }
            methods += HandyManUtils.getFileContent(Constantes.DATA_PATH + "/" + m.getControllerMethodContent() + "."
                    + Constantes.CONTROLLER_TEMPLATE_EXT);
            methods = methods.replace("[controllerMethodParameter]", methodParameters);
            changeInstanciation = "";
            foreignList = "";
            foreignInclude = "";
            for (int i = 0; i < entity.getFields().length; i++) {
                if (entity.getFields()[i].isPrimary()) {
                    continue;
                } else if (entity.getFields()[i].isForeign()) {
                    changeInstanciation += getController().getControllerForeignInstanciation().get("template");
                    changeInstanciation = changeInstanciation.replace("[content]", getTypeParsers()
                            .get(getTypes().get(database.getTypes().get(entity.getColumns()[i].getType()))));
                    changeInstanciation = changeInstanciation.replace("[value]",
                            getController().getControllerForeignInstanciation().get("value"));
                    changeInstanciation = changeInstanciation.replace("[fieldNameMin]",
                            HandyManUtils.minStart(entity.getFields()[i].getName()));
                    changeInstanciation = changeInstanciation.replace("[fieldNameMaj]",
                            HandyManUtils.majStart(entity.getFields()[i].getName()));
                    changeInstanciation = changeInstanciation.replace("[foreignType]", entity.getFields()[i].getType());
                    changeInstanciation = changeInstanciation.replace("[referencedFieldNameMaj]",
                            HandyManUtils.majStart(entity.getFields()[i].getReferencedField()));
                    changeInstanciation = changeInstanciation.replace("[foreignNameMin]",
                            HandyManUtils.minStart(entity.getFields()[i].getName()));
                    foreignList += getController().getControllerForeignList();
                    foreignList = foreignList.replace("[foreignType]", entity.getFields()[i].getType());
                    foreignList = foreignList.replace("[foreignNameMin]",
                            HandyManUtils.minStart(entity.getFields()[i].getName()));
                    foreignInclude += getController().getControllerForeignInclude();
                    foreignInclude = foreignInclude.replace("[foreignNameMaj]",
                            HandyManUtils.majStart(entity.getFields()[i].getName()));
                    continue;
                }
                changeInstanciation += getController().getControllerChangeInstanciation().get("template");
                changeInstanciation = changeInstanciation.replace("[content]",
                        getTypeParsers().get(entity.getFields()[i].getType()));
                changeInstanciation = changeInstanciation.replace("[value]",
                        getController().getControllerChangeInstanciation().get("value"));
                changeInstanciation = changeInstanciation.replace("[fieldNameMin]",
                        HandyManUtils.minStart(entity.getFields()[i].getName()));
                changeInstanciation = changeInstanciation.replace("[fieldNameMaj]",
                        HandyManUtils.majStart(entity.getFields()[i].getName()));
            }
            whereInstanciation = "";
            whereInstanciation += getController().getControllerWhereInstanciation().get("template");
            whereInstanciation = whereInstanciation.replace("[content]",
                    getTypeParsers().get(entity.getPrimaryField().getType()));
            whereInstanciation = whereInstanciation.replace("[value]",
                    getController().getControllerWhereInstanciation().get("value"));
            methods = methods.replace("[primaryParse]",
                    getTypeParsers().get(entity.getPrimaryField().getType()).replace("[value]", "[primaryNameMin]"));
            methods = methods.replace("[controllerChangeInstanciation]", changeInstanciation);
            methods = methods.replace("[controllerWhereInstanciation]", whereInstanciation);
            methods = methods.replace("[controllerForeignList]", foreignList);
            methods = methods.replace("[controllerForeignInclude]", foreignInclude);
            methods = methods.replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));
            methods = methods.replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
            methods = methods.replace("[primaryNameMaj]", HandyManUtils.majStart(entity.getPrimaryField().getName()));
            methods = methods.replace("[primaryType]", entity.getPrimaryField().getType());
            methods = methods.replace("[primaryNameMin]", HandyManUtils.minStart(entity.getPrimaryField().getName()));
            methods = methods.replace("[databaseDriver]", database.getDriver());
            methods = methods.replace("[databaseSgbd]", database.getNom());
            methods = methods.replace("[databaseHost]", credentials.getHost());
            methods = methods.replace("[databasePort]", database.getPort());
            methods = methods.replace("[databaseName]", credentials.getDatabaseName());
            methods = methods.replace("[user]", credentials.getUser());
            methods = methods.replace("[pwd]", credentials.getPwd());
            methods = methods.replace("[databaseUseSSL]", String.valueOf(credentials.isUseSSL()));
            methods = methods.replace("[databaseAllowKey]", String.valueOf(credentials.isAllowPublicKeyRetrieval()));
        }
        content = content.replace("[methods]", methods);
        content = content.replace("[controllerNameMaj]", getController().getControllerName());
        content = content.replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
        content = content.replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));
        content = content.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
        content = content.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
        content = content.replace("[databaseDriver]", database.getDriver());
        content = content.replace("[databaseSgbd]", database.getNom());
        content = content.replace("[databaseHost]", credentials.getHost());
        content = content.replace("[databaseName]", credentials.getDatabaseName());
        content = content.replace("[databasePort]", database.getPort());
        content = content.replace("[databaseID]", String.valueOf(database.getId()));
        content = content.replace("[user]", credentials.getUser());
        content = content.replace("[pwd]", credentials.getPwd());
        content = content.replace("[databaseUseSSL]", String.valueOf(credentials.isUseSSL()));
        content = content.replace("[databaseAllowKey]", String.valueOf(credentials.isAllowPublicKeyRetrieval()));
        return content;
    }

    public String generateView(Entity entity, String projectName) throws IOException, Exception {
        String content = HandyManUtils.getFileContent(
                Constantes.DATA_PATH + "/" + getView().getViewContent() + "." + Constantes.VIEW_TEMPLATE_EXT);
        String foreignList = "";
        String tableHeader = "";
        String tableLine = "";
        String foreignGet;
        String updateForm = "", insertForm = "";
        for (EntityField ef : entity.getFields()) {
            foreignGet = "";
            tableHeader += getView().getViewTableHeader();
            tableHeader = tableHeader.replace("[fieldNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
            tableHeader = tableHeader.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
            tableHeader = tableHeader.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
            tableLine += getView().getViewTableLine();
            tableLine = tableLine.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
            tableLine = tableLine.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
            if (ef.isPrimary()) {
                tableLine = tableLine.replace("[foreignFieldGet]", foreignGet);
                continue;
            }
            if (ef.isForeign() == false) {
                updateForm += HandyManUtils.getFileContent(Constantes.DATA_PATH + "/"
                        + getView().getViewUpdateFormField().get(ef.getType()) + "." + Constantes.VIEW_TEMPLATE_EXT);
                updateForm = updateForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                updateForm = updateForm.replace("[fieldNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
                updateForm = updateForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                insertForm += HandyManUtils.getFileContent(Constantes.DATA_PATH + "/"
                        + getView().getViewInsertFormField().get(ef.getType()) + "." + Constantes.VIEW_TEMPLATE_EXT);
                insertForm = insertForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                insertForm = insertForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                insertForm = insertForm.replace("[fieldNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
                tableLine = tableLine.replace("[foreignFieldGet]", foreignGet);
                continue;
            }
            updateForm += HandyManUtils.getFileContent(Constantes.DATA_PATH + "/"
                    + getView().getViewUpdateFormForeignField() + "." + Constantes.VIEW_TEMPLATE_EXT);
            updateForm = updateForm.replace("[foreignType]", ef.getType());
            updateForm = updateForm.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
            updateForm = updateForm.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
            updateForm = updateForm.replace("[foreignPrimaryNameMaj]", HandyManUtils.majStart(ef.getReferencedField()));
            updateForm = updateForm.replace("[foreignPrimaryNameMin]", HandyManUtils.minStart(ef.getReferencedField()));
            updateForm = updateForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
            updateForm = updateForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
            updateForm = updateForm.replace("[foreignNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
            insertForm += HandyManUtils.getFileContent(Constantes.DATA_PATH + "/"
                    + getView().getViewInsertFormForeignField() + "." + Constantes.VIEW_TEMPLATE_EXT);
            insertForm = insertForm.replace("[foreignType]", ef.getType());
            insertForm = insertForm.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
            insertForm = insertForm.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
            insertForm = insertForm.replace("[foreignPrimaryNameMaj]", HandyManUtils.majStart(ef.getReferencedField()));
            insertForm = insertForm.replace("[foreignPrimaryNameMin]", HandyManUtils.minStart(ef.getReferencedField()));
            insertForm = insertForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
            insertForm = insertForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
            insertForm = insertForm.replace("[foreignNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
            foreignGet = getView().getForeignFieldGet();
            tableLine = tableLine.replace("[foreignFieldGet]", foreignGet);
            foreignList += getView().getViewForeignList();
            foreignList = foreignList.replace("[foreignType]", ef.getType());
            foreignList = foreignList.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
            foreignList = foreignList.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
        }
        content = content.replace("[viewForeignList]", foreignList);
        content = content.replace("[viewTableHeader]", tableHeader);
        content = content.replace("[viewTableLine]", tableLine);
        content = content.replace("[viewUpdateFormField]", updateForm);
        content = content.replace("[viewInsertFormField]", insertForm);
        content = content.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
        content = content.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
        content = content.replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
        content = content.replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));
        content = content.replace("[primaryNameMaj]", HandyManUtils.majStart(entity.getPrimaryField().getName()));
        content = content.replace("[primaryNameMin]", HandyManUtils.minStart(entity.getPrimaryField().getName()));
        return content;
    }

    /*********************************************************** */

    public String createComposant(Entity entity, String projectName) throws IOException, Exception {

        String directory = projectName + "/composants/";
        try {
            creerDossier(directory);
            Constantes c = new Constantes();
            // String contentFile = "D:\\GenesisV2_Framework_Psql\\Generateur\\GenesisV2_Framework_Psql\\template\\composant.templ";
            String contentFile = c.VIEW_LISTE_COMPOSANT;
            String content = HandyManUtils.getFileContent(contentFile);

            String tableHeader = "";
            String tableLineTd = "";

            String tableauDebut = "<td>{props.";
            String tableauFin = "}</td>";
            String propTypesDeclaration = "";

            for (EntityField ef : entity.getFields()) {
                String fieldNameMin = HandyManUtils.minStart(ef.getName());
                String fieldType = "PropTypes.string";
                tableHeader += getView().getViewTableHeader();
                // Concaténer les valeurs de chaque champ à tableLine
                tableLineTd += tableauDebut + HandyManUtils.minStart(ef.getName()) + tableauFin;
                content = content.replace("[fieldId]", HandyManUtils.minStart(ef.getName()));
                content = content.replace("[propsType]", "PropTypes.string");
                propTypesDeclaration += "\n    " + fieldNameMin + " : " + fieldType + ",";

            }

            // Remplacer [tableLine] par la valeur complète de tableLine
            content = content.replace("[tableLine]", tableLineTd);
            content = content.replace("[propTypes]", propTypesDeclaration);

            String className = HandyManUtils.majStart(entity.getClassName());
            content = content.replace("[classNameMaj]", className);
            content = content.replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));

            // Écriture du contenu dans le fichier
            try {
                writeToFile(directory + className + ".jsx", content);
                System.out.println("Fichier créé avec succès : " + directory + className + ".jsx");
            } catch (IOException e) {
                System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du composant : " + e.getMessage());
            throw e; // Propagez l'exception pour la gestion à un niveau supérieur si nécessaire
        }

        return directory;
    }

    public String createPage(Entity entity, String projectName) throws IOException, Exception {

        String directory = projectName + "/pages/";
        try {
            creerDossier(directory);
            Constantes c = new Constantes();
            // String contentFile = "D:\\GenesisV2_Framework_Psql\\Generateur\\GenesisV2_Framework_Psql\\template\\page.templ";
            String contentFile = c.VIEW_LISTE_PAGE;
            String content = HandyManUtils.getFileContent(contentFile);

            String tableHeader = "";
            String tableLineTd = "";

            String thDebut = "<th>";
            String thFin = "</th>";
            String column = "";

            for (EntityField ef : entity.getFields()) {
                String fieldNameMin = HandyManUtils.minStart(ef.getName());
                String classNameMin = HandyManUtils.minStart(entity.getClassName());
                tableHeader += getView().getViewTableHeader();
                // Concaténer les valeurs de chaque champ à tableLine
                tableLineTd += thDebut + HandyManUtils.minStart(ef.getName()) + thFin;
                content = content.replace("[fieldId]", HandyManUtils.minStart(ef.getName()));
                content = content.replace("[propsType]", "PropTypes.string");
                column += "\n    " + fieldNameMin + " = { " + classNameMin + "." + fieldNameMin + "}";

            }

            // Remplacer [tableLine] par la valeur complète de tableLine
            content = content.replace("[headerTab]", tableLineTd);
            content = content.replace("[fields]", column);

            String className = HandyManUtils.majStart(entity.getClassName());

            content = content.replace("[classNameMaj]", className);
            content = content.replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));
            content = content.replace("[variable]", HandyManUtils.minStart(entity.getClassName()));

            // Écriture du contenu dans le fichier
            try {
                writeToFile(directory + "Select" + className + ".jsx", content);
                System.out.println("Fichier créé avec succès : " + directory + className + ".jsx");
            } catch (IOException e) {
                System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du composant : " + e.getMessage());
            throw e; // Propagez l'exception pour la gestion à un niveau supérieur si nécessaire
        }

        return directory;
    }

    /*public String generateService(Entity entity, String projectName) throws IOException {
        // Chemin complet du répertoire service
        String serviceDirectory = projectName + "/services/";

        // Créer le répertoire service s'il n'existe pas
        creerDossier(serviceDirectory);

        // Chemin du fichier de contenu
        String contentFilePath = "D:\\GenesisV2_Framework_Psql\\Generateur\\GenesisV2_Framework_Psql\\template\\service.temp";

        // Lire le contenu du fichier
        String content;
        try {
            content = HandyManUtils.getFileContent(contentFilePath);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier de contenu : " +
                    e.getMessage());
            return null; // Ou lancez une exception si vous préférez
        }

        // Remplacement des placeholders dans le contenu
        content = content.replace("[classNameMaj]",
                HandyManUtils.majStart(entity.getClassName()));
        content = content.replace("[classNameMin]",
                HandyManUtils.minStart(entity.getClassName()));
        content = content.replace("[projectName]", projectName);

        String className = HandyManUtils.majStart(entity.getClassName());
        content = content.replace("[classNameMaj]", className);

        // Écriture du contenu dans le fichier
        try {
            writeToFile(serviceDirectory + className + "Service.jsx", content);
            System.out.println("Fichier créé avec succès : " + serviceDirectory +
                    className + ".jsx");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " +
                    e.getMessage());
            return null; // Ou lancez une exception si vous préférez
        }

        return serviceDirectory;
    }*/

    public void creerDossier(String serviceDirectory) {
        try {
            File directory = new File(serviceDirectory);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (created) {
                    System.out.println("Répertoire  créé avec succès.");
                } else {
                    throw new IOException("Erreur lors de la création du répertoire ");
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du répertoire : " + e.getMessage());
            return; // Ou lancez une exception si vous préférez
        }
    }

    public void writeToFile(String filePath, String content) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath); // Il manque le nom de fichier ici
            writer.write(content);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

<<<<<<< Updated upstream:GenesisV2_Framework_Psql/src/genesis/Language.java
    // santatra
        // generation view
        public String lireFichier(String path) throws Exception {

            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder content = new StringBuilder();
            String line;
    
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
    
            return String.valueOf(content);
        }
    
        public void generateViewUpdate(Entity entity ) throws IOException , Exception {
            String file = Constantes.UPDATE_VIEW_TEMPLATE;
            String template = lireFichier(file);
            String nomTable = HandyManUtils.majStart(entity.getTableName());
            String nomForm = "";
            String condition = "";
            String nomTableMn = HandyManUtils.minStart(nomTable);
            String updateForm = "";
            String foreign = " "; 
            String nomFichier = "Update/Update"+ nomTable + ".jsx";
            String importation = "";
    
            for(EntityField ef:entity.getFields()) {
                updateForm += this.creationChampColonneUpdate(ef);
                if(ef.isForeign() == true) {
                    importation += "import {select"+HandyManUtils.majStart(ef.getName())+"} from '../services/"+HandyManUtils.majStart(ef.getName())+"Service';";
                    foreign += this.generateFonctionForeign(ef.getName(),HandyManUtils.minStart(ef.getName()));
                }
            }
            for(int i=1;i<entity.getFields().length;i++) {
                nomForm   += "\""+entity.getFields()[i].getName()+"\": props."+entity.getFields()[i].getName()+", \n";
                condition += this.generateCondition(entity.getFields()[i].getName());
            }
            template = template.replace("[formData]",nomForm);
            template = template.replace("[condition]",condition);
            template = template.replace("[nomTableMj]",nomTable);
            template = template.replace("[nomTableMn]",nomTableMn);
            template = template.replace("[inputUpdate]",updateForm);
            template = template.replace("[foreign]", foreign);
            template = template.replace("[importForeign]",importation);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
                writer.write(template);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        public String generateFonctionForeign(String nomTable,String nomTableMn) {
            String foreign = "";
            foreign += "    const ["+nomTableMn+"s, set"+nomTable+"s] = useState([]); \n";
            foreign += "        useEffect(() => { \n";
            foreign += "            const fetchData = async () => { \n";
            foreign += "            const "+nomTableMn+"sData = await select"+nomTable+"(); \n";
            foreign += "            set"+nomTable+"s("+nomTableMn+"sData); \n";
            foreign += "        }; \n";
            foreign += "        fetchData(); \n";
            foreign += "    }, []); \n";
            return foreign;
        }
    
        public String generateCondition(String fields) {
            String condition = "";
            condition += "  if(formData."+fields+" != null) {"+"\n";
            condition += "      formDataUpdate.append( \""+fields+"\", formData."+fields+")"+"\n";
            condition += "  }else{ \n";
            condition += "      formDataUpdate.append( \""+fields+"\", props."+fields+")"+"\n";
            condition += "  } \n";
            return condition;
        }
    
        public String creationChampColonneUpdate(EntityField ef) throws Exception {
            Generateur g = new Generateur();
            String label = ef.getName();
            String inputUpdate = "";
            String foreign = "";
            if(ef.isPrimary() == true) {
                return inputUpdate;
            }else if(ef.isForeign() == false) {
                String type = g.getRemplacement().get(ef.getType());
                inputUpdate += this.generateInputeUpdateSimple(label,type);
            }else {
                String label2 = ef.getReferencedField().split("id")[1];
                inputUpdate += this.generateSelectUpdateForeign(label2,label);
            }
            return inputUpdate;
        }
    
        public String generateInputeUpdateSimple(String label,String type) {
            return "<label>" + label + ":\n        <input name='"+label+"' type='"+type+"' values={formData."+label+"} onChange={handleInputChange} /> \n </label> \n";
        }
    
        public String generateSelectUpdateForeign(String label2,String label) {
            String select = "";
            String foreign = " ";
            foreign += "{"+label+"s.map(("+label+",index) => ( \n";
            foreign +=          "<option key={index} value={"+label+".id"+label+"}> {"+label+".nom"+label+"}</option> \n";
            foreign += "))}";
            select += "<label> \n" + label2 + ":\n            <select name='"+label+"'> \n   "+ foreign
            +"\n          </select> \n </label> \n";
            return select;
        }
    
        // generation Service
        public void generateService(Entity entity) throws IOException,Exception {
            // String template = lireFichier("D:/S5/GenesisV2_Framework_Psql/bin/data_genesis/flamework/Service/ServiceTemplate.templ");
            String template = lireFichier(Constantes.SERVICE_TEMPLATE);
            String nomTable = HandyManUtils.majStart(entity.getTableName());
            String nomTableMn = HandyManUtils.minStart(nomTable);
            String nomFichier = "Services/"+nomTable+"Service"+".jsx";
            template = template.replace("[nomTableMj]",nomTable);
            template = template.replace("[nomTableMn]",nomTableMn);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
                writer.write(template);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        // generation API
        public void generateAPI(String nomProjet) throws IOException,Exception {
            // String template = lireFichier("D:/S5/GenesisV2_Framework_Psql/bin/data_genesis/flamework/Api/ApiTemplate.templ");
            String template = lireFichier(Constantes.API_TEMPLATE);
            template = template.replace("[nomProjet]",nomProjet);
            String nomFichier = "api/ApiUrl.jsx";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
                writer.write(template);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Fiderana
        public void generateInsertFile(Entity e)throws Exception{
            GenerateInsert g=new GenerateInsert();
            String input=GenerateInsert.lireFichier(getView().getInputTempl());
            String select=GenerateInsert.lireFichier(getView().getSelectTempl());
            String tempimportForeign=getView().getImportForeign();
            String tempforeignList=GenerateInsert.lireFichier(getView().getForeignListe());
            String tempFormData=GenerateInsert.lireFichier(getView().getFormdataAttribute());
            String insertTemp=GenerateInsert.lireFichier(getView().getInsertTempl());
            String temp=g.generateInsertView(e, select, input, tempFormData, tempimportForeign, tempforeignList, insertTemp);
            g.creationInsertion(temp, e);
        }
=======
     public void generateInsertFile(Entity e)throws Exception{
        GenerateInsert g=new GenerateInsert();
        String input=GenerateInsert.lireFichier(getView().getInputTempl());
        String select=GenerateInsert.lireFichier(getView().getSelectTempl());
        String tempimportForeign=getView().getImportForeign();
        String tempforeignList=GenerateInsert.lireFichier(getView().getForeignListe());
        String tempFormData=GenerateInsert.lireFichier(getView().getFormdataAttribute());
        String insertTemp=GenerateInsert.lireFichier(getView().getInsertTempl());
        String insertEntite=getView().getImportInsertEntite();
        System.out.println(insertEntite);
        String temp=g.generateInsertView(e, select, input, tempFormData, tempimportForeign, tempforeignList, insertTemp,insertEntite);
        g.creationInsertion(temp, e);
    }

>>>>>>> Stashed changes:GenesisV2_Framework_Psql - Copie (2)/src/genesis/Language.java
}
