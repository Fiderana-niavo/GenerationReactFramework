package huhu.controllers;

import java.sql.Connection;

import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import huhu.entities.Employe;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;

@Controller
@Singleton

public class EmployeController {
    private DAO dao = new DAO("testdb", "localhost", "5432", "postgres", "2003", false, 2);

    @URLMapping("insertemploye.do")
    public ModelRedirect insert(ServletEntity entity) throws Exception {
        Employe o = new Employe();
        o.setNom(entity.getData().get("nom"));
        o.setDirection(new huhu.entities.Direction(Integer.parseInt(entity.getData().get("direction"))));
        o.setCategory(new huhu.entities.Category(Integer.parseInt(entity.getData().get("category"))));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.insertWithoutPrimaryKey(connex, o);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudemploye.do");
            return model;
        }
    }

    @URLMapping("tocrudemploye.do")
    public ModelView crudpage(ServletEntity entity) throws Exception {
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            ModelView model = new ModelView();
            model.setView("pages/layout/layout.jsp");
            Employe[] o = dao.select(connex, Employe.class);
            model.addItem("viewpage", "employe.jsp");
            model.addItem("title", "Employe");
            model.addItem("o", o);
            huhu.entities.Direction[] direction = dao.select(connex, huhu.entities.Direction.class);
            model.addItem("directions", direction);
            huhu.entities.Employe[] employe = dao.select(connex, huhu.entities.Employe.class);
            model.addItem("employes", employe);
            huhu.entities.Category[] category = dao.select(connex, huhu.entities.Category.class);
            model.addItem("categorys", category);
            return model;
        }
    }

    @URLMapping("updateemploye.do")
    public ModelRedirect update(ServletEntity entity) throws Exception {
        Employe o = new Employe();
        o.setNom(entity.getData().get("nom"));
        o.setDirection(new huhu.entities.Direction(Integer.parseInt(entity.getData().get("direction"))));
        o.setCategory(new huhu.entities.Category(Integer.parseInt(entity.getData().get("category"))));
        Employe where = new Employe();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.update(connex, o, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudemploye.do");
            return model;
        }
    }

    @URLMapping("deleteemploye.do")
    public ModelRedirect delete(ServletEntity entity) throws Exception {
        Employe where = new Employe();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.delete(connex, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudemploye.do");
            return model;
        }
    }

}
