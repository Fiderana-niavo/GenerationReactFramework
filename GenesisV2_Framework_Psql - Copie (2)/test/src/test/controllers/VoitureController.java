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
import test.entities.Voiture;

@Controller
@Singleton

public class VoitureController {
    private DAO dao=new DAO("vehiculegeneration", "localhost", "5432", "postgres", "p", false, 2);

    
    @URLMapping("insertvoiture.do")
public ModelRest insert(ServletEntity entity) throws Exception{
    Voiture o=new Voiture();
    o.setMarque(new test.entities.Marque(Integer.parseInt(entity.getData().get("marque"))));o.setNomvoiture(entity.getData().get("nomvoiture"));o.setDatasortie(java.time.LocalDate.parse(entity.getData().get("datasortie")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        dao.insertWithoutPrimaryKey(connex, o);
        connex.commit();
        ModelRest model=new ModelRest();
        return model;
    }
}
@URLMapping("tocrudvoiture.do")
public ModelRest crudpage(ServletEntity entity) throws Exception{
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        ModelRest model=new ModelRest();
        Voiture[] o=dao.select(connex, Voiture.class);
        model.addItem("o", o);
        test.entities.Marque[] marque=dao.select(connex, test.entities.Marque.class);
model.addItem("marques", marque);
        return model;
    }
}
@URLMapping("updatevoiture.do")
public ModelRest update(ServletEntity entity) throws Exception{
    Voiture o=new Voiture();
    o.setMarque(new test.entities.Marque(Integer.parseInt(entity.getData().get("marque"))));o.setNomvoiture(entity.getData().get("nomvoiture"));o.setDatasortie(java.time.LocalDate.parse(entity.getData().get("datasortie")));
    Voiture where=new Voiture();
    where.setIdvoiture(Integer.parseInt(entity.getData().get("idvoiture")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        dao.update(connex, o, where);
        connex.commit();
        ModelRest model=new ModelRest();
        return model;
    }
}
@URLMapping("deletevoiture.do")
public ModelRest delete(ServletEntity entity) throws Exception{
    Voiture where=new Voiture();
    where.setIdvoiture(Integer.parseInt(entity.getData().get("idvoiture")));
    try(Connection connex=DAOConnexion.getConnexion("org.postgresql.Driver", "postgresql", "localhost", "5432", "vehiculegeneration", "postgres", "p", false, false)){
        dao.delete(connex, where);
        connex.commit();
        ModelRest model=new ModelRest();
        return model;
    }
}

}

