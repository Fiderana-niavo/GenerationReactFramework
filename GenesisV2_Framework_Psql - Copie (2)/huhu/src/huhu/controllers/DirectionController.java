package huhu.controllers;

import java.sql.Connection;

import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import huhu.entities.Direction;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;

@Controller
@Singleton

public class DirectionController {
    private DAO dao = new DAO("testdb", "localhost", "5432", "postgres", "2003", false, 2);

    @URLMapping("insertdirection.do")
    public ModelRedirect insert(ServletEntity entity) throws Exception {
        Direction o = new Direction();
        o.setNom(entity.getData().get("nom"));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.insertWithoutPrimaryKey(connex, o);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocruddirection.do");
            return model;
        }
    }

    @URLMapping("tocruddirection.do")
    public ModelView crudpage(ServletEntity entity) throws Exception {
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            ModelView model = new ModelView();
            model.setView("pages/layout/layout.jsp");
            Direction[] o = dao.select(connex, Direction.class);
            model.addItem("viewpage", "direction.jsp");
            model.addItem("title", "Direction");
            model.addItem("o", o);

            return model;
        }
    }

    @URLMapping("updatedirection.do")
    public ModelRedirect update(ServletEntity entity) throws Exception {
        Direction o = new Direction();
        o.setNom(entity.getData().get("nom"));
        Direction where = new Direction();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.update(connex, o, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocruddirection.do");
            return model;
        }
    }

    @URLMapping("deletedirection.do")
    public ModelRedirect delete(ServletEntity entity) throws Exception {
        Direction where = new Direction();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.delete(connex, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocruddirection.do");
            return model;
        }
    }

}
