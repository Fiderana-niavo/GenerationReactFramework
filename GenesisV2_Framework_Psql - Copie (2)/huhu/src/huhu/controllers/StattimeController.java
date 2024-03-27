package huhu.controllers;

import java.sql.Connection;

import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import huhu.entities.Stattime;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;

@Controller
@Singleton

public class StattimeController {
    private DAO dao = new DAO("testdb", "localhost", "5432", "postgres", "2003", false, 2);

    @URLMapping("insertstattime.do")
    public ModelRedirect insert(ServletEntity entity) throws Exception {
        Stattime o = new Stattime();
        o.setMatchid(Integer.parseInt(entity.getData().get("matchid")));
        o.setPlayer(Integer.parseInt(entity.getData().get("player")));
        o.setShottime(entity.getData().get("shottime"));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.insertWithoutPrimaryKey(connex, o);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudstattime.do");
            return model;
        }
    }

    @URLMapping("tocrudstattime.do")
    public ModelView crudpage(ServletEntity entity) throws Exception {
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            ModelView model = new ModelView();
            model.setView("pages/layout/layout.jsp");
            Stattime[] o = dao.select(connex, Stattime.class);
            model.addItem("viewpage", "stattime.jsp");
            model.addItem("title", "Stattime");
            model.addItem("o", o);

            return model;
        }
    }

    @URLMapping("updatestattime.do")
    public ModelRedirect update(ServletEntity entity) throws Exception {
        Stattime o = new Stattime();
        o.setMatchid(Integer.parseInt(entity.getData().get("matchid")));
        o.setPlayer(Integer.parseInt(entity.getData().get("player")));
        o.setShottime(entity.getData().get("shottime"));
        Stattime where = new Stattime();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.update(connex, o, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudstattime.do");
            return model;
        }
    }

    @URLMapping("deletestattime.do")
    public ModelRedirect delete(ServletEntity entity) throws Exception {
        Stattime where = new Stattime();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.delete(connex, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudstattime.do");
            return model;
        }
    }

}
