package ma.enset.studentapp;
import ma.enset.studentapp.entities.Patient;
import ma.enset.studentapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner {


	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// ajouter des patients dans la table PATIENTS

		patientRepository.save(new Patient(null,"moussa",new Date(),false,12));
		patientRepository.save(new Patient(null,"mustapha",new Date(),false,18));
		patientRepository.save(new Patient(null,"hajar",new Date(),false,13));
		patientRepository.save(new Patient(null,"oumaima",new Date(),false,116));
		patientRepository.save(new Patient(null,"youness",new Date(),false,90));
		patientRepository.save(new Patient(null,"hassan",new Date(),false,34));
		patientRepository.save(new Patient(null,"mahmod",new Date(),false,78));
		patientRepository.save(new Patient(null,"ayoub",new Date(),false,23));
		patientRepository.save(new Patient(null,"amin",new Date(),true,10));
		patientRepository.save(new Patient(null,"barik",new Date(),false,16));
		patientRepository.save(new Patient(null,"fatima",new Date(),false,72));
		patientRepository.save(new Patient(null,"hind",new Date(),false,128));
		patientRepository.save(new Patient(null,"mohamed",new Date(),true,513));
		patientRepository.save(new Patient(null,"yassin",new Date(),false,196));
		patientRepository.save(new Patient(null,"youness",new Date(),false,916));
		patientRepository.save(new Patient(null,"kaoutar",new Date(),false,102));
		patientRepository.save(new Patient(null,"doha",new Date(),false,189));
		patientRepository.save(new Patient(null,"omar",new Date(),true,131));
		patientRepository.save(new Patient(null,"abdelkarim",new Date(),false,816));
		patientRepository.save(new Patient(null,"abderahman",new Date(),false,916));

		//Consulter tous les patients
		System.out.println("la liste des patients .....");
		System.out.println("...............................................................");
		List<Patient> patients=patientRepository.findAll();
		patients.forEach(p->{
			System.out.println(p.toString());
		});

		System.out.println("................................................................");
		System.out.println("Chercher les 3 premieres patients de la page 0 ");

		//Chercher les 3 premiers patients de la page 0
		Page<Patient> cherchePatients=patientRepository.findAll(PageRequest.of(0,3));
		System.out.println("*********************************************");
		System.out.println("total pages :"+cherchePatients.getTotalPages());
		System.out.println("total elements :"+cherchePatients.getTotalElements());
		System.out.println("page numero :"+cherchePatients.getNumber());
		System.out.println("**********************************************");
		cherchePatients.forEach(p->{
			System.out.println(p.toString());
			System.out.println("**********************");
		});
		System.out.println("................................................................");
		System.out.println("cherche les patients sont malades ");
		List<Patient> patientsMaldes=patientRepository.findByMalade(true);
		patientsMaldes.forEach(p->{
			System.out.println(p.toString());
			System.out.println("**********************");
		});

		System.out.println("................................................................");
		System.out.println("Consulter un patient selon identification ID");

		// Consulter un patient selon ID
		Patient patient=patientRepository.findById(1L).orElse(null);
		if(patient!=null) {
			System.out.println("id : " + patient.getId());
			System.out.println("Nom : " + patient.getNom());
			System.out.println("date naissance : " + patient.getDateNaissance());
			System.out.println("malade : " + patient.isMalade());
			System.out.println("Score : " + patient.getScore());
		}
		System.out.println(".............................................................");
		//mettre a jour le patient
		patient.setScore(128);
		patientRepository.save(patient); //cette fois ci pour modifier le patient
		System.out.println("le score de patient ait le ID=1 a ete changer");
		//Supprimer un patient
		patientRepository.deleteById(2L);
		System.out.println("le patient ID=2 a ete supprimer dans la base de donnees");

	}
}
