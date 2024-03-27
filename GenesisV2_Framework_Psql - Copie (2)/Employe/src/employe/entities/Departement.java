package employe.entities;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("departement")

public class Departement {
    @PrimaryKey
@Column("iddepartement")
private Integer iddepartement;
public Integer getIddepartement(){ return iddepartement; }
public void setIddepartement(Integer o){ iddepartement=o; }
@Column("nomdepartement")
private String nomdepartement;
public String getNomdepartement(){ return nomdepartement; }
public void setNomdepartement(String o){ nomdepartement=o; }

    public Departement(){}
public Departement(Integer o){ iddepartement=o; }

}

