/**
 * 
 */
package de.padur.boersendaten;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.padur.boersendaten.bo.Aktie;
import de.padur.boersendaten.dto.AktienDatenDTO;
import de.padur.boersendaten.repository.AktienRepository;

/**
 * @author ronald.padur
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private AktienRepository aktienrepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	public void run(String... arg0) throws Exception {
		// alles löschen
		aktienrepository.deleteAll();

		final List<Aktie> aktien = sammleAktienDaten();
		
		for (Aktie aktie : aktien) {
			aktienrepository.save(aktie);
		}

		// fetch all customers
		System.out.println("Aktien found with findAll():");
		System.out.println("-------------------------------");
		for (Aktie aktie : aktienrepository.findAll()) {
			System.out.println(aktie);
		}

		

	}

	private List<Aktie> sammleAktienDaten() {
		final List<Aktie> aktien = new ArrayList<Aktie>();
		final Datensammler sammler = new Datensammler();
		List<AktienDatenDTO> startEvaluation = sammler.startEvaluation();
		for (AktienDatenDTO aktienDatenDTO : startEvaluation) {
			final Aktie aktie = new Aktie(aktienDatenDTO.getName(), aktienDatenDTO.getWkn(), aktienDatenDTO.getIsin());
			aktien.add(aktie);
		}
		return aktien;
	}

}
