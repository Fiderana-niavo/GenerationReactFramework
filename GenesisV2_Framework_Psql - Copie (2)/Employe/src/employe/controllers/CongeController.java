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
import employe.entities.Conge;

@Controller
@Singleton

public class CongeController {
    private DAO dao=new DAO("testgeneration", "localhost", "5432", "postgres", "postgres", false, 2);

    
    @URLMapping("insertconge.do")
public ModelRedirect insert(ServletEntity entity) throws Exception{
    Conge o=new Conge();
    o.setDuree(Integer.parseInt(entity.getData().get("duree")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.insertWithoutPrimaryKey(connex, o);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudconge.do");
        return model;
    }
}
@URLMapping("tocrudconge.do")
public ModelView crudpage(ServletEntity entity) throws Exception{
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        ModelView model=new ModelView();
        model.setView("pages/layout/layout.jsp");
        Conge[] o=dao.select(connex, Conge.class);
        model.addItem("viewpage", "conge.jsp");
        model.addItem("title", "Conge");
        model.addItem("o", o);
        
        return model;
    }
}
@URLMapping("updateconge.do")
public ModelRedirect update(ServletEntity entity) throws Exception{
    Conge o=new Conge();
    o.setDuree(Integer.parseInt(entity.getData().get("duree")));
    Conge where=new Conge();
    where.setIdconge(Integer.parseInt(entity.getData().get("idconge")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.update(connex, o, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudconge.do");
        return model;
    }
}
@URLMapping("deleteconge.do")
public ModelRedirect delete(ServletEntity entity) throws Exception{
    Conge where=new Conge();
    where.setIdconge(Integer.parseInt(entity.getData().get("idconge")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.delete(connex, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudconge.do");
        return model;
    }
}

}

