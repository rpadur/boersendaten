/**
 * 
 */
package de.padur.boersendaten;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.padur.boersendaten.web.AktieRestDTO;
import de.padur.boersendaten.web.AktienResources;
import de.padur.boersendaten.web.RestClientConfiguration;

/**
 * @author ronald.padur
 *
 */
@Service
public class BoersendatenWebApplication {

	private static final String URL = "http://localhost:8080/aktien/";

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {

		final BoersendatenWebApplication app = new BoersendatenWebApplication();
		app.run();
	}

	private void run() {
		restTemplate = new RestClientConfiguration().restTemplate();
		AktienResources aktienResourcen = restTemplate.getForObject(URL,
				AktienResources.class);

		for (Link link : aktienResourcen.getLinks()) {
			if ("aktie".equals(link.getRel())) {
				AktieRestDTO aktie = restTemplate.getForEntity(link.getHref(),
						AktieRestDTO.class).getBody();
				System.out.println(aktie);
			}
		}

	}
}
