package huhu.controllers;

import java.sql.Connection;

import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import huhu.entities.Etudiant;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;

@Controller
@Singleton

public class EtudiantController {
    private DAO dao = new DAO("testdb", "localhost", "5432", "postgres", "2003", false, 2);

    @URLMapping("insertetudiant.do")
    public ModelRedirect insert(ServletEntity entity) throws Exception {
        Etudiant o = new Etudiant();
        o.setNumero(entity.getData().get("numero"));
        o.setNom(entity.getData().get("nom"));
        o.setAdmissionStatus(Integer.parseInt(entity.getData().get("admissionStatus")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.insertWithoutPrimaryKey(connex, o);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudetudiant.do");
            return model;
        }
    }

    @URLMapping("tocrudetudiant.do")
    public ModelView crudpage(ServletEntity entity) throws Exception {
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            ModelView model = new ModelView();
            model.setView("pages/layout/layout.jsp");
            Etudiant[] o = dao.select(connex, Etudiant.class);
            model.addItem("viewpage", "etudiant.jsp");
            model.addItem("title", "Etudiant");
            model.addItem("o", o);

            return model;
        }
    }

    @URLMapping("updateetudiant.do")
    public ModelRedirect update(ServletEntity entity) throws Exception {
        Etudiant o = new Etudiant();
        o.setNumero(entity.getData().get("numero"));
        o.setNom(entity.getData().get("nom"));
        o.setAdmissionStatus(Integer.parseInt(entity.getData().get("admissionStatus")));
        Etudiant where = new Etudiant();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.update(connex, o, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudetudiant.do");
            return model;
        }
    }

    @URLMapping("deleteetudiant.do")
    public ModelRedirect delete(ServletEntity entity) throws Exception {
        Etudiant where = new Etudiant();
        where.setId(Integer.parseInt(entity.getData().get("id")));
        try (Connection connex = DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432",
                "testdb", "postgres", "2003", false, false)) {
            dao.delete(connex, where);
            connex.commit();
            ModelRedirect model = new ModelRedirect("tocrudetudiant.do");
            return model;
        }
    }

}
