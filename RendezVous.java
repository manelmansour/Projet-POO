
public class RendezVous {
	private String date;
	private String heure;
	private Medecin medecin;
	private Patient patient;
	public RendezVous(String date, String heure, Medecin medecin, Patient patient) {
		this.date = date;
		this.heure = heure;
		this.medecin = medecin;
		this.patient = patient;
	} 
	//methode peur obtenir les details de rendez-vous
	public String obtenirDetails() {
		return  date + ","+ heure+ ","+ patient.getNom()+ ","+ medecin.getNom();		 
	}
	public String getDate() {
		return date;
	}
	public String getHeure() {
		return heure;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public Patient getPatient() {
		return patient;
	}
	@Override
	public String toString() {
		return  obtenirDetails() ;
	}

	
	


}
