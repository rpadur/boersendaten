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

	@Autowired
	private Datensammler sammler;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	public void run(String... arg0) throws Exception {
		// alles l�schen
aktienrepository.deleteAll();
		final List<String> indizes = getAlleIndizes();
		System.out.println("Es wurden "+indizes.size() +" gefunden.");
		sammleAktienDaten(indizes);

		// fetch all Aktien
		System.out.println("Aktien found with findAll():");
		System.out.println("-------------------------------");
		for (Aktie aktie : aktienrepository.findAll()) {
			System.out.println(aktie);
		}

	}

	private List<String> getAlleIndizes() {
		return sammler.getAllIndizes();
	}

	private void sammleAktienDaten(List<String> indizes) {
		for (String index : indizes) {
			List<AktienDatenDTO> startEvaluation = sammler
					.startEvaluation(index);
			for (AktienDatenDTO aktienDatenDTO : startEvaluation) {
				final Aktie aktie = aktienDTOConverter.convertAktienDTO(aktienDatenDTO);
				aktienrepository.save(aktie);
			}
		}

	}

}
