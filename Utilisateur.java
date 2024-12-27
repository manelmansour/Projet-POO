
public abstract class Utilisateur {
	
	private String identifiant;
	private String motDePasse;
	private String role;
	public Utilisateur(String iD, String motDePasse, String role) {
		
		this.identifiant = iD;
		this.motDePasse = motDePasse;
		this.role = role;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public String getRole() {
		return role;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	@Override
	public String toString() {
		return "Utilisateur [identifiant=" + identifiant + ", motDePasse=" + motDePasse + ", role=" + role + "]";
	}
	
	
	

}
