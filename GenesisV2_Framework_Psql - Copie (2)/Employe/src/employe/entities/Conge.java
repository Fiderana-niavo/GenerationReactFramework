package employe.entities;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("conge")

public class Conge {
    @PrimaryKey
@Column("idconge")
private Integer idconge;
public Integer getIdconge(){ return idconge; }
public void setIdconge(Integer o){ idconge=o; }
@Column("duree")
private Integer duree;
public Integer getDuree(){ return duree; }
public void setDuree(Integer o){ duree=o; }

    public Conge(){}
public Conge(Integer o){ idconge=o; }

}

