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
import employe.entities.Departement;

@Controller
@Singleton

public class DepartementController {
    private DAO dao=new DAO("testgeneration", "localhost", "5432", "postgres", "postgres", false, 2);

    
    @URLMapping("insertdepartement.do")
public ModelRedirect insert(ServletEntity entity) throws Exception{
    Departement o=new Departement();
    o.setNomdepartement(entity.getData().get("nomdepartement"));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.insertWithoutPrimaryKey(connex, o);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocruddepartement.do");
        return model;
    }
}
@URLMapping("tocruddepartement.do")
public ModelView crudpage(ServletEntity entity) throws Exception{
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        ModelView model=new ModelView();
        model.setView("pages/layout/layout.jsp");
        Departement[] o=dao.select(connex, Departement.class);
        model.addItem("viewpage", "departement.jsp");
        model.addItem("title", "Departement");
        model.addItem("o", o);
        
        return model;
    }
}
@URLMapping("updatedepartement.do")
public ModelRedirect update(ServletEntity entity) throws Exception{
    Departement o=new Departement();
    o.setNomdepartement(entity.getData().get("nomdepartement"));
    Departement where=new Departement();
    where.setIddepartement(Integer.parseInt(entity.getData().get("iddepartement")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.update(connex, o, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocruddepartement.do");
        return model;
    }
}
@URLMapping("deletedepartement.do")
public ModelRedirect delete(ServletEntity entity) throws Exception{
    Departement where=new Departement();
    where.setIddepartement(Integer.parseInt(entity.getData().get("iddepartement")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.delete(connex, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocruddepartement.do");
        return model;
    }
}

}

