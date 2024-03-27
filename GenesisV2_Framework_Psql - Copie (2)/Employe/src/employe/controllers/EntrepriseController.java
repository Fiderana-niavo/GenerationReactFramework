package employe.controllers;
import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;
import java.sql.Connection;
import employe.entities.Entreprise;

@Controller
@Singleton

public class EntrepriseController {
    private DAO dao=new DAO("testgeneration", "localhost", "5432", "postgres", "postgres", false, 2);

    
    @URLMapping("insertentreprise.do")
public ModelRedirect insert(ServletEntity entity) throws Exception{
    Entreprise o=new Entreprise();
    o.setNomentreprise(entity.getData().get("nomentreprise"));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.insertWithoutPrimaryKey(connex, o);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudentreprise.do");
        return model;
    }
}
@URLMapping("tocrudentreprise.do")
public ModelView crudpage(ServletEntity entity) throws Exception{
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        ModelView model=new ModelView();
        model.setView("pages/layout/layout.jsp");
        Entreprise[] o=dao.select(connex, Entreprise.class);
        model.addItem("viewpage", "entreprise.jsp");
        model.addItem("title", "Entreprise");
        model.addItem("o", o);
        
        return model;
    }
}
@URLMapping("updateentreprise.do")
public ModelRedirect update(ServletEntity entity) throws Exception{
    Entreprise o=new Entreprise();
    o.setNomentreprise(entity.getData().get("nomentreprise"));
    Entreprise where=new Entreprise();
    where.setIdentreprise(Integer.parseInt(entity.getData().get("identreprise")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.update(connex, o, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudentreprise.do");
        return model;
    }
}
@URLMapping("deleteentreprise.do")
public ModelRedirect delete(ServletEntity entity) throws Exception{
    Entreprise where=new Entreprise();
    where.setIdentreprise(Integer.parseInt(entity.getData().get("identreprise")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.delete(connex, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudentreprise.do");
        return model;
    }
}

}

