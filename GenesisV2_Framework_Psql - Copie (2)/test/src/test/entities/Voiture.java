package test.entities;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("voiture")

public class Voiture {
    @PrimaryKey
@Column("idvoiture")
private Integer idvoiture;
public Integer getIdvoiture(){ return idvoiture; }
public void setIdvoiture(Integer o){ idvoiture=o; }
@veda.godao.annotations.ForeignKey(recursive=true)
@Column("idmarque")
private Marque marque;
public Marque getMarque(){ return marque; }
public void setMarque(Marque o){ marque=o; }
@Column("nomvoiture")
private String nomvoiture;
public String getNomvoiture(){ return nomvoiture; }
public void setNomvoiture(String o){ nomvoiture=o; }
@Column("datasortie")
private java.time.LocalDate datasortie;
public java.time.LocalDate getDatasortie(){ return datasortie; }
public void setDatasortie(java.time.LocalDate o){ datasortie=o; }

    public Voiture(){}
public Voiture(Integer o){ idvoiture=o; }

}

