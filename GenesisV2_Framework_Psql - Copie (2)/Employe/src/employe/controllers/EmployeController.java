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
import employe.entities.Employe;

@Controller
@Singleton

public class EmployeController {
    private DAO dao=new DAO("testgeneration", "localhost", "5432", "postgres", "postgres", false, 2);

    
    @URLMapping("insertemploye.do")
public ModelRedirect insert(ServletEntity entity) throws Exception{
    Employe o=new Employe();
    o.setIdemploye(Integer.parseInt(entity.getData().get("idemploye")));o.setDatenaissance(java.time.LocalDate.parse(entity.getData().get("datenaissance")));o.setDepartement(new employe.entities.Departement(Integer.parseInt(entity.getData().get("departement"))));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.insertWithoutPrimaryKey(connex, o);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudemploye.do");
        return model;
    }
}
@URLMapping("tocrudemploye.do")
public ModelView crudpage(ServletEntity entity) throws Exception{
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        ModelView model=new ModelView();
        model.setView("pages/layout/layout.jsp");
        Employe[] o=dao.select(connex, Employe.class);
        model.addItem("viewpage", "employe.jsp");
        model.addItem("title", "Employe");
        model.addItem("o", o);
        employe.entities.Departement[] departement=dao.select(connex, employe.entities.Departement.class);
model.addItem("departements", departement);
        return model;
    }
}
@URLMapping("updateemploye.do")
public ModelRedirect update(ServletEntity entity) throws Exception{
    Employe o=new Employe();
    o.setIdemploye(Integer.parseInt(entity.getData().get("idemploye")));o.setDatenaissance(java.time.LocalDate.parse(entity.getData().get("datenaissance")));o.setDepartement(new employe.entities.Departement(Integer.parseInt(entity.getData().get("departement"))));
    Employe where=new Employe();
    where.setNomemploye(entity.getData().get("nomemploye"));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.update(connex, o, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudemploye.do");
        return model;
    }
}

@URLMapping("deleteemploye.do")
public ModelRedirect delete(ServletEntity entity) throws Exception{
    Employe where=new Employe();
    where.setNomemploye(entity.getData().get("nomemploye"));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "testgeneration", "postgres", "postgres", false, false)){
        dao.delete(connex, where);
        connex.commit();
        ModelRedirect model=new ModelRedirect("tocrudemploye.do");
        return model;
    }
}

}

