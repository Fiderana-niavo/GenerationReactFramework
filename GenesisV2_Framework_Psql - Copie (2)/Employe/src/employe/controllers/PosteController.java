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
import employe.entities.Poste;

@Controller
@Singleton

public class PosteController {
    private DAO dao=new DAO("testgeneration", "localhost", "5432", "postgres", "postgres", false, 2);

    
    @URLMapping("insertposte.do")
public ModelRedirect insert(ServletEntity entity) throws Exception{
    Poste o=new Poste();
    o.setNomposte(entity.getData().get("nomposte"));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.insertWithoutPrimaryKey(connex, o);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudposte.do");
        return model;
    }
}
@URLMapping("tocrudposte.do")
public ModelView crudpage(ServletEntity entity) throws Exception{
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        ModelView model=new ModelView();
        model.setView("pages/layout/layout.jsp");
        Poste[] o=dao.select(connex, Poste.class);
        model.addItem("viewpage", "poste.jsp");
        model.addItem("title", "Poste");
        model.addItem("o", o);
        
        return model;
    }
}
@URLMapping("updateposte.do")
public ModelRedirect update(ServletEntity entity) throws Exception{
    Poste o=new Poste();
    o.setNomposte(entity.getData().get("nomposte"));
    Poste where=new Poste();
    where.setIdposte(Integer.parseInt(entity.getData().get("idposte")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.update(connex, o, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudposte.do");
        return model;
    }
}
@URLMapping("deleteposte.do")
public ModelRedirect delete(ServletEntity entity) throws Exception{
    Poste where=new Poste();
    where.setIdposte(Integer.parseInt(entity.getData().get("idposte")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.delete(connex, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudposte.do");
        return model;
    }
}

}

