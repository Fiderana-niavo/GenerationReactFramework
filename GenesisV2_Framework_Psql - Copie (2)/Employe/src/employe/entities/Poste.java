package employe.entities;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("poste")

public class Poste {
    @PrimaryKey
@Column("idposte")
private Integer idposte;
public Integer getIdposte(){ return idposte; }
public void setIdposte(Integer o){ idposte=o; }
@Column("nomposte")
private String nomposte;
public String getNomposte(){ return nomposte; }
public void setNomposte(String o){ nomposte=o; }

    public Poste(){}
public Poste(Integer o){ idposte=o; }

}

