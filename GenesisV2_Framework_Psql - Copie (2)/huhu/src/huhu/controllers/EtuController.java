package huhu.controllers;

import java.sql.Connection;

import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import huhu.entities.Etu;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;

@Controller
@Singleton

public class EtuController {
    private DAO dao = new DAO("testdb", "localhost", "5432", "postgres", "2003", false, 2);

    @URLMapping("insertetu.do")
    public ModelRedirect insert(ServletEntity entity) throws Exception {
        Etu o = new Etu();
        o.setMlle(Integer.parseInt(entity.getData().get("mlle")));
        o.setNom(entity.getData().get("nom"));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.insertWithoutPrimaryKey(connex, o);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudetu.do");
            return model;
        }
    }

    @URLMapping("tocrudetu.do")
    public ModelView crudpage(ServletEntity entity) throws Exception {
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            ModelView model = new ModelView();
            model.setView("pages/layout/layout.jsp");
            Etu[] o = dao.select(connex, Etu.class);
            model.addItem("viewpage", "etu.jsp");
            model.addItem("title", "Etu");
            model.addItem("o", o);

            return model;
        }
    }

    @URLMapping("updateetu.do")
    public ModelRedirect update(ServletEntity entity) throws Exception {
        Etu o = new Etu();
        o.setMlle(Integer.parseInt(entity.getData().get("mlle")));
        o.setNom(entity.getData().get("nom"));
        Etu where = new Etu();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.update(connex, o, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudetu.do");
            return model;
        }
    }

    @URLMapping("deleteetu.do")
    public ModelRedirect delete(ServletEntity entity) throws Exception {
        Etu where = new Etu();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.delete(connex, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudetu.do");
            return model;
        }
    }

}
