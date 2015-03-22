/**
 * 
 */
package de.padur.boersendaten;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import de.padur.boersendaten.datenquelle.godmodetrader.FundamentalDatenErmittler;
import de.padur.boersendaten.dto.AktienDatenDTO;
import de.padur.boersendaten.dto.JahresWertTupel;

/**
 * @author Ronald
 *
 */
public class Datensammler {

	private List<AktienDatenDTO> aktien = new ArrayList<AktienDatenDTO>();
	private static String BASE_URL = "http://www.godmode-trader.de";
	@Autowired
	private FundamentalDatenErmittler fundamentalDatenermittler;

	public static void main(String[] args) {
		final Datensammler sammler = new Datensammler();
		final List<String> indizes = sammler.getAllIndizes();
		for (String index : indizes) {
			List<AktienDatenDTO> startEvaluation = sammler
					.startEvaluation(index);
			for (AktienDatenDTO aktienDatenDTO : startEvaluation) {
				System.out.println(aktienDatenDTO);
			}
		}

	}

	private void evaluateIndexPage(final Document doc) throws IOException {
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String linkToStock = link.attr("href");
			if (linkToStock.startsWith("/aktien")) {
				// get the value from href attribute
				// System.out.println("\nlink : " + linkToStock);
				// System.out.println("Aktie : " + link.text());
				final AktienDatenDTO aktienDto = new AktienDatenDTO();
				aktienDto.setName(link.text());
				final Document customData = getPage(BASE_URL + linkToStock);
				evaluateStockData(customData, aktienDto);
				final Document fundamentalData = getPage(BASE_URL + linkToStock
						+ "/kennzahlen");
				fundamentalDatenermittler.evaluateStockPageFundamentalData(fundamentalData, aktienDto);
				aktien.add(aktienDto);
			}
		}
	}

	private void evaluateStockData(Document doc, AktienDatenDTO aktienDto) {
		Elements aktienDaten = doc
				.select("table:contains(Bezeichnung) > tbody > tr");
		// System.out.println(aktienDaten.text());
		for (Element element : aktienDaten) {
			Elements select = element.select("td");
			if (select.get(0).text().equals("WKN")) {
				aktienDto.setWkn(select.get(1).text());
			}
			if (select.get(0).text().equals("ISIN")) {
				aktienDto.setIsin(select.get(1).text());
			}
		}
	}

	

	public List<String> getAllIndizes() {
		final Set<String> indizes = new HashSet<String>();
		try {
			Document indexPage = getPage(BASE_URL + "/kurse-und-charts");
			Elements links = indexPage.select("a[href]");
			for (Element index : links) {

				String linkToStock = index.attr("href");
				if (linkToStock.startsWith("/indizes")
						&& !linkToStock.endsWith("/komponenten")) {
					System.out.println("\nlink : " + linkToStock);
					System.out.println("Index : " + index.text());
					indizes.add(linkToStock);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>(indizes);
	}

	public List<AktienDatenDTO> startEvaluation(final String index) {
		try {

			String url = BASE_URL + index + "/komponenten";
			System.out.println(url);
			final Document doc = getPage(url);
			evaluateIndexPage(doc);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return aktien;

	}

	private Document getPage(String url) throws IOException {

		System.out.println(url);
		String filename = url.replace(BASE_URL, "").replaceAll("/","_");
		File file = new File("D:\\privat\\boersendaten_workspace\\testdaten\\" +filename+".html");
		System.out.println(file.getAbsolutePath());
		FileWriter writer = new FileWriter(file, false);
		writer.write("Test");
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(500000).get();
		} catch (ConnectException e) {
			System.out.println(e);
		}
		if (doc != null) {
			writer.write(doc.html());
		}
		writer.flush();
		writer.close();
		return doc;
	}
}
