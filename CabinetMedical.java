import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class CabinetMedical {

	private List<Utilisateur> utilisateurs;//liste des utilisateurs du cabinet (medecind et patient)
	private List<Medecin> medecins;//liste des medecins
	private List<Patient> patients;
	private List<RendezVous> rendezVous;  // liste des rendez-vous planifiés
	private List<Consultation> consultations;
	private static final String MEDECIN_FILE ="medecin.txt";
	private static final String PATIENT_FILE ="patient.txt";
	private static final String RENDEZVOUS_FILE ="rendezVous.txt";
	private static final String CONSULTATION_FILE ="consultation.txt";


	public CabinetMedical() {
		utilisateurs = new ArrayList<>();
		rendezVous= new ArrayList<>();
		consultations= new ArrayList<>();
	
	
	}
	
    //methode pour enregistrer les medecins
    public void  enregistrerMedecin(Medecin medecin) {
    			try(BufferedWriter writer = new BufferedWriter(new FileWriter(MEDECIN_FILE, true))){ 
    				writer.write(medecin.toString());
    	    		writer.newLine();
    	    		}catch(IOException e) {
    		System.out.println("Erreur lors de l'enregistrement du medecin.");
    		e.printStackTrace();
    	}
    }
    //methode pour enregistrer les medecins
    public void  enregistrerPatient(Patient patient) {
    			try(BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE, true))){ 
    				writer.write(patient.toString());
    	    		writer.newLine();
    	    		}catch(IOException e) {
    		System.out.println("Erreur lors de l'enregistrement du medecin.");
    		e.printStackTrace();
    	}
    }	

    
    //methode pour charger les medecins
    public  void chargerUtilisateur(){
    	
    	try{
    		BufferedReader reader= new BufferedReader(new FileReader(MEDECIN_FILE));
    		String ligne;
    		while((ligne= reader.readLine()) !=null) {
    			String []parts= ligne.split(",");
    			String nom = parts[0];
    			String prenom = parts[1];
    			String specialite = parts[2];
    			String identifiant = parts[3];
    			String motDePasse= parts[4];
    			if(parts.length==5) {
    			utilisateurs.add(new Medecin(identifiant, motDePasse,nom , prenom, specialite));
    			}
    		}
    		reader.close();
    		reader= new BufferedReader(new FileReader(PATIENT_FILE));
    		
    		while((ligne= reader.readLine()) !=null) {
    			String []parts= ligne.split(",");
    			String nom = parts[0];
    			String prenom = parts[1];
    			String numDeTelephone = parts[2];
    			String maladiesChronique = parts[3];
    			String antecedentsMedicaux = parts[4];
    			double taille = Double.parseDouble(parts[5]);
    			double poids = Double.parseDouble(parts[6]);
    			String dateDeNaissance = parts[7];
    			String identifiant = parts[8];
    			String motDePasse = parts[9];
    			if(parts.length==10) {
    			utilisateurs.add(new Patient(identifiant, motDePasse, nom, prenom, numDeTelephone, maladiesChronique, antecedentsMedicaux,
    					taille, poids, dateDeNaissance));
    			}
    		}
    		reader.close();
    	}catch(IOException e) {
    		System.out.println("Erreur lors de la lecture des medecins .");
    		e.printStackTrace();
    	}
    	
    	
    }


    //methode pour verifier si l'utilisateur existe déjà
	public boolean existeUtilisateur( String identifiant) {
		File fichierMedecin = new File ("medecin.txt");
		if(fichierMedecin.exists()) {
			try(BufferedReader reader = new BufferedReader(new FileReader(fichierMedecin))){
				String ligne;
				while((ligne = reader.readLine())!= null) {
					String[] data= ligne.split(",");
					if(data.length>4 && data[0].equals(identifiant)) {
						return true;
					}
				}
			}catch(IOException e) {
				System.out.println("Erreur lors de la lecture du fichier.");
			}
		}
		File fichierPatient = new File ("patient.txt");
		if(fichierPatient.exists()) {
			try(BufferedReader reader = new BufferedReader(new FileReader(fichierPatient))){
				String ligne;
				while((ligne = reader.readLine())!= null) {
					String[] data= ligne.split(",");
					if(data.length>9 && data[8].equals(identifiant)) {
						return true;
					}
				}
			}catch(IOException e) {
				System.out.println("Erreur lors de la lecture du fichier.");
			}
		}
		return false;
	}
	public Utilisateur rechercherUtilisateurDansFichier(String nomFichier, String identifiant, String motDePasse) {
		try(BufferedReader reader= new BufferedReader(new FileReader(nomFichier))){
			String ligne;
			while((ligne = reader.readLine())!= null) {
				String[] element= ligne.split(",");
				if(element.length>=2) {
					if(nomFichier.equals("medecin.txt")&&  element[0].equals(identifiant)&& element[1].equals(motDePasse)) {
						return new Medecin(element[0], element[1],element[2],element[3],element[4]);
					}else if(nomFichier.equals("patient.txt") && element[8].equals(identifiant)&& element[9].equals(motDePasse)) {
						return new Patient(element[8],element[9],element[0],element[1],element[2],element[3],element[7],
								Double.parseDouble(element[5]),Double.parseDouble(element[6]),element[4]);
					}
				}
			}
		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture de fichier !");
			e.printStackTrace();
		}
		return null;
	}

	//methode pour se connecter (utilisateur, medecin)
	public Utilisateur SeConnecter(String identifiant, String motDePasse) {
		if(MEDECIN_FILE != null) {
		Utilisateur utilisateurConnecte= rechercherUtilisateurDansFichier("medecin.txt", identifiant, motDePasse);
		if(utilisateurConnecte!= null) {
			return utilisateurConnecte;
		}
		}
		if(PATIENT_FILE != null) {
		Utilisateur utilisateurConnecte= rechercherUtilisateurDansFichier("patient.txt",identifiant, motDePasse);
		if(utilisateurConnecte!= null) {
			return utilisateurConnecte;
		}
		}
		return null;
	}
	//methode pour enregistrer les Rendez-Vous
	public void enregistrerRendezVous(RendezVous rdv) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(RENDEZVOUS_FILE, true))){
			writer.write(rdv.toString());
			writer.newLine();
		}catch(IOException e) {
			System.out.println("Rendez-Vous enregistré avec succés !");
			e.printStackTrace();
		}
	}
	//methode pour charger les Rendez vous
	public void chargerRendezVous(){
		
		try(BufferedReader reader = new BufferedReader(new FileReader(RENDEZVOUS_FILE))){
			String ligne;
			while((ligne= reader.readLine())!= null) {
				String[] parts = ligne.split(",");
				String date = parts[0];
				String heure = parts[1];
				String identifiantMedecin = parts[2];
				String identifiantPatient = parts[3];
				Medecin medecin = (Medecin)getUtilisateur(identifiantMedecin);
				Patient patient = (Patient)getUtilisateur(identifiantPatient);
				if(medecin != null && patient!= null) {
					rendezVous.add(new RendezVous(date, heure, medecin, patient));
				}
			}

		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture des rendez-vous.");
			e.printStackTrace();
		}
	}
	//methode pour enregistrer les consultation
	public void enregistrerConsultation(Consultation consultation) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(CONSULTATION_FILE, true))){
			String ligne =consultation.toString();
			writer.write(ligne);
			writer.newLine();
			
		}catch(IOException e) {
			System.out.println("Erreur lors de l'enregistrement de la consultation.");
			e.printStackTrace();
		}
	}
	//methode pour charger les consultation
	public void chargerConsultation(){
		
		try(BufferedReader reader = new BufferedReader(new FileReader(CONSULTATION_FILE))){
			String ligne;
			while((ligne = reader.readLine())!= null) {
				String[] parts= ligne.split(",");
				String date = parts[0];
				String heure = parts[1];
				String details = parts[2];
				String identifiantMedecin = parts[3];
				String identifiantPatient = parts[4];
				Medecin medecin = (Medecin)getUtilisateur(identifiantMedecin);
				Patient patient = (Patient)getUtilisateur(identifiantPatient);
				if(medecin != null && patient != null) {
					consultations.add(new Consultation(date, heure, details, medecin, patient));
				}
			}
		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture des consultations. ");
			e.printStackTrace();
		}
		
	}
	// obtenir medecin par nom
	public Medecin getMedecinParNom(String Nom) {
		try(Scanner scanner = new Scanner(new File(MEDECIN_FILE))){
			
			while(scanner.hasNextLine()) {
				String ligne= scanner.nextLine();
				String[] part= ligne.split(",");
				if(part.length>=5) {
					String nomMedecin = part[2];
					String identifiant = part[0];
					String motDePasse = part[1];
					if(nomMedecin.equalsIgnoreCase(Nom)) {
						String prenom = part[3];
						String specialite = part[4];
						
						return new Medecin(identifiant, motDePasse,nomMedecin, prenom, specialite);
					}
				}
			}
		}catch(FileNotFoundException e) {
			System.out.println("Le fichier medecin.txt introuvable");
		}
		System.out.println("Medecin introuvable.");
		return null;
	}
	public Patient getPatientParNom(String Nom) {
		try(Scanner scanner = new Scanner(new File(PATIENT_FILE))){
			
			while(scanner.hasNextLine()) {
				String ligne= scanner.nextLine();
				String[] part= ligne.split(",");
				if(part.length>=10) {
					String nomPatient = part[0];
					String identifiant = part[8];
					String motDePasse = part[9];
					if(nomPatient.equalsIgnoreCase(Nom)) {
						String prenom = part[1];
						String numTel = part[2];
						String maladieChronique = part[3];
						String dateDeNaissance = part[4];
						double taille = Double.parseDouble(part[5]);
						double poids = Double.parseDouble(part[6]);
						String antecedent = part[7];
						return new Patient(identifiant, motDePasse,nomPatient, prenom, numTel,
								maladieChronique, antecedent, taille, poids, dateDeNaissance);
					}
				}
			}
		}catch(FileNotFoundException e) {
			System.out.println("Le fichier patient.txt introuvable");
		}
		System.out.println("Patient introuvable.");
		return null;
	}



	public void modifierRendezVous(String nomPatient, String dateActuelle, String heureActuelle , String nouvelleDate, String nouvelleHeure) {
		List<String> lignes = new ArrayList<>();
		boolean trouve = false;
		try(BufferedReader reader = new BufferedReader(new FileReader(RENDEZVOUS_FILE))){
			String ligne;
			while((ligne = reader.readLine())!= null) {
				String[] part = ligne.split(",");
				if(part[2].equalsIgnoreCase(nomPatient)&& part[0].equalsIgnoreCase(dateActuelle) && part[1].equalsIgnoreCase(heureActuelle)) {
					lignes.add(nouvelleDate+","+nouvelleHeure+ ","+part[2]+","+ part[3]);
					trouve=true;
				}else {
					lignes.add(ligne);
				}
			}
		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture de fichier.");
		}
		if(trouve) {
		    try(BufferedWriter writer= new BufferedWriter(new FileWriter(RENDEZVOUS_FILE)) ){
		    	for(String ligne : lignes) {
		    		writer.write(ligne);
		    		writer.newLine();
		    	}
		    	
		    }catch(IOException e) {
		    	System.out.println("Erreur lors de l'ecriture.");
		    }
		}else {
			System.out.println("Rendez-Vous introuvable pour le patient "+nomPatient );
		}
	}


	public void supprimerRendezVous(String nomPatient, String date, String heure) {
		boolean trouve= false;
		File fichier = new File("rendezVous.txt");
		File temp = new File("rendezVousTMP.txt");
		try(BufferedReader reader= new BufferedReader(new FileReader(fichier));
				BufferedWriter writer = new BufferedWriter(new FileWriter(temp))){
			String ligne;
			while((ligne= reader.readLine())!= null) {
				String[] part = ligne.split(",");
				if(part[2].equals(nomPatient)&& part[0].equals(date)&& part[1].equals(heure)) {
					trouve= true;
				}else {
					writer.write(ligne);
					writer.newLine();
				}
			}
		
		if(trouve) {
			
			System.out.println("Rendez-Vous supprime avec succés !");
			
		}else {
			System.out.println("Rendez-Vous introuvable.");
		}
		}catch(IOException e) {
			System.out.println("Erreur lors de la suppression du Rendez-Vous.");
		}
		fichier.delete();
		temp.renameTo(fichier);
	}

	public void modifierConsultation(String nomPatient, String date, String nouveauxDetails) {
		List<String> lignes = new ArrayList<>();
		boolean trouve = false;
		try(BufferedReader reader = new BufferedReader(new FileReader(CONSULTATION_FILE))){
			String ligne;
			while((ligne = reader.readLine())!= null) {
				String[] part = ligne.split(",");
				if(part[3].equalsIgnoreCase(nomPatient)&& part[0].equalsIgnoreCase(date)) {
					lignes.add(part[0]+","+part[1]+ ","+part[2]+","+ part[3]+","+ nouveauxDetails);
					trouve=true;
				}else {
					lignes.add(ligne);
				}
			}
		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture de fichier.");
		}
		if(trouve) {
		    try(BufferedWriter writer= new BufferedWriter(new FileWriter(CONSULTATION_FILE)) ){
		    	for(String ligne : lignes) {
		    		writer.write(ligne);
		    		writer.newLine();
		    	}
		    	System.out.println("Consultation modifiee avec succes !");
		    }catch(IOException e) {
		    	System.out.println("Erreur lors de l'ecriture.");
		    }
		}else {
			System.out.println("Consultation introuvable pour le patient "+nomPatient );
		}
	}



	public void afficheRendezVousPatient(String nomPatient) {
		boolean trouve= false;
		try(BufferedReader reader = new BufferedReader(new FileReader(RENDEZVOUS_FILE))){
			String ligne;
			while((ligne= reader.readLine())!= null) {
				String [] part= ligne.split(",");
				if(part[2].equals(nomPatient)) {
				System.out.println("Medecin: "+ part[3]+" patient: "+ part[2]+" date:"+part[0]+ " heure: "+ part[1]);
				trouve = true;
				}
			}
		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture de fichier.");
		}
		if(!trouve) {
			System.out.println("Aucun rendez-vous planifie pour le patient "+nomPatient);
		}
	}
	public void afficheRendezVousMedecin(String nomMedecin) {
		boolean trouve= false;
		try(BufferedReader reader = new BufferedReader(new FileReader(RENDEZVOUS_FILE))){
			String ligne;
			while((ligne= reader.readLine())!= null) {
				String [] part= ligne.split(",");
				if(part[3].equalsIgnoreCase(nomMedecin)) {
				System.out.println("Medecin: "+ part[3]+" patient: "+ part[2]+" date:"+part[0]+ " heure: "+ part[1]);
				trouve = true;
				}
			}
		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture de fichier.");
		}
		if(!trouve) {
			System.out.println("Aucun rendez-vous planifie pour le medecin "+nomMedecin);
		}
	}
	public void afficheConsultation(String nomPatient) {
		boolean trouve= false;
		try(BufferedReader reader = new BufferedReader(new FileReader(CONSULTATION_FILE))){
			String ligne;
			while((ligne= reader.readLine())!= null) {
				String [] part= ligne.split(",");
				if(part[3].equalsIgnoreCase(nomPatient)) {
				System.out.println("Medecin: "+ part[2]+" patient: "+ part[3]+" date:"+part[0]+ " heure: "+ part[1]+ " details: "+part[4]);
				trouve = true;
				}
			}
		}catch(IOException e) {
			System.out.println("Erreur lors de la lecture de fichier.");
		}
		if(!trouve) {
			System.out.println("Aucune consultation pour le patient "+nomPatient);
		}
	}

	public void supprimerPatient(String identifiant) {
		File fichier = new File(PATIENT_FILE);
		File temp = new File("patientTMP.txt");
		try(BufferedReader reader= new BufferedReader(new FileReader(fichier));BufferedWriter writer = new BufferedWriter(new FileWriter(temp))){
			boolean trouve = false;
			String ligne;
			while((ligne=reader.readLine())!= null) {
				
				String[] parts= ligne.split(",");
				if(parts[8].equals(identifiant)) {
					trouve= true;
				}else {
					writer.write(ligne);
					writer.newLine();
				}
		    }
			if(!trouve) {
				
				System.out.println("Le patient introuvable.");
			}else {
				System.out.println("Le patient été supprimé.");
			}
	}catch(IOException e) {
		System.out.println("Erreur lors de lecture de fichier.");
	}
		if(fichier.delete()) {
			temp.renameTo(fichier);
		}else {
			System.out.println("Erreur impossible de supprimer le patient.");
		}
	}
	public void supprimerMedecin(String identifiant) {
		File file = new File(MEDECIN_FILE);
		File tempF = new File("medecinTMP.txt");
		try(BufferedReader reader= new BufferedReader(new FileReader(file));BufferedWriter writer = new BufferedWriter(new FileWriter(tempF))){
			boolean trouve = false;
			String ligne;
			while((ligne=reader.readLine())!= null) {
				
				String[] parts= ligne.split(",");
				if(parts[0].equals(identifiant)) {
					trouve= true;
				}else {
					writer.write(ligne);
					writer.newLine();
				}
		    }
			if(!trouve) {
				
				System.out.println("Le medecin introuvable.");
			}else {
				System.out.println("Le medecin été supprimé.");
			}
	}catch(IOException e) {
		System.out.println("Erreur lors de lecture de fichier.");
	}
		if(file.delete()) {
			tempF.renameTo(file);
		}else {
			System.out.println("Erreur impossible de supprimer le medecin.");
		}
	}
	
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	

	public List<Medecin> getMedecins() {
		return medecins;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public List<Consultation> getConsultations() {
		return consultations;
	}
	public List<RendezVous> getRendezVous() {
		return rendezVous;
	}
	public Utilisateur getUtilisateur(String identifiant) {
		for(Utilisateur utilisateur : utilisateurs) {
			if ( utilisateur.getIdentifiant().equals(identifiant)) {
				return utilisateur;
			}
		}
		return null;
	}
	
}

