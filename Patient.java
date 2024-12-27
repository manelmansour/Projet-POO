

public class Patient extends Utilisateur{
	private String Nom;
	private String Prenom;
	private String NumeroDeTelephon;
	private String maladieChronique;
	private String DateDeNaissance;
	private double taille;
	private double poids;
     
    private String antecedentsMedicaux;
    public Patient(String identifiant, String motDePasse, String nom, String prenom, String numeroTelephone,
    		 String maladieChronique,String antecedentsMedicaux,double taille, double poids,String DateDeNaissance) {
 super(identifiant, motDePasse, "Patient"); // Appel au constructeur de la classe parente.
 this.Nom = nom;
 this.Prenom = prenom;
 this.taille = taille;
 this.poids = poids;
 this.NumeroDeTelephon = numeroTelephone;
 this.maladieChronique= maladieChronique;
 this.antecedentsMedicaux = antecedentsMedicaux;
 this.DateDeNaissance= DateDeNaissance;
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
	public void setNom(String nom) {
		this.Nom = nom;
	}
	public void setPrenom(String prenom) {
		this.Prenom = prenom;
	}
	public void setNumeroDeTelephon(String numeroDeTelephon) {
		NumeroDeTelephon = numeroDeTelephon;
	}
	public void setMaladieChronique(String maladieChronique) {
		this.maladieChronique = maladieChronique;
	}
	public void setTaille(double taille) {
		this.taille = taille;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public void setAntecedentsMedicaux(String antecedentsMedicaux) {
		this.antecedentsMedicaux = antecedentsMedicaux;
	}
	public String getNom() {
		return Nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public String getNumeroDeTelephon() {
		return NumeroDeTelephon;
	}
	public String getMaladieChronique() {
		return maladieChronique;
	}


    public String getAntecedentsMedicaux() {
        return antecedentsMedicaux;
    }
	public double getTaille() {
		return taille;
	}
	public double getPoids() {
		return poids;
	}
	
	public String getDateDeNaissance() {
		return DateDeNaissance;
	}
	public void setDateDeNaissance(String dateDeNaissance) {
		this.DateDeNaissance = dateDeNaissance;
	}
	@Override 
	public String toString() {
		return   Nom + ","+ Prenom + ","  + NumeroDeTelephon
				+ "," + maladieChronique + "," + DateDeNaissance + ","
				+ taille + "," + poids + "," + antecedentsMedicaux +","+getIdentifiant() + "," + getMotDePasse();
	}
	@Override
	public void setMotDePasse(String motDePasse) {
		// TODO Auto-generated method stub
		super.setMotDePasse(motDePasse);
	}

	

}
