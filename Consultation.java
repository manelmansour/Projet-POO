
public class Consultation {
	private String date;    //date de la consultation
	private String heure;    // heure de la consultation
	private String details;    //détail de la consultation
	private Medecin medecin;//le médecin associé à la consultatiion
	private Patient patient;


	public Consultation(String date, String heure, String details, Medecin medecin, Patient patient) {
		this.date = date;
		this.heure = heure;
		this.details = details;
		this.medecin = medecin;
		this.patient = patient;
	}
	//methode pour obtenir les details de  la consultation
	public String obtenirDetails() {
		return date +","+ heure +","+ medecin.getNom() +"," + patient.getNom()+","+details;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getDate() {
		return date;
	}
	public String getHeure() {
		return heure;
	}
	public String getDetails() {
		return details;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return  obtenirDetails() ;
	}
	

}
