package employe.entities;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("employe")

public class Employe {
    @Column("idemploye")
private Integer idemploye;
public Integer getIdemploye(){ return idemploye; }
public void setIdemploye(Integer o){ idemploye=o; }
@PrimaryKey
@Column("nomemploye")
private String nomemploye;
public String getNomemploye(){ return nomemploye; }
public void setNomemploye(String o){ nomemploye=o; }
@Column("datenaissance")
private java.time.LocalDate datenaissance;
public java.time.LocalDate getDatenaissance(){ return datenaissance; }
public void setDatenaissance(java.time.LocalDate o){ datenaissance=o; }
@veda.godao.annotations.ForeignKey(recursive=true)
@Column("iddepartement")
private Departement departement;
public Departement getDepartement(){ return departement; }
public void setDepartement(Departement o){ departement=o; }

    public Employe(){}
public Employe(String o){ nomemploye=o; }

}

