/**
 * 
 */
package de.padur.boersendaten;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.padur.boersendaten.bo.Aktie;
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

		aktienrepository.save(new Aktie("Bayer AG", "BAY001", "DE000BAY0017"));
		aktienrepository.save(new Aktie("BayWa AG", "519406", "DE0005194062"));

		// fetch all customers
		System.out.println("Aktien found with findAll():");
		System.out.println("-------------------------------");
		for (Aktie customer : aktienrepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Aktien found with findByWKN('BAY001'):");
		System.out.println("--------------------------------");
		System.out.println(aktienrepository.findByWkn("BAY001"));

		System.out.println("Aktien found with findByName('Bay'):");
		System.out.println("--------------------------------");
		for (Aktie aktie : aktienrepository.findByName("Bay*")) {
			System.out.println(aktie);
		}

	}

}
