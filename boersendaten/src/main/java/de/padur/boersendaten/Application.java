/**
 * 
 */
package de.padur.boersendaten;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.padur.boersendaten.bo.Aktie;
import de.padur.boersendaten.converter.AktienDTONachAktieConverter;
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

	@Autowired
	private AktienDTONachAktieConverter aktienDTOConverter;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	public void run(String... arg0) throws Exception {
		// alles löschen
//		aktienrepository.deleteAll();
		final List<String> indizes = getAlleIndizes();
		sammleAktienDaten(indizes);

//		// fetch all customers
//		System.out.println("Aktien found with findAll():");
//		System.out.println("-------------------------------");
//		for (Aktie aktie : aktienrepository.findAll()) {
//			System.out.println(aktie);
//		}

	}

	private List<String> getAlleIndizes() {
		final Datensammler sammler = new Datensammler();
		return sammler.getAllIndizes();
	}

	private void sammleAktienDaten(List<String> indizes) {
		final Datensammler sammler = new Datensammler();
		for (String index : indizes) {
			List<AktienDatenDTO> startEvaluation = sammler
					.startEvaluation(index);
			for (AktienDatenDTO aktienDatenDTO : startEvaluation) {
				final Aktie aktie = aktienDTOConverter.convertAktienDTO(aktienDatenDTO);
				//aktienrepository.save(aktie);
			}
		}

	}

}
