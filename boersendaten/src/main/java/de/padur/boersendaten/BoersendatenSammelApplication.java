/**
 * 
 */
package de.padur.boersendaten;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import de.padur.boersendaten.bo.Aktie;
import de.padur.boersendaten.converter.AktienDTONachAktieConverter;
import de.padur.boersendaten.converter.FileToDocumementConverter;
import de.padur.boersendaten.dto.AktienDatenDTO;
import de.padur.boersendaten.repository.AktienRepository;
import de.padur.boersendaten.web.Page;

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
		// final List<String> indizes = getAlleIndizes();
		// System.out.println("Es wurden " + indizes.size() + " gefunden.");
		// sammleAktienDatenImWeb(indizes);
		sammleAktienDatenAusDateien();
		// fetch all Aktien
		System.out.println("Aktien found with findAll():");
		System.out.println("-------------------------------");
		List<Aktie> aktien = aktienrepository.findAll();
		for (Aktie aktie : aktien) {
			System.out.println(aktie);
		}
		System.out.println("Es gibt "+aktien.size()+" Aktien.");
		RestTemplate template = new RestTemplate();
		template.getForObject("localhost:8080/aktien", Page.class);

	}

	private void sammleAktienDatenAusDateien() throws Exception {
		String pathname = "D:\\privat\\boersendaten_workspace\\testdaten\\";
		File testdatenverzeichnis = new File(pathname);
		String[] list = testdatenverzeichnis.list();
		for (int i = 0; i < list.length; i++) {
			
			if (list[i].startsWith("_aktien") && !list[i].contains("kennzahlen")) {
				String aktienname = list[i].substring(8, list[i].indexOf("-kurs"));
				String id= list[i].substring(list[i].indexOf(",")+1, list[i].indexOf(".html"));
				
				System.out.println(aktienname+ "\t"+id);
				AktienDatenDTO aktienDto = new AktienDatenDTO();
				aktienDto.setName(aktienname);
				sammler.evaluateStockData(
						FileToDocumementConverter.getDocument(new File(pathname
								+ list[i])), aktienDto);
				sammler.evaluateFundamentalData(FileToDocumementConverter.getDocument(new File(pathname+"_aktien_"+aktienname+"-kurs,"+id+"_kennzahlen.html")), aktienDto);
				convertiereUndSpeichereAktie(aktienDto);
			}
		}
	}

	private List<String> getAlleIndizes() {
		return sammler.getAllIndizes();
	}

	private void sammleAktienDatenImWeb(List<String> indizes) {
		for (String index : indizes) {
			List<AktienDatenDTO> startEvaluation = sammler
					.startEvaluation(index);
			for (AktienDatenDTO aktienDatenDTO : startEvaluation) {
				convertiereUndSpeichereAktie(aktienDatenDTO);
			}
		}

	}

	private void convertiereUndSpeichereAktie(AktienDatenDTO aktienDatenDTO) {
		final Aktie aktie = aktienDTOConverter.convertAktienDTO(aktienDatenDTO);
		aktienrepository.save(aktie);
	}

}
