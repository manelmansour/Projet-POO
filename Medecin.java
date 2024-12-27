
public class Medecin extends Utilisateur {
	private String Nom;
	private String Prenom;
	private String specialite;
	public Medecin(String iD, String motDePasse, String nom, String prenom, String specialite) {
		super(iD, motDePasse, "madecin");
		Nom = nom;
		Prenom = prenom;
		this.specialite = specialite;
	}
	public String getNom() {
		return Nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public String getSpecialite() {
		return specialite;
	}

	@Override
	public String toString() {
		return   getIdentifiant() + "," + getMotDePasse()+","+ Nom +"," + Prenom + "," + specialite  ;
	}
	@Override
	public void setMotDePasse(String motDePasse) {
		// TODO Auto-generated method stub
		super.setMotDePasse(motDePasse);
	}
	public void setNom(String nom) {
		this.Nom = nom;
	}
	public void setPrenom(String prenom) {
		this.Prenom = prenom;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	@Override
	public String getIdentifiant() {
		// TODO Auto-generated method stub
		return super.getIdentifiant();
	}
	@Override
	public String getMotDePasse() {
		// TODO Auto-generated method stub
		return super.getMotDePasse();
	}
	
	

}
