package beans;

public class Fichier {

    private String description;
    private String nom;
    private String courtnom;

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

	public String getCourtNom() {
		return courtnom;
	}

	public void setCourtNom(String courtNom) {
		this.courtnom = courtNom;
	}
    
}