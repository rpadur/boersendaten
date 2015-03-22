package de.padur.boersendaten;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.padur.boersendaten.datenquelle.godmodetrader.FundamentalDatenErmittler;
import de.padur.boersendaten.dto.AktienDatenDTO;

/**
 * 
 */

/**
 * @author ronald.padur
 *
 */
public class TestHelper {

	public Document getBeispielDocument() throws Exception{
		File mcdonaldsdaten = new File(
				"src/main/test/testdaten/aktie_mcdonalds_fundamentaldaten.html");
		final BufferedReader reader = new BufferedReader(new FileReader(
				mcdonaldsdaten));
		StringBuffer htmlBuffer = new StringBuffer();
		String line = new String();
		while ((line = reader.readLine()) != null) {
			htmlBuffer.append(line).append('\n');
		}
		reader.close();
		final Document doc = Jsoup.parse(htmlBuffer.toString());
		return doc;
	}
	
	public AktienDatenDTO getBeispielFundamentalDatenAktienDatenDTO() throws Exception{
		final FundamentalDatenErmittler ermittler = new FundamentalDatenErmittler();
		AktienDatenDTO dto = new AktienDatenDTO();
		ermittler.evaluateStockPageFundamentalData(getBeispielDocument(), dto);
		return dto;
	}
}
