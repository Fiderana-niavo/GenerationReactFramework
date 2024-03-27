package test.controllers;
import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRedirect;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;
import veda.godao.DAO;
import veda.godao.utils.DAOConnexion;
import java.sql.Connection;
import test.entities.Marque;

@Controller
@Singleton

public class MarqueController {
    private DAO dao=new DAO("vehiculegeneration", "localhost", "5432", "postgres", "p", false, 2);

    
    @URLMapping("insertmarque.do")
public ModelRest insert(ServletEntity entity) throws Exception{
    Marque o=new Marque();
    o.setNommarque(entity.getData().get("nommarque"));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        dao.insertWithoutPrimaryKey(connex, o);
        connex.commit();
        ModelRest model=new ModelRest();
        return model;
    }
}
@URLMapping("tocrudmarque.do")
public ModelRest crudpage(ServletEntity entity) throws Exception{
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        ModelRest model=new ModelRest();
        Marque[] o=dao.select(connex, Marque.class);
        model.addItem("o", o);
        
        return model;
    }
}
@URLMapping("updatemarque.do")
public ModelRest update(ServletEntity entity) throws Exception{
    Marque o=new Marque();
    o.setNommarque(entity.getData().get("nommarque"));
    Marque where=new Marque();
    where.setIdmarque(Integer.parseInt(entity.getData().get("idmarque")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        dao.update(connex, o, where);
        connex.commit();
        ModelRest model=new ModelRest();
        return model;
    }
}
@URLMapping("deletemarque.do")
public ModelRest delete(ServletEntity entity) throws Exception{
    Marque where=new Marque();
    where.setIdmarque(Integer.parseInt(entity.getData().get("idmarque")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        dao.delete(connex, where);
        connex.commit();
        ModelRest model=new ModelRest();
        return model;
    }
}

}

