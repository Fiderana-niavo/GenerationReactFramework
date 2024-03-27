package huhu.controllers;

import java.sql.Connection;

import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import huhu.entities.Category;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;

@Controller
@Singleton

public class CategoryController {
    private DAO dao = new DAO("testdb", "localhost", "5432", "postgres", "2003", false, 2);

    @URLMapping("insertcategory.do")
    public ModelRedirect insert(ServletEntity entity) throws Exception {
        Category o = new Category();
        o.setNom(entity.getData().get("nom"));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.insertWithoutPrimaryKey(connex, o);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudcategory.do");
            return model;
        }
    }

    @URLMapping("tocrudcategory.do")
    public ModelView crudpage(ServletEntity entity) throws Exception {
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            ModelView model = new ModelView();
            model.setView("pages/layout/layout.jsp");
            Category[] o = dao.select(connex, Category.class);
            model.addItem("viewpage", "category.jsp");
            model.addItem("title", "Category");
            model.addItem("o", o);

            return model;
        }
    }

    @URLMapping("updatecategory.do")
    public ModelRedirect update(ServletEntity entity) throws Exception {
        Category o = new Category();
        o.setNom(entity.getData().get("nom"));
        Category where = new Category();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.update(connex, o, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudcategory.do");
            return model;
        }
    }

    @URLMapping("deletecategory.do")
    public ModelRedirect delete(ServletEntity entity) throws Exception {
        Category where = new Category();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.delete(connex, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudcategory.do");
            return model;
        }
    }

}
