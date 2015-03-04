/**
 * 
 */
package de.padur.boersendaten;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.padur.boersendaten.dto.AktienDatenDTO;
import de.padur.boersendaten.dto.JahresWertTupel;

/**
 * @author Ronald
 *
 */
public class Datensammler {

	
	private List<AktienDatenDTO> aktien = new ArrayList<AktienDatenDTO>();
	private static String BASE_URL = "http://www.godmode-trader.de";
	private Elements tabellendaten;

	public static void main(String[] args) {
		final Datensammler sammler = new Datensammler();
		sammler.startEvaluation();

	}

	private void evaluateIndexPage(final Document doc) throws IOException{
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String linkToStock = link.attr("href");
			if (linkToStock.startsWith("/aktien")) {
				// get the value from href attribute
				System.out.println("\nlink : " + linkToStock);
				System.out.println("Aktie : " + link.text());
				final AktienDatenDTO aktienDto = new AktienDatenDTO();
				aktienDto.setName(link.text());
				final Document fundamentalData = getPage(BASE_URL+linkToStock + "/kennzahlen") ;
			evaluateStockPageFundamentalData(fundamentalData, aktienDto);
			aktien.add(aktienDto);
			}
		}
	}
	
	private void evaluateStockPageFundamentalData(final Document doc, AktienDatenDTO dto){
		String title = doc.title();
		System.out.println("title : " + title);
		
		
		Elements fundametaldaten = doc.select("table:contains(2013) > tbody");
		for (Element daten : fundametaldaten) {
			
			// zuerst kommt der Tabellenkopf	
			tabellendaten = daten.select("tr");
			for (Element element : tabellendaten) {
				Elements spaltendaten = element.select("td");
				if(spaltendaten.size()!=3){
					continue;
				}
				
				final JahresWertTupel tupel2013 = new JahresWertTupel("2013", spaltendaten.get(1).text());
				final JahresWertTupel tupel2014 = new JahresWertTupel("2014", spaltendaten.get(2).text());
				final List<JahresWertTupel> tupel = new ArrayList<JahresWertTupel>();
				tupel.add(tupel2013);
				tupel.add(tupel2014);
				dto.getDaten().put(spaltendaten.get(0).text(),tupel);
			}
			
			
				
		}
	}
	
	private void startEvaluation() {
		Document doc;
		try {

			// need http protocol
			doc = getPage(BASE_URL+"/indizes/dax-performance-index-kurs,133962/komponenten");
			evaluateIndexPage(doc);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (AktienDatenDTO aktienDatenDTO : aktien) {
			System.out.println(aktienDatenDTO);
		}
		
	}

	private Document getPage(String url) throws IOException {
		final Document doc = Jsoup.connect(url).get();
		return doc;
	}
}
