


import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in); 
		CabinetMedical cabinet = new CabinetMedical();
		System.out.println("       			  Bienvenu dans le système cabinet medical !");
		System.out.println("1. Inscrire");
		System.out.println("2.Se connecter");
		System.out.println("3.Quitter");
		int choix;
		
			
			do {
			System.out.println("Veuillez choisir une option (1-3)");
		    choix = scanner.nextInt();
			}while(choix!=1 && choix!=2 && choix !=3);
		    scanner.nextLine();
		switch(choix) {
		case 1:
			System.out.println("            	   ***Inscription***");
			
			System.out.println("Entrez votre identifiant :  ");
			
			String identifiant = scanner.nextLine();
			
			if(cabinet.existeUtilisateur(identifiant)) {
				System.out.println("Erreur l'identifiant existe déjà. Veuillez vous connecter.");
				
			}
			else {
			System.out.println("Entrez le mot de passe : ");
			
			String motDePasse=scanner.nextLine();
			
			System.out.println("Entrez votre rôle ");
			System.out.println("1.Médecin ");
			System.out.println("2.Patient ");
			int role;
			do {
				System.out.println(" Veuillez entrer 1 ou 2 .");
			    role = scanner.nextInt();
			}while(role!=1 && role!=2);
			    scanner.nextLine();
			if(role==1) {
				System.out.println("Entrez votre nom : ");
			    
				String nom =scanner.nextLine();
				System.out.println("Entrez votre prénom : ");
				
				String prenom = scanner.nextLine();
				
				System.out.println("Entrez votre spécialité : ");
				String specialite = scanner.nextLine();
				
				Medecin medecin= new Medecin(identifiant, motDePasse , nom, prenom, specialite );
				cabinet.enregistrerMedecin(medecin);
				System.out.println("utilisateur enregistrer:" + medecin);
				break;
			}
			else if(role==2) {
				System.out.println("Entrez votre nom : ");				
				String nom = scanner.nextLine();				
				System.out.println("Entrez votre prénom : ");				
				String prenom = scanner.nextLine();
				System.out.println("Entrez votre numéro de téléphone : ");				
				String NumeroDeTelephone= scanner.nextLine();
				System.out.println("Entrez vous maladies chronique en détails : ");				
				String maladieChronique = scanner.nextLine();
                System.out.println("Entrez vous antécédents médicaux en détails: ");                
				String antecedentMedicaux = scanner.nextLine();
				System.out.println("Entrez votre poids : ");
				double poids = scanner.nextDouble();
				System.out.println("Entrez votre taille : ");
				double taille = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Entrez votre date de naissance (aaaa-mm-jj) : ");			
				String DateDeNaissance = scanner.nextLine();
				Patient patient = new Patient(identifiant, motDePasse, nom, prenom,maladieChronique, 
						 NumeroDeTelephone,antecedentMedicaux,taille, poids, DateDeNaissance);
				cabinet.enregistrerPatient(patient);
				System.out.println("utilisateur enregistré:" + patient);			    
			}else {
				System.out.println("Rôle invalide !");				
			}
			}
			break;
		case 2:
			System.out.println("            	   ***Connexion***");
			System.out.println("Entrez votre identifiant :  ");
			String Identifiant = scanner.nextLine();
			System.out.println("Entrez votre mot de passe : ");
			
			String MotDePasse = scanner.nextLine();
			Utilisateur utilisateurConnecte= cabinet.SeConnecter(Identifiant, MotDePasse);
			if(utilisateurConnecte != null) {
				System.out.println("Connexion réussi !");
				System.out.println("Bienvenu, "+ utilisateurConnecte);
				//action specifique apres connexion
				if(utilisateurConnecte instanceof Medecin) {
					Medecin medecinConnecte = (Medecin) utilisateurConnecte;
					System.out.println("Vous êtes connecté en tant que médecin . ");
				    gererMedecin(scanner, cabinet, medecinConnecte);					
				}else if(utilisateurConnecte instanceof Patient) {
					Patient patientConnecte = (Patient) utilisateurConnecte;
					System.out.println("Vous êtes connecté en tant que patient . ");
					gererPatient(scanner, cabinet, patientConnecte);					
				}				
			}else {
				System.out.println("Identifiant ou mot de passe incorrect. Veuillez ressayer.");				
			}
			break;
		case 3:
			System.out.println("Merci d'avoir utilisé le systéme de cabinet médical. Au revoir !");
			break;
		}
		scanner.close();
	}
	public static void gererMedecin(Scanner scanner, CabinetMedical cabinet , Medecin medecin ) {
		System.out.println(" 					***Menu médecin*** ");
		System.out.println("1.Consulter les rendez-vous ");
		System.out.println("2.Ajouter un rendez-vous");
		System.out.println("3.Modifier un rendez-vous");
		System.out.println("4.Supprimer un rendez-vous");
		System.out.println("5.Ajouter une consultation");
		System.out.println("6.Modifier une consultation");
		System.out.println("7.Modifier mes informations");
		System.out.println("8.Supprimer mon compte");
		int choix;
		do {
			System.out.println("Vueillez choisir une option : ");
			choix=scanner.nextInt();
		}while(choix!=1 && choix!=2 && choix !=3&&choix!=4 && choix!=5 && choix !=6 && choix!=7 && choix!=8);
			scanner.nextLine();
		switch(choix) {
		case 1://consutlter le rendez-vous
			System.out.println("       		   --- Liste des rendez-vous ---");
			cabinet.afficheRendezVousMedecin(medecin.getNom());
			
			break;
		case 2://ajouter un rendez-vous
			System.out.println("  				   --- Ajouter un Rendez-Vous ---");
			System.out.println("Entrez la date du rendez-vous (aaaa-mm-jj): ");
			String dateRDV= scanner.nextLine();
			
			System.out.println("Entrez l'heure du rendez-vous (aaaa-mm-jj): ");
			String heureRDV= scanner.nextLine();
			
			System.out.println("Entrez le nom du patient : ");
			String nomPatient= scanner.nextLine();
		
			Patient patient = cabinet.getPatientParNom(nomPatient);
			if(patient != null) {
				RendezVous rdv = new RendezVous(dateRDV, heureRDV, medecin, patient);
				System.out.println("Rendez-vous ajouté avec succés !");
				cabinet.enregistrerRendezVous(rdv);
			}else {
				System.out.println("Patient non trouvé. Impossible d'ajouter le rendez-vous. ");
			}
			break;
		case 3://modifier un rendez-vous
			System.out.println("     			---Modifier un Rendez-Vous --- ");
			System.out.println("Entrez le nom du patient : ");
			String patientName= scanner.nextLine();
			
			System.out.println("Entrez la date actuelle de rendez-vous (aaaa-mm-jj : ");
			String ancienneDate= scanner.nextLine();
			
			System.out.println("Entrez l'heure actuelle de rendez-vous : ");
			String ancienneHeure =  scanner.nextLine();
			
		
				System.out.println("Enttrez la nouvelle date de Rendez-Vous (aaaa-mm=jj) : ");
				String nouvelleDate= scanner.nextLine();
				
				System.out.println("Entrez la nouvelle heure de Rendez-Vous : ");
				String nouvelleHeure= scanner.nextLine();
				
				cabinet.modifierRendezVous(patientName, ancienneDate, ancienneHeure, nouvelleDate, nouvelleHeure);
				System.out.println("Rendez-Vous modifié avec succés !");
			
			break;
		case 4://supprimer un rendez-vous
			System.out.println(" 				---Supprimer un Rendez-Vous---");
			System.out.println("Entrez le nom du patient : ");
			String Name = scanner.nextLine();
			
			System.out.println("Entrez la date du Rendez-Vous à supprimer (aaaa-mm-jj) : ");
			String SupprimerDate = scanner.nextLine();
			
			System.out.println("Entrez l'heure du Rendez-Vous à supprimer : ");
			String SupprimerHeure= scanner.nextLine();
			
				cabinet.supprimerRendezVous(Name, SupprimerDate, SupprimerHeure);
				
			
			break;
		case 5://ajouter une consultation
			System.out.println("  				   --- Ajouter une consultation---");
			System.out.println("Entrez la date du consultation (aaaa-mm-jj): ");
			String dateConsult= scanner.nextLine();
			
			System.out.println("Entrez l'heure du consultation (aaaa-mm-jj): ");
			String heureConsult= scanner.nextLine();
			
			System.out.println("Entrez le nom du patient : ");
			String NomPatient= scanner.nextLine();
			System.out.println("Entrez les details de la consultation : ");
			String detail= scanner.nextLine();
		
			Patient p = cabinet.getPatientParNom(NomPatient);
			if(p != null) {
				Consultation rdv = new Consultation(dateConsult, heureConsult,detail, medecin, p);
				System.out.println("Consultation ajoutée avec succés !");
				cabinet.enregistrerConsultation(rdv);
			}else {
				System.out.println("Patient non trouvé. Impossible d'ajouter la consultation. ");
			}
			break;
		case 6://modifier une consultation
			System.out.println(" 				---Modifier une consultation--- ");
			System.out.println("Entrez le nom du patient : ");
			String NamePatient = scanner.nextLine();
			System.out.println("Entrez la date du consultation  (aaaa-mm-jj) : ");
			String dateConsultation = scanner.nextLine();
			
				System.out.println("Entrez les nouveaux détails du consultation : ");
				String details = scanner.nextLine();
				cabinet.modifierConsultation(NamePatient, dateConsultation, details);
				System.out.println("Détails ajouté avec succés !");
			
			break;
		case 7:// modifier les informations d'un medecin
			System.out.println("    			--- Modifier mes informations --- ");
			System.out.println("1.Modifier mon nom");
			System.out.println("2.Modifier mon prenom");
			System.out.println("3.Modifier ma spécialité");
			System.out.println("4.Modifier mon mot de passe");
			int c;
			do {
				System.out.println("Veuillez entrer une option : ");
			    c= scanner.nextInt();
			}while(c!=1 && c!=2 && c !=3&&c!=4 );
			scanner.nextLine();
			switch(c) {
			case 1:
				System.out.println("Entrez votre nom : ");
				String nom = scanner.nextLine();
				medecin.setNom(nom);
				System.out.println("Le nom est modifié avec succés ! ");
				
				break;
			case 2:
				System.out.println("Entrez votre prenom : ");
				String prenom = scanner.nextLine();
				medecin.setPrenom(prenom);
				System.out.println("Le prenom est modifié avec succés ! ");
				break;
			case 3:
				System.out.println("Entrez votre spécialité: ");
				String specialite = scanner.nextLine();
				medecin.setSpecialite(specialite);
				System.out.println("La spécialité est modifié avec succés ! ");
				break;
			case 4:
				System.out.println("Entrez votre mot de passe : ");
				String mdp = scanner.nextLine();
				medecin.setMotDePasse(mdp);
				System.out.println("Le mot de passe est modifié avec succés ! ");
				break;
			}
			break;
			
		case 8:// supprimer le compte de medecin
			cabinet.supprimerMedecin(medecin.getIdentifiant());
			break;
		
		
			
		}
		scanner.close();
		
	}
	public static void gererPatient(Scanner scanner, CabinetMedical cabinet, Patient patient) {
		CabinetMedical cabine = new CabinetMedical();
		System.out.println("     				***Menu patient***");
		System.out.println("1.Consulter mes Rendez-Vous ");
		System.out.println("2.Consulter mes Consultation ");
		System.out.println("3.Modifier mes information");
		System.out.println("4.Supprimer mon compte");
		int ch;
		do {
			System.out.println("Veuillez choisir une option : ");
		    ch = scanner.nextInt();
		}while(ch!=1 && ch!=2 && ch !=3 && ch!=4 );
		    scanner.nextLine();
		switch(ch) {
		case 1://le patient veut consulter ses Rendez-vous
			System.out.println("       		  ---Mes Rendez-Vous--- "	);
		    cabinet.afficheRendezVousPatient(patient.getNom());
			
			break;
		case 2://le patient veut consulter ses consultations
			System.out.println("      		  ---Mes consultations--- ");
		     cabinet.afficheConsultation(patient.getNom());

			break;
		case 3:
			System.out.println("  				---Modifier mes informations--- ");
			System.out.println("1.Modifier mon nom");
			System.out.println("2.Modifier mon prenom");
			System.out.println("3.Modifier mon numero de telephone");
			System.out.println("4.Modifier mes maladies chroniques");
			System.out.println("5.Modifier mes antecedents medecaux");
			System.out.println("6.Modifier ma taille");
			System.out.println("7.Modifier mon poids");
			System.out.println("8.Modifier le mot de passe ");
			
			int c;
			do {
				System.out.println("Veuillez choisir une option : ");
				c=scanner.nextInt();
			}while(c!=1 && c!=2 && c !=3&& c!=4 && c!=5 && c !=6 && c!=7 && c!=8);
				scanner.nextLine();
			switch(c) {
			case 1:
				System.out.println("Entrez votre nom : ");
				String Nom= scanner.nextLine();
				patient.setNom(Nom);
				System.out.println("Le nom est modifié avec succés ! ");
				break;
			case 2:
				System.out.println("Entrez votre prénom : ");
				String Prenom= scanner.nextLine();
				patient.setPrenom(Prenom);
				System.out.println("Le prénom est modifié avec succés ! ");
				break;
			case 3:
				System.out.println("Entrez votre numéro de téléphone : ");
				String Num =scanner.nextLine();
				patient.setNumeroDeTelephon(Num);
				System.out.println("Le numéro de téléphone est modifié avec succés ! ");
				break;
			case 4:
				System.out.println("Entrez vous maladies chroniques : ");
				String maladiesChroniques = scanner.nextLine();
				patient.setMaladieChronique(maladiesChroniques);
				System.out.println("Les maladies chroniques sont modifiés avec succés ! ");
				break;
			case 5:
				System.out.println("Entrez vous antecedents medicaux : ");
				String antecedentsMedicaux = scanner.nextLine();
				patient.setAntecedentsMedicaux(antecedentsMedicaux);
				System.out.println("Les antecedents medicaux sant modifiés avec succés ! ");
			case 6:
				System.out.println("Entrez votre taille : ");
				double taille = scanner.nextDouble();
				patient.setTaille(taille);
				System.out.println("La taille est modifiée avec succés ! ");
				break;
			case 7:
				System.out.println("Entrez votre poids : ");
				double poids = scanner.nextDouble();
				patient.setPoids(poids);
				System.out.println("Le poids est modifié avec succés ! ");
				break;
			case 8:
				System.out.println("Entrez votre mot de passe : ");
				String mdp= scanner.nextLine();
				patient.setMotDePasse(mdp);
				System.out.println("Le mot de passe est modifié avec succés ! ");
				break;
			}
			break;
		case 4:
		     cabine.supprimerPatient(patient.getIdentifiant());
			break;
			
		}
		scanner.close();
		
	}
	

}
